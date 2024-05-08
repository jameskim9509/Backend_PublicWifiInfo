package mvc;

import java.util.Map;

import repository.History_Repository;

public class DeleteHistorySubmitController implements Controller{
	 @Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		String id = paramMap.getOrDefault("id", null);
		History_Repository.deleteRowById(id);
		
		return "delete-history-submit";
	}
}
