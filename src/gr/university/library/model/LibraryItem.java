package gr.university.library.model;

import java.time.Year;
import gr.university.library.contracts.Borrowable;
import java.io.Serializable;

// Abstract κλάση που αναπαριστά γενικό υλικό βιβλιοθήκης (βιβλίο, περιοδικό, ψηφιακό βιβλίο).
// Υλοποιεί το Borrowable interface ώστε όλα τα υλικά να μπορούν να δανείζονται/επιστρέφονται.
public abstract class LibraryItem implements Borrowable, Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String title;
    private int year;
    private boolean available ;
    private static int total = 0;  // μετρητής όλων των αντικειμένων βιβλιοθήκης
    public static final int DEFAULT_LOAN_DAYS = 14; // προεπιλεγμένη διάρκεια δανεισμού

    public LibraryItem(String code, String title, int year ){  /**Υλοποιήση κατασκευαστή με τα γενικά στοιχεία ενός αντικειμένου βιβλιοθήκης
                                                                με χρήση setters και ελέγχους εγκυρότητας*/
        
        
        setCode(code);
        setTitle(title);
        setYear(year);
        ++total;
        setAvailable(true);

    }

    //εφαρμογή των setters με κατάλληλους ελέγχους εγκυρότητας
    //update: επειδή το serialization δεν αποθηκεύει static πεδία πρέπει μέσω setter να δίνουμε στα static πεδία την σωστή τιμή
    public static void setTotal(int total) {
        LibraryItem.total = total;
    }
    public void setCode(String code){
        if(code == null || code.strip().equals("")){
            throw new IllegalArgumentException("Μη έγκυρος κωδικός!");

        }
        this.code = code;
    }

    public void setTitle(String title){
        if(title == null || title.strip().equals("")){
            throw new IllegalArgumentException("Μη έγκυρος τίτλος!");

        }
        this.title = title;
    }
    public void setYear(int year){
        int currentYear = Year.now().getValue();  //παίρνουμε την φετινή χρονία ώστε να βεβαιώσουμε ότι το year δεν το ξεπερνά
        if(year <=0 || year >currentYear){
            throw new IllegalArgumentException("Μη έγκυρη ημερομηνία έκδοσης!");

        }
        this.year = year;
    }

    protected void setAvailable(boolean available){ //protected ωστε να αποτραπεί τυχόν αλλαγή διαθεσιμότητας σε σημείο που δεν πρέπει
        this.available = available;
    }

    public static int getTotal(){
        return total;

    }

    public String getCode(){
        return this.code;
    }
    public String getTitle(){
        return this.title;
    }
    public int getYear(){
        return this.year;
    }

    

    public abstract String getDescription(); // δήλωση abstract μεθόδου που επιστρέφει περιγραφή η οποία θα εφαρμοστεί από υποκλάση

    //εφαρμόζουμε τις μεθόδους του interface borrowable

    @Override
    public boolean isAvailable(){  //έλεγχος διαθεσιμότητας
        return this.available;
    }

    @Override
    public boolean returnItem(){ //επιστροφή αντικειμένου βιβλιοθήκης και επιστρέφει αν ήτανμ επιτυχής ή όχι
        if(!isAvailable()){
            setAvailable(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean borrowTo(Member member){
        if (isAvailable()) {
            
            setAvailable(false);
            return true;
        }
        return false;
    }


    @Override
    public String toString(){
        return "Τίτλος: " + getTitle() +" | κωδικός: "+ getCode() +  " | Ημερομηνία έκδοσης: " + getYear() + " | "+(this.available ? " Διαθέσιμο" : " Δανεισμένο"); //εμφανίζουμε βασικά γενικά στοιχεία αντικειμένου βιβλιοθήκης

    }

    
}
