package basic.control;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ButtonController implements Initializable{
@FXML private CheckBox chk1;
@FXML private CheckBox chk2;
@FXML private ImageView checkImageView;
@FXML private ToggleGroup group;
@FXML private ImageView radioImageView;
@FXML private Button btnExit;
@FXML private RadioButton rad1;
@FXML private RadioButton rad2;
@FXML private RadioButton rad3;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			
			public void changed(ObservableValue<? extends Toggle> observable, Toggle StartValue, Toggle endValue) {
				Image image = new Image(getClass().
						getResource("/basic/images/" 
						+ endValue.getUserData().toString()+ ".png").toString());
				
				radioImageView.setImage(image);
				
			}
		});
		
		rad1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent me) {
				System.out.println("rad1 clicked.");
				
			}
			
		});
		rad2.setOnMouseClicked((a)->System.out.println("rad2 clicked."));
		rad2.setSelected(true);
		chk1.setOnAction((ae)->handleChkAction());
		chk2.setOnAction((ae)->handleChkAction());
		
	}
	
	private void handleChkAction() {
		String imgName="";
		if(chk1.isSelected() && chk2.isSelected()) {
			imgName="/basic/images/geek-glasses-hair.gif";
		}else if(chk1.isSelected()) {
			imgName="/basic/images/geek-glasses.gif";
		}else if(chk2.isSelected()) {
			imgName="/basic/images/geek-hair.gif";
		}else {
			imgName="/basic/images/geek.gif";
		}
		checkImageView.setImage(new Image(imgName));
	}

//	public void handleChkAction(ActionEvent e) {
//		if(chk1.isSelected() && chk2.isSelected()) {
//			checkImageView.setImage(new Image(getClass().
//					getResource("/basic/images/geek-glasses-hair.gif").toString()));
//		}else if(chk1.isSelected()) {
//			checkImageView.setImage(new Image(getClass().
//					getResource("/basic/images/geek-glasses.gif").toString()));
//		}else if(chk2.isSelected()) {
//			checkImageView.setImage(new Image(getClass().
//					getResource("/basic/images/geek-hair.gif").toString()));
//		}else {
//			checkImageView.setImage(new Image(getClass().
//					getResource("/basic/images/geek.gif").toString()));
//		}
//	}
	
	public void handleBtnExitAction(ActionEvent e) {
		Platform.exit();
	}

}
