package client;

import java.io.Serializable;
import java.util.Arrays;

public class Command implements Serializable {
	private int numbers;
	private String[] params;

	public Command(int numbers, String[] params) {
		this.numbers = numbers;
		this.params = params;
	}

	public int getNumbers() {
		return numbers;
	}

	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "Command [numbers=" + numbers + ", params=" + Arrays.toString(params) + "]";
	}

}
