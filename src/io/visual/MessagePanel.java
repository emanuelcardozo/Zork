package io.visual;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

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

	private JTextPane createTextPane( String text ) {
		
        JTextPane pane = new JTextPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        pane.setMaximumSize(new Dimension(550, 200));
        pane.setMinimumSize(new Dimension(550, 10));
        pane.setText( text );
        pane.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        pane.setEditable(false);
        add(Box.createVerticalStrut(padding));
        
        return pane;
    }
	
	public void addNewMessage( String text, String type ) {
		JTextPane pane = createTextPane(text);
		if(text.charAt(0) == '*')
			type = "Alert";
		
		switch( type ) {
		case "Error":
			pane.setBackground(Color.RED);
			pane.setForeground(Color.WHITE);
			break;
		case "User":
			pane.setForeground(Color.BLUE);
			pane.setFont(new Font("MONOSPACED", Font.BOLD, 14));
			break;
		case "Alert":
			pane.setForeground(Color.BLACK);
			pane.setBackground(Color.YELLOW);
			pane.setFont(new Font("Courier", Font.BOLD, 14));
			break;
		default:
			pane.setFont(new Font("Courier", Font.PLAIN, 16));
		}
		
		add(pane);
	}
}
