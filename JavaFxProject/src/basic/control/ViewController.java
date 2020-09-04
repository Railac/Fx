package basic.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewController implements Initializable{
@FXML private ListView<String> listView;
@FXML private TableView<Phone> tableView;
@FXML private ImageView imageView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("갤럭시S1");
		list.add("갤럭시S2");
		list.add("갤럭시S3");
		list.add("갤럭시S4");
		list.add("갤럭시S5");
		list.add("갤럭시S6");
		list.add("갤럭시S7");
		listView.setItems(list);
		
		listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				tableView.getSelectionModel().select(newValue.intValue());
				tableView.scrollTo(newValue.intValue());
				
			}
		});
		
		ObservableList<Phone> pList =FXCollections.observableArrayList();
		pList.add(new Phone("갤럭시S1", "phone01.png"));
		pList.add(new Phone("갤럭시S2", "phone02.png"));
		pList.add(new Phone("갤럭시S3", "phone03.png"));
		pList.add(new Phone("갤럭시S4", "phone04.png"));
		pList.add(new Phone("갤럭시S5", "phone05.png"));
		pList.add(new Phone("갤럭시S6", "phone06.png"));
		pList.add(new Phone("갤럭시S7", "phone07.png"));
		
//		TableColumn tcSmartPhone = tableView.getColumns().get(0);
		TableColumn<Phone, ?> tcSmartPhone = tableView.getColumns().get(0);
		tcSmartPhone.setCellValueFactory(new PropertyValueFactory<>("smartPhone"));
		tcSmartPhone.setStyle("-fx-alignment: CENTER;");
		
		TableColumn<Phone, ?> tcImage = tableView.getColumns().get(1);
		tcImage.setCellValueFactory(new PropertyValueFactory<>("image"));
		tcImage.setStyle("-fx-alignment: CENTER;");
		tableView.setItems(pList);
		
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Phone>() {

			@Override
			public void changed(ObservableValue<? extends Phone> observable, Phone oldValue, Phone newValue) {
				imageView.setImage(new Image("/basic/images/"+newValue.getImage()));
				
			}
		});
	
	}// end init
	public void handleBtnOkAction(ActionEvent e) {
		String item = listView.getSelectionModel().getSelectedItem();
		System.out.println("ListView 스마트폰: "+item);
		
		Phone phone = tableView.getSelectionModel().getSelectedItem();
		System.out.println("TableView 스마트폰: "+phone.getSmartPhone());
		System.out.println("TableView 이미지: "+phone.getImage());
	}
	public void handleBtnCancelAction(ActionEvent e) {
		Platform.exit();
	}

}//end class
