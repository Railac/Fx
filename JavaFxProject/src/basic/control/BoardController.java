package basic.control;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BoardController implements Initializable{
@FXML TableView<Board> boardView;
@FXML TextField txtTitle;
@FXML ComboBox<String> comboPublic;
@FXML TextField txtExitDate; //오류
@FXML TextArea txtContent;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TableColumn<Board, String> tcTitle = new TableColumn<>("제목");
		tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));//칼럼이름
		tcTitle.setPrefWidth(80);		
		boardView.getColumns().add(tcTitle);
		
		TableColumn<Board, String> tcPublicity = new TableColumn<>("공개여부");
		tcPublicity.setCellValueFactory(new PropertyValueFactory<>("publicity"));//칼럼이름
		tcPublicity.setPrefWidth(80);		
		boardView.getColumns().add(tcPublicity);
		
		boardView.setItems(getBoardList());
		
		boardView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Board>() {

			@Override
			public void changed(ObservableValue<? extends Board> observable, Board oldValue, Board newValue) {
				txtTitle.setText(newValue.getTitle());
				comboPublic.setValue(newValue.getPublicity());
				txtExitDate.setText(newValue.getExitDate());
				txtContent.setText(newValue.getContent());
				
			}
		});
		
	}
	
	public ObservableList<Board> getBoardList() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr", passwd = "hr";
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, passwd);
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Database에 연결되었습니다.\n");
		
		String sql = "select * from new_board order by 1";
		ObservableList<Board> list = FXCollections.observableArrayList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board(rs.getString("title"),
						rs.getString("password"),
						rs.getString("publicity"),
						rs.getString("exit_date"),
						rs.getString("content"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
}
