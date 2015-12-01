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
		System.out.println("Cr�ation du festivalier "+this.idFestivalier);
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
			//Ajout du festivalier au site de d�part
			this.siteDepart.ajouterFestivalier(this);
			System.out.println("Ajout du festivalier "+this.idFestivalier+" sur le site de d�part");
			//Changement d'état
			this.etat="B";
			//Un festivalier cherche à monter dans un bus non plein dès que possible.
			try {
				this.siteDepart.monterDansBus(this);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}