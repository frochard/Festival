package festival.simulation;

/**
 * Cette classe g�re la billetterie du fesival. 
 * @author Sanaa Mairouch
 * @author Frederic Rochard
 */
public class Billeterie {

	//Max number of tickets
	final static int nbBilletMax=2000;
	
	private int nbBilletDispo; 
	
	public Billeterie() {
		this.nbBilletDispo=nbBilletMax;
	}
	
	public synchronized boolean achatPlace(){
		//Test s'il y a des billets disponibles
		if(this.nbBilletDispo>0){
			//On décrémente le nombre de billets disponibles
			nbBilletDispo--;
			return true;
		}else{
			return false;
		}
	}
}
