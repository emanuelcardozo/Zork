package io.visual;

import java.awt.Color;
import java.awt.Font;

public class ErrorMessage extends Message {

	private static final long serialVersionUID = 1L;
	
	public ErrorMessage(String text) {
		super(text);
		
		setBackground(Color.RED);
		setForeground(Color.WHITE);
		setFont(new Font("Sans Serif", Font.BOLD, 14));
	}
	
}
