package festival.simulation;

import java.util.ArrayList;
import java.util.List;

public class People extends Thread{

	private int idFestivalier;
	private List<Etat> etats;
	private Billeterie billeterie;
	private Site siteDepart;
	private Site siteArrivee;

	public People(int idFestivalier,Billeterie billeterie, Site siteDepart, Site siteArrivee) {
		this.idFestivalier=idFestivalier;
		this.etats=new ArrayList<Etat>();
		this.etats.add(new Etat());
		this.billeterie=billeterie;
		this.siteDepart=siteDepart;
		this.siteArrivee=siteArrivee;
		System.out.println("Création du festivalier "+this.idFestivalier);
	}

	public People(int id) {
		this.idFestivalier=idFestivalier;
	}

	public int getIdFestivalier() {
		return idFestivalier;
	}

	
	public void setIdFestivalier(int idFestivalier) {
		this.idFestivalier = idFestivalier;
	}

	public List<Etat> getEtats() {
		return etats;
	}

	public void setEtats(List<Etat> etats) {
		this.etats = etats;
	}
	
	public Etat etatEnCours(){
		Etat etatEnCours;
		etatEnCours=this.etats.get(this.etats.size() - 1);
		return etatEnCours;
	}

	public void run(){
		//Test si billet disponible
		if(this.billeterie.achatPlace()){
			//Ajout du festivalier au site de départ
			this.siteDepart.ajouterFestivalier(this);
			System.out.println("Ajout du festivalier "+this.idFestivalier+" sur le site de départ");
			//Changement d'etat
			this.etats.add(new Etat("B", System.currentTimeMillis()));
			//Un festivalier cherche Ã  monter dans un bus non plein dÃ¨s que possible.
			try {
				this.siteDepart.monterDansBus(this);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}