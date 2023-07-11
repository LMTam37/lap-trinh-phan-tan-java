package lab2.exercise2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class ServiceFindState {
	private String path;

	public ServiceFindState(String path) {
		this.path = path;
	}

	public State[] findAll() {
		Gson gson = new Gson();
		JsonReader reader = null;
		try {
			reader = new JsonReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		State[] states = gson.fromJson(reader, State[].class);
		return states;
	}

	public State findByAb(String abb) {
		State[] states = this.findAll();
		for (State curState : states) {
			if (curState.getAbbreviation().equals(abb)) {
				return curState;
			}
		}
		return null;
	}

	public ArrayList<State> findByYear(int year) {
		State[] states = this.findAll();
		ArrayList<State> result = new ArrayList<State>();
		for (State curState : states) {
			if (curState.getStatehood() < year) {
				result.add(curState);
			}
		}
		return result;
	}
}
