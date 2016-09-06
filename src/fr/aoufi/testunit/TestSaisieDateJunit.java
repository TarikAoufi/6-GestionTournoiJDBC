package fr.aoufi.testunit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test de la saisie des dates dans le formulaire JUnit
 * Effectue les diff�rentes possibilit�s de saisie envisag�es
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
		// test de date saisie valable, superieure � aujourd'hui et dans la limite d'un an.
		String dateTest = "23/11/2014";

		assertEquals("le r�sultat est ", true, fr.aoufi.tech.Util.isValidDate(dateTest));	//OK
		//		assertEquals("le r�sultat est ", true, tech.Util.isValidDate(dateTest));	//KO

	}

	@Test
	public void testIsValidDateBefore() {
		// test de date saisie non valable car situ�e dans le pass�.
		String dateTest = "26/12/2011";

		assertEquals("le r�sultat est ", true, fr.aoufi.tech.Util.isValidDate(dateTest));	//OK
		//		assertEquals("le r�sultat est ", false, tech.Util.isValidDate(dateTest));	//KO

	}	

	@Test
	public void testIsValidDateAfter() {
		// test de date saisie non valable car situ�e au-dela de la limite de 1 an.
		String dateTest = "26/12/2016";

		assertEquals("le r�sultat est ", false, fr.aoufi.tech.Util.isValidDate(dateTest));	//OK
		//		assertEquals("le r�sultat est ", true, tech.Util.isValidDate(dateTest));	//KO

	}	

	@Test
	public void testIsValidDateBadFormat() {
		// test de date saisie non valable, mois et jours inexistants
		String dateTest = "99/99/9999";

		assertEquals("le r�sultat est ", false, fr.aoufi.tech.Util.isValidDate(dateTest));	//OK
		//		assertEquals("le r�sultat est ", true, tech.Util.isValidDate(dateTest));	//KO

	}	

	@Test
	public void testIsValidDateEmpty() {
		// test de date saisie non valable, car aucune saisie utilisateur
		String dateTest = "  /  /    ";

		assertEquals("le r�sultat est ", false, fr.aoufi.tech.Util.isValidDate(dateTest));	//OK
		//		assertEquals("le r�sultat est ", true, tech.Util.isValidDate(dateTest));	//KO

	}	

	@Test
	public void testIsValidDateLetters() {
		// test de date saisie non valable, car uniquement des lettres saisies.
		String dateTest = "ee/ee/eeee";

		assertEquals("le r�sultat est ", false, fr.aoufi.tech.Util.isValidDate(dateTest));	//OK
		//		assertEquals("le r�sultat est ", true, tech.Util.isValidDate(dateTest));	//KO

	}	


}
