package basic.database.console;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class BoardMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = FXMLLoader.load(getClass().getResource("menuList.fxml"));
		
		
		Scene s = new Scene(root);
		primaryStage.setTitle("잠온다~");
		primaryStage.setScene(s);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
