package com.dong.test;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student01 {
	private SimpleIntegerProperty id; //시퀀스
	private SimpleStringProperty name;
	private SimpleIntegerProperty korean;
	private SimpleIntegerProperty math;
	private SimpleIntegerProperty english;
	
	public Student01(int id, String name, int korean, int math, int english) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.korean = new SimpleIntegerProperty(korean);
		this.math = new SimpleIntegerProperty(math);
		this.english = new SimpleIntegerProperty(english);
	}
	public SimpleIntegerProperty idProperty() {
		return this.id;
	}
	

	public int getId() {
		return this.id.get();
	}
	

	public void setId(int id) {
		this.id.set(id);
	}
	
	public SimpleStringProperty nameProperty() {
		return this.name;
	}
	

	public String getName() {
		return this.name.get();
	}
	

	public void setName(String name) {
		this.name.set(name);
	}
	

	public SimpleIntegerProperty koreanProperty() {
		return this.korean;
	}
	

	public int getKorean() {
		return this.korean.get();
	}
	

	public void setKorean(int korean) {
		this.korean.set(korean);
	}
	

	public SimpleIntegerProperty mathProperty() {
		return this.math;
	}
	

	public int getMath() {
		return this.math.get();
	}
	

	public void setMath(int math) {
		this.math.set(math);
	}
	

	public SimpleIntegerProperty englishProperty() {
		return this.english;
	}
	

	public int getEnglish() {
		return this.english.get();
	}
	

	public void setEnglish(int english) {
		this.english.set(english);
	}
}
