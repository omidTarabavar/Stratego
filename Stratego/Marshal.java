package Stratego;
/**
 * class mohreye marshal ke az class Piece ers bari mikone
 */
public class Marshal extends Piece{
    /**
     * constructor
     * @param row -- row piece dar board
     * @param col -- col piece dar board
     * @param player -- playeri ke in piece baraye on hast
     * rank : rank piece mored nazar
     */
    public Marshal(int row, int col, Players player){
        super(row,col,player);
        rank = 10;
    }
    /**
     * toString class
     * @return name piece mored nazar -- mesl bomb ya spy ya ...
     */
    @Override
    public String toString() {
        return "Marshal";
    }
}
