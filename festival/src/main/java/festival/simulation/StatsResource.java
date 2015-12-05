package festival.simulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class StatsResource extends ServerResource {

	/** Backend. */
	private Simulation simulation_;

    /** festivalier handled by this resource. */
    private People festivalier_;

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
		int nbA=0;
		int nbB=0;
		int nbC=0;
		int nbD=0;
		long tempsTotal=0;
		//Parcours des festivaliers
		for(People festivalier:simulation_.getFestivaliers()){
			//Test de l'état du festivalier
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
		//Calcul du temps moyen
		long tempsMoyen=0;
		if (nbD!=0){
			tempsMoyen=tempsTotal/nbD;
		}
		//Collection contenant les stats pour chaque état
		Collection<JSONObject> jsonStats = new ArrayList<JSONObject>();
		
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
		
		JSONObject statistic = new JSONObject();
		statistic.put("states", jsonStats);
		statistic.put("temps", tempsMoyen);
        JsonRepresentation result = new JsonRepresentation(statistic);
        result.setIndenting(true);
        return result;
	}
}