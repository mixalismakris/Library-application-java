package gr.university.library.service;                                                                   
import java.util.ArrayList;                                 //κάνουμε import Array Lists, 
import gr.university.library.contracts.Searchable;          //το interface searchable
import gr.university.library.model.*;                     //και τα models,όπου υλοποιούνται οι κλάσεις με τις κύριες λειτουργίες του προγράμματος μας
import java.io.Serializable;

//κεντρική κλάση Library όπου εμπεριέχεται η λίστα των μελών,υλικού βιβλιοθήκης και δανεισμών
//Υλοποιεί το interface Searchable, άρα πρέπει να υλοποιούνται και οι μεθόδοι του
public class Library implements Searchable,Serializable{
    private static final long serialVersionUID = 1L;
    private ArrayList<Member> members = new ArrayList<>();      //δήλωση των λιστών
    private ArrayList<LibraryItem> items = new ArrayList<>();
    private ArrayList<Loan> loans = new ArrayList<>();

    //προσθήκη μέλους στην λίστα
    public boolean addMember(Member member){
        if(member != null){
            if(findMemberByID(member.getId())!= null) throw new IllegalArgumentException("Το μέλος υπάρχει ήδη!");
            members.add(member);
            return true;
        }
        return false;

        
    }

    //προσθήκη υλικόυ βιβλιοθήκης στην λίστα
    public boolean addLibraryItem(LibraryItem item){
        if(item != null){
            if(!isValidCode(item.getCode())) return false;
            if(findItemByCode(item.getCode()) != null) throw new IllegalArgumentException("Το αντικείμενο υπάρχει ήδη στην βιβλιοθήκη!");
            items.add(item);
            return true;

        }
        return false;
    }


    //Υλοποιήση του findItemByCode απο το interface
    //Αν βρεθεί ίδιος κωδικός,επιστρέφουμε το αντίστοιχο αντικείμενο,αλλιώς επιστρέφουμε null

    @Override
    public LibraryItem findItemByCode(String code){
        if(!isValidCode(code)) return null;
        for(LibraryItem item : items){
            if(item.getCode().equals(code)) return item;
        }
        return null;
    }

    //παρόμοια υλοποίηση για την findByTitle
    public LibraryItem findItemByTitle(String title){
        for(LibraryItem item : items){
            if(item.getTitle().equals(title)) return item;
        }
        return null;
    }
    
    //η υλοποίηση της findByAuthor διαφέρει σημαντικά,καθώς ένας συγγραφέας μπορεί να έχει γράψει πολλά βιβλία
    //η μέθοδος είναι τύπου ArrayList<Book>, αφού θα επιστρέφει τα βιβλία που έχουν γραφτεί απο τον συγκεκριμένο συγγραφέα

    public ArrayList<Book> findItemByAuthor(String author){
        ArrayList<Book> booksByAuthor = new ArrayList<>();
        for(LibraryItem item : items){              //πεδίο Author εμπεριέχουν μόνο τα βιβλία και οι υποκλάσεις του,συνεπώς θα ήταν λανθασμένος ο έλεγχος 
                                                    //συγγραφέα σε μη βιβλίου. Έτσι γίνεται επιπλέον έλεγχος αν το συγκεκριμένο item είναι τύπος βιβλίου
            if(item instanceof Book){               // και κάνουμε downcasting το item σε book,εξασφαλίζοντας στον compiler ότι το item έχει μέθοδο getAuthor
                if(((Book)item).getAuthor().equals(author)){
                    booksByAuthor.add((Book)item);
                }
            }                   
        }
        return booksByAuthor;
    }

    //Υλοποιήση της findMemberById από το interface
    //Προσπελάυνουμε την λίστα με τα μέλη και ελέγχουμε αν υπάρχει μέλος με το αντίστοιχο id.
    @Override
    public Member findMemberByID(String id){
        for(Member member : members){
            if(member.getId().equals(id)) return member;
        }
        return null;
    }
    
    //υλοποίηση μεθόδου δανεισμού σε μέλος
    public Loan loanToMember(Member member, LibraryItem item){
        if(member == null || item == null) return null;
        if(findMemberByID(member.getId()) != null && findItemByCode(item.getCode()) != null && item.borrowTo(member)) { //ελέγχουμε αν το μέλος και το αντικείμενο βιλιοθήκης υπάρχουν στα δεδομένα μας,αλλάζεο ταυτόχρονα η διαθεσιμότητα του βιβλίου
            Loan successfulLoan = new Loan(member,item); //αν υπάρχουν φτιάχνουμε νέο αντικείμενο δανεισμού
            loans.add(successfulLoan); //προσθέτουμε στην λίστα με τους δανεισμόυς τον δανεισμό
            return successfulLoan; //επιστροφή διαπιστευτηρίου επιτυχούς δανεισμού
        }
        return null; // αν ο δανεισμός δεν πετύχει,επιστρέφουμε null
        
    }

    //υλοποίηση μεθόδου επιστροφή δανεισμού

    public boolean returnItem(String code){
        if(!isValidCode(code)) return false;
        for(LibraryItem item : items){ //βρίσκω το αντικείμενο βιβλιοθήκης μέσω του κωδικού
            if(item.getCode().equals(code)){
                for(Loan loan : loans){  //προσπέλαση στη λίστα δανεισμών
                    if(loan.isActive() && loan.getLibraryItem() == item){ //έλεγχος αν ο δανεισμός είναι ενεργός και πρόκεται για το σωστό αντικείμενο βιβλιοθήκης
                        loan.endOfLoan(); //καλόυμε endOfLoan για να κλείσει ο δανεισμός
                        item.returnItem(); //ξανά γίνεται διαθέσιμο το αντικείμενο
                        return true;
                    }
                }
                return false; //αν φτάσουμε εδώ δεν ικανοποιούνται οι συνθήκες επιστροφής δανεισμού
            }   
        }
        return false; //αν φτάσουμε εδώ δεν ικανοποιούνται οι συνθήκες επιστροφής δανεισμού

    }

    //υλοποίηση μεθόδου acitve loans,εισάγουμε τους ενεργούς δανεισμούς σε λίστα και τους επιστρέφουμε
    public ArrayList<Loan> activeLoans(){
        ArrayList<Loan> active = new ArrayList<>();
        for(Loan loan : loans){
            if(loan.isActive()){
                active.add(loan);
                
            }
            
        }
        return active;
    }

    //package private μεθοδος για έλεγχο εγκυρότητας κωδικού
    boolean isValidCode(String code){
        return code != null && !code.strip().equals("");
    }
    

    //getters για τις λίστες

    public ArrayList<Loan> getLoans(){
        return loans;

    }

    public ArrayList<Member> getMembers(){
        return members;
    }

    public ArrayList<LibraryItem> getLibraryItems(){
        return items;
    }

}    
