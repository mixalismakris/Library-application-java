package gr.university.library.model;
import java.io.Serializable;


public abstract class Member implements Serializable{//abstract κλάση member , όπου ορίζουμε τα βασικά κοινά στοιχέια των μελών
    private static final long serialVersionUID = 1L;  
    private String id;
    private String name;
    private String email;
    protected int maxLoanDays; // maxLoanDays protected έτσι ώστε να είναι ορατή από τις υποκλάσεις που θα την χρησιμοποιήσουν
    private static int totalMembers = 0; //κοινή μεταβλητή για όλα τα αντικείμενα μελών

    public Member (String id, String name,String email){
        setId(id);
        setName(name);
        setEmail(email);//επικαλλούμαστε setters για έλεγχο έγκυρης εισαγωγής δεδομένων
        
        ++totalMembers; //αύξηση του μετρητή totalMembers,αφού δημιουργήθηκε νέο μέλος
    }
    //Υλοποίηση setters. Σε περίπτωση λανθασμένης εισόδου πετάμε exception και την main ξανα ζητείται είσοδο απο τον χρήστη
    //update: επειδή το serialization δεν αποθηκεύει static πεδία πρέπει μέσω setter να δίνουμε στα static πεδία την σωστή τιμή
    public static void setTotalMembers(int totalMembers) {
        Member.totalMembers = totalMembers;
    }
    public void setEmail(String email){
        

        if(email == null || email.strip().equals("")){//έλεγχος κενού email
            throw new IllegalArgumentException("Μη έγκυρη εισαγωγή email.To email δεν μπορεί να είναι άδειο!");
        }
        else if(!email.contains("@")){ //έλεγχος αν το mail περιέχει @
            throw new IllegalArgumentException("Μη έγκυρη εισαγωγή email.To email πρέπει να περιεχει \"@\"");
        }
        this.email = email;  //Αν πέρασαν όλοι οι έλεχγοι,δίνεται η τιμή εισόδου στην μεταβλητή

    }
    //παρόμοιοι έλεχοι και στους υπόλοιπους setters
    public void setName(String name){
        if(name == null || name.strip().equals("")){
            throw new IllegalArgumentException("Μη έγκυρη εισαγωγή ονόματος.To όνομα δεν μπορεί να είναι άδειο!");
            
        }
        this.name = name;

    }
    public void setId(String id){
        if(id == null || id.strip().equals("")){
            throw new IllegalArgumentException("Μη έγκυρη εισαγωγή id.To id δεν μπορεί να είναι άδειο!");

        }
        this.id = id;

    }

    public void setMaxLoanDays(int maxLoanDays){
        if(maxLoanDays <= 0){
            throw new IllegalArgumentException("Παρακαλώ εισάγετε έγκυρο αριθμό ημερών");
        }

        this.maxLoanDays = maxLoanDays;

    }

    public abstract int calculateMaxLoanDays(); //αρχικοποίηση abstract μεθοδου υπολογισμού maxLoanDays που υλοποιείται στις υποκλάσεις

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }
    
    public int getMaxLoanDays(){
        return this.maxLoanDays;
    }

    public static int getTotalMembers(){
        return totalMembers;

    }

    @Override
    public String toString(){
        return "Όνομα: "+ this.name + " | id: "+ this.id + "| email: " + this.email;  //Επιστρογή γενικής περιγραφής μέλους

    }
    
}
