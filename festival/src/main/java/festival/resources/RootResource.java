package festival.resources;

import org.restlet.data.MediaType;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Cette classe redirige vers la page web par défaut de notre simulation
 * @author Sanaa Mairouch
 * @author Frederic Rochard
 */
public class RootResource extends ServerResource {

	@Get
	public Representation getRoot() {
		return new FileRepresentation("templates/index.html", MediaType.TEXT_HTML); 
	}
	
}
