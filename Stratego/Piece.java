package Stratego;

import javax.swing.*;
import java.awt.*;
/**
 * class Piece haye bazi ke class haye (Bomb,Capitan,Colonel,Flag,General,Lieutenant,Major,Marshal,Miner,Scout,Sergeant,Spy) azin ers bari mikonn
 * in class amal marbot be piece haye bazi ro barrasi mikone
 */
public abstract class Piece {
    /**
     * dar in arraye tedad mohre haye avalie bazi negah dari mishavad - har index neshane az yek noe mohre darad
     * index = rank piece -1 --- marshal = 9 - spy = 0 -- bomb = 10 , flag = 11;
     * NOTE: az onjaii ke rank flag 0 va rank bomb 100e va gharar dadn -1 ya 99 be onvane index ok nist, khodmon beheshon index 10 va 11 dadim
     * type mojod dar class Computer ya SGUI -- darvaghe hamin index hast
     */
    static int[] numbers = {1,8,5,4,4,4,3,2,1,1,6,1};
    /**
     * Aks piece mored nazar ke dar constructor class Piece (this) meghdar dahi shode
     */
    public Image image;
    /**
     * position X Piece dar board -- X = row Piece dar arraye board * 64 -- 64 chon har khone board ro yek cube 64x64 dar nazar gereftim
     */
    int x;
    /**
     * position Y Piece dar board -- Y = col Piece dar arraye board * 64 -- 64 chon har khone board ro yek cube 64x64 dar nazar gereftim
     */
    int y;
    /**
     * rank Piece
     */
    int rank;
    /**
     * team Piece ( Person ya Computer )
     */
    String team;
    /**
     * row piece dar board
     */
    int row;
    /**
     * col piece dar board
     */
    int col;

    /**
     * constructor -- piece ra ba tavajoh be row va col, dar arraye board gharar midahad
     * @param row -- row piece dar board
     * @param col -- col piece dar board
     * @param player -- playeri ke in piece baraye on hast
     */
    public Piece(int row, int col, Players player){
        x = col * 64;
        y = row * 64;
        this.row = row;
        this.col = col;
        SGUI.board[row][col] = this;
        team = player.toString();
        if(player.toString().equals("Person")) {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\" + toString() + ".png")).getImage();
        }else {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\questionMark" + player.color + ".png")).getImage();
        }
    }

    /**
     * bad az move, ba estefade azin method ro board peymayesh va (row-col-X-Y) piece haro update mikonim
     */
    public void updatePieceStatus(){
        for(int i =0 ; i < SGUI.board.length;i++){
            for(int j = 0 ; j < SGUI.board[i].length;j++){
                if(SGUI.board[i][j] == this){
                    this.row = i;
                    this.col = j;
                    this.x = j*64;
                    this.y = i * 64;
                }
            }
        }
    }

    /**
     * baraye check harkat piece ha roye board ba tavajoh be row va col avalie va sanavie :
     * darin method be vasile 4 mored, move ro check mikonim, 1- inke piece dar Sea nare (Sea : row 4 va 5 - col 2-3 va 6-7)
     * 2- inke harkat ghotri nabashe -- vertical ya horizontal bashe (validMove) -- 3- harkat roye khone haye tekrari nabashe (duplicateMove - tozih dar class Players dade shode)
     * 4- jumpChecker baraye Scout -- tozih dar class Scout dade shode ==== agar harkati, in 4 factor ro ok bashe , harkat anjam dade mishe - dar gheir
     * har kodom ke rad mishe, agar player, Person bod dar TextArea be player gozaresh dade mishe
     * agar Piece 2 null nabud va mogheiiat attack bod, be method attack mire va dar akhar statusPiece ha update mishan
     * @param player -- -- playeri ke in piece baraye on hast
     * @param row1 -- row piece dar board
     * @param col1 -- col piece dar board
     * @param row2 -- row khoneii ke piece mikhad be on bere
     * @param col2 -- col khoneii ke piece mikhad be on bere
     * @return -- inke move anjam shode ya na
     */
    public boolean move(Players player,int row1, int col1, int row2, int col2){
        boolean moved = false;
        boolean ok = true;
        Piece piece1 = findPieceInBoard(row1,col1);
        Piece piece2 = findPieceInBoard(row2,col2);
        if(piece1 != null) {
            if ((row2 == 4 || row2 == 5) && ((col2 == 2 || col2 == 3 || col2 == 6 || col2 == 7))) {
                if(player.toString().equals("Person"))
                    SGUI.textArea.append("You cant go to sea\n");
                return false;
            } else {
                boolean validMove = piece1.validMove(row1, col1, row2, col2);
                boolean duplicateCheck = player.checkMoves(row1, col1, row2, col2);
                boolean jumpChecker =true;
                if(piece1.toString().equals("Scout")) {
                    jumpChecker = Scout.jumpChecker(row1, col1, row2, col2);
                }
                ok = validMove && duplicateCheck && jumpChecker;
                if(player.toString().equals("Person")){
                    if (!validMove) {
                        SGUI.textArea.append("Do a valid move\n");
                    }else if(!jumpChecker){
                        SGUI.textArea.append("You can not pass over the pieces\n");
                    }else if(!duplicateCheck){
                        SGUI.textArea.append("You can not move more than twice between two tiles\n");
                    }
                }

                if (ok && piece2 == null) {
                    Piece temp = SGUI.board[row1][col1];
                    SGUI.board[row1][col1] = SGUI.board[row2][col2];
                    SGUI.board[row2][col2] = temp;
                    player.addToMoves(row1, col1, row2, col2);
                    moved = true;
                } else if(ok){
                    if (!piece1.team.equals(piece2.team)) {
                        moved = piece1.attack(player, row1, col1, row2, col2);
                    } else {
                        if(player.toString().equals("Person")) {
                            SGUI.textArea.append("You cant attack you own piece\n");
                        }
                    }
                }
                if(piece1 != null)
                    piece1.updatePieceStatus();
                if (piece2 != null)
                    piece2.updatePieceStatus();
            }
        }
        return moved;
    }

    /**
     * method checker baraye check ghotri nabudn move (horizontal ya vertical bodn) va inke age hor ya ver bod, 1 khone bishtr harkat nakone
     * @param row1 -- row piece dar board
     * @param col1 -- col piece dar board
     * @param row2 -- row khoneii ke piece mikhad be on bere
     * @param col2 -- col khoneii ke piece mikhad be on bere
     * @return age diagonal nabud va yek khone harkat kard, true dar gheir, false
     */
    public boolean validMove(int row1, int col1, int row2, int col2){
        if(Math.abs(row2-row1) == 0){
            return Math.abs(col2 - col1) == 1;
        }else if(Math.abs(col2-col1)==0){
            return Math.abs(row2 - row1) == 1;
        }
        return false;
    }

    /**
     * method attack piece: aval ba tavajoh be row va col piece, Piece ro dar arraye board peyda mikone. age piece Scout bod, check mikone ke faseleye
     * piecei ke mikhad behesh attack bede, bishtr az 1 bod, behesh ejaze attack nmide ( az onja ke emkan attack va move ham zaman jayez nist ).
     * bad az in check, attack ro be 3 shart taghsim kardim --
     * agar rank piecei ke bahash attack midim bishtr az rank piece attack khorde bashe -- piece 1 jaye piece2 migire
     * agar rank piece 1 kamtar az rank piece 2 -- piece 1 null mishe
     * agar rank piece 1 barabar ba rank piece -- piece 1 va piece 2 jofteshon null mishan
     * @param player -- playeri ke in piece baraye on hast
     * @param row1 -- row piece dar board
     * @param col1 -- col piece dar board
     * @param row2 -- row khoneii ke piece mikhad be on attack bede
     * @param col2 -- col khoneii ke piece mikhad be on attack bede
     * @return -- yek boolean ke moshakhas konad attack anjam shode ya na
     */
    public boolean attack(Players player,int row1, int col1, int row2, int col2){
        Piece piece1 = findPieceInBoard(row1,col1);
        Piece piece2 = findPieceInBoard(row2,col2);
        int rowDif = Math.abs(row2-row1);
        int colDif = Math.abs(col2-col1);
        if(piece1.toString().equals("Scout")){
            if(rowDif > 1 || colDif > 1) {
                if(player.toString().equals("Person")){
                    SGUI.textArea.append("You cant move and attack at the same time\n");
                }
                return false;
            }
        }
        if(piece1.rank > piece2.rank){
            SGUI.textArea.append(piece1.team+"'s "+ piece1 +" attacked "+piece2.team+"'s "
                    + piece2 +"\n-> "+ piece2 +" is removed\n");
            Piece temp = SGUI.board[row1][col1];
            SGUI.board[row1][col1] = null;
            SGUI.board[row2][col2] = temp;
            player.addToMoves(row1, col1, row2, col2);
        }else if(piece1.rank < piece2.rank){
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

    /**
     * baraye peyda kardn piece dar board, mitonest method nashe vali baraye try catchesh, methodesh kardim
     * @param row -- row khoneii ke mikhaim piecesh ro peyda konim
     * @param col -- piece mored nazar
     * @return piece dar board
     */
    public static Piece findPieceInBoard(int row,int col){
        try {
            return SGUI.board[row][col];
        }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
            return null;
        }
    }
}
