import java.util.List;
import java.util.Map;

/**
 * Solution Class.
 *
 * @param <T> - the type of the nodes that the solution contains.
 */
public class Solution<T> {
    //---------- LOCAL CLASS VARIABLES ----------
    private Map<T,T> path;
    private List<T> visited;

    //---------- INITIALIZER ----------

    /**
     * Solution(Map<T,T> path).
     *
     * @param path Map<T,T> -- the solution's path
     */
    public Solution(Map<T,T> path, List<T> visited) {
        this.path = path;
        this.visited = visited;
    }

    //---------- GETTERS ----------

    /**
     * Map<T,T> getPath().
     *
     * @return the solution path.
     */
    public Map<T,T> getPath() { return this.path; }

    /**
     * getVisited().
     *
     * @return a list of visited noden on this run
     */
    public List<T> getVisited() {
        return visited;
    }
}