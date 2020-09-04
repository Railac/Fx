package basic.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TilePaneExample extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
		TilePane tp = new TilePane();
		tp.setPrefTileHeight(100);
		tp.setPrefTileWidth(100);

		ImageView[] ivb = new ImageView[5];
		for (int i = 0; i < ivb.length; i++) {
			ImageView iv = new ImageView();
			iv.setImage(new Image("/basic/images/fruit" + (i + 1)+".jpg"));
			ivb[i]=iv;
			tp.getChildren().add(ivb[i]);
			
		}


		Scene scene = new Scene(tp); // tp컨테이너로 씬생성
		arg0.setTitle("연습");
		arg0.setScene(scene); // 윈도우 씬 설정
		arg0.show(); // 윈도우 출력
	}

	public static void main(String[] args) {
		launch(args);
	}

}
