package Stratego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * class GUI bazi
 * in class tamami class haye dige ro bekar migire. amal class haro besorate GUI be user neshon mide.
 * main method in class hast ke run mishe
 */
public class SGUI {
    static Scanner scanner;
    /**
     * baraye vaziat payan bazi
     */
    static boolean finished = false;
    /**
     * arraye 2 bodi ke board ro moshakhas mikonad
     */
    public static Piece[][] board = new Piece[10][10];
    /**
     * board temp ke azash dar fillBoard estefade mikonim
     */
    public static Piece[][] tempBoard = new Piece[10][10];
    /**
     * vaziat shoroe bazi
     */
    static boolean start = false;
    /**
     * Player person
     */
    static Person person = new Person();
    /**
     * Player Computer
     */
    static Computer computer = new Computer();
    /**
     * integeri baraye zakhire row avalie piece
     */
    static int row1;
    /**
     * integeri baraye zakhire col avalie piece
     */
    static int col1;
    /**
     * integeri baraye zakhire row sanavie piece
     */
    static int row2;
    /**
     * integeri baraye zakhire row sanavie piece
     */
    static int col2;
    /**
     * baraye moshakhas kardn inke nobat kie -- true = person - false = Computer
     */
    static boolean playerTurn = true;
    /**
     * text fieldi baraye gereftan address
     */
    public static JTextField textField = new JTextField();
    /**
     * text areaii baraye neveshtan log
     */
    public static JTextArea textArea = new JTextArea("Hi, Welcome to Stratego!\nBy: Omid Tarabavar\n\nYou can customize your pieces by replacing them\n" +
            "or load a text file with the following format:\n"+"Capitan,2,3...\n"+"You can also use text.txt in Game package :)\n"+"\nYou can save the board at any time you want\n"
            +"NOTE: you can't load this text file cause it contains\nwhole board (even computer pieces)\n\n"+"First: Pick a color\n\nSecond: Press start if you are ready\n");

    /**
     * board ro peymayesh mikone va piece haro ba estefade az int type va method getPiece, to khone haye arraye michine - piece haman index dar arraye numbers class Piece
     * amount - tedad piece haye mojod az type mored nazare - baraye Player person ham hamin karo anjam midahad, az onjaii ke computer dar shoroe bazi
     * row haye 0 ta 3 va person row haye 6 ta 9 ro por mikonn, i haye for loop ha darin mahdode ast.
     * @param person -- player person bazi
     * @param computer -- player computer bazi
     */
    public static void initialBoard(Person person, Computer computer){
        int type = 0;
        int amount=0;
        for(int i = 0 ; i < 4 ; i++){
            for(int j = 0 ; j < board[i].length;j++){
                if(amount < Piece.numbers[type]){
                    board[i][j] = getPiece(type,i,j,computer);
                    amount += 1;
                }else {
                    amount = 1;
                    type += 1;
                    board[i][j] = getPiece(type,i,j,computer);
                }
            }
        }
        type = 0;
        amount = 0;
        for(int i = 9 ; i > 5 ; i--){
            for(int j = 0; j< board.length;j++){
                if(amount < Piece.numbers[type]) {
                    board[i][j] = getPiece(type, i, j, person);
                    amount += 1;
                }else {
                    amount = 1;
                    type += 1;
                    board[i][j] = getPiece(type, i, j, person);
                }
            }
        }
    }

    /**
     * method initial piece ha
     * @param type -- noe mohre ( index arraye numbers dar class Piece )
     * @param row -- row khoneii ke mikhaim piece ro to on initial konim
     * @param col -- col khoneii ke mikhaim piece ro to on initial konim
     * @param player -- playeri ke piece ro mikhaim baraye on initial konim ( team piece )
     * @return piece player ro ba tavajoh be param type dar row va col mored nazar return mikone
     */
    public static Piece getPiece(int type, int row , int col, Players player){
        switch (type){
            case 0:{
                return new Spy(row,col,player);
            }case 1:{
                return new Scout(row,col,player);
            }case 2:{
                return new Miner(row,col,player);
            }case 3:{
                return new Sergeant(row,col,player);
            }case 4:{
                return new Lieutenant(row,col,player);
            }case 5:{
                return new Capitan(row,col,player);
            }case 6:{
                return new Major(row,col,player);
            }case 7:{
                return new Colonel(row,col,player);
            }case 8:{
                return new General(row,col,player);
            }case 9:{
                return new Marshal(row,col,player);
            }case 10:{
                return new Bomb(row,col,player);
            }case 11:{
                return new Flag(row,col,player);
            }
        }
        return null;
    }
    public static void main(String[] args) {
        try {
            // initial avalie board
            initialBoard(person, computer);

            // frame GUI
            JFrame frame = new JFrame("Stratego");

            // set width va height frame
            frame.setBounds(0, 0, 1200, 800);

            // paneli baraye neshon dadn board
            JPanel jPanel = new JPanel() {
                /**
                 * boardi ke dar JPanel hast ro mesl khone haye shatranj, charkhone charkhone mikonim, paint yeki az method haye class JPanel hastesh ke
                 * overridesh kardim - ghesmat haye daryache ro Color.BLUE dadim
                 * bad az initial charkhone haye board, ba tavajoh be tartib mohre ha dar arraye board, image marbot be mohre haro set mikonim roshon dar
                 * X va Y har piece
                 * baraye rahatie Person dar donbal kardn harkat computer, yek delay 0.6 sanieii be bazi dadim
                 * @param g -- sheii az class Graphics
                 */
                @Override
                public void paint(Graphics g) {
                    boolean color = true; // true = white - false = blue
                    for (int row = 0; row < 10; row++) {
                        for (int col = 0; col < 10; col++) {
                            if ((row == 4 || row == 5) && (col == 2 || col == 3 || col == 6 || col == 7)) {
                                g.setColor(Color.BLUE);
                            } else if (color) {
                                g.setColor(Color.getHSBColor(190, 37, 89));
                            } else {
                                g.setColor(Color.getHSBColor(150, 227, 210));
                            }
                            g.fillRect(col * 64, row * 64, 64, 64);
                            color = !color;
                        }
                        color = !color;
                    }
                    for (int i = 0; i < board.length; i++) {
                        for (int j = 0; j < board[0].length; j++) {
                            Piece piece = board[i][j];
                            if (piece != null) {
                                g.drawImage(piece.image, piece.x, piece.y, this);
                            }
                        }
                    }
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    /*
                     * moshakhas mikonad ke aya kasi tavanaii edame ro darad ( finish decider ) - darmored method canContinue dar ghesmat khodesh tozih midim
                     * agar continuable bod, va nobat person nabud, computer move ro anjam mide va bad frame bazi repaint mishe ke image piece ha dar jaye dorost gharar begire
                     */
                    boolean continuable = canContinue(person) && canContinue(computer);
                    if (continuable) {
                        if (!playerTurn) {
                            boolean computerMoved = computer.computerMove();
                            if (computerMoved) {
                                frame.repaint();
                                playerTurn = true;
                            }
                        }
                        //baraye inke dar textArea be person, player winner ro elam konim, check mikonim ke kodom, mitone edame bede
                    } else if (!finished) {
                        if (canContinue(person)) {
                            textArea.append("-----------------------------------------------\nPerson wins!\n");
                            finished = true;
                        } else {
                            textArea.append("-----------------------------------------------\nComputer wins!\n");
                            finished = true;
                        }
                    }
                }
            };
            // layout frame ro null mikonim ke betonim component haro ba setBounds position bandi konim
            frame.setLayout(null);
            jPanel.setBounds(20, 20, 640, 640);
            // button start
            JButton button = new JButton("Start");
            button.setBounds(950, 517, 70, 25);
            // radioButton haye entekhab team
            JRadioButton redRadio = new JRadioButton("Red");
            // radioButton haye entekhab team
            JRadioButton blueRadtio = new JRadioButton("Blue");
            redRadio.setBounds(850, 500, 50, 30);
            blueRadtio.setBounds(850, 530, 50, 30);
            // baraye inke faghat yek radioButton ghabele select bashe, to ye group kardimeshon
            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(redRadio);
            buttonGroup.add(blueRadtio);
            // edit kardn textArea tavasot person ro disable kardim
            textArea.setEditable(false);
            // scroll bar baraye textArea
            JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setBounds(725, 100, 400, 300);
            scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
                // ba in method, scroll bar ro kari mikonim ke be sorat khodar, biad paiin
                public void adjustmentValueChanged(AdjustmentEvent e) {
                    e.getAdjustable().setValue(e.getAdjustable().getMaximum());
                }
            });
            // label "Address"
            JLabel label = new JLabel("Address: ");
            label.setBounds(20, 700, 60, 20);
            textField.setBounds(80, 700, 580, 20);
            // button "load"
            JButton buttonLoad = new JButton("Load");
            buttonLoad.setBounds(700, 700, 70, 20);
            // button "Save board"
            JButton buttonSaveBoard = new JButton("Save board");
            buttonSaveBoard.setBounds(800, 700, 100, 20);
            // label "Color"
            JLabel labelColor = new JLabel("Color: ");
            labelColor.setBounds(800, 510, 50, 30);
            // logoye university ke dar yek button ghararesh midim
            Icon icon = new ImageIcon(SGUI.class.getResource("sutech.png"));
            // button logo
            JButton logo = new JButton(icon);
            logo.setBounds(1070, 650, 100, 100);
            // label "GitHub"
            Label gitHub = new Label("GitHub");
            gitHub.setBounds(1140, 5, 50, 25);
            // abi kardn color label github (shabih hyperlink ha)
            gitHub.setForeground(Color.BLUE.darker());
            gitHub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            // label esm programmer :))
            Label programmerName = new Label("By Omid Tarabavar");
            programmerName.setBounds(0,740,150,20);
            // add kardn component ha be frame
            frame.add(jPanel);
            frame.add(button);
            frame.add(redRadio);
            frame.add(blueRadtio);
            frame.add(scrollPane);
            frame.add(label);
            frame.add(textField);
            frame.add(buttonLoad);
            frame.add(buttonSaveBoard);
            frame.add(labelColor);
            frame.add(logo);
            frame.add(gitHub);
            frame.add(programmerName);
            // ezafe kardn actionListener be label gitHub -- ta vaghti rosh click mishe, be page gitHub project bere
            gitHub.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://github.com/omidTarabavar/Stratego.git"));
                    } catch (IOException | URISyntaxException e1) {
                    }
                }

                // temp method, chon niaz nadashtim, azashon estefade nakardim
                @Override
                public void mousePressed(MouseEvent e) {
                }
                // temp method, chon niaz nadashtim, azashon estefade nakardim
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                // temp method, chon niaz nadashtim, azashon estefade nakardim
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                // temp method, chon niaz nadashtim, azashon estefade nakardim
                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            button.addActionListener(new ActionListener() {
                /**
                 * actionListener button Start, dar textArea start shodn bazio log mikone va chand component az frame ro disable mikone ke karbar
                 * dige ghabeliat taghir onaro nadashe bashe - ba tavajoh be entekhab player dar radioButton ha, color team haro moshakhas mikone va dar akhar
                 * color piece haye computer ro update mikone
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (redRadio.isSelected() || blueRadtio.isSelected()) {
                        start = true;
                        textArea.append("-----------------------------------------------\nGame Started, Good luck\n");
                        // shekl border button start ro vaghti click shodesh, taghir mide ke moshakhas tar bashe ke click shode
                        button.setBorder(BorderFactory.createLoweredBevelBorder());
                        button.setEnabled(false);
                        redRadio.setEnabled(false);
                        blueRadtio.setEnabled(false);
                        buttonLoad.setEnabled(false);
                        // shekl border button load ro vaghti click shodesh, taghir mide ke moshakhas tar bashe ke click shode
                        buttonLoad.setBorder(BorderFactory.createLoweredBevelBorder());
                        if (redRadio.isSelected()) {
                            person.color = "Red";
                            computer.color = "Blue";
                            playerTurn = true;
                        } else {
                            person.color = "Blue";
                            computer.color = "Red";
                            playerTurn = false;
                        }
                        computer.updateColor();
                        frame.repaint();
                    } else {
                        textArea.append("Please select your color first\n");
                    }
                }
            });
            /*
             * ba tavajoh be address vared shode tavasot person, method loadBoard ro seda mizane va bad frame ro repaint mikone ke jaye image piece ha dorost shan
             * tozihat marbot be loadBoard dar ghesmat khodesh dade mishe
             */
            buttonLoad.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String address = textField.getText();
                    if (!address.isEmpty()) {
                        loadBoard(address);
                        frame.repaint();
                    } else {
                        textArea.append("Address is empty\n");
                    }
                }
            });
            buttonSaveBoard.addActionListener(new ActionListener() {
                /**
                 * ba tavajoh be address vared shode tavasot person, method saveBoard ro seda mizane
                 * tozihat marbot be saveBoard dar ghesmat khodesh dade mishe
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    String address = textField.getText();
                    if (!address.isEmpty()) {
                        boolean ok = saveBoard(address);
                        if (ok)
                            textArea.append("Done\n");
                    } else {
                        textArea.append("Address is empty\n");
                    }
                }
            });
            // mouseListeneri be frame dade mishe ke ba tavajoh be amalkard mouse dar frame, dastorati ejra shan
            frame.addMouseListener(new MouseListener() {
                /**
                 * temp method, chon niaz nadashtim, azashon estefade nakardim
                 */
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                /**
                 * method click mouse roye piece ha, agar bazi start nashode bod, faghat row va col piece hayii dar ghesmat row 6 ta 9 save mishan
                 * agar bazi shoro shode bod, row va col piece entekhab shode zakhire mishe
                 */
                @Override
                public void mousePressed(MouseEvent e) {
                    if (playerTurn) {
                        int col = (e.getX() - 10) / 64;
                        int row = (e.getY() - 32) / 64;
                        if (!start) {
                            if (row > 5) {
                                row1 = row;
                                col1 = col;
                            }
                        }
                        if (start) {
                            row1 = row;
                            col1 = col;
                        }
                    }
                }

                /**
                 * method release ( vel kardn ) mouse dar frame : jaii ke mouse vel shode ro row va colesho zakhire mikone
                 * agar bazi shoro nashode bod, va piece avalie ( mouse click ) va piece sanavie ( mouse release ) null nabudn, va joz piece haye person bodn,
                 * anharo be method replace mibarad va agar bazi start shode bod, baraye method move piece mibarad, dar akhar ham frame ro repaint mikone ke
                 * jaye image ha ok shan va age player jaii joz board ro mouse click ya release karde bod, behesh dar text array log dade mishe
                 */
                @Override
                public void mouseReleased(MouseEvent e) {
                    if (playerTurn) {
                        col2 = (e.getX() - 10) / 64;
                        row2 = (e.getY() - 32) / 64;
                        Piece piece1 = Piece.findPieceInBoard(row1, col1);
                        Piece piece2 = Piece.findPieceInBoard(row2, col2);
                        if (!start && (piece1 != null && piece2 != null) && (row2 > 5) && (row1 > 5)) {
                            replacePieces(piece1, piece2);
                            frame.repaint();
                        }
                        if (start) {
                            try {
                                boolean moved = false;
                                if (piece1 != null) {
                                    if (piece1.team.equals("Person")) {
                                        moved = piece1.move(person, row1, col1, row2, col2);
                                    } else {
                                        SGUI.textArea.append("You cant move computer pieces\n");
                                    }
                                    if (moved) {
                                        frame.repaint();
                                        playerTurn = false;
                                    }
                                }
                                // in Exception dar method move rokh midahad - agar player mouse ro jaii joz board release konad
                            } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                                textArea.append("Do a valid move");
                            }

                        }
                    }
                }
                /**
                 * temp method, chon niaz nadashtim, azashon estefade nakardim
                 */
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                /**
                 * temp method, chon niaz nadashtim, azashon estefade nakardim
                 */
                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setVisible(true);
            // age exception nashenakhteii throw shod, catch she va be person log dade she
        }catch (Exception exception){
            textArea.append("\nAn unknown exception got thrown\nI would appreciate it if you let me know about this problem\nso that I can fix it\n" +
                    "contact me via gitHub(Top right corner)\n");
        }
    }

    /**
     * timei ke bazi start nashode, mitonim ba in method, piece haro replace konim, faghat niaze jashon dar arraye board avaz karde va bad updatePieceStatus
     * ta row va col va x va y piece ha ham update shan
     * @param piece1 -- piece aval
     * @param piece2 -- piece dovom
     */
    public static void replacePieces(Piece piece1 , Piece piece2){
        try {
            Piece tempPiece = board[piece1.row][piece1.col];
            board[piece1.row][piece1.col] = board[piece2.row][piece2.col];
            board[piece2.row][piece2.col] = tempPiece;
            piece1.updatePieceStatus();
            piece2.updatePieceStatus();
        }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
            textArea.append("Do a valid replacement\n");
        }
    }

    /**
     * method baraye load board az address dade shode. method haye bekar borde ro dar ghesmat khodesh tozih midim.
     * aval file ro baz mikone va bad read mikone va ba tavajoh be data haye file, board michine va bad file ro mibande
     * @param address -- address vared shode tavasot Person
     */
    public static void loadBoard(String address){
        boolean openFile = openFile(address);
        boolean fillBoard;
        if(openFile){
            fillBoard = fillBoard();
            if(fillBoard) {
                fillBoardByTempBoard();
            }
            closeFile();
        }
    }

    /**
     * ba estefade az scanner, say mikonim file ro baz karde va datash ro to scanner berizim. exception catch shode dar sorat moshkel to open file hast
     * @param address -- address vared shode tavasot Person
     * @return -- yek boolean neshon dahnde inke file ba movafaghiat baz shod ya kheir
     */
    private static boolean openFile(String address){
        try {
            scanner = new Scanner(Paths.get(address));
        }catch (IOException ioException){
            textArea.append("Error opening file\n");
            return false;
        }
        return true;
    }

    /**
     * darin method, ma khat be khat az scanner migirim va dar input zakhire mikonim, az onjaii ke har line be sorate
     * -- namepiece,row,col -- hastesh, ba seperator ',' etelaat board ro az input dar miarim.
     * NOTE: az onjaii ke dar pdf project gofte shode khoneye 9,0 dar arraye board dar vaghe khoneye 1,1 bazi hastesh va file text ham bar asas hamin format
     * neveshte mishe, ma row va col bazi ro be row va col board tabdil kardim. bad ham dar board zakhire kardim - exception haye marbot be
     * moshkel dar khondn file ya format input eshtebah dar file ham catch shodn
     * @return -- yek boolean ke neshon bede amaliat khondn file va fill board ba movafaghiat anjam shod ya kheir
     */
    private static boolean fillBoard(){
        try {
            while (scanner.hasNext()){
                String input= scanner.next();
                String pieceName="";
                int row=0,col=0;
                for(int i = 0 ; i < input.length();i++){
                    if(input.charAt(i) == ','){
                        row = 10 - (input.charAt(i+1)-48);
                        if(i+4 < input.length())
                            col = 9;
                        else
                            col = (input.charAt(i+3)-48) - 1;
                        break;
                    }
                    pieceName += input.charAt(i);
                }
                tempBoard[row][col] = getPieceByName(pieceName,row,col);
            }
        }catch (NoSuchElementException elementException){
            textArea.append("File improperly formed\n");
            return false;
        }catch (IllegalStateException illegalStateException){
            textArea.append("Error reading file\n");
            return false;
        }
        return true;
    }

    /**
     * baraye dorost kardn yek piece az noe namei va dar row va coli ke az input file load board bedast avordim
     * @param name -- name piece ( noe piece )
     * @param row -- row piece
     * @param col -- col piece
     * @return pieceii az noe name ke dar khoneii ba row va col dade shode initial shode
     */
    private static Piece getPieceByName(String name,int row,int col){
        Piece piece = null;
        for(int i = 0 ;i < 12 ; i++){
            piece = getPiece(i,row,col,person);
            if(piece.toString().equals(name)){
                break;
            }else {
                piece = null;
            }
        }
        return piece;
    }

    /**
     * piece haye tempboardi ke dar fillboard por kardim ro ba in method be board asli mibarim
     * dalil estefade az tempboard in bod ke age vasat kar load, moshkeli pish omad, board asli dast nakhorde bemone - karesh ham yek peimayesh sade ro 2 arraye
     */
    private static void fillBoardByTempBoard(){
        for(int i = 9 ; i > 5 ; i--){
            for(int j = 0 ; j < board[i].length;j++){
                board[i][j] = tempBoard[i][j];
            }
        }
    }

    /**
     * dar akhar ham file ro mibandim.
     */
    private static void closeFile(){
        if(scanner != null)
            scanner.close();
    }

    /**
     * baraye inke moshakhas konim player hanoz tavan edameye bazi ro dare ya na azin method estefade mikonim, aval to arraye board peimayesh mikonim
     * agar flag nadasht, yani tavanaii edame nadare, age dasht mirim baghie mohre haro check mikonim
     * @param player -- playeri ke mohre hasho check mikonim
     * @return -- age mohreii joz flag ya bomb peyda kardim -- true agar na -- false
     */
    public static boolean canContinue(Players player){
        boolean haveFlag = false;
        for(int i = 0 ; i< board.length;i++){
            for(int j = 0 ; j < board[i].length;j++){
                if(board[i][j] != null && board[i][j].team.equals(player.toString()) && board[i][j].toString().equals("Flag")){
                    haveFlag = true;
                }
            }
        }
        if(haveFlag) {
            for (int k = 0; k < board.length;k++){
                for(int m = 0 ; m < board[k].length;m++){
                    if(board[k][m] != null && board[k][m].team.equals(player.toString()) && !(board[k][m].toString().equals("Flag") || board[k][m].toString().equals("Bomb"))){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * dar address dade shode tavasot Person, yek file be name board.txt dorost mikonim va ba estefade az class OutPutStream java, dar file text write mikonim
     * write ham be in sorate ke araye peimayesh mikonim va piece haro ba format: team -- namePiece,row,col\n write mikonim
     * String NEW_LINE hamon \n hastesh ke bade har khat cursor ro bebare khat badi
     * @param address -- address vared shode tavasot Person
     * @return yek boolean ke neshon bede amaliat save movafaghiat amiz bode ya na
     */
    public static boolean saveBoard(String address){
        try {
            final String NEW_LINE = System.lineSeparator();
            File myFile = new File(address+"\\board.txt");
            OutputStream outputStream =new FileOutputStream(myFile,true);
            for(int i = 0 ; i < board.length; i++){
                for(int j = 0 ; j < board[i].length;j++){
                    if(board[i][j] != null){
                        String save = board[i][j].team +" -> "+board[i][j].toString()+","+(10-board[i][j].row)+","+(board[i][j].col+1)+NEW_LINE;
                        outputStream.write(save.getBytes(),0,save.length());
                    }
                }
            }
            return true;
        }catch (IOException ioException){
            textArea.append("Error opening file\n");
            return false;
        }
    }
}
