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
public class NCC{
    public static int cnt_MaNCC = 0;
    
    private String maNCC; // Mã nhà cung cấp
    private String tenNCC; // Tên nhà cung cấp
    private String diaChi; // Địa chỉ nhà cung cấp
    private String soDT; // Số điện thoại
    private String maSP; // Mã của sản phẩm mà nhà cung cấp đó đã nhập
    private int soLuongNhap; // Số lượng sản phẩm có mã ở trên được nhập
    
    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(int SoLuongNhap) {
        this.soLuongNhap = SoLuongNhap;
    }
 
    public NCC() {
        this.maNCC = "";
        this.tenNCC = "";
        this.diaChi = "";
        this.soDT = "";
        soLuongNhap = 0;
        this.maSP="";
    }

    public NCC(String maNCC, String tenNCC, String diaChi, String soDT, int soLuongNhap, String maSP) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.soLuongNhap = soLuongNhap;
        this.maSP=maSP;
    }

    @Override
    public String toString() {
        return this.maNCC+","+this.tenNCC+","+this.diaChi+","+this.soDT+","+this.soLuongNhap+","+this.maSP;
    }
    
    public static ArrayList<NCC> getNCCList() {
        ArrayList<NCC> listNCC = new ArrayList<NCC>();
        String fileName = "CSDL\\NCC.txt";
        String line;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(",");
                String maNCC = fields[0];
                String tenNCC = fields[1];
                String diaChi = fields[2];
                String soDT = fields[3];
                int soLuongNhap = Integer.parseInt(fields[4]);
                String maSP = fields[5];
                
                cnt_MaNCC = Math.max(cnt_MaNCC, Integer.parseInt(maNCC.substring(3)) );
                
                listNCC.add( new NCC(maNCC, tenNCC, diaChi, soDT,soLuongNhap,  maSP) );
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error reading file " + fileName);
        }
        return listNCC;
    }
}
