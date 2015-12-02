package festival.restlet;


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

import festival.simulation.People;

/**
 * Resource exposing the users
 *
 * @author ctedeschi
 * @author msimonin
 *
 */
public class FestivaliersResource extends ServerResource
{

    /** Backend. */
    private Backend backend_;

    /**
     * Constructor.
     * Call for every single people request.
     */
    public FestivaliersResource()
    {
        super();
        backend_ = (Backend) getApplication().getContext().getAttributes()
            .get("backend");
    }
    
    /**
     * Returns the list of all the people
     *
     * @return  JSON representation of the people
     * @throws JSONException
     */
    @Get("json")
    public Representation getUsers() throws JSONException
    {
        Collection<People> festivaliers = backend_.getDatabase().getFestivalier();
        Collection<JSONObject> jsonFestivaliers = new ArrayList<JSONObject>();

        for (People people : festivaliers)
        {
            JSONObject current = new JSONObject();
            current.put("id", people.getId());
            current.put("url", getReference().toString() + people.getId());
            current.put("tweet_url", getReference().toString() + people.getId() + "/tweets");
            jsonFestivaliers.add(current);

        }
        JSONArray jsonArray = new JSONArray(jsonFestivaliers);
        JsonRepresentation result = new JsonRepresentation(jsonArray);
        result.setIndenting(true);
        return result;
    }

    /**
     * Create a user with the data present in the json representation
     * 
     * @param json representation of the user to create
     * @return JSON representation of the newly created user
     * @throws JSONException
     */
    @Post("json")
    public Representation createUser(JsonRepresentation representation)
        throws Exception
    {
        JSONObject object = representation.getJsonObject();
        int id = object.getInt("id");

        // Save the user
        People people = backend_.getDatabase().createFestivalier(id);

        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("id", people.getId());
        JsonRepresentation result = new JsonRepresentation(resultObject);
        return result;
    }

}
