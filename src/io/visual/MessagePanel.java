package io.visual;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class MessagePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private int padding = 8;

	public MessagePanel() {
    	initialize();
    }

    private void initialize() {               
        setBackground(Color.DARK_GRAY);
        setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public void addNewMessage( String text, String type ) {
		Message message;
		
		if( text.charAt(0) == '*')
			type = "Alert";
		
		switch( type ) {
		case "Error":
			message = new ErrorMessage(text);
			break;
		case "User":
			message = new UserMessage(text);
			break;
		case "Alert":
			message = new AlertMessage(text);
			break;
		default:
			message = new Message(text);
		}
		
		add(Box.createVerticalStrut(padding));
		add(message);
	}
}
