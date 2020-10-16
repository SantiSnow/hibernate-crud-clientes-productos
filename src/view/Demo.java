package view;

import java.util.List;

import javax.swing.*;

import src.Cliente;

public class Demo extends JFrame {

	private JPanel jContentPane = null;
	private JTextArea jTextArea = null;

	public Demo(List<Cliente> lista) {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		initialize(lista);
	}

	private void initialize(List<Cliente> lista) {
		this.setContentPane(getJContentPane(lista));
		this.setSize(800, 800);
	
	}

	
	private JPanel getJContentPane(List<Cliente> lista) {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJTextArea(lista), null);
			
		}
		return jContentPane;
	}

	
	private JTextArea getJTextArea(List<Cliente> lista) {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setBounds(20, 20, 650, 650);
			for (Cliente i: lista) {
				//jTextArea.setText(i);
				jTextArea.append(i + "\n\n");
			}
		}
		return jTextArea;
	}
	
}



