package basic.database.console;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Member {
	private SimpleStringProperty name;
	private SimpleIntegerProperty age;
	private SimpleStringProperty phone;
	private SimpleStringProperty email;
	
	public Member(String name, int age, String phone, String email) {
		super();
		this.name = new SimpleStringProperty(name);
		this.age = new SimpleIntegerProperty(age);
		this.phone = new SimpleStringProperty(phone);
		this.email = new SimpleStringProperty(email);
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
