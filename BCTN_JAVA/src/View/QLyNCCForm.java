/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;
import Model.NCC;
import Model.SanPham;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
public class QLyNCCForm extends javax.swing.JFrame {
    private ArrayList<NCC> listNCC = NCC.getNCCList();
    private ArrayList<SanPham> listSP = SanPham.getSanPhamList();
    private DefaultTableModel tblModel = new DefaultTableModel();
    private int auto_MaNCC = NCC.cnt_MaNCC;
    
    /**
     * Creates new form QLyNCC
     */
    public QLyNCCForm() {
        initComponents();
        initTable();
        fillTable();
        setLocationRelativeTo(null); // Chỉnh cửa sổ xuất hiện ở giữa màn hình
        
        
        // Set icon cho các nút
        btnAdd.setIcon( new ImageIcon( new ImageIcon( getClass().getResource("/images/Add_Icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT) ) );
        btnUpdate.setIcon( new ImageIcon( new ImageIcon( getClass().getResource("/images/Update_Icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT) ) );
        btnDelete.setIcon( new ImageIcon( new ImageIcon( getClass().getResource("/images/Delete_Icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT) ) );
        btnRefresh.setIcon( new ImageIcon( new ImageIcon( getClass().getResource("/images/Renew_Icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT) ) );
        btnBack.setIcon( new ImageIcon( new ImageIcon( getClass().getResource("/images/Back_Icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT) ) );
        btnSort.setIcon( new ImageIcon( new ImageIcon( getClass().getResource("/images/Sort_Icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT) ) );
        
        /**
         * Chỉnh bảng
         */
        // Format tiêu đề bảng
	JTableHeader T_Header = tblNCC.getTableHeader();
	T_Header.setBackground(Color.MAGENTA);
	T_Header.setForeground(Color.black);
	tblNCC.getTableHeader().setPreferredSize( new Dimension(tblNCC.getColumnModel().getTotalColumnWidth(), 26));
						
	// Format cột MaDH
	tblNCC.getColumnModel().getColumn(0).setPreferredWidth(40);

        // Đổi màu của text trong cột ID
	DefaultTableCellRenderer color_column = new DefaultTableCellRenderer();
	color_column.setForeground(Color.red);
	tblNCC.getColumnModel().getColumn(0).setCellRenderer(color_column);
		
	// Chỉnh kích cỡ các dòng
	tblNCC.setRowHeight(25);
        
        // Chỉnh cột Mã sản phẩm
        tblNCC.getColumnModel().getColumn(4).setCellRenderer(color_column);
        
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

    private void fillTable() {
        tblModel.setRowCount(0);
        for (NCC o : listNCC) {
            tblModel.addRow(new String[]{o.getMaNCC(), o.getTenNCC(), o.getDiaChi(), o.getSoDT(), o.getMaSP(), String.valueOf(o.getSoLuongNhap())  });
        }
        tblModel.fireTableDataChanged();
    }

    private void initTable() {
        String[] columns = new String[]{"Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại", "Mã sản phẩm", "Số Lượng Nhập"};
        tblModel.setColumnIdentifiers(columns);
        tblNCC.setModel(tblModel);
    }

    private boolean dieukienSLN() {
        String text = txtSLN.getText();
        try {
            int value = Integer.parseInt(text);
            if (value < 0) {
                throw new NumberFormatException("Số lượng phải là số nguyên dương");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là một số", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRefresh = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtSLN = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        Title = new javax.swing.JLabel();
        txtMaNCC = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        TableSP = new javax.swing.JScrollPane();
        tblNCC = new javax.swing.JTable();
        txtTenNCC = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        txtdcNCC = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        txtSoDT = new javax.swing.JTextField();
        Jlabel6 = new javax.swing.JLabel();
        txtMaSPNCC = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnQLDH = new javax.swing.JButton();
        btnQLSP = new javax.swing.JButton();
        btnQLNCC = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_Day = new javax.swing.JLabel();
        lbl_Time = new javax.swing.JLabel();
        btnSort = new javax.swing.JButton();
        cbxSapXep = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnRefresh.setBackground(new java.awt.Color(242, 242, 242));
        btnRefresh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnRefresh.setText("Làm mới");
        btnRefresh.setToolTipText("Làm mới các trường dữ liệu");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Số lượng nhập: ");

        txtSLN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSLN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSLNActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Mã nhà cung cấp:");

        Title.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        Title.setText("QUẢN LÝ NHÀ CUNG CẤP ");

        txtMaNCC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tên nhà cung cấp: ");

        tblNCC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblNCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNCCMouseClicked(evt);
            }
        });
        TableSP.setViewportView(tblNCC);

        txtTenNCC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Địa chỉ: ");

        btnAdd.setBackground(new java.awt.Color(242, 242, 242));
        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAdd.setText("Thêm mới");
        btnAdd.setToolTipText("Thêm thông tin 1 nhà cung cấp vào hệ thống");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        txtdcNCC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnUpdate.setBackground(new java.awt.Color(242, 242, 242));
        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.setToolTipText("Sửa thông tin 1 nhà cung cấp có trong hệ thống");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Số điện thoại: ");

        btnDelete.setBackground(new java.awt.Color(242, 242, 242));
        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.setToolTipText("Xóa thông tin một nhà cung cấp khỏi hệ thống");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        txtSoDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        Jlabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Jlabel6.setText("Mã sản phẩm: ");

        txtMaSPNCC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaSPNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPNCCActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(242, 242, 242));
        btnBack.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBack.setText("Quay Lại");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("MENU CHÍNH");

        btnQLDH.setBackground(new java.awt.Color(242, 242, 242));
        btnQLDH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnQLDH.setText("Quản lý đơn hàng");
        btnQLDH.setToolTipText("Chuyển đến màn hình \"Quản lý đơn hàng\"");
        btnQLDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLDHActionPerformed(evt);
            }
        });

        btnQLSP.setBackground(new java.awt.Color(242, 242, 242));
        btnQLSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnQLSP.setText("Quản lý sản phẩm");
        btnQLSP.setToolTipText("Chuyển đến màn hình \"Quản lý sản phẩm\"");
        btnQLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLSPActionPerformed(evt);
            }
        });

        btnQLNCC.setBackground(new java.awt.Color(242, 242, 242));
        btnQLNCC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnQLNCC.setText("Quản lý nhà cung cấp");
        btnQLNCC.setToolTipText("Chuyển đến màn hình \"Quản lý nhà cung cấp\"");
        btnQLNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLNCCActionPerformed(evt);
            }
        });

        btnThongKe.setBackground(new java.awt.Color(242, 242, 242));
        btnThongKe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThongKe.setText("Thống kê");
        btnThongKe.setToolTipText("Chuyển đến màn hình \"Thống kê\"");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dhcnhn.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Ngày:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Giờ:");

        lbl_Day.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_Day.setText("jLabel10");

        lbl_Time.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_Time.setText("jLabel11");

        btnSort.setText("Sắp xếp");
        btnSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortActionPerformed(evt);
            }
        });

        cbxSapXep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tăng dần theo số lượng nhập", "Giảm dần theo số lượng nhập" }));
        cbxSapXep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSapXepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnQLDH, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnQLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnQLNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9))
                                    .addGap(43, 43, 43)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbl_Time)
                                        .addComponent(lbl_Day))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(40, 40, 40)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtdcNCC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMaNCC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Jlabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaSPNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSLN, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(TableSP)
                                        .addGap(15, 15, 15))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Title)
                                        .addGap(131, 131, 131)
                                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator1)
                                    .addComponent(jSeparator3))))
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(119, 119, 119)
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(96, 96, 96)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(141, 141, 141))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(cbxSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(351, 351, 351))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_Day, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_Time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Title)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSLN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Jlabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdcNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaSPNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnQLDH, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQLSP, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQLNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TableSP, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSort, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSLNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSLNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSLNActionPerformed

    private void tblNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNCCMouseClicked
        int SelectedRow = tblNCC.getSelectedRow();
        txtMaNCC.setEnabled(false);
        
        if (SelectedRow >= 0) {
            String maNCC = (String) tblNCC.getValueAt(SelectedRow, 0);

            for (NCC ncc : listNCC) {
                if ( maNCC.equals(ncc.getMaNCC()) ) {
                    txtMaNCC.setText(ncc.getMaNCC());
                    txtTenNCC.setText(ncc.getTenNCC());
                    txtdcNCC.setText(ncc.getDiaChi());
                    txtSoDT.setText(ncc.getSoDT());
                    txtSLN.setText("" + ncc.getSoLuongNhap());
                    txtMaSPNCC.setText("" + ncc.getMaSP());
                    break;
                }
            }
        }
    }//GEN-LAST:event_tblNCCMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        StringBuilder sb = new StringBuilder();

        // Mã NCC sẽ tự sinh

        if (txtTenNCC.getText().equals("")) {
            sb.append("Tên nhà cung cấp không được để trống \n");
            txtTenNCC.setBackground(Color.gray);
        } else {
            txtTenNCC.setBackground(Color.white);
        }

        if (txtdcNCC.getText().equals("")) {
            sb.append("Địa chỉ nhà cung cấp không được để trống \n");
            txtdcNCC.setBackground(Color.gray);
        } else {
            txtdcNCC.setBackground(Color.white);
        }

        if (txtSoDT.getText().equals("")) {
            sb.append("Số điện thoại nhà cung cấp không được để trống \n");
            txtSoDT.setBackground(Color.gray);
        } else {
            txtSoDT.setBackground(Color.white);
        }

        if (txtSLN.getText().equals("")) {
            sb.append("Số Lượng nhập không được để trống \n");
            txtSLN.setBackground(Color.gray);
        } else {
            txtSLN.setBackground(Color.white);
        }

        if (txtMaSPNCC.getText().equals("")) {
            sb.append("Mã sản phẩm nhập không được để trống \n");
            txtMaSPNCC.setBackground(Color.gray);
        } else {
            txtMaSPNCC.setBackground(Color.white);
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check trùng tên nhà cung cấp
        if ( listNCC.stream().filter( x->x.getTenNCC().equalsIgnoreCase( txtTenNCC.getText() ) ).collect( Collectors.toList() ).size() != 0 ) {
                JOptionPane.showMessageDialog(this, "Tên nhà cung cấp đã tồn tại", "Error", JOptionPane.ERROR_MESSAGE);           
                return;
        }          
        // Check số lượng nhập đúng định dạng
        else if (!dieukienSLN()) return;
        // Check tồn tại mã sản phẩm
        else if ( listSP.stream().filter( x->x.getMaSP().equalsIgnoreCase( txtMaSPNCC.getText() ) ).collect( Collectors.toList() ).size() == 0 ){
            JOptionPane.showMessageDialog(this, "Không tồn tại mã sản phẩm này!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Thêm mới nhà cung cấp, cập nhật các thông tin liên quan
        NCC a = new NCC();
            a.setMaNCC( "NCC" + Integer.toString(++auto_MaNCC) );
            a.setTenNCC(txtTenNCC.getText());
            a.setDiaChi(txtdcNCC.getText());
            a.setSoDT(txtSoDT.getText());
            a.setSoLuongNhap(Integer.parseInt(txtSLN.getText()));
            a.setMaSP(txtMaSPNCC.getText());

            listNCC.add(a);
            fillTable();

            // Cập nhật số lượng sản phẩm (tăng lên)
            try {
                    // new FileWriter(path, true/false) : true thì là ghi tiếp vào nội dung đang có, false tức là ghi mới (xóa hết nội dung cũ)
                    
                    // Cần cập nhật số lượng sản phẩm (được tăng lên)
                    FileWriter fw = new FileWriter("CSDL\\SanPham.txt", false); 
                    BufferedWriter bw = new BufferedWriter(fw);            

                    // Ghi lại sản phẩm vừa sửa
                    for(SanPham x : listSP){
                        if( x.getMaSP().equalsIgnoreCase( txtMaSPNCC.getText() ) ){
                            x.setSoLuong( x.getSoLuong() + Integer.parseInt( txtSLN.getText())  );
                        }
                        bw.write(x.toString());
                        bw.newLine();
                    }

                    bw.close();
                    fw.close();
                } catch (IOException e) {
                }
            
            // Thêm nhà cung cấp mới
            try {
                FileWriter fw = new FileWriter("CSDL\\NCC.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(a.toString());
                bw.newLine();
                bw.close();
                fw.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Lỗi mở file", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thành công!");
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        StringBuilder sb = new StringBuilder();

        if (txtdcNCC.getText().equals("")) {
            sb.append("Địa chỉ nhà cung cấp không được để trống \n");
            txtdcNCC.setBackground(Color.gray);
        } else {
            txtdcNCC.setBackground(Color.white);
        }

        if (txtSoDT.getText().equals("")) {
            sb.append("Số điện thoại nhà cung cấp không được để trống \n");
            txtSoDT.setBackground(Color.gray);
        } else {
            txtSoDT.setBackground(Color.white);
        }

        if (txtSLN.getText().equals("")) {
            sb.append("Số Lượng nhập không được để trống \n");
            txtSLN.setBackground(Color.gray);
        } else {
            txtSLN.setBackground(Color.white);
        }

        if (txtMaSPNCC.getText().equals("")) {
            sb.append("Mã sản phẩm nhập không được để trống \n");
            txtMaSPNCC.setBackground(Color.gray);
        } else {
            txtMaSPNCC.setBackground(Color.white);
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Check tồn tại mã sản phẩm
        if( listSP.stream().filter( x->x.getMaSP().equalsIgnoreCase( txtMaSPNCC.getText() ) ).collect( Collectors.toList() ).size() == 0 ){
            JOptionPane.showMessageDialog(this, "Không tồn tại mã sản phẩm này!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Số lượng nhập có hợp lệ không
        if( !dieukienSLN() ){
            return;
        }
        
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật thông tin không? ", "Xác Nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION) {
            return;
        }

        // Cập nhật thông tin nhà cung cấp, và các thông tin liên quan
        for (NCC ncc : listNCC) {
            if (ncc.getMaNCC().equals(txtMaNCC.getText())) {
                try {
                    // new FileWriter(path, true/false) : true thì là ghi tiếp vào nội dung đang có, false tức là ghi mới (xóa hết nội dung cũ)
                    
                    // Cần cập nhật số lượng sản phẩm (được tăng lên)
                    FileWriter fw = new FileWriter("CSDL\\SanPham.txt", false); 
                    BufferedWriter bw = new BufferedWriter(fw);            

                    // Ghi lại sản phẩm vừa sửa
                    for(SanPham x : listSP){
                        if( x.getMaSP().equalsIgnoreCase( txtMaSPNCC.getText() ) ){
                            x.setSoLuong( x.getSoLuong() + Integer.parseInt( txtSLN.getText())  );
                        }
                        bw.write(x.toString());
                        bw.newLine();
                    }

                    bw.close();
                    fw.close();
                } catch (IOException e) {
                }
                
                // Cập nhật thông tin nhà cung cấp
                try {
                    // new FileWriter(path, true/false) : true thì là ghi tiếp vào nội dung đang có, false tức là ghi mới (xóa hết nội dung cũ)
                    FileWriter fw = new FileWriter("CSDL\\NCC.txt", false); 
                    BufferedWriter bw = new BufferedWriter(fw);            

                    // Ghi lại sản phẩm vừa sửa
                    for(NCC x : listNCC){
                        if( x.getMaNCC().equalsIgnoreCase( txtMaNCC.getText() ) ){
                            x.setDiaChi(txtdcNCC.getText());
                            x.setSoDT(txtSoDT.getText());
                            x.setSoLuongNhap( Integer.parseInt( txtSLN.getText() )  );
                            x.setMaSP(txtMaSPNCC.getText());
                        }
                        bw.write(x.toString());
                        bw.newLine();
                    }

                    bw.close();
                    fw.close();
                } catch (IOException e) {
                }
                
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhà cung cấp thành công!");
                
                break;
            }
        }
        fillTable();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        StringBuilder sb = new StringBuilder();

        if (txtMaNCC.getText().equals("")) {
            sb.append("Mã nhà cung cấp không được để trống \n");
            txtMaNCC.setBackground(Color.gray);
        } else {
            txtMaNCC.setBackground(Color.white);
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá nhà cung cấp không? ", "Xác Nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION) {
            return;
        }

        for (NCC o : listNCC) {
            if (o.getMaNCC().equalsIgnoreCase(txtMaNCC.getText())) {
                listNCC.remove(o);
                break;
            }
        }
        
        // Ghi lại file sau khi xóa nhà cung cấp
        try {
            FileWriter fw = new FileWriter("CSDL\\NCC.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (NCC o : listNCC) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
        }
        fillTable();
        
        JOptionPane.showMessageDialog(this, "Xóa nhà cung cấp thành công!");
        
        btnRefreshActionPerformed(evt);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        txtMaNCC.setText("");
        txtMaNCC.setEnabled(true);
        txtTenNCC.setText("");
        txtdcNCC.setText("");
        txtSoDT.setText("");
        txtSLN.setText("");
        txtMaSPNCC.setText("");
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtMaSPNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPNCCActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        MenuForm menuform = new MenuForm();
        menuform.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

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
        // TODO add your handling code here:
        listNCC.sort(Comparator.comparing(NCC::getSoLuongNhap));
        
        // Đảo ngược danh sách sx tăng dần, đc danh sách sx giảm dần
        if( cbxSapXep.getSelectedIndex() == 1 ) Collections.reverse(listNCC);
        
        // Ghi lại danh sách vừa sắp xếp vào file
        try {
            // new FileWriter(path, true/false) : true thì là ghi tiếp vào nội dung đang có, false tức là ghi mới (xóa hết nội dung cũ)
            FileWriter fw = new FileWriter("CSDL\\NCC.txt", false); 
            BufferedWriter bw = new BufferedWriter(fw);            
                
            for(NCC ncc : listNCC){
                bw.write(ncc.toString());
                bw.newLine();
            }
                
            bw.close();
            fw.close();
        } catch (IOException e) {
        }
        
        fillTable();
        JOptionPane.showMessageDialog(this, "Sắp xếp thành công!");
    }//GEN-LAST:event_btnSortActionPerformed

    private void cbxSapXepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSapXepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSapXepActionPerformed

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
            java.util.logging.Logger.getLogger(QLyNCCForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLyNCCForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLyNCCForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLyNCCForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new QLyNCCForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jlabel6;
    private javax.swing.JScrollPane TableSP;
    private javax.swing.JLabel Title;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnQLDH;
    private javax.swing.JButton btnQLNCC;
    private javax.swing.JButton btnQLSP;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSort;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbxSapXep;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbl_Day;
    private javax.swing.JLabel lbl_Time;
    private javax.swing.JTable tblNCC;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtMaSPNCC;
    private javax.swing.JTextField txtSLN;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtTenNCC;
    private javax.swing.JTextField txtdcNCC;
    // End of variables declaration//GEN-END:variables
}
