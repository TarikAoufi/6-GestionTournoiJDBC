package fr.aoufi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import fr.aoufi.controleur.AppListener;
import fr.aoufi.vue.FrmListeTournois;

/**
 * Classe de persistance pour l'ensemble de l'application
 * Table : tournoi
 *
 */

//	id_Tournoi (Integer)						SMALLINT
//	nom_Tournoi (String)						VARCHAR2(50)
//	nom_Jeu (String)							VARCHAR2(50)
//	lieu_Tournoi (String)						VARCHAR2(50)
//	date_Tournoi (Date)							DATE
//	dateInscrL_Tournoi (Date)					DATE
//	taille_Tournoi (boolean | 8 ou 16)			VARCHAR2(1)
//	fin_Tournoi (boolean)						VARCHAR2(1)


//*************************************************************************************************************
//*************************************************************************************************************
public class DaoTournoiBdD {

	static Integer nombreLigne = 0;

	//*************************************************************************************************************
	//*************************************************************************************************************
	public static void comptaOccur(){

		AccesBdD connect    				= new AccesBdD();

		try {

			nombreLigne = 0;
			String reqCount 					= "select count (*) from TOURNOI";		

			ResultSet rs  						= null;

			rs = (ResultSet) connect.executeQuery(reqCount);

			while (rs.next()){

				nombreLigne 					= rs.getInt(1);

			}

		} catch (SQLException e) {
			System.out.println("erreur comptage");
			e.printStackTrace();
		}

		connect.close();
	}

	//Methode de remplissage du tableau 
	//*************************************************************************************************************
	//*************************************************************************************************************
	public static void remplirTableau(){

		comptaOccur();

		String reqSql 						= "select nom_tournoi, nom_jeu, lieu_tournoi, date_tournoi, dateinscrl_tournoi, taille_tournoi, id_tournoi from TOURNOI order by id_tournoi";		
		AccesBdD connect    				= new AccesBdD();
		ResultSet rs  						= null;
		FrmListeTournois.tabTab 			= new String[nombreLigne][];	

		try {

			rs = (ResultSet) connect.executeQuery(reqSql);					

			int i=0;
			String placesRest = null;

			while( rs.next() ){ 

				String sFormat = "dd/MM/yyyy";

				String sT = rs.getString ("nom_tournoi");
				Date dateT = rs.getDate("date_tournoi");
				String sA = fr.aoufi.tech.Util.dateToString(dateT, sFormat);
				Date dateLT = rs.getDate("dateinscrl_tournoi");
				String sE = fr.aoufi.tech.Util.dateToString(dateLT, sFormat);
				String sTh =rs.getString ("lieu_tournoi"); 
				String sD = rs.getString("nom_jeu"); 
				String sC = rs.getString ("taille_tournoi"); 
				String sI = rs.getString ("id_tournoi");

				switch (sC) {

				case "1" : placesRest = "8 personnes";
				break;

				case "2" : placesRest = "16 personnes";
				break;

				default : placesRest = "à determiner";

				}

				FrmListeTournois.tabTab[i] = new String [] {sT, sA, sE, sTh, sD, placesRest, sI};	
				i++;

			} 

		} catch (SQLException e) {
			System.out.println("erreur comptage");
			e.printStackTrace();
		}
		connect.close();
	}


	//methode de selection d'un tournoi par rapport à son ID
	//*************************************************************************************************************
	//*************************************************************************************************************
	public static String [] selectTournoi(){

		AccesBdD connect    				= new AccesBdD();
		String [] tabSelect 				= null;
		try {

			nombreLigne = 0;
			String reqSelect 					= "select nom_tournoi, nom_jeu, lieu_tournoi, date_tournoi, dateinscrl_tournoi, taille_tournoi from TOURNOI where id_tournoi = ?";		
			ResultSet rs  						= null;

			rs = (ResultSet) connect.executeQueryPrep(reqSelect, AppListener.select);

			while ( rs.next() ){

				String sFormat = "dd/MM/yyyy";

				String sT = rs.getString ("nom_tournoi");
				Date dateT = rs.getDate("date_tournoi");
				String sA = fr.aoufi.tech.Util.dateToString(dateT, sFormat);
				Date dateLT = rs.getDate("dateinscrl_tournoi");
				String sE = fr.aoufi.tech.Util.dateToString(dateLT, sFormat);
				String sTh =rs.getString ("lieu_tournoi"); 
				String sD = rs.getString("nom_jeu"); 
				String sC = rs.getString ("taille_tournoi"); 


				tabSelect = new String [] {sT, sA, sE, sTh, sD, sC};	

			}

		} catch (SQLException e) {
			System.out.println("erreur sql");
			e.printStackTrace();
		}

		connect.close();

		return tabSelect;
	}

	//methode de recherche d'un tournoi par rapport à son nom
	//*************************************************************************************************************
	//*************************************************************************************************************
	public static boolean isNameExist(String nomTournoi){


		AccesBdD connect    				= new AccesBdD();

		boolean isExist = false;
		String resultNom = null;

		try {

			String reqSearch 					= "select nom_tournoi from TOURNOI where nom_tournoi = '" + nomTournoi + "'";		
			ResultSet rs  						= null;

			rs = (ResultSet) connect.executeQuery(reqSearch);

			while (rs.next()){

				resultNom = rs.getString (1);	

			}

		} catch (SQLException e) {
			System.out.println("erreur sql");
			e.printStackTrace();

		}

		connect.close();

		if (nomTournoi.equals(resultNom)){
			isExist =  true;
		}

		return isExist;
	}

	//methode de selection de la plus grande valeur d'ID (pour connaitre le dernier numéro d'ID enregistré
	//*************************************************************************************************************
	//*************************************************************************************************************
	public static int comptaIdMax(){

		int idMax = 0;
		AccesBdD connect    				= new AccesBdD();

		try {

			String reqCount 					= "SELECT MAX(id_tournoi) FROM tournoi";		

			ResultSet rs  						= null;

			rs = (ResultSet) connect.executeQuery(reqCount);

			while (rs.next()){

				idMax	 						= rs.getInt(1);

				System.out.println				("l'id max est : " + idMax);

			}

		} catch (SQLException e) {
			System.out.println("erreur SQL");
			e.printStackTrace();
		}

		connect.close();

		return idMax;
	}

	//methode d'ajout d'un tournoi par rapport à son ID
	//*************************************************************************************************************
	//*************************************************************************************************************
	public static void manipTournoi(String insertSql){

		AccesBdD manip    				= new AccesBdD();

		try {

			manip.executeUpdate(insertSql);


		} catch (SQLException e) {
			System.out.println("erreur sql");
			e.printStackTrace();
		}

		manip.close();

	}


	//	insert into tournoi values (1001, 'le tournoi beau', 'dragon ball Z', 'six-fours-les-plages',to_date('12/12/2013','dd/mm/yyyy'),to_date('10/11/2013','dd/mm/yyyy'),1,1);
	//	("insert into livre (id_livre, titre ,id_editeur, date_edition, id_theme) values(?,?,?,?,?)");
	//	("update livre set titre = ?, id_editeur = ?, date_edition = to_date(?,'dd/mm/yyyy'), id_theme = ? where id_livre = ? "); 

	//	id_Tournoi (Integer)						SMALLINT
	//	nom_Tournoi (String)						VARCHAR2(50)
	//	nom_Jeu (String)							VARCHAR2(50)
	//	lieu_Tournoi (String)						VARCHAR2(50)
	//	date_Tournoi (Date)							DATE
	//	dateInscrL_Tournoi (Date)					DATE
	//	taille_Tournoi (boolean | 8 ou 16)			VARCHAR2(1)
	//	fin_Tournoi (boolean)						VARCHAR2(1)
}

