package gr.university.library.persistence;
import java.io.*; //import την κατάλληλη βιβλιοθήκη για file operations
import gr.university.library.service.Library;

    

public final class LibraryStorage {

    private LibraryStorage(){} //private constructor αφού δεν θέλουμε να δημιουργηθεί νέο αντικείμενο
    //boolean μέθοδος save όπου επιχειρούμε να αποθηκεύσουμε την τρέχον βιβλιοθήκη,επιστρέφουμε αλήθεια και κατάλληλο μήνυμα αν επιτυχώς και αντίσροφα αν αποτυχία
    public static boolean save(Library library , String filename){ 
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){
            out.writeObject(library);
            System.out.println("Επιτυχής αποθήκευση!");
            return true;
        }catch(IOException e){
            System.out.println("Σφάλμα αποθήκευσης: " + e.getMessage());
            return false;
        }

    }
    //μέθοδος τύπου Library όπου επιχειρούμε να διαβάσουμε βιβλιοθήκη από αρχείο και την επιστρέφουμε,σε περίπτωση σφάλματος επιστρέφουμε κατάλληλο μήνυμα
    public static Library load(String filename){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
            Library library = (Library) in.readObject(); //cast του περιεχομένου που διαβάζουμε σε τύπο library
            System.out.println("Επιτυχής φόρτωση βιβλιοθήκης");
            return library;
        }catch(FileNotFoundException e){
            System.out.println("Δεν βρέθηκε αρχείο αποθήκευσης, δημιουργία καινούργιου...");
            return null;
        }catch(IOException | ClassNotFoundException e){ //δυο exceptions με ενα catch
            System.out.println("Σφάλμα κατα την φόρτωση της βιβλιοθήκης: " + e.getMessage());
            return null;

        }

    }
    
}
