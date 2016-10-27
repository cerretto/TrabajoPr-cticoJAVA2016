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
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class UI_ABM extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtIngresePersonaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

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
		
		JButton btnEditarPersonaje = new JButton("Editar Personaje");
		btnEditarPersonaje.setVerticalAlignment(SwingConstants.TOP);
		
		JButton btnElimarPersonaje = new JButton("Eliminar Personaje");
		
		JButton btnLimpiar = new JButton("Limpiar");
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
	}
}
