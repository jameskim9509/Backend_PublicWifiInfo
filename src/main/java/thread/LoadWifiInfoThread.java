package thread;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import repository.Public_Wifi_Info_Repository;

public class LoadWifiInfoThread extends Thread{
	int cur_total;
	int total_cnt;
	
	public LoadWifiInfoThread() {
		cur_total = 0;
		total_cnt = Integer.MAX_VALUE;
	}
	
	@Override
	public void run() {
		LoadWifiThreadManager.setCurTotal(cur_total);
        LoadWifiThreadManager.setTotal(total_cnt);
        
		Public_Wifi_Info_Repository.createRepository();
		Public_Wifi_Info_Repository.deleteRepositoryRows();
		
		int cur_ind = 0;
		int end_ind = 1000;
		while(true)
		{
			StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
			
			/*URL*/
	        try {
				urlBuilder.append("/" + URLEncoder.encode("496977444f626d6f313231504f706572", "UTF-8"));
				urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /*요청파일타입 (xml,xmlf,xls,json) */
		        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));
		        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(cur_ind+1), "UTF-8"));
		        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(end_ind), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	        String jsonString = getFromHttpUrl(urlBuilder.toString());
	       
	        // json to ArrayList<Map<Stirng, String>>
	        ArrayList<Map<String, String>> mapArr = new ArrayList<>();
	        JsonObject jsonObj = JsonParser.parseString(jsonString).getAsJsonObject();
            JsonObject publicInfoObj = jsonObj.getAsJsonObject("TbPublicWifiInfo");
            total_cnt = publicInfoObj.get("list_total_count").getAsInt();
            JsonArray rowArr = publicInfoObj.getAsJsonArray("row");
            for(int i = 0; i < rowArr.size(); i++)
            {
            	Map<String, String> m = new HashMap<>();
            	JsonObject rowObj = rowArr.get(i).getAsJsonObject();
            	Set<String> keys = rowObj.keySet();
            	for(String key : keys)
            	{
            		String value = rowObj.get(key).toString().replaceAll("^\"|\"$", ""); // json문자열의 앞뒤 큰따옴표(") 제거
            		m.put(key, value);
            	}
            	mapArr.add(m);
            }
            
            //ArrayList<Map<String,String>> to DB
            cur_total += Public_Wifi_Info_Repository.saveRepository(mapArr);
            
            LoadWifiThreadManager.setCurTotal(cur_total);
            LoadWifiThreadManager.setTotal(total_cnt);
            
            cur_ind = end_ind;
            end_ind = end_ind + 1000 > total_cnt ? total_cnt : end_ind + 1000;
            if(cur_ind >= total_cnt) break;
		}
		
		LoadWifiThreadManager.remove();
	}
	
	// 동기적으로 Http Api 요청
	private String getFromHttpUrl(String Url)
	{
		final OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(Url).build();
		
		String str = null;
		try(Response response = client.newCall(request).execute())
		{
			if (!response.isSuccessful()) return str;
			
			str = response.body().string();
		}
		catch(IOException e){}
		
		return str;
	}
}