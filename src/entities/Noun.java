package entities;

public class Noun {
	private String name;
	private String gender;
	private String number;

	public Noun(String name, String gender, String number) {
		this.name = name;
		this.gender = gender;
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	
	public String getGender() {
		return gender;
	}

	public String getNumber() {
		return number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Noun [name=" + name + ", gender=" + gender + ", number=" + number + "]";
	}
}
