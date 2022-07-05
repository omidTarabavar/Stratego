package Stratego;
/**
 * class Player haye bazi ke 2 class Computer va Person azin ers bari mikonn
 * in class amal marbot be player haye bazi ro barrasi mikone
 */
public abstract class Players {
    /**
     * harkat player ha darin arraye zakhire mishe - dalil string bodanesh ham ine ke row+col ro besorate string tosh zakhire mikonim
     */
    String[][] moves = new String[2][2];
    /**
     * color player tosh zakhire mishe -- ya red ya blue
     */
    String color;

    /**
     * vaghti player harkati mizane, ba estefade az in arraye, on harkat be move ha ezafe mishe - karbordesh vaghtie ke mikhaim player ro az
     * harkat bein 2 khane tekrari bish az 2 bar motevali mahdod konim -- darin method, row va col khoneii ke bode va khoneii ke mire ro zakhire mikonim
     * az index finder estefade mikonim ke bbinim to che indexi bayad ezafe konim , age arraye moves por bod, az onjaii ke ma faghat 2 move akhar ro mikhaim
     * khone haye arraye ro 1 shift midim aghab va harkat jadid ro to khone akhar zakhire mikonim.
     * @param row1 -- row avalie piece ( ghable harkat )
     * @param col1 -- col avalie piece ( ghable harkat )
     * @param row2 -- row sanavie piece ( bade harkat )
     * @param col2 -- col sanavie piece ( bade harkat )
     */
    public void addToMoves(int row1,int col1,int row2,int col2){
        int index = indexFinder();
        if(index < 2){
            moves[index][0] = ""+row1+""+col1;
            moves[index][1] = ""+row2+""+col2;
        }else {
            moves[0][0] = moves[1][0];
            moves[0][1] = moves[1][1];
            moves[1][0] = ""+row1+""+col1;
            moves[1][1] = ""+row2+""+col2;
        }

    }

    /**
     * vaghti mikhaim movei be arraye moves ezafe konim bayad bedonim be che indexi ezafe konim, baraye hamin azin method esteface mikonim
     * @return index khoneyeii ke bayad move ro zakhire konim
     */
    public int indexFinder(){
        int index =2;
        for(int i = 0 ; i < moves.length ; i++){
            if(moves[i][0] == null){
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * vaghti move haro zakhire kardim, bayad check konim ke aya harkat tekrari hastn ya na - ba in method check mikonim
     * @param row1 -- row avalie piece ( ghable harkat )
     * @param col1 -- col avalie piece ( ghable harkat )
     * @param row2 -- row sanavie piece ( bade harkat )
     * @param col2 -- col sanavie piece ( bade harkat )
     * @return natije check - false age move ha tekrari bodn - true age tekrari nabudn
     */
    public boolean checkMoves(int row1,int col1,int row2,int col2) {
        int cnt = 0;
        String firstPlace = "" + row1 + "" + col1;
        String secondPlace = "" + row2 + "" + col2;

        if (indexFinder() == 2) {
            for (int i = 0; i < moves.length; i++) {
                if (moves[i][0].equals(firstPlace) && moves[i][1].equals(secondPlace)) {
                    cnt += 1;
                } else if (moves[i][1].equals(firstPlace) && moves[i][0].equals(secondPlace)) {
                    cnt += 1;
                }
            }
            return cnt != 2;
        }
        return true;
    }
}
