package Stratego;

/**
 * class mohreye bomb ke az class Piece ers bari mikone
 */
public class Bomb extends Piece{
    /**
     * constructor
     * @param row -- row piece dar board
     * @param col -- col piece dar board
     * @param player -- playeri ke in piece baraye on hast
     * rank : rank piece mored nazar
     */
    public Bomb(int row, int col, Players player){
        super(row,col,player);
        rank = 100; // in baray inke joz bomber, baghie age attack konn hazf shan injuri set shode
    }

    /**
     * toString class
     * @return name piece mored nazar -- mesl bomb ya spy ya ...
     */
    @Override
    public String toString() {
        return "Bomb";
    }

    /**
     * be dalil inke bomb harkat nemikonad, in method ro bayad az class Piece override mikardim
     * va dar sorati ke player Person bod, barash dar textArea benivisim ke nemitavand bomb ra move dahad
     * @param player -- playeri ke in piece baraye on hast
     * @param row1 -- row piece dar board
     * @param col1 -- col piece dar board
     * @param row2 -- row khoneii ke piece mikhad be on bere
     * @param col2 -- col khoneii ke piece mikhad be on bere
     * @return -- yek boolean ke moshakhas konad harkat anjam shode ya na
     */
    @Override
    public boolean move(Players player,int row1, int col1, int row2, int col2) {
        if(player.toString().equals("Person"))
            SGUI.textArea.append("You cant move the bomb\n");
        return false;
    }
}
