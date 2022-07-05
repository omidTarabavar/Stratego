package Stratego;
/**
 * class mohreye spy ke az class Piece ers bari mikone
 */
public class Spy extends Piece{
    /**
     * constructor
     * @param row -- row piece dar board
     * @param col -- col piece dar board
     * @param player -- playeri ke in piece baraye on hast
     * rank : rank piece mored nazar
     */
    public Spy(int row, int col, Players player){
        super(row,col,player);
        rank = 1;
    }
    /**
     * toString class
     * @return name piece mored nazar -- mesl bomb ya spy ya ...
     */
    @Override
    public String toString() {
        return "Spy";
    }

    /**
     * az onjaii ke spy mitone Marshal ro bezane, majbor bodim in method ro override konim
     * aval ba method findPieceInBoard, piece haro ba tavajoh be row va coleshon peyda mikonim, bad attack ro be 3 shart taghsim kardim
     * agar rank piecei ke behesh attack midim 10 ( Marshal rankesh 10e ) ya kamtar az rank spy bod ( flag ) -- spy mizane
     * agar rank piece ke behesh attack midim bishtr az spy bod -- spy hazf mishe
     * agar barabar bod, jofteshon hazf mishan va vaziat har kodom ro dar textArea log mikonim.
     * @param player -- playeri ke in piece baraye on hast
     * @param row1 -- row piece dar board
     * @param col1 -- col piece dar board
     * @param row2 -- row khoneii ke piece mikhad be on attack bede
     * @param col2 -- col khoneii ke piece mikhad be on attack bede
     * @return -- yek boolean ke moshakhas konad attack anjam shode ya na
     */
    @Override
    public boolean attack(Players player,int row1, int col1, int row2, int col2) {
        Piece piece1 = findPieceInBoard(row1,col1);
        Piece piece2 = findPieceInBoard(row2,col2);
        if(piece2.rank == 10 || piece1.rank > piece2.rank){
            SGUI.textArea.append(piece1.team+"'s "+ piece1 +" attacked "+piece2.team+"'s "
                    + piece2 +"\n-> "+ piece2 +" is removed\n");
            Piece temp = SGUI.board[row1][col1];
            SGUI.board[row1][col1] = null;
            SGUI.board[row2][col2] = temp;
            player.addToMoves(row1, col1, row2, col2);
        }else if(piece2.rank > piece1.rank){
            SGUI.textArea.append(piece1.team+"'s "+ piece1 +" attacked "+piece2.team+"'s "
                    + piece2 +"\n-> "+ piece1 +" is removed\n");
            SGUI.board[row1][col1] = null;
        }else {
            SGUI.textArea.append(piece1.team+"'s "+ piece1 +" attacked "+piece2.team+"'s "
                    + piece2 +"\n-> both got removed\n");
            SGUI.board[row1][col1] = null;
            SGUI.board[row2][col2] = null;
        }
        return true;
    }
}
