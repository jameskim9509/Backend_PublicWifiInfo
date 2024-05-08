package mvc;

import java.util.Map;
import thread.*;

public class LoadWifiController implements Controller {	
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		// wait 페이지로 부터 1초 간격으로 반복 요청 (refresh 쿼리스트링)
		boolean isRefresh = Boolean.valueOf(paramMap.getOrDefault("refresh", "false"));
		int cur_total = LoadWifiThreadManager.getCurTotal();
		int total = LoadWifiThreadManager.getTotal();
		
		if(!isRefresh && LoadWifiThreadManager.addThread()) // 처음 요청시 스레드 실행
		{
			LoadWifiThreadManager.run();
			cur_total = 0;
			total = Integer.MAX_VALUE;
		}
		
		model.put("cur_total", cur_total);
		if(total == Integer.MAX_VALUE) model.put("total_cnt", "?"); // 초기값이면
		else model.put("total_cnt", total);
		
		if(cur_total < total) return "wait";
		else return "load-wifi";
	}
}
