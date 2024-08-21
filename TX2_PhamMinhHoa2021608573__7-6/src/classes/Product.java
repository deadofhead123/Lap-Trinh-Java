package classes;

import java.io.Serializable;

/*
 * Để ghi được 1 đối tượng nào đó vào file với đầy đủ thông tin, 
   cần phải implement interface Serializable ở cả lớp cha và lớp kế thừa của nó.  
 */
public class Product implements Serializable{
	public static int cnt_id = 0;
	
	protected String product_id; // mã sản phẩm
	protected String product_name; // tên sản phẩm
	protected double product_price; //giá sản phẩm
	protected int product_total; // số lượng có
	
	// Constructors không tham số
	public Product() {}
	
	// Constructors có tham số
	public Product(String product_name, double product_price, int product_total) {
		this.product_id = "BDS" + Integer.toString(cnt_id++);
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_total = product_total;
	}

	// Getters và Setters
	public String getProduct_id() {
		return product_id;
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

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", product_price="
				+ product_price + ", product_total=" + product_total + "]";
	}
	
	
}
