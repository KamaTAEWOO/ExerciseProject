package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.setProperty("prism.lcdtext", "false");
		Font.loadFont(getClass().getResourceAsStream("RixFont-Regular.ttf"), 14);
		Font.loadFont(getClass().getResourceAsStream("LABDigital.ttf"), 14);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("miniproject1.fxml"));
		Parent root = loader.load();
		MiniProjectMainControl controller = loader.getController();
		controller.setPrimaryStage(primaryStage);
		
		//Parent root = FXMLLoader.load(getClass().getResource("miniproject1.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("AppMain");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
		System.out.println("main");
	}
}
