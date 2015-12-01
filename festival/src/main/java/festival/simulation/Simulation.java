package festival.simulation;

public class Simulation {

	/* Constantes (final indique que la valeur ne peut pas changer) */
	static final int maxClients = 100;

	private Site siteDepart;
    private Site siteArrivee;
    private Billeterie billeterie;

	public Simulation() {
	    this.billeterie=new Billeterie();
	    this.siteDepart=new Site();
	    this.siteArrivee=new Site();
    }

	public void addPeople(int people) {
		for(int i=1;i<=people;i++){
			new People(i,this.billeterie, this.siteDepart, this.siteArrivee).start();
		}
    }

	public void addBuses(int buses, int seats) {
		for(int i=1;i<=buses;i++){
			new Bus(i,seats, this.siteDepart, this.siteArrivee).start();
		}
    }
}
