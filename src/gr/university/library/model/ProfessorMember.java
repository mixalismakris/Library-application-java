package gr.university.library.model;
//υλοποιήση κλάσης ProffesοrMember που κληρονομεί στοιχεία και μεθόδους από την Member
public class ProfessorMember extends Member {
    private String subject;
    private static final long serialVersionUID = 1L;  


    public ProfessorMember(String id, String name,String email,String subject){
        super(id, name, email); //κλήση του constructor της υπερκλάσης,δημιουργόντας τα στοιχεία που υπάρχουν σε αυτή 
        setMaxLoanDays(calculateMaxLoanDays());//καλούμε τον setter που βρίσκεται στην υπερκλάση με όρισμα την calculateMaxLoanDays που βρίσκεται σε αυτή την κλάση
        setSubject(subject);

        
    }

    public void setSubject(String subject){
        if(subject == null || subject.strip().equals("")){ //έλεγχος αν το μάθημα είναι κενό
            throw new IllegalArgumentException("Μη έγκυρο γνωστικό αντικείμενο!");
        }
        this.subject = subject;
    }

    public String getSubject(){
        return this.subject;
    }

    @Override
    public int calculateMaxLoanDays(){ //υλοποίηση της abstract μεθόδου υπολογισμόυ ημερών για την κλάση proffesor member
        return 30; // καθηγητές δικαιούνται μεγαλύτερη διάρκεια δανεισμού
    }



    @Override
    public String toString(){
        return super.toString() + "| Ειδικότητα: καθηγητής | Γνωστικό αντικείμενο: " + getSubject(); //επιστρογή γενικής περιγραφής μέλους καθηγητή αξιοποιώντας την toString της υπερκλάσης
    }
    
}
