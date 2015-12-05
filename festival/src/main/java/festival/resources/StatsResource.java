package festival.resources;

import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import festival.simulation.People;
import festival.simulation.Simulation;

/**
 * This class gets the simulation statistics
 * @author Sanaa Mairouch
 * @author Frederic Rochard
 */
public class StatsResource extends ServerResource {

	/** Backend. */
	private Simulation simulation_;

	/**
	 * Constructor. Call for every single user request.
	 */
	public StatsResource() {
		super();
		simulation_ = (Simulation) getApplication().getContext().getAttributes().get("simulation");
	}

	/**
	 * Returns the list of the statistics 
	 *
	 * @return JSON representation of the statistics
	 * @throws JSONException
	 */
	@Get("json")
	public Representation getStats() throws JSONException {
		//These var contains the people number in each state
		int nbA=0;
		int nbB=0;
		int nbC=0;
		int nbD=0;
		long tempsTotal=0;
		//Get each people
		for(People festivalier:simulation_.getFestivaliers()){
			//Test the people state
			switch (festivalier.etatEnCours().getLibelleEtat()){
			  case "A":
			    nbA++;
			    break;        
			  case "B":
				  nbB++;
			    break;        
			  case "C":
				  nbC++;
			    break;        
			  case "D":
				  nbD++;
				  tempsTotal+=festivalier.etatEnCours().getDateEtat()-festivalier.getEtats().get(0).getDateEtat();
			    break;        
			  default:
			    /*Action*/;             
			}
		}
		//Average time for getting at the concert area
		long tempsMoyen=0;
		if (nbD!=0){
			tempsMoyen=tempsTotal/nbD;
		}
		//Collection of statistics for each state (JSON)
		Collection<JSONObject> jsonStats = new ArrayList<JSONObject>();
		//Each state is added to the collection 
		JSONObject current = new JSONObject();
		current.put("state", "A");
		current.put("nb", nbA);
		jsonStats.add(current);
		current = new JSONObject();
		current.put("state", "B");
		current.put("nb", nbB);
		jsonStats.add(current);
		current = new JSONObject();
		current.put("state", "C");
		current.put("nb", nbC);
		jsonStats.add(current);
		current = new JSONObject();
		current.put("state", "D");
		current.put("nb", nbD);
		jsonStats.add(current);
		//Creation of the JSON Object Statistic
		JSONObject statistic = new JSONObject();
		statistic.put("states", jsonStats);
		statistic.put("temps", tempsMoyen);
        JsonRepresentation result = new JsonRepresentation(statistic);
        result.setIndenting(true);
        return result;
	}
}