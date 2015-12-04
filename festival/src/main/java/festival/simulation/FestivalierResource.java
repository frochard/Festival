package festival.simulation;

import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import festival.restlet.Backend;
import festival.simulation.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 * Resource exposing a user
 *
 * @author Sanaa Mairouch
 * @author Frederic Rochard
 *
 */
public class FestivalierResource extends ServerResource
{

    /** Backend. */
    private Backend backend_;

    /** festivalier handled by this resource. */
    private People festivalier_;

    
    /* 
     * The method doInit is called prior to the others.
     */
    @Override
    protected void doInit() throws ResourceException 
    {
        // On récupère l'id passée dans l'URL
        // Note : a priori le cast ne passe pas en java6
        //int userId = (Integer) getRequest().getAttributes().get("userId");
        int festivalierId = Integer.valueOf((String) getRequest().getAttributes().get("festivalierId"));
        festivalier_ = backend_.getDatabase().getFestivalier(festivalierId);
        if (festivalier_ == null)
        {
            getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
        }
    }
    
    /**
     * Constructor.
     * Call for every single user request.
     */
    public FestivalierResource()
    {
        backend_ = (Backend) getApplication().getContext().getAttributes()
                .get("backend");
    }

    /**
     * Returns the user matching the id given in the URI
     * 
     * @return JSON representation of a user
     * @throws JSONException
     */
    @Get("json")
    public Representation getFestivalier() throws JSONException 
    {
    	// user_ is set by doInit

        JSONObject festivalierObject = toJson(festivalier_);
        festivalierObject.put("id", festivalier_.getId());
        festivalierObject.put("etat_url", getReference().toString() + festivalier_.getId() + "/stats");

        JsonRepresentation result = new JsonRepresentation(festivalierObject);
        result.setIndenting(true);
        return result;
    }
    
    JSONObject toJson(People festivalier) throws JSONException{
    	JSONObject festivalierObject = new JSONObject();
    	festivalierObject.put("name", festivalier.getName());
        festivalierObject.put("id", festivalier.getId());
        return festivalierObject;
    }

}
