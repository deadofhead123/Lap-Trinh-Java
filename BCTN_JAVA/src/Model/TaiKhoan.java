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
public class TaiKhoan {
    private String tenTK;
    private String matKhau;

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public TaiKhoan() {
        this.tenTK = "";
        this.matKhau = "";
    }

    public TaiKhoan(String tenTK, String matKhau) {
        this.tenTK = tenTK;
        this.matKhau = matKhau;
    }
    
    public static ArrayList<TaiKhoan> getTKNVList() {
        ArrayList<TaiKhoan> listTK = new ArrayList<TaiKhoan>();
        String fileName = "CSDL\\TKQL.txt";
        String line;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(",");
                
                String tenTK = fields[0];
                String matKhau = fields[1];

                listTK.add(new TaiKhoan(tenTK, matKhau));
            }
            
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error reading file " + fileName);
        }
        
        return listTK;
    }
}
