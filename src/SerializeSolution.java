import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SerializeSolution Class.
 */
public class SerializeSolution {
    //---------- LOCAL CLASS VARIABLES ----------
    List<String> solMovementsAsString;

    //---------- INITIALIZER ----------

    /**
     * SerializeSolution(Solution<Position> sol, Position goal).
     *
     * @param sol Solution<Position> -- a solution.
     * @param goal Position -- the goal position on a searchable item.
     */
    public SerializeSolution(Solution<GameMap> sol, GameMap goal, List<GameMap> visited, AlgorithmType type) {
        this.solMovementsAsString = solutionToString(sol, goal, visited, type);
    }

    //---------- GETTERS ----------

    /**
     * getSolMovementsAsString().
     *
     * @return the serialized solution.
     */
    public List<String> getSolMovementsAsString() {
        return this.solMovementsAsString;
    }

    //---------- UTILITY FUNCTIONS ----------

    /**
     * solutionToString(Solution<Position> sol, Position goal).
     *
     * @param sol  Solution<Position> -- a solution to serialize.
     * @param goal Position -- a position goal.
     * @return a list of strings, defines the movements taken by the search algorithm
     * to find this solution.
     */
    private List<String> solutionToString(Solution<GameMap> sol,
                                          GameMap goal,
                                          List<GameMap> visited,
                                          AlgorithmType type) {
        // Local Variables
        Map<GameMap, GameMap> cameFrom = sol.getPath();
        GameMap curr = goal;
        GameMap prev = cameFrom.get(goal);
        int maxDepth = goal.getG();

        int totalCost = 0;

        if (null == prev)
            return null;

        List<String> backTraceSolToString = new ArrayList<>();

        while (prev != null) {
            // evaluate cost and number of developed sons
            totalCost += prev.getManhattanDistance();

            // inner loop variables
            int dRow = curr.getZeroPosition().getRow() - prev.getZeroPosition().getRow();
            int dCol = curr.getZeroPosition().getCol() - prev.getZeroPosition().getCol();

            // get the right movement string for a given movement made
            String str = movementToString(dRow, dCol);

            // add str to solution
            backTraceSolToString.add(0, str);

            // move
            curr = prev;
            prev = cameFrom.get(prev);

            // used for IDS
            maxDepth = curr.getG();
        }

        backTraceSolToString.add("\n");
        if(type == AlgorithmType.IDS) {
            List<GameMap> visitedForDepthG = new ArrayList<>();

            for(GameMap gameMap : visited) {
                if(gameMap.getG() == maxDepth)
                    visitedForDepthG.add(gameMap);
            }
            backTraceSolToString.add(Integer.toString(visitedForDepthG.size()));
        } else {
            backTraceSolToString.add(Integer.toString(visited.size()));
        }
        backTraceSolToString.add("\n");

        switch (type) {
            case A_STAR:
                // total cost for A_STAR
                backTraceSolToString.add(Integer.toString(totalCost));
                break;
            case IDS:
                // Depth for IDS
                backTraceSolToString.add(Integer.toString(maxDepth));
                break;
            case BFS:
                // 0 for BFS
                backTraceSolToString.add(Integer.toString(0));
                break;
        }


        return backTraceSolToString;
    }

    /**
     * movementToString(int drow, int dcol).
     *
     * @param dRow int -- a delta for row.
     * @param dCol int -- a delta for col.
     * @return a string describing the movement made by the search alhorithm.
     */
    public static String movementToString(int dRow, int dCol) {
        if(dRow < 0)
            return "D";

        if(dRow > 0)
            return "U";

        if(dCol < 0)
            return "R";

        if(dCol > 0)
            return "L";

        return "";
    }
}
