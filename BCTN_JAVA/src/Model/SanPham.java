package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SanPham {
    public static int cnt_MaSP = 0;
    
    public String maSP; // Mã sản phẩm
    private String tenSP; // Tên sản phẩm
    private String moTa; // Mô tả về sản phẩm
    private String donViTinh; // Đơn vị tính
    private int SoLuong; // Số lượng sản phẩm
    private double Gia; // Giá sản phẩm

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String MaSP) {
        this.maSP = MaSP;
    }
    
    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }
    
    public SanPham() {
        maSP = "";
        tenSP = "";
        moTa = "";
        donViTinh = "";
        SoLuong = 0;
        Gia = 0;
    }

    public SanPham(String maSP, String tenSP, String moTa, String donViTinh, int SoLuong, double Gia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.donViTinh = donViTinh;
        this.SoLuong = SoLuong;
        this.Gia = Gia;
    }

    @Override
    public String toString() {
        return this.maSP+","+this.tenSP+","+this.moTa+","+this.donViTinh+","+this.SoLuong+","+this.Gia;
    }

    //get data from database
    public static ArrayList<SanPham> getSanPhamList() {
        ArrayList<SanPham> listSP = new ArrayList<SanPham>();
        String fileName = "CSDL\\SanPham.txt";
        String line;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(",");
                String maSP = fields[0];
                String tenSP = fields[1];
                String moTa = fields[2];
                String donViTinh = fields[3];
                int SoLuong = Integer.parseInt(fields[4]);
                double Gia = Double.parseDouble(fields[5]);
                
                cnt_MaSP = Math.max( cnt_MaSP, Integer.parseInt( maSP.substring(2) ) );
                listSP.add( new SanPham(maSP, tenSP, moTa, donViTinh, SoLuong, Gia) );
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error reading file " + fileName);
        }
        return listSP;
    }
}
