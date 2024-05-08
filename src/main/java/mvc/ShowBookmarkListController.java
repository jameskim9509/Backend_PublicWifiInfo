package mvc;

import java.util.ArrayList;
import java.util.Map;

import repository.Bookmark_Repository;

public class ShowBookmarkListController implements Controller{
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		
		ArrayList<Map<String, String>> bookmarkList = Bookmark_Repository.searchBookmarkList();
		model.put("bookmarkList", bookmarkList);
		
		return "show-bookmarkList";
	}
}
