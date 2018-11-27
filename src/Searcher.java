/**
 * Searcher Class.
 *
 * @param <T> - the type of the nodes that the searcher contains.
 */
public interface Searcher<T> {
    /**
     * search(Searchable<T> searchable).
     *
     * @param searchable Searchable<T> -- a searchable item.
     * @return the solution for the search.
     */
    Solution<T> search(Searchable<T> searchable);
}