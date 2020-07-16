package entities;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sonido {
	
	private String path;
	
	public Sonido(String path) {
		this.path = path;
	}
	
	public void reproducir() {
		synchronized (this) {
			try {
		        Clip sonido = AudioSystem.getClip();
		        sonido.open(AudioSystem.getAudioInputStream(new File(path)));		        
		        sonido.start();
		        
		        Thread.sleep(1000);
		        
		        while (sonido.isRunning())
					Thread.sleep(1000);
				
		        sonido.close();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("No se encontro el archivo de Sonido");
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				System.out.println("El archivo tiene un formato no soportado");
				e.printStackTrace();
			} catch (InterruptedException e) {
				System.out.println("Error al pausar el hilo");
				e.printStackTrace();
			}
		}
	}
}
