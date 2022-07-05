package Stratego;
/**
 * class mohreye colonel ke az class Piece ers bari mikone
 */
public class Colonel extends Piece{
    /**
     * constructor
     * @param row -- row piece dar board
     * @param col -- col piece dar board
     * @param player -- playeri ke in piece baraye on hast
     * rank : rank piece mored nazar
     */
    public Colonel(int row, int col, Players player){
        super(row,col,player);
        rank = 8;
    }
    /**
     * toString class
     * @return name piece mored nazar -- mesl bomb ya spy ya ...
     */
    @Override
    public String toString() {
        return "Colonel";
    }
}
