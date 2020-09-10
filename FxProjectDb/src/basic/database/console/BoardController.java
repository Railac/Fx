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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BoardController implements Initializable{
	//btnMemberAdd=회원가입, btnLogin=접속(로그인)버튼
@FXML Button btnMemberAdd, btnLogin;

@FXML TextField txtId;
@FXML PasswordField txtPwd;

Connection conn = DBConnection.getConnection();
ObservableList<Member> mlist;
ObservableList<Member> list;
int i=0;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		mlist=FXCollections.observableArrayList();
		
		btnLogin.setOnAction(event->{
			if(txtId.getText() == null || txtId.getText().equals("")) {//start if1
				showPopup("아이디를 입력하세요!!!");
			}else if(txtPwd.getText() == null || txtPwd.getText().equals("")) {
				showPopup("패스워드를 입력하세요!!!");
			}else {			
				list=FXCollections.observableArrayList();
				list=getMemberList();
			
			for(i=0;i<list.size(); i++) {//start for1
				
				//start if 2
				if(list.get(i).getId().equals(txtId.getText()) 
						&& list.get(i).getPassword().equals(txtPwd.getText())) {
					System.out.println("로그인 되었습니다.");
					//start if3
					if(list.get(i).getId().equals("sys")) {
						Stage stage = new Stage(StageStyle.UTILITY);
						stage.initModality(Modality.WINDOW_MODAL);
						stage.initOwner(btnMemberAdd.getScene().getWindow()); 
						
						try {//관리자 도서등록창
							Parent parent=FXMLLoader.load(getClass().getResource("bookList.fxml"));
							Scene s = new Scene(parent);
							stage.setScene(s);
							stage.show();
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}else{
						Stage stage = new Stage(StageStyle.UTILITY);
						stage.initModality(Modality.WINDOW_MODAL);
						stage.initOwner(btnMemberAdd.getScene().getWindow());
						
						try {
							Parent parent=FXMLLoader.load(getClass().getResource("borrowList.fxml"));
							Scene s = new Scene(parent);
							stage.setScene(s);
							stage.show();
						} catch (IOException e) {
							
							e.printStackTrace();
						}
					}//end if3
					
					
				}else {
					
				}//end if2
				
			    }//end for1
			
			if(list.size()==i) {
				
				System.out.println("다시 입력하세요.");
			}
				
			} //end if1
			
			
		});
		
		//회원가입 버튼 눌렀을경우
		btnMemberAdd.setOnAction(event->{
			Stage stage = new Stage(StageStyle.UTILITY);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(btnMemberAdd.getScene().getWindow()); 
			
			try {
				Parent parent =FXMLLoader.load(getClass().getResource("memberAdd.fxml"));
				Scene s = new Scene(parent);
				stage.setTitle("회원가입 창");
				stage.setScene(s);
				stage.show();
				
				TextField txtId = (TextField) parent.lookup("#txtId");
				PasswordField txtPwd = (PasswordField) parent.lookup("#txtPwd");
				TextField txtName = (TextField) parent.lookup("#txtName");
				TextField txtAge = (TextField) parent.lookup("#txtAge");
				TextField txtPhone = (TextField) parent.lookup("#txtPhone");
				TextField txtEmail = (TextField) parent.lookup("#txtEmail");
				
				//회원가입창에서 등록버튼 눌렀을경우
				Button btnMemberAdd = (Button) parent.lookup("#btnMemberAdd");
				btnMemberAdd.setOnAction(event1 ->{
					if(txtId.getText() == null || txtId.getText().equals("")) {
						showPopup("아이디를 입력하세요!!!");
				    }else if(txtPwd.getText() == null || txtPwd.getText().equals("")) {
						showPopup("패스워드를 입력하세요!!!");
				    }else if(txtName.getText() == null || txtName.getText().equals("")) {
						showPopup("이름을 입력하세요!!!");	
					}else if(txtAge.getText() == null || txtAge.getText().equals("")) {
						showPopup("나이를 입력하세요!!!");
					}else if(txtPhone.getText()==null || txtPhone.getText().equals("")) {
						showPopup("연락처를 입력하세요!!");
					}else if(txtEmail.getText()==null || txtEmail.getText().equals("")) {
						showPopup("이메일을입력하세요!!");
					}else {
					Member m = new Member(txtId.getText(), 
									      txtPwd.getText(),
									      txtName.getText(),
									      Integer.parseInt(txtAge.getText()),
									      txtPhone.getText(),
									      txtEmail.getText());
					
					mlist.add(m);
					
					stage.close();
					}
				});//end 회원등록
				
				//회원가입창에서 취소버튼 눌렀을경우
				Button btnMemberCancel = (Button) parent.lookup("#btnMemberCancel");
				btnMemberCancel.setOnAction(event1->{
					txtId.clear();
					txtPwd.clear();
					txtName.clear();
					txtAge.clear();
					txtPhone.clear();
					txtEmail.clear();
					txtId.requestFocus();
					
				});//end 회원취소
				
				//회원가입창에서 종료버튼 눌렀을경우
				Button btnMemberExit = (Button) parent.lookup("#btnMemberExit");
				btnMemberExit.setOnAction(event1->stage.close());
				

			} catch (IOException e) {
				e.printStackTrace();
			}
		}); //end 회원가입 버튼
		
	}
	
	//회원정보 조회창
	private ObservableList<Member> getMemberList() {
		String sql = "select *" 
				+"\n" + " from member";
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Member m = new Member(rs.getString("id"), rs.getString("password"),
						rs.getString("name"), rs.getInt("age"),
						rs.getString("phone"), rs.getString("email"));
					
				list.add(m);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	} //end 회원정보 조회 메소드

	//정보입력안했을경우 팝업창
	private void showPopup(String msg) {
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
		pop.show(btnMemberAdd.getScene().getWindow());
		
	}
	

}
