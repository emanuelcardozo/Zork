package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Dibujador {

	public void dibujar(Graphics g, JPanel panel, String path, Posicion posicion) {
		File file = new File(path);
    	Image image = null;
    	
    	if( !file.exists() ) return;
    	
    	try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("No se pudo leer la imagen: " + file.getPath());
		}    	
		
		if ( image != null ) {
            g.drawImage(image, posicion.getX(), posicion.getY(), posicion.getAncho(), posicion.getAlto(), panel);
		}
	}
}
