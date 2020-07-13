package io.visual;

import java.awt.Color;
import java.awt.Font;

public class UserMessage extends Message {

	private static final long serialVersionUID = 1L;

	public UserMessage(String text) {
		super(text);
		
		setForeground(Color.BLUE);
		setFont(new Font("Monospaced", Font.BOLD, 14));
	}
	

}
