package mvc;

import java.util.Map;

public class AddBookmarkGroupController implements Controller {
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		
		return "add-bookmarkGroup";
	}
}
