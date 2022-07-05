package Stratego;
/**
 * class mohreye flag ke az class Piece ers bari mikone
 */
public class Flag extends Piece{
    /**
     * constructor
     * @param row -- row piece dar board
     * @param col -- col piece dar board
     * @param player -- playeri ke in piece baraye on hast
     * rank : rank piece mored nazar
     */
    public Flag(int row, int col, Players player){
        super(row,col,player);
        rank = 0; // in baray inke har piece be flag attack dad, betone hazfesh kone
    }
    /**
     * toString class
     * @return name piece mored nazar -- mesl bomb ya spy ya ...
     */
    @Override
    public String toString() {
        return "Flag";
    }
    /**
     * be dalil inke flag harkat nemikonad, in method ro bayad az class Piece override mikardim
     * va dar sorati ke player Person bod, barash dar textArea benivisim ke nemitavand flag ra move dahad
     * @param player -- playeri ke in piece baraye on hast
     * @param row1 -- row piece dar board
     * @param col1 -- col piece dar board
     * @param row2 -- row khoneii ke piece mikhad be on bere
     * @param col2 -- col khoneii ke piece mikhad be on bere
     * @return -- yek bolean ke moshakhas konad harkat anjam shode ya na
     */
    @Override
    public boolean move(Players player,int row1, int col1, int row2, int col2) {
        if(player.toString().equals("Person"))
            SGUI.textArea.append("You cant move the flag\n");
        return false;
    }
}
