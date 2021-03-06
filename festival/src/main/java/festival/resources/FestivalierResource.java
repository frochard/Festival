package festival.resources;

import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import festival.simulation.People;
import festival.simulation.Simulation;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

/**
 * Acc�s � la ressource Festivalier. R�cup�ration des infos d�un festivalier.
 * @author Sanaa Mairouch
 * @author Frederic Rochard
 */
public class FestivalierResource extends ServerResource{

    /** Backend. */
    private Simulation simulation_;

    /** festivalier handled by this resource. */
    private People festivalier_;

    
    /* 
     * The method doInit is called prior to the others.
     */
    @Override
    protected void doInit() throws ResourceException{
        // On récupère l'id passée dans l'URL
        // Note : a priori le cast ne passe pas en java6
        //int userId = (Integer) getRequest().getAttributes().get("userId");
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
    
    /**
     * Constructor.
     * Call for every single user request.
     */
    public FestivalierResource(){
    	simulation_ = (Simulation) getApplication().getContext().getAttributes().get("simulation");
    }

    /**
     * Returns people matching the id given in the URI
     * @return JSON representation of a people
     * @throws JSONException
     */
    @Get("json")
    public Representation getFestivalier() throws JSONException 
    {
    	// user_ is set by doInit
        JSONObject festivalierObject = toJson(festivalier_);
        festivalierObject.put("id", festivalier_.getIdFestivalier());
        festivalierObject.put("state", festivalier_.etatEnCours().getLibelleEtat());
        festivalierObject.put("url_stats", getReference().toString() + "/stats");
        JsonRepresentation result = new JsonRepresentation(festivalierObject);
        result.setIndenting(true);
        return result;
    }
    
    //get a JSON object of the people
    JSONObject toJson(People festivalier) throws JSONException{
    	JSONObject festivalierObject = new JSONObject();
        festivalierObject.put("id", festivalier_.getIdFestivalier());
        festivalierObject.put("state", festivalier_.etatEnCours().getLibelleEtat());
        festivalierObject.put("url_stats", getReference().toString() + "/stats");
        return festivalierObject;
    }
}