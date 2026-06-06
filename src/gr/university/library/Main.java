package gr.university.library;

import gr.university.library.persistence.LibraryStorage;
import java.util.ArrayList;
import java.util.Scanner;
//εισάγουμε όλα τα packages που έχουμε δημιουργήσει
import gr.university.library.service.Library;
import gr.university.library.util.ReportGenerator;
import gr.university.library.model.*;

public class Main {
    // δημιουργία αντικειμένου library μέσω του οποίου θα υλοποιούνται οι
    // λειτουργίες του προγράμματος
    // και scanner για τη εμφάνιση του menu.Δηλώνονται ως static ώστε να μπορούν να
    // χρησιμοποιηθούν από
    // την Main απευθείας χωρίς να δημιουργήσουμε αντικείμενο main

    private static Library library ;
    private static Scanner scanner = new Scanner(System.in);
    private static final String SAVE_FILE = "library.ser"; //ορισμός του ονόματος του αρχείου ως σταθερά

    public static void main(String[] args) {
        //φόρτωση της βιβλιοθήκης αν υπάρχει,αλλιώς δημιουργούμε καινούργια
        library = LibraryStorage.load(SAVE_FILE);
        if(library == null){
            library = new Library();
        }else{
            //φορτώθηκε βιβλιοθήκη επιτυχώς,κάνουμε update τα static πεδία με τους setters που δημιουργήσαμε
              LibraryItem.setTotal(library.getLibraryItems().size());
            Member.setTotalMembers(library.getMembers().size());
            Loan.setLoanCounter(library.activeLoans().size());
        }

        while (true) {
            System.out.println("""
                === University Library ===
                1. Προσθήκη υλικού βιβλιοθήκης
                2. Προσθήκη μέλους
                3. Αναζήτηση υλικού βιβλιοθήκης 
                4. Δανεισμός υλικού βιβλιοθήκης
                5. Επιστροφή υλικού βιβλιοθήκης
                6. Εμφάνιση ενεργών δανεισμών
                7. Στατιστικά βιβλιοθήκη
                8. Αποθήκευση δεδομένων
                0. Έξοδος και αποθήκευση
                Επιλογή:

                    """);

            int choice;

            try {
                choice = Integer.valueOf(scanner.nextLine().strip()); //έλεγχος αν δόθηκε αριθμός

            } catch (NumberFormatException e) {
                System.out.println("Παρακαλώ δώστε αριθμό ως επιλογή!");
                continue; //αν μη έγκυρη επιλογή,επιστροφή στο μενού
            }

            switch (choice) {
                //προσθήκη υλικού με τον χρήστη να επιλέγει τον τύπο του,
                //να δίνει τα στοιχεία του και το αντικείμενο προστίθεται στην βιβλιοθήκη
                case 1: {
                    System.out.println("Επιλέξτε τύπο υλικού βιβλιοθήκης: Κανονικό βιβλίο, ηλεκτρονικό βιβλίο , ή περιοδικό; (1 / 2 / 3): ");
                    int itemChoice;

                    while (true) {
                        // πιάνουμε IllegalArgumentException που πετιέται από τους setters των κλάσεων
                        // σε περίπτωση μη έγκυρων δεδομένων
                        try {
                            //έλεγχος αν η επιλογή είναι ακέραιος ή εντός των οριών που θέλουμε
                            //όπου αριθμός,διαβάζουμε με χρήση Integer.valueOf(scanner.nextLine()) ώστε να αποφευχθεί πατώντας enter να χαθεί μια σειρά    
                            itemChoice = Integer.valueOf(scanner.nextLine());
                            if (itemChoice == 1 || itemChoice == 2 || itemChoice == 3) break;
                            System.out.println("Παρακαλώ δώστε έγκυρη επιλογή! (1 / 2 / 3):");
                        } catch (NumberFormatException e) {
                            System.out.println("Παρακαλώ δώστε έγκυρη επιλογή! (1/2/3):");
                        }
                    }

                    if (itemChoice == 1) {
                        System.out.println("Κωδικός βιβλίου: ");
                        String code = scanner.nextLine();
                        System.out.println("Τίτλος βιβλίου: ");
                        String title = scanner.nextLine();
                        System.out.println("Χρονολογία έκδοσης (μόνο αριθμός): ");
                        int year = -1;
                        try {
                            year = Integer.valueOf(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Μη έγκυρη χρονολογία!");
                            break;
                        }
                        System.out.println("Συγγραφέας βιβλίου: ");
                        String author = scanner.nextLine();
                        System.out.println("ISBN βιβλίου: ");
                        String isbn = scanner.nextLine();

                        try {
                            //προσπάθεια δημιουργίας αντικειμένου,εμφανίζοντας κατάλληλο μήνυμα αν έχει δοθεί λάθος όρισμα.Παρομοίως και παρακάτω
                            Book book = new Book(code, title, year, author, isbn);
                            library.addLibraryItem(book);
                            System.out.println("Επιτυχής προσθήκη βιβλίου!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Σφάλμα: " + e.getMessage()); //εμφανίζεται ανάλογο μήνυμα για το error
                            break;
                        }

                    } else if (itemChoice == 2) {
                        System.out.println("Κωδικός βιβλίου: ");
                        String code = scanner.nextLine();
                        System.out.println("Τίτλος βιβλίου: ");
                        String title = scanner.nextLine();
                        System.out.println("Χρονολογία έκδοσης (μόνο αριθμός): ");
                        int year = -1;
                        try {
                            year = Integer.valueOf(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Μη έγκυρος αριθμός!");
                            break;
                        }
                        System.out.println("Συγγραφέας βιβλίου: ");
                        String author = scanner.nextLine();
                        System.out.println("ISBN βιβλίου: ");
                        String isbn = scanner.nextLine();
                        System.out.println("Χωρητικότητα αρχείου (μόνο αριθμός): ");
                        double fileSize = -1;
                        try {
                            fileSize = Double.valueOf(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Μη έγκυρη είσοδος!");
                            break;
                        }
                        System.out.println("Τύπος αρχείου: ");
                        String fileType = scanner.nextLine();

                        try {
                            DigitalBook digitalBook = new DigitalBook(code, title, year, author, isbn, fileSize, fileType);
                            library.addLibraryItem(digitalBook);
                            System.out.println("Επιτυχής προσθήκη ηλεκτρονικού βιβλίου!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Σφάλμα: " + e.getMessage());
                            break;
                        }

                    } else {
                        System.out.println("Κωδικός περιοδικού: ");
                        String code = scanner.nextLine();
                        System.out.println("Τίτλος περιοδικού: ");
                        String title = scanner.nextLine();
                        System.out.println("Χρονολογία έκδοσης (μόνο αριθμός): ");
                        int year = -1;
                        try {
                            year = Integer.valueOf(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Μη έγκυρη χρονολογία");
                            break;
                        }
                        System.out.println("Έκδοση περιοδικού (μόνο αριθμός): ");
                        int issue = -1;
                        try {
                            issue = Integer.valueOf(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Μη έγκυρη έκδοση!");
                            break;
                        }

                        try {
                            Magazine magazine = new Magazine(code, title, year, issue);
                            library.addLibraryItem(magazine);
                            System.out.println("Επιτυχής προσθήκη περιοδικού!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Σφάλμα: " + e.getMessage());
                            break;
                        }
                    }

                    break;
                }
                //προσθήκη μέλους
                case 2: {
                    //παρόμοια υλοποίηση με την πρώτη επιλογή
                    System.out.println("Επιλέξτε είδος μέλους, φοιτητής ή καθηγητής; (1 / 2):");
                    int memberChoice;

                    while (true) {
                        try {
                            memberChoice = Integer.valueOf(scanner.nextLine());
                            if (memberChoice == 1 || memberChoice == 2) break;
                            System.out.println("Παρακαλώ δώστε έγκυρη επιλογή! (1 / 2 )");
                        } catch (NumberFormatException e) {
                            System.out.println("Παρακαλώ δώστε έγκυρη επιλογή! (1 / 2)");
                        }
                    }

                    if (memberChoice == 1) {
                        System.out.println("ID μέλους: ");
                        String id = scanner.nextLine();
                        System.out.println("Όνομα μέλους: ");
                        String name = scanner.nextLine();
                        System.out.println("Email μέλους: ");
                        String email = scanner.nextLine();
                        System.out.println("Αριθμός Μητρώου: ");
                        String registrationNumber = scanner.nextLine();
                        System.out.println("Τμήμα: ");
                        String department = scanner.nextLine();

                        try {
                            StudentMember student = new StudentMember(id, name, email, registrationNumber, department);
                            library.addMember(student);
                            System.out.println("Επιτυχής εγγραφή φοιτητή!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Σφάλμα: " + e.getMessage());
                        }
                    } else {
                        System.out.println("ID μέλους: ");
                        String id = scanner.nextLine();
                        System.out.println("Όνομα μέλους: ");
                        String name = scanner.nextLine();
                        System.out.println("Email μέλους: ");
                        String email = scanner.nextLine();
                        System.out.println("Γνωστικό αντικείμενο: ");
                        String subject = scanner.nextLine();

                        try {
                            ProfessorMember professor = new ProfessorMember(id, name, email, subject);
                            library.addMember(professor);
                            System.out.println("Επιτυχής εγγραφή καθηγητή!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Σφάλμα: " + e.getMessage());
                        }
                    }

                    break;
                }
                //αναζήτηση υλικού με βάση κωδικό,τίτλο ή συγγραφέα
                case 3: {
                    System.out.println("Επιλέξτε τρόπο αναζήτησης, κωδικός,τίτλος ή συγγραφέας; (1 / 2 / 3)");
                    int searchChoice;

                    while (true) {
                        try {
                            searchChoice = Integer.valueOf(scanner.nextLine());
                            if (searchChoice == 1 || searchChoice == 2 || searchChoice == 3) break;
                            System.out.println("Παρακαλώ δώστε έγκυρη επιλογή!");
                        } catch (NumberFormatException e) {
                            System.out.println("Παρακαλώ δώστε έγκυρη επιλογή!");
                        }
                    }

                    if (searchChoice == 1) {
                        System.out.println("Παρακαλώ δώστε κωδικό υλικού βιβλιοθήκης: ");
                        String code = scanner.nextLine();
                        LibraryItem found = library.findItemByCode(code);
                        if (found != null) {  //αν υπάρχει εμφανίζουμε το αντικείμενο,αλλίως εμφανίζουμε κατάλληλο μήνυμα,αντίστοιχα και πιο κάτω
                            System.out.println(found);
                        } else {
                            System.out.println("Μη υπαρκτός κωδικός στην βιβλιοθήκη");
                        }

                    } else if (searchChoice == 2) {
                        System.out.println("Παρακαλώ δώστε τίτλο υλικού βιβλιοθήκης: ");
                        String title = scanner.nextLine();
                        LibraryItem found = library.findItemByTitle(title);
                        if (found != null) {
                            System.out.println(found);
                        } else {
                            System.out.println("Μη υπαρκτός τίτλος στην βιβλιοθήκη");
                        }

                    } else {
                        System.out.println("Παρακαλώ δώστε συγγραφέα βιβλίου: ");
                        String author = scanner.nextLine();
                        // findItemByAuthor επιστρέφει λίστα γιατί ένας συγγραφέας μπορεί να έχει 
                        // πολλά βιβλία,επιστρέφει κενή λίστα αν δεν βρεθεί κανένα
                        ArrayList<Book> results = library.findItemByAuthor(author);
                        if (results.isEmpty()) {
                            System.out.println("Μη υπαρκτός συγγραφέας στην βιβλιοθήκη");
                        } else {
                            for (Book book : results) {
                                System.out.println(book);
                            }
                        }
                    }

                    break;
                }
                //δανεισμός βιβλίου από υπάρχον μέλος
                case 4: {
                    System.out.println("Ποιο μέλος θέλει να δανειστεί; Δώστε το id του: ");
                    String id = scanner.nextLine();
                    Member member = library.findMemberByID(id);
                    if (member == null) {
                        System.out.println("Σφάλμα: το μέλος δεν υπάρχει!");
                        break;
                    }

                    System.out.println("Ποιο υλικό θέλει να δανειστεί; Δώστε τον κωδικό του: ");
                    String code = scanner.nextLine();
                    LibraryItem item = library.findItemByCode(code);
                    if (item == null) {
                        System.out.println("Σφάλμα: το υλικό δεν υπάρχει!");
                        break;
                    }

                    
                    //loanToMember αλλάζει αυτόματα τη διαθεσιμότητα και προσθέτει το Loan στη λίστα
                    // επιστρέφει null αν το υλικό δεν είναι διαθέσιμο
                    Loan loan = library.loanToMember(member, item);
                    if (loan != null) {
                        System.out.println("Επιτυχής δανεισμός!");
                    } else {
                        System.out.println("Το υλικό δεν είναι διαθέσιμο!");
                    }
                    
                    break;
                }
                //επιστροφή υλικού με βάση τον κωδικό
                case 5: {
                    System.out.println("Εισάγετε τον κωδικό του αντικειμένου προς επιστροφή: ");
                    String code = scanner.nextLine();

                    if (library.returnItem(code)) {
                        System.out.println("Επιτυχής επιστροφή!");
                    } else {
                        System.out.println("Μη έγκυρος κωδικός ή το βιβλίο δεν έχει δανειστεί!");
                    }

                    break;
                }
                case 6:
                    ReportGenerator.printActiveLoans(library.activeLoans());
                    break;

                case 7:
                    ReportGenerator.printStatistics();
                    break;
                case 8:
                    LibraryStorage.save(library, SAVE_FILE); //αποθήκευση της βιβλιοθήκης 
                    break;
                case 0:
                    LibraryStorage.save(library, SAVE_FILE);
                    System.out.println("Γίνεται έξοδος...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Παρακαλώ δώστε έγκυρη επιλογή");
            }
        }
    }
}