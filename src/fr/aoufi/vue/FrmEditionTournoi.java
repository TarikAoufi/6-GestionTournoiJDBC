package fr.aoufi.vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import fr.aoufi.controleur.AppListener;
import fr.aoufi.tech.ConstantesEcran;

/**
 * Classe decrivant le concept de frame de connexion
 * Elle contient les champs de saisie de login et mot de passe ainsi qu'un bouton connexion
 *
 */

public class FrmEditionTournoi extends Window {

	/**
	 * Variables
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldJeux;
	private JTextField textFieldLieu;
	private JButton btnAnnuler;								
	private JButton btnModifier; 						
	private JButton btnCreer;
	private JButton btnSupprimer; 
	private JButton btnInscription; 
	private JButton btnDesinscription;
	private JButton btnDemarrer;								
	private JComboBox <String> comboBoxTaille;
	private JFormattedTextField formattedTextFieldDate;
	private JFormattedTextField formattedTextFieldDateLimite;
	private JLabel lblTitre;
	private JLabel lblInfo; 						



	/**
	 * Constructeur de la frame d'Edition des Tournois
	 */
	public FrmEditionTournoi() {


		setResizable(false);
		//		setVisible(true);
		setTitle("Battle Royal");
		setIconImage(ConstantesEcran.LOGO_FAVICON);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 515);


		//I - INSTANCIATION DES OBJETS DE LA JFRAME
		//*************************************************************************************************************
		//*************************************************************************************************************
		contentPane = new JPanel();
		setContentPane(contentPane);

		//1 - Instanciation des panneaux de la JFrame**********************************************
		JPanel panelNorth 									= new JPanel();

		JPanel panelWest 									= new JPanel();

		JPanel panelCenter 									= new JPanel();

		JPanel panelCenterNorth 							= new JPanel();
		JPanel panelCenterEast 								= new JPanel();
		JPanel panelCenterWest 								= new JPanel();
		JPanel panelCenterCenter 							= new JPanel();
		JPanel panelCenterSouth 							= new JPanel();

		JPanel panelEast 									= new JPanel();

		JPanel panelSouth 									= new JPanel();
		JPanel panelSouthNorth 								= new JPanel();
		JPanel panelSouthSouth 								= new JPanel();

		//2 - Instanciation des labels*************************************************************
		lblTitre 											= new JLabel(" Cr\u00E9ation d'une nouvelle Battle");
		lblInfo 											= new JLabel("   VEUILLEZ RENSEIGNER LES CHAMPS CI-DESSOUS");
		JLabel lblNomTournoi 								= new JLabel("  Nom de la Battle :");
		JLabel lblDate 										= new JLabel("  Date :");
		JLabel lblJeux 										= new JLabel("  Jeux :");
		JLabel lblLieu 										= new JLabel("  Lieu de la Battle :");
		JLabel lblTailleTournoi 							= new JLabel("  Taille de la Battle : ");
		JLabel lblDateLimite 								= new JLabel("  Date limite d'inscription :   ");

		//3 - Instanciation des champs de saisie et outils de selection de choix utilisateur*******
		textFieldNom 										= new JTextField();
		textFieldJeux 										= new JTextField();
		textFieldLieu 										= new JTextField();

		comboBoxTaille 										= new JComboBox <String> ();
		formattedTextFieldDate 								= new JFormattedTextField();
		formattedTextFieldDateLimite 						= new JFormattedTextField();
		
		MaskFormatter dateMask;
		MaskFormatter dateLimiteMask;
		
		try {
			dateMask 										= new MaskFormatter("##/##/####");
			
			dateMask.install(formattedTextFieldDate);
			
		} catch (ParseException e) {
			// TODO à gérer plus tard
			e.printStackTrace();
		}
		
		try {
			dateLimiteMask 									= new MaskFormatter("##/##/####");
			
			dateLimiteMask.install(formattedTextFieldDateLimite);
			
		} catch (ParseException e) {
			// TODO à gérer plus tard
			e.printStackTrace();
		}

		//4 - Instanciation des boutons de la page*************************************************
		btnAnnuler 											= new JButton("Annuler");
		btnModifier 										= new JButton("Modifier");
		btnCreer 											= new JButton("Creer");
		btnInscription 										= new JButton("S'inscrire");
		btnDesinscription									= new JButton("Se désinscrire");
		btnDemarrer											= new JButton("Démarrer la Battle");
		btnSupprimer 										= new JButton ("Supprimer la Battle"); 

		//5 - Creation des composants de séparation des elements des panneaux**********************
		Component vSPanelNorth01 							= Box.createVerticalStrut(20);
		Component vSPanelNorth02 							= Box.createVerticalStrut(5);

		Component vSPanelCenterNorth01 						= Box.createVerticalStrut(15);

		Component vSPanelCenterWest01 						= Box.createVerticalStrut(25);
		Component vSPanelCenterWest02 						= Box.createVerticalStrut(25);
		Component vSPanelCenterWest03 						= Box.createVerticalStrut(25);
		Component vSPanelCenterWest04 						= Box.createVerticalStrut(25);
		Component vSPanelCenterWest05 						= Box.createVerticalStrut(25);

		Component vSPanelCenterCenter01 					= Box.createVerticalStrut(17);
		Component vSPanelCenterCenter02 					= Box.createVerticalStrut(17);
		Component vSPanelCenterCenter03 					= Box.createVerticalStrut(17);
		Component vSPanelCenterCenter04 					= Box.createVerticalStrut(23);
		Component vSPanelCenterCenter05 					= Box.createVerticalStrut(22);

		Component vSPanelCenterSouth01 						= Box.createVerticalStrut(15);

		Component vSPanelSouthNorth01 						= Box.createVerticalStrut(2);

		Component hSPanelSouthSouth01 						= Box.createHorizontalStrut(20);
		Component hSPanelSouthSouth02 						= Box.createHorizontalStrut(1);

		FlowLayout fl_panelSouthSouth 						= (FlowLayout) panelSouthSouth.getLayout();

		//6 - Instanciation du listener de la page*************************************************
		AppListener listener 				= new AppListener(this);


		//II - FORMATAGE DES PANNEAUX ET COMPOSANTS DE LA JFRAME
		//*************************************************************************************************************
		//*************************************************************************************************************
		//1 - Formatage des panneaux de la JFrame**************************************************
		contentPane.setBackground							(ConstantesEcran.COLOR_CELL);
		contentPane.setBorder								(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout								(new BorderLayout(0, 0));


		panelNorth.setBackground							(ConstantesEcran.COLOR_CELL);
		panelNorth.setForeground							(ConstantesEcran.COLOR_CELL_CENTRALE);
		panelNorth.setLayout								(new BoxLayout(panelNorth, BoxLayout.Y_AXIS));


		panelWest.setBackground								(ConstantesEcran.COLOR_CELL);


		panelCenter.setBackground							(ConstantesEcran.COLOR_CELL_CENTRALE);
		panelCenter.setBorder								(ConstantesEcran.BORDER_CENTRALE);
		panelCenter.setLayout								(new BorderLayout(0, 0));

		panelCenterNorth.setBackground						(ConstantesEcran.COLOR_CELL_CENTRALE);

		panelCenterEast.setBackground						(ConstantesEcran.COLOR_CELL_CENTRALE);

		panelCenterWest.setBackground						(ConstantesEcran.COLOR_CELL_CENTRALE);
		panelCenterWest.setLayout							(new BoxLayout(panelCenterWest, BoxLayout.Y_AXIS));

		panelCenterCenter.setBackground						(ConstantesEcran.COLOR_CELL_CENTRALE);
		panelCenterCenter.setLayout							(new BoxLayout(panelCenterCenter, BoxLayout.Y_AXIS));

		panelCenterSouth.setBackground						(ConstantesEcran.COLOR_CELL_CENTRALE);


		panelEast.setBackground								(ConstantesEcran.COLOR_CELL);


		panelSouth.setBackground							(ConstantesEcran.COLOR_CELL);	
		panelSouth.setLayout								(new BorderLayout(0, 0));


		panelSouthNorth.setBackground						(ConstantesEcran.COLOR_CELL);	
		panelSouthSouth.setBackground						(ConstantesEcran.COLOR_CELL);
		fl_panelSouthSouth.setAlignment						(FlowLayout.RIGHT);

		//2 - Formatage des labels de la JFrame****************************************************
		lblTitre.setForeground								(ConstantesEcran.COLOR_TITRE1);
		lblTitre.setIcon									(ConstantesEcran.LOGO_MOYEN);
		lblTitre.setFont									(ConstantesEcran.FONT_TITRE1);
		lblTitre.setHorizontalAlignment						(SwingConstants.CENTER);

		lblInfo.setForeground								(ConstantesEcran.COLOR_TITRE2);
		lblInfo.setFont										(ConstantesEcran.FONT_TITRE2);
		lblInfo.setHorizontalAlignment						(SwingConstants.CENTER);

		lblNomTournoi.setForeground							(ConstantesEcran.COLOR_LBL_CENTRALE);	
		lblNomTournoi.setFont								(ConstantesEcran.FONT_GLOBAL);

		lblDate.setForeground								(ConstantesEcran.COLOR_LBL_CENTRALE);
		lblDate.setFont										(ConstantesEcran.FONT_GLOBAL);

		lblJeux.setForeground								(ConstantesEcran.COLOR_LBL_CENTRALE);	
		lblJeux.setFont										(ConstantesEcran.FONT_GLOBAL);	

		lblLieu.setForeground								(ConstantesEcran.COLOR_LBL_CENTRALE);
		lblLieu.setFont										(ConstantesEcran.FONT_GLOBAL);

		lblTailleTournoi.setForeground						(ConstantesEcran.COLOR_LBL_CENTRALE);
		lblTailleTournoi.setFont							(ConstantesEcran.FONT_GLOBAL);

		lblDateLimite.setForeground							(ConstantesEcran.COLOR_LBL_CENTRALE);
		lblDateLimite.setFont								(ConstantesEcran.FONT_GLOBAL);


		//3 - Formatage des champs de saisie et outils de selection de choix utilisateur***********
		textFieldNom.setFont								(ConstantesEcran.FONT_GLOBAL);
		textFieldNom.setBorder								(ConstantesEcran.BORDER_CENTRALE);
		textFieldNom.setEnabled(false);

		textFieldJeux.setFont								(ConstantesEcran.FONT_GLOBAL);
		textFieldJeux.setBorder								(ConstantesEcran.BORDER_CENTRALE);
		textFieldJeux.setEnabled(false);

		textFieldLieu.setFont								(ConstantesEcran.FONT_GLOBAL);
		textFieldLieu.setBorder								(ConstantesEcran.BORDER_CENTRALE);
		textFieldLieu.setEnabled(false);

		formattedTextFieldDate.setFont						(ConstantesEcran.FONT_GLOBAL);
		formattedTextFieldDate.setBorder					(ConstantesEcran.BORDER_CENTRALE);
		formattedTextFieldDate.setEnabled(false);

		formattedTextFieldDateLimite.setFont				(ConstantesEcran.FONT_GLOBAL);
		formattedTextFieldDateLimite.setBorder				(ConstantesEcran.BORDER_CENTRALE);
		formattedTextFieldDateLimite.setEnabled(false);

		comboBoxTaille.setBorder							(ConstantesEcran.BORDER_CENTRALE);
		comboBoxTaille.setFont								(ConstantesEcran.FONT_GLOBAL);
		comboBoxTaille.setModel								(new DefaultComboBoxModel <String> (new String[] {"Petit Tournoi (8 participants)", "Grand Tournoi (16 participants)"}));
		comboBoxTaille.setEnabled(false);


		//4 - Formatage des boutons de la JFrame***************************************************
		btnAnnuler.setFont									(ConstantesEcran.FONT_BOUTON);
		btnModifier.setFont									(ConstantesEcran.FONT_BOUTON);
		btnCreer.setFont									(ConstantesEcran.FONT_BOUTON);
		btnInscription.setFont								(ConstantesEcran.FONT_BOUTON);
		btnDesinscription.setFont							(ConstantesEcran.FONT_BOUTON);
		btnDemarrer.setFont									(ConstantesEcran.FONT_BOUTON);
		btnSupprimer.setFont								(ConstantesEcran.FONT_BOUTON);
		
		btnAnnuler.setBackground							(ConstantesEcran.COLOR_CELL);				
		btnModifier.setBackground							(ConstantesEcran.COLOR_CELL);				
		btnCreer.setBackground								(ConstantesEcran.COLOR_CELL);			
		btnInscription.setBackground						(ConstantesEcran.COLOR_CELL);	
		btnDesinscription.setBackground						(ConstantesEcran.COLOR_CELL);
		btnDemarrer.setBackground							(ConstantesEcran.COLOR_CELL);
		btnSupprimer.setBackground							(ConstantesEcran.COLOR_CELL);
		
	
		btnAnnuler.addActionListener(listener);										//Abonnement des bouton au listener de la JFrame
		btnModifier.addActionListener(listener);
		btnCreer.addActionListener(listener);
		btnInscription.addActionListener(listener);
		btnDesinscription.addActionListener(listener);
		btnDemarrer.addActionListener(listener);
		btnSupprimer.addActionListener(listener);
		

		btnAnnuler.setVisible								(true);					//Visibilité par defaut des boutons de la JFrame
		btnModifier.setVisible								(false);
		btnCreer.setVisible									(false);
		btnInscription.setVisible							(true);
		btnDesinscription.setVisible						(false);
		btnDemarrer.setVisible								(false);
		btnSupprimer.setVisible								(false);
		

		//III - AJOUT DES COMPOSANTS AUX PANNEAUX ET DES PANNEAUX AU CONTENTPANE DE LA JFRAME
		//*************************************************************************************************************
		//*************************************************************************************************************
		//1 - Ajout des elements nécéssaires à la partie superieure du panneau central*************
		panelCenterNorth.add								(vSPanelCenterNorth01);

		//2 - Ajout des elements nécéssaires à la partie gauche du panneau central*****************
		panelCenterWest.add									(lblNomTournoi);	
		panelCenterWest.add									(vSPanelCenterWest01);
		panelCenterWest.add									(lblDate);
		panelCenterWest.add									(vSPanelCenterWest02);
		panelCenterWest.add									(lblJeux);
		panelCenterWest.add									(vSPanelCenterWest03);
		panelCenterWest.add									(lblLieu);
		panelCenterWest.add									(vSPanelCenterWest04);
		panelCenterWest.add									(lblTailleTournoi);
		panelCenterWest.add									(vSPanelCenterWest05);
		panelCenterWest.add									(lblDateLimite);

		//3 - Ajout des elements nécéssaires à la partie centrale du panneau central***************
		panelCenterCenter.add								(textFieldNom);
		panelCenterCenter.add								(vSPanelCenterCenter01);
		panelCenterCenter.add								(formattedTextFieldDate);
		panelCenterCenter.add								(vSPanelCenterCenter02);
		panelCenterCenter.add								(textFieldJeux);
		panelCenterCenter.add								(vSPanelCenterCenter03);
		panelCenterCenter.add								(textFieldLieu);
		panelCenterCenter.add								(vSPanelCenterCenter04);
		panelCenterCenter.add								(comboBoxTaille);
		panelCenterCenter.add								(vSPanelCenterCenter05);
		panelCenterCenter.add								(formattedTextFieldDateLimite);

		//4 - Ajout des elements nécéssaires à la partie inferieure du panneau central*************
		panelCenterSouth.add								(vSPanelCenterSouth01);

		//5 - Ajout des elements nécéssaires à la partie superieure du panneau sud*****************
		panelSouthNorth.add									(vSPanelSouthNorth01);

		//6 - Ajout des elements nécéssaires à la partie inferieure du panneau sud*****************
		panelSouthSouth.add									(btnAnnuler);
		panelSouthSouth.add									(hSPanelSouthSouth01);
		panelSouthSouth.add									(btnModifier);
		panelSouthSouth.add									(btnCreer);	
		panelSouthSouth.add									(btnDesinscription);
		panelSouthSouth.add									(btnInscription);
		panelSouthSouth.add									(btnDemarrer);
		panelSouthSouth.add									(btnSupprimer);
		panelSouthSouth.add									(hSPanelSouthSouth02);

		//7 - Ajout des elements nécéssaires au panneau nord***************************************
		panelNorth.add										(lblTitre);	
		panelNorth.add										(vSPanelNorth01);
		panelNorth.add										(lblInfo);
		panelNorth.add										(vSPanelNorth02);

		//8 - Ajout des elements nécéssaires au panneau central************************************
		panelCenter.add										(panelCenterNorth, BorderLayout.NORTH);	
		panelCenter.add										(panelCenterEast, BorderLayout.EAST);
		panelCenter.add										(panelCenterWest, BorderLayout.WEST);
		panelCenter.add										(panelCenterCenter, BorderLayout.CENTER);
		panelCenter.add										(panelCenterSouth, BorderLayout.SOUTH);

		//9 - Ajout des elements nécéssaires au panneau sud****************************************
		panelSouth.add										(panelSouthNorth, BorderLayout.NORTH);
		panelSouth.add										(panelSouthSouth, BorderLayout.SOUTH);

		//10 - Ajout des 5 panneaux au panneau principal (contentPane)*****************************
		contentPane.add										(panelNorth, BorderLayout.NORTH);
		contentPane.add										(panelEast, BorderLayout.EAST);
		contentPane.add										(panelWest, BorderLayout.WEST);
		contentPane.add										(panelCenter, BorderLayout.CENTER);	
		contentPane.add										(panelSouth, BorderLayout.SOUTH);

	}
		//FIN CONSTRUCTEUR
		//*************************************************************************************************************
		//*************************************************************************************************************	
	

	//	//METHODES
	//	//*************************************************************************************************************
	//	//*************************************************************************************************************	
	
	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}


	public void setBtnSupprimer(JButton btnSupprimer) {
		this.btnSupprimer = btnSupprimer;
	}



	public JTextField getTextFieldNom() {
		return textFieldNom;
	}


	public JTextField getTextFieldJeux() {
		return textFieldJeux;
	}


	public JTextField getTextFieldLieu() {
		return textFieldLieu;
	}


	public JButton getBtnAnnuler() {
		return btnAnnuler;
	}


	public JButton getBtnModifier() {
		return btnModifier;
	}


	public JButton getBtnCreer() {
		return btnCreer;
	}


	public JButton getBtnInscription() {
		return btnInscription;
	}


	public JButton getBtnDemarrer() {
		return btnDemarrer;
	}


	public JComboBox <String> getComboBoxTaille() {
		return comboBoxTaille;
	}


	public JFormattedTextField getFormattedTextFieldDate() {
		return formattedTextFieldDate;
	}


	public JFormattedTextField getFormattedTextFieldDateLimite() {
		return formattedTextFieldDateLimite;
	}


	public void setTextFieldNom(JTextField textFieldNom) {
		this.textFieldNom = textFieldNom;
	}


	public void setTextFieldJeux(JTextField textFieldJeux) {
		this.textFieldJeux = textFieldJeux;
	}


	public void setTextFieldLieu(JTextField textFieldLieu) {
		this.textFieldLieu = textFieldLieu;
	}


	public void setBtnAnnuler(JButton btnAnnuler) {
		this.btnAnnuler = btnAnnuler;
	}


	public void setBtnModifier(JButton btnModifier) {
		this.btnModifier = btnModifier;
	}


	public void setBtnCreer(JButton btnCreer) {
		this.btnCreer = btnCreer;
	}


	public void setBtnInscription(JButton btnInscription) {
		this.btnInscription = btnInscription;
	}


	public void setBtnDemarrer(JButton btnDemarrer) {
		this.btnDemarrer = btnDemarrer;
	}


	public void setComboBoxTaille(JComboBox <String> comboBoxTaille) {
		this.comboBoxTaille = comboBoxTaille;
	}


	public void setFormattedTextFieldDate(JFormattedTextField formattedTextFieldDate) {
		this.formattedTextFieldDate = formattedTextFieldDate;
	}


	public void setFormattedTextFieldDateLimite(
			JFormattedTextField formattedTextFieldDateLimite) {
		this.formattedTextFieldDateLimite = formattedTextFieldDateLimite;
	}


	public JLabel getLblTitre() {
		return lblTitre;
	}


	public JLabel getLblInfo() {
		return lblInfo;
	}


	public void setLblTitre(JLabel lblTitre) {
		this.lblTitre = lblTitre;
	}


	public void setLblInfo(JLabel lblInfo) {
		this.lblInfo = lblInfo;
	}


	public JButton getBtnDesinscription() {
		return btnDesinscription;
	}


	public void setBtnDesinscription(JButton btnDesinscription) {
		this.btnDesinscription = btnDesinscription;
	}


}
