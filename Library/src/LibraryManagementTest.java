import static org.junit.Assert.*;

import org.junit.Test;

public class LibraryManagementTest
{

	private Transaction testTransaction;
	private Book testBook;
	private Member testMember;

    @Test
    public void testBorrowReturn() throws Exception 
    {
    	
    	testTransaction = Transaction.getTransaction();
  	    testBook = new Book(200, "Book 200");
  	    testMember = new Member(100, "Member 100");
    	
    	
    	
    	// Test book is available to start
	   	assertTrue(testBook.isAvailable());
	   	
	    // Borrowing the book - expecting a success
        boolean borrowTestSuccess = testTransaction.borrowBook(testBook, testMember);
        // Assertions for borrowing success
        assertTrue(borrowTestSuccess);
        //Ensuring the test book is not available still after borrow
	    assertFalse(testBook.isAvailable());
	    //Ensuring the member's array list of borrowed books contains the book they just borrowed
        assertTrue(testMember.getBorrowedBooks().contains(testBook));
        // Attempting to borrow the book again, expecting book to be unvailable
        boolean borrowNotAvailable = testTransaction.borrowBook(testBook, testMember);
        // Ensuring that the member was not able to borrow the same book they already have 
	    assertFalse(borrowNotAvailable);

        // Return the book
	    boolean returnTestSuccess = testTransaction.returnBook(testBook, testMember);
	    assertTrue(returnTestSuccess);
	    assertTrue(testBook.isAvailable());
	    assertFalse(testMember.getBorrowedBooks().contains(testBook));

	    // Attempt to return the alreasy returned book again, expecting failure
        boolean returnFailure = testTransaction.returnBook(testBook, testMember);
        assertFalse(returnFailure);
    }
    
    @Test
    public void testBookId()
    {
        try
        {
        	//Instance 2 book objects with min and max valid Id integers
            Book book1 = new Book(100, "Minimum Valid Book ID");
            // assert Book id
            assertEquals(100, book1.getId());
            // check name
            assertEquals("Minimum Valid Book ID", book1.getTitle());

            Book book2 = new Book(999, "Maximum Valid Book ID");
            assertEquals(999, book2.getId());
            assertEquals("Maximum Valid Book ID", book2.getTitle());

        } 
        catch (Exception e)
        {
            fail("Invalid Book ID" + e.getMessage());
        }

        
        // Invalid Test Cases
        try 
        {
        	// Fail the test if this object is instanced
            new Book(99, "Invalid ID Test 1");
            fail("Expected exception for invalid book ID < 100.");
        } 
        catch (Exception e)
        {
        	// This error should be produced
            assertEquals("Invalid Book ID", e.getMessage());
        }

        
        try
        {
            new Book(1000, "Invalid ID Test 2");
            fail("Expected exception for invalid book ID = >999.");
        }
        catch (Exception e)
        {
            assertEquals("Invalid Book ID", e.getMessage());
        }

       
        try
        {
            new Book(-1, "Invalid ID Test 3");
            fail("Expected exception for invalid book ID = <0.");
        }
        catch (Exception e)
        {
            assertEquals("Invalid Book ID", e.getMessage());
        }

        
       
        try
        {
            new Book(0, "Zero ID Book");
            fail("Expected exception for invalid book ID = 0.");
        }
        catch (Exception e)
        {
            assertEquals("Invalid Book ID", e.getMessage());
        }
          
    }    
}