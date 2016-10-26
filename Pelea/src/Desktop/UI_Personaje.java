package Desktop;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//import org.apache.logging.log4j.Level;

import javax.swing.JCheckBox;
import javax.swing.JButton;

import entities.*;
import logic.*;
import util.ApplicationException;
//import util.SuperLogger;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class UI_Personaje {
	
	private PersonajeLogic ctrl;
	Personaje perActual;

	private JFrame frame;
	private JTextField textId;
	private JTextField textNombre;
	private JTextField textVida;
	private JTextField textEnergia;
	private JTextField textDefensa;
	private JTextField textEvasion;
	private JTextField textPtsrest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI_Personaje window = new UI_Personaje();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI_Personaje() {
		initialize();
		ctrl= new PersonajeLogic();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 54, 70, 15);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblVida = new JLabel("Vida ");
		lblVida.setBounds(22, 121, 32, 15);
		frame.getContentPane().add(lblVida);
		
		JLabel lblEnergia = new JLabel("Energ\u00EDa");
		lblEnergia.setBounds(22, 147, 43, 15);
		frame.getContentPane().add(lblEnergia);
		
		JButton btnResetear = new JButton("Resetear");
		btnResetear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				agregar();
			}
		});
		btnResetear.setBounds(262, 77, 117, 25);
		frame.getContentPane().add(btnResetear);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		btnCancelar.setBounds(262, 131, 117, 25);
		frame.getContentPane().add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				buscar();
			}
		});
		btnGuardar.setBounds(262, 23, 117, 25);
		frame.getContentPane().add(btnGuardar);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(22, 28, 70, 15);
		frame.getContentPane().add(lblId);
		
		textId = new JTextField();
		textId.setEditable(false);
		textId.setEnabled(false);
		textId.setBounds(69, 25, 114, 19);
		frame.getContentPane().add(textId);
		textId.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBackground(Color.WHITE);
		textNombre.setEnabled(false);
		textNombre.setColumns(10);
		textNombre.setBounds(69, 51, 114, 19);
		frame.getContentPane().add(textNombre);
		
		JLabel lblDefensa = new JLabel("Defensa");
		lblDefensa.setBounds(22, 182, 43, 15);
		frame.getContentPane().add(lblDefensa);
		
		JLabel lblEvasion = new JLabel("Evasi\u00F3n");
		lblEvasion.setBounds(23, 211, 43, 15);
		frame.getContentPane().add(lblEvasion);
		
		textVida = new JTextField();
		textVida.setBackground(Color.WHITE);
		textVida.setEnabled(false);
		textVida.setColumns(10);
		textVida.setBounds(69, 118, 114, 19);
		frame.getContentPane().add(textVida);
		
		textEnergia = new JTextField();
		textEnergia.setBackground(Color.WHITE);
		textEnergia.setEnabled(false);
		textEnergia.setColumns(10);
		textEnergia.setBounds(69, 147, 114, 19);
		frame.getContentPane().add(textEnergia);
		
		textDefensa = new JTextField();
		textDefensa.setBackground(Color.WHITE);
		textDefensa.setEnabled(false);
		textDefensa.setColumns(10);
		textDefensa.setBounds(69, 179, 114, 19);
		frame.getContentPane().add(textDefensa);
		
		textEvasion = new JTextField();
		textEvasion.setBackground(Color.WHITE);
		textEvasion.setEnabled(false);
		textEvasion.setColumns(10);
		textEvasion.setBounds(69, 208, 114, 19);
		frame.getContentPane().add(textEvasion);
		
		JLabel lblPtsrest = new JLabel("Ptos. restantes");
		lblPtsrest.setBounds(10, 235, 82, 15);
		frame.getContentPane().add(lblPtsrest);
		
		textPtsrest = new JTextField();
		textPtsrest.setEnabled(false);
		textPtsrest.setColumns(10);
		textPtsrest.setBackground(SystemColor.controlHighlight);
		textPtsrest.setBounds(89, 232, 43, 19);
		frame.getContentPane().add(textPtsrest);
	}

	protected void modificar() {
		try {
			ctrl.guardar(MapearDeFormulario());
			limpiarCampos();
		} catch (ApplicationException appe) {
			notifyUser(appe.getMessage(),appe, Level.DEBUG);
		} catch (ArithmeticException are){
			notifyUser("Ha ocurrido algo inesperado, consulte al administrador de sistemas.", are, Level.ERROR);
		} catch (Exception e){
			notifyUser("Ha ocurrido algo totalmente inesperado, consulte al administrador de sistemas.",e, Level.FATAL);
		} utils
	}

	protected void agregar() {
		if(datosValidos()){
			try {
				Personaje p=MapearDeFormulario();
				ctrl.add(p);
				MapearAFormulario(p);
				//limpiarCampos();
			} catch (ApplicationException ae) {
				notifyUser(ae.getMessage(),ae, Level.DEBUG);
			}
		}
	}

	private void limpiarCampos() {
		textId.setText("");
		textNombre.setText("");
		textVida.setText("0");
		textEnergia.setText("0");
		textDefensa.setText("0");
		textEvasion.setText("0");
		textPtsrest.setText("200");
	}

	protected void buscar() {
		Personaje p = ctrl.getByNombre(MapearDeFormulario());
		if(p!=null){
			MapearAFormulario(p);
		}
	}

	public void MapearAFormulario(Personaje p){
		if(p.getId()>0) textId.setText(String.valueOf(p.getId()));
		textNombre.setText(p.getNombre());
		textVida.setText(String.valueOf(p.getVida()));
		textEnergia.setText(String.valueOf(p.getEnergia()));
		textDefensa.setText(String.valueOf(p.getDefensa()));
		textEvasion.setText(String.valueOf(p.getEvasion()));
		textPtsrest.setText(String.valueOf(p.getPtsTotales()));
	}
	
	public Personaje MapearDeFormulario(){
		Personaje p = new Personaje();
		if(!textId.getText().isEmpty()) p.setId(Integer.parseInt(textId.getText()));
		p.setNombre(textNombre.getText());
		p.setVida(Integer.parseInt(textVida.getText()));
		p.setEnergia(Integer.parseInt(textEnergia.getText()));
		p.setDefensa(Integer.parseInt(textDefensa.getText()));
		p.setEvasion(Integer.parseInt(textEvasion.getText()));
		p.setPtsTotales(Integer.parseInt(textPtsrest.getText()));
		return p;
	}
	
	public boolean datosValidos(){
		boolean valido=true;
		if(txtDni.getText().trim().length()==0
			|| textNombre.getText().trim().length()==0
			|| txtApellido.getText().trim().length()==0){
			valido=false;
			notifyUser("Complete todos los campos");
		}
		if(valido && !txtDni.getText().matches("[0-9]*")){
			valido=false;
			notifyUser("DNI inválido");
		}
			
		return valido;
	}
	
	private void notifyUser(String mensaje) {
		JOptionPane.showMessageDialog(this.frame, mensaje);
	}

	private void notifyUser(String mensaje, Exception e, Level l) {
		notifyUser(mensaje);
		SuperLogger.logger.log(l, mensaje, e);
	}
}