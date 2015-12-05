package festival.simulation;

import java.util.ArrayList;

/**
 * Cette classe g�re les bus de la simulation. Elle impl�mente l�interface Thread.  
 * @author Sanaa Mairouch
 * @author Frederic Rochard
 */
public class Bus extends Thread {

	//Travel duration
	final static int dureeTrajet=500;
	//duration for taking or leaving people
	final static int dureeArret=100;

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
		System.out.println("Cr�ation du bus "+this.numBus);
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
	
	/**
	 * Cette classe fait descendre les festivaliers du bus et changent leur �tat
	 * @throws InterruptedException
	 */
	public void descendreDuBus() throws InterruptedException{
		//Parcours des passagers du bus
		for(People p:passagers){
			//Changement d'�tat des passagers
			p.getEtats().add(new Etat("D", System.currentTimeMillis()));
			//Ajout du festivalier sur site d'arriv�e
			this.siteArrivee.ajouterFestivalier(p);
			//Augmentation du nombre de places libres dans le bus
			this.nbPlace++;
		}
		//Remove people from the bus
		this.passagers.clear();
	}

	public void run(){
		//Les bus font des allers retours tout au long de la simulation => Condition toujours vraie
		while(true){
			//Attente de parking sur le site de départ
			this.siteDepart.ajouterBus(this);
			//Tempo pour laisser les gens monter
			try {
				sleep(dureeArret);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Depart du bus
			this.siteDepart.retirerBus(this);
			//Trajet aller
			try {
				sleep(dureeTrajet);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Descente des passagers
			System.out.println("Arriv�e du bus "+this.numBus+" sur site d'arriv�e.");
			try {
				this.descendreDuBus();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				sleep(dureeArret);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Trajet retour : Retour du bus vers le site de d�part
			System.out.println("D�part du bus "+this.numBus+" du site d'arriv�e.");
			try {
				sleep(dureeTrajet);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}