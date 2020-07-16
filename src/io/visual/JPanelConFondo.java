package io.visual;

import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;
import entities.Item;
import entities.Location;
import entities.NPC;
 
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
    	if ( location == null ) return;
    	
    	drawBackground(g);
    	
    	this.npcArray = location.getAllNPCs();
    	if ( npcArray != null ) drawNPCs(g);
    	
    	this.itemList = location.getAllItems();
    	if ( itemList != null ) drawItems(g);
    	    	    	            	
    	setOpaque(false);
 
        super.paint(g);
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