package basic.database.console;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Member {
	private SimpleStringProperty id;
	private SimpleStringProperty password;
	private SimpleStringProperty name;
	private SimpleIntegerProperty age;
	private SimpleStringProperty phone;
	private SimpleStringProperty email;
	
//	public Member() {
//		id = new SimpleStringProperty();
//	}
	public Member(String id, String password, String name, int age, String phone, String email) {
		
		this.id = new SimpleStringProperty(id);
		this.password = new SimpleStringProperty(password);
		this.name = new SimpleStringProperty(name);
		this.age = new SimpleIntegerProperty(age);
		this.phone = new SimpleStringProperty(phone);
		this.email = new SimpleStringProperty(email);
	}
	

	public String getId() {
		return this.id.get();
	}
	
	public void setId(String id) {
		this.id.set(id);
	}
	
	public String getPassword() {
		return this.password.get();
	}
	
	public void setPassword(String password) {
		this.password.set(password);
	}
	
	public String getName() {
		return this.name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public int getAge() {
		return this.age.get();
	}
	
	public void setAge(int age) {
		this.age.set(age);
	}
	
	public String getPhone() {
		return this.phone.get();
	}
	
	public void setPhone(String phone) {
		this.phone.set(phone);
	}
	
	public String getEmail() {
		return this.email.get();
	}
	
	public void setEmail(String email) {
		this.email.set(email);
	}
	
	
}
