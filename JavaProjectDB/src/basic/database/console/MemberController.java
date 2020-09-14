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
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MemberController implements Initializable{
@FXML TableView<Member> MtableView;
//btnMemberAdd = 회원등록, btnMemberDelete=회원삭제, btnMemberUpdate=회원수정
@FXML Button btnMemberAdd, btnMemberDelete, btnMemberUpdate;

ObservableList<Member> mlist;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TableColumn<Member, ?> tc = MtableView.getColumns().get(0); //첫번째칼럼
		tc.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		tc = MtableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("age"));

		tc = MtableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("phone"));

		tc = MtableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		mlist=FXCollections.observableArrayList();
		
		//회원정보 테이블을 list배열의 값을 받아 초기화 
		MtableView.setItems(mlist);
		
		//회원정보 컬럼 한번 클릭했을때 ~
		MtableView.setOnMouseClicked(event->{
			if(event.getClickCount()==1) {
				String stitle = MtableView.getSelectionModel().getSelectedItem().getName();
				handleOneClickAction(stitle);//삭제하기위해 메소드
			}
		});
		
		//회원등록 버튼 눌렀을
		btnMemberAdd.setOnAction(event->handlebtnMemberAdd());
		
		
	}

	private void handleOneClickAction(String stitle) {//회원정보 1번클릭했을때
		btnMemberDelete.setOnAction(event->{//1번 클릭하고 회원삭제 버튼 눌렀을
			for(int i=0; i<mlist.size();i++) {
				if(mlist.get(i).getName()==stitle) {
					mlist.remove(i);
					break;
				}
			}
		});
		
		btnMemberUpdate.setOnAction(evnet->{//칼럼 1번 클릭하고 회원 수정 버튼 눌렀을
			Stage stage = new Stage(StageStyle.UTILITY);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(btnMemberAdd.getScene().getWindow());
			
			try {
				Parent parent=FXMLLoader.load(getClass().getResource("MemberUpdate.fxml"));
				Scene s = new Scene(parent);
				stage.setTitle("회원 정보 수정");
				stage.setScene(s);
				stage.show();
				
				TextField txtName = (TextField) parent.lookup("#txtName");
				TextField txtAge = (TextField) parent.lookup("#txtAge");
				TextField txtPhone = (TextField) parent.lookup("#txtPhone");
				TextField txtEmail = (TextField) parent.lookup("#txtEmail");
				//수정창 들어왔을경우, 해당 수정할 칼럼의 값을 필드에 뿌려준다.
				for(Member m : mlist) {
					if(m.getName()==stitle) {
						txtName.setText(m.getName());
						txtAge.setText(String.valueOf(m.getAge()));
						txtPhone.setText(m.getPhone());
						txtEmail.setText(m.getEmail());
					}
				}
				//회원정보를 수정한뒤에 수정버튼 클릭했을경우 이벤트 정의
				Button btnMemberUpdate = (Button) parent.lookup("#btnMemberUpdate");
				btnMemberUpdate.setOnAction(event->{
					for(int i=0;i<mlist.size(); i++) {
						if(mlist.get(i).getName()==stitle) {
							Member m = new Member(txtName.getText(),
									Integer.parseInt(txtAge.getText()),
									txtPhone.getText(),
									txtEmail.getText());
							
							mlist.set(i, m);
							break;
									
						}
					}
					stage.close();
				});
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
	}//end 회원수정

	private void handlebtnMemberAdd() {//회원등록 버튼 눌렀을 이벤트 정
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnMemberAdd.getScene().getWindow());
		
		try {
			Parent parent =FXMLLoader.load(getClass().getResource("member.fxml"));
			Scene s = new Scene(parent);
			stage.setTitle("회원을 등록하세요.");
			stage.setScene(s);
			stage.show();
			
			TextField txtName = (TextField) parent.lookup("#txtName");
			TextField txtAge = (TextField) parent.lookup("#txtAge");
			TextField txtPhone = (TextField) parent.lookup("#txtPhone");
			TextField txtEmail = (TextField) parent.lookup("#txtEmail");
			
			Button btnMemberAdd= (Button) parent.lookup("#btnMemberAdd");
			btnMemberAdd.setOnAction(event->{
				if(txtName.getText() == null || txtName.getText().equals("")) {
					showPopup("이름을 입력하세요!!!");	
				}else if(txtAge.getText() == null || txtAge.getText().equals("")) {
					showPopup("나이를 입력하세요!!!");
				}else if(txtPhone.getText()==null || txtPhone.getText().equals("")) {
					showPopup("연락처를 입력하세요!!");
				}else if(txtEmail.getText()==null || txtEmail.getText().equals("")) {
					showPopup("이메일을입력하세요!!");
				}else {
					
				Member member = new Member(txtName.getText(), 
						Integer.parseInt(txtAge.getText()), 
						txtPhone.getText(),
						txtEmail.getText());
				
				//입력한 텍스트 리스트에추가, tableView에 값추가
				mlist.add(member);
				stage.close(); //회원정보 입력하고 등록버튼누르면 해당 창닫김.	
				}
			});
			
			//취소 눌렀을때 입력한값 초기화
			Button btnMemberInit=(Button) parent.lookup("#btnMemberInit");
			btnMemberInit.setOnAction(event->{
				txtName.clear();
				txtAge.clear();
				txtPhone.clear();
				txtEmail.clear();
				txtName.requestFocus();
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		pop.show(btnMemberAdd.getScene().getWindow());
		
	}

}
