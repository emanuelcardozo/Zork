package io;

import java.util.Scanner;

import entities.Aventura;

public class Consola implements InOutputable {

	private Scanner teclado;
	private FileLogger fileLogger;
	
	public Consola() {
		 teclado = new Scanner(System.in);
	}
	
	@Override
	public String getValue(String message) {
		showMessage(message);
		String input = teclado.nextLine();
		
		fileLogger.jugadorDice("  >>" + input);
		
		return input;
	}

	@Override
	public void showMessage(String message) {
		if(message != null) {
			fileLogger.juegoDice(message);
			System.out.println(message);
		}					
	}

	@Override
	public void showError(String errorMessage) {
		System.err.println(errorMessage);		
	}

	@Override
	public void addFileLogger(FileLogger fileLogger) {
		this.fileLogger = fileLogger;		
	}

	@Override
	public void showEnd(String endMessage) {
		showMessage(endMessage);
		fileLogger.terminarLog();
	}

	@Override
	public void changeLocation(String locationName, String nombreEscenario) {
		// TODO Auto-generated method stub
		
	}
}
