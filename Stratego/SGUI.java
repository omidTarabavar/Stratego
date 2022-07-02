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
import java.net.URL;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SGUI {
    static Scanner scanner;
    public static Piece[][] board = new Piece[10][10];
    public static Piece[][] tempBoard = new Piece[10][10];
    static boolean activeMouse = false;
    static boolean start = false;
    static Person person = new Person();
    static Computer computer = new Computer();
    static int row1;
    static int col1;
    static int row2;
    static int col2;
    static boolean playerTurn = true;
    public static JTextField textField = new JTextField();
    public static JTextArea textArea = new JTextArea("Hi, Welcome to Stratego!\nBy: Omid Tarabavar\n\nYou can customize your pieces by replacing them\n" +
            "or load a text file with the following format:\n"+"Capitan,2,3\n..."+"You can also use text.txt in Game package :)\n"+"\nYou can also save the board\n"
            +"NOTE: you can't load this text file cause it contains\nwhole board (even computer pieces)\n\n"+"First: Pick a color\n\nSecond: Press start if you are ready\n");
    //       "
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
        initialBoard(person,computer);
        JFrame frame = new JFrame("Stratego");
        frame.setBounds(0,0,1200,800);

//        frame.setUndecorated(true);
        JPanel jPanel = new JPanel(){
            @Override
            public void paint(Graphics g) {
                boolean color = true; // true = white - false = blue
                for(int row = 0 ; row < 10 ;row++){
                    for(int col = 0 ;col < 10 ; col++){
                        if((row ==4 || row == 5)  && (col == 2 || col == 3 || col ==6 || col==7) ){
                            g.setColor(Color.BLUE);
                        }else if(color){
                            g.setColor(Color.getHSBColor(190,37,89));
                        }else {
                            g.setColor(Color.getHSBColor(150,227,210));
                        }
                        g.fillRect(col*64,row*64,64,64);
                        color = !color;
                    }
                    color = !color;
                }
                for(int i = 0 ; i < board.length;i++){
                    for(int j = 0 ; j < board[0].length;j++){
                        Piece piece = board[i][j];
                        if(piece != null) {
                            g.drawImage(piece.image, piece.x, piece.y, this);
                        }
                    }
                }
                try {
                    Thread.sleep(600);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }

                boolean continuable = canContinue(person) && canContinue(computer);
                if(continuable) {
                    if (!playerTurn) {
                        boolean computerMoved = computer.computerMove();
                        if (computerMoved) {
                            frame.repaint();
                            playerTurn = true;
                        }
                    }
                }else {
                    if(canContinue(person)){
                        textArea.append("-----------------------------------------------\nPerson wins!\n");
                    }else {
                        textArea.append("-----------------------------------------------\nComputer wins!\n");
                    }
                }
            }
        };
        frame.setLayout(null);
        jPanel.setBounds(20,20,640,640);
        JButton button = new JButton("Start");
        button.setBounds(950,517,70,25);
        JRadioButton redRadio = new JRadioButton("Red");
        JRadioButton blueRadtio = new JRadioButton("Blue");
        redRadio.setBounds(850,500,50,30);
        blueRadtio.setBounds(850,530,50,30);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(redRadio);
        buttonGroup.add(blueRadtio);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(725,100,400,300);
        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
        });
        JLabel label = new JLabel("Address: ");
        label.setBounds(20,700,60,20);
        textField.setBounds(80,700,580,20);
        JButton buttonLoad = new JButton("Load");
        buttonLoad.setBounds(700,700,70,20);
        JButton buttonCopy = new JButton("Save board");
        buttonCopy.setBounds(800,700,100,20);
        JLabel labelColor = new JLabel("Color: ");
        labelColor.setBounds(800,510,50,30);
        Icon icon = new ImageIcon(SGUI.class.getResource("sutech.png"));
        JButton logo = new JButton(icon);
        logo.setBounds(1070,650,100,100);
        Label gitHub = new Label("GitHub");
        gitHub.setBounds(1140,5,50,25);
        gitHub.setForeground(Color.BLUE.darker());
        gitHub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        frame.add(jPanel);
        frame.add(button);
        frame.add(redRadio);
        frame.add(blueRadtio);
        frame.add(scrollPane);
        frame.add(label);
        frame.add(textField);
        frame.add(buttonLoad);
        frame.add(buttonCopy);
        frame.add(labelColor);
        frame.add(logo);
        frame.add(gitHub);
        gitHub.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    Desktop.getDesktop().browse(new URI("https://github.com/omidTarabavar/Stratego.git"));

                } catch (IOException | URISyntaxException e1) {
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (redRadio.isSelected() || blueRadtio.isSelected()) {
                    start = true;
                    textArea.append("-----------------------------------------------\nGame Started, Good luck\n");
                    button.setBorder(BorderFactory.createLoweredBevelBorder());
                    button.setEnabled(false);
                    redRadio.setEnabled(false);
                    blueRadtio.setEnabled(false);
                    buttonLoad.setEnabled(false);
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
                }else {
                    textArea.append("Please select your color first\n");
                }
            }
        });
        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = textField.getText();
                if(!address.isEmpty()){
                    loadBoard(address);
                    frame.repaint();
                }else {
                    textArea.append("Address is empty\n");
                }
            }
        });
        buttonCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = textField.getText()+'\\';
                if(!address.isEmpty()){
                    saveBoard(address);
                    textArea.append("Done\n");
                }else {
                    textArea.append("Address is empty\n");
                }
            }
        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(playerTurn) {
                     int col = (e.getX() - 10) / 64;
                     int row = (e.getY() - 32) / 64;
                    if (!start) {
                        if (row > 5) {
                            row1 = row;
                            col1 = col;
                            System.out.println("col:" + col + " row: " + row);
                        }
                    }
                    if (start) {
                        row1 = row;
                        col1 = col;
                        Piece piece = Piece.findPieceInBoard(row, col);
                        System.out.println(piece);
                        System.out.println("col:" + col + " row: " + row);
                    }
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(playerTurn) {
                    col2 = (e.getX() - 10) / 64;
                    row2 = (e.getY() - 32) / 64;
                    Piece piece1 = Piece.findPieceInBoard(row1, col1);
                    Piece piece2 = Piece.findPieceInBoard(row2, col2);
                    if (!start && (piece1 != null && piece2 != null) &&  (row2 > 5) && (row1 > 5)) {
                        replacePieces(piece1, piece2);
                        frame.repaint();
                    }
                    if (start) {
                        boolean moved = false;
                        if (piece1 != null) {
                            if(piece1.team.equals("Person")) {
                                moved = piece1.move(person, row1, col1, row2, col2);
                            }else {
                                SGUI.textArea.append("You cant move computer piece\n");
                            }
                            if (moved) {
                                frame.repaint();
                                playerTurn = false;
                            }
                        }
                    }
                }
        }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void replacePieces(Piece piece1 , Piece piece2){
        int temp = piece1.x;
        piece1.x = piece2.x;
        piece2.x = temp;
        temp = piece1.y;
        piece1.y = piece2.y;
        piece2.y=temp;
        Piece tempPiece = board[piece1.y/64][piece1.x/64];
        board[piece1.y/64][piece1.x/64] = board[piece2.y/64][piece2.x/64];
        board[piece2.y/64][piece2.x/64] = tempPiece;

        activeMouse = false;

    }
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
    private static boolean openFile(String address){
        try {
            scanner = new Scanner(Paths.get(address));
        }catch (IOException ioException){
            textArea.append("Error opening file.");
            return false;
        }
        return true;
    }
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
                System.out.println(pieceName);
                System.out.println(row);
                System.out.println(col);
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
    private static void fillBoardByTempBoard(){
        for(int i = 9 ; i > 5 ; i--){
            for(int j = 0 ; j < board[i].length;j++){
                board[i][j] = tempBoard[i][j];
            }
        }
    }
    private static void closeFile(){
        if(scanner != null)
            scanner.close();
    }
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
    public static void saveBoard(String address){
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
        }catch (IOException ioException){
            textArea.append("Error opening file\n");
        }

    }


}
