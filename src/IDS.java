import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IDS Class, implements Searcher.
 *
 * @param <T> - the type of the nodes that IDS contains.
 */
public class IDS<T> implements Searcher<T> {
    //---------- LOCAL CLASS VARIABLES ----------
    private int maxDepth;

    //---------- INITIALIZER ----------

    /**
     * IDS(int maxDepth).
     *
     * @param maxDepth int -- the max depth for this IDS search.
     */
    public IDS(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    /**
     * DLS(Searchable<T> searchable, T src, T target, int maxDepth).
     *
     * @param searchable Searchable<T>   -- the given searchable
     * @param src        T               -- a generic source
     * @param target     T               -- a generic destination
     * @param maxDepth   int             -- the max depth to run search on
     * @return a list of T items leading from src to target, or null if no such path exists.
     */
    private List<T> DLS(Searchable<T> searchable, T src, T target, int maxDepth) {
        // stopping condition
        if (maxDepth == 0 && src.equals(target)) {
            // create the path list
            List<T> path = new ArrayList<>();
            // add target to path
            path.add(target);
            return path;
        }

        // illegal max depth
        if (maxDepth <= 0) {
            return null;
        }

        // get neighbors for src
        List<T> neighbors = searchable.getNeighbors(src);

        // iterate over neighbors
        for (T v : neighbors) {
            // create the path from current neighbor to dest, with maxDepth - 1
            List<T> path = DLS(searchable, v, target, maxDepth - 1);

            // found in path
            if (path != null) {
                // add the current node to it and return
                path.add(src);
                return path;
            }
        }
        // no path found -> return null
        return null;
    }

    /**
     * search(Searchable<T> map).
     *
     * @param searchable Searchable<T> -- a map to search on.
     * @return the solution for the search or starting point as an empty solution.
     */
    public Solution<T> search(Searchable<T> searchable) {
        // Local Variables
        List<T> visited = new ArrayList<>();
        Map<T, T> cameFrom = new HashMap<>();

        cameFrom.put(searchable.getStart(), null);

        // iteratively deepen in map's depth, searching for a path
        for (int depth = 1; depth < maxDepth; ++depth) {
            visited.add(searchable.getStart()); // TODO: CHECK THIS....
            // run DLS
            List<T> path = DLS(searchable, searchable.getStart(), searchable.getGoal(), depth);

            // a path was found
            if (path != null) {
                // put the path in the cameFrom map, used to return a solution
                for (int i = 1; i < path.size(); ++i) {
                    cameFrom.put(path.get(i - 1), path.get(i));
                }
                // return the solution
                return new Solution<>(cameFrom, visited);
            }
        }
        // return an empty solution
        return new Solution<>(cameFrom, visited);
    }
}