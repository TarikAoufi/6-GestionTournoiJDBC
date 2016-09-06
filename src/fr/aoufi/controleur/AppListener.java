package fr.aoufi.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.aoufi.dao.DaoTournoiBdD;
import fr.aoufi.metier.Tournoi;
import fr.aoufi.vue.FrmConnexion;
import fr.aoufi.vue.FrmEditionTournoi;
import fr.aoufi.vue.FrmListeTournois;
import fr.aoufi.vue.Window;

/**
 *  * <b>Controleur des Frames de l'application.</b>
 * <p>
 * controle les evenements des boutons des 3 frames :
 * <ul>
 * <li>FrmConnexion : controle sur le bouton de connexion et sur la cellule password lorsque le bouton "enter" est press�</li>
 * <li>FrmEditionTournoi : controle des boutons creer, modifier, supprimer, s'inscrire et d�marrer</li>
 * <li>FrmListeTournois : controle sur les boutons creer, modifier, supprimer, visualiser ainsi que sur la table des tournois</li>
 * </ul>
 * </p>
 * <p>
 * <b>Note : </b> impl�mente actionListener, KeyListener et ListSelectionListener donc contient obligatoirements les methodes de ces interfaces.
 * </p>
 * 
 *
 */

public class AppListener implements ActionListener, KeyListener, ListSelectionListener  {

	//	Variables
	//*************************************************************************************************************
	//*************************************************************************************************************
	private Window window;														//la Classe window etend JFrame
	public static int indexLigne;												//Permet aux diff�rentes classes du programme de connaitre la ligne de la table selectionn�e par l'utilisateur
	public static Integer select = null;										//Permet de mettre en memoire pour toutes les classes l'id du tournoi correspondant � la ligne selectionn�e
	private Tournoi tournoi;													//Objet tournoi que l'on instancie une fois la saisie utilisateur effectu�e

	//	constructeur prenant comme argument une JFrame (cas de plusieurs objets diff�rents � instancier)
	//*************************************************************************************************************
	//*************************************************************************************************************
	public AppListener(Window window){
		this.window = window;
	}

	/**
	 * redefinition de la methode actionPerformed = action lors de la pression d'un bouton 
	 * se situant sur une des pages de l'application
	 */
	//*************************************************************************************************************
	//*************************************************************************************************************
	@Override
	public void actionPerformed(ActionEvent e) {


		//I - CAS D'UNE ACTION SITUEE SUR LA PAGE DE CONNEXION
		//*************************************************************************************************************
		if (window instanceof FrmConnexion){								

			FrmConnexion frmconnexion = ((FrmConnexion)window);

			//1 - Si l'evenement est declench� par le bouton "CONNEXION"***************************

			if (e.getSource() == frmconnexion.getBtnConnexion()){

				verifConnexion();																						//permet de savoir si l'utilisateur est utilisateur, organisateur ou inconnu.
			}

		}

		//II - CAS D'UNE ACTION SITUEE SUR LA PAGE DE LISTAGE DES TOURNOIS
		//*************************************************************************************************************	
		else if (window instanceof FrmListeTournois){

			FrmListeTournois frmListeTournois = ((FrmListeTournois)window);

			//1 - Si l'evenement est declench� par le bouton "VISUALISER"**************************

			if (e.getSource() == frmListeTournois.getBtnVisualiser()) {

				FrmEditionTournoi frmEditionTournoi = new FrmEditionTournoi();
				frmEditionTournoi.setVisible(true);

				frmEditionTournoi.getLblTitre().setText(" Infos sur la Battle");
				frmEditionTournoi.getLblInfo().setText("   INSCRIVEZ-VOUS VITE ! ");

				recupSelect(frmEditionTournoi);																			//permet de recuperer dans la base l'id du tournoi � afficher et de le remplir dans la frame

				window.dispose();
			}

			//2 - Si l'evenement est declench� par le bouton "CREER UN NOUVEAU TOURNOI"************

			else if (e.getSource() == frmListeTournois.getBtnCreer()) {

				JOptionPane.showMessageDialog(null, "Creation d'une Battle :\r\n\r\nPour que votre Battle soit valid�e, respectez les indications ci-dessous\r\n\r\n-Tous les champs doivent etre renseign�s.\r\n-Le nom de la Battle doit etre unique\r\n-Choisissez bien le nom de la Battle : il n'est pas modifiable par la suite.\r\n-Les guillemets sont automatiquement remplac�s par des underscores.\r\n-La date d'organisation de la Battle ne peut exceder 1 an.\r\n-La date limite d'inscription doit etre fix�e avant la date de la Battle", "Creation de la Battle", JOptionPane.INFORMATION_MESSAGE);	

				FrmEditionTournoi frmEditionTournoi = new FrmEditionTournoi();
				frmEditionTournoi.setVisible(true);

				frmEditionTournoi.getBtnCreer().setVisible(true);														//definition de ce qui doit etre visible sur la frame et ce qui ne doit pas l'etre
				frmEditionTournoi.getBtnInscription().setVisible(false);	
				frmEditionTournoi.getTextFieldNom().setEnabled(true);
				frmEditionTournoi.getTextFieldJeux().setEnabled(true);
				frmEditionTournoi.getTextFieldLieu().setEnabled(true);
				frmEditionTournoi.getFormattedTextFieldDate().setEnabled(true);	
				frmEditionTournoi.getFormattedTextFieldDateLimite().setEnabled(true);
				frmEditionTournoi.getComboBoxTaille().setEnabled(true);

				window.dispose();

			}

			//3 - Si l'evenement est declench� par le bouton "SUPPRIMER"***************************

			else if (e.getSource() == frmListeTournois.getBtnSupprimer()){

				FrmEditionTournoi frmEditionTournoi = new FrmEditionTournoi();
				frmEditionTournoi.setVisible(true);

				frmEditionTournoi.getLblTitre().setText(" Suppression d'une Battle");
				frmEditionTournoi.getLblInfo().setText("   SOUHAITEZ-VOUS ANNULER CETTE BATTLE ? ");

				frmEditionTournoi.getBtnInscription().setVisible(false);												//definition de ce qui doit etre visible sur la frame et ce qui ne doit pas l'etre
				frmEditionTournoi.getBtnSupprimer().setVisible(true);

				recupSelect(frmEditionTournoi);																			//permet de recuperer dans la base l'id du tournoi � afficher et de le remplir dans la frame		

				window.dispose();

			}

			//4 - Si l'evenement est declench� par le bouton "MODIFIER"****************************

			else if (e.getSource() == frmListeTournois.getBtnModifier()) {

				JOptionPane.showMessageDialog(null, "Modification d'une Battle :\r\n\r\nPour que votre Battle soit valid�e, respectez les indications ci-dessous\r\n\r\n-Tous les champs doivent etre renseign�s.\r\n-Le nom de la Battle ne peut �tre modifi�\r\n-Les guillemets sont automatiquement remplac�s par des underscores.\r\n-La date d'organisation de la Battle ne peut exceder 1 an.\r\n-La date limite d'inscription doit etre fix�e avant la date de la Battle", "Modification de la Battle", JOptionPane.INFORMATION_MESSAGE);	

				FrmEditionTournoi frmEditionTournoi = new FrmEditionTournoi();
				frmEditionTournoi.setVisible(true);

				frmEditionTournoi.getBtnModifier().setVisible(true);													//definition de ce qui doit etre visible sur la frame et ce qui ne doit pas l'etre
				frmEditionTournoi.getBtnInscription().setVisible(false);

				frmEditionTournoi.getLblTitre().setText(" Modification de la Battle");
				frmEditionTournoi.getLblInfo().setText("   VEUILLEZ MODIFIER LES CHAMPS CI-DESSOUS ! ");

				frmEditionTournoi.getTextFieldJeux().setEnabled(true);													//le textField nom est volontairement laiss� desactiv� car le nom d'un tournoi ne doit pas etre modifiable
				frmEditionTournoi.getTextFieldLieu().setEnabled(true);
				frmEditionTournoi.getFormattedTextFieldDate().setEnabled(true);	
				frmEditionTournoi.getFormattedTextFieldDateLimite().setEnabled(true);
				frmEditionTournoi.getComboBoxTaille().setEnabled(true);

				recupSelect(frmEditionTournoi);																			//permet de recuperer dans la base l'id du tournoi � afficher et de le remplir dans la frame

				window.dispose();
			}
		}


		//II - CAS D'UNE ACTION SITUEE SUR LA PAGE D'EDITION / MODIFICATION / VISUALISATION / INSCRIPTION / SUPPRESSION
		//*************************************************************************************************************
		else if (window instanceof FrmEditionTournoi){

			FrmEditionTournoi frmeditionTournoi = ((FrmEditionTournoi)window);

			//1 - Si l'evenement est declench� par le bouton "VISUALISER"**************************

			if (e.getSource() == frmeditionTournoi.getBtnAnnuler()) {

				FrmListeTournois frmListeTournois = new FrmListeTournois();

				frmListeTournois.setVisible(true);																		//definition de ce qui doit etre visible sur la frame et ce qui ne doit pas l'etre
				frmListeTournois.getBtnCreer().setVisible(true);
				frmListeTournois.getBtnModifier().setVisible(true);
				frmListeTournois.getBtnSupprimer().setVisible(true);

				window.dispose();

			}

			//2 - Si l'evenement est declench� par le bouton "s'inscrire""*************************

			if (e.getSource() == frmeditionTournoi.getBtnInscription()) {

				frmeditionTournoi.getBtnInscription().setVisible(false);												//definition de ce qui doit etre visible sur la frame et ce qui ne doit pas l'etre
				frmeditionTournoi.getBtnDesinscription().setVisible(true);

				JOptionPane.showMessageDialog(null, "Merci de votre inscription � la Battle\r\n"+ frmeditionTournoi.getTextFieldNom().getText().toString()+ "", "Inscription � la Battle", JOptionPane.INFORMATION_MESSAGE);
				// TODO mettre en place la gestion des inscriptions sur le concept 

			}

			//3 - Si l'evenement est declench� par le bouton "Se D�sinscrire""*********************

			if (e.getSource() == frmeditionTournoi.getBtnDesinscription()) {

				frmeditionTournoi.getBtnInscription().setVisible(true);													//definition de ce qui doit etre visible sur la frame et ce qui ne doit pas l'etre
				frmeditionTournoi.getBtnDesinscription().setVisible(false);

				JOptionPane.showMessageDialog(null, "Vous etes, � pr�sent, desinscris\r\nde la Battle "+ frmeditionTournoi.getTextFieldNom().getText().toString()+ "", "D�sinscription de la Battle", JOptionPane.INFORMATION_MESSAGE);
				// TODO mettre en place la gestion des inscriptions sur le concept 


			}

			//4 - Si l'evenement est declench� par le bouton "CREATION"****************************

			if (e.getSource() == frmeditionTournoi.getBtnCreer()) {


				String sDateTournoi = frmeditionTournoi.getFormattedTextFieldDate().getText().toString();				//recup�ration des champs date sous forme de chaine
				String sDateInscrLTournoi = frmeditionTournoi.getFormattedTextFieldDateLimite().getText().toString();

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");												//instanciation de mon format de date

				Date dateTournoi = new Date();																			//creation de deux dates et initialisation au jour actuel
				Date dateInscrLTournoi = new Date();

				try {
					dateTournoi = sdf.parse(sDateTournoi);																//je parse la chaine date du tournoi au format cr�e precedemment et je l'affecte � la date instanci�e
				} catch (ParseException e1) {
					// TODO la date n'est pas parsable
					e1.printStackTrace();
				}

				try {
					dateInscrLTournoi = sdf.parse(sDateInscrLTournoi);													//je parse la chaine date limite d'inscription au format cr�e precedemment et je l'affecte � la date instanci�e
				} catch (ParseException e1) {
					// TODO la date n'est pas parsable
					e1.printStackTrace();
				}


				//a - La saisie utilisateur est valide
				if (DaoTournoiBdD.isNameExist(frmeditionTournoi.getTextFieldNom().getText())==false && frmeditionTournoi.getTextFieldNom().getText().trim().isEmpty() == false && frmeditionTournoi.getTextFieldLieu().getText().trim().isEmpty() == false && frmeditionTournoi.getTextFieldJeux().getText().trim().isEmpty() == false && fr.aoufi.tech.Util.isValidDate(frmeditionTournoi.getFormattedTextFieldDate().getText().toString()) == true &&  fr.aoufi.tech.Util.isValidDate(frmeditionTournoi.getFormattedTextFieldDateLimite().getText().toString()) == true && dateInscrLTournoi.before(dateTournoi) == true && new Date().before(dateInscrLTournoi)==true ){
					//si le nom du tournoi n'est pas deja pr�sent dans la table tournoi et que les champs nom, lieu et jeux ne sont pas vides, que les formats de date sont valides, que les dates souhait�es ne se situent pas avant la date du jour et que la date limite d'inscription se situe avant la date du tournoi

					boolean tailleTournoi = true;																		//permet de connaitre le choix de la taille du tournoi selectionn�e par la combobox
					switch (frmeditionTournoi.getComboBoxTaille().getSelectedItem().toString()) {

					case "Petit Tournoi (8 participants)" : tailleTournoi = false;
					break;

					case "Grand Tournoi (16 participants)" : tailleTournoi = true;
					break;

					}

					tournoi = new Tournoi(frmeditionTournoi.getTextFieldNom().getText().toString(), frmeditionTournoi.getTextFieldJeux().getText().toString(), frmeditionTournoi.getTextFieldLieu().getText().toString(), dateTournoi, dateInscrLTournoi, tailleTournoi);
					//instanciation de l'objet tournoi avec les parametres valides saisis. utilisation du constructeur qui genere automatiquement un id (donc qui ne le prend pas en parametre)

					//- pr�paration des elements � integrer dans la requete de cr�ation dans la base

					int taille = 0;																						//l'int taille est la correspondance entre le boolean tailleTournoi et la colonne taille_tournoi de la table TOURNOI

					if (tournoi.isTailleTournoi() == false) taille = 1;													//afin de faire la correspondance avec la base de donn�e taille 1 correspond au boolean false qui signifie petit tournoi
					if (tournoi.isTailleTournoi() == true) taille = 2;													//afin de faire la correspondance avec la base de donn�e taille 2 correspond au boolean true qui signifie petit tournoi



					String nomTournoi  = tournoi.getNomTournoi();														//suppression des " ' " de la saisie utilisateur afin de ne pas avoir de probleme de requete SQL 
					nomTournoi = nomTournoi.replaceAll("'","_");

					String nomJeu = tournoi.getJeuTournoi();
					nomJeu = nomJeu.replaceAll("'","_");

					String nomLieu = tournoi.getLieuTournoi();
					nomLieu = nomLieu.replaceAll("'","_");								//[^\\w]


					//- creation d'une chaine de caractere contenant la requete d'insertion en vue de la transmettre � la classe dao
					String addTournoi  = "insert into tournoi values ("+ tournoi.getIdTournoi() + ",'" + nomTournoi +"','" + nomJeu + "','" + nomLieu + "', to_date('"+ sDateTournoi + "','dd/mm/yyyy'), to_date('"+ sDateInscrLTournoi + "','dd/mm/yyyy')," + taille + ", 1)";
					DaoTournoiBdD.manipTournoi(addTournoi);

					FrmListeTournois frmListeTournois = new FrmListeTournois();
					frmListeTournois.setVisible(true);
					frmListeTournois.getBtnCreer().setVisible(true);
					frmListeTournois.getBtnModifier().setVisible(true);
					frmListeTournois.getBtnSupprimer().setVisible(true);

					window.dispose();
				}

				//b - La saisie utilisateur n'est pas valide
				else																									//si une des conditions d'enregistrement dans la base n'etait pas reuni, affichage � l'utilisateur du probleme rencontr�

				{
					if (DaoTournoiBdD.isNameExist(frmeditionTournoi.getTextFieldNom().getText())==true){
						JOptionPane.showMessageDialog(null, "Ce nom de Battle existe d�j�. Veuillez renseigner un autre nom de Battle.", "Battle non valide", JOptionPane.ERROR_MESSAGE);	
					}			
					if (frmeditionTournoi.getTextFieldNom().getText().trim().isEmpty() == true){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner le nom de la Battle.", "Battle non valide", JOptionPane.ERROR_MESSAGE);	
					}	
					if (frmeditionTournoi.getTextFieldLieu().getText().trim().isEmpty() == true){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner le lieu de la Battle.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (frmeditionTournoi.getTextFieldJeux().getText().trim().isEmpty() == true){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner le jeux de la Battle.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (fr.aoufi.tech.Util.isValidDate(frmeditionTournoi.getFormattedTextFieldDate().getText().toString()) == false){
						JOptionPane.showMessageDialog(null, "Veuillez saisir une date valide\r\nLa date ne peut etre superieure � un an.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (fr.aoufi.tech.Util.isValidDate(frmeditionTournoi.getFormattedTextFieldDateLimite().getText().toString()) == false ){
						JOptionPane.showMessageDialog(null, "Merci d'indiquer une date limite valide\r\nCelle-ci doit se situer avant la date de d�roulement de la battle.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (dateInscrLTournoi.before(dateTournoi) == false){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner une date limite d'inscription\r\ninferieure � la date de la battle.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (new Date().before(dateInscrLTournoi)==false){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner une date d'inscription limite dans le futur\r\nNous ne gerons pas encore les evenements se d�roulant dans le pass�.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (new Date().before(dateTournoi)==false){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner une battle se d�roulant dans le futur\r\nNous ne gerons pas encore les evenements se d�roulant dans le pass�.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
				}	
			}

			//5 - Si l'evenement est declench� par le bouton "MODIFIER"****************************

			if (e.getSource() == frmeditionTournoi.getBtnModifier()) {

				String sDateTournoi = frmeditionTournoi.getFormattedTextFieldDate().getText().toString();				//recup�ration des champs date sous forme de chaine
				String sDateInscrLTournoi = frmeditionTournoi.getFormattedTextFieldDateLimite().getText().toString();

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");												//instanciation de mon format de date

				Date dateTournoi = new Date();																			//creation de deux dates et initialisation au jour actuel
				Date dateInscrLTournoi = new Date();

				try {
					dateTournoi = sdf.parse(sDateTournoi);																//je parse la chaine date du tournoi au format cr�e precedemment et je l'affecte � la date instanci�e
				} catch (ParseException e1) {
					// TODO la date n'est pas parsable
					e1.printStackTrace();
				}

				try {
					dateInscrLTournoi = sdf.parse(sDateInscrLTournoi);													//je parse la chaine date limite d'inscription au format cr�e precedemment et je l'affecte � la date instanci�e
				} catch (ParseException e1) {
					// TODO la date n'est pas parsable
					e1.printStackTrace();
				}


				//a - La saisie utilisateur est valide
				if (frmeditionTournoi.getTextFieldLieu().getText().trim().isEmpty() == false && frmeditionTournoi.getTextFieldJeux().getText().trim().isEmpty() == false && fr.aoufi.tech.Util.isValidDate(frmeditionTournoi.getFormattedTextFieldDate().getText().toString()) == true &&  fr.aoufi.tech.Util.isValidDate(frmeditionTournoi.getFormattedTextFieldDateLimite().getText().toString()) == true && dateInscrLTournoi.before(dateTournoi) == true && new Date().before(dateInscrLTournoi)==true ){
					//si les champs nom, lieu et jeux ne sont pas vides, que les formats de date sont valides, que les dates souhait�es ne se situent pas avant la date du jour et que la date limite d'inscription se situe avant la date du tournoi

					boolean tailleTournoi = true;																		//permet de connaitre le choix de la taille du tournoi selectionn�e par la combobox
					switch (frmeditionTournoi.getComboBoxTaille().getSelectedItem().toString()) {

					case "Petit Tournoi (8 participants)" : tailleTournoi = false;
					break;

					case "Grand Tournoi (16 participants)" : tailleTournoi = true;
					break;

					}

					tournoi = new Tournoi(select, frmeditionTournoi.getTextFieldNom().getText().toString(), frmeditionTournoi.getTextFieldJeux().getText().toString(), frmeditionTournoi.getTextFieldLieu().getText().toString(), dateTournoi, dateInscrLTournoi, tailleTournoi);
					//instanciation de l'objet tournoi avec les parametres valides saisis en utilisant le constructeur acceptant en parametre l'id

					//- pr�paration des elements � integrer dans la requete de cr�ation dans la base

					int taille = 0;																						//l'int taille est la correspondance entre le boolean tailleTournoi et la colonne taille_tournoi de la table TOURNOI

					if (tournoi.isTailleTournoi() == false) taille = 1;													//afin de faire la correspondance avec la base de donn�e taille 1 correspond au boolean false qui signifie petit tournoi
					if (tournoi.isTailleTournoi() == true) taille = 2;													//afin de faire la correspondance avec la base de donn�e taille 2 correspond au boolean true qui signifie petit tournoi



					String nomTournoi  = tournoi.getNomTournoi();														//suppression des " ' " de la saisie utilisateur afin de ne pas avoir de probleme de requete SQL 
					nomTournoi = nomTournoi.replaceAll("'","_");

					String nomJeu = tournoi.getJeuTournoi();
					nomJeu = nomJeu.replaceAll("'","_");

					String nomLieu = tournoi.getLieuTournoi();
					nomLieu = nomLieu.replaceAll("'","_");								//[^\\w]


					//- creation d'une chaine de caractere contenant la requete d'update en vue de la transmettre � la classe dao
					String modifTournoi  = "update tournoi set nom_tournoi = '" + nomTournoi +"', nom_jeu = '" + nomJeu + "', lieu_Tournoi = '" + nomLieu + "', date_tournoi = to_date('"+ sDateTournoi + "','dd/mm/yyyy'), dateinscrl_tournoi = to_date('"+ sDateInscrLTournoi + "','dd/mm/yyyy'), taille_tournoi = " + taille + "  where id_tournoi = "+ select + "";
					DaoTournoiBdD.manipTournoi(modifTournoi);

					FrmListeTournois frmListeTournois = new FrmListeTournois();
					frmListeTournois.setVisible(true);
					frmListeTournois.getBtnCreer().setVisible(true);
					frmListeTournois.getBtnModifier().setVisible(true);
					frmListeTournois.getBtnSupprimer().setVisible(true);

					window.dispose();
				}

				//b - La saisie utilisateur n'est pas valide
				else {																									//si une des conditions d'enregistrement dans la base n'etait pas reuni, affichage � l'utilisateur du probleme rencontr�

					if (frmeditionTournoi.getTextFieldLieu().getText().trim().isEmpty() == true){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner le lieu de la Battle.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (frmeditionTournoi.getTextFieldJeux().getText().trim().isEmpty() == true){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner le jeux de la Battle.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (fr.aoufi.tech.Util.isValidDate(frmeditionTournoi.getFormattedTextFieldDate().getText().toString()) == false){
						JOptionPane.showMessageDialog(null, "Veuillez saisir une date valide\r\nLa date ne peut etre superieure � un an.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (fr.aoufi.tech.Util.isValidDate(frmeditionTournoi.getFormattedTextFieldDateLimite().getText().toString()) == false ){
						JOptionPane.showMessageDialog(null, "Merci d'indiquer une date limite valide\r\nCelle-ci doit se situer avant la date de d�roulement de la battle.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (dateInscrLTournoi.before(dateTournoi) == false){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner une date limite d'inscription\r\ninferieure � la date de la battle.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (new Date().before(dateInscrLTournoi)==false){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner une date d'inscription limite dans le futur\r\nNous ne gerons pas encore les evenements se d�roulant dans le pass�.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (new Date().before(dateTournoi)==false){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner une battle se d�roulant dans le futur\r\nNous ne gerons pas encore les evenements se d�roulant dans le pass�.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

			//7 - Si l'evenement est declench� par le bouton "SUPPRIMER"****************************

			if (e.getSource() == frmeditionTournoi.getBtnSupprimer()) {

				JOptionPane.showMessageDialog(null, "Action irreversible\r\nEtes-vous sur de vouloir effacer votre Battle?", "Suppression d'une Battle", JOptionPane.WARNING_MESSAGE);
				JOptionPane.showMessageDialog(null, "La Battle est supprim�e", "Suppression d'une Battle", JOptionPane.OK_CANCEL_OPTION);

				//- creation d'une chaine de caractere contenant la requete de delete en vue de la transmettre � la classe dao
				String suppTournoi  = "delete from tournoi where id_tournoi = "+ select + "";
				DaoTournoiBdD.manipTournoi(suppTournoi);

				FrmListeTournois frmListeTournois = new FrmListeTournois();
				frmListeTournois.setVisible(true);
				frmListeTournois.getBtnCreer().setVisible(true);
				frmListeTournois.getBtnModifier().setVisible(true);
				frmListeTournois.getBtnSupprimer().setVisible(true);

				window.dispose();

			}

			//6 - Si l'evenement est declench� par le bouton "DEMARRER"****************************

			if (e.getSource() == frmeditionTournoi.getBtnDemarrer()) {

				JOptionPane.showMessageDialog(null, "D�marrage de la Battle en cours", "Que la bataille commence !", JOptionPane.WARNING_MESSAGE);
				// TODO gerer la transmission d'un tournoi dont le jour de d�roulement est arriv�e
			}
		}

	}


	/**
	 * methode de r�cup�ration de la selection du tableau
	 */
	//*************************************************************************************************************
	//*************************************************************************************************************
	private void recupSelect(FrmEditionTournoi frmEditionTournoi) {

		int tailleTournoi = Integer.parseInt(DaoTournoiBdD.selectTournoi()[5]);

		switch (tailleTournoi) {

		case 2 : tailleTournoi = 1;
		break;

		case 1 : tailleTournoi = 0;
		break;
		}

		frmEditionTournoi.getTextFieldNom().setText(DaoTournoiBdD.selectTournoi()[0]);
		frmEditionTournoi.getTextFieldJeux().setText(DaoTournoiBdD.selectTournoi()[4]);
		frmEditionTournoi.getTextFieldLieu().setText(DaoTournoiBdD.selectTournoi()[3]);

		frmEditionTournoi.getComboBoxTaille().setSelectedIndex(tailleTournoi);

		frmEditionTournoi.getFormattedTextFieldDate().setText(DaoTournoiBdD.selectTournoi()[1]);
		frmEditionTournoi.getFormattedTextFieldDateLimite().setText(DaoTournoiBdD.selectTournoi()[2]);
	}

	/**
	 * Methode de verification de connexion
	 * s'appuyant sur le boolean renvoy� par la methode connexionL()
	 */
	//*************************************************************************************************************
	//*************************************************************************************************************
	private void verifConnexion() {
		if (!connexionL()) {
			System.out.println("acces refus�");
			JOptionPane.showMessageDialog(null, "Identifiant inconnu, veuillez v�rifier votre saisie");
		}
	}

	/**
	 * methode de v�rification du niveau de privil�ge de l'identifiant
	 * permet de savoir quel type d'utilisateur se connecte et d'instancier les JFrame correpondant au niveau de provilege
	 */
	//*************************************************************************************************************
	//*************************************************************************************************************
	private boolean connexionL() {

		boolean verifId = false;

		if (((FrmConnexion)window).getPasswordField().getText().equals("admin") && ((FrmConnexion)window).getTextIdentifiant().getText().equals("admin")){

			System.out.println("utilisateur super-user authentifi�");
			FrmListeTournois frmListeTournois = new FrmListeTournois();
			frmListeTournois.setVisible(true);
			frmListeTournois.getBtnCreer().setVisible(true);
			frmListeTournois.getBtnModifier().setVisible(true);
			frmListeTournois.getBtnSupprimer().setVisible(true);

			window.dispose();
			verifId = true;
		}

		if (((FrmConnexion)window).getPasswordField().getText().equals("user") && ((FrmConnexion)window).getTextIdentifiant().getText().equals("user")){

			System.out.println("utilisateur joueur authentifi�");
			FrmListeTournois frmListeTournois = new FrmListeTournois();
			frmListeTournois.setVisible(true);

			window.dispose();
			verifId = true;
		}


		return verifId;
	}


	//redefinition de la methode actionPerformed
	//*************************************************************************************************************
	//*************************************************************************************************************
	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("touche keyTyped" + e);

	}

	/**
	 * redefinition de la methode actionPerformed
	 * ecoute la pression de la touche entr�e afin d'executer diverses op�ration
	 */
	//*************************************************************************************************************
	//*************************************************************************************************************
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10){																		//correspondant au code de la touche entr�e

			//I - CAS D'UNE ACTION SITUEE SUR LA PAGE DE CONNEXION
			//*************************************************************************************************************
			if (window instanceof FrmConnexion){

				FrmConnexion frmConnexion = ((FrmConnexion)window);
				//System.out.println("touche keyPressed" + e);
				verifConnexion();
			}

		}

	}


	//redefinition de la methode actionPerformed
	//*************************************************************************************************************
	//*************************************************************************************************************
	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("touche KeyEvent" + e);

	}

	/**
	 * redefinition de la methode actionPerformed
	 */
	//*************************************************************************************************************
	//*************************************************************************************************************
	@Override
	public void valueChanged(ListSelectionEvent e) {


		//I - CAS D'UNE ACTION SITUEE SUR LA PAGE DE LISTAGE DES TOURNOIS
		//*************************************************************************************************************	
		if (window instanceof FrmListeTournois){
			FrmListeTournois frmListeTournois = ((FrmListeTournois)window);

			indexLigne = frmListeTournois.getTableTournoi().getSelectedRow()+1;															//permet de renseigner le static correspondant � la ligne selectionn�e

			select = Integer.parseInt(frmListeTournois.getTableTournoi().getModel().getValueAt(indexLigne -1 , 6).toString());			//permet de renseigner le static correspondant � l'id du tournoi selectionn�

			if (indexLigne != -1){																										//permet d'activer les boutons li�s � une selection d'une ligne du tableau
				frmListeTournois.getBtnModifier().setEnabled(true);
				frmListeTournois.getBtnSupprimer().setEnabled(true);
				frmListeTournois.getBtnVisualiser().setEnabled(true);
			}					
		}

	}

}


