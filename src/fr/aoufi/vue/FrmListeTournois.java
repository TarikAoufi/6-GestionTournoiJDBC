package fr.aoufi.vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import fr.aoufi.controleur.AppListener;
import fr.aoufi.dao.DaoTournoiBdD;
import fr.aoufi.tech.ConstantesEcran;

/**
 * Classe decrivant le concept de frame de connexion
 * Elle contient les champs de saisie de login et mot de passe ainsi qu'un bouton connexion
 * 
 */

public class FrmListeTournois extends Window {

	/**
	 * Variables
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableTournoi;
	private JButton btnCreer;				
	private JButton btnModifier; 				
	private JButton btnSupprimer; 				
	private JButton btnVisualiser; 				
	static public String[][] tabTab ;

	/**
	 * Constructeur par defaut de FrmListeTournois
	 */
	public FrmListeTournois() {


		setResizable(false);
		setTitle("Battle Royal");
		setIconImage(ConstantesEcran.LOGO_FAVICON);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);


		//I - INSTANCIATION DES OBJETS DE LA JFRAME
		//*************************************************************************************************************
		//*************************************************************************************************************
		contentPane 						= new JPanel();
		setContentPane(contentPane);

		//1 - Instanciation des panneaux de la JFrame**********************************************
		JPanel panelNorth 					= new JPanel();						//Panneau Nord et ses panneaux
		JPanel panelNorthCenter 			= new JPanel();
		JPanel panelNorthSouth 				= new JPanel();
		JPanel panelNorthEast 				= new JPanel();

		JPanel panelCenter 					= new JPanel();						//Panneau Central

		JPanel panelWest 					= new JPanel();						//Panneau Ouest

		JPanel panelEast 					= new JPanel();						//Panneau Est

		JPanel panelSouth 					= new JPanel();						//Panneau Sud et ses panneaux
		JPanel panelSouthNorth				= new JPanel();
		JPanel panelSouthCenter 			= new JPanel();
		JPanel panelSouthEast				= new JPanel();

		FlowLayout fl_panelSouthCenter 		= (FlowLayout) panelSouthCenter.getLayout();

		//2 - Instanciation des labels*************************************************************
		JLabel lblTitle						= new JLabel(" Battles du moment");
		JLabel lblLstTournois 				= new JLabel("VOICI LA LISTE DES BATTLES ACTUELS, INSCRIVEZ-VOUS VITE!");
		String[] TitreTabTournois 			= new String[]{"Nom", "Date", "Date limite d'inscription", "Lieu", "Jeux", "Places restantes", "id_battle"};	

		//3 - Instanciation des champs de saisie et outils de selection de choix utilisateur*******
		tableTournoi 						= new JTable();

		JScrollPane scrollPaneTabTournoi 	= new JScrollPane(tableTournoi,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		//3 - Instanciation des boutons de la page*************************************************
		 btnCreer 							= new JButton("Creer un nouveau Tournoi");
		 btnModifier 						= new JButton("Modifier");
		 btnSupprimer 						= new JButton("Supprimer");
		 btnVisualiser 						= new JButton("Visualiser et s'inscrire");

		//5 - Creation des composants de séparation des elements des panneaux**********************
		Component vSPanelCenter01 			= Box.createVerticalStrut(10);		//Elements de séparation de panneau Central
		Component vSPanelCenter02 			= Box.createVerticalStrut(10);
		Component hSPanelCenter01 			= Box.createHorizontalStrut(10);	
		Component hSPanelCenter02 			= Box.createHorizontalStrut(10);

		Component vSPanelSouthNorth01 		= Box.createVerticalStrut(2);		//Elements de séparation du panneau Sud
		Component hSPanelSouthCenter01 		= Box.createHorizontalStrut(60);
		Component hSPanelSouthCenter02 		= Box.createHorizontalStrut(150);

		//6 - Instanciation du listener de la page*************************************************
		AppListener listener 				= new AppListener(this);
		
		
		//II - FORMATAGE DES PANNEAUX ET COMPOSANTS DE LA JFRAME
		//*************************************************************************************************************
		//*************************************************************************************************************
		//1 - Formatage des panneaux de la JFrame**************************************************
		contentPane.setBackground			(ConstantesEcran.COLOR_CELL);
		contentPane.setBorder				(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout				(new BorderLayout(0, 0));


		panelNorth.setBackground			(ConstantesEcran.COLOR_CELL);
		panelNorth.setLayout				(new BorderLayout(0, 0));

		panelNorthEast.setBackground		(ConstantesEcran.COLOR_CELL);

		panelNorthCenter.setBackground		(ConstantesEcran.COLOR_CELL);
		panelNorthCenter.setLayout			(new BorderLayout(0, 0));

		panelNorthSouth.setBackground		(ConstantesEcran.COLOR_CELL);


		panelCenter.setBorder				(ConstantesEcran.BORDER_CENTRALE);
		panelCenter.setBackground			(ConstantesEcran.COLOR_CELL_CENTRALE);
		panelCenter.setLayout				(new BorderLayout(0, 0));


		panelWest.setBackground				(ConstantesEcran.COLOR_CELL);


		panelEast.setBackground				(ConstantesEcran.COLOR_CELL);


		panelSouth.setBackground			(ConstantesEcran.COLOR_CELL);

		panelSouth.setLayout				(new BorderLayout(0, 0));

		panelSouthNorth.setBackground		(ConstantesEcran.COLOR_CELL);

		fl_panelSouthCenter.setAlignment	(FlowLayout.RIGHT);
		panelSouthCenter.setBackground		(ConstantesEcran.COLOR_CELL);
		panelSouthEast.setBackground		(ConstantesEcran.COLOR_CELL);

		//2 - Formatage des labels de la JFrame****************************************************
		lblTitle.setIcon					(ConstantesEcran.LOGO_MOYEN);
		lblTitle.setForeground				(ConstantesEcran.COLOR_TITRE1);
		lblTitle.setFont					(ConstantesEcran.FONT_TITRE1);
		lblTitle.setHorizontalAlignment		(SwingConstants.CENTER);


		lblLstTournois.setForeground		(ConstantesEcran.COLOR_TITRE2);
		lblLstTournois.setFont				(ConstantesEcran.FONT_TITRE2);
		lblLstTournois.setHorizontalAlignment(SwingConstants.CENTER);

		//3 - Formatage des champs de saisie et outils de selection de choix utilisateur***********	
		DaoTournoiBdD.remplirTableau();
		
		DefaultTableModel tabModel = new DefaultTableModel(tabTab,TitreTabTournois);
		
		tableTournoi.setModel(tabModel);																				
		tableTournoi.getColumnModel().getColumn(0).setPreferredWidth(60);		//definition de la taille preferée des colonnes de la Jtable
		tableTournoi.getColumnModel().getColumn(1).setPreferredWidth(5);
		tableTournoi.getColumnModel().getColumn(2).setPreferredWidth(5);
		tableTournoi.getColumnModel().getColumn(5).setPreferredWidth(30);
		tableTournoi.getColumnModel().getColumn(6).setPreferredWidth(5);	
		
		tableTournoi.getSelectionModel().addListSelectionListener(listener);	//Abonnement de la table au listener

		scrollPaneTabTournoi.setAutoscrolls	(true);
		scrollPaneTabTournoi.setBorder		(ConstantesEcran.BORDER_CENTRALE);

		//4 - Formatage des boutons de la JFrame***************************************************
		btnCreer.setFont					(ConstantesEcran.FONT_BOUTON);
		btnSupprimer.setFont				(ConstantesEcran.FONT_BOUTON);
		btnModifier.setFont					(ConstantesEcran.FONT_BOUTON);
		btnVisualiser.setFont				(ConstantesEcran.FONT_BOUTON);
		
		btnCreer.setBackground				(ConstantesEcran.COLOR_CELL);				
		btnSupprimer.setBackground			(ConstantesEcran.COLOR_CELL);				
		btnModifier.setBackground			(ConstantesEcran.COLOR_CELL);			
		btnVisualiser.setBackground			(ConstantesEcran.COLOR_CELL);			
		
		btnCreer.addActionListener(listener);									//Abonnement des 4 boutons de la frame au listener
		btnSupprimer.addActionListener(listener);
		btnModifier.addActionListener(listener);
		btnVisualiser.addActionListener(listener);
		
		btnCreer.setVisible(false);												//Parametrage de la visibilité des boutons par défaut à nulle (sauf visualiser commun à tous les utilisateurs)
		btnModifier.setVisible(false);
		btnSupprimer.setVisible(false);
		
		btnModifier.setEnabled(false);											//Paramétrage de l'activation des boutons neccesitant un choix à off.
		btnSupprimer.setEnabled(false);
		btnVisualiser.setEnabled(false);
		
		
		//III - AJOUT DES COMPOSANTS AUX PANNEAUX ET DES PANNEAUX AU CONTENTPANE DE LA JFRAME
		//*************************************************************************************************************
		//*************************************************************************************************************
		//1 - Ajout des elements nécéssaires à la partie centrale du panneau Central***************
		panelNorthCenter.add				(lblTitle, BorderLayout.NORTH);				
		panelNorthCenter.add				(lblLstTournois, BorderLayout.SOUTH);
		panelNorthCenter.add				(btnCreer, BorderLayout.EAST);

		//2 - Ajout des elements nécéssaires à la partie superieure du panneau Sud*****************
		panelSouthNorth.add					(vSPanelSouthNorth01);						

		//3 - Ajout des elements nécéssaires à la partie centrale du panneau Sud*******************
		panelSouthCenter.add				(btnSupprimer);								
		panelSouthCenter.add				(hSPanelSouthCenter01);		
		panelSouthCenter.add				(btnModifier);
		panelSouthCenter.add				(hSPanelSouthCenter02);
		panelSouthCenter.add				(btnVisualiser);

		//4 - Ajout des elements nécéssaires au panneau nord***************************************
		panelNorth.add						(panelNorthCenter, BorderLayout.CENTER);		
		panelNorth.add						(panelNorthEast, BorderLayout.EAST);
		panelNorth.add						(panelNorthSouth, BorderLayout.SOUTH);

		//5 - Ajout des elements nécéssaires au panneau Central************************************
		panelCenter.add						(scrollPaneTabTournoi);							
		panelCenter.add						(vSPanelCenter01, BorderLayout.NORTH);
		panelCenter.add						(vSPanelCenter02, BorderLayout.SOUTH);
		panelCenter.add						(hSPanelCenter01, BorderLayout.EAST);
		panelCenter.add						(hSPanelCenter02, BorderLayout.WEST);

		//6 - Ajout des elements nécéssaires au panneau Sud****************************************
		panelSouth.add						(panelSouthNorth, BorderLayout.NORTH);			
		panelSouth.add						(panelSouthEast, BorderLayout.EAST);
		panelSouth.add						(panelSouthCenter, BorderLayout.CENTER);

		//7 - Ajout des elements nécéssaires au panneau Principal**********************************
		contentPane.add						(panelNorth, BorderLayout.NORTH);				
		contentPane.add						(panelCenter, BorderLayout.CENTER);
		contentPane.add						(panelWest, BorderLayout.WEST);
		contentPane.add						(panelEast, BorderLayout.EAST);
		contentPane.add						(panelSouth, BorderLayout.SOUTH);

		
		//FIN CONSTRUCTEUR
		//*************************************************************************************************************
		//*************************************************************************************************************	
	}

	//IV - METHODES
	//*************************************************************************************************************
	//*************************************************************************************************************	
	public JTable getTableTournoi() {
		return tableTournoi;
	}


	public JButton getBtnCreer() {
		return btnCreer;
	}


	public JButton getBtnModifier() {
		return btnModifier;
	}


	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}


	public JButton getBtnVisualiser() {
		return btnVisualiser;
	}


	public void setTableTournoi(JTable tableTournoi) {
		this.tableTournoi = tableTournoi;
	}


	public void setBtnCreer(JButton btnCreer) {
		this.btnCreer = btnCreer;
	}


	public void setBtnModifier(JButton btnModifier) {
		this.btnModifier = btnModifier;
	}


	public void setBtnSupprimer(JButton btnSupprimer) {
		this.btnSupprimer = btnSupprimer;
	}


	public void setBtnVisualiser(JButton btnVisualiser) {
		this.btnVisualiser = btnVisualiser;
	}

	
	

	
}
