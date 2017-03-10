package dds.poi.adapter.wrapper;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

public class JsonObjectWrapper extends JsonObject {

	private static final long serialVersionUID = 1L;

	private JsonObject jsonObject;
	
	public JsonObjectWrapper(JsonObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	
	public double getDouble(String propertyName) {
		return this.jsonObject.get(propertyName).asDouble();
	}
	
	public String getString(String propertyName) {
		return this.jsonObject.get(propertyName).asString();
	}
	
	public JsonArray getJsonArray(String propertyName) {
		return this.jsonObject.get(propertyName).asArray();
	}
}
