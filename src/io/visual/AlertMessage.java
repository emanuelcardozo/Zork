package io.visual;

import java.awt.Color;
import java.awt.Font;

public class AlertMessage extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlertMessage(String text) {
		super(text);

		setForeground(Color.BLACK);
		setBackground(Color.YELLOW);
		setFont(new Font("Courier", Font.BOLD, 14));
	}

}
