package gr.university.library.model;

import java.time.LocalDate;
import java.io.Serializable;

//υλοποιήση της κλάσης δανεισμόυ χρησιμοποιώντας και αντικείμενα απο κλάσεις Member,LibraryItem
//καλούμε την κλάση LocalDate από την java.time για την εύρεση των ημερομηνιών
public class Loan implements Serializable{
    private static final long serialVersionUID = 1L;
    private Member member; //πεδίο με το μέλος που δανείζεται.Στην πραγματικότητα είναι αντικείμενο έναν εκ των ProfessorMember,StudentMember
    private LibraryItem libraryItem;//πεδίο με το είδος αντικειμένου που δανείζεται.Magazine,Book,DigitalBook
    private LocalDate loanDate;
    private LocalDate returnDate;
    private static int loanCounter = 0;//μετρητής πόσοι δανεισμοί έχουν γίνει


    public Loan(Member member, LibraryItem libraryItem){
        
        

        setMember(member);
        setLibraryItem(libraryItem);
        this.loanDate = LocalDate.now();
        this.returnDate = null;
        ++loanCounter;

    }
    //αν returnDate = null δεν έχει επιστραφεί,ορίζουμε ημερομηνία επιστροφής και επιστρέφουμε αλήθεια.
    //αλλιώς επιστρέφουμε ψέμα
    public boolean endOfLoan(){ 
        
        if(returnDate ==null){
            this.returnDate = LocalDate.now();
            --loanCounter;//μειώνουμε τους ενεργούς δανεισμούς
            return true;
        }
        
        return false;

    }
    //update: επειδή το serialization δεν αποθηκεύει static πεδία πρέπει μέσω setter να δίνουμε στα static πεδία την σωστή τιμή
    public static void setLoanCounter(int loanCounter) {
        Loan.loanCounter = loanCounter;
    }
    public void setMember(Member member) {
        if(member == null){     //έλεγχος ώστε τo member να μην είναι null
            throw new IllegalArgumentException("Μη έγκυρη εισαγωγή μέλους");
        }
            this.member = member;
    }
    public void setLibraryItem(LibraryItem libraryItem) {
        if(libraryItem == null){     //έλεγχος ώστε τo libraryitem να μην είναι null
            throw new IllegalArgumentException("Μη έγκυρη εισαγωγή αντικειμένου βιβλιοθήκης");
        }
        this.libraryItem = libraryItem;
    }
    public boolean isActive() { // έλεγχος αν ο δανεισμός είναι ενεργός
        return returnDate == null;
    }

    public LibraryItem getLibraryItem() {
        return this.libraryItem;
    }
    public LocalDate getLoanDate() {
        return this.loanDate;
    }public static int getActiveLoans() {
        return loanCounter;
    }
    public Member getMember() {
        return this.member;
    }
    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    @Override
    public String toString() {
        return member.getName() + " δανείστηκε το " + libraryItem.getTitle() + " στις " + getLoanDate()+ (returnDate != null ? " | Επιστράφηκε: " + returnDate : " | Ενεργός");
  
    }

    
}
