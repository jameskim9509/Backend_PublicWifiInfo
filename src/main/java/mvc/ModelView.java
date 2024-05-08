package mvc;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ModelView {
	public String viewName;
	public Map<String, Object> model = new HashMap<>();
}
