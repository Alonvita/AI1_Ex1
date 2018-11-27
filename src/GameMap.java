import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
  * GameMap Class, extends MapUtilities.
  */
 public class GameMap implements Searchable {
     //---------- LOCAL CLASS VARIABLES ----------
     private int size;
     private int h;
     private int g;
     private Cell[][] map;
     private Cell[][] goal;
     private Position zeroPosition;


     //---------- INITIALIZATION FUNCTIONS ----------

     /**
      * GameMap(Cell[][] map).
      *
      * @param size int -- the size of the board.
      * @param map Cell[][] -- a cells map.
      */
     public GameMap(int size, Cell[][] map) {
         // initialize
         this.size = size;
         this.map = map;
         this.g = 0;

         initializeH();
         initializeGoalAndZeroLocation();
     }

     /**
      *
      * GameMap(Cell[][] map).
      *
      * @param size int -- the size of the board
      * @param map Cell[][] -- a cells map.
      * @param depth int -- the depth of the cell in a given search.
      */
     public GameMap(int size, Cell[][] map, int depth) {
         // initialize
         this.size = size;
         this.map = map;
         this.g = depth;

         initializeH();
         initializeGoalAndZeroLocation();
     }

     /**
      * initializeGoalAndZeroLocation().
      *
      * self explanatory...
      */
     private void initializeGoalAndZeroLocation() {
         int count = 1;
         int n = this.size;

         this.goal = new Cell[n][n];

         for (int row = 0; row < n; ++row) {
             for (int col = 0; col < n; ++col) {
                 // locate zero
                 if (this.map[row][col].getValue() == 0)
                     this.zeroPosition = new Position(row, col);

                 // skip last cell of the matrix
                 if (count == (n * n)) {
                     break;
                 }

                 this.goal[row][col] = new Cell(count);

                 ++count;
             }
         }

         // put 0 at the end...
         this.goal[n - 1][n - 1] = new Cell(0);
     }

     /**
      * initializeH().
      *
      * Calculates the Manhattan distance from goal.
      */
     private void initializeH() {
         int manhattanDistance = 0;

         for(int row = 0; row < this.size; ++row) {
             for(int col = 0; col < this.size; ++col) {
                 int val = this.map[row][col].getValue();

                 if (val == 0)
                     continue;

                 int truePlaceRow = (val - 1) / this.size;
                 int truePlaceCol = (val - 1) % this.size;

                 int distance = Math.abs(truePlaceRow - row) + Math.abs(truePlaceCol - col);
                 manhattanDistance += distance;
             }
         }

         this.h = manhattanDistance;
     }

     //---------- GETTERS ----------

     /**
      * getZeroPosition().
      *
      * @return the position of the empty cell (marked as 0)
      */
     public Position getZeroPosition() {
         return zeroPosition;
     }

     /**
      * getSize().
      *
      * @return the size of the board
      */
     public int getSize() {
         return size;
     }

     /**
      * getG();
      *
      * @return the G value
      */
     public int getG() {
         return g;
     }

     /**
      * getManhattanDistance().
      *
      * @return the getManhattanDistance from the end state.
      */
     public int getManhattanDistance() {
         return h;
     }

     /**
      * getMap().
      *
      * @return the cells map of this GameMap.
      */
     public Cell[][] getMap() {
         Cell[][] temp = new Cell[this.size][this.size];

         for(int row = 0; row < this.size; ++row){
             for(int col = 0; col < this.size; ++col) {
                 temp[row][col] = new Cell(this.map[row][col].getValue());
             }
         }

         return temp;
     }

     /**
      * getStart().
      *
      * @return the starting state of this Map.
      */
     @Override
     public GameMap getStart() {
         return new GameMap(this.size, this.getMap());
     }

     /**
      * getGoal().
      *
      * @return the a new GameMap representing the goal state of the game.
      */
     @Override
     public GameMap getGoal() {
         return new GameMap(this.size, this.goal);
     }

     /**
      * getCost(Position src, Position target).
      *
      * @param src    Position -- a source position.
      * @param target Position -- a target position.
      * @return the cost to move from src to target.
      */
     @Override
     public double getCost(Object src, Object target) {
         return 1;
     }

    /**
      * getNeighbors(Object obj).
      *
      * @return a list of available moves from a current state
      */
     @Override
     public List<GameMap> getNeighbors(Object obj) {
         // Local Variables
         if(!(obj instanceof GameMap)) {
             throw new ClassCastException();
         }

         Position zeroPos = ((GameMap) obj).getZeroPosition();
         int zRow = zeroPos.getRow();
         int zCol = zeroPos.getCol();
         int depth = this.g + 1;

         Cell[][] swap = null;

         List<GameMap> neighbors = new ArrayList<>();

         // UP
         if (isMoveLegal(zRow - 1, zCol)) {
             swap = this.swapCells(((GameMap)obj).getMap(), zeroPos, new Position(zRow - 1, zCol));
             neighbors.add(new GameMap(((GameMap)obj).getSize(), swap, depth));
         }

         // DOWN
         if (isMoveLegal(zRow + 1, zCol)) {
             swap = this.swapCells(((GameMap)obj).getMap(), zeroPos, new Position(zRow + 1, zCol));
             neighbors.add(new GameMap(((GameMap)obj).getSize(), swap, depth));
         }

         // LEFT
         if (isMoveLegal(zRow, zCol - 1)) {
             swap = this.swapCells(((GameMap)obj).getMap(), zeroPos, new Position(zRow, zCol - 1));
             neighbors.add(new GameMap(((GameMap)obj).getSize(), swap, depth));
         }

         // RIGHT
         if (isMoveLegal(zRow, zCol + 1)) {
             swap = this.swapCells(((GameMap)obj).getMap(), zeroPos, new Position(zRow, zCol + 1));
             neighbors.add(new GameMap(((GameMap)obj).getSize(), swap, depth));
         }

         // OPTIMIZATION: sort the neighbors list to give the lowest distance first
         neighbors.sort(Comparator.comparingDouble(GameMap::getManhattanDistance));

         return neighbors;
     }

     //---------- PUBLIC UTILITY FUNCTIONS ----------

     /**
      * toString().
      */
     @Override
     public String toString() {
         /// Local Varibales
         StringBuilder stringBuilder = new StringBuilder();

         for(int row = 0; row < this.size; ++row) {
             for(int col = 0; col < this.size; ++col) {

                 stringBuilder.append(this.map[row][col].getValue());
                 if(col == this.size - 1)
                     continue;
                 stringBuilder.append("-");
             }
             stringBuilder.append("\n");
         }
         return stringBuilder.toString();
     }

     //---------- PRIVATE UTILITY FUNCTIONS ----------

     /**
      * isMoveLegal(int row, int col).
      *
      * @param row int -- row.
      * @param col int -- col.
      * @return true if move is legal, or false otherwise.
      */
     private boolean isMoveLegal(int row, int col) {
         return (0 <= row) && (row < this.map.length) && (0 <= col) && (col < this.map[0].length);

     }

     /**
      * isNeighbor(Position cell1, Position cell2).
      *
      * @param cell1 Position -- a cell on the map
      * @param cell2 Position -- a cell on the map
      * @return true if cells are neighbors, or false otherwise
      */
     private boolean isNeighbor(Position cell1, Position cell2) {
         return Math.abs(cell1.getRow() - cell2.getRow()) + Math.abs(cell1.getCol() - cell2.getCol()) > 2;
     }

     /**
      * swapCells(Cell[][] map).
      *
      * @param map Cell[][] -- a cell map
      * @param lhs Position -- a position on the map.
      * @param rhs Position -- a position on the map.
      * @return a cell[][] after the change
      */
     private Cell[][] swapCells(Cell[][] map, Position lhs, Position rhs) {
         Cell temp = map[lhs.getRow()][lhs.getCol()];

         map[lhs.getRow()][lhs.getCol()] = map[rhs.getRow()][rhs.getCol()];
         map[rhs.getRow()][rhs.getCol()] = temp;

         return map;
     }

     // ---------- OVERRIDING ----------

     /**
      * equals(GameMap obj).
      *
      * @param obj GameMap -- a game map.
      * @return return true if the values of the objects are equal, or false otherwise.
      */
     @Override
     public boolean equals(Object obj) {
         return this.toString().equals(obj.toString());
     }


    /**
     * hashCode().
     */
    @Override
    public int hashCode() {
        int sum = 0;

        for(int row = 0; row < this.size; ++row) {
            for(int col = 0; col < this.size; ++col) {
                int val = this.map[row][col].getValue();
                sum += (row + 1) * val + (col + 1) * val;
            }
        }

        return sum;
    }
}