package festival.simulation;

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
 * @author Sanaa MAIROUCH / Frederic ROCHARD
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
        router.attach("/people", FestivaliersResource.class);/*| GET : récupère la liste des festivaliers | POST      | ajoute un certain nombre de festivaliers*/
        router.attach("/people/", FestivaliersResource.class);/*| GET : récupère la liste des festivaliers | POST      | ajoute un certain nombre de festivaliers*/
        router.attach("/people/{people-id}", FestivalierResource.class);/*GET       | récupère les infos d'un festivalier*/
        router.attach("/people/{people-id}/", FestivalierResource.class);/*GET       | récupère les infos d'un festivalier*/
        router.attach("/people/{people-id}/stats", StatsResource.class);/*GET       | récupère les quatre instants de changement d'état d'un festivalier*/
        router.attach("/people/{people-id}/stats/", StatsResource.class);/*GET       | récupère les quatre instants de changement d'état d'un festivalier*/
        router.attach("/buses", BusesResource.class);/*POST : ajoute un certain nombre de bus*/
        router.attach("/buses/", BusesResource.class);/*POST : ajoute un certain nombre de bus*/

        return router;
    }
}
