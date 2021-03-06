package officelog;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Szandi, Zooty
 */
public class Officelog extends Application {    
    @Override
    public void start(Stage primaryStage) throws Exception{        
        Parent root = FXMLLoader.load(getClass().getResource("view/dashboard.fxml"));
        Scene scene = new Scene(root);        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Officelog");
        primaryStage.setResizable(false);
        primaryStage.setMinWidth(700);
        primaryStage.setMaxWidth(900);
        primaryStage.setMinHeight(700);
        primaryStage.getIcons().add(new Image("http://i.imgur.com/SDmKEqG.jpg"));
        primaryStage.show();         
    }

    public static void main(String[] args) {              
        launch(args);
    }    
}