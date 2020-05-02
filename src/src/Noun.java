package src;

public class Noun {
	protected String name;
	protected String gender;
	protected String number;

	public Noun(String name, String gender, String number) {
		this.name = name;
		this.gender = gender;
		this.number = number;
	}

	@Override
	public String toString() {
		return "Noun [name=" + name + ", gender=" + gender + ", number=" + number + "]";
	}
}
