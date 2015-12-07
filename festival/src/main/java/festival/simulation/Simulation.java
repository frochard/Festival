package festival.simulation;

import java.util.ArrayList;

/**
 * Cette classe g�re le nombre de bus et de festivaliers que nous voulons ajouter dans notre simulation
 * @author Sanaa Mairouch
 * @author Frederic Rochard
 */
public class Simulation {

	private Site siteDepart;
    private Site siteArrivee;
    private Billeterie billeterie;
    //Liste des festivaliers de la simulation
	private ArrayList<People> festivaliers;
    //Liste des bus de la simulation
	private ArrayList<Bus> buses;

	public Simulation() {
	    this.billeterie=new Billeterie();
	    this.siteDepart=new Site();
	    this.siteArrivee=new Site();
	    this.festivaliers=new ArrayList<People>();
	    this.buses=new ArrayList<Bus>();
    }

	public ArrayList<People> getFestivaliers() {
		return festivaliers;
	}

	public ArrayList<Bus> getBuses() {
		return buses;
	}

	/**
	 * Ajoute le nombre de festivaliers pass� en param�tre
	 * @param people nombre de festivaliers � ajouter
	 */
	public void addPeople(int people) {
		for(int i=1;i<=people;i++){
			People p=new People(i,this.billeterie, this.siteDepart);
			this.festivaliers.add(p);
			p.start();
		}
    }

	/**
	 * Ajoute le nombre de bus pass� en param�tre avec un nombre de places pass� en param�tre
	 * @param buses nombre de bus � ajouter
	 * @param seats nombre places dans le bus
	 */
	public void addBuses(int buses, int seats) {
		for(int i=1;i<=buses;i++){
			Bus b=new Bus(i,seats, this.siteDepart, this.siteArrivee);
			this.buses.add(b);
			b.start();
		}
    }
}
