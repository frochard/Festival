package festival.simulation;

import java.util.ArrayList;

public class Simulation {

	/* Constantes (final indique que la valeur ne peut pas changer) */
	static final int maxClients = 100;

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

	public void addPeople(int people) {
		for(int i=1;i<=people;i++){
			People p=new People(i,this.billeterie, this.siteDepart);
			this.festivaliers.add(p);
			p.start();
		}
    }

	public void addBuses(int buses, int seats) {
		for(int i=1;i<=buses;i++){
			Bus b=new Bus(i,seats, this.siteDepart, this.siteArrivee);
			this.buses.add(b);
			b.start();
		}
    }
}
