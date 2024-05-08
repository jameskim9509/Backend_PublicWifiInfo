package mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import repository.Bookmark_Repository;

public class AddBookmarkGroupSubmitController implements Controller {
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {

		String num = paramMap.getOrDefault("num", null);
		String name = paramMap.getOrDefault("name", null);
		
		ArrayList<Map<String, String>> groupList = new ArrayList<>();
		groupList.add(new HashMap<String, String>(){{ // 이중 중괄호 초기화
			put("NUM", num);
			put("NAME", name); 
		}});
		Bookmark_Repository.saveRepository(groupList);
			
		return "add-bookmarkGroup-submit";
	}
}
