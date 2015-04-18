package simpleLib;

import simpleLib.LibSystem.MenuState;

public class Librarian extends LibraryUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6263525161968640052L;

	public Librarian(String username, String password) {
		super(username, password);
		menuAccessType = MenuState.LIBRARIAN_MENU;
		usertype = "Librarian";
	}

	public Librarian(String username, String password, String id) {
		this(username, password);
	}





}
