package view;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.*;
import model.*;

public class VentanaRegistrosHistoricos extends JFrame{

	JPanel miPanel;
	JTextArea visor;
	JScrollPane scroll;
	JLabel titulo;
	
	public VentanaRegistrosHistoricos(List<PedidoHuerfano> listaHistorica) {
		setSize(800, 600);
		setTitle("Tabla Historica");
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		agregarPanel();
		agregarVisor();
		agregarTitulo();
		mostrarPedidos(listaHistorica);
		
		setVisible(true);
		
	}
	
	//agregar panel
	public void agregarPanel() {
		miPanel = new JPanel();
		miPanel.setBackground(Color.white);
		miPanel.setLayout(null);
		add(miPanel);
	}
	
	//agrega el visor
	public void agregarVisor() {
		visor = new JTextArea(); 
		visor.setBounds(50, 80, 600, 400);
		visor.setBackground(Color.WHITE);
			
		scroll = new JScrollPane(visor);
		scroll.setBounds(50, 80, 600, 400);
		scroll.setBackground(Color.WHITE);
			
		miPanel.add(scroll);
	}
	
	public void agregarTitulo() {
		titulo = new JLabel();
		titulo.setText("Tabla historica de pedidos:");
		titulo.setBounds(15, 15, 600, 30);
		titulo.setFont(new Font("arial", Font.BOLD, 25));
		miPanel.add(titulo);
	}
	
	public void mostrarPedidos(List<PedidoHuerfano> listaHistorica) {
		for (PedidoHuerfano i: listaHistorica){
			visor.append("\nPedido:");
			visor.append("\nID del pedido: " + i.getId());
			visor.append("\nForma de pago: " + i.getFormaPago());
			visor.append("\n ");
			visor.append("\n ");
		}
	}
}
