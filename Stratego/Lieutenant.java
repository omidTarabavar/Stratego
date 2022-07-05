package Stratego;
/**
 * class mohreye lieutenant ke az class Piece ers bari mikone
 */
public class Lieutenant extends Piece{
    /**
     * constructor
     * @param row -- row piece dar board
     * @param col -- col piece dar board
     * @param player -- playeri ke in piece baraye on hast
     * rank : rank piece mored nazar
     */
    public Lieutenant(int row, int col, Players player){
        super(row,col,player);
        rank = 5;
    }
    /**
     * toString class
     * @return name piece mored nazar -- mesl bomb ya spy ya ...
     */
    @Override
    public String toString() {
        return "Lieutenant";
    }
}
