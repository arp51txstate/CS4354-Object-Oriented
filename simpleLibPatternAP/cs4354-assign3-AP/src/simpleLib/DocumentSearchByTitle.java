

package simpleLib;

import java.util.HashMap;
import java.util.Map;
public class DocumentSearchByTitle extends DocumentSearch {
	protected static LibSystem libs;
	
	public DocumentSearchByTitle(LibSystem lib) {
		DocumentSearchByTitle.libs = lib;
		query = "";
		queryName = "title";
	}
	@Override
	protected Map<Integer, Object> performSearch(String query) {
		query = query.toLowerCase();
		int index = 1;
		Map<Integer, Object> searchResults = new HashMap<Integer, Object>();
		for (Book book : libs.getBooks()) {
			if (book.title.toLowerCase().contains(query)) {
				searchResults.put(index, book);
				index++;
			}
		}
		for (Journal journal: libs.getJournals()) {
			if (journal.title.contains(query)) {
				searchResults.put(index, journal);
				index++;
			}
		}
		
		return searchResults;
	}

}
