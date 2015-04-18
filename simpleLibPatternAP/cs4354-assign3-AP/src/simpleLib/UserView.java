package simpleLib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import simpleLib.LibSystem.MenuState;

public class UserView extends ConsoleUserInterface {
	public UserView(LibSystem lib) {
		UserView.libs = lib;

		title = "User Menu - " + lib.getCurrentUser();
		menuOptions = new HashMap<Integer, String>();
		menuOptions.put(new Integer(1), "Search Documents");
		menuOptions.put(new Integer(2), "Checkout Item");
		menuOptions.put(new Integer(3), "Return Item");
		menuOptions.put(new Integer(4), "Current Loans");
		menuOptions.put(new Integer(5), "Go Back");

	}

	public boolean parseInput(int choice) {

		switch (choice) {
		case 1:
			doSearch();
			break;
		case 2:
			checkoutItem();
			break;
		case 3:
			returnItem();
			break;
		case 4:
			displayLoanedItems();
			break;
		case 5:
			switchToGeneralView();
			break;
		default:
			System.err.println("Please select a number between 1 and 4.");
		}
		return false;

	}

	/**
	 * Search all Journals and Books for a specific title. This function is also used to provide selection options for check-outs.
	 */
	private void doSearch() {
		
		
		
		HashMap<Integer, String> searchDocumentOptions = new HashMap<Integer, String>();
		searchDocumentOptions.put(new Integer(1), "Search By Title");
		searchDocumentOptions.put(new Integer(2), "Search by Author");
		searchDocumentOptions.put(new Integer(3), "Cancel");
		
		Map<Integer, Object> results = null;
		String query = "";
		
		
		DocumentSearch searchType = null;
		do {
			printMenu("Search Type?", searchDocumentOptions);
			int itemSelection = GetSelection("Please enter selection: ", searchDocumentOptions.keySet());
		    switch (itemSelection) {
		    case 1:
		    	searchType = new DocumentSearchByTitle(libs);
		    	break;
		    case 2:
		    	searchType = new DocumentSearchByAuthor(libs);
		    	break;
		    case 3:
		    	return;
		    default:
		    	System.out.println("Invalid selection made...");
		    }

		} while (searchType == null);
		searchType.requestQuery();
		results = searchType.performSearch();
		DisplayDocuments("Search results for \"" + query + "\":", results);
		System.out.println("Please press enter to continue...");
		sc.nextLine();

	}
	
	/**
	 * Adds a new loaned item for the current user.
	 */
	private void checkoutItem() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter a title to checkout: ");
		String title = "";
		try {
			title = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<Integer, Object> results = new DocumentSearchByTitle(libs).performSearch(title);
		DisplayDocuments("Matching items:", results);
		int itemSelection = GetSelection("Please enter selection: ", results.keySet());
		libs.checkoutItem(libs.getCurrentUser(), (LibraryDocument) results.get(itemSelection));
	}
	
	/**
	 * Removes a loaned item from the database's list of loans.
	 */
	private void returnItem() {
		HashMap<Integer, Object> results = libs.getLoanedItems(libs.getCurrentUser());
		if (results.size() < 1) {
			System.out.println("No items to return!");
			return;
		}
		DisplayDocuments("Loaned items for " + libs.getCurrentUser() + ":", results);
		int itemSelection = GetSelection("Please enter selection to return: ", results.keySet());
		libs.returnItem(libs.getCurrentUser(), (LibraryDocument) results.get(itemSelection));
	}

	private void switchToGeneralView() {
		libs.setMenu(MenuState.MAIN_MENU);
		
	}


}
