package mvc;

import java.util.Map;

import repository.Bookmark_Repository;

public class EditBookmarkGroupController implements Controller{
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		
		String id = paramMap.getOrDefault("id", null);
		
		Map<String, String> row = Bookmark_Repository.searchRowById(id);
		
		model.put("id", row.get("ID"));
		model.put("name", row.get("NAME"));
		model.put("num", row.get("NUM"));
		
		return "edit-bookmarkGroup";
	}
}
