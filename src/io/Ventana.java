package io;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import entities.Aventura;

public class Ventana implements InOutputable {

	private JFrame ventanaFrame;
	private JTextArea mainTextArea;
	private JTextField comandoTextInput;
	private JTextArea inventarioTextArea;

	public Ventana() {
		initialize();
		ventanaFrame.setVisible(true);
	}
	
	public void setText(String text) {
		mainTextArea.append(text +"\n");
	}
	
	public void setInventario(String item) {
		inventarioTextArea.append(item +"\n");
	}
	
	public String getInput() {
		String text = null;
		
		try {
			Thread.currentThread().wait();			
			text = comandoTextInput.getText();
			comandoTextInput.setText("");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return text;
	}
	

	private void initialize() {
		Ventana self = this;
		ventanaFrame = new JFrame();
		ventanaFrame.setTitle("miZork");
		ventanaFrame.setBounds(100, 100, 600, 400);
		ventanaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaFrame.getContentPane().setLayout(null);

		JButton enviarButton = new JButton("Enviar");
		enviarButton.setBounds(489, 337, 105, 35);
		ventanaFrame.getContentPane().add(enviarButton);

		mainTextArea = new JTextArea();
		mainTextArea.setLineWrap(true);
		mainTextArea.setColumns(45);
		mainTextArea.setRows(15);
		mainTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(mainTextArea);
		scrollPane.setBounds(6, 6, 342, 296);
		mainTextArea.setAutoscrolls(true);
		DefaultCaret caret = (DefaultCaret) mainTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		ventanaFrame.getContentPane().add(scrollPane);
		
		comandoTextInput = new JTextField();
		comandoTextInput.setToolTipText("Ingrese HELP para recibir ayuda.");
		comandoTextInput.setBounds(6, 336, 477, 35);
		comandoTextInput.addKeyListener(new KeyAdapter() {			
			public void keyPressed(KeyEvent e) {
				synchronized (self) {
				    if (e.getKeyCode()==KeyEvent.VK_ENTER){
				    	self.notify();
				    }
				}
			}
		});
		ventanaFrame.getContentPane().add(comandoTextInput);
		comandoTextInput.setColumns(100);

		inventarioTextArea = new JTextArea();
		inventarioTextArea.setEditable(false);
		inventarioTextArea.setBounds(360, 59, 234, 243);
		ventanaFrame.getContentPane().add(inventarioTextArea);

		JLabel inventarioLabel = new JLabel("Inventario:");
		inventarioLabel.setBounds(360, 33, 91, 16);
		ventanaFrame.getContentPane().add(inventarioLabel);

		JLabel lblNewLabel_1 = new JLabel("Instrucción:");
		lblNewLabel_1.setBounds(6, 314, 99, 16);
		ventanaFrame.getContentPane().add(lblNewLabel_1);
		comandoTextInput.requestFocus();
	}

	@Override
	public String getValue(String message) {
		synchronized (this) {
			try {
				showMessage(message);
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			String text = comandoTextInput.getText();
			comandoTextInput.setText("");
			showMessage("  >> " + text);
			return text;
		}
	}

	@Override
	public void showMessage(String message) {
		if(message != null)
			mainTextArea.append(message + "\n");
	}

	@Override
	public void showError(String errorMessage) {
		mainTextArea.append("ERROR" + errorMessage + "\n");
	}
}