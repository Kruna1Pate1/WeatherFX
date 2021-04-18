import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("layouts/main.fxml"));
            Scene scene = new Scene(root);
            
            scene.getStylesheets().add("layouts/style.css");
            Image icon = new Image("assets/icon.png");
            primaryStage.getIcons().add(icon);

            primaryStage.setTitle("Weather Application");

            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
