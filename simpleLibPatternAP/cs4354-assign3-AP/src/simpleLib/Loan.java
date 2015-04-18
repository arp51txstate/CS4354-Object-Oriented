package simpleLib;

import java.util.Date;

public class Loan  implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4349901791595497297L;
	
	LibraryUser user;
	LibraryDocument document;
	Date startDate;
	
	public Loan(LibraryUser user, LibraryDocument document) {
		this.user = user;
		this.document = document;
		startDate = new Date();
	}
	
	@SuppressWarnings("deprecation")
	public String toString() {
		return String.format("%s started on %s/%s checked out by: %s", document,startDate.getMonth(),startDate.getDate(),user);
	}
}
