package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Housing extends Product implements HousingManager, Serializable{
	public static ArrayList<Housing> List_housing = new ArrayList<>();   
	
    private String housing_type; // Căn hộ - chung cư, căn hộ Dual-key, căn hộ Loft...
    private double housing_acreage; // Diện tích đất
    private int housing_year; // Năm cấp sổ đỏ

    // Constructor
    public Housing() {}
    public Housing(String product_name, double product_price, int product_total,
                   String housing_type, double housing_acreage, int housing_year) {
        super(product_name, product_price, product_total);

        this.housing_type = housing_type;
        this.housing_acreage = housing_acreage;
        this.housing_year = housing_year;
    }

    // Getters và Setters
    public String getHousing_type() {
        return housing_type;
    }
    public void setHousing_type(String housing_type) {
        this.housing_type = housing_type;
    }

    public double getHousing_acreage() {
        return housing_acreage;
    }
    public void setHousing_acreage(double housing_acreage) {
        this.housing_acreage = housing_acreage;
    }

    public int getHousing_year() {
        return housing_year;
    }
    public void setHousing_year(int housing_year) {
        this.housing_year = housing_year;
    }

    
    @Override
	public String toString() {
		return "Housing [housing_type=" + super.toString() + housing_type + ", housing_acreage=" + housing_acreage + ", housing_year="
				+ housing_year + "]";
	}
    
    
	// Thêm mới bất động sản 
    public boolean addHousing(Housing h) {     
        List_housing.add(h);
        
        return List_housing.contains(h);
    }
    
    // Sửa thông tin bất động sản
    public boolean editHousing(Housing h) {
        return List_housing.contains(h);
    }

    // Xóa bất động sản
    public boolean deleteHousing(Housing h) {
    	List_housing.remove(h);
        return List_housing.contains(h);
    }
    
    // Tìm kiếm bất động sản theo các tiêu chí
 	public List<Housing> searchHousing(String name) {
 		 String[] spl = name.split(" ");
 		  		 
 		 // spl[cuối] sẽ lưu lựa chọn tìm kiếm
 		 
 		 // Xử lí trường hợp giá trị tìm kiếm có dấu cách
 		 String value_get = "", value_find;
 		 
 		 for(int i = 0 ; i < spl.length - 1 ; i++) {
 			value_get += spl[i];
 			
 			if(i != spl.length - 2 ) value_get += " ";
 		 }
 		 
 		 
 		 // a e i o u
 		 // Xử lí từ khóa tiếng Việt:
 		 // Sử dụng cắt chuỗi để thay thế ký tự:(
// 		 for(int i = 0 ; i < value_get.length() ; i++) {
// 			 if(value_get.charAt(i) == 'à' || value_get.charAt(i) == 'á' || value_get.charAt(i) == 'ạ' || value_get.charAt(i) == 'ă' 
// 					 || value_get.charAt(i) == 'ằ' || value_get.charAt(i) == 'ắ' || value_get.charAt(i) == 'ặ') {
// 				value_get = value_get.substring(0, i) + "a" + value_get.substring(i + 1);
// 			 }
// 			 else if(value_get.charAt(i) == 'è' || value_get.charAt(i) == 'ẹ' || value_get.charAt(i) == 'é' ) {
// 				value_get = value_get.substring(0, i) + "e" + value_get.substring(i + 1);
// 			 }
// 			 else if(value_get.charAt(i) == 'ì' || value_get.charAt(i) == 'í' || value_get.charAt(i) == 'ị' ) {
// 				value_get = value_get.substring(0, i) + "i" + value_get.substring(i + 1);
// 			 }
// 			 else if( value_get.charAt(i) == 'ò' || value_get.charAt(i) == 'ó' || value_get.charAt(i) == 'ọ' || value_get.charAt(i) == 'ơ' 
// 					 || value_get.charAt(i) == 'ở' || value_get.charAt(i) == 'ờ' || value_get.charAt(i) == 'ớ' || value_get.charAt(i) == 'ợ') {
// 				value_get = value_get.substring(0, i) + "o" + value_get.substring(i + 1);
// 			 }
// 			 else if( value_get.charAt(i) == 'ù' || value_get.charAt(i) == 'ú' || value_get.charAt(i) == 'ụ' ||
// 					value_get.charAt(i) == 'ừ' || value_get.charAt(i) == 'ứ' || value_get.charAt(i) == 'ự' ) {
// 				value_get = value_get.substring(0, i) + "u" + value_get.substring(i + 1);
// 			 }
// 		 }
 		 	 
 		 value_find = value_get;

 		 
         //Tìm kiếm theo tên
         if( spl[spl.length - 1].equals("0")) {
             return List_housing.stream()
                     .filter(x->x.getProduct_name().contains(value_find) ).collect(Collectors.toList());
         }

         // Tìm kiếm theo giá
         else if( spl[spl.length - 1].equals("1")) {
             return List_housing.stream().
                     filter(x->x.getProduct_price() == Double.parseDouble(value_find) ).collect(Collectors.toList());
         }

         // Tìm kiếm theo loại
         else if( spl[spl.length - 1].equals("2")) {
             return List_housing.stream()
                     .filter( x->x.getHousing_type().contains(value_find)  ).collect(Collectors.toList());
         }

         // Tìm kiếm theo diện tích
         else if( spl[spl.length - 1].equals("3")) {
             return List_housing.stream().
                     filter(x->x.getHousing_acreage() == Double.parseDouble(value_find)).collect(Collectors.toList());
         }

         // Tìm kiếm theo năm cấp sổ đỏ
         else if( spl[spl.length - 1].equals("4")) {
             return List_housing.stream().
                     filter(x->x.getHousing_year() == Integer.parseInt(value_find) ).collect(Collectors.toList());
         }
         
 		 return null;
 	}
 	
    // Sắp xếp bất động sản theo diện tích tăng hoặc giảm
    public List<Housing> sortedHousing(double acreage) {
    	// option = 0 là sắp xếp TĂNG dần theo giá
    	// option = 1 là sắp xếp GIẢM dần theo giá
        List_housing.sort(Comparator.comparing(Housing::getProduct_price));

        if(acreage == 1) Collections.reverse(List_housing);

        return List_housing;
    }

}
