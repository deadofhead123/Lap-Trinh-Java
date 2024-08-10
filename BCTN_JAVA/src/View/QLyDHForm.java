/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.DonHang;
import Model.NhanVien;
import Model.SanPham;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Administrator
 */
public class QLyDHForm extends javax.swing.JFrame {
    private ArrayList<DonHang> listDH = DonHang.getDonHangList();
    private ArrayList<SanPham> listSP = SanPham.getSanPhamList();
    private ArrayList<NhanVien> listNV = NhanVien.getNhanVienList();
    private DefaultTableModel tblModel = new DefaultTableModel();
    private int auto_MaDH = DonHang.cnt_MaDH;
    
    /**
     * Creates new form QLyDHForm
     */
    public QLyDHForm() {
        initComponents();
        initTable();
        fillTable();
        setLocationRelativeTo(null); // Chỉnh cửa sổ xuất hiện ở giữa màn hình
        
        
        // Set icon cho các nút
        btnAdd.setIcon( new ImageIcon( new ImageIcon( getClass().getResource("/images/Add_Icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT) ) );
        btnDelete.setIcon( new ImageIcon( new ImageIcon( getClass().getResource("/images/Delete_Icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT) ) );
        btnRefresh.setIcon( new ImageIcon( new ImageIcon( getClass().getResource("/images/Renew_Icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT) ) );
        btnTTNV.setIcon( new ImageIcon( new ImageIcon( getClass().getResource("/images/EmployeeInformation_Icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT) ) );
        btnBack.setIcon( new ImageIcon( new ImageIcon( getClass().getResource("/images/Back_Icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT) ) );
        btnSort.setIcon( new ImageIcon( new ImageIcon( getClass().getResource("/images/Sort_Icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT) ) );
        
        /**
         * Chỉnh bảng
         */
        // Format tiêu đề bảng
	JTableHeader T_Header = tblDH.getTableHeader();
	T_Header.setBackground(Color.blue);
	T_Header.setForeground(Color.white);
	tblDH.getTableHeader().setPreferredSize( new Dimension(tblDH.getColumnModel().getTotalColumnWidth(), 26) );
						
	// Format cột "Mã đơn hàng"
	tblDH.getColumnModel().getColumn(0).setPreferredWidth(40);
						
	// Đổi màu của text trong cột ID
	DefaultTableCellRenderer color_column = new DefaultTableCellRenderer();
	color_column.setForeground(Color.red);
	tblDH.getColumnModel().getColumn(0).setCellRenderer(color_column);
		
        // Format cột "Tổng tiền"
	tblDH.getColumnModel().getColumn(6).setPreferredWidth(100);
        
	// Chỉnh kích cỡ các dòng
	tblDH.setRowHeight(25);
                
        
        // Lấy ngày thực tế cho vào ô Ngày lập
        // new Date(): lấy ngày hiện tại
        // SimpleDateFormat: chỉnh kiểu hiển thị của ngày
        lbl_Day.setText( (new SimpleDateFormat("dd/MM/yyyy")).format(new Date()) );
        
        // Lấy đồng hồ thực tế cho vào ô Thời gian
        // Lấy đồng hồ thực tế
        Timer t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbl_Time.setText( new SimpleDateFormat("hh:mm:ss a").format( new Date() ) );
            }
        } );
        t.start();
        
    }

    private double tinhTienTheoMaSP(String maSP) {
        for (SanPham o : listSP) {
            if (o.getMaSP().equalsIgnoreCase(maSP)) {
                return o.getGia();
            }
        }
        return 0;
    }

    // Bắt lỗi "Số lượng mua"
    private boolean dieukienSL(String SLM) {
        String text = txtSLM.getText();
        
        try {
            int value = Integer.parseInt(text);
            if (value < 0) {
                throw new NumberFormatException("Số lượng là số nguyên dương");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là một số", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // Nạp dữ liệu vào bảng
    private void fillTable() {
        tblModel.setRowCount(0);
        
        for (DonHang o : listDH) {
            // Số double lớn hơn 1 triệu thì sẽ hiển thị ở dạng xE.y
            // Phải chuyển về double cho chắc
            DecimalFormat e_to_double = new DecimalFormat("#0,000.000");
            String formattedNumber = e_to_double.format(( 1.0 * o.getSoLuongMua() * tinhTienTheoMaSP( o.getMaSP() ) ));
            
            tblModel.addRow(new String[]{o.getMaDH(), o.getMaSP(), o.getMaNV(),
                                "" + o.getSoLuongMua(), o.getNgay(), o.getThoiGian(),
                                "" + formattedNumber } );
        }

        tblModel.fireTableDataChanged();
    }

    
    private void initTable() {
        String[] columns = new String[]{"Mã đơn hàng", "Mã Sản Phẩm", "Mã nhân viên", "Số lượng mua", "Ngày đặt", "Thời gian", "Tổng tiền"};
        tblModel.setColumnIdentifiers(columns);
        tblDH.setModel(tblModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel19 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDH = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtSLM = new javax.swing.JTextField();
        txtNgay = new javax.swing.JTextField();
        txtMaSP = new javax.swing.JTextField();
        btnTTNV = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtMaDH = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnQLDH = new javax.swing.JButton();
        btnQLSP = new javax.swing.JButton();
        btnQLNCC = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        txtThoiGian = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbl_Day = new javax.swing.JLabel();
        lbl_Time = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        cbxSapXep = new javax.swing.JComboBox<>();
        btnSort = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel19.setText("QUẢN LÝ ĐƠN HÀNG ");

        btnBack.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBack.setText("Quay Lại");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        tblDH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblDH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", ""
            }
        ));
        tblDH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDH);

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setText("Thêm mới");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRefresh.setText("Làm mới");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Mã nhân viên: ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mã sản phẩm: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Số lượng mua: ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Ngày đặt hàng: ");

        txtMaNV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtSLM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtNgay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtMaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnTTNV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTTNV.setText("Xem thông tin nhân viên tạo đơn hàng");
        btnTTNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTTNVActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Mã Đơn hàng: ");

        txtMaDH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dhcnhn.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("MENU CHÍNH");

        btnQLDH.setBackground(new java.awt.Color(242, 242, 242));
        btnQLDH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnQLDH.setText("Quản lý đơn hàng");
        btnQLDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLDHActionPerformed(evt);
            }
        });

        btnQLSP.setBackground(new java.awt.Color(242, 242, 242));
        btnQLSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnQLSP.setText("Quản lý sản phẩm");
        btnQLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLSPActionPerformed(evt);
            }
        });

        btnQLNCC.setBackground(new java.awt.Color(242, 242, 242));
        btnQLNCC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnQLNCC.setText("Quản lý nhà cung cấp");
        btnQLNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLNCCActionPerformed(evt);
            }
        });

        btnThongKe.setBackground(new java.awt.Color(242, 242, 242));
        btnThongKe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThongKe.setText("Thống kê");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        txtThoiGian.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Thời gian:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Ngày:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Giờ:");

        lbl_Day.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_Day.setText("jLabel11");

        lbl_Time.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_Time.setText("jLabel12");

        cbxSapXep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tăng dần theo ngày", "Giảm dần theo ngày", "Tăng dần theo số lượng mua", "Giảm dần theo số lượng mua" }));

        btnSort.setText("Sắp xếp");
        btnSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addComponent(jLabel7))
                                .addComponent(btnQLDH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnQLSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnThongKe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnQLNCC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(31, 31, 31)
                                .addComponent(lbl_Time)))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnTTNV, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbxSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(btnSort))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                        .addGap(203, 203, 203)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                        .addGap(181, 181, 181)
                                        .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))
                                .addGap(104, 104, 104))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(228, 228, 228)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBack)
                                .addGap(79, 79, 79))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jSeparator1)
                                        .addGap(13, 13, 13)))
                                .addGap(19, 19, 19))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMaNV))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMaDH)))
                                .addGap(174, 174, 174)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSLM, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(102, 102, 102))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addGap(41, 41, 41))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_Day)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaDH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSLM, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSort, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnTTNV, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbxSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_Day, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_Time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(63, 63, 63)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQLDH, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQLSP, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQLNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                        .addGap(19, 19, 19))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Nút "Quay lại"
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        MenuForm menuform = new MenuForm();
        menuform.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    // Nút "Xem thông tin nhân viên tạo đơn hàng"
    private void btnTTNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTNVActionPerformed
        int SelectedRow = tblDH.getSelectedRow();
        boolean flag = false;
        if (SelectedRow >= 0) {
            // Có thể nhân viên này đã nghỉ việc, cần kiểm tra trước khi hiển thị
            String maNV = (String) tblDH.getValueAt(SelectedRow, 2);
            for (NhanVien nv : listNV) {
                if (maNV.equalsIgnoreCase(nv.getMaNV())) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                TTNVForm formTTNV = new TTNVForm(maNV);
                formTTNV.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Không có thông tin nhân viên này", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnTTNVActionPerformed

    // Hiển thị thông tin của dòng được chọn vào các ô text tương ứng
    private void tblDHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDHMouseClicked
        int SelectedRow = tblDH.getSelectedRow();
        if (SelectedRow >= 0) {
            String maDH = (String) tblDH.getValueAt(SelectedRow, 0);
            for (DonHang dh : listDH) {
                if (maDH.equals(dh.getMaDH())) {
                    txtMaDH.setText(dh.getMaDH());
                    txtMaSP.setText(dh.getMaSP());
                    txtMaNV.setText(dh.getMaNV());
                    txtSLM.setText("" + dh.getSoLuongMua());
                    txtNgay.setText(dh.getNgay());
                    txtThoiGian.setText(dh.getThoiGian());
                    break;
                }
            }
        }
    }//GEN-LAST:event_tblDHMouseClicked

    // Nút "Làm mới"
    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        txtMaDH.setText("");
        txtMaSP.setText("");
        txtMaNV.setText("");
        txtSLM.setText("");
        txtNgay.setText("");
        txtThoiGian.setText("");
    }//GEN-LAST:event_btnRefreshActionPerformed

    // Nút "Thêm mới"
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        StringBuilder sb = new StringBuilder();

        if (txtMaNV.getText().equals("")) {
            sb.append("Mã nhân viên không được để trống \n");
            txtMaNV.setBackground(Color.gray);
        } else {
            txtMaNV.setBackground(Color.white);
        }

        if (txtSLM.getText().equals("")) {
            sb.append("Số lượng mua không được để trống \n");
            txtSLM.setBackground(Color.gray);
        } else {
            txtSLM.setBackground(Color.white);
        }
        
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int flag = -1;
        
        // Kiểm tra tồn tại mã nhân viên
        if ( listNV.stream().filter( x->x.getMaNV().equalsIgnoreCase( txtMaNV.getText() ) ).collect(Collectors.toList()).size() == 0 ){
            JOptionPane.showMessageDialog(this, "Mã nhân viên này không hợp lệ! ( mã nhân viên phải trùng với các mã nhân viên đã có )", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Kiểm tra tồn tại mã sản phẩm
        if ( listSP.stream().filter( x->x.getMaSP().equalsIgnoreCase( txtMaSP.getText() ) ).collect( Collectors.toList() ).size() == 0 ){
            JOptionPane.showMessageDialog(this, "Mã sản phẩm này không hợp lệ! ( mã sản phẩm phải trùng với các mã sản phẩm đã có )", "Error", JOptionPane.ERROR_MESSAGE);
            return;  
        }       
        
        // Kiểm tra định dạng số lượng mua
        if ( !dieukienSL( txtSLM.getText() ) ) return;
        
        // Kiểm tra xem còn sản phẩm trong kho hay không
        for(SanPham sp : listSP){
            if( sp.getMaSP().equalsIgnoreCase( txtMaSP.getText() ) && Integer.parseInt( txtSLM.getText() ) > sp.getSoLuong() ){
                flag = 3;
                    
                JOptionPane.showMessageDialog(null, "Không đủ số lượng sản phẩm để mua!");
                    
                return;
            }
        }
        
        // Không sai điều kiện nào, thì thêm thông tin đơn hàng
        DonHang a = new DonHang();
        a.setMaDH("DH" + Integer.toString(++auto_MaDH));
        a.setMaSP(txtMaSP.getText());
        a.setMaNV(txtMaNV.getText());
        a.setSoLuongMua(Integer.parseInt(txtSLM.getText()));
        a.setNgay( lbl_Day.getText() );
        a.setThoiGian(lbl_Time.getText());
            
        // Cập nhật số lượng của sản phẩm vừa mua trong file
        try {
            // new FileWriter(path, true/false) : true thì là ghi tiếp vào nội dung đang có, false tức là ghi mới (xóa hết nội dung cũ)
            FileWriter fw = new FileWriter("CSDL\\SanPham.txt", false); 
            BufferedWriter bw = new BufferedWriter(fw);            
                
            // Đặt lại số lượng sản phẩm trong kho (giảm đi)
            for(SanPham sp : listSP){
                if( sp.getMaSP().equalsIgnoreCase( txtMaSP.getText() ) ){
                   sp.setSoLuong( sp.getSoLuong() - Integer.parseInt( txtSLM.getText() ) );
                }
                bw.write(sp.toString());
                bw.newLine();
            }
                
            bw.close();
            fw.close();
        } catch (IOException e) {
        }
            
        listDH.add(a);
        fillTable();

        // Cập nhật danh sách đơn hàng
        try {
            FileWriter fw = new FileWriter("CSDL\\DonHang.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(a.toString());
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
        }
            
        JOptionPane.showMessageDialog(this, "Thêm đơn hàng mới thành công!");
        
        // Tẩy đi các ô để có thể nhập mới
        btnRefreshActionPerformed(evt);
    }//GEN-LAST:event_btnAddActionPerformed

    // Nút "Xóa"
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        StringBuilder sb = new StringBuilder();

        if (txtMaDH.getText().equals("")) {
            sb.append("Mã Đơn hàng không được để trống \n");
            txtMaDH.setBackground(Color.gray);
        } else {
            txtMaDH.setBackground(Color.white);
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá đơn hàng không? ", "Xác Nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION) {
            return;
        }

        // Phải chọn 1 dòng nào đó thì mới thực hiện xóa được
        for (DonHang o : listDH) {
            if ( o.getMaDH().equalsIgnoreCase( txtMaDH.getText() ) ) {
                listDH.remove(o);
                break;
            }
        }
        
        // Cập nhật file sau khi xóa sản phẩm
        try {
            FileWriter fw = new FileWriter("CSDL\\DonHang.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (DonHang o : listDH) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
        }
        fillTable();
        btnRefreshActionPerformed(evt);
        JOptionPane.showMessageDialog(this, "Xóa đơn hàng thành công!");
    }//GEN-LAST:event_btnDeleteActionPerformed

    // Liên kết đến các chức năng khác
    private void btnQLDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLDHActionPerformed
        QLyDHForm formQLDH = new QLyDHForm();
        formQLDH.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnQLDHActionPerformed

    private void btnQLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLSPActionPerformed
        QLySPForm formQLSP = new QLySPForm();
        formQLSP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnQLSPActionPerformed

    private void btnQLNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLNCCActionPerformed
        QLyNCCForm formQLNCC = new QLyNCCForm();
        formQLNCC.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnQLNCCActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        ThongKeForm formThongKe = new ThongKeForm();
        formThongKe.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortActionPerformed
        int lc = cbxSapXep.getSelectedIndex();
        
        // Sắp xếp theo ngày
        if(lc == 0 || lc == 1){
            listDH.sort(Comparator.comparing(DonHang::getNgay));
        
            // Đảo ngược danh sách sx tăng dần, đc danh sách sx giảm dần
            if( cbxSapXep.getSelectedIndex() == 1 ) Collections.reverse(listDH);
        }
        // Sắp xếp theo số lượng mua
        else if(lc == 2 || lc == 3){
            listDH.sort(Comparator.comparing(DonHang::getSoLuongMua));
        
            // Đảo ngược danh sách sx tăng dần, đc danh sách sx giảm dần
            if( cbxSapXep.getSelectedIndex() == 3 ) Collections.reverse(listDH);
        }
        
        // Ghi lại danh sách vừa sắp xếp vào file
        try {
            // new FileWriter(path, true/false) : true thì là ghi tiếp vào nội dung đang có, false tức là ghi mới (xóa hết nội dung cũ)
            FileWriter fw = new FileWriter("CSDL\\DonHang.txt", false); 
            BufferedWriter bw = new BufferedWriter(fw);            
                
            for(DonHang dh : listDH){
                bw.write(dh.toString());
                bw.newLine();
            }
                
            bw.close();
            fw.close();
        } catch (IOException e) {
        }
        
        fillTable();
        JOptionPane.showMessageDialog(this, "Sắp xếp thành công!");
    }//GEN-LAST:event_btnSortActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QLyDHForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLyDHForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLyDHForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLyDHForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new QLyDHForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnQLDH;
    private javax.swing.JButton btnQLNCC;
    private javax.swing.JButton btnQLSP;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSort;
    private javax.swing.JButton btnTTNV;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JComboBox<String> cbxSapXep;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbl_Day;
    private javax.swing.JLabel lbl_Time;
    private javax.swing.JTable tblDH;
    private javax.swing.JTextField txtMaDH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtNgay;
    private javax.swing.JTextField txtSLM;
    private javax.swing.JTextField txtThoiGian;
    // End of variables declaration//GEN-END:variables
}
