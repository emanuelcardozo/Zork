package io.visual;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;

public class Message extends JTextPane {

	private static final long serialVersionUID = 1L;
	private final int padding = 8;

	public Message(String text) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setMaximumSize(new Dimension(550, 200));
        setMinimumSize(new Dimension(550, 10));
        setText( text );
        setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        setEditable(false);
        setFont(new Font("Courier", Font.PLAIN, 16));
	} 
}
