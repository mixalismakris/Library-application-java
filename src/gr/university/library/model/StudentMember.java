package gr.university.library.model;



//υλοποιήση κλάσης StudentMember που κληρονομεί στοιχεία και μεθόδους από την Member
public class StudentMember extends Member {
    private static final long serialVersionUID = 1L;
    private String registrationNumber;
    private String department;

    public StudentMember(String id, String name,String email,String registrationNumber,String department){//υλοποίηση κατασκευαστή αντικειμένου StudentMember υλοποιώντας τον κατασκευαστή της υπερκλάσης
        super(id,name,email); 
        setMaxLoanDays(calculateMaxLoanDays());
        setRegistrationNumber(registrationNumber);
        setDepartment(department);

    }

    public void setRegistrationNumber(String registrationNumber) {
        if(registrationNumber == null || registrationNumber.strip().equals("")){ //έλεγχος αν ο αριθμός μητρώου είναι κενός
            throw new IllegalArgumentException("Μη έγκυρος αριθμός μητρώου!");
        }
        
        this.registrationNumber = registrationNumber;
    }

    public void setDepartment(String department){
        if(department == null || department.strip().equals("")){ //έλεγχος αν το μάθημα είναι κενό
            throw new IllegalArgumentException("Μη έγκυρο τμήμα!");
        }
        
        this.department = department;

    }

    public String getRegistrationNumber(){
        return this.registrationNumber;
    }

    public String getDepartment(){
        return this.department;
    }

    @Override
    public int calculateMaxLoanDays(){ //υλοποίηση της abstract μεθόδου υπολογισμόυ ημερών για την κλάση student member
        return LibraryItem.DEFAULT_LOAN_DAYS; // φοιτητές δικαιούνται την προεπιλεγμένη διάρκεια δανεισμού
    }

    @Override
    public String toString(){
        return super.toString() + " | Αριθμός μητρώου: "+ getRegistrationNumber() + " | Τμήμα: "+ getDepartment(); //επιστρογή γενικής περιγραφής μέλους μαθητή αξιοποιώντας την toString της υπερκλάσης
    }

    
}
