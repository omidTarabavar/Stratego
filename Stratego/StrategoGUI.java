package Stratego;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class StrategoGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    Game game = new Game();
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        Image image = new Image(new FileInputStream("bomb.jpg"));

        ImageView imageView = new ImageView(image);
        imageView.setX(50);
        imageView.setY(25);

        imageView.setFitHeight(400);
        imageView.setFitWidth(400);

        imageView.setPreserveRatio(true);
        Group root = new Group(imageView);
        Scene scene = new Scene(root,500,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
