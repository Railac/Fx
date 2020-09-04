package com.dong.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App2Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = FXMLLoader.load(getClass().getResource("Root.fxml"));
		
		Scene s = new Scene(root);
		primaryStage.setTitle("연습중");
		primaryStage.setResizable(false); //false 는 프레임크기 조절 X
		primaryStage.setScene(s);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
