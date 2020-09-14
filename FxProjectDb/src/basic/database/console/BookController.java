package basic.database.console;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BookController implements Initializable{
	//btnAdd = 도서등록, btnDelete = 도서삭
@FXML Button btnBookAdd, btnDelete; 
	//tableView =등록한 도서 리스트 출력
@FXML TableView<Book> tableView;

ObservableList<Book> list;

Connection conn = DBConnection.getConnection();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TableColumn<Book, ?> tc = tableView.getColumns().get(0); //첫번째칼럼
		tc.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("author"));

		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("publisher"));

		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		list=FXCollections.observableArrayList();
		list=getBookList();
		
		tableView.setItems(list);
		
		//테이블 클릭시 이벤트 정
		tableView.setOnMouseClicked(event->{
			String stitle;
			if(event.getClickCount()==2) {
//				stitle = tableView.getSelectionModel().getSelectedItem().getTitle();
//				handleDoubleClickAction(stitle);
			}else if(event.getClickCount()==1) {
					stitle = tableView.getSelectionModel().getSelectedItem().getTitle();
					handleOneClickAction(stitle);
			}
		});
		
		//도서등록 버튼 클릭시 이벤트 정의
		btnBookAdd.setOnAction(event->{
			Stage stage = new Stage(StageStyle.UTILITY);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(btnBookAdd.getScene().getWindow());
			
			try {//도서 등록창
				Parent parent= FXMLLoader.load(getClass().getResource("BookAdd.fxml"));
				Scene s = new Scene(parent);
				stage.setScene(s);
				stage.setTitle("도서 등록 창");
				stage.show();
				
				TextField txtTitle = (TextField) parent.lookup("#txtTitle");
				TextField txtAuthor = (TextField) parent.lookup("#txtAuthor");
				TextField txtPublisher= (TextField) parent.lookup("#txtPublisher");
				TextField txtPrice = (TextField) parent.lookup("#txtPrice");
				
				//도서 정보 입력후 등록버튼 누를때 이벤트 정의
				Button btnBookAdd1 =(Button) parent.lookup("#btnBookAdd1");
				btnBookAdd1.setOnAction(event1->{
					Book book = new Book(txtTitle.getText(),
										 txtAuthor.getText(),
										 txtPublisher.getText(),
										 Integer.parseInt(txtPrice.getText()));
					
					
					setBookInsert(book);
					list.add(book);
				});
				//도서 등록창에서 입력하던 정보취소하고싶을때 취소버튼 누르면 초기화
				Button btnBookInit = (Button) parent.lookup("#btnBookInit");
				btnBookInit.setOnAction(event2->{
					txtTitle.clear();
					txtAuthor.clear();
					txtPublisher.clear();
					txtPrice.clear();
				});
				// 도서정보 등록창에서 종료버튼 누르면 등록창 닫음 
				Button btnBookCancel = (Button) parent.lookup("#btnBookCancel");
				btnBookCancel.setOnAction(event4->stage.close());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tableView.setItems(list);
		});
		
	}
	
	// 테이블 1번 클릭시 이벤트 정의
	private void handleOneClickAction(String stitle) {
		btnDelete.setOnAction(event->{ //1번 클릭후 도서삭제 버튼 클릭시 이벤트 정의
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getTitle()==stitle) {
					setBookDelete(stitle);
					list.remove(i);
					break;
				}
			}
		});
		
	}

	private void setBookDelete(String title) {
		String sql = "delete from book" 
				+"\n"+" where title = " +title;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int r = pstmt.executeUpdate();
			System.out.println(r + " 건 삭제되었습니다.");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	//도서 정보 입력 !!!!!!!!!!!!!!!!!!!!!, 도서등
	private void setBookInsert(Book bk) {		
		String sql = "insert into book(title, author, publisher, price, borrow)"
				+"\n"+" values(\'"+bk.getTitle()+"\',\'"+bk.getAuthor()+"\',\'"
				+bk.getPublisher()+"\',\'"+bk.getPrice()+"\',\'가능\')";
		
		try {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int r = pstmt.executeUpdate();
		System.out.println(r + "건 입력되었습니다.");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	// 테이블에 등록한 도서목록 조회
	private ObservableList<Book> getBookList(){
		String sql = "select * "
				+'\n'+"from book";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Book book = new Book(
						rs.getString("title"),
						rs.getString("author"),
						rs.getString("publisher"),
						rs.getInt("price"));
				
				list.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
		
	}

}
