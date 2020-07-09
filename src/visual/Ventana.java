package visual;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Ventana {

	private JFrame ventanaFrame;
	private JTextField comandoTextInput;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Ventana window = new Ventana();
//					window.ventanaFrame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
	public Ventana() {
		initialize();
		ventanaFrame.setVisible(true);
	}

	private void initialize() {
		ventanaFrame = new JFrame();
		ventanaFrame.setTitle("miZork");
		ventanaFrame.setBounds(100, 100, 600, 400);
		ventanaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaFrame.getContentPane().setLayout(null);

		comandoTextInput = new JTextField();
		comandoTextInput.setToolTipText("Ingrese HELP para recibir ayuda.");
		comandoTextInput.setBounds(6, 336, 477, 35);
		ventanaFrame.getContentPane().add(comandoTextInput);
		comandoTextInput.setColumns(100);

		JButton enviarButton = new JButton("Enviar");
		enviarButton.setBounds(489, 337, 105, 35);
		ventanaFrame.getContentPane().add(enviarButton);

		JTextArea mainTextArea = new JTextArea();
		mainTextArea.setLineWrap(true);
		mainTextArea.setColumns(45);
		mainTextArea.setRows(10000);
		mainTextArea.setBounds(6, 6, 342, 296);
		ventanaFrame.getContentPane().add(mainTextArea);
		mainTextArea.setAutoscrolls(true);

		JTextArea inventarioTextArea = new JTextArea();
		inventarioTextArea.setEditable(false);
		inventarioTextArea.setBounds(360, 59, 234, 243);
		ventanaFrame.getContentPane().add(inventarioTextArea);

		JLabel inventarioLabel = new JLabel("Inventario:");
		inventarioLabel.setBounds(360, 33, 91, 16);
		ventanaFrame.getContentPane().add(inventarioLabel);

		JLabel lblNewLabel_1 = new JLabel("Instrucción:");
		lblNewLabel_1.setBounds(6, 314, 99, 16);
		ventanaFrame.getContentPane().add(lblNewLabel_1);
	}
}