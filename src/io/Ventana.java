package io;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Ventana implements InOutputable {

	private JFrame ventanaFrame;
	private JTextArea mainTextArea;
	private JTextField comandoTextInput;
	private JTextArea inventarioTextArea;
	private FileLogger fileLogger;
	private JPanelConFondo panelImagen;
	private Historial historial;
	private JTextPane ubicacion;

	public Ventana() {
		this.historial = new Historial();
		initialize();
		ventanaFrame.setVisible(true);
	}

	public void setText(String text) {
		mainTextArea.append(text + "\n");
	}

	public void setInventario(String item) {
		inventarioTextArea.append(item + "\n");
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
		ventanaFrame.setIconImage(new ImageIcon("./images/icono/cobit-19.png").getImage());
		ventanaFrame.setTitle("Zork COBIT-19");
		ventanaFrame.setBounds(100, 100, 650, 680);
		ventanaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaFrame.getContentPane().setLayout(null);
		ventanaFrame.setLocationRelativeTo(null);
		ventanaFrame.setResizable(false);
		ventanaFrame.getContentPane().setBackground(Color.BLACK);

		JButton enviarButton = new JButton("Enviar");
		enviarButton.setBackground(java.awt.Color.green);
		enviarButton.setBounds(510, 587, 105, 35);
		ventanaFrame.getContentPane().add(enviarButton);

		mainTextArea = new JTextArea();
		mainTextArea.setLineWrap(true);
		mainTextArea.setColumns(45);
		mainTextArea.setRows(15);
		mainTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(mainTextArea);
		scrollPane.setBounds(20, 250, 593, 296);
		mainTextArea.setAutoscrolls(true);
		DefaultCaret caret = (DefaultCaret) mainTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		ventanaFrame.getContentPane().add(scrollPane);
		
		try {
			File input = new File("./images/fondos/escenarioCreado/frente.jpg");
		    Image image = ImageIO.read(input);
		    
		    panelImagen = new JPanelConFondo();
		    panelImagen.setBounds(20, 10, 593, 230);
			panelImagen.setBackground(Color.ORANGE);
			panelImagen.setImagen(image);
			ventanaFrame.getContentPane().add(panelImagen);
		} catch (IOException e1) {
			System.out.println("No se pudo leer la imagen");
			e1.printStackTrace();
		}
		
		final int N=7;
		ubicacion = new JTextPane();
		StyledDocument doc = ubicacion.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		ubicacion.setBounds(30, 20, 150, 30);
		ubicacion.setText("Ubicacion");
		ubicacion.setEditable(false);
		ubicacion.setFont(new Font("Courier", Font.BOLD, 16));
		ubicacion.setBorder(BorderFactory.createEmptyBorder(N, N, N, N));
		ventanaFrame.getContentPane().add(ubicacion);

		comandoTextInput = new JTextField();
		comandoTextInput.setToolTipText("Ingrese HELP para recibir ayuda.");
		comandoTextInput.setBounds(20, 586, 477, 35);
		comandoTextInput.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				synchronized (self) {
					switch( e.getKeyCode() ) {
					case KeyEvent.VK_ENTER:
						self.notify();
						break;
					case KeyEvent.VK_UP:
						self.setPreviousCommand();
						break;
					case KeyEvent.VK_DOWN:
						self.setNextCommand();
						break;
					}
//					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//						self.notify();
//					}
				}
			}

		});

		ventanaFrame.getContentPane().add(comandoTextInput);
		comandoTextInput.setColumns(100);
		JLabel lblNewLabel_1 = new JLabel("Instruccion:");
		lblNewLabel_1.setForeground(Color.white);
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(20, 564, 99, 16);
		ventanaFrame.getContentPane().add(lblNewLabel_1);
		comandoTextInput.requestFocus();
	}

	protected void setPreviousCommand() {
		comandoTextInput.setText(historial.getPrevious());		
	}
	
	protected void setNextCommand() {
		comandoTextInput.setText(historial.getNext());		
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
			historial.add(text);
			showMessage("  >> " + text);
			return text;
		}
	}
	
	@Override
	public void changeLocation(String locationName) {
		ubicacion.setText(locationName.toUpperCase());
	}

	@Override
	public void showMessage(String message) {
		if(message != null) {
			fileLogger.juegoDice(message);
			mainTextArea.append(message + "\n");
		}
			
	}

	@Override
	public void showError(String errorMessage) {
		mainTextArea.append("ERROR" + errorMessage + "\n");
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
}