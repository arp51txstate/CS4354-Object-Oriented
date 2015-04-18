

package simpleLib;

import java.util.HashMap;
import java.util.Map;
public class DocumentSearchByAuthor extends DocumentSearch {
	protected static LibSystem libs;
	
	public DocumentSearchByAuthor(LibSystem lib) {
		DocumentSearchByAuthor.libs = lib;
		query = "";
		queryName = "author";
	}
	@Override
	protected Map<Integer, Object> performSearch(String query) {
		query = query.toLowerCase();
		int index = 1;
		Map<Integer, Object> searchResults = new HashMap<Integer, Object>();
		for (Book book : libs.getBooks()) {
			if (book.author.toLowerCase().contains(query)) {
				searchResults.put(index, book);
				index++;
			}
		}
		for (Journal journal: libs.getJournals()) {
			if (journal.author.contains(query)) {
				searchResults.put(index, journal);
				index++;
			}
		}
		
		return searchResults;
	}

}
