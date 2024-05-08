package mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet(urlPatterns = {"", "/history", "/delete-history-submit", "/load-wifi", "/detail", "/show-bookmarkList",
		"/add-bookmark-submit", "/manage-bookmarkGroup", "/delete-bookmark", "/delete-bookmark-submit",
		"/edit-bookmarkGroup", "/edit-bookmarkGroup-submit", "/add-bookmarkGroup", "/add-bookmarkGroup-submit", 
		"/delete-bookmarkGroup", "/delete-bookmarkGroup-submit"})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Map<String, Object> controllerMap = new HashMap<>();
	ArrayList<Adapter> adapters = new ArrayList<>();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
//        super();
    	initControllerMap();
    	initAdapter();
    }
    
    private void initControllerMap()
    {
    	controllerMap.put("/", new HomeController());
    	controllerMap.put("/history", new HistoryController());
    	controllerMap.put("/delete-history-submit", new DeleteHistorySubmitController());
    	controllerMap.put("/load-wifi", new LoadWifiController());
    	controllerMap.put("/detail", new DetailController());
    	controllerMap.put("/manage-bookmarkGroup", new ManageBookmarkGroupController());
    	controllerMap.put("/add-bookmarkGroup", new AddBookmarkGroupController());
    	controllerMap.put("/add-bookmarkGroup-submit", new AddBookmarkGroupSubmitController());
    	controllerMap.put("/edit-bookmarkGroup", new EditBookmarkGroupController());
    	controllerMap.put("/edit-bookmarkGroup-submit", new EditBookmarkGroupSubmitController());
    	controllerMap.put("/delete-bookmarkGroup", new DeleteBookmarkGroupController());
    	controllerMap.put("/delete-bookmarkGroup-submit", new DeleteBookmarkGroupSubmitController());
    	controllerMap.put("/show-bookmarkList", new ShowBookmarkListController());
    	controllerMap.put("/add-bookmark-submit", new AddBookmarkSubmitController());
    	controllerMap.put("/delete-bookmark", new DeleteBookmarkController());
    	controllerMap.put("/delete-bookmark-submit", new DeleteBookmarkSubmitController());
    }
    
    private void initAdapter()
    {
    	adapters.add(new ControllerAdapter()); 
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		requestURI = requestURI.replaceAll(contextPath, "");
//		requestURI = requestURI.replaceAll("Public_Wifi_Info/", "");
		Object handler = controllerMap.get(requestURI);
		if(handler == null) return;
		
		Adapter handlerAdapter = getHandlerAdapter(handler);
		ModelView mv = handlerAdapter.handle(request, response, handler);
		
		String viewPath = viewResolver(mv.getViewName());
		View myView = new View();
		myView.setViewPath(viewPath);
		myView.render(mv.getModel(), request, response);
	}
	
	private Adapter getHandlerAdapter(Object handler)
	{
		for(Adapter a : adapters)
		{
			if(a.supports(handler)) return a;
		}
		return null;
	}
	
	private String viewResolver(String viewName)
	{
		return "/WEB-INF/"+viewName+".jsp";
	}
}
