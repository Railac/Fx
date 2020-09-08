package com.dong.test;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import basic.common.db.ConnectionDB;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppController implements Initializable {
	Connection conn = ConnectionDB.getDB();
	ObservableList<Student01> slist;

	@FXML
	TableView<Student01> tableView;
	@FXML
	Button btnAdd, btnBarChart;

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

		tableView.setItems(getStudentList()); // 테이블뷰 초기화(초기화할값)

		btnAdd.setOnAction(event -> handleBtnAddAction());
		btnBarChart.setOnAction(event -> handleBtnBarAction());
	}// end init

	private void handleBtnAddAction() { // add버튼 클릭했을때 이벤트
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());// 새로운 stage 생성

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("AddForm.fxml"));
			TextField txtName = (TextField) parent.lookup("#txtName");
			TextField txtKorean = (TextField) parent.lookup("#txtKorean");
			TextField txtMath = (TextField) parent.lookup("#txtMath");
			TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");

			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd");
			// parent의 btnformadd라는 아이디랑매칭(버튼이라고 명시해줘야됨), save버튼
			Button btnFormCancel = (Button) parent.lookup("#btnFormCancel");
			// parent의 btnFormCancel 라는 id매칭 시켜서 찾아옴, (버튼이라고 명시해줘야됨),cancel버튼

			btnFormAdd.setOnAction(event -> {

				String sql = "insert into chart values(chart_sq.NEXTVAL,?,?,?,?)";
				try {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, txtName.getText());
					pstmt.setInt(2, Integer.parseInt(txtKorean.getText()));
					pstmt.setInt(3, Integer.parseInt(txtMath.getText()));
					pstmt.setInt(4, Integer.parseInt(txtEnglish.getText()));
					int r = pstmt.executeUpdate();
					System.out.println(r + " 건 입력됨.");
				} catch (SQLException e) {

					e.printStackTrace();
				}
				tableView.setItems(getStudentList()); // tableView 리셋
				stage.close(); // 창닫기
			});

			btnFormCancel.setOnAction(event -> { // cancel 버튼 클릭시 , 텍스트필드초기화
				txtName.clear();
				txtKorean.clear();
				txtMath.clear();
				txtEnglish.clear();
			});

			Scene s = new Scene(parent);
			stage.setScene(s);
			stage.show(); // 새창 띠우기
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void handleBtnBarAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());// 새로운 stage 생성
		try {
			Parent Chart = FXMLLoader.load(getClass().getResource("BarChart.fxml"));// 새창 띠움
			BarChart barChart = (BarChart) Chart.lookup("#barChart");
			Button btnClose	= (Button) Chart.lookup("#btnClose");

			// 국어,영어,수학 점수를받는 3개의 series 차트 생성한다
			// 국어
			XYChart.Series<String, Integer> seriesK = new XYChart.Series();
			seriesK.setName("국어");
			ObservableList<XYChart.Data<String, Integer>> kList = FXCollections.observableArrayList();
			System.out.println(slist.size());
			for (int i = 0; i < slist.size(); i++) {
				kList.add(new XYChart.Data<>(slist.get(i).getName(), slist.get(i).getKorean()));
			}
			seriesK.setData(kList);
			barChart.getData().add(seriesK);
			// 수학
			XYChart.Series<String, Integer> seriesM = new XYChart.Series();
			seriesM.setName("수학");
			ObservableList<XYChart.Data<String, Integer>> mList = FXCollections.observableArrayList();
			System.out.println(slist.size());
			for (int i = 0; i < slist.size(); i++) {
				mList.add(new XYChart.Data<>(slist.get(i).getName(), slist.get(i).getMath()));
			}
			seriesM.setData(mList);
			barChart.getData().add(seriesM);
			// 영어
			XYChart.Series<String, Integer> seriesE = new XYChart.Series();
			seriesE.setName("영어");
			ObservableList<XYChart.Data<String, Integer>> eList = FXCollections.observableArrayList();
			System.out.println(slist.size());
			for (int i = 0; i < slist.size(); i++) {
				eList.add(new XYChart.Data<>(slist.get(i).getName(), slist.get(i).getEnglish()));
			}
			seriesE.setData(eList);
			barChart.getData().add(seriesE);
			
			btnClose.setOnAction(event -> stage.close());

			Scene s = new Scene(Chart);
			stage.setScene(s);
			stage.show(); // 새창 띠우기
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
	}

	private ObservableList<Student01> getStudentList() { // 조회 메소드
		String sql = "select * from chart";
		slist = FXCollections.observableArrayList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { // rs.next 는 rs에 값이있으면 true, 없으면 false 리턴
				slist.add(new Student01(rs.getInt("id"), rs.getString("name"), rs.getInt("kgarde"), rs.getInt("mgarde"),
						rs.getInt("egarde")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return slist;
	}

}
