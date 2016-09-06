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
 * <li>FrmConnexion : controle sur le bouton de connexion et sur la cellule password lorsque le bouton "enter" est pressé</li>
 * <li>FrmEditionTournoi : controle des boutons creer, modifier, supprimer, s'inscrire et démarrer</li>
 * <li>FrmListeTournois : controle sur les boutons creer, modifier, supprimer, visualiser ainsi que sur la table des tournois</li>
 * </ul>
 * </p>
 * <p>
 * <b>Note : </b> implémente actionListener, KeyListener et ListSelectionListener donc contient obligatoirements les methodes de ces interfaces.
 * </p>
 * 
 *
 */

public class AppListener implements ActionListener, KeyListener, ListSelectionListener  {

	//	Variables
	//*************************************************************************************************************
	//*************************************************************************************************************
	private Window window;														//la Classe window etend JFrame
	public static int indexLigne;												//Permet aux différentes classes du programme de connaitre la ligne de la table selectionnée par l'utilisateur
	public static Integer select = null;										//Permet de mettre en memoire pour toutes les classes l'id du tournoi correspondant à la ligne selectionnée
	private Tournoi tournoi;													//Objet tournoi que l'on instancie une fois la saisie utilisateur effectuée

	//	constructeur prenant comme argument une JFrame (cas de plusieurs objets différents à instancier)
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

			//1 - Si l'evenement est declenché par le bouton "CONNEXION"***************************

			if (e.getSource() == frmconnexion.getBtnConnexion()){

				verifConnexion();																						//permet de savoir si l'utilisateur est utilisateur, organisateur ou inconnu.
			}

		}

		//II - CAS D'UNE ACTION SITUEE SUR LA PAGE DE LISTAGE DES TOURNOIS
		//*************************************************************************************************************	
		else if (window instanceof FrmListeTournois){

			FrmListeTournois frmListeTournois = ((FrmListeTournois)window);

			//1 - Si l'evenement est declenché par le bouton "VISUALISER"**************************

			if (e.getSource() == frmListeTournois.getBtnVisualiser()) {

				FrmEditionTournoi frmEditionTournoi = new FrmEditionTournoi();
				frmEditionTournoi.setVisible(true);

				frmEditionTournoi.getLblTitre().setText(" Infos sur la Battle");
				frmEditionTournoi.getLblInfo().setText("   INSCRIVEZ-VOUS VITE ! ");

				recupSelect(frmEditionTournoi);																			//permet de recuperer dans la base l'id du tournoi à afficher et de le remplir dans la frame

				window.dispose();
			}

			//2 - Si l'evenement est declenché par le bouton "CREER UN NOUVEAU TOURNOI"************

			else if (e.getSource() == frmListeTournois.getBtnCreer()) {

				JOptionPane.showMessageDialog(null, "Creation d'une Battle :\r\n\r\nPour que votre Battle soit validée, respectez les indications ci-dessous\r\n\r\n-Tous les champs doivent etre renseignés.\r\n-Le nom de la Battle doit etre unique\r\n-Choisissez bien le nom de la Battle : il n'est pas modifiable par la suite.\r\n-Les guillemets sont automatiquement remplacés par des underscores.\r\n-La date d'organisation de la Battle ne peut exceder 1 an.\r\n-La date limite d'inscription doit etre fixée avant la date de la Battle", "Creation de la Battle", JOptionPane.INFORMATION_MESSAGE);	

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

			//3 - Si l'evenement est declenché par le bouton "SUPPRIMER"***************************

			else if (e.getSource() == frmListeTournois.getBtnSupprimer()){

				FrmEditionTournoi frmEditionTournoi = new FrmEditionTournoi();
				frmEditionTournoi.setVisible(true);

				frmEditionTournoi.getLblTitre().setText(" Suppression d'une Battle");
				frmEditionTournoi.getLblInfo().setText("   SOUHAITEZ-VOUS ANNULER CETTE BATTLE ? ");

				frmEditionTournoi.getBtnInscription().setVisible(false);												//definition de ce qui doit etre visible sur la frame et ce qui ne doit pas l'etre
				frmEditionTournoi.getBtnSupprimer().setVisible(true);

				recupSelect(frmEditionTournoi);																			//permet de recuperer dans la base l'id du tournoi à afficher et de le remplir dans la frame		

				window.dispose();

			}

			//4 - Si l'evenement est declenché par le bouton "MODIFIER"****************************

			else if (e.getSource() == frmListeTournois.getBtnModifier()) {

				JOptionPane.showMessageDialog(null, "Modification d'une Battle :\r\n\r\nPour que votre Battle soit validée, respectez les indications ci-dessous\r\n\r\n-Tous les champs doivent etre renseignés.\r\n-Le nom de la Battle ne peut être modifié\r\n-Les guillemets sont automatiquement remplacés par des underscores.\r\n-La date d'organisation de la Battle ne peut exceder 1 an.\r\n-La date limite d'inscription doit etre fixée avant la date de la Battle", "Modification de la Battle", JOptionPane.INFORMATION_MESSAGE);	

				FrmEditionTournoi frmEditionTournoi = new FrmEditionTournoi();
				frmEditionTournoi.setVisible(true);

				frmEditionTournoi.getBtnModifier().setVisible(true);													//definition de ce qui doit etre visible sur la frame et ce qui ne doit pas l'etre
				frmEditionTournoi.getBtnInscription().setVisible(false);

				frmEditionTournoi.getLblTitre().setText(" Modification de la Battle");
				frmEditionTournoi.getLblInfo().setText("   VEUILLEZ MODIFIER LES CHAMPS CI-DESSOUS ! ");

				frmEditionTournoi.getTextFieldJeux().setEnabled(true);													//le textField nom est volontairement laissé desactivé car le nom d'un tournoi ne doit pas etre modifiable
				frmEditionTournoi.getTextFieldLieu().setEnabled(true);
				frmEditionTournoi.getFormattedTextFieldDate().setEnabled(true);	
				frmEditionTournoi.getFormattedTextFieldDateLimite().setEnabled(true);
				frmEditionTournoi.getComboBoxTaille().setEnabled(true);

				recupSelect(frmEditionTournoi);																			//permet de recuperer dans la base l'id du tournoi à afficher et de le remplir dans la frame

				window.dispose();
			}
		}


		//II - CAS D'UNE ACTION SITUEE SUR LA PAGE D'EDITION / MODIFICATION / VISUALISATION / INSCRIPTION / SUPPRESSION
		//*************************************************************************************************************
		else if (window instanceof FrmEditionTournoi){

			FrmEditionTournoi frmeditionTournoi = ((FrmEditionTournoi)window);

			//1 - Si l'evenement est declenché par le bouton "VISUALISER"**************************

			if (e.getSource() == frmeditionTournoi.getBtnAnnuler()) {

				FrmListeTournois frmListeTournois = new FrmListeTournois();

				frmListeTournois.setVisible(true);																		//definition de ce qui doit etre visible sur la frame et ce qui ne doit pas l'etre
				frmListeTournois.getBtnCreer().setVisible(true);
				frmListeTournois.getBtnModifier().setVisible(true);
				frmListeTournois.getBtnSupprimer().setVisible(true);

				window.dispose();

			}

			//2 - Si l'evenement est declenché par le bouton "s'inscrire""*************************

			if (e.getSource() == frmeditionTournoi.getBtnInscription()) {

				frmeditionTournoi.getBtnInscription().setVisible(false);												//definition de ce qui doit etre visible sur la frame et ce qui ne doit pas l'etre
				frmeditionTournoi.getBtnDesinscription().setVisible(true);

				JOptionPane.showMessageDialog(null, "Merci de votre inscription à la Battle\r\n"+ frmeditionTournoi.getTextFieldNom().getText().toString()+ "", "Inscription à la Battle", JOptionPane.INFORMATION_MESSAGE);
				// TODO mettre en place la gestion des inscriptions sur le concept 

			}

			//3 - Si l'evenement est declenché par le bouton "Se Désinscrire""*********************

			if (e.getSource() == frmeditionTournoi.getBtnDesinscription()) {

				frmeditionTournoi.getBtnInscription().setVisible(true);													//definition de ce qui doit etre visible sur la frame et ce qui ne doit pas l'etre
				frmeditionTournoi.getBtnDesinscription().setVisible(false);

				JOptionPane.showMessageDialog(null, "Vous etes, à présent, desinscris\r\nde la Battle "+ frmeditionTournoi.getTextFieldNom().getText().toString()+ "", "Désinscription de la Battle", JOptionPane.INFORMATION_MESSAGE);
				// TODO mettre en place la gestion des inscriptions sur le concept 


			}

			//4 - Si l'evenement est declenché par le bouton "CREATION"****************************

			if (e.getSource() == frmeditionTournoi.getBtnCreer()) {


				String sDateTournoi = frmeditionTournoi.getFormattedTextFieldDate().getText().toString();				//recupération des champs date sous forme de chaine
				String sDateInscrLTournoi = frmeditionTournoi.getFormattedTextFieldDateLimite().getText().toString();

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");												//instanciation de mon format de date

				Date dateTournoi = new Date();																			//creation de deux dates et initialisation au jour actuel
				Date dateInscrLTournoi = new Date();

				try {
					dateTournoi = sdf.parse(sDateTournoi);																//je parse la chaine date du tournoi au format crée precedemment et je l'affecte à la date instanciée
				} catch (ParseException e1) {
					// TODO la date n'est pas parsable
					e1.printStackTrace();
				}

				try {
					dateInscrLTournoi = sdf.parse(sDateInscrLTournoi);													//je parse la chaine date limite d'inscription au format crée precedemment et je l'affecte à la date instanciée
				} catch (ParseException e1) {
					// TODO la date n'est pas parsable
					e1.printStackTrace();
				}


				//a - La saisie utilisateur est valide
				if (DaoTournoiBdD.isNameExist(frmeditionTournoi.getTextFieldNom().getText())==false && frmeditionTournoi.getTextFieldNom().getText().trim().isEmpty() == false && frmeditionTournoi.getTextFieldLieu().getText().trim().isEmpty() == false && frmeditionTournoi.getTextFieldJeux().getText().trim().isEmpty() == false && fr.aoufi.tech.Util.isValidDate(frmeditionTournoi.getFormattedTextFieldDate().getText().toString()) == true &&  fr.aoufi.tech.Util.isValidDate(frmeditionTournoi.getFormattedTextFieldDateLimite().getText().toString()) == true && dateInscrLTournoi.before(dateTournoi) == true && new Date().before(dateInscrLTournoi)==true ){
					//si le nom du tournoi n'est pas deja présent dans la table tournoi et que les champs nom, lieu et jeux ne sont pas vides, que les formats de date sont valides, que les dates souhaitées ne se situent pas avant la date du jour et que la date limite d'inscription se situe avant la date du tournoi

					boolean tailleTournoi = true;																		//permet de connaitre le choix de la taille du tournoi selectionnée par la combobox
					switch (frmeditionTournoi.getComboBoxTaille().getSelectedItem().toString()) {

					case "Petit Tournoi (8 participants)" : tailleTournoi = false;
					break;

					case "Grand Tournoi (16 participants)" : tailleTournoi = true;
					break;

					}

					tournoi = new Tournoi(frmeditionTournoi.getTextFieldNom().getText().toString(), frmeditionTournoi.getTextFieldJeux().getText().toString(), frmeditionTournoi.getTextFieldLieu().getText().toString(), dateTournoi, dateInscrLTournoi, tailleTournoi);
					//instanciation de l'objet tournoi avec les parametres valides saisis. utilisation du constructeur qui genere automatiquement un id (donc qui ne le prend pas en parametre)

					//- préparation des elements à integrer dans la requete de création dans la base

					int taille = 0;																						//l'int taille est la correspondance entre le boolean tailleTournoi et la colonne taille_tournoi de la table TOURNOI

					if (tournoi.isTailleTournoi() == false) taille = 1;													//afin de faire la correspondance avec la base de donnée taille 1 correspond au boolean false qui signifie petit tournoi
					if (tournoi.isTailleTournoi() == true) taille = 2;													//afin de faire la correspondance avec la base de donnée taille 2 correspond au boolean true qui signifie petit tournoi



					String nomTournoi  = tournoi.getNomTournoi();														//suppression des " ' " de la saisie utilisateur afin de ne pas avoir de probleme de requete SQL 
					nomTournoi = nomTournoi.replaceAll("'","_");

					String nomJeu = tournoi.getJeuTournoi();
					nomJeu = nomJeu.replaceAll("'","_");

					String nomLieu = tournoi.getLieuTournoi();
					nomLieu = nomLieu.replaceAll("'","_");								//[^\\w]


					//- creation d'une chaine de caractere contenant la requete d'insertion en vue de la transmettre à la classe dao
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
				else																									//si une des conditions d'enregistrement dans la base n'etait pas reuni, affichage à l'utilisateur du probleme rencontré

				{
					if (DaoTournoiBdD.isNameExist(frmeditionTournoi.getTextFieldNom().getText())==true){
						JOptionPane.showMessageDialog(null, "Ce nom de Battle existe déjà. Veuillez renseigner un autre nom de Battle.", "Battle non valide", JOptionPane.ERROR_MESSAGE);	
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
						JOptionPane.showMessageDialog(null, "Veuillez saisir une date valide\r\nLa date ne peut etre superieure à un an.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (fr.aoufi.tech.Util.isValidDate(frmeditionTournoi.getFormattedTextFieldDateLimite().getText().toString()) == false ){
						JOptionPane.showMessageDialog(null, "Merci d'indiquer une date limite valide\r\nCelle-ci doit se situer avant la date de déroulement de la battle.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (dateInscrLTournoi.before(dateTournoi) == false){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner une date limite d'inscription\r\ninferieure à la date de la battle.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (new Date().before(dateInscrLTournoi)==false){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner une date d'inscription limite dans le futur\r\nNous ne gerons pas encore les evenements se déroulant dans le passé.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (new Date().before(dateTournoi)==false){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner une battle se déroulant dans le futur\r\nNous ne gerons pas encore les evenements se déroulant dans le passé.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
				}	
			}

			//5 - Si l'evenement est declenché par le bouton "MODIFIER"****************************

			if (e.getSource() == frmeditionTournoi.getBtnModifier()) {

				String sDateTournoi = frmeditionTournoi.getFormattedTextFieldDate().getText().toString();				//recupération des champs date sous forme de chaine
				String sDateInscrLTournoi = frmeditionTournoi.getFormattedTextFieldDateLimite().getText().toString();

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");												//instanciation de mon format de date

				Date dateTournoi = new Date();																			//creation de deux dates et initialisation au jour actuel
				Date dateInscrLTournoi = new Date();

				try {
					dateTournoi = sdf.parse(sDateTournoi);																//je parse la chaine date du tournoi au format crée precedemment et je l'affecte à la date instanciée
				} catch (ParseException e1) {
					// TODO la date n'est pas parsable
					e1.printStackTrace();
				}

				try {
					dateInscrLTournoi = sdf.parse(sDateInscrLTournoi);													//je parse la chaine date limite d'inscription au format crée precedemment et je l'affecte à la date instanciée
				} catch (ParseException e1) {
					// TODO la date n'est pas parsable
					e1.printStackTrace();
				}


				//a - La saisie utilisateur est valide
				if (frmeditionTournoi.getTextFieldLieu().getText().trim().isEmpty() == false && frmeditionTournoi.getTextFieldJeux().getText().trim().isEmpty() == false && fr.aoufi.tech.Util.isValidDate(frmeditionTournoi.getFormattedTextFieldDate().getText().toString()) == true &&  fr.aoufi.tech.Util.isValidDate(frmeditionTournoi.getFormattedTextFieldDateLimite().getText().toString()) == true && dateInscrLTournoi.before(dateTournoi) == true && new Date().before(dateInscrLTournoi)==true ){
					//si les champs nom, lieu et jeux ne sont pas vides, que les formats de date sont valides, que les dates souhaitées ne se situent pas avant la date du jour et que la date limite d'inscription se situe avant la date du tournoi

					boolean tailleTournoi = true;																		//permet de connaitre le choix de la taille du tournoi selectionnée par la combobox
					switch (frmeditionTournoi.getComboBoxTaille().getSelectedItem().toString()) {

					case "Petit Tournoi (8 participants)" : tailleTournoi = false;
					break;

					case "Grand Tournoi (16 participants)" : tailleTournoi = true;
					break;

					}

					tournoi = new Tournoi(select, frmeditionTournoi.getTextFieldNom().getText().toString(), frmeditionTournoi.getTextFieldJeux().getText().toString(), frmeditionTournoi.getTextFieldLieu().getText().toString(), dateTournoi, dateInscrLTournoi, tailleTournoi);
					//instanciation de l'objet tournoi avec les parametres valides saisis en utilisant le constructeur acceptant en parametre l'id

					//- préparation des elements à integrer dans la requete de création dans la base

					int taille = 0;																						//l'int taille est la correspondance entre le boolean tailleTournoi et la colonne taille_tournoi de la table TOURNOI

					if (tournoi.isTailleTournoi() == false) taille = 1;													//afin de faire la correspondance avec la base de donnée taille 1 correspond au boolean false qui signifie petit tournoi
					if (tournoi.isTailleTournoi() == true) taille = 2;													//afin de faire la correspondance avec la base de donnée taille 2 correspond au boolean true qui signifie petit tournoi



					String nomTournoi  = tournoi.getNomTournoi();														//suppression des " ' " de la saisie utilisateur afin de ne pas avoir de probleme de requete SQL 
					nomTournoi = nomTournoi.replaceAll("'","_");

					String nomJeu = tournoi.getJeuTournoi();
					nomJeu = nomJeu.replaceAll("'","_");

					String nomLieu = tournoi.getLieuTournoi();
					nomLieu = nomLieu.replaceAll("'","_");								//[^\\w]


					//- creation d'une chaine de caractere contenant la requete d'update en vue de la transmettre à la classe dao
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
				else {																									//si une des conditions d'enregistrement dans la base n'etait pas reuni, affichage à l'utilisateur du probleme rencontré

					if (frmeditionTournoi.getTextFieldLieu().getText().trim().isEmpty() == true){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner le lieu de la Battle.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (frmeditionTournoi.getTextFieldJeux().getText().trim().isEmpty() == true){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner le jeux de la Battle.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (fr.aoufi.tech.Util.isValidDate(frmeditionTournoi.getFormattedTextFieldDate().getText().toString()) == false){
						JOptionPane.showMessageDialog(null, "Veuillez saisir une date valide\r\nLa date ne peut etre superieure à un an.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (fr.aoufi.tech.Util.isValidDate(frmeditionTournoi.getFormattedTextFieldDateLimite().getText().toString()) == false ){
						JOptionPane.showMessageDialog(null, "Merci d'indiquer une date limite valide\r\nCelle-ci doit se situer avant la date de déroulement de la battle.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (dateInscrLTournoi.before(dateTournoi) == false){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner une date limite d'inscription\r\ninferieure à la date de la battle.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (new Date().before(dateInscrLTournoi)==false){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner une date d'inscription limite dans le futur\r\nNous ne gerons pas encore les evenements se déroulant dans le passé.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
					if (new Date().before(dateTournoi)==false){
						JOptionPane.showMessageDialog(null, "Veuillez renseigner une battle se déroulant dans le futur\r\nNous ne gerons pas encore les evenements se déroulant dans le passé.", "Battle non valide", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

			//7 - Si l'evenement est declenché par le bouton "SUPPRIMER"****************************

			if (e.getSource() == frmeditionTournoi.getBtnSupprimer()) {

				JOptionPane.showMessageDialog(null, "Action irreversible\r\nEtes-vous sur de vouloir effacer votre Battle?", "Suppression d'une Battle", JOptionPane.WARNING_MESSAGE);
				JOptionPane.showMessageDialog(null, "La Battle est supprimée", "Suppression d'une Battle", JOptionPane.OK_CANCEL_OPTION);

				//- creation d'une chaine de caractere contenant la requete de delete en vue de la transmettre à la classe dao
				String suppTournoi  = "delete from tournoi where id_tournoi = "+ select + "";
				DaoTournoiBdD.manipTournoi(suppTournoi);

				FrmListeTournois frmListeTournois = new FrmListeTournois();
				frmListeTournois.setVisible(true);
				frmListeTournois.getBtnCreer().setVisible(true);
				frmListeTournois.getBtnModifier().setVisible(true);
				frmListeTournois.getBtnSupprimer().setVisible(true);

				window.dispose();

			}

			//6 - Si l'evenement est declenché par le bouton "DEMARRER"****************************

			if (e.getSource() == frmeditionTournoi.getBtnDemarrer()) {

				JOptionPane.showMessageDialog(null, "Démarrage de la Battle en cours", "Que la bataille commence !", JOptionPane.WARNING_MESSAGE);
				// TODO gerer la transmission d'un tournoi dont le jour de déroulement est arrivée
			}
		}

	}


	/**
	 * methode de récupération de la selection du tableau
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
	 * s'appuyant sur le boolean renvoyé par la methode connexionL()
	 */
	//*************************************************************************************************************
	//*************************************************************************************************************
	private void verifConnexion() {
		if (!connexionL()) {
			System.out.println("acces refusé");
			JOptionPane.showMessageDialog(null, "Identifiant inconnu, veuillez vérifier votre saisie");
		}
	}

	/**
	 * methode de vérification du niveau de privilège de l'identifiant
	 * permet de savoir quel type d'utilisateur se connecte et d'instancier les JFrame correpondant au niveau de provilege
	 */
	//*************************************************************************************************************
	//*************************************************************************************************************
	private boolean connexionL() {

		boolean verifId = false;

		if (((FrmConnexion)window).getPasswordField().getText().equals("admin") && ((FrmConnexion)window).getTextIdentifiant().getText().equals("admin")){

			System.out.println("utilisateur super-user authentifié");
			FrmListeTournois frmListeTournois = new FrmListeTournois();
			frmListeTournois.setVisible(true);
			frmListeTournois.getBtnCreer().setVisible(true);
			frmListeTournois.getBtnModifier().setVisible(true);
			frmListeTournois.getBtnSupprimer().setVisible(true);

			window.dispose();
			verifId = true;
		}

		if (((FrmConnexion)window).getPasswordField().getText().equals("user") && ((FrmConnexion)window).getTextIdentifiant().getText().equals("user")){

			System.out.println("utilisateur joueur authentifié");
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
	 * ecoute la pression de la touche entrée afin d'executer diverses opération
	 */
	//*************************************************************************************************************
	//*************************************************************************************************************
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10){																		//correspondant au code de la touche entrée

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

			indexLigne = frmListeTournois.getTableTournoi().getSelectedRow()+1;															//permet de renseigner le static correspondant à la ligne selectionnée

			select = Integer.parseInt(frmListeTournois.getTableTournoi().getModel().getValueAt(indexLigne -1 , 6).toString());			//permet de renseigner le static correspondant à l'id du tournoi selectionné

			if (indexLigne != -1){																										//permet d'activer les boutons liés à une selection d'une ligne du tableau
				frmListeTournois.getBtnModifier().setEnabled(true);
				frmListeTournois.getBtnSupprimer().setEnabled(true);
				frmListeTournois.getBtnVisualiser().setEnabled(true);
			}					
		}

	}

}


