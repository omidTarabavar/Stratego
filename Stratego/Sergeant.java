package Stratego;
/**
 * class mohreye sergeant ke az class Piece ers bari mikone
 */
public class Sergeant extends Piece{
    /**
     * constructor
     * @param row -- row piece dar board
     * @param col -- col piece dar board
     * @param player -- playeri ke in piece baraye on hast
     * rank : rank piece mored nazar
     */
    public Sergeant(int row, int col, Players player){
        super(row,col,player);
        rank = 4;
    }
    /**
     * toString class
     * @return name piece mored nazar -- mesl bomb ya spy ya ...
     */
    @Override
    public String toString() {
        return "Sergeant";
    }
}
