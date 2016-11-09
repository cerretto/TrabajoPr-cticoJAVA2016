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
//import util.ApplicationException;
//import util.SuperLogger;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;
import java.awt.Font;

public class UI_Personaje {
	
	private PersonajeLogic ctrl;
	Personaje perActual;

	private JFrame frame;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtVida;
	private JTextField txtEnergia;
	private JTextField txtDefensa;
	private JTextField txtEvasion;
	private JTextField txtPtsrest;

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
		perActual = new Personaje();
		limpiarCampos();
	}
	
	public UI_Personaje(Personaje per){
		this();
		MapearAFormulario(per);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 54, 70, 15);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblVida = new JLabel("Vida ");
		lblVida.setBounds(22, 121, 70, 15);
		frame.getContentPane().add(lblVida);
		
		JLabel lblEnergia = new JLabel("Energ\u00EDa");
		lblEnergia.setBounds(22, 147, 70, 15);
		frame.getContentPane().add(lblEnergia);
		
		JButton btnResetear = new JButton("Resetear");
		btnResetear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarCampos();
			}
		});
		btnResetear.setBounds(210, 230, 117, 25);
		frame.getContentPane().add(btnResetear);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelar();
			}
		});
		btnCancelar.setBounds(369, 230, 117, 25);
		frame.getContentPane().add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				guardar();
			}
		});
		btnGuardar.setBounds(369, 147, 117, 25);
		frame.getContentPane().add(btnGuardar);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(22, 28, 70, 15);
		frame.getContentPane().add(lblId);
		
		txtId = new JTextField();
		txtId.setBackground(UIManager.getColor("TextField.inactiveBackground"));
		txtId.setFont(new Font("Dialog", Font.BOLD, 12));
		txtId.setForeground(UIManager.getColor("TextField.inactiveBackground"));
		txtId.setEnabled(false);
		txtId.setBounds(89, 26, 114, 19);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setColumns(10);
		txtNombre.setBounds(89, 52, 114, 19);
		frame.getContentPane().add(txtNombre);
		
		JLabel lblDefensa = new JLabel("Defensa");
		lblDefensa.setBounds(22, 174, 70, 15);
		frame.getContentPane().add(lblDefensa);
		
		JLabel lblEvasion = new JLabel("Evasi\u00F3n");
		lblEvasion.setBounds(22, 198, 69, 15);
		frame.getContentPane().add(lblEvasion);
		
		txtVida = new JTextField();
		txtVida.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				calcularPtsRestantes();
			}
		});
		txtVida.setBackground(Color.WHITE);
		txtVida.setColumns(10);
		txtVida.setBounds(89, 119, 114, 19);
		frame.getContentPane().add(txtVida);
		
		txtEnergia = new JTextField();
		txtEnergia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				calcularPtsRestantes();
			}
		});
		txtEnergia.setBackground(Color.WHITE);
		txtEnergia.setColumns(10);
		txtEnergia.setBounds(89, 145, 114, 19);
		frame.getContentPane().add(txtEnergia);
		
		txtDefensa = new JTextField();
		txtDefensa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				calcularPtsRestantes();
			}
		});
		txtDefensa.setBackground(Color.WHITE);
		txtDefensa.setColumns(10);
		txtDefensa.setBounds(89, 176, 114, 19);
		frame.getContentPane().add(txtDefensa);
		
		txtEvasion = new JTextField();
		txtEvasion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				calcularPtsRestantes();
			}
		});
		txtEvasion.setBackground(Color.WHITE);
		txtEvasion.setColumns(10);
		txtEvasion.setBounds(89, 199, 114, 19);
		frame.getContentPane().add(txtEvasion);
		
		JLabel lblPtsrest = new JLabel("Ptos. restantes");
		lblPtsrest.setBounds(10, 235, 117, 15);
		frame.getContentPane().add(lblPtsrest);
		
		txtPtsrest = new JTextField();
		txtPtsrest.setEnabled(false);
		txtPtsrest.setForeground(Color.WHITE);
		txtPtsrest.setFont(new Font("Dialog", Font.BOLD, 12));
		txtPtsrest.setColumns(10);
		txtPtsrest.setBackground(UIManager.getColor("TextField.background"));
		txtPtsrest.setBounds(124, 233, 43, 19);
		frame.getContentPane().add(txtPtsrest);
		
		JLabel lblMaxd = new JLabel("(m\u00E1x. 20) ");
		lblMaxd.setBounds(210, 180, 70, 15);
		frame.getContentPane().add(lblMaxd);
		
		JLabel lblMaxe = new JLabel("(m\u00E1x. 80) ");
		lblMaxe.setBounds(210, 198, 70, 15);
		frame.getContentPane().add(lblMaxe);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				buscar();
			}
		});
		btnBuscar.setBounds(215, 49, 117, 25);
		frame.getContentPane().add(btnBuscar);
		
		JButton btnElegir = new JButton("Elegir");
		btnElegir.setBounds(369, 49, 117, 25);
		frame.getContentPane().add(btnElegir);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				delete();
			}
		});
		btnBorrar.setBounds(369, 184, 117, 25);
		frame.getContentPane().add(btnBorrar);
	}


	private void limpiarCampos() {
		txtId.setText("");
		txtNombre.setText("");
		txtVida.setText("");
		txtEnergia.setText("");
		txtDefensa.setText("");
		txtEvasion.setText("");
		txtPtsrest.setText("200");
	}
	
	public void buscar(){
		if(this.txtNombre.getText().isEmpty()){
			notifyUser("Debe ingresar un nombre por el cual buscar!");
		} else{
			try{
				perActual = ctrl.getByNombre(txtNombre.getText());
				if (perActual.getNombre() == null){
					notifyUser("Personaje inexistente !");
				} else{
					this.MapearAFormulario(perActual);
					notifyUser("Hola " + perActual.getNombre() + " !");
				}
				
			} catch (Exception ex) {
				notifyUser(ex.getMessage());
			}
		}
	}
	
	public void guardar(){
		if(datosValidos()){
			try{
				perActual = this.MapearDeFormulario();
				if(perActual.getId() > 0){
					perActual.setEstData(Entidad.estadoData.Modified);
				} else {
					perActual.setEstData(Entidad.estadoData.New);
				}
				ctrl.guardar(perActual);
				notifyUser("Guardado con exito.");
				
			} catch(Exception ex){
				notifyUser(ex.getMessage());
			}
		}
		
	}
	
	public void delete(){
		try{
			perActual = this.MapearDeFormulario();
			if(perActual.getId() > 0 ){
				perActual.setEstData(Entidad.estadoData.Deleted);
				ctrl.guardar(perActual);
				notifyUser("Personaje eliminado con exito.");
			}else{
				limpiarCampos();
			}
		}
		catch(Exception ex){
			notifyUser(ex.getMessage());
		}
	}
	
	public void cancelar(){
		terminate();
	}
	
	public void calcularPtsRestantes(){
		int ptsRestantes = perActual.getPtsTotales();
		if(!txtVida.getText().equals("")){
			try{
				ptsRestantes -= Integer.parseInt(txtVida.getText());
				
			} catch (Exception ex){
				notifyUser(ex.getMessage());
			}
		}
		if(!txtDefensa.getText().equals("")){
			try{
				ptsRestantes -= Integer.parseInt(txtDefensa.getText());
			} catch (Exception ex){
				notifyUser(ex.getMessage());
			}
		}
		if(!txtEvasion.getText().equals("")){
			try{
				ptsRestantes -= Integer.parseInt(txtEvasion.getText());
			} catch (Exception ex){
				notifyUser(ex.getMessage());
			}
		}
		if(!txtEnergia.getText().equals("")){
			try{
				ptsRestantes -= Integer.parseInt(txtEnergia.getText());
			} catch (Exception ex){
				notifyUser(ex.getMessage());
			}
		}
		if (ptsRestantes < 0){
			ptsRestantes = 0;
			txtPtsrest.setText(String.valueOf(ptsRestantes));
			notifyUser("Se ha excedido de su limite de puntos restantes");
		}else{
			txtPtsrest.setText(String.valueOf(ptsRestantes));
		}
		
	}

	public void MapearAFormulario(Personaje p){
		if(p.getId()>0) txtId.setText(String.valueOf(p.getId()));
		txtNombre.setText(p.getNombre());
		txtVida.setText(String.valueOf(p.getVida()));
		txtEnergia.setText(String.valueOf(p.getEnergia()));
		txtDefensa.setText(String.valueOf(p.getDefensa()));
		txtEvasion.setText(String.valueOf(p.getEvasion()));
		txtPtsrest.setText(String.valueOf(p.getPtsTotales()));
	}
	
	public Personaje MapearDeFormulario(){
		Personaje p = new Personaje();
		if(!txtId.getText().isEmpty()) p.setId(Integer.parseInt(txtId.getText()));
		p.setNombre(txtNombre.getText());
		p.setVida(Integer.parseInt(txtVida.getText()));
		p.setEnergia(Integer.parseInt(txtEnergia.getText()));
		p.setDefensa(Integer.parseInt(txtDefensa.getText()));
		p.setEvasion(Integer.parseInt(txtEvasion.getText()));
		p.setPtsTotales(Integer.parseInt(txtPtsrest.getText()));
		return p;
	}
	
	public boolean datosValidos(){
		boolean valido=true;
		String errores ="";
		String nombre = this.txtNombre.getText().trim();
		int vida = stringToInt(txtVida.getText().trim());
		int evasion = stringToInt(txtEvasion.getText().trim());
		int defensa = stringToInt(txtDefensa.getText().trim());
		int energia = stringToInt(txtEnergia.getText().trim());
		
		if(txtNombre.getText().trim().length()==0
			|| txtVida.getText().trim().length()==0
			|| txtEvasion.getText().trim().length()==0
			|| txtDefensa.getText().trim().length()==0
			|| txtEnergia.getText().trim().length()==0){
			valido=false;
			errores += "Complete todos los campos\n";
		}
		if( !txtId.getText().matches("[0-9]*")
					|| !txtVida.getText().matches("[0-9]*")
					|| !txtDefensa.getText().matches("[0-9]*")
					|| !txtEnergia.getText().matches("[0-9]*")
					|| !txtEvasion.getText().matches("[0-9]*")){
			valido=false;
			errores += "Datos incorrectos en campos numericos\n";
		}
		
		
		if(defensa > 20 || evasion > 80){
			valido=false;
			errores += "Ha superado los puntos permitidos de Evasion(80) y/o Defensa(20)\n";
		}
		if( (vida + energia + defensa + evasion)  > perActual.getPtsTotales()){
			valido = false;
			errores += "Los atributos del personaje superan los puntos disponibles\n";
		}		
		if(!valido) notifyUser(errores);
				
		return valido;
	}
	
	public void notifyUser(String mensaje) {
		JOptionPane.showMessageDialog(this.frame, mensaje);
	}
	
	private int stringToInt(String text){
		int campo = text.isEmpty() ? 0 : Integer.parseInt(text);
		return campo;
	}
	
	public void terminate(){
		this.frame.dispose();
	}
}