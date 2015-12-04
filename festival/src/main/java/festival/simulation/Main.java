package festival.simulation;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.data.Reference;
import org.restlet.resource.Directory;

import festival.restlet.Backend;
public class Main {

	public static void main(String[] args) throws Exception {
		
		Simulation simulation = new Simulation();
		/*simulation.addPeople(14);
		simulation.addBuses(2,5);*/
		
		
       // Create a component
        Component component = new Component();
        Context context = component.getContext().createChildContext();
        component.getServers().add(Protocol.HTTP, 5000);
        component.getClients().add(Protocol.FILE);

        // Create an application
        Application application = new FestivalApplication(context);

        // Add the backend into component's context
        Backend backend = new Backend();
        context.getAttributes().put("backend", backend);
        component.getDefaultHost().attach(application);
       
        // Start the component
        component.start();
    }
}