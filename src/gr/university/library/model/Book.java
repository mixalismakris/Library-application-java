package gr.university.library.model;



//υλοποιήση κλάσης book που κληρονομεί τα κύρια χαρακτηριστηκά της απο την LibaryItem
//Δεν χρείαζεται να κάνουμε implement το interface borrowable, αφου το κάνει η υπερκλάση
public class Book extends LibraryItem {
    private static final long serialVersionUID = 1L;

    private String author;
    private String isbn;

    public Book(String code , String title, int year, String author, String isbn){
        super(code,title,year);  //Καλείται ο constructor από την υπερκλάση LibaryItem για τα πρώτα τρια στοιχεία
        setAuthor(author);
        setIsbn(isbn);
    }
    
    //υλοποιήση setters

    public void setAuthor(String author){
        if(author == null || author.strip().equals("")){
            throw new IllegalArgumentException("Μη έγκυρο όνομα συγγραφέα!");
        }
        this.author = author;
    }
    
    public void setIsbn(String isbn){
        if(isbn == null || isbn.strip().equals("")){
            throw new IllegalArgumentException("Μη έγκυρο isbn!");
        }
        this.isbn = isbn;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getIsbn(){
        return this.isbn;
    }

    //υλοποίηση της abstract μεθόδου getDescription με χρήση override

    @Override
    public String getDescription(){
        return " Βιβλίο " + getTitle() + ",γραμμένο από τον/την " + getAuthor() + " το " + getYear() + ".(ISBN: " + getIsbn() + ")";
    }

    @Override
    public String toString(){
        return super.toString() + " | Συγγραφέας: " + getAuthor() + " | ISBN: " + getIsbn();  //Χρησιμοπούμε το toString της υπερκλάσης LibraryItem και τον συμπληρώνουμε με τα υπόλοιπα στοιχεία
    }
}
