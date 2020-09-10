package basic.database.console;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BoardMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene s = new Scene(root);
		primaryStage.setScene(s);
		primaryStage.setTitle("도서 대출 프로그램 ");
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
