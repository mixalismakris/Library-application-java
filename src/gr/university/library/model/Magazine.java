package gr.university.library.model;
//υλοποιήση κλάσης magazine που κληρονομεί τα κύρια χαρακτηριστηκά της απο την LibaryItem
//Δεν χρείαζεται να κάνουμε implement το interface borrowable, αφου το κάνει η υπερκλάση

public class Magazine extends LibraryItem{
    private static final long serialVersionUID = 1L;

    private int issue;
    
    public Magazine(String code, String title, int year, int issue){ //υλοποίηση κατασκευαστή καλλόντας τον κατασκευαστή της υπερκλάσης και κάνοντας απαραίτητες προσθήκες
        super(code,title,year);
        setIssue(issue);
    }
    
    public void setIssue(int issue){
        if(issue <= 0 ){
            throw new IllegalArgumentException("Μη έγκυρη έκδοση!");
        }
        this.issue = issue;
    }
    public int getIssue(){
        return issue;
    }

    //υλοποίηση της abstract μεθόδου getDescription με χρήση override

    @Override
    public String getDescription(){
        return "Περιοδικό " + getTitle() + ",με έκδοση  "+ getIssue() +" εκδομένο το "  + getYear() ;
    }

    @Override
    public String toString(){
        return super.toString() + " | Έκδοση: "+ getIssue();//Χρησιμοπούμε το toString της υπερκλάσης LibraryItem και τον συμπληρώνουμε με τα υπόλοιπα στοιχεία
    }
}
