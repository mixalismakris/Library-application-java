package gr.university.library.model;
//υλοποίηση κλάσης DigitalBook με διπλή κληρονομικότητα, υιοθετόντας τα πεδία και μεθόδους των Book,LibraryItem


public class DigitalBook extends Book {
    private double fileSize;
    private String fileType;
    private static final long serialVersionUID = 1L;

    public DigitalBook(String code, String title, int year,String author, String isbn, double fileSize,String fileType){
        super(code,title,year,author,isbn);//κλήση constructor της υπερκλάσης Book,συνεπώς και του constructor του LibraryItem
        setFileSize(fileSize);
        setFileType(fileType);
    }
    
    public void setFileSize(double fileSize){
        if(fileSize<= 0 ){
            throw new IllegalArgumentException("Μη έγκυρη χωρητικότητα αρχείου!");
        }
        this.fileSize = fileSize;
    }

    public void setFileType(String fileType){
        if(fileType == null || fileType.strip().equals("")){
            throw new IllegalArgumentException("Μη έγκυρη μορφή αρχείου!");
        }
        this.fileType = fileType;
    }

    public double getFileSize(){
        return this.fileSize;
    }

    public String getFileType(){
        return this.fileType;
    }

    @Override
    public String getDescription(){
       /*καλούμε την getDescription της υπερκλάσης κάνοντας την override,
        προσθέτοντας τα κατάλληλα στοιχεία
        */
        return "Ηλεκτρονικό " + super.getDescription() +" , με τύπο αρχείου " + getFileType() + " και χώρο " + getFileSize()+" MB."; 

    }
    @Override
    public String toString(){
        return super.toString() + " | Τύπος αρχείου: " +getFileType() + " | Χωρητικότητα αρχείου: " + getFileSize();
        //χρησιμοποιούμε την toString της υπερκλάσης προσθέτοντας τα υπόλοιπα στοιχεία
    }
}
