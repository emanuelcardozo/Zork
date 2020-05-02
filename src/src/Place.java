package src;

import java.util.ArrayList;

public class Place extends Noun {
	private ArrayList<String> items = new ArrayList<String>();
	public Place(String name, String gender, String number) {
		super(name, gender, number);
	}
}
