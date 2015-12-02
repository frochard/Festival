package festival.simulation;

import java.util.ArrayList;

public class Bus extends Thread {

	private int numBus;
	private int nbPlace;
	private Site siteDepart;
	private Site siteArrivee;
	private ArrayList<People> passagers;

	public Bus(int numBus, int seats, Site siteDepart, Site siteArrivee) {
		this.numBus = numBus;
		this.nbPlace=seats;
		this.siteDepart=siteDepart;
		this.siteArrivee=siteArrivee;
		this.passagers=new ArrayList<People>();
		System.out.println("Création du bus "+this.numBus);

	}

	public Bus(int nbPlace, int numBus) {
		this.nbPlace = nbPlace;
		this.numBus = numBus;
	}

	public int getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}

	public int getNumBus() {
		return numBus;
	}

	public void setNumbus(int numBus) {
		this.numBus = numBus;
	}

	
	public ArrayList<People> getPassagers() {
		return passagers;
	}

	public void setPassagers(ArrayList<People> passagers) {
		this.passagers = passagers;
	}
	


	public synchronized void descendreDuBus() throws InterruptedException{
		System.out.println("*************************");
		for(People p:passagers){
			System.out.println("passager "+p.getIdFestivalier()+" dans le bus "+this.getNumBus());
		}
		//Parcours des passagers du bus
		for(People p:passagers){
			//Changement d'état des passagers
			p.getEtats().add(new Etat("D", System.currentTimeMillis()));
			//Ajout du festivalier sur site d'arrivée
			this.siteArrivee.ajouterFestivalier(p);
			//Augmentation du nombre de places libres dans le bus
			this.nbPlace++;
		}
		System.out.println("*************************");
		//Suppresion du festivalier du bus
		this.passagers.clear();
		for(People p:this.siteArrivee.getFestivaliers()){
			System.out.println("Festivalier présent sur site arrivée"+p.getIdFestivalier());
		}
		System.out.println("*************************");
	}
	

	public void run(){
		while(true){
			//Attente de parking sur le site de dÃ©part
			this.siteDepart.ajouterBus(this);
			//Tempo pour laisser les gens monter
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Depart du bus
			this.siteDepart.retirerBus(this);
			//Trajet aller
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Descente des passagers
			System.out.println("Arrivée du bus "+this.numBus+" sur site d'arrivée.");
			try {
				this.descendreDuBus();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Trajet retour : Retour du bus vers le site de départ
			System.out.println("Départ du bus "+this.numBus+" du site d'arrivée.");
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}