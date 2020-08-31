package basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AppMain01 extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10));
		hbox.setSpacing(10);
		
		
		//컨트롤
		TextField textField = new TextField();
		textField.setPrefWidth(200);
		
		Button button = new Button();
		button.setText("확인");
		button.setOnAction(evenvt->Platform.exit());
		
		hbox.getChildren().add(textField);
		hbox.getChildren().add(button);
		
		Scene scene = new Scene(hbox); //신생성
		
		arg0.setTitle("어휴 빡시다"); //윈도우 타이틀
		arg0.setScene(scene); //신 출력
		arg0.show();

		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
