import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

	// Private static instance
    private static Transaction uniqueInstance;

    // Private constructor 
    private Transaction() {}

    // Public access method
    public static Transaction getTransaction()
    {
        if (uniqueInstance == null) 
        {
        	uniqueInstance = new Transaction();
        }
        return uniqueInstance;
    }
	
    // Perform the borrowing of a book
    public boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            System.out.println(transactionDetails);
            // Call saveTransaction method - feed method variable transactionDetails
            saveTransaction(transactionDetails);
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    public void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            System.out.println(transactionDetails);
            // Call saveTransaction method - feed method variable transactionDetails
            saveTransaction(transactionDetails);
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }

    // Get the current date and time in a readable format
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    
    
    
    
    private void saveTransaction(String stringDetails)
    {
    	// Try/catch to attempt to define BuferedWriter object to "transactions.txt"
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt", true)))
        {
        	// take string fed into method  write to file
            writer.write(stringDetails);
            // New line so next transaction on next line
            writer.newLine();
            //confirmation message
            System.out.println("Recipt saved to file.");
        } 
        catch (IOException e) 
        {
            System.out.println("IO: Could not save transaction." + e.getMessage());
        }
    }
    
    
    
  
}
