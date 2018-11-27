import java.util.List;

/**
 * MapUtilities Class, implements Searchable.
 * An abstract class that contains some utility functions for the map.
 *
 * @param <T> - the type of the nodes that the searcher contains.
 */
public abstract class MapUtilities<T> implements Searchable<T> {
    /**
     * Computes the path from the given solution
     *
     * @param solution - the solution object
     * @return List of nodes that represents the path from start to goal.
     */
    public List<T> computePath(Solution<T> solution) {

        //TODO: do this at the end to parse the path to a string

//        Map<T, T> parent = solution.getPath();
//        List<T> path = new ArrayList<>();
//        for (T node = this.getGoal(); node != null; node = parent.get(node)) {
//            path.add(0, node);
//        }
        return null;
    }
}