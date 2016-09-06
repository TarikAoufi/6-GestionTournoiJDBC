package fr.aoufi.tech;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

/**
 * Classe contenant des methodes de conversion de date en différents format 
 * ainsi qu'une methode de test de validité isValidDate();
 *
 */

public class Util {

	// affichage message
	public static void afficheMessage(String strMessage)
	{
		System.out.println(strMessage);
	}
	// conversion Date to String
	public static String dateToString(Date dDate, String sFormat)  
	{
		// String sFormat = "dd/MM/yy hh:mm";
		// String sFormat = "dd/MM/yyyy";
		String sDate = "";
		SimpleDateFormat sdf;
		try {
			sdf = new SimpleDateFormat(sFormat);
			sDate = sdf.format(dDate);
		}
		catch (Exception e){
			sDate = "";
		}
		// System.out.println(sDate);
		return sDate;
	}
	//		 conversion String to Date
	public static Date stringToDate(String sDate, String sFormat)  
	{
		SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
		Date maDate;
		try {
			maDate = sdf.parse(sDate);
		} catch (ParseException e) {
			// le mois = mois - 1   <=>    0 => janvier
			maDate = new GregorianCalendar(1900,00,01).getTime();
		}
		return maDate;
	} 

/**
 * methode permettant la verification de la validité d'une date. 
 * Celle-ci doit etre au format correct, existante et ne pas se situer dans le temps au dela d'un an par rapport à la date du jour
 * @param verif
 * @return
 */
	public static boolean isValidDate(String verif) {

		boolean validation = true;
		String formatDate = "dd/MM/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(formatDate);
		Date verifDate = stringToDate(verif, formatDate);
		Date newDate = new Date();
		Date maxDate = new Date();	

		// Pour ajouter un jour en millisecondes, ceci necessite de diviser au
		// prealable par 1000 pour ne pas depasser la limite des Integer et
		// obtenir une valeur negative
		maxDate.setTime(1000 * (newDate.getTime()/1000 + 3600 * 24 * 365));

		boolean testDate = verifDate.before(maxDate);

		if (validation = testDate){
			try 
			{	
				format.setLenient(false);
				format.parse(verif);	

				return true;
			}
			catch(ParseException e){
				JOptionPane.showMessageDialog(null, "Format de date non valide\r\nMerci de bien vouloir saisir les champs date correctement.", "Battle non valide", JOptionPane.ERROR_MESSAGE);

				return false;
			}	
		}

		else return false;

	}



	@SuppressWarnings("unused")
	private static boolean testDate (int year, int month, int day){ 

		// attention : le mois = mois - 1
		// 11 => décembre
		//  0 => janvier
		GregorianCalendar calendar = new GregorianCalendar(); 
		calendar.set(year, month, day); 
		int goodYear = calendar.get(Calendar.YEAR); 
		int goodMonth = calendar.get(Calendar.MONTH); 
		int goodDay = calendar.get(Calendar.DAY_OF_MONTH); 
		//		System.out.println("***************************"); 
		//		System.out.println("Date entrée = " + year + month + day); 
		//		System.out.println("Date formatée = " + goodYear + goodMonth + goodDay); 

		if(goodYear==year & goodMonth==month & goodDay==day){ 
			//			System.out.println("Date valide"); 
			return true; 
		} 
		//		System.out.println("Date non valide"); 
		return false; 
	} 



}
