package gr.university.library.util;

//import ολες τις βιλιοθήκες που θα χρειαστούμε
import java.util.ArrayList;

import gr.university.library.model.LibraryItem;
import gr.university.library.model.Member;
import gr.university.library.model.Loan;


//η κλάση ReportGenerator ειναι final καθώς δεν θέλουμε να κληρονομηθεί
//οι μεθόδοι static ,δεν χρειάζεται instantiation συνεπώς και ο constructor θα είναι private
//έτσι απαγορέυομε την δημιουργία αντικειμένου ReportGenerator

public final class ReportGenerator {
      
    
    
    private ReportGenerator(){}
    
    
    //εμφάνιση περιγραφής βιβλίου στην λίστα
    public static void printAllItems(ArrayList<LibraryItem> items){
        if(items.isEmpty()) {
            System.out.println("Η βιβλιοθήκη είναι άδεια!");
        }else{
            System.out.println("Υλικό βιβλιοθήκης: ");
            for(LibraryItem item : items) {
                System.out.println(item.getDescription());

            }
        }

    }  
    //εμφάνιση ενεργών δανεισμών
    public static void printActiveLoans(ArrayList<Loan> activeLoans){
        if(activeLoans.isEmpty()){
            System.out.println("Δεν υπάρχουν ενεργοί δανεισμοί!");
        }else{
            System.out.println("Ενεργοί δανεισμοί: ");
            for(Loan loan : activeLoans){
                System.out.println(loan);
            }

        }
            


    }
        
    
    
    
    //εμφάνιση βασικών στατιστικών (static στοιχεία των κλάσεων)
    public static void printStatistics(){
        System.out.println("Πλήθος εγγραφών υλικού βιβλιοθήκης: " + LibraryItem.getTotal() + "\nΠλήθος μελών: "+ Member.getTotalMembers() + "\nΠλήθος ενεργών δανεισμών: " + Loan.getActiveLoans());
        
        
        
    }

    
}
