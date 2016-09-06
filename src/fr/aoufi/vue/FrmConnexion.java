package fr.aoufi.vue;

/**
 * Classe decrivant le concept de frame de connexion
 * Elle contient les champs de saisie de login et mot de passe ainsi qu'un bouton connexion
 * 
 *
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import fr.aoufi.controleur.AppListener;
import fr.aoufi.tech.ConstantesEcran;

public class FrmConnexion extends Window {

	//Variables
	//*************************************************************************************************************
	//*************************************************************************************************************
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textIdentifiant;
	private JPasswordField passwordField;
	private JButton btnConnexion;

	//Getters et Setters
	//*************************************************************************************************************
	//*************************************************************************************************************
	public JTextField getTextIdentifiant() {
		return textIdentifiant;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JButton getBtnConnexion() {
		return btnConnexion;
	}

	public void setTextIdentifiant(JTextField textIdentifiant) {
		this.textIdentifiant = textIdentifiant;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public void setBtnConnexion(JButton btnConnexion) {
		this.btnConnexion = btnConnexion;
	}

	//constructeur
	//*************************************************************************************************************
	//*************************************************************************************************************
	public FrmConnexion() {

		setResizable(false);
		setTitle("Bienvenue dans BattleRoyal");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmConnexion.class.getResource("/fr/aoufi/ressources/tournoisV.2.0 petit2.png")));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 325);

		contentPane = new JPanel();
		contentPane.setBackground(ConstantesEcran.COLOR_CELL);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		//Initialisation du panneau Nord Accueil
		//*************************************************************************************************************
		//*************************************************************************************************************
		JPanel PanelNorth = new JPanel();
		PanelNorth.setBackground(ConstantesEcran.COLOR_CELL);
		contentPane.add(PanelNorth, BorderLayout.NORTH);
		PanelNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTitreApp = new JLabel(" Battle Royal");
		lblTitreApp.setIcon(ConstantesEcran.LOGO_MOYEN);
		lblTitreApp.setForeground(SystemColor.text);
		lblTitreApp.setFont(ConstantesEcran.FONT_TITRE1);
		lblTitreApp.setHorizontalAlignment(SwingConstants.CENTER);
		PanelNorth.add(lblTitreApp);

		//Initialisation du panneau Centre et de ses 3 panneaux
		//*************************************************************************************************************
		//*************************************************************************************************************
		JPanel PanelCenter = new JPanel();
		PanelCenter.setLayout(new BorderLayout(0, 0));
		contentPane.add(PanelCenter, BorderLayout.CENTER);


		//1
		//*************************************************************************************************************
		JPanel PanelNorthNorth = new JPanel();

		JLabel lblBienvenue = new JLabel("BONJOUR, MERCI DE VOUS IDENTIFIER");
		lblBienvenue.setForeground(ConstantesEcran.COLOR_TITRE2);
		lblBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenue.setFont(ConstantesEcran.FONT_TITRE2);

		PanelNorthNorth.setBackground(ConstantesEcran.COLOR_CELL);
		PanelNorthNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		PanelNorthNorth.add(lblBienvenue);

		PanelCenter.add(PanelNorthNorth, BorderLayout.NORTH);


		//2
		//*************************************************************************************************************
		JPanel PanelNorthCenter = new JPanel();
		PanelNorthCenter.setBackground(ConstantesEcran.COLOR_CELL_CENTRALE);
		PanelNorthCenter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		PanelCenter.add(PanelNorthCenter, BorderLayout.CENTER);

		JLabel lblIdentifiant = new JLabel("  Identifiant");
		lblIdentifiant.setForeground(ConstantesEcran.COLOR_LBL_CENTRALE);
		lblIdentifiant.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdentifiant.setFont(ConstantesEcran.FONT_GLOBAL);

		JLabel lblPassword = new JLabel("Mot de passe");
		lblPassword.setForeground(ConstantesEcran.COLOR_LBL_CENTRALE);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(ConstantesEcran.FONT_GLOBAL);

		passwordField = new JPasswordField();
		passwordField.setFont(ConstantesEcran.FONT_GLOBAL);
		passwordField.setText("admin");
		passwordField.setColumns(10);
		passwordField.setBorder(ConstantesEcran.BORDER_CENTRALE);
		FlowLayout fl_PanelNorthCenter = new FlowLayout(FlowLayout.CENTER, 10, 35);
		fl_PanelNorthCenter.setAlignOnBaseline(true);
		PanelNorthCenter.setLayout(fl_PanelNorthCenter);
		PanelNorthCenter.add(lblIdentifiant);
		AppListener monListener = new AppListener(this);		//instanciation Listener sur le champs password

		passwordField.addKeyListener(monListener);

		textIdentifiant = new JTextField();
		textIdentifiant.setFont(ConstantesEcran.FONT_GLOBAL);
		textIdentifiant.setText("admin");
		textIdentifiant.setHorizontalAlignment(SwingConstants.CENTER);
		textIdentifiant.setColumns(10);
		textIdentifiant.setBorder(ConstantesEcran.BORDER_CENTRALE);
		PanelNorthCenter.add(textIdentifiant);
		PanelNorthCenter.add(lblPassword);
		PanelNorthCenter.add(passwordField);



		//3
		//*************************************************************************************************************
		JPanel PanelNorthSouth = new JPanel();
		PanelNorthSouth.setBackground(ConstantesEcran.COLOR_CELL);

		PanelCenter.add(PanelNorthSouth, BorderLayout.SOUTH);
		PanelNorthSouth.setLayout(new BorderLayout(0, 0));

		JPanel panelWest = new JPanel();
		panelWest.setBackground(ConstantesEcran.COLOR_CELL);
		contentPane.add(panelWest, BorderLayout.WEST);

		JPanel panelEast = new JPanel();
		panelEast.setBackground(ConstantesEcran.COLOR_CELL);
		contentPane.add(panelEast, BorderLayout.EAST);

		JPanel panelSouth = new JPanel();
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setBackground(ConstantesEcran.COLOR_CELL);
		panelSouth.setLayout(new BorderLayout(0, 0));

		Component vSPanelSouth01 = Box.createVerticalStrut(20);
		panelSouth.add(vSPanelSouth01, BorderLayout.NORTH);

		btnConnexion = new JButton("Connexion");
		panelSouth.add(btnConnexion, BorderLayout.CENTER);
		btnConnexion.setForeground(UIManager.getColor("Button.controlText"));
		btnConnexion.setFont(ConstantesEcran.FONT_GLOBAL);
		btnConnexion.addActionListener(monListener);
		btnConnexion.setBackground(ConstantesEcran.COLOR_CELL);

		Component vSPanelSouth02 = Box.createVerticalStrut(20);
		panelSouth.add(vSPanelSouth02, BorderLayout.SOUTH);

		Component hSPanelSouth01 = Box.createHorizontalStrut(200);
		panelSouth.add(hSPanelSouth01, BorderLayout.WEST);

		Component hSPanelSouth02 = Box.createHorizontalStrut(200);
		panelSouth.add(hSPanelSouth02, BorderLayout.EAST);


	}


}

