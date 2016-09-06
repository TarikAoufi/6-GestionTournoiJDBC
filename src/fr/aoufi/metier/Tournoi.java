package fr.aoufi.metier;

import java.util.Date;

/**
 * Classe de description du concept TOURNOI
 * Elle contient deux constructeurs : 
 * 1 - Le premier lors de la creation d'un nouveau tournoi genere son propre ID unique grace à la methode calculIdTournoi()
 * 2 - le second est utilisé lors de l'instanciation d'un tournoi deja présent dans la BDD. Il utilise alors un id existant.
 *
 */

public class Tournoi {

	private int idTournoi;									//l'identifiant unique d'un tournoi
	private String nomTournoi;								//le nom unique d'un tournoi
	private String jeuTournoi;								//le jeux sur lequel le tournoi va porter
	private String lieuTournoi;								//le lieu geographique ou le tournoi doit se derouler
	private Date dateTournoi;								//date de deroulement du tournoi
	private Date dateInscrLTournoi;							//date de limite d'inscription au tournoi pour les joueurs
	private boolean tailleTournoi;							//taille du tournoi false = petit tournoi (8 personnes )/ true = grand tournoi (16 personnes)
	private boolean finTournoi = false;						//indicateur permettant de savoir si le tournoi est achevé


	/**
	 * constructeur generant automatiquement l'id du tournoi --> pour instancier un nouveau tournoi
	 * @param nomTournoi
	 * @param jeuTournoi
	 * @param lieuTournoi
	 * @param dateTournoi
	 * @param dateInscrLTournoi
	 * @param TailleTournoi
	 */
	public Tournoi (String nomTournoi, String jeuTournoi, String lieuTournoi, Date dateTournoi, Date dateInscrLTournoi, boolean TailleTournoi){

		idTournoi=calculIdTournoi();
		this.nomTournoi=nomTournoi;
		this.jeuTournoi=jeuTournoi;
		this.lieuTournoi=lieuTournoi;
		this.dateTournoi=dateTournoi;
		this.dateInscrLTournoi=dateInscrLTournoi;
		this.tailleTournoi=TailleTournoi;	

	}

	/**
	 * constructeur prenant en parametre l'id du tournoi --> pour instancier un tournoi deja crée (deja présent dans la base)
	 * @param idTournoi
	 * @param nomTournoi
	 * @param jeuTournoi
	 * @param lieuTournoi
	 * @param dateTournoi
	 * @param dateInscrLTournoi
	 * @param TailleTournoi
	 */
	public Tournoi (int idTournoi, String nomTournoi, String jeuTournoi, String lieuTournoi, Date dateTournoi, Date dateInscrLTournoi, boolean TailleTournoi){

		this.idTournoi=idTournoi;
		this.nomTournoi=nomTournoi;
		this.jeuTournoi=jeuTournoi;
		this.lieuTournoi=lieuTournoi;
		this.dateTournoi=dateTournoi;
		this.dateInscrLTournoi=dateInscrLTournoi;
		this.tailleTournoi=TailleTournoi;

	}

	/**
	 * methode de calcul automatique de l'id d'un tournoi
	 * @return idTournoi
	 */
	private int calculIdTournoi(){
		int idMax = fr.aoufi.dao.DaoTournoiBdD.comptaIdMax();
		int idTournoi = idMax+1;
		return idTournoi;
	}


	/**
	 * redefinition de la methode ToString
	 */
	@Override
	public String toString() {
		return "Tournoi [idTournoi=" + idTournoi + ", nomTournoi=" + nomTournoi
				+ ", jeuTournoi=" + jeuTournoi + ", lieuTournoi=" + lieuTournoi
				+ ", dateTournoi=" + dateTournoi + ", dateInscrLTournoi="
				+ dateInscrLTournoi + ", TailleTournoi=" + tailleTournoi
				+ ", finTournoi=" + finTournoi + "]";
	}


	public int getIdTournoi() {
		return idTournoi;
	}

	public String getNomTournoi() {
		return nomTournoi;
	}

	public String getJeuTournoi() {
		return jeuTournoi;
	}

	public String getLieuTournoi() {
		return lieuTournoi;
	}

	public Date getDateTournoi() {
		return dateTournoi;
	}

	public Date getDateInscrLTournoi() {
		return dateInscrLTournoi;
	}

	public boolean isTailleTournoi() {
		return tailleTournoi;
	}

	public boolean isFinTournoi() {
		return finTournoi;
	}

	public void setId_tournoi(int idTournoi) {
		this.idTournoi = idTournoi;
	}

	public void setNomTournoi(String nomTournoi) {
		this.nomTournoi = nomTournoi;
	}

	public void setJeuTournoi(String jeuTournoi) {
		this.jeuTournoi = jeuTournoi;
	}

	public void setLieuTournoi(String lieuTournoi) {
		this.lieuTournoi = lieuTournoi;
	}

	public void setDateTournoi(Date dateTournoi) {
		this.dateTournoi = dateTournoi;
	}

	public void setDateInscrLTournoi(Date dateInscrLTournoi) {
		this.dateInscrLTournoi = dateInscrLTournoi;
	}

	public void setTailleTournoi(boolean tailleTournoi) {
		tailleTournoi = tailleTournoi;
	}

	public void setFinTournoi(boolean finTournoi) {
		this.finTournoi = finTournoi;
	}

}
