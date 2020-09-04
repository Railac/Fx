package basic.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//UI:BoardControl.fxml
//Control: Boardcontroller.java
//Board.java
public class BoardExample extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		AnchorPane ap = FXMLLoader.load(getClass().getResource("BoardControl.fxml"));
		Scene scene = new Scene(ap);
		arg0.setScene(scene);
		arg0.setTitle("피곤하다");
		arg0.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
