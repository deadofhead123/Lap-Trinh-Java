/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class DonHang {
    public static int cnt_MaDH = 0;
    
    private String maDH; // Mã đơn hàng
    private String maSP; // Mã sản phẩm đã mua
    private String maNV; // Mã nhân viên tạo đơn
    private int soLuongMua; // Số lượng mua của sản phẩm có mã ở trên 
    private String Ngay; // Ngày tạo đơn (ngày - tháng - năm)
    private String ThoiGian; // Thời gian tạo đơn (giờ - phút - giây)
    
    public String getMaDH() {
        return maDH;
    }

    public void setMaDH(String maDH) {
        this.maDH = maDH;
    }
    
    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }
    
    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String Ngay) {
        this.Ngay = Ngay;
    }

    public String getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(String ThoiGian) {
        this.ThoiGian = ThoiGian;
    }

    public DonHang() {
        this.maDH = maDH;
        this.maSP = maSP;
        this.maNV = maNV;
        this.soLuongMua = 0;
        this.Ngay = Ngay;
        this.ThoiGian = ThoiGian;
    }

    public DonHang(String maDH, String maSP, String maNV, int soLuongMua, String Ngay, String ThoiGian) {
        this.maDH = maDH;
        this.maSP = maSP;
        this.maNV = maNV;
        this.soLuongMua = soLuongMua;
        this.Ngay = Ngay;
        this.ThoiGian = ThoiGian;
    }
    
    @Override
    public String toString() {
        return this.maDH+","+this.maSP+","+this.maNV+","+this.soLuongMua+","+this.Ngay + "," + this.ThoiGian;
    }
    
    //get data from database
    public static ArrayList<DonHang> getDonHangList() {
        ArrayList<DonHang> listDH = new ArrayList<DonHang>();
        String fileName = "CSDL\\DonHang.txt";
        String line;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                
                String[] fields = line.split(",");
                String maDH = fields[0];
                String maSP = fields[1];
                String maNV = fields[2];
                int soLuongMua = Integer.parseInt(fields[3]);
                String Ngay = fields[4];
                String ThoiGian = fields[5];
                
                cnt_MaDH = Math.max(cnt_MaDH, Integer.parseInt(maDH.substring(2) ) );
                        
                listDH.add( new DonHang(maDH, maSP, maNV, soLuongMua, Ngay, ThoiGian) );
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error reading file " + fileName);
        }
        return listDH;
    }
}
