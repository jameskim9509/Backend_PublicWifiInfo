package mvc;

import java.util.Map;

import repository.Bookmark_Repository;

public class AddBookmarkSubmitController implements Controller{
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		
		String mgrNo = paramMap.getOrDefault("mgrNo", null);
		String id = paramMap.getOrDefault("id", null);
		
		Bookmark_Repository.updateMgrNo(mgrNo, id);
		
		return "add-bookmark-submit";
	}
}
