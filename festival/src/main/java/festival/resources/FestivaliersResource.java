package festival.resources;


import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import festival.simulation.People;
import festival.simulation.Simulation;

/**
 * Accès à la ressource Festivaliers. Création d’un ensemble de festivaliers et récupération de la liste des festivaliers.
 * @author Sanaa Mairouch
 * @author Frederic Rochard
 */
public class FestivaliersResource extends ServerResource{

	//Number of people to create for the current simulation
	final static int nbFestivalier=2053;
	
    /** Backend. */
    private Simulation simulation_;

    /**
     * Constructor.
     * Call for every single people request.
     */
    public FestivaliersResource(){
        super();
        simulation_ = (Simulation) getApplication().getContext().getAttributes().get("simulation");
    }
    
    /**
     * Returns the list of all the people of the simulation
     *
     * @return  JSON representation of the people
     * @throws JSONException
     */
    @Get("json")
    public Representation getFestivaliers() throws JSONException{
    	//Collection of people
        Collection<People> festivaliers = simulation_.getFestivaliers();
        Collection<JSONObject> jsonFestivaliers = new ArrayList<JSONObject>();
        for (People people : festivaliers){
            JSONObject current = new JSONObject();
            current.put("id", people.getIdFestivalier());
            current.put("url", getReference().toString() + people.getIdFestivalier());
            current.put("stat_url", getReference().toString() + people.getIdFestivalier() + "/stats");
            jsonFestivaliers.add(current);
        }
        JSONArray jsonArray = new JSONArray(jsonFestivaliers);
        JsonRepresentation result = new JsonRepresentation(jsonArray);
        result.setIndenting(true);
        return result;
    }

    /**
     * Crée un certain nombre de festivalier
     */
    @Post("json")
    public void createUsers(){
    	//Ajout d'un certain nombre de festivalier
    	simulation_.addPeople(nbFestivalier);
    }
}