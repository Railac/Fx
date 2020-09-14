package basic.database.console;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
	private SimpleStringProperty title; //도서
	private SimpleStringProperty author; //저자
	private SimpleStringProperty publisher; //출판사
	private SimpleIntegerProperty price; //가격
	private SimpleStringProperty borrow; //대출여부
	private SimpleStringProperty borrowdate ; //반납일
	
	public Book(String title, String author, String publisher, int price) {
		this.title = new SimpleStringProperty(title);
		this.author = new SimpleStringProperty(author);
		this.publisher = new SimpleStringProperty(publisher);
		this.price = new SimpleIntegerProperty(price);
	}
	
	public Book(String title, String borrow, String borrowdate) {
		this.title = new SimpleStringProperty(title);
		this.borrow = new SimpleStringProperty(borrow);
		this.borrowdate = new SimpleStringProperty(borrowdate);
	}
	
	public String getTitle() {
		return this.title.get();
	}
	public void setTitle(String title) {
		this.title.set(title);
	}
	
	public String getAuthor() {
		return this.author.get();
	}
	public void setAuthor(String author) {
		this.author.set(author);
	}
	public String getPublisher() {
		return this.publisher.get();
	}
	public void setPublisher(String publisher) {
		this.publisher.set(publisher);
	}
	public int getPrice() {
		return this.price.get();
	}
	public void setPrice(int price) {
		this.price.set(price);
	}
	public String getBorrow() {
		return this.borrow.get();
	}
	public void setBorrow(String borrow) {
		this.borrow.set(borrow);
	}
	
	public String getBorrowdate() {
		return this.borrowdate.get();
	}
	public void setBorrowdate(String borrowdate) {
		this.borrowdate.set(borrowdate);
	}
	
}
