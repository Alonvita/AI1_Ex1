import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class java_ex1 {
    public static void main(String[] args) {
        int n;
        AlgorithmType type;
        GameMap gameMap;
        try {
            // parse, get the map size, get algorithm type, and create the what so called "graph"
            ParseResult p = ParseMapFromFile.ParseMapFromFile("data/input.txt");

            n = p.getMatrix().length;
            type = p.getType();
            gameMap = new GameMap(n, p.getMatrix());

            Heuristic<GameMap> manhattanDistance = (s) -> {
                int dist =  s.getManhattanDistance();

                return dist;
            };

            Searcher<GameMap> searcher = null;

            switch (type) {
                case IDS:
                    searcher = new IDS<>(n * n);
                    break;
                case BFS:
                    searcher = new BFS<>();
                    break;
                case A_STAR:
                    searcher = new AStar<>(manhattanDistance);
                    break;
                default:
                    throw new RuntimeException("No Algorithm Defined");
            }


            Solution solution = searcher.search(gameMap);

            // Initialize Solution Serializer
            SerializeSolution serSol = new SerializeSolution(solution,
                                                             gameMap.getGoal(),
                                                             solution.getVisited(),
                                                             type);

            List<String> movementAsString = serSol.getSolMovementsAsString();
            StringBuilder output = new StringBuilder();

            System.out.println("Path:");
            for (String movement : movementAsString) {
                System.out.print(movement);
                output.append(movement);
            }

            try {
                Files.write(Paths.get("output.txt"), output.toString().getBytes());
            } catch (Exception e) {
                System.out.println("Could not write result to output file.");
            }

        } catch (IOException e) {
            System.out.println("Could not find/open input.txt");
        } catch (Exception e) {
            // if something happened, i just print and quit.
            System.out.println(e.getMessage());
            System.out.println("Exiting...");
        }
    }
}