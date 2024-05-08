package mvc;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import repository.History_Repository;
import repository.Public_Wifi_Info_Repository;

public class HomeController implements Controller {
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model)
	{
		
		String lat = paramMap.getOrDefault("lat", "");
		String lnt = paramMap.getOrDefault("lnt", "");
		
		model.put("lat", lat);
		model.put("lnt", lnt);
		
		if(!lat.isEmpty() && !lnt.isEmpty())
		{
			ArrayList<Map<String, String>> locDataList = new ArrayList<>();
			locDataList.add(new HashMap<String, String>(){{ // 이중 중괄호 초기화
				put("LAT", lat);
				put("LNT", lnt); 
			}});
			History_Repository.createRepository();
			History_Repository.saveRepository(locDataList);
			
			Public_Wifi_Info_Repository.deleteAllDistanceData(); // 전에 계산된 distance 값이 있다면 제거
			ArrayList<Map<String, String>> PublicWifiInfoList = Public_Wifi_Info_Repository.searchByDistance(lat, lnt);
			model.put("PublicWifiInfoList", PublicWifiInfoList);
		}
		
		return "home";
	}
}
