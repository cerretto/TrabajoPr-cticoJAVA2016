package Desktop;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import entities.Entidad;
import entities.Personaje;
import logic.PersonajeLogic;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class UI_ABM extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtIngresePersonaje;
	
	private Personaje perActual;
	private PersonajeLogic ctrl;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI_ABM frame = new UI_ABM();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public UI_ABM() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		txtIngresePersonaje = new JTextField();
		txtIngresePersonaje.setText("Ingrese Personaje");
		txtIngresePersonaje.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnAgregarPersonaje = new JButton("Agregar Personaje");
		btnAgregarPersonaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarPersonaje();
			}
		});
		
		JButton btnEditarPersonaje = new JButton("Editar Personaje");
		btnEditarPersonaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarPersonaje();
			}
		});
		btnEditarPersonaje.setVerticalAlignment(SwingConstants.TOP);
		
		JButton btnElimarPersonaje = new JButton("Eliminar Personaje");
		btnElimarPersonaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarPersonaje();
			}
		});
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshLista();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(191)
					.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAgregarPersonaje)
					.addContainerGap(19, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(41)
					.addComponent(btnEditarPersonaje, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
					.addComponent(btnElimarPersonaje)
					.addGap(52))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(txtIngresePersonaje, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
					.addGap(239))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(176)
					.addComponent(btnLimpiar)
					.addContainerGap(183, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtIngresePersonaje, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar)
						.addComponent(btnAgregarPersonaje))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(40)
							.addComponent(btnEditarPersonaje))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(btnLimpiar)
							.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
							.addComponent(btnElimarPersonaje)))
					.addContainerGap())
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre Personaje"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(104);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		iniciar();
	}
	
	private void iniciar(){
		ctrl = new PersonajeLogic();
		perActual = null;
		refreshLista();
	}
	
	private void refreshLista(){
		try{
			setearTabla(new PersonajeLogic().GetAll());
		}catch(Exception ex){
			notifyUser(ex.getMessage());
		}
	}
	
	private void setearTabla(ArrayList<Personaje> personajes){
		DefaultTableModel model = makeModel();
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		try
		{
			
			for (Personaje personaje : personajes) 
			{
				model.addRow(mapearAarray(personaje));
			}

			table.setModel(model);
		}
		catch (Exception ex)
		{
			notifyUser(ex.getMessage());
		}
	}
	
	private DefaultTableModel makeModel(){
		DefaultTableModel model = (new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return false;
			}
			
		});
		model.addColumn("ID");
		model.addColumn("Nombre Personaje");
		return model;
	}
	
	private Object[] mapearAarray(Personaje per)
	{
		Object [] perArray = new Object[7];
		perArray[0] = per.getId();
		perArray[1] = per.getNombre();
		
		return perArray;
	}
	
	private Personaje getFromTabla()
	{
		//construyo arreglo de objetos de la longitud de la cantidad de columnas
		Object[] arre = new Object[table.getModel().getColumnCount()];
		//tomo la fila seleccionada
		int index = table.getSelectedRow();
		//mapeo a un arreglo
		for (int i = 0; i < table.getModel().getColumnCount(); i++)
		{
			arre[i] = table.getModel().getValueAt(index, i);
		}
		//mapeo arreglo a Personaje
		return mapFromArray(arre);
	}
	
	private Personaje mapFromArray(Object[] perArray){
		Personaje p = new Personaje();
		p.setId((int)perArray[0]);
		p.setNombre((String)perArray[1]);
		
		return p;
	}
	
	private void agregarPersonaje(){
		new UI_Personaje();
		
		refreshLista();
	}
	
	private void editarPersonaje(){
		
		if (table.getSelectedRow() == -1){
			notifyUser("Elija un personaje");
			return;
		}
				
		Personaje per = getFromTabla();
		per.setEstData(Entidad.estadoData.Modified);
		
		new UI_Personaje(per);
		
		refreshLista();
	}
	
	private void eliminarPersonaje(){
		
		if (table.getSelectedRow() == -1){
			notifyUser("Elija un personaje");
			return;
		}
		
		
		Personaje per = getFromTabla();
		String msj = "Est� seguro que desea eliminar a " + per.getNombre() + "?";
		
		int response = JOptionPane.showConfirmDialog(this, msj, "Eliminar Personaje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if (response == JOptionPane.YES_OPTION) {

			try
			{
				per.setEstData(Entidad.estadoData.Deleted);
				ctrl.guardar(per);
			}
			catch(Exception ex) {
				notifyUser(ex.getMessage());
			}
		}
		
		refreshLista();
	}
	
	public void notifyUser(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	
	
	
	
	
	
	
	
}
