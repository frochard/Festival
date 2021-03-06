package festival.simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe g�re les festivalier de la simulation. Elle impl�mente l�interface Thread.
 * @author Sanaa Mairouch
 * @author Frederic Rochard
 */
public class People extends Thread{

	private int idFestivalier;
	private List<Etat> etats;
	private Billeterie billeterie;
	private Site siteDepart;

	public People(int idFestivalier,Billeterie billeterie, Site siteDepart) {
		this.idFestivalier=idFestivalier;
		this.etats=new ArrayList<Etat>();
		this.etats.add(new Etat());
		this.billeterie=billeterie;
		this.siteDepart=siteDepart;
		System.out.println("Cr�ation du festivalier "+this.idFestivalier);
	}

	public People(int id) {
		this.idFestivalier=id;
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

	/**
	 * gets the current people state
	 * @return etatEnCours current people state
	 */
	public Etat etatEnCours(){
		Etat etatEnCours;
		etatEnCours=this.etats.get(this.etats.size() - 1);
		return etatEnCours;
	}

	public void run(){
		//Test si billet disponible
		if(this.billeterie.achatPlace()){
			//Ajout du festivalier au site de d�part
			this.siteDepart.ajouterFestivalier(this);
			System.out.println("Ajout du festivalier "+this.idFestivalier+" sur le site de d�part");
			//Changement d'etat
			this.etats.add(new Etat("B", System.currentTimeMillis()));
			//Un festivalier cherche à monter dans un bus non plein dès que possible.
			try {
				this.siteDepart.monterDansBus(this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}