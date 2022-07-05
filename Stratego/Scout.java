package Stratego;
/**
 * class mohreye scout ke az class Piece ers bari mikone
 */
public class Scout extends Piece{
    /**
     * constructor
     * @param row -- row piece dar board
     * @param col -- col piece dar board
     * @param player -- playeri ke in piece baraye on hast
     * rank : rank piece mored nazar
     */
    public Scout(int row, int col, Players player){
        super(row,col,player);
        rank = 2;
    }
    /**
     * toString class
     * @return name piece mored nazar -- mesl bomb ya spy ya ...
     */
    @Override
    public String toString() {
        return "Scout";
    }

    /**
     * baraye harkat scout bayad check konim ke az mohreii mipare ya na. rowDif = ekhtelaf row ha - colDif = ekhtelaf col ha
     * ba in 2 int, noe harkat ro moshakhas mikonim ( horizontal ya vertical ).
     * har kodom ke bod, roye khone haii ke azash rad mishe peymayesh mikonim, agar mohreii bod, yani jump zade,checker false neshon mide ke to
     * method move azash estefade mikonim
     * @param row1 -- row piece dar board
     * @param col1 -- col piece dar board
     * @param row2 -- row khoneii ke piece mikhad be on bere
     * @param col2 -- col khoneii ke piece mikhad be on bere
     * @return -- yek boolean ke moshakhas konad heyn harkat, az roye mohreii rad shode ya na
     */
    public static boolean jumpChecker(int row1,int col1,int row2,int col2){
        boolean flag = true;
        int rowDif = Math.abs(row2-row1);
        int colDif = Math.abs(col2-col1);

        if(rowDif == 0){
            int smallerCol = Math.min(col1,col2);
            int biggerCol = Math.max(col1,col2);
            for(int i = smallerCol+1 ; i < biggerCol ; i++){
                if(SGUI.board[row1][i] != null){
                    flag = false;
                    break;
                }
            }
        }else if(colDif == 0){
            int smallerRow = Math.min(row1,row2);
            int biggerRow = Math.max(row1,row2);
            for(int i = smallerRow+1 ; i< biggerRow ;i++){
                if(SGUI.board[i][col1] != null){
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * yek checker digas ke check she ke harkat ghotri nabashad -- ya horizontal ya vertical bashad
     * @param row1 -- row piece dar board
     * @param col1 -- col piece dar board
     * @param row2 -- row khoneii ke piece mikhad be on bere
     * @param col2 -- col khoneii ke piece mikhad be on bere
     * @return yek boolean ke moshakhas kone harkat valid hast ya na
     */
    @Override
    public boolean validMove(int row1, int col1, int row2, int col2) {
        return (Math.abs(row2-row1)==0 || Math.abs(col2-col1)==0);
    }
}
