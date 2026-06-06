package gr.university.library.contracts;

import gr.university.library.model.Member; //φορτώνουμε την κλάση Member , αφού χρησιμοποιείται απο το interface και βρίσκεται σε διαφορετικο package

public interface Borrowable { //Ορίζουμε την συμπεριφορά των αντικειμένων που μπορούν να δανειστόυν
    boolean borrowTo(Member member);   //δανείζουμε σε συγκεκριμένο μέλος
    boolean returnItem();  //επιστρέφουμε αντικείμενο στην βιβλιοθήκη
    boolean isAvailable(); //έλεγχος αν είναι διαθέσιμο αντικέιμενο της βιβλιοθήκης

    //Οι μεθόδοι έχουν απο default public abstract 

    
} 

