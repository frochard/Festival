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
public class BusesResource extends ServerResource
{

    /** Backend. */
    private Simulation simulation_;

//    /** festivalier handled by this resource. */
  //  private People festivalier_;

    
    /**
     * Constructor.
     * Call for every single user request.
     */
    public BusesResource()
    {
        simulation_ = (Simulation) getApplication().getContext().getAttributes()
                .get("simulation");
    }


    /**
     * Create buses with the data present in the json representation
     * 
     * @param json representation of the user to create
     * @return JSON representation of the newly created user
     * @throws JSONException
     */
    @Post("json")
    public void createBuses(){
    	simulation_.addBuses(1, 2);
    }
}
