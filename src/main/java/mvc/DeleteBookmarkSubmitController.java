package mvc;

import java.util.Map;

import repository.Bookmark_Repository;

public class DeleteBookmarkSubmitController implements Controller{
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		
		String id = paramMap.getOrDefault("id", null);
		Bookmark_Repository.deleteBookmarkById(id);
		
		return "delete-bookmark-submit";
	}
}
