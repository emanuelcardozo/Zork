package io;

import entities.Location;

public interface InOutputable {
	
	public String getValue(String message);
	public void showMessage(String message);
	public void showError(String errorMessage);
	public void addFileLogger(FileLogger filelogger);
	public void showEnd( String endMessage );
	public void changeLocation( Location location, String escenario );
}
