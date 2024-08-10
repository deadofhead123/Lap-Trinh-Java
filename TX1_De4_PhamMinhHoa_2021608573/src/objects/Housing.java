//--- Có tham khảo:
// + Video thầy gửi
// + Học liệu trên Học kết hợp
// + www.javatpoint.com

package objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Housing extends Product implements HousingManager{
    public static ArrayList<Housing> List_housing = new ArrayList<>();
    public static int cnt_id = 0;

    private String housing_type; // Căn hộ - chung cư, căn hộ Dual-key, căn hộ Loft...
    private double housing_acreage; // Diện tích đất
    private int housing_year; // Năm cấp sổ đỏ

    // Constructor
    public Housing() {}
    public Housing(String product_id, String product_name, double product_price, int product_total,
                   String housing_type, double housing_acreage, int housing_year) {
        super(product_id, product_name, product_price, product_total);

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

    /*
     * Ghi đè phương thức xuất của lớp Product
     */
    public String toString() {
        return "Housing [" + super.toString() + ", housing_type= " + housing_type +
                ", housing_acreage= " + housing_acreage + ", housing_year= " + housing_year + "]\n";
    }

    /*
     * Ghi đè phương thức nhập của lớp Product
     */
    public void Provide_Input() {
        super.Provide_Input();

        System.out.print("Nhập housing_type: ");
        this.housing_type = sc.nextLine();
        System.out.print("Nhập housing_deposits: ");
        this.housing_acreage = sc.nextDouble();
        System.out.print("Nhập housing_year: ");
        this.housing_year = sc.nextInt(); 
        
        sc.nextLine();
    }

    // Thêm mới bất động sản
    public boolean addHousing(Housing h) {
        //Sinh ra id sản phẩm
        System.out.println("product_id sẽ được sinh!");
        h.setProduct_id( "BDS" + Integer.toString(cnt_id++) );

        h.Provide_Input();

        List_housing.add(h);

        return List_housing.contains(h);
    }

    // Sửa thông tin bất động sản
    public boolean editHousing(Housing h) {
        int idx = List_housing.indexOf(h);
        System.out.println("Nhập thông tin mới cho bất động sản này: ");
        h.Provide_Input();

        // Cập nhật thông tin
        List_housing.set(idx, h);
        return List_housing.get(idx) == h;
    }

    // Xóa bất động sản
    public boolean deleteHousing(Housing h) {
        List_housing.remove(h);
        return List_housing.contains(h);
    }

    // Tìm kiếm bất động sản theo các tiêu chí
    public List<Housing> searchHousing(String name) {
        //Tìm kiếm theo tên
        if(name.equals("1")) {
            System.out.print("- Nhập tên bất động sản cần tìm: ");
            String search_follow_name = sc.nextLine();

            return List_housing.stream()
                    .filter(x->x.getProduct_name().equals(search_follow_name)).collect(Collectors.toList());
        }

        //Tìm kiếm theo giá
        else if(name.equals("2")) {
            System.out.print("- Nhập giá bất động sản cần tìm: ");
            double search_price = sc.nextDouble(); sc.nextLine();

            return List_housing.stream().
                    filter(x->x.getProduct_price() == search_price).collect(Collectors.toList());
        }

        //Tìm kiếm theo loại
        else if(name.equals("3")) {
            System.out.print("- Nhập loại bất động sản cần tìm: ");
            String search_type = sc.nextLine();

            return List_housing.stream()
                    .filter(x->x.getHousing_type().equals(search_type)).collect(Collectors.toList());
        }

        //Tìm kiếm theo diện tích
        else if(name.equals("4")) {
            System.out.print("- Nhập diện tích của bất động sản cần tìm: ");
            double search_acreage = sc.nextDouble(); sc.nextLine();

            return List_housing.stream().
                    filter(x->x.getHousing_acreage() == search_acreage).collect(Collectors.toList());
        }

        //Tìm kiếm theo năm cấp sổ đỏ
        else if(name.equals("5")) {
            System.out.print("- Nhập năm cấp sổ đỏ của bất động sản cần tìm: ");
            int search_year = sc.nextInt(); sc.nextLine();

            return List_housing.stream().
                    filter(x->x.getHousing_year() == search_year).collect(Collectors.toList());
        }

        System.out.println("Không có lựa chọn này!");
        return null;
    }

    //Sắp xếp bất động sản theo diện tích tăng hoặc giảm
    public List<Housing> sortedHousing(double acreage) {
        List_housing.sort(Comparator.comparing(Housing::getHousing_acreage));

        if(acreage == 2) Collections.reverse(List_housing);

        return List_housing;
    }


    public static void main(String[] args) {

        try {
            // Menu lựa chọn chức năng
            int choose = 0;
            do {
                System.out.println("\n----------------------------------------");
                System.out.println("1. Thêm 1 thông tin bất động sản");
                System.out.println("2. Sửa thông tin bất động sản");
                System.out.println("3. Xóa thông tin bất động sản");
                System.out.println("4. Tìm kiếm bất động sản");
                System.out.println("5. Sắp xếp danh sách bất động sản");
                System.out.println("6. In ra danh sách bất động sản");
                System.out.println("7. Thoát");
                System.out.println("----> Nhập lựa chọn: ");
                choose = sc.nextInt(); sc.nextLine();

                if(choose == 1) {
                    System.out.println("--------> Thêm 1 thông tin bất động sản vào hệ thống");

                    Housing new_housing = new Housing();

                    if( new Housing().addHousing(new_housing) ) System.out.println("Thêm mới thành công!");
                }

                // Sửa thông tin bất động sản
                else if(choose == 2) {
                    System.out.println("--------> Nhập id của bất động sản cần sửa: ");
                    String id_edit = sc.nextLine();

                    boolean id_existed = false;

                    for(Housing h : List_housing) {
                        if( h.getProduct_id().equals(id_edit) ) {
                            id_existed = new Housing().editHousing(h);
                            
                            System.out.println("--> Sửa thành công!");
                            break;
                        }
                    }

                    if( !id_existed ) System.out.println("--> Không tồn tại bất động sản có id này, không sửa thông tin được!");
                }

                // Xóa thông tin bất động sản
                else if(choose == 3) {
                    System.out.println("--------> Nhập id của bất động sản cần xóa: ");
                    String id_delete = sc.nextLine();

                    boolean id_existed = true;

                    for(Housing h : List_housing) {
                        if( h.getProduct_id().equals(id_delete) ) {
                            id_existed = new Housing().deleteHousing(h);

                            System.out.println("--> Xóa thành công!");
                            System.out.println("--------> Danh sách bất động sản sau khi xóa BDS có id " + id_delete + ":");
                            for(Housing x : List_housing) System.out.print(x.toString());
                            break;
                        }
                    }

                    if( id_existed ) System.out.println("--> Không tồn tại bất động sản có id này, không xóa được!");
                }

                // Tìm kiếm bất động sản
                else if(choose == 4) {
                    System.out.println("--------> Tiêu chí tìm kiếm: ");

                    System.out.println("1. Tên");
                    System.out.println("2. Giá");
                    System.out.println("3. Loại");
                    System.out.println("4. Diện tích");
                    System.out.println("5. Năm cấp sổ đỏ");

                    System.out.print("-----> Lựa chọn: ");
                    String choose_search = sc.nextLine();

                    System.out.println(new Housing().searchHousing(choose_search));
                }

                // Sắp xếp danh sách bất động sản
                else if(choose == 5) {
                    System.out.println("--------> Kiểu sắp xếp: ");

                    System.out.println("1. Tăng dần theo diện tích");
                    System.out.println("2. Giảm dần theo diện tích");

                    System.out.print("-----> Lựa chọn: ");
                    double choose_search = sc.nextDouble(); sc.nextLine();

                    System.out.println("-- Danh sách trước sắp xếp: ");
                    for(Housing h : List_housing) System.out.print(h.toString());

                    System.out.println("-- Danh sách sau sắp xếp: ");
                    System.out.println(new Housing().sortedHousing(choose_search));
                }

                // In ra danh sách bất động sản đã lưu
                else if(choose == 6) {
                    System.out.println("--------> Danh sách bất động sản hiện có:");
                    for(Housing h : List_housing) System.out.print(h.toString());
                }

            }while(choose >= 1 && choose <= 6);

            sc.close();
        }
        catch(Exception e) {
            System.out.println("Lỗi, sai kiểu dữ liệu!");
        }

        System.out.println("Kết thúc chương trình!");
    }

}
