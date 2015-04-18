package simpleLib;

import java.util.Date;

public class Book extends LibraryDocument {
	/**
	 * 
	 */
	private static final long serialVersionUID = -873671636414925733L;
	String isbn;
	Integer copies;
	String author;


	@SuppressWarnings("deprecation")
	public Book(String title, String publisher, String date, String isbn, String copies, String author) {
		this.title = title;
		this.publisher = publisher;
		this.publishDate = new Date(date);
		this.copies = new Integer(copies);
		this.author = author;
		
		
	}
	public String toString() {
		return title + " by " + author;// + " (" + copies + " total)";
	}

}
