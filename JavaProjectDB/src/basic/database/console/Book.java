package basic.database.console;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
	private SimpleStringProperty title;
	private SimpleStringProperty author;
	private SimpleStringProperty publisher;
	private SimpleIntegerProperty price;
	
	public Book(String title, String author, String publisher, int price) {
		this.title = new SimpleStringProperty(title);
		this.author = new SimpleStringProperty(author);
		this.publisher = new SimpleStringProperty(publisher);
		this.price = new SimpleIntegerProperty(price);
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
	
	
}
