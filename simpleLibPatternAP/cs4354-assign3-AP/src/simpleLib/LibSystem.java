package simpleLib;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LibSystem implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3079783437045162664L;

	public enum MenuState {
		MAIN_MENU(0), USER_MENU(1), LIBRARIAN_MENU(2), ADMIN_MENU(3);
		private final int value;

		private MenuState(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	private static LibSystem instance = null;
	// transient
	private MenuState currentMenu;
	private ArrayList<LibraryUser> users;
	private ArrayList<Book> books;
	private ArrayList<Journal> journals;
	private ArrayList<Loan> loans;
	private LibraryUser currentUser;

	protected LibSystem() {
		users = new ArrayList<LibraryUser>();
		books = new ArrayList<Book>();
		journals = new ArrayList<Journal>();
		loans = new ArrayList<Loan>();

		currentMenu = MenuState.MAIN_MENU;
	}

	public static LibSystem getInstance() {
		if (instance == null) {
			instance = new LibSystem();
		}
		return instance;
	}

	public void addUser(LibraryUser newUser) {
		users.add(newUser);
		System.out.println("New user (" + newUser + ") added!");

	}

	public void addDocument(LibraryDocument newDocument) {
		if (newDocument instanceof Book) {
			books.add((Book) newDocument);
		} else if (newDocument instanceof Journal) {
			journals.add((Journal) newDocument);
		}

	}

	public Map<Integer, Object> performSearchByAuthor(String author) {
		author = author.toLowerCase();
		int index = 1;
		Map<Integer, Object> searchResults = new HashMap<Integer, Object>();
		for (Book book : books) {
			if (book.author.toLowerCase().contains(author)) {
				searchResults.put(index, book);
				index++;
			}
		}
		for (Journal journal : journals) {
			if (journal.title.contains(author)) {
				searchResults.put(index, journal);
				index++;
			}
		}

		return searchResults;

	}

	/**
	 * Alternative method for loading from text if save / serialized data is not
	 * available.
	 */
	public void LoadDataFromText() {
		String file = "books.txt";
		try {
			InputStream ips = new FileInputStream(file);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			line = br.readLine(); // ignore header
			while ((line = br.readLine()) != null) {
				String dataValue[] = Arrays.copyOf(line.split("\t"), 6);

				String title = dataValue[0];
				String publisher = dataValue[1];
				String date = dataValue[2];
				String isbn = dataValue[3];
				String copies = dataValue[4];
				String author = dataValue[5];
				if (title.length() == 0)
					continue;
				books.add(new Book(title, publisher, date, isbn, copies, author));
			}
			br.close();

		} catch (Exception e) {
			System.out.println(e.toString());

		}
		file = "students.txt";
		try {
			InputStream ips = new FileInputStream(file);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			line = br.readLine(); // ignore header
			while ((line = br.readLine()) != null) {
				String dataValue[] = Arrays.copyOf(line.split("\t"), 4);

				String username = dataValue[0];
				String password = dataValue[1];
				String id = dataValue[2];
				// String numberOfCopies = dataValue[3];
				if (username.length() == 0)
					continue;
				users.add(new LibraryUser(username, password, id));
			}
			br.close();

		} catch (Exception e) {
			System.out.println(e.toString());

		}

		file = "faculty.txt";
		try {
			InputStream ips = new FileInputStream(file);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			line = br.readLine(); // ignore header
			while ((line = br.readLine()) != null) {
				String dataValue[] = Arrays.copyOf(line.split("\t"), 4);

				String username = dataValue[0];
				String password = dataValue[1];
				String id = dataValue[2];
				// String numberOfCopies = dataValue[3];
				if (username.length() == 0)
					continue;
				users.add(new Librarian(username, password, id));
			}
			br.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		file = "admin.txt";
		try {
			InputStream ips = new FileInputStream(file);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			line = br.readLine(); // ignore header
			while ((line = br.readLine()) != null) {
				String dataValue[] = Arrays.copyOf(line.split("\t"), 4);

				String username = dataValue[0];
				String password = dataValue[1];
				String id = dataValue[2];
				// String numberOfCopies = dataValue[3];
				if (username.length() == 0)
					continue;
				users.add(new Administrator(username, password, id));
			}
			br.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	public void checkoutItem(LibraryUser currentUser,
			LibraryDocument libraryDocument) {
		loans.add(new Loan(currentUser, libraryDocument));
		System.out.println(String.format("User %s has checked out item %s!",
				currentUser, libraryDocument));

	}

	public LibraryUser checkLogin(String username, String password) {

		for (LibraryUser currentUser : users) {
			// System.out.println(String.format("checking %s : %s against %s : %s",
			// currentUser.username.toLowerCase(), username.toLowerCase(),
			// currentUser.password, password));
			if (currentUser.username.equalsIgnoreCase(username)
					&& currentUser.password.equals(password)) {
				return currentUser;
			}
		}

		return null;
	}

	public HashMap<Integer, Object> getLoanedItems(LibraryUser currentUser) {
		HashMap<Integer, Object> results = new HashMap<Integer, Object>();
		int count = 1;
		for (Loan loan : loans) {
			if (loan.user == currentUser) {
				results.put(Integer.valueOf(count), loan);
				count++;
			}
		}
		return results;
	}

	public void returnItem(LibraryUser currentUser,
			LibraryDocument currentDocument) {
		for (Loan loan : loans) {
			if (loan.user == currentUser && loan.document == currentDocument) {
				loans.remove(loan);
				System.out.println(String.format(
						"Loan %s removed for user %s.", loan, currentUser));
				break;
			}
		}

	}

	public HashMap<Integer, Object> getAllLoanedItems() {
		HashMap<Integer, Object> results = new HashMap<Integer, Object>();
		int count = 1;
		for (Loan loan : loans) {
			results.put(Integer.valueOf(count), loan);
			count++;
		}
		return results;
	}

	public HashMap<Integer, Object> getCurrentUsers() {
		HashMap<Integer, Object> results = new HashMap<Integer, Object>();
		int index = 1;
		for (LibraryUser user : users) {
			results.put(Integer.valueOf(index), user);
			index++;
		}
		return results;
	}

	public void setMenu(MenuState newMenu) {
		this.currentMenu = newMenu;
	}

	public ConsoleUserInterface getMenu() {
		ConsoleUserInterface currentInterface = null;
		switch (currentMenu) {
		case MAIN_MENU:
			currentInterface = new GeneralView(this);
			break;
		case USER_MENU:
			currentInterface = new UserView(this);
			break;
		case LIBRARIAN_MENU:
			currentInterface = new LibrarianView(this);
			break;
		case ADMIN_MENU:
			currentInterface = new AdminView(this);
			break;
		default:
			currentInterface = new GeneralView(this);
		}
		return currentInterface;
	}

	public LibraryUser getCurrentUser() {
		// TODO Auto-generated method stub
		return currentUser;
	}

	public void removeUser(LibraryUser user) {
		users.remove(user);
		System.out.println(user + " removed!");

	}

	public void logoutCurrentUser() {
		LibraryUser user = currentUser;
		currentUser = null;
		System.out.println(user + " logged out!");

	}

	public void setCurrentUser(LibraryUser currentUser) {
		this.currentUser = currentUser;

	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public ArrayList<Journal> getJournals() {
		return journals;
	}
}
