package basic;



import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TextMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();
		root.setPrefWidth(350);
		root.setPrefHeight(150);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(20);
		
		Label label = new Label();
		label.setText("Hello, JavaFx");
		label.setFont(new Font(50));
		
		Button button = new Button();
		button.setText("확인");
		button.setOnAction(event->Platform.exit());
		
		root.getChildren().add(label);
		root.getChildren().add(button);
		
		
		
		Scene scene = new Scene(root);//vbox 를 컨테이너로해서 scene생성
		primaryStage.setTitle("TestMain");
		primaryStage.setScene(scene);//윈도우에 scene 생성한거 출력
		primaryStage.show(); //윈도우 보여주기
		
	}
	public static void main(String[] args) {
		launch(args); //appMain 객체 생성 및 메인 윈도우 생성
	}

}
