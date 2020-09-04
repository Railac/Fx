package basic.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//UI:Input.fxml
//Controller:InputController.java
public class InputExample extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		AnchorPane root = FXMLLoader.load(getClass().getResource("Input.fxml"));
		
		Scene scene = new Scene(root);
		arg0.setScene(scene);
		arg0.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
