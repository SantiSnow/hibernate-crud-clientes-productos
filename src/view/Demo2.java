package view;
import javax.swing.JFrame;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JButton;

import src.Cliente;
import src.Pedido;

public class Demo2 extends JFrame {

	private JPanel jContentPane = null;
	private JTextArea jTextArea = null;

	public Demo2(List<Pedido> lista) {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		initialize(lista);
	}

	private void initialize(List<Pedido> lista) {
		this.setContentPane(getJContentPane(lista));
		this.setSize(800, 800);
	
	}

	
	private JPanel getJContentPane(List<Pedido> lista) {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJTextArea(lista), null);
			
		}
		return jContentPane;
	}

	
	private JTextArea getJTextArea(List<Pedido> lista) {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setBounds(20, 20, 650, 650);
			for (Pedido i: lista) {
				//jTextArea.setText(i);
				jTextArea.append(i + "\n\n");
			}
		}
		return jTextArea;
	}
	
}



