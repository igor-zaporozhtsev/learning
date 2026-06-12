package interview.aggregate_data;

import cources.stepic.base.tutorial.Main_2.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LibraryService {

	 /*
       Return Map where:
       Key is Reader's Email (String);
       Value is List of Book Titles (String) that the reader has borrowed

       Note: handle edge cases:
       - Reader::getEmail not present in Loan::getReaderEmail
         → add Reader::getEmail with empty list
       - Loan::getBookIsbn not present in Book::getIsbn
         → Skip that Loan (book was removed from system)
       - Loan::isReturned() == true
         → Skip that Loan (only show currently borrowed books)
    */

	public static Map<String, List<String>> currentLoans(
		List<Book> books,
		List<Reader> readers,
		List<Loan> loans
	) {
		/*
		* expected output
		* email -> book_tittles
		*
		* */

		Map<String, List<String>> result = new HashMap<>();

		Map<Integer, String> readersMap = readers.stream()
			.collect(Collectors.toMap(Reader::getId, Reader::getEmail));

		Map<String, String> booksMap = books.stream()
			.collect(Collectors.toMap(Book::getIsbn, Book::getTitle));

		for (Reader reader : readers) {
			result.put(reader.email, new ArrayList<>());
		}

		for (Loan loan : loans) {
			String email = readersMap.get(loan.readerId); //read
			String tittle = booksMap.get(loan.bookIsbn); //write

			if (tittle == null) {
				return result;
			}

			List<String> tittles = result.get(email);
			if (!loan.isReturned() && email != null && tittles != null) {
				tittles.add(tittle);
			}
		}

		return result;
	}

	public static class Book {
		String isbn;        // "978-0-123456-78-9"
		String title;       // "Clean Code"

		public Book(String isbn, String title) {
			this.isbn = isbn;
			this.title = title;
		}

		public String getIsbn() {
			return isbn;
		}

		public String getTitle() {
			return title;
		}
	}

	public static class Reader {
		int id;
		String email;       // "john@example.com"

		public Reader(int id, String email) {
			this.id = id;
			this.email = email;
		}

		public int getId() {
			return id;
		}

		public String getEmail() {
			return email;
		}
	}

	public static class Loan {
		String bookIsbn;
		int readerId;
		boolean returned;

		public Loan(String bookIsbn, int readerId, boolean returned) {
			this.bookIsbn = bookIsbn;
			this.readerId = readerId;
			this.returned = returned;
		}

		public String getBookIsbn() {
			return bookIsbn;
		}

		public int getReaderId() {
			return readerId;
		}

		public boolean isReturned() {
			return returned;
		}
	}

	public static void main(String[] args) {
		List<Book> books = List.of(
			new Book("978-1", "Effective Java"),
			new Book("978-2", "Clean Code"),
			new Book("978-3", "Head First Design Patterns")
		);

		List<Reader> readers = List.of(
			new Reader(1, "alice@mail.com"),
			new Reader(2, "bob@mail.com"),
			new Reader(3, "charlie@mail.com")
		);

		List<Loan> loans = List.of(
			new Loan("978-1", 1, false),    // Alice borrowed Effective Java
			new Loan("978-2", 1, false),    // Alice borrowed Clean Code
			new Loan("978-3", 2, true),     // Bob borrowed and RETURNED
			new Loan("978-999", 1, false),  // Invalid ISBN (book not in system)
			new Loan("978-2", 999, false)   // Invalid reader ID
		);

		Map<String, List<String>> result = currentLoans(books, readers, loans);
		System.out.println(result);

        /* Expected output:
        {
          "alice@mail.com": ["Effective Java", "Clean Code"],
          "bob@mail.com": [],
          "charlie@mail.com": []
        }
        */

	}
}
