import java.util.*;

public class BFS<T> implements Searcher<T> {

    /**
     * search(Searchable<T> map).
     *
     * @param map Searchable<T> -- a searchable item.
     * @return a solution representing the path taken for this solution.
     */
    public Solution<T> search(Searchable<T> map) {
        // set start and goal
        T start = map.getStart();
        T goal = map.getGoal();
        int priority = 0;

        List<T> visited = new ArrayList<>();
        Map<T, T> cameFrom = new HashMap<>();
        MyPriorityQueue<T> queue = new MyPriorityQueue<>(); // using MyPriorityQueue with
                                                            // priority as a counter will serve
                                                            // as a normal queue

        cameFrom.put(start, null);
        queue.add(start, priority++);

        while(!queue.isEmpty()) {
            T current = queue.top();
            visited.add(current);

            // get the neighbors
            List<T> neighbors = map.getNeighbors(current);

            // breaking condition
            if(current.equals(goal)) {
                break;
            }

            for(T neighbor : neighbors) {
                // use closed list
                if(!visited.contains(neighbor)) {
                    // add to queue and increase priority for next item
                    queue.add(neighbor, priority++);
                    // update cameFrom list
                    cameFrom.put(neighbor, current);
                }
            }
        }

        return new Solution<>(cameFrom,visited);
    }
}
