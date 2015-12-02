package festival.restlet;

import java.util.Collection;
import java.util.List;

import festival.simulation.*;


/**
 *
 * Interface to the database.
 *
 * @author msimonin
 *
 */
public interface Database
{

    /**
     *
     * Create a new people in the database.
     *
     * @param name      The name of the people
     * @param age       The age of the people
     * @return  the new people.
     */
    People createFestivalier(int id)  throws InterruptedException;


    /**
     *
     * Returns the list of People.
     *
     * @return the list of People
     */
    Collection<People> getFestivalier();


    /**
     * Returns the People with a given id.
     * 
     * @param id	The people id
     * @return	The people or null if not found.
     */
    People getFestivalier(int id);


	/**
	 * 
	 * Deletes the people from the DB.
	 * 
	 * @param people		The people.
	 */
    People deleteFestivalier(People festivalier);

}
