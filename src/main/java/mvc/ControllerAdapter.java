package mvc;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerAdapter implements Adapter {
	@Override
	public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) {
		Controller c = (Controller) handler;
		
		Map<String, String> paramMap = getParameters(req);
		Map<String, Object> model = new HashMap<>();
		
		String viewName = c.process(paramMap, model);
		
		ModelView mv = new ModelView();
		mv.setViewName(viewName);
		mv.setModel(model);
		
		return mv;
	}
	
	private Map<String, String> getParameters(HttpServletRequest req)
	{
		Map<String, String> paramMap = new HashMap<>();
		Enumeration<String> e = req.getParameterNames();
		while(e.hasMoreElements())
		{
			String name = e.nextElement();
			paramMap.put(name, req.getParameter(name));
		}
		
		return paramMap;
	}
	
	@Override
	public boolean supports(Object handler) {
		// TODO Auto-generated method stub
		return (handler instanceof Controller);
	}
}
