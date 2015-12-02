package festival.restlet;




import java.io.File;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.data.Reference;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

/**
 *
 * Application.
 *
 * @author msimonin
 *
 */
public class FestivalApplication extends Application
{

    public FestivalApplication(Context context)
    {
        super(context);
    }

    @Override
    public Restlet createInboundRoot()
    {
    	File staticDirectory = new File("static/");
    	Directory directory = new Directory(getContext(), "file:///" + staticDirectory.getAbsolutePath() + "/");
    	directory.isDeeplyAccessible();
    	directory.isListingAllowed();
    	    	
        Router router = new Router(getContext());
        router.attach("/", RootResource.class);
        router.attach("/static", directory);
        router.attach("/users", FestivaliersResource.class);
        router.attach("/users/", FestivaliersResource.class);
        router.attach("/users/{userId}", FestivalierResource.class);
        router.attach("/users/{userId}/", FestivalierResource.class);
        router.attach("/users/{userId}/tweets", EtatsResource.class);
        router.attach("/users/{userId}/tweets/", EtatsResource.class);
        return router;
    }
}
