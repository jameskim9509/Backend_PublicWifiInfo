package mvc;

import java.util.ArrayList;
import java.util.Map;

import repository.Bookmark_Repository;

public class ManageBookmarkGroupController implements Controller {
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		
		Bookmark_Repository.createRepository();
		
		ArrayList<Map<String, String>> bookmarkGroupList = Bookmark_Repository.searchBookmarkGroupList();
		model.put("bookmarkGroupList", bookmarkGroupList);
		
		return "manage-bookmarkGroup";
	}
}
