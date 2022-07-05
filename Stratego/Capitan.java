package Stratego;
/**
 * class mohreye capitan ke az class Piece ers bari mikone
 */
public class Capitan extends Piece{
    /**
     * constructor
     * @param row -- row piece dar board
     * @param col -- col piece dar board
     * @param player -- playeri ke in piece baraye on hast
     * rank : rank piece mored nazar
     */
    public Capitan(int row, int col, Players player){
        super(row,col,player);
        rank = 6;
    }
    /**
     * toString class
     * @return name piece mored nazar -- mesl bomb ya spy ya ...
     */
    @Override
    public String toString() {
        return "Capitan";
    }
}
