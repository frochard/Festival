package festival.restlet;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import festival.simulation.*;

/**
 *
 * In-memory database 
 *
 * @author ctedeschi
 * @author msimonin
 *
 */
public class InMemoryDatabase implements Database
{

    /** User count (next id to give).*/
    private int festivalierCount_;

    /** User Hashmap. */
    Map<Integer, People> festivaliers_;


    public InMemoryDatabase()
    {
    	festivaliers_ = new HashMap<Integer, People>();
    }

    /**
     *
     * Synchronized user creation.
     * @param name
     * @param age
     *
     * @return the user created
     * @throws InterruptedException 
     */
    @Override
    public synchronized People createFestivalier(int id) throws InterruptedException
    {
    	People festivalier = new People(id);
    	festivalier.setIdFestivalier(festivalierCount_);
    	festivaliers_.put(festivalierCount_, festivalier);
        Thread.sleep(100);
        festivalierCount_ ++;
        return festivalier;
    }

    @Override
    public Collection<People> getFestivalier()
    {
        return festivaliers_.values();
    }

    @Override
    public People getFestivalier(int id)
    {
        return festivaliers_.get(id);
    }

	@Override
	public People deleteFestivalier(People festivalier) {
		// TODO Auto-generated method stub
		return festivaliers_.remove(festivalier.getIdFestivalier());
	}

}
