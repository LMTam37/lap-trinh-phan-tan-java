package lab2.exercise2;

public class State {
	private String StateName;
	private String Abbreviation;
	private String Capital;
	private int Statehood;
	private int id;

	public State(String stateName, String abbreviation, String capital, int statehood, int id) {
		StateName = stateName;
		Abbreviation = abbreviation;
		Capital = capital;
		Statehood = statehood;
		this.id = id;
	}

	public State() {
	}

	public String getStateName() {
		return StateName;
	}

	public void setStateName(String stateName) {
		StateName = stateName;
	}

	public String getAbbreviation() {
		return Abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		Abbreviation = abbreviation;
	}

	public String getCapital() {
		return Capital;
	}

	public void setCapital(String capital) {
		Capital = capital;
	}

	public int getStatehood() {
		return Statehood;
	}

	public void setStatehood(int statehood) {
		Statehood = statehood;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "State [StateName=" + StateName + ", Abbreviation=" + Abbreviation + ", Capital=" + Capital
				+ ", Statehood=" + Statehood + ", id=" + id + "]";
	}

}
