package festival.main;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.data.Protocol;

import festival.application.FestivalApplication;
import festival.simulation.Simulation;

public class Main {

	public static void main(String[] args) throws Exception {

       // Create a component
        Component component = new Component();
        Context context = component.getContext().createChildContext();
        component.getServers().add(Protocol.HTTP, 5000);
        component.getClients().add(Protocol.FILE);
        // Create an application
        Application application = new FestivalApplication(context);
        // Add the backend into component's context
        Simulation simulation = new Simulation();
        context.getAttributes().put("simulation", simulation);
        component.getDefaultHost().attach(application);
        // Start the component
        component.start();
    }
}