package festival.simulation;

/**
 * Cette classe gère les états du festivalier : libellé état (A,B,C ou D) et date du changement d’état.
 * @author Sanaa Mairouch
 * @author Frederic Rochard
 */
public class Etat {

	private String libelleEtat;
	private long dateEtat;
	
	public Etat(String libelleEtat, long dateEtat) {
		this.libelleEtat = libelleEtat;
		this.dateEtat = dateEtat;
	}
	public Etat() {
		this.libelleEtat="A";
		this.dateEtat=System.currentTimeMillis();
	}
	public String getLibelleEtat() {
		return libelleEtat;
	}
	public void setLibelleEtat(String libelleEtat) {
		this.libelleEtat = libelleEtat;
	}
	public long getDateEtat() {
		return dateEtat;
	}
	public void setDateEtat(long dateEtat) {
		this.dateEtat = dateEtat;
	}
}
