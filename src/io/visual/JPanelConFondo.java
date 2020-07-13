package io.visual;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
 
public class JPanelConFondo extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image imagen;
 
    public JPanelConFondo() {;}
 
    public JPanelConFondo(String nombreImagen) {
        if (nombreImagen != null) {
            imagen = new ImageIcon(
                           getClass().getResource(nombreImagen)
                           ).getImage();
        }
    }
    
    public void changeImage( String escenario, String location ) {
    	File path = new File("./images/fondos/"+ escenario +"/"+ location +".jpg");
	    try {
			Image image = ImageIO.read(path);
			setImagen(image);
		} catch (IOException e) {
			System.out.println("No se pudo leer la imagen");
			e.printStackTrace();
		}
    }
 
    public JPanelConFondo(Image imagenInicial) {
        if (imagenInicial != null) {
            imagen = imagenInicial;
        }
    }
 
    public void setImagen(String nombreImagen) {
        if (nombreImagen != null) {
            imagen = new ImageIcon(
                   getClass().getResource(nombreImagen)
                   ).getImage();
        } else {
            imagen = null;
        }
 
        repaint();
    }
 
    public void setImagen(Image nuevaImagen) {
        imagen = nuevaImagen;
 
        repaint();
    }
 
    @Override
    public void paint(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(),
                              this);
 
            setOpaque(false);
        } else {
            setOpaque(true);
        }
 
        super.paint(g);
    }
}