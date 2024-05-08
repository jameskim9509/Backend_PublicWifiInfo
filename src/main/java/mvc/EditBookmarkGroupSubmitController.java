package mvc;

import java.util.Map;

import repository.Bookmark_Repository;

public class EditBookmarkGroupSubmitController implements Controller {
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		
		String name = paramMap.getOrDefault("name", null);
		String num = paramMap.getOrDefault("num", null);
		String id = paramMap.getOrDefault("id", null);
		
		Bookmark_Repository.editBookmarkGroupById(name, num, id);
		
		return "edit-bookmarkGroup-submit";
	}
}
