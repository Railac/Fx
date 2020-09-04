package basic.container;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxExample extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox(); //컨테이너 생성
		root.setPadding(new Insets(10)); //안쪽 여백
		root.setSpacing(10); //컨트롤 간의 여백
		
		
		ImageView iv = new ImageView();
		iv.setFitWidth(200);
		iv.setPreserveRatio(true);
		iv.setImage(new Image("/basic/images/fruit1.jpg"));
		
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER); //컨트롤 중앙정렬
		hbox.setSpacing(20);
		VBox.setMargin(hbox, new Insets(10));
		
		Button bt = new Button();
		bt.setText("이전");
		Button bt1 = new Button("다음");
		hbox.setHgrow(bt1, Priority.ALWAYS);
		bt1.setMaxWidth(Double.MAX_VALUE);
		
		//이벤트 핸들러를 해당 컨트롤에 등록.
//		bt1.setOnAction(event->Platform.exit());
		bt1.setOnAction(new EventHandler<ActionEvent>() {
			int loc = 1;
			@Override
			public void handle(ActionEvent ae) {
				if(loc==9) {
					loc = 1;
				}
				iv.setImage(new Image("/basic/images/fruit"+ loc++ +".jpg"));
			}	
		});
			
		root.getChildren().add(iv);
		root.getChildren().add(hbox);
		hbox.getChildren().add(bt);//컨테이너에 컨트롤 추가
		hbox.getChildren().add(bt1);
		
		
		Scene scene = new Scene(root); // 씬(컨테이너는 root,씬안의 내용들)생성
		primaryStage.setScene(scene); // 씬 설정
		primaryStage.setTitle("VBox 예제");
//		primaryStage.setResizable(false);
		primaryStage.show(); // 윈도우 출력
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
