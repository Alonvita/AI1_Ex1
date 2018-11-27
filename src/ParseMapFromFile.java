import java.util.List;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 * ParseMapFromFile Class.
 */
public class ParseMapFromFile {
    //---------- INITIALIZER ----------

    /**
     * ParseMapFromFile(String filename) throws Exception.
     *
     * @param filename String -- a string.
     *
     * @return the parsed result for this solution.
     * @throws Exception -- illegal number of lines in file.
     */
    public static ParseResult ParseMapFromFile(String filename) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(filename));

        // not enough lines - bad format.
        if (lines.size() < 3) {
            throw new Exception("Illegal file format received");
        }

        // read algorithm type
        AlgorithmType type;
        switch (lines.get(0)) {
            case "1":
                type = AlgorithmType.IDS;
                break;
            case "2":
                type = AlgorithmType.BFS;
                break;
            case "3":
                type = AlgorithmType.A_STAR;
                break;

            default:
                throw new Exception("Unknown algorithm \"" + lines.get(0) + "\"");
        }

        // rad the board size
        int n = Integer.parseInt(lines.get(1));

        Cell[][] grid = new Cell[n][n];

        // remove both lines
        lines.remove(1);
        lines.remove(0);

        int row = 0;
        String raw_values = lines.get(row);
        raw_values = raw_values.replace("-","");

        for (int index = 0; index < n * n; ++index) {
            // Local Variable
            char value = raw_values.charAt(index);

            if((index) % n == 0 && index != 0)
                ++row;

            grid[row][index % n] = new Cell(Character.getNumericValue(value));

        }

        return new ParseResult(type, grid);

    }
}