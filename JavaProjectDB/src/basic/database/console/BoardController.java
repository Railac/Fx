package basic.database.console;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BoardController implements Initializable{
	//btnAdd=도서등록 , btnDelete=도서삭제, btnMember=회원정보, btnBookborrow=도서대출
@FXML Button btnAdd, btnDelete, btnMember, btnBookborrow;
@FXML TableView<Book> tableView;

ObservableList<Book> list;
ObservableList<Book> list2;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TableColumn<Book, ?> tc = tableView.getColumns().get(0); //첫번째칼럼
		tc.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("author"));

		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("publisher"));

		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		//도서정보 등록하는 리스트
		list= FXCollections.observableArrayList(); 
		
		//첫화면 리스트 초기화
		tableView.setItems(list);
		
		//테이블 칼럼 더블클릭했을시 발생이벤트
		tableView.setOnMouseClicked(event->{
			String stitle;
			if(event.getClickCount()==2) {
				stitle = tableView.getSelectionModel().getSelectedItem().getTitle();
				handleDoubleClickAction(stitle);
			}else if(event.getClickCount()==1) {
					stitle = tableView.getSelectionModel().getSelectedItem().getTitle();
					handleOneClickAction(stitle);
			}
		});
		
		//도서등록 눌렀을때
		btnAdd.setOnAction(event->handlebtnAdd());
		
		//회원정보 보기
		btnMember.setOnAction(event->handleBtnMember());
		
		//도서대출
		btnBookborrow.setOnAction(event->handleBtnborrow());
		
		
	}
	//도서대출 버튼눌렀을 이벤트 정의
	private void handleBtnborrow() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());
		
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("BookBorrow.fxml"));
			Scene s = new Scene(parent);
			stage.setTitle("도서대출창");
			stage.setScene(s);
			stage.show();	
			
			TableView<Book> tableView1 = (TableView) parent.lookup("#tableView1");
			
			TableColumn<Book, ?> tc = tableView1.getColumns().get(0); //첫번째칼럼
			tc.setCellValueFactory(new PropertyValueFactory<>("title"));
			
			tc = tableView1.getColumns().get(1);
			tc.setCellValueFactory(new PropertyValueFactory<>("borrow"));

			tc = tableView1.getColumns().get(2);
			tc.setCellValueFactory(new PropertyValueFactory<>("borrowdate"));
			
			list2=FXCollections.observableArrayList();
			for(int i=0; i<list.size(); i++) {
				Book book = new Book(list.get(i).getTitle(), "가능", "");
				list2.add(book);
			}
			tableView1.setItems(list2);

			//대출 테이블 뷰의 칼럼 클릭했을때 이벤트 정
			tableView1.setOnMouseClicked(event->{
				if(event.getClickCount()==1) { //한번 클릭했을
					String stitle = tableView1.getSelectionModel().getSelectedItem().getTitle();
					//대출 테이블 칼럼 클릭후 대출 버튼눌렀을경
					Button btnBookBorrow =  (Button) parent.lookup("#btnBookBorrow");
					
					btnBookBorrow.setOnAction(event1->{
						Stage stage1 = new Stage(StageStyle.UTILITY);
						stage1.initModality(Modality.WINDOW_MODAL);
						stage1.initOwner(btnAdd.getScene().getWindow());
						
						
						try {
							Parent p2 = FXMLLoader.load(getClass().getResource("BorrowAdd.fxml"));
							Scene s1 = new Scene(p2);
							stage1.setScene(s1);
							stage1.show();
							
							
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					});
				}
			});
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//대출 테이블뷰 클릭 한번해서 넘어왔다 ~
	private void handleBtnOneClick(String stitle) {
		
		
	}
	//회원정보 버튼 눌렀을때 이벤트정
	private void handleBtnMember() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());
		
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("memberList.fxml"));
			Scene s = new Scene(parent);
			stage.setTitle("회원 정보창");
			stage.setScene(s);
			stage.show();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	private void showPopup(String msg) { // 등록정보 입력안할때
		HBox hbox = new HBox();
		hbox.setStyle("-fx-background-color: black; -fx-background-radius: 20;");
		hbox.setAlignment(Pos.CENTER);

		Label label = new Label();
		label.setText(msg);
		label.setStyle("-fx-text-fill: yellow; ");
		hbox.getChildren().addAll(label);

		Popup pop = new Popup();
		pop.getContent().add(hbox);
		pop.setAutoHide(true);
		pop.show(btnAdd.getScene().getWindow());
		
	}

	private void handleOneClickAction(String stitle) {//테이블의 임의의 칼럼 한번클릭시
		btnDelete.setOnAction(event->{
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getTitle()==stitle) {
					list.remove(i);
					break;
				}
			}
		});
		
	}

	private void handleDoubleClickAction(String stitle) {//테이블의 임의의 칼럼 더블클릭시
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());
		
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("BookUpdate.fxml"));
			Scene s = new Scene(parent);
			stage.setTitle("도서수정하세요.");
			stage.setScene(s);
			stage.show();
			
			TextField txtTitle = (TextField) parent.lookup("#txtTitle");
			TextField txtAuthor = (TextField) parent.lookup("#txtAuthor");
			TextField txtPublisher = (TextField) parent.lookup("#txtPublisher");
			TextField txtPrice = (TextField) parent.lookup("#txtPrice");
			
			txtAuthor.requestFocus();
			//수정창열렸을때 해당수정할행 값받아오기
			for(Book book : list) {
				if(book.getTitle()==stitle) {
					txtTitle.setText(book.getTitle());
					txtAuthor.setText(book.getAuthor());
					txtPublisher.setText(book.getPublisher());
					txtPrice.setText(String.valueOf(book.getPrice()));
				}
			}
			Button btnBookUpdate = (Button) parent.lookup("#btnBookUpdate");
			//수정하기
			btnBookUpdate.setOnAction(event->{
				for(int i=0;i<list.size();i++) {
					if(list.get(i).getTitle()==stitle) {
						Book book = new Book(txtTitle.getText(), txtAuthor.getText(), txtPublisher.getText()
								,Integer.parseInt(txtPrice.getText()));
						
						list.set(i, book);
						break;
					}
				}
				stage.close();
			});
			Button btnBookUpdateExit = (Button) parent.lookup("#btnBookUpdateExit");
			btnBookUpdateExit.setOnAction(event-> stage.close());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	private void handlebtnAdd() {//도서등록
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());
		
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("BookAdd.fxml"));
			Scene s = new Scene(parent);
			stage.setTitle("도서등록하세요.");
			stage.setScene(s);
			stage.show();
			
			TextField txtTitle = (TextField) parent.lookup("#txtTitle");
			TextField txtAuthor = (TextField) parent.lookup("#txtAuthor");
			TextField txtPublisher = (TextField) parent.lookup("#txtPublisher");
			TextField txtPrice = (TextField) parent.lookup("#txtPrice");
			//등록버튼 눌렀을경우
			Button btnBookAdd = (Button) parent.lookup("#btnBookAdd");
			btnBookAdd.setOnAction(event->{
				if(txtTitle.getText() == null || txtTitle.getText().equals("")) {
					showPopup("도서명을 입력하세요!!!");	
				}else if(txtAuthor.getText() == null || txtAuthor.getText().equals("")) {
					showPopup("저자를 입력하세요!!!");
				}else if(txtPublisher.getText()==null || txtPublisher.getText().equals("")) {
					showPopup("출판사를 입력하세요!!");
				}else if(txtPrice.getText()==null || txtPrice.getText().equals("")) {
					showPopup("가격을 입력하세요!!");
				}else {
				Book book = new Book(txtTitle.getText(), txtAuthor.getText(), txtPublisher.getText()
						,Integer.parseInt(txtPrice.getText()));
				
				//입력한 텍스트 리스트에추가, tableView에 값추가
				list.add(book);
				txtTitle.clear();
				txtAuthor.clear();
				txtPublisher.clear();
				txtPrice.clear();
				txtTitle.requestFocus();
				}
				
			});
			//취소(초기화)버튼 눌렀을경우
			Button btnBookInit = (Button) parent.lookup("#btnBookInit");
			btnBookInit.setOnAction(event->{
				
				txtTitle.clear();
				txtAuthor.clear();
				txtPublisher.clear();
				txtPrice.clear();	
			});
			//종료 버튼 눌렀을때
			Button btnBookCancel = (Button) parent.lookup("#btnBookCancel");
			btnBookCancel.setOnAction(event->{
				stage.close();
			});
			
			
					
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
