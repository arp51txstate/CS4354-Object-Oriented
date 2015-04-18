package simpleLib;

import simpleLib.LibSystem.MenuState;

public class Administrator extends LibraryUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6263525161968640052L;

	public Administrator(String username, String password) {
		
		super(username, password);
		menuAccessType = MenuState.ADMIN_MENU;
		usertype = "Admin";
	}

	public Administrator(String username, String password, String id) {		
		this(username, password);
	}





}
