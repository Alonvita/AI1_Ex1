/**
 * Heuristic Class.
 *
 * @param <T> - the type of the nodes that the searcher contains.
 */
public interface Heuristic<T> {
    double getHeuristic(T src);
}