//---------- INCLUDING ----------

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A_STAR Class.
 */
public class AStar<T> implements Searcher<T> {
    //---------- LOCAL CLASS VARIABLES ----------
    private Heuristic<T> heuristic;

    //---------- INITIALIZER ----------

    /**
     * A_STAR(Heuristic<T> heuristic).
     *
     * @param heuristic Heuristic<T> -- a search heuristic.
     */
    public AStar(Heuristic<T> heuristic) {
        this.heuristic = heuristic;
    }

    //---------- UTILITY FUNCTIONS ----------

    /**
     * search(Searchable<T> searchable).
     *
     * @param map Searchable<T> -- a searchable map
     * @return the solution for current search, or the starting point as an empty solution.
     */
    public Solution<T> search(Searchable<T> map) {
        // Local Helper Variables
        MyPriorityQueue<T> priorityQueue = new MyPriorityQueue<>();

        // get start and goal
        T start = map.getStart();
        T goal = map.getGoal();

        // set start into priority queue
        priorityQueue.add(start, 0.0);

        List<T> visited = new ArrayList<>();
        Map<T, T> cameFrom = new HashMap<>();
        Map<T, Double> costSoFar = new HashMap<>();

        // set came from & cost
        cameFrom.put(start, null);
        costSoFar.put(start, 0.0);

        // iter over queue items
        while (!priorityQueue.isEmpty()) {
            // top current item
            T current = priorityQueue.top();
            visited.add(current);

            // check equality
            if (current.equals(goal)) {
                break;
            }

            List<T> list = map.getNeighbors(current);

            // itter over current's neighbors
            for (T next : list) {
                visited.add(next);
                // Duplicate Pruning
                if (priorityQueue.contains(next)) {
                    continue;
                }

                //OPTIMIZATION: check if neighbor is the solution
                if (next.equals(goal)) {
                    cameFrom.put(next, current);
                    return new Solution<>(cameFrom, visited);
                }

                // cost of the new node (neighbor)
                double newCost = costSoFar.get(current) + map.getCost(current, next);
                // if the new cost is better than what we know
                if (newCost < costSoFar.getOrDefault(next, (double) Integer.MAX_VALUE)) {
                    // set came from & cost
                    cameFrom.put(next, current);
                    costSoFar.put(next, newCost);
                    // set priority
                    double priority = newCost + this.heuristic.getHeuristic(next);
                    // add to queue
                    priorityQueue.add(next, priority);
                }
            }
        }

        return new Solution<>(cameFrom, visited);
    }
}