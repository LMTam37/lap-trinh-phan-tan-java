package lab2.exercise3;

import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class test {
	public static void convertObj2JSON(Employee emp) throws Exception {
		Gson gson = new Gson();
		gson.toJson(emp, new FileWriter("D:\\file.json"));
		String jsonInString = gson.toJson(emp);
		System.out.println(jsonInString);
	}

	public static void convertJSON2Object() throws Exception {
		Gson gson = new Gson();
		Employee emp1 = gson.fromJson(new FileReader("json/file.json"), Employee.class);
		String jsonInString = "{\"id\":1001,\"name\":\"Than thi det\"}";
		Employee emp2 = gson.fromJson(jsonInString, Employee.class);
		JsonElement json = gson.fromJson(new FileReader("json/file.json"), JsonElement.class);
		String result = gson.toJson(json);
		System.out.println(emp1);
		System.out.println(emp2);
		System.out.println(result);
	}

	public static void main(String[] args) throws Exception {
		convertJSON2Object();
		convertObj2JSON(new Employee(1001, "Than Thi Det"));
	}
}
