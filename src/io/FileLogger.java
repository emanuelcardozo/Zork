package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLogger {
	
	private PrintWriter pw;
	private String playerName;
	
	public FileLogger(String playerName) throws FileNotFoundException {
		this.playerName = playerName;
		initialize();
	}
	
	public void initialize() throws FileNotFoundException {
		String partialPath = "PartidasGuardadas/" + playerName + "-";
		int i = 0;
		Path path;
		
		do {
			path = Paths.get(partialPath + (++i) + ".log");
		} while (Files.exists(path));
		
		pw = new PrintWriter(new File(partialPath + i + ".log"));
	}
	
	public void jugadorDice(String text) {
		pw.println( text );
	}
	
	public void juegoDice(String text) {
		pw.println( text );
	}
	
	public void terminarLog() {
		pw.close();
	}
}
