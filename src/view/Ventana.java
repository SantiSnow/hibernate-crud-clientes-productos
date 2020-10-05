package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Ventana extends JFrame{
	
	
	private JPanel jContentPane = null;
	private JTextArea jTextArea = null;
	
	List<String> lista = new ArrayList<String>();

	public Ventana(String cliente1, String cliente2, String cliente3, String cliente4, String cliente5) {
		
		lista.add(cliente1);
		lista.add(cliente2);
		lista.add(cliente3);
		lista.add(cliente4);
		lista.add(cliente5);
		
		
		setSize(600, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("Mi aplicación de practica");
		setLocation(10, 10);
		
		inicializarComponentes();
		getJContentPane();
		getJTextArea(lista);
	}
	
	private void inicializarComponentes() {
		this.setContentPane(getJContentPane());
	}
	
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJTextArea(lista), null);
			
		}
		return jContentPane;
	}
	
	private JTextArea getJTextArea(List<String> lista) {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setBounds(20, 20, 400, 600);
			for (String i: lista) {
				jTextArea.append(i + "\n");
			}
		}
		return jTextArea;
	}
	
}
