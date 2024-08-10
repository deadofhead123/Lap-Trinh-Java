package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NhanVien {
    
    private String maNV; // Mã nhân viên
    private String tenNV; // Tên nhân viên
    private String diaChi; // Địa chỉ nhân viên
    private String soDT; // Số điện thoại nhân viên

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
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

    public NhanVien() {
        this.maNV = "";
        this.tenNV = "";
        this.diaChi = "";
        this.soDT = "";
    }

    public NhanVien(String maNV, String tenNV, String diaChi, String soDT) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.diaChi = diaChi;
        this.soDT = soDT;
    }

    @Override
    public String toString() {
        return this.maNV+","+this.tenNV+","+this.diaChi+","+this.soDT;
    }

    public static ArrayList<NhanVien> getNhanVienList() {
        ArrayList<NhanVien> listNV = new ArrayList<NhanVien>();
        String fileName = "CSDL\\NhanVien.txt";
        String line;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(",");
                
                String maNV = fields[0];
                String tenNV = fields[1];
                String diaChi = fields[2];
                String soDT = fields[3];
                
                listNV.add( new NhanVien(maNV, tenNV, diaChi, soDT) );
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error reading file " + fileName);
        }
        return listNV;
    }
}
