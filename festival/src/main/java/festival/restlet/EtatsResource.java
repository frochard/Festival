package festival.restlet;

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

import festival.simulation.*;

public class EtatsResource extends ServerResource {

	/** Backend. */
	private Backend backend_;

	/** User handled by this resource. */
	private People people_;

	/**
	 * Constructor. Call for every single user request.
	 */
	public EtatsResource() {
		super();
		backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
	}

	/*
	 * The method doInit is called prior to the others.
	 */
	@Override
	protected void doInit() throws ResourceException {
		// On récupère l'id passée dans l'URL
		// Note : a priori le cast ne passe pas en java6
		// int userId = (Integer) getRequest().getAttributes().get("userId");
		int userId = Integer.valueOf((String) getRequest().getAttributes().get("userId"));
		people_ = backend_.getDatabase().getFestivalier(userId);
		if (people_ == null) {
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
		}
	}

	@Get("html")
	public Representation getUsersHtml() {
		return new FileRepresentation("templates/list-etats.html", MediaType.TEXT_HTML);
	}

	/**
	 * Returns the list of the tweets of a user
	 *
	 * @return JSON representation of the tweets
	 * @throws JSONException
	 */
	@Get("json")
	public Representation getEtats() throws JSONException {
		List<Etat> etats = people_.getEtats();
		Collection<JSONObject> jsonEtats = new ArrayList<JSONObject>();

		for (Etat etat : etats) {
			JSONObject current = toJson(etat);
			jsonEtats.add(current);
		}
		JSONArray jsonArray = new JSONArray(jsonEtats);
		JsonRepresentation result = new JsonRepresentation(jsonArray);
		result.setIndenting(true);
		return result;
	}

	/**
	 * Create a tweet for the user whose id is userId
	 * 
	 * @param json
	 *            representation of the tweet to create
	 * @return JSON representation of the newly created tweet
	 * @throws JSONException
	 */
/*	@Post("json")
	public Representation createTweet(JsonRepresentation representation) throws Exception {
		JSONObject object = representation.getJsonObject();
		Etat etat = new Etat(object.getString("content"));
		people_.addEtat(etat);
		JSONObject jsonTweet = toJson(etat);
		JsonRepresentation result = new JsonRepresentation(jsonTweet);
		result.setIndenting(true);
		return result;
	}*/

	private JSONObject toJson(Etat etat) throws JSONException {
		JSONObject current = new JSONObject();
		current.put("content", etat.getLibelleEtat());//.getContent());
		return current;
	}
}
