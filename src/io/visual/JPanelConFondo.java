package io.visual;

import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

import entities.Dibujador;
import entities.Item;
import entities.Location;
import entities.NPC;
import entities.Posicion;
 
public class JPanelConFondo extends JPanel {
	private static final long serialVersionUID = 1L;
	private Location location;
	private NPC[] npcArray;
	private List<Item> itemList;
 
    public JPanelConFondo() {
    	this.location = null;
    	this.npcArray = null;
    	this.itemList = null;
    }

    
    public void changeImage( String escenario, Location location ) {    	
    	this.location = location;
    		    
	    repaint();
    }
 
    @Override
    public void paint(Graphics g) {    	        
    	if ( location != null ) {
    		drawBackground(g);
        	
        	this.npcArray = location.getAllNPCs();
        	if ( npcArray != null ) drawNPCs(g);
        	
        	this.itemList = location.getAllItems();
        	if ( itemList != null ) drawItems(g);
    	} else {
    		drawDefault(g);
    	}
    	   	    	    	            	
    	setOpaque(false);
 
        super.paint(g);
    }
    
    private void drawDefault(Graphics g) {
    	String path = "./General/Images/Zork.png";
    	int imageSize = 200;
		Posicion posicion = new Posicion( (getWidth()-imageSize)/2, (getHeight()-imageSize)/2, imageSize, imageSize);
		Dibujador dibujador = new Dibujador();
		dibujador.dibujar(g, this, path, posicion);
	}


	private void drawItems(Graphics g) {
    	for(Item item : itemList) {
    		item.draw(g, this);
    	}		
	}
    
    private void drawNPCs(Graphics g) {
    	for(NPC npc : npcArray) {
    		npc.draw(g, this);
    	}		
	}

	private void drawBackground(Graphics g) {
		location.draw(g, this);		
	}
}