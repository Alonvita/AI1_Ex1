import java.util.ArrayList;
import java.util.List;

/**
 * Searchable Class.
 *
 * @param <T> - the type of the nodes that the searchable item contains.
 */
public interface Searchable<T> {
    /**
     * getNeighbors(T v).
     *
     * @return a list of neighbors to the given object.
     */
    List<T> getNeighbors(T obj);

    /**
     * getStart().
     *
     * @return get the starting item of this search.
     */
    T getStart();

    /**
     * getGoal.
     *
     * @return the goal of this search.
     */
    T getGoal();

    /**
     * getCost(T src, T target).
     *
     * @param src T -- a source.
     * @param target T -- a target.
     * @return the movement cost between src and target.
     */
    double getCost(T src, T target);

    @Override
    boolean equals(Object obj);
}