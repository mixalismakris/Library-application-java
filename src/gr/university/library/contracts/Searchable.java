package gr.university.library.contracts;

import gr.university.library.model.Member;        //φορτώνουμε τις απαραίτητες κλάσεις,αφού βρίσκονται σε διαφορετικό package
import gr.university.library.model.LibraryItem;

public interface Searchable {  //Ορίζουμε τις συμπεριφορές αναζήτησης
    
    LibraryItem findItemByCode(String code); //Αναζήτηση και εύρεση αντικειμένου βιβλιοθήκης μέσω κωδικού
    Member findMemberByID(String id);  //αναζήτηση μέλους μέσω id
    
}
