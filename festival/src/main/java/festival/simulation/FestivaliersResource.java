package festival.simulation;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.FileRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import festival.restlet.Backend;

/**
 * Resource exposing the users
 *
 * @author 
 *
 */
public class FestivaliersResource extends ServerResource
{

    /** Backend. */
    private Simulation simulation_;

    /**
     * Constructor.
     * Call for every single people request.
     */
    public FestivaliersResource()
    {
        super();
        simulation_ = (Simulation) getApplication().getContext().getAttributes()
            .get("simulation");
    }

    
    /**
     * Returns the list of all the people
     *
     * @return  JSON representation of the people
     * @throws JSONException
     */
    @Get("json")
    public Representation getFestivaliers() throws JSONException
    {
        Collection<People> festivaliers = simulation_.getFestivaliers();
        System.out.println("--------------***********---------------");
    	System.out.println("Affichage des festivaliers de la simulation");
        for (People people : festivaliers){
        	System.out.println("festivalier "+people.getId());
        }
        System.out.println("--------------***********---------------");
        Collection<JSONObject> jsonFestivaliers = new ArrayList<JSONObject>();
        for (People people : festivaliers){
            JSONObject current = new JSONObject();
            current.put("id", people.getId());
            current.put("url", getReference().toString() + people.getId());
            jsonFestivaliers.add(current);
        }
        JSONArray jsonArray = new JSONArray(jsonFestivaliers);
        JsonRepresentation result = new JsonRepresentation(jsonArray);
        result.setIndenting(true);
        return result;
    }

    /**
     * Crée un certain nombre de festivalier
     * 
     * @param json representation of the user to create
     * @return JSON representation of the newly created user
     * @throws JSONException
     */
    @Post("json")
    public void createUsers(){
    	System.out.println("Crée un certain nombre de festivalier");
    	simulation_.addPeople(2);
    }

}
