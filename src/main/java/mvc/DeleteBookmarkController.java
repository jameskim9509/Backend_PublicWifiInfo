package mvc;

import java.util.Map;

import repository.Bookmark_Repository;

public class DeleteBookmarkController implements Controller{
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		String id = paramMap.getOrDefault("id", null);
		
		Map<String, String> bookmark = Bookmark_Repository.searchBookmarkById(id);
		model.put("bookmark", bookmark);
		
		return "delete-bookmark";
	}
}
