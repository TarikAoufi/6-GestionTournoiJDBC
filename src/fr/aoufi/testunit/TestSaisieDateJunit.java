package fr.aoufi.testunit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test de la saisie des dates dans le formulaire JUnit
 * Effectue les différentes possibilités de saisie envisagées
 *
 */

public class TestSaisieDateJunit {


	@Before
	public void debut() throws Exception {		
		System.out.println("Execution de debut()");
	}

	@After
	public void fin() throws Exception {	
		System.out.println("Execution de fin()");
	}


	@Test
	public void testIsValidDate() {
		// test de date saisie valable, superieure à aujourd'hui et dans la limite d'un an.
		String dateTest = "23/11/2014";

		assertEquals("le résultat est ", true, fr.aoufi.tech.Util.isValidDate(dateTest));	//OK
		//		assertEquals("le résultat est ", true, tech.Util.isValidDate(dateTest));	//KO

	}

	@Test
	public void testIsValidDateBefore() {
		// test de date saisie non valable car située dans le passé.
		String dateTest = "26/12/2011";

		assertEquals("le résultat est ", true, fr.aoufi.tech.Util.isValidDate(dateTest));	//OK
		//		assertEquals("le résultat est ", false, tech.Util.isValidDate(dateTest));	//KO

	}	

	@Test
	public void testIsValidDateAfter() {
		// test de date saisie non valable car située au-dela de la limite de 1 an.
		String dateTest = "26/12/2016";

		assertEquals("le résultat est ", false, fr.aoufi.tech.Util.isValidDate(dateTest));	//OK
		//		assertEquals("le résultat est ", true, tech.Util.isValidDate(dateTest));	//KO

	}	

	@Test
	public void testIsValidDateBadFormat() {
		// test de date saisie non valable, mois et jours inexistants
		String dateTest = "99/99/9999";

		assertEquals("le résultat est ", false, fr.aoufi.tech.Util.isValidDate(dateTest));	//OK
		//		assertEquals("le résultat est ", true, tech.Util.isValidDate(dateTest));	//KO

	}	

	@Test
	public void testIsValidDateEmpty() {
		// test de date saisie non valable, car aucune saisie utilisateur
		String dateTest = "  /  /    ";

		assertEquals("le résultat est ", false, fr.aoufi.tech.Util.isValidDate(dateTest));	//OK
		//		assertEquals("le résultat est ", true, tech.Util.isValidDate(dateTest));	//KO

	}	

	@Test
	public void testIsValidDateLetters() {
		// test de date saisie non valable, car uniquement des lettres saisies.
		String dateTest = "ee/ee/eeee";

		assertEquals("le résultat est ", false, fr.aoufi.tech.Util.isValidDate(dateTest));	//OK
		//		assertEquals("le résultat est ", true, tech.Util.isValidDate(dateTest));	//KO

	}	


}
