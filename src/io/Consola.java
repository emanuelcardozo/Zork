package io;

import java.util.Scanner;

import entities.Aventura;

public class Consola implements InOutputable {

	private Scanner teclado;
	
	public Consola() {
		 teclado = new Scanner(System.in);
	}
	
	@Override
	public String getValue(String message) {
		showMessage(message);
		return teclado.nextLine();
	}

	@Override
	public void showMessage(String message) {
		if(message != null)
			System.out.println(message);		
	}

	@Override
	public void showError(String errorMessage) {
		System.err.println(errorMessage);		
	}
}
