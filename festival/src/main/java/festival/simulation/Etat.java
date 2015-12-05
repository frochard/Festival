package festival.simulation;

/**
 * Cette classe g�re les �tats du festivalier : libell� �tat (A,B,C ou D) et date du changement d��tat.
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
