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

	/*
	 * The method doInit is called prior to the others.
	 */
	@Override
	protected void doInit() throws ResourceException {
		// On r√©cup√®re l'id pass√©e dans l'URL
		// Note : a priori le cast ne passe pas en java6
		// int userId = (Integer) getRequest().getAttributes().get("userId");
        int festivalierId = Integer.valueOf((String) getRequest().getAttributes().get("people-id"));
        int i=0;
        while(festivalier_ == null & i < this.simulation_.getFestivaliers().size()){
            if(simulation_.getFestivaliers().get(i).getIdFestivalier()==festivalierId){
            	festivalier_ = simulation_.getFestivaliers().get(i);
            }
            i++;
        }               
        if (festivalier_ == null)
        {
            getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
        }
	}

	@Get("html")
	public Representation getUsersHtml() {
		return new FileRepresentation("templates/list-stats.html", MediaType.TEXT_HTML);
	}

	/**
	 * Returns the list of the stats of a user
	 *
	 * @return JSON representation of the stats
	 * @throws JSONException
	 */
	@Get("json")
	public Representation getStats() throws JSONException {
		List<Etat> etats = festivalier_.getEtats();
		Collection<JSONObject> jsonStats = new ArrayList<JSONObject>();
		for (Etat etat : etats) {
			System.out.println("----------------");
			System.out.println("Etat ‡ afficher pour le festivalier " + festivalier_.getIdFestivalier());
			System.out.println("Ètat : " + etat.getLibelleEtat());
			System.out.println("date Ètat : " + etat.getDateEtat());
			System.out.println("----------------");
			JSONObject current = toJson(etat);
			jsonStats.add(current);
		}
		JSONArray jsonArray = new JSONArray(jsonStats);
		JsonRepresentation result = new JsonRepresentation(jsonArray);
		result.setIndenting(true);
		return result;
	}

	private JSONObject toJson(Etat etat) throws JSONException {
		JSONObject current = new JSONObject();
		current.put("libelle_etat", etat.getLibelleEtat());
		current.put("date_etat", etat.getDateEtat());
		return current;
	}
}