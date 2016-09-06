package fr.aoufi.application;

import javax.swing.UIManager;

import fr.aoufi.vue.FrmConnexion;

/**
 *  * <b>Classe de point d'entrée de l'application</b>
 * <p>
 * <b>Note : </b> Utilisation du look and Feel System.
 * </p>
 *
 */

public class Application {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.
					getSystemLookAndFeelClassName());
		} catch(Exception e) {} 


		FrmConnexion enterPoint = new FrmConnexion();
		enterPoint.setVisible(true);

	}

}
