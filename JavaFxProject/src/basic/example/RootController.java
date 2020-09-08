package basic.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable{
@FXML TableView<Student> tableView;
@FXML Button btnAdd, btnBarChart;


ObservableList<Student> list;
Stage primaryStage;

public void setPrimaryStage(Stage primaryStage) {
	this.primaryStage = primaryStage;
}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TableColumn<Student, ?> tc = tableView.getColumns().get(0); //첫번째칼럼
		tc.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("korean"));
		
		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("math"));
		
		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("english"));
		
		//성적저장
		list = FXCollections.observableArrayList();
		
		tableView.setItems(list);
		
		
		//add버튼 눌렀을시
		btnAdd.setOnAction(event-> handleBtnAddAction());
		btnBarChart.setOnAction(event-> handleBtnChartAction());
		
		tableView.setOnMouseClicked(event->{
			System.out.println(event);
			if(event.getClickCount()==2) {
				String selectedName = tableView.getSelectionModel().getSelectedItem().getName();
				handleDoubleClickAction(selectedName);
			}
		});
	
	} //end init
	
	public void handleDoubleClickAction(String name) {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);
		
		AnchorPane ap = new AnchorPane();
		ap.setPrefSize(210, 230);//컨테이너 크기지정
		
		Label lName, lKorean, lMath, lEnglish;
		TextField tName, tKorean, tMath, tEnglish;
		lName = new Label("이름"); //라벨위치
		lName.setLayoutX(35);
		lName.setLayoutY(35);
		
		lKorean = new Label("국어"); //라벨위치
		lKorean.setLayoutX(35);
		lKorean.setLayoutY(73);
		
		lMath = new Label("수학"); //라벨위치
		lMath.setLayoutX(35);
		lMath.setLayoutY(99);
		
		lEnglish = new Label("영어"); //라벨위치
		lEnglish.setLayoutX(35);
		lEnglish.setLayoutY(132);
		
		tName = new TextField();
		tName.setPrefWidth(110);
		tName.setLayoutX(72);
		tName.setLayoutY(32);
		
		tName.setText(name);
		tName.setEditable(false); //수정 못하도록
		
		tKorean = new TextField();
		tKorean.setPrefWidth(110);
		tKorean.setLayoutX(72);
		tKorean.setLayoutY(69);
		
		tMath = new TextField();
		tMath.setPrefWidth(110);
		tMath.setLayoutX(72);
		tMath.setLayoutY(95);
		
		tEnglish = new TextField();
		tEnglish.setPrefWidth(110);
		tEnglish.setLayoutX(72);
		tEnglish.setLayoutY(128);
		
		Button btnUpdate = new Button("수정");
		btnUpdate.setLayoutX(85);
		btnUpdate.setLayoutY(184);
		
		btnUpdate.setOnAction(event->{ //더블클릭해서 들어온창에서 수정버튼을 눌렀을때 이벤트
			for(int i=0; i<list.size(); i++) {
				if(list.get(i).getName().equals(name)) {
//					Student student = new Student(name, Integer.parseInt(tKorean.getText()),
//							Integer.parseInt(tMath.getText()),
//							Integer.parseInt(tEnglish.getText()));
//					
//					list.set(i, student);
							
					list.get(i).setKorean(Integer.parseInt(tKorean.getText()));
					list.get(i).setMath(Integer.parseInt(tMath.getText()));
					list.get(i).setEnglish(Integer.parseInt(tEnglish.getText()));
				}
			}
			stage.close();
		});
		
		for(Student stu : list) { 
			if(stu.getName().equals(name)) {
				tMath.setText(String.valueOf(stu.getMath()));
				tKorean.setText(String.valueOf(stu.getKorean()));
				tEnglish.setText(String.valueOf(stu.getEnglish()));
				
			}
		}
		
		ap.getChildren().addAll(lName, lKorean, lMath, lEnglish, 
				tName, tKorean, tMath, tEnglish, btnUpdate);
		
		Scene scene = new Scene(ap);
		stage.setScene(scene);
		stage.show();
		
		
	}

	private void handleBtnChartAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);
		
		try {
			Parent chart = FXMLLoader.load(getClass().getResource("BarChart.fxml"));
			Scene scene = new Scene(chart);
			stage.setScene(scene);
			stage.show();
			
			// chart 가지고와서 series 를 추가
			BarChart barChart = (BarChart) chart.lookup("#barChart"); //어디어디에있는 bar차트라고 명시적선언해줌
			XYChart.Series<String, Integer> seriesK = new XYChart.Series<String, Integer>();
			//시리즈 선언
			seriesK.setName("국어"); //시리즈(그래프) 이름
			ObservableList<XYChart.Data<String, Integer>> koreanList 
						= FXCollections.observableArrayList();//array 리스트생성
			for(int i =0; i<list.size(); i++) {
				koreanList.add(new XYChart.Data<>(list.get(i).getName(),
								                    list.get(i).getKorean()));
			}
			seriesK.setData(koreanList);
			barChart.getData().add(seriesK);
			
			XYChart.Series<String, Integer> seriesM = new XYChart.Series<String, Integer>();
			//시리즈 선언
			seriesM.setName("수학"); //시리즈(그래프) 이름
			ObservableList<XYChart.Data<String, Integer>> mathList 
						= FXCollections.observableArrayList(); //array 리스트생성
			for(int i =0; i<list.size(); i++) {
				mathList.add(new XYChart.Data<>(list.get(i).getName(),
								                    list.get(i).getMath()));
			}
			
			seriesM.setData(mathList);
			barChart.getData().add(seriesM);
			
			XYChart.Series<String, Integer> seriesE = new XYChart.Series<String, Integer>();
			//시리즈 선언
			seriesE.setName("영어"); //시리즈(그래프) 이름
			ObservableList<XYChart.Data<String, Integer>> englishList 
						= FXCollections.observableArrayList(); //array 리스트생성
			for(int i =0; i<list.size(); i++) {
				englishList.add(new XYChart.Data<>(list.get(i).getName(),
								                    list.get(i).getEnglish()));
			}
			
			seriesE.setData(englishList);
			barChart.getData().add(seriesE);
			
			Button btnClose = (Button) chart.lookup("#btnClose");
			btnClose.setOnAction(event-> stage.close());
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	//추가화면 보여주는 작업.
	private void handleBtnAddAction() { // btnAdd라는 버튼 을 눌렀을경우 메소드 호출
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow()); //새윈도우창
		
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("AddForm.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();
			
			//추가화면 컨트롤 사용하기
			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd"); 
			btnFormAdd.setOnAction(event-> {
					TextField txtName = (TextField) parent.lookup("#txtName");
					TextField txtKorean = (TextField) parent.lookup("#txtKorean");
					TextField txtMath = (TextField) parent.lookup("#txtMath");
					TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");
					
					Student student = new Student(txtName.getText(),
							Integer.parseInt(txtKorean.getText()),
							Integer.parseInt(txtMath.getText()),
							Integer.parseInt(txtEnglish.getText()));
					
					list.add(student);
					
					stage.close();
			});
			
			Button btnFormCancel = (Button) parent.lookup("#btnFormCancel");
			btnFormCancel.setOnAction(event -> { //취소버튼눌렀을경우 //1
				TextField txtName = (TextField) parent.lookup("#txtName");
				TextField txtKorean = (TextField) parent.lookup("#txtKorean");
				TextField txtMath = (TextField) parent.lookup("#txtMath");
				TextField txtEnglish = (TextField) parent.lookup("#txtEnglish"); 
				
				txtName.clear();
				txtKorean.clear();
				txtMath.clear();
				txtEnglish.clear();
			});
			
		}catch(IOException e) { //end try
			e.printStackTrace();
		}
	}
	

}
