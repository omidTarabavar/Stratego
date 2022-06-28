package Stratego;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StrategoGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    public int counter(int[][] indexs){
        for(int i =0 ;i < indexs.length;i++){
            if(indexs[i][0] != 99){
                return i+1;
            }
        }
        return 0;
    }
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException{
        File file = new File("D:\\Transfer from laptop\\GitHub\\Stratego\\Stratego\\Pieces\\bomb.png");
        Image img = new Image(getClass().getResourceAsStream("Pieces\\bomb.png"),80,80,false,false);

        ImageView view = new ImageView(img);
        view.setPreserveRatio(true);
        view.setFitWidth(20);
        view.setFitHeight(20);
        ImageView[][] imageViews = new ImageView[10][10];
        for(int i = 0 ;i < 10;i++) {
            for (int j = 0; j < 10; j++) {
                imageViews[i][j] = new ImageView(img);
            }
        }

        VBox root1 = new VBox();
        Button[] buttons = new Button[2];
        int[][] indexs = {{99,99},{99,99}};
        int buttonCounter = 0 ;
        root1.setAlignment(Pos.CENTER);
        Button[][] buttons1 = new Button[8][8];
        for(int i = 0 ; i< 10 ; i++){
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            for(int j = 0 ; j< 10; j++){
                buttons1[i][j].setMaxSize(80,80);
                buttons1[i][j].setMinSize(80,80);
                buttons1[i][j].setOnAction(event -> {
//                    indexs[]
//                    if(counter(indexs)==2){
//                        Button temp = buttons[0];
//                        buttons[0] = buttons[1];
//                        buttons[1] = temp;
//                    }


                });
                if(i != 4 && i != 5) {
                    buttons1[i][j].setGraphic(imageViews[i][j]);
                }else if (j ==2 || j == 3 || j==6 || j ==7){
                    buttons1[i][j].setBackground(new Background(new BackgroundFill(Color.BLUE,new CornerRadii(0),new Insets(0))));
                }
                hBox.getChildren().add(buttons1[i][j]);
            }
            root1.getChildren().add(hBox);
        }
        root1.setAlignment(Pos.CENTER);
        VBox root = new VBox(root1);
        root.setAlignment(Pos.CENTER);
//        Group root = new Group(root1);
        Scene scene = new Scene(root,1400,800, Color.BEIGE);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
