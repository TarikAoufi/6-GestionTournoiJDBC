package fr.aoufi.vue;

import javax.swing.JFrame;

/**
 * Classe decrivant le concept de frame de connexion
 * Elle contient les champs de saisie de login et mot de passe ainsi qu'un bouton connexion
 * 
 */

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	
	// m�morise si une fenetre est ouverte et 
	//      empeche l'ouverture de plusieurs fen�tres � partir du menu
	//
	// chaque constructeur initialise fenetreOuverte � true
	// lorsqu'on ferme une fenetre,fenetreOuverte = false
	
	private static boolean fenetreOuverte = false;

	public static boolean isFenetreOuverte() {
		return fenetreOuverte;
	}

	public static void setFenetreOuverte(boolean fenetreOuverte) {
		Window.fenetreOuverte = fenetreOuverte;
	} 


}
