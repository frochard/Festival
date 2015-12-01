package festival.simulation;

import java.util.ArrayList;

public class Site {

	private ArrayList<Bus> buses;
	private ArrayList<People> festivaliers;

	public Site() {
		this.buses=new ArrayList<Bus>();
		this.festivaliers=new ArrayList<People>();
	}

	public synchronized ArrayList<People> getFestivaliers() {
		return festivaliers;
	}

	public ArrayList<Bus> getBuses() {
		return buses;
	}

	public void setBuses(ArrayList<Bus> buses) {
		this.buses = buses;
	}

	public void setFestivaliers(ArrayList<People> festivaliers) {
		this.festivaliers = festivaliers;
	}

	public synchronized void ajouterFestivalier(People p){
		//on ajoute le festivalier au site
		festivaliers.add(p);
	}

	public synchronized void retirerFestivalier(People p){
		festivaliers.remove(p);
	}

	public synchronized void ajouterBus(Bus b){
		//on ajoute le bus au site
		buses.add(b);
		//On notifie les festivaliers en attente
		notifyAll();
	}

	public synchronized void retirerBus(Bus b){
		buses.remove(b);
		System.out.println("Départ du bus "+b.getNumBus()+" du site de départ.");
	}

	public synchronized void monterDansBus(People p) throws InterruptedException{
		while(p.getEtat()!="C"){
			//On parcourt les bus du site de départ pour rechercher une place libre
			for (Bus b:this.buses){
				//Test si le festivalier est en attente de bus
				if(p.getEtat()=="B"){
					//Test s'il reste des places libres dans le bus
					if (b.getNbPlace()<=0){
						System.out.println("Il n'y a plus de place disponible dans le bus "+b.getNumBus()+". ");
					}else{
						//Retrait du festivalier sur site de depart
						this.retirerFestivalier(p);
						//On décrémente le nombre de places dispo
						int nbPlaceDispo=b.getNbPlace();
						nbPlaceDispo--;
						b.setNbPlace(nbPlaceDispo);
						//on ajoute le festivalier à la liste des passagers du bus
						b.getPassagers().add(p);
						System.out.println("Le festivalier "+p.getIdFestivalier()+" monte dans le bus "+b.getNumBus());
						//Changement d'état du festivalier
						p.setEtat("C");
					}
				}
			}
			if(p.getEtat()=="B"){
				//Mise en attente des festvaliers
				wait();
				System.out.println("festivalier "+p.getIdFestivalier()+" en attente d'un bus");
			}
		}
	}
}