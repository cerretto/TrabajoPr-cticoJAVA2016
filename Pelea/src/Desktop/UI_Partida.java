package Desktop;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class UI_Partida extends JFrame {

	private JPanel contentPane;
	private JTextField txtVida1;
	private JTextField txtEnergia1;
	private JTextField txtEvasion1;
	private JTextField txtDefensa1;
	private JTextField txtVida2;
	private JTextField txtEnergia2;
	private JTextField txtEvasion2;
	private JTextField txtDefensa2;
	private JTextField txtEnergiaAtaque;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI_Partida frame = new UI_Partida();
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
	public UI_Partida() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPersonaje1 = new JLabel("Personaje 1");
		lblPersonaje1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPersonaje1.setBounds(41, 11, 101, 14);
		contentPane.add(lblPersonaje1);
		
		JLabel lblPersonaje2 = new JLabel("Personaje 2");
		lblPersonaje2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPersonaje2.setBounds(262, 11, 101, 14);
		contentPane.add(lblPersonaje2);
		
		JLabel lblVida1 = new JLabel("Vida:");
		lblVida1.setBounds(22, 46, 46, 14);
		contentPane.add(lblVida1);
		
		JLabel lblEnergia1 = new JLabel("Energ\u00EDa:");
		lblEnergia1.setBounds(22, 71, 46, 14);
		contentPane.add(lblEnergia1);
		
		JLabel lblEvasion1 = new JLabel("Evasi\u00F3n:");
		lblEvasion1.setBounds(22, 96, 46, 14);
		contentPane.add(lblEvasion1);
		
		JLabel lblDefensa1 = new JLabel("Defensa:");
		lblDefensa1.setBounds(22, 120, 46, 14);
		contentPane.add(lblDefensa1);
		
		JLabel lblVida2 = new JLabel("Vida:");
		lblVida2.setBounds(242, 48, 46, 14);
		contentPane.add(lblVida2);
		
		JLabel lblEnergia2 = new JLabel("Energ\u00EDa:");
		lblEnergia2.setBounds(242, 71, 46, 14);
		contentPane.add(lblEnergia2);
		
		JLabel lblEvasion2 = new JLabel("Evasi\u00F3n:");
		lblEvasion2.setBounds(242, 96, 46, 14);
		contentPane.add(lblEvasion2);
		
		JLabel lblDefensa2 = new JLabel("Defensa:");
		lblDefensa2.setBounds(242, 120, 46, 14);
		contentPane.add(lblDefensa2);
		
		txtVida1 = new JTextField();
		txtVida1.setEnabled(false);
		txtVida1.setBounds(87, 43, 59, 20);
		contentPane.add(txtVida1);
		txtVida1.setColumns(10);
		
		txtEnergia1 = new JTextField();
		txtEnergia1.setEnabled(false);
		txtEnergia1.setColumns(10);
		txtEnergia1.setBounds(87, 68, 59, 20);
		contentPane.add(txtEnergia1);
		
		txtEvasion1 = new JTextField();
		txtEvasion1.setEnabled(false);
		txtEvasion1.setColumns(10);
		txtEvasion1.setBounds(87, 93, 59, 20);
		contentPane.add(txtEvasion1);
		
		txtDefensa1 = new JTextField();
		txtDefensa1.setEnabled(false);
		txtDefensa1.setColumns(10);
		txtDefensa1.setBounds(87, 117, 59, 20);
		contentPane.add(txtDefensa1);
		
		txtVida2 = new JTextField();
		txtVida2.setEnabled(false);
		txtVida2.setColumns(10);
		txtVida2.setBounds(305, 43, 59, 20);
		contentPane.add(txtVida2);
		
		txtEnergia2 = new JTextField();
		txtEnergia2.setEnabled(false);
		txtEnergia2.setColumns(10);
		txtEnergia2.setBounds(305, 68, 59, 20);
		contentPane.add(txtEnergia2);
		
		txtEvasion2 = new JTextField();
		txtEvasion2.setEnabled(false);
		txtEvasion2.setColumns(10);
		txtEvasion2.setBounds(305, 93, 59, 20);
		contentPane.add(txtEvasion2);
		
		txtDefensa2 = new JTextField();
		txtDefensa2.setEnabled(false);
		txtDefensa2.setColumns(10);
		txtDefensa2.setBounds(305, 117, 59, 20);
		contentPane.add(txtDefensa2);
		
		JRadioButton rdbtnAtacar = new JRadioButton("Atacar");
		rdbtnAtacar.setBounds(125, 147, 66, 23);
		contentPane.add(rdbtnAtacar);
		
		JRadioButton rdbtnDefender = new JRadioButton("Defender");
		rdbtnDefender.setBounds(224, 147, 109, 23);
		contentPane.add(rdbtnDefender);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(57, 227, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnComenzarPartida = new JButton("Comenzar Partida");
		btnComenzarPartida.setEnabled(false);
		btnComenzarPartida.setBounds(280, 227, 117, 23);
		contentPane.add(btnComenzarPartida);
		
		JLabel lblTurno = new JLabel("Turno:");
		lblTurno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTurno.setBounds(22, 173, 46, 14);
		contentPane.add(lblTurno);
		
		JLabel lblEnergaAtaque = new JLabel("Energ\u00EDa Ataque:");
		lblEnergaAtaque.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnergaAtaque.setBounds(22, 198, 109, 14);
		contentPane.add(lblEnergaAtaque);
		
		txtEnergiaAtaque = new JTextField();
		txtEnergiaAtaque.setBounds(122, 196, 66, 20);
		contentPane.add(txtEnergiaAtaque);
		txtEnergiaAtaque.setColumns(10);
		
		JLabel lblPersonaje = new JLabel("Personaje 1 o 2");
		lblPersonaje.setBounds(71, 174, 101, 14);
		contentPane.add(lblPersonaje);
	}
}
