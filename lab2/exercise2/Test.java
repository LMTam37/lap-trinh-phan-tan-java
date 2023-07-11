package lab2.exercise2;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		String path = "json/usa_state.json";
		ServiceFindState sft = new ServiceFindState(path);
		System.out.println("State co ab la AZ " + sft.findByAb("AZ"));
		System.out.println("cac state truoc nam 1900");
		ArrayList<State> states = sft.findByYear(1900);
		for (State curState : states) {
			System.out.println(curState);
		}
	}
}