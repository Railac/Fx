package basic.control;


import javafx.beans.property.SimpleStringProperty;


public class Phone {
	
	private SimpleStringProperty smartPhone;
	private SimpleStringProperty image;
	
	public Phone(String smartPhone, String image) {
		this.smartPhone = new SimpleStringProperty(smartPhone);
		this.image = new SimpleStringProperty(image);
	}

	public String getSmartPhone() {
		return this.smartPhone.get();
	}

	public void setSmartPhone(String smartPhone) {
		this.smartPhone.set(smartPhone);
	}

	public String getImage() {
		return this.image.get();
	}

	public void setImage(String image) {
		this.image.set(image);
	}
	
	
//	StringProperty model;
//	IntegerProperty price;
//	
//	public void setModel(String model) {
//		this.model.set(model);
//	}
//	public String getModel() {
//		return this.model.get();
//	}
//	public StringProperty modelProperty() {
//		return this.model;
//	}
}
