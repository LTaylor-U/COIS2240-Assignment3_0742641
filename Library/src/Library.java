import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Member> members = new ArrayList<Member>();
    private List<Book> books = new ArrayList<Book>();
  

    // Add a new member to the library
    public boolean addMember(Member member)
    {
    	// If find member by ID method returns a non-null value, there exists a member with that ID already
    	if (findMemberById(member.getId()) != null)
    	{
    		System.out.println("Member ID Error: There exists a member with book ID " + member.getId());
    		return false;
    	}
    	else
    	{
        members.add(member);
        return true;
    	}
    }
    
    // Add a new book to the library
    public boolean addBook(Book book)
    {
    	// If find book by ID method returns a non-null value, there exists a book with that ID already
    	if (findBookById(book.getId()) != null)
    	{
    		System.out.println("Book ID Error: There exists a book with book ID " + book.getId());
    		return false;
    	}
    	else
    	{
        books.add(book);
        return true;
    	}
    }

    
    // Find a member by ID
    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    // Find a book by ID
    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    // Get the list of members
    public List<Member> getMembers() {
        return members;
    }
    
    // Get the list of books
    public List<Book> getBooks() {
        return books;
    }
}
