package simpleLib;

import java.util.ArrayList;

import simpleLib.LibSystem.MenuState;

public class LibraryUser  implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9150049329963274788L;
	ArrayList<LibraryDocument> checkedOutItems;
	String username;
	protected String password;
	protected String usertype;
	MenuState menuAccessType;
	public LibraryUser(String username, String password) {
		checkedOutItems = new ArrayList<LibraryDocument>();
		this.username = username;
		this.password = password;
		menuAccessType = MenuState.USER_MENU;
		usertype = "User";
	}
	
	public LibraryUser(String username, String password, String id) {
		this(username, password);
		
	}



	public String toString() {
		return username + " (" + usertype + ")";
	}

	public MenuState getMenuAccessType() {
		return menuAccessType;
	}
	

}
