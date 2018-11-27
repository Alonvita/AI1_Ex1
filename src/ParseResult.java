/**
 * ParseResult Class.
 */
public class ParseResult {
    //---------- LOCAL CLASS VARIABLES ----------
    private AlgorithmType type;
    private Cell[][] grid;

    //---------- INITIALIZER ----------

    /**
     * ParseResult(AlgorithmType type, Cell[][] grid).
     * @param type AlgorithmType -- the algorithm type for this result.
     * @param grid Cell[][] -- the cell matrix for this result.
     */
    public ParseResult(AlgorithmType type, Cell[][] grid) {
        this.type = type;
        this.grid = grid;
    }

    //---------- GETTERS ----------

    /**
     * getType().
     *
     * @return the algorithm type used.
     */
    public AlgorithmType getType() {
        return this.type;
    }

    /**
     * getMatrix().
     *
     * @return the cells matrix.
     */
    public Cell[][] getMatrix() {
        return this.grid;
    }
}