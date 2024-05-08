package mvc;

import java.util.ArrayList;
import java.util.Map;

import repository.Bookmark_Repository;
import repository.Public_Wifi_Info_Repository;

public class DetailController implements Controller {
	
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		String mgrNo = paramMap.get("mgrNo");
		
		Map<String, String> wifiInfo = Public_Wifi_Info_Repository.searchByMgrNo(mgrNo);
		ArrayList<Map<String, String>> bookmarkGroupList = Bookmark_Repository.searchBookmarkGroupList();
		
		model.put("wifiInfo", wifiInfo);
		model.put("bookmarkGroupList", bookmarkGroupList);
		
		return "detail";
	}	
}
