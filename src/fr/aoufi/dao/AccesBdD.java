package fr.aoufi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.aoufi.tech.Parametre;

/**
 * Classe de connexion à la base de donnée.
 * Table : tournoi
 * 
 *
 */

public class AccesBdD {

	private Connection conn;

	public AccesBdD() {


		try {
			Class.forName(Parametre.strNomDriver);
			conn = DriverManager.getConnection(Parametre.dbURL);

		} catch (ClassNotFoundException e) {
			System.out.println("=== AccesBdD : Probleme de config du driver ojdbc");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("=== AccesBdD : Probleme ouverture connection");
		}
	}


	//*************************************************************************************************************
	//*************************************************************************************************************
	public void executeUpdate(String sql) throws SQLException {
		Statement stmt = conn.createStatement();

		stmt.executeUpdate(sql);
	}


	//*************************************************************************************************************
	//*************************************************************************************************************
	public void executeUpdateCreate(String sql) throws SQLException {
		Statement stmt = conn.prepareStatement(sql);

		stmt.executeUpdate(sql);
	}

	//Prepare Statement suite à un choix de ligne utilisateur
	//*************************************************************************************************************
	//*************************************************************************************************************
	public Object executeQueryPrep(String sql, Integer indexLigne) throws SQLException {

		PreparedStatement stmt = conn.prepareStatement(sql); 

		stmt.setInt(1, indexLigne);
		stmt.executeUpdate();

		ResultSet rs = stmt.getResultSet();

		return rs;
	}


	//	insert into tournoi values (1001, 'le tournoi beau', 'dragon ball Z', 'six-fours-les-plages',to_date('12/12/2013','dd/mm/yyyy'),to_date('10/11/2013','dd/mm/yyyy'),1,1);
	//	("insert into livre (id_livre, titre ,id_editeur, date_edition, id_theme) values(?,?,?,?,?)");
	//	("update livre set titre = ?, id_editeur = ?, date_edition = to_date(?,'dd/mm/yyyy'), id_theme = ? where id_livre = ? "); 	


	//Prepare Statement suite à un choix de ligne utilisateur
	//*************************************************************************************************************
	//*************************************************************************************************************
	public Object executeQueryPrepCreate(String sql, String id, String nom, String jeux, String lieu, String date, String dateL, String taille, String finTournoi) throws SQLException {

		PreparedStatement stmt = conn.prepareStatement(sql); 

		stmt.setString(1, id);
		stmt.setString(2, nom);
		stmt.setString(3, jeux);
		stmt.setString(4, lieu);
		stmt.setString(5, date);
		stmt.setString(6, dateL);
		stmt.setString(7, taille);
		stmt.setString(8, finTournoi);

		stmt.executeUpdate();

		ResultSet rs = stmt.getResultSet();

		return rs;
	}



	//*************************************************************************************************************
	//*************************************************************************************************************
	public Object executeQuery(String sql) throws SQLException {
		Statement stmt = conn.createStatement();

		ResultSet rs =  stmt.executeQuery(sql);
		return rs;
	}

	public void close() {
		try {
			if (!conn.isClosed()) conn.close();
		} catch (SQLException e) {
			System.out.println("=== AccesBdD : Probleme close connection");
			e.printStackTrace();
		}
		System.out.println("deconnexion de la base de donnée");
	}

}