import java.util.Date;

public class Vehicle {	//Defining vehicle class.
	
	//Defining field instances.
	private String vin;
	private String type;
	private String brand;
	private String model;
	private String color;
	private int year;
	private Date rDate;
	
    public Vehicle(String vin, String type, String brand, String model,
    		String color, int year, Date rDate) {
    	this.vin = vin;
    	this.type = type;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.year = year;
        this.rDate = rDate;
    }
    
    public String getVin() {
        return vin;
    }

    public String getType() {
        return type;
    }
    
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }
    
    public String getColor() {
        return color;
    }
    
    public int getYear() {
        return year;
    }
    
    public Date getRegistrationDate() {
        return rDate;
    }
    
	/*@Override
	public String toString() {
		return "Type: " + type + "\n" + "\n" + "Brand: " + brand + "\n" + "\n" + "Model: " + model + "\n" + "\n" + "Color: " + color + "\n" + "\n" + "Year: " + year;
	}*/
	
}
