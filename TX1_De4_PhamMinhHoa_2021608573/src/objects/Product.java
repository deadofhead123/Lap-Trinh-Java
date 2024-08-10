package objects;

import java.util.Scanner;

public class Product {
	static Scanner sc = new Scanner(System.in);
	
	protected String product_id; // mã sản phẩm
	protected String product_name; // tên sản phẩm
	protected double product_price; //giá sản phẩm
	protected int product_total; // số lượng có
	
	// Constructors không tham số
	public Product() {}
	
	// Constructors có tham số
	public Product(String product_id, String product_name, double product_price, int product_total) {
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_total = product_total;
	}

	// Getters và Setters
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	public int getProduct_total() {
		return product_total;
	}
	public void setProduct_total(int product_total) {
		this.product_total = product_total;
	}
	
	// Phương thức xuất
	public String toString() {
		return "product_id= " + product_id + ", product_name= " + product_name 
			+ ", product_price= " + product_price + ", product_total=" + product_total;
	}
	
	public void Provide_Input() {	
		System.out.print("Nhập product_name: ");
		product_name = sc.nextLine();
		System.out.print("Nhập product_price: ");
		product_price = sc.nextDouble();
		System.out.print("Nhập product_total: ");
		product_total = sc.nextInt();
		
		sc.nextLine();
	}
}
