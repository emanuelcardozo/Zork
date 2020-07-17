package io.visual;

import javax.swing.JLabel;

public class ErrorMessage extends JLabel {

	private static final long serialVersionUID = 1L;
	
	private final static String OPEN_HTML = "<html><body style='width: 430px; color: white;' >";
	private final static String CLOSE_HTML = "</body></html>";
	
	public ErrorMessage(String text) {
		super( OPEN_HTML + "<div style='margin: 0 75px;  background-color: red; width: 430px;  padding: 10px; font-size: 12px; font-family: Courier'>"+ text.replaceAll("\n", "<br/>") + "</div>" + CLOSE_HTML );
	}
	
}
