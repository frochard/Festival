package festival.simulation;

public class Billeterie {

	static int nbBilletMax=500;
	
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
