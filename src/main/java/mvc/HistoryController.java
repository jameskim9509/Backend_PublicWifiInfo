package mvc;

import java.util.ArrayList;
import java.util.Map;

import repository.History_Repository;

public class HistoryController implements Controller{
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		
		ArrayList<Map<String, String>> historyList = History_Repository.searchHistory();
		model.put("historyList", historyList);
		
		return "history";
	}
}
