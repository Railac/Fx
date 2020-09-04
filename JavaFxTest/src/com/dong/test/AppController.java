package com.dong.test;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import basic.common.db.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppController implements Initializable{
	Connection conn = ConnectionDB.getDB();
	ObservableList<Student01> slist;
	
@FXML TableView<Student01> tableView;
@FXML Button btnAdd, btnBarChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TableColumn<Student01, ?> tc = tableView.getColumns().get(0); // 첫번째칼럼
		tc.setCellValueFactory(new PropertyValueFactory<>("name"));

		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("korean"));

		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("math"));

		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("english"));
		
		
		tableView.setItems(getStudentList()); //테이블뷰 초기화(초기화할값)
		
		btnAdd.setOnAction(event->handleBtnAddAction());
	}//end init

	private void handleBtnAddAction() { //버튼 클릭했을때(액션) 이벤트 핸들러
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());//새로운 stage 생성
		
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("AddForm.fxml"));
			Scene s = new Scene(parent);
			stage.setScene(s);
			stage.show(); //새창 띠우기
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private ObservableList<Student01> getStudentList() { //조회 메소드
		String sql = "select * from chart";
		slist = FXCollections.observableArrayList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { //rs.next 는 rs에 값이있으면 true, 없으면 false 리턴
				slist.add(new Student01(rs.getInt("id"), rs.getString("name"),
						rs.getInt("kgarde"), rs.getInt("mgarde"), rs.getInt("egarde")));		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return slist;
	}

}
