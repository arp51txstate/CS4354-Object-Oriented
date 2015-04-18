package simpleLib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;


public abstract class DocumentSearch {
	protected static LibSystem libs;
	protected String query;
	protected String queryName;
	protected void requestQuery() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(String.format("Please enter a %s to search: ", queryName));
		
		try {
			query = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected Map<Integer, Object> performSearch() {
		return performSearch(query);
	}
	protected abstract Map<Integer, Object> performSearch(String query);
}
