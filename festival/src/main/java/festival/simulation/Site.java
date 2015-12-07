package festival.simulation;

import java.util.ArrayList;

/**
 * On gère dans cette classe les deux sites de la simulation : le site de départ et le site d'arrivée 
 * @author Sanaa Mairouch
 * @author Frederic Rochard
 */
public class Site {

	private ArrayList<Bus> buses;
	private ArrayList<People> festivaliers;

	public Site() {
		this.buses=new ArrayList<Bus>();
		this.festivaliers=new ArrayList<People>();
	}

	public  ArrayList<People> getFestivaliers() {
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

	/**
	 * on ajoute le festivalier au site
	 * On synchronise cette méthode pour protéger la variable contenant la liste des festivaliers du site
	 * @param p festivalier à ajouter sur le site
	 */
	public synchronized void ajouterFestivalier(People p){
		festivaliers.add(p);
	}

	/**
	 * on retire un festivalier du site
	 * On synchronise cette méthode pour protéger la variable contenant la liste des festivaliers du site
	 * @param p festivalier à retirer du site
	 */
	public synchronized void retirerFestivalier(People p){
		festivaliers.remove(p);
	}

	/**
	 * 	ajoute un bus sur le site. On synchronise cette méthode pour protéger la variable contenant la liste des busdu site
	 * @param b bus à ajouter sur le site
	 */
	public synchronized void ajouterBus(Bus b){
		//on ajoute le bus au site
		buses.add(b);
		//On notifie les festivaliers en attente
		notifyAll();
	}

	/**
	 * 	retire un bus du site. On synchronise cette méthode pour protéger la variable contenant la liste des busdu site
	 * @param b bus à retirer du site
	 */
	public synchronized void retirerBus(Bus b){
		buses.remove(b);
		System.out.println("Départ du bus "+b.getNumBus()+" du site de départ.");
	}

	/**
	 * ajoute un festivalier dans le bus et change son état
	 * On synchronise cette méthode pour protéger le nombre de place disponible dans le bus
	 * @param p festivalier à faire monter dans le bus
	 * @throws InterruptedException
	 */
	public synchronized void monterDansBus(People p) throws InterruptedException{
		while(p.etatEnCours().getLibelleEtat()=="B"){
			//On parcourt les bus du site de départ pour rechercher une place libre
			for (Bus b:this.buses){
				//Test si le festivalier est en attente de bus
				if(p.etatEnCours().getLibelleEtat()=="B"){
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
						p.getEtats().add(new Etat("C",System.currentTimeMillis()));
					}
				}
			}
			if(p.etatEnCours().getLibelleEtat()=="B"){
				//Mise en attente des festvaliers
				wait();
				System.out.println("festivalier "+p.getIdFestivalier()+" en attente d'un bus");
			}
		}
	}
}