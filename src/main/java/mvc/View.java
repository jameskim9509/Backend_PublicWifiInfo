package mvc;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Setter;

@Setter
public class View {
	String viewPath;
	public void render(Map<String, Object> model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		Set<String> paramSet = model.keySet();
		for(String name : paramSet)
		{
			req.setAttribute(name, model.get(name));
		}
		
		try {
			RequestDispatcher d = req.getRequestDispatcher(viewPath);
			d.forward(req, resp);
		} catch (Exception e) {
//			System.out.println(e.toString());
		}
		
	}
}
