package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.hibernate.Session;

import src.VaciarTablas;

public class VentanaEliminaTablas  extends JFrame{
	
	JPanel miPanel;
	JLabel titulo;
	
	JButton botonEliminaProductos;
	JButton botonEliminaClientes;
	JButton botonEliminaPedidos;
	
	public VentanaEliminaTablas(Session mySession) {
		setSize(800, 600);
		setTitle("Eliminar tablas");
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		agregarPanel();
		agregarTitulo();
		agregarBotones();
		agregarListeners(mySession);
		setVisible(true);
	}
	
	//agregar panel
	public void agregarPanel() {
		miPanel = new JPanel();
		miPanel.setBackground(Color.white);
		miPanel.setLayout(null);
		add(miPanel);
	}
	
	public void agregarTitulo() {
		titulo = new JLabel();
		titulo.setText("Eliminar tablas completas:");
		titulo.setBounds(15, 15, 600, 30);
		titulo.setFont(new Font("arial", Font.BOLD, 25));
		miPanel.add(titulo);
	}
	
	public void agregarBotones() {
		botonEliminaProductos = new JButton("Vaciar tabla de productos");
		botonEliminaClientes = new JButton("Vaciar tabla de clientes");
		botonEliminaPedidos = new JButton("Vaciar tabla de pedidos");
		
		botonEliminaProductos.setBounds(50, 100, 200, 30);
		botonEliminaClientes.setBounds(50, 150, 200, 30);
		botonEliminaPedidos.setBounds(50, 200, 200, 30);
		
		botonEliminaProductos.setBackground(new Color(255, 0, 0));
		botonEliminaClientes.setBackground(new Color(255, 0, 0));
		botonEliminaPedidos.setBackground(new Color(255, 0, 0));
		
		botonEliminaProductos.setForeground(Color.WHITE);
		botonEliminaClientes.setForeground(Color.WHITE);
		botonEliminaPedidos.setForeground(Color.WHITE);
		
		miPanel.add(botonEliminaProductos);
		miPanel.add(botonEliminaClientes);
		miPanel.add(botonEliminaPedidos);
	}
	
	public void agregarListeners(Session mySession) {
		ActionListener vaciarProductos = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "Vaciar una tabla, significa eliminar todos sus registros. Esta acción no puede deshacerse");
				Integer eleccion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la tabla por completo?", "Eliminar productos", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if(eleccion == 0) {
					VaciarTablas.vaciarTablaProductos(mySession);
					JOptionPane.showMessageDialog(null, "La tabla ha sido vaciada correctamente.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Ha seleccionado no, la tabla no va a eliminarse.");
				}
				
			}
		};
		
		ActionListener vaciarClientes = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Vaciar una tabla, significa eliminar todos sus registros. Esta acción no puede deshacerse");
				Integer eleccion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la tabla por completo?", "Eliminar productos", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if(eleccion == 0) {
					VaciarTablas.vaciarTablaClientes(mySession);
					JOptionPane.showMessageDialog(null, "La tabla ha sido vaciada correctamente.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Ha seleccionado no, la tabla no va a eliminarse.");
				}
				
				
			}
		};
		
		ActionListener vaciarPedidos = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Vaciar una tabla, significa eliminar todos sus registros. Esta acción no puede deshacerse");
				Integer eleccion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la tabla por completo?", "Eliminar productos", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if(eleccion == 0) {
					VaciarTablas.vaciarTablaPedidos(mySession);
					JOptionPane.showMessageDialog(null, "La tabla ha sido vaciada correctamente.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Ha seleccionado no, la tabla no va a eliminarse.");
				}
				
				
			}
		};
		
		botonEliminaProductos.addActionListener(vaciarProductos);
		botonEliminaClientes.addActionListener(vaciarClientes);
		botonEliminaPedidos.addActionListener(vaciarPedidos);
	}

}
