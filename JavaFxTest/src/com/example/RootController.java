package com.example;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class RootController implements Initializable{
@FXML Button btn1, btn2, btn3;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				handleBtn1Action(event);
				
			}
		});
		
		btn2.setOnAction(event->handleBtn2Action(event));
		btn3.setOnAction(event->handleBtn3Action(event));
		
	}
	private void handleBtn3Action(ActionEvent event) {
		System.out.println("버튼2 클릭");
	}
	private void handleBtn2Action(ActionEvent event) {
		System.out.println("버튼3 클릭");
	}
	public void handleBtn1Action(ActionEvent event) {
		System.out.println("버튼1 클릭");
		
	}

}
