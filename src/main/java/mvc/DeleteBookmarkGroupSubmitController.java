package mvc;

import java.util.Map;

import repository.Bookmark_Repository;

public class DeleteBookmarkGroupSubmitController implements Controller {
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		String id = paramMap.getOrDefault("id", null);
		Bookmark_Repository.deleteRowById(id);
		return "delete-bookmarkGroup-submit";
	}
}
