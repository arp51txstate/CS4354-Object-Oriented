

package simpleLib;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class DocumentSearchByPubDate extends DocumentSearch {
	protected static LibSystem libs;
	
	public DocumentSearchByPubDate(LibSystem lib) {
		DocumentSearchByPubDate.libs = lib;
		query = "";
		queryName = "publish date";
	}
	@SuppressWarnings("deprecation")
	@Override
	protected Map<Integer, Object> performSearch(String query) {
		Date queryDate = new Date(query);
		int index = 1;
		Map<Integer, Object> searchResults = new HashMap<Integer, Object>();
		for (Book book : libs.getBooks()) {
			if (book.publishDate.equals(queryDate)) {
				searchResults.put(index, book);
				index++;
			}
		}
		for (Journal journal: libs.getJournals()) {
			if (journal.publishDate.equals(queryDate)) {
				searchResults.put(index, journal);
				index++;
			}
		}
		
		return searchResults;
	}

}
