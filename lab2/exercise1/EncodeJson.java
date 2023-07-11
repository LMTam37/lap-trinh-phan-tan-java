package lab2.exercise1;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class EncodeJson {
	public static void main(String[] args) throws Exception {
		EncodeJson ej = new EncodeJson();
		Employee e = new Employee(10001, "Thân Thị Đẹt", 10000d);
		String js = ej.genjson(e);
		ej.export("json/emp.json", js);
	}

	public String genjson(Employee e) {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("id", e.getId());
		builder.add("name", e.getName());
		builder.add("salary", e.getSalary());
		JsonObject jo = builder.build();
		return jo.toString();
	}

	public void export(String filePath, String json) throws Exception {
		PrintWriter out = new PrintWriter(new FileOutputStream(filePath), true);
		out.println(json);
		out.close();
	}
}
