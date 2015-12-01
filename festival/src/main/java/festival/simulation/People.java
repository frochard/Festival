package festival.simulation;


public class People extends Thread{

	private int idFestivalier;
	private String etat;
	private long dateEtat;
	private Billeterie billeterie;
	private Site siteDepart;
	private Site siteArrivee;

	public People(int idFestivalier,Billeterie billeterie, Site siteDepart, Site siteArrivee) {
		this.idFestivalier=idFestivalier;
		this.etat="A";
		this.dateEtat=System.currentTimeMillis();
		this.billeterie=billeterie;
		this.siteDepart=siteDepart;
		this.siteArrivee=siteArrivee;
		System.out.println("Création du festivalier "+this.idFestivalier);
	}

	public int getIdFestivalier() {
		return idFestivalier;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public long getDateEtat() {
		return dateEtat;
	}

	public void setDateEtat(long dateEtat) {
		this.dateEtat = dateEtat;
	}


	public void run(){
		//Test si billet disponible
		if(this.billeterie.achatPlace()){
			//Ajout du festivalier au site de départ
			this.siteDepart.ajouterFestivalier(this);
			System.out.println("Ajout du festivalier "+this.idFestivalier+" sur le site de départ");
			//Changement d'Ã©tat
			this.etat="B";
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