package mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Adapter {
	boolean supports(Object handler);
	ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler);
}
