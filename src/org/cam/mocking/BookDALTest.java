package org.cam.mocking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.*;

public class BookDALTest {
	//inject the mocked version of the BookedDAL
	private static BookDAL mockedBookDal;
	private static Book book1;
	private static Book book2;
	
	@BeforeClass
	public static void setUp() throws Exception {
		//Create mock object of BookDAL
		mockedBookDal = Mockito.mock(BookDAL.class);
		
	//create a few instances of Book class
		book1 = new Book("8131721019","Compilers Principles",
            Arrays.asList("D. Jeffrey Ulman","Ravi Sethi", "Alfred V. Aho", "Monica S. Lam"),
            "Pearson Education Singapore Pte Ltd", 2008,1009,"BOOK_IMAGE");
		
		book2 = new Book("9788183331630","Let Us C 13th Edition",
	            Arrays.asList("Yashavant Kanetkar"),"BPB PUBLICATIONS", 2012,675,"BOOK_IMAGE");
		
		//stub the methods of mocked BookDAL with mocked Data
		
		//Stubbed method one: When someone calls getAllBooks, return the two books I have created above. 
		Mockito.when(mockedBookDal.getAllBooks()).thenReturn(Arrays.asList(book1,book2));
		
		//Stubbed method two: 
		Mockito.when(mockedBookDal.getBook("8131721019")).thenReturn(book1);
		Mockito.when(mockedBookDal.addBook(book1)).thenReturn(book1.getIsbn());
		Mockito.when(mockedBookDal.updateBook(book1)).thenReturn(book1.getIsbn());
		}
	  
		@Test
		public void testGetAllBooks() throws Exception {
		  List<Book> allBooks = mockedBookDal.getAllBooks();
		    assertEquals(2, allBooks.size());
		    Book myBook = allBooks.get(0);
		    assertEquals("8131721019", myBook.getIsbn());
		    assertEquals("Compilers Principles", myBook.getTitle());
		    assertEquals(4, myBook.getAuthors().size());
		    assertEquals((Integer)2008, myBook.getYearOfPublication());
		    assertEquals((Integer) 1009, myBook.getNumberOfPages());
		    assertEquals("Pearson Education Singapore Pte Ltd", myBook.getPublication());
		    assertEquals("BOOK_IMAGE", myBook.getImage());
	  }

	  public void testGetBook() throws Exception {
		  String isbn = "8131721019";

		    Book myBook = mockedBookDal.getBook(isbn);

		    assertNotNull(myBook);
		    assertEquals(isbn, myBook.getIsbn());
		    assertEquals("Compilers Principles", myBook.getTitle());
		    assertEquals(4, myBook.getAuthors().size());
		    assertEquals("Pearson Education Singapore Pte Ltd", myBook.getPublication());
		    assertEquals((Integer)2008, myBook.getYearOfPublication());
		    assertEquals((Integer)1009, myBook.getNumberOfPages());
	  }

	  public void testAddBook() throws Exception {
		    String isbn = mockedBookDal.addBook(book1);
		    assertNotNull(isbn);
		    assertEquals(book1.getIsbn(), isbn);
	  }

	  public void testUpdateBook() throws Exception {
		  String isbn = mockedBookDal.updateBook(book1);
		    assertNotNull(isbn);
		    assertEquals(book1.getIsbn(), isbn);
	  }
}
