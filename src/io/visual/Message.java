package io.visual;

import javax.swing.JLabel;

public class Message extends JLabel {
	private static final long serialVersionUID = 1L;
	
	private final static String OPEN_HTML = "<html><body style='width: 430px;' >";
	private final static String CLOSE_HTML = "</body></html>";
	
	public Message(String text) {
		super( OPEN_HTML + "<div style='background-color: white; width: 430px;  padding: 10px; font-size: 12px; font-family: Courier'>"+ text.replaceAll("\n", "<br/>") + "</div>" + CLOSE_HTML );
	}
}