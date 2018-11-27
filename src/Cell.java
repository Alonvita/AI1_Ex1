/**
 * Cell Class.
 */
public class Cell {
    //---------- LOCAL CLASS VARIABLES ----------
    private final int value;

    //---------- INITIALIZER ----------

    /**
     * Cell(double value).
     *
     * @param value int -- a value for this cell.
     */
    Cell(int value) {this.value = value; }

    //---------- GETTER ----------

    /**
     * getCost().
     *
     * @return cell's value.
     */
    public int cost() {
        return this.value;
    }

    /**
     * getValue().
     *
     * @return the value of the cell.
     */
    public int getValue() { return this.value; }
}