package festival.resources;

import org.restlet.resource.ServerResource;

import festival.simulation.Simulation;

import org.restlet.resource.Post;

/**
 * This class creates buses.
 * @author Sanaa Mairouch
 * @author Frederic Rochard
 */
public class BusesResource extends ServerResource{

	//Number of buses to create for the current simulation
	final static int nbBus = 6;
	//Capacity per bus
	final static int nbPlaces = 52;

	/** Backend. */
	private Simulation simulation_;

	/**
	 * Constructor.
	 * get the current simulation.
	 */
	public BusesResource()
	{
		simulation_ = (Simulation) getApplication().getContext().getAttributes()
				.get("simulation");
	}

	/**
	 * Create buses with the data present in the json representation
	 */
	@Post("json")
	public void createBuses(){
		simulation_.addBuses(nbBus, nbPlaces);
	}
}
