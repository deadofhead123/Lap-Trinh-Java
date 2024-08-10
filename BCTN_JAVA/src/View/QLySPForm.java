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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Administrator
 */
public class QLySPForm extends javax.swing.JFrame {

    private ArrayList<SanPham> listSP = SanPham.getSanPhamList();
    private DefaultTableModel tblModel = new DefaultTableModel();
    private int auto_MaSP = SanPham.cnt_MaSP;
    
    /**
     * Creates new form QLySPForm
     */
    public QLySPForm() {
        initComponents();
        initTable();
        fillTable();
        setLocationRelativeTo(null); // chinh cua so xuat hien chinh giua man hinh
        
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
	JTableHeader T_Header = tblSP.getTableHeader();
	T_Header.setBackground(Color.darkGray);
	T_Header.setForeground(Color.white);
	tblSP.getTableHeader().setPreferredSize( new Dimension(tblSP.getColumnModel().getTotalColumnWidth(), 26));
						
	// Format cột MaDH
	tblSP.getColumnModel().getColumn(0).setPreferredWidth(40);
						
	// Đổi màu của text trong cột ID
	DefaultTableCellRenderer color_column = new DefaultTableCellRenderer();
	color_column.setForeground(Color.red);
	tblSP.getColumnModel().getColumn(0).setCellRenderer(color_column);
		
	// Chỉnh kích cỡ các dòng
	tblSP.setRowHeight(25);
        
        // Lấy ngày thực tế
        jLabel11.setText( (new SimpleDateFormat("dd/MM/yyyy")).format(new Date()) );
        
        // Lấy đồng hồ thực tế
        Timer t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jLabel12.setText( new SimpleDateFormat("hh:mm:ss a").format( new Date() ) );
            }
        } );
        t.start();
        
    }

    private void fillTable() {
        tblModel.setRowCount(0);
        for (SanPham o : listSP) {
            tblModel.addRow(new String[]{o.getMaSP(), o.getTenSP(), o.getMoTa(), o.getDonViTinh(), String.valueOf(o.getSoLuong()), 
                String.valueOf(o.getGia())});
        }
        tblModel.fireTableDataChanged();
    }

    private void initTable() {
        String[] columns = new String[]{"Mã Sản Phẩm", "Tên Sản Phẩm", "Mô tả", "Đơn vị tính", "Số Lượng", "Giá Tiền"};
        tblModel.setColumnIdentifiers(columns);
        tblSP.setModel(tblModel);
    }

    // Check số lượng có phải số nguyên không
    private boolean dieukienSL() {
        String text = txtSL.getText();
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

    // Check giá có phải số thực không
    private boolean dieukienGia() {
        String text = txtGia.getText();
        try {
            double value = Double.parseDouble(text);
            if (value < 0) {
                throw new NumberFormatException("Giá tiền phải là số dương");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Giá tiền phải là một số", "Error", JOptionPane.ERROR_MESSAGE);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        Title = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        TableSP = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMoTa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDvTinh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSL = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnQLDH = new javax.swing.JButton();
        btnQLSP = new javax.swing.JButton();
        btnQLNCC = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbxSapXep = new javax.swing.JComboBox<>();
        btnSort = new javax.swing.JButton();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jScrollPane3.setViewportView(jTree1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 204, 255));

        Title.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        Title.setText("QUẢN LÝ SẢN PHẨM");

        tblSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblSP.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        TableSP.setViewportView(tblSP);

        btnAdd.setBackground(new java.awt.Color(242, 242, 242));
        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAdd.setText("Thêm mới");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(242, 242, 242));
        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(242, 242, 242));
        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnRefresh.setBackground(new java.awt.Color(242, 242, 242));
        btnRefresh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnRefresh.setText("Làm mới");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Mã Sản Phẩm: ");

        txtMaSP.setEditable(false);
        txtMaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tên Sản Phẩm: ");

        txtTenSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Mô tả: ");

        txtMoTa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Đơn vị Tính: ");

        txtDvTinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Số Lượng: ");

        txtSL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSLActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Giá Tiền:");

        txtGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dhcnhn.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Ngày:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Giờ:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("jLabel11");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("jLabel12");

        cbxSapXep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tăng dần theo giá", "Giảm dần theo giá" }));

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnQLDH, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnQLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnQLNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(jLabel7)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(219, 219, 219)
                                .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(24, 24, 24)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(txtDvTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(504, 504, 504))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TableSP, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(54, 54, 54)
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(69, 69, 69)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(56, 56, 56)
                                        .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(80, 80, 80)
                                        .addComponent(cbxSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(50, 50, 50))
                                    .addComponent(jSeparator1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel2))
                                                .addGap(41, 41, 41)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDvTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnQLDH, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQLNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TableSP, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbxSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnSort, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();

        // Mã sản phẩm tự sinh

        // Kiểm tra "tên sản phẩm"
        if (txtTenSP.getText().equals("")) {
            sb.append("Tên Sản Phẩm không được để trống \n");
            txtTenSP.setBackground(Color.gray);
        } else {
            txtTenSP.setBackground(Color.white);
        }
        
        // Kiểm tra "Mô tả"
        if (txtMoTa.getText().equals("")) {
            sb.append("Mô tả không được để trống \n");
            txtMoTa.setBackground(Color.gray);
        } else {
            txtMoTa.setBackground(Color.white);
        }
        
        // Kiểm tra "Đơn vị tính"
        if (txtDvTinh.getText().equals("")) {
            sb.append("Đơn vị tính không được để trống \n");
            txtDvTinh.setBackground(Color.gray);
        } else {
            txtDvTinh.setBackground(Color.white);
        }

        // Kiểm tra "Số lượng"
        if (txtSL.getText().equals("")) {
            sb.append("Số Lượng không được để trống \n");
            txtSL.setBackground(Color.gray);
        } else {
            txtSL.setBackground(Color.white);
        }

        // Kiểm tra "Giá"
        if (txtGia.getText().equals("")) {
            sb.append("Giá tiền không được để trống \n");
            txtGia.setBackground(Color.gray);
        } else {
            txtGia.setBackground(Color.white);
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
                
        // Số lượng không hợp lệ
        if ( !dieukienSL() ){
            return;
        }
        
        // Giá không hợp lệ
        else if ( !dieukienGia() ){
            return;
        }
        
        else if( listSP.stream().filter(x->x.getTenSP().equalsIgnoreCase(txtTenSP.getText())).collect( Collectors.toList() ).size()!= 0 ){
            JOptionPane.showMessageDialog(this, "Đã tồn tại sản phẩm có tên này!");
            return;
        }
        
        SanPham a = new SanPham();
            a.setMaSP( "SP" + Integer.toString(++auto_MaSP) );
            a.setTenSP( txtTenSP.getText() );
            a.setMoTa( txtMoTa.getText() );
            a.setDonViTinh( txtDvTinh.getText() );
            a.setSoLuong( Integer.parseInt( txtSL.getText() ) );
            a.setGia( Double.parseDouble( txtGia.getText() ) );

            listSP.add(a);
            fillTable();

            // Thêm sản phẩm mới vào file
            try {
                FileWriter fw = new FileWriter("CSDL\\SanPham.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                
                bw.write(a.toString());
                bw.newLine();
                
                bw.close();
                fw.close();
            } catch (IOException e) {
            }
            
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        StringBuilder sb = new StringBuilder();

        if (txtTenSP.getText().equals("")) {
            sb.append("Tên Sản Phẩm không được để trống \n");
            txtTenSP.setBackground(Color.gray);
        } else {
            txtTenSP.setBackground(Color.white);
        }

        if (txtMoTa.getText().equals("")) {
            sb.append("Mô tả không được để trống \n");
            txtMoTa.setBackground(Color.gray);
        } else {
            txtMoTa.setBackground(Color.white);
        }

        if (txtDvTinh.getText().equals("")) {
            sb.append("Đơn vị tính không được để trống \n");
            txtDvTinh.setBackground(Color.gray);
        } else {
            txtDvTinh.setBackground(Color.white);
        }

        if (txtSL.getText().equals("")) {
            sb.append("Số Lượng không được để trống \n");
            txtSL.setBackground(Color.gray);
        } else {
            txtSL.setBackground(Color.white);
        }

        if (txtGia.getText().equals("")) {
            sb.append("Giá tiền không được để trống \n");
            txtGia.setBackground(Color.gray);
        } else {
            txtGia.setBackground(Color.white);
        }

        // Báo lỗi để trống thông tin
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Số lượng có hợp lệ không
        if( !dieukienSL() ){
            return;
        }
        
        // Giá tiền có hợp lệ không
        if( !dieukienGia() ){
            return;
        }
        
        // Hiện cửa sổ xác nhận
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật thông tin không? ", "Xác Nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION) {
            return;
        }

        // Cập nhật sản phẩm vừa sửa vào file
        try {
            // new FileWriter(path, true/false) : true thì là ghi tiếp vào nội dung đang có, false tức là ghi mới (xóa hết nội dung cũ)
            FileWriter fw = new FileWriter("CSDL\\SanPham.txt", false); 
            BufferedWriter bw = new BufferedWriter(fw);            
                
            // Ghi lại sản phẩm vừa sửa
            for(SanPham x : listSP){
                if( x.getMaSP().equalsIgnoreCase( txtMaSP.getText() ) ){
                    x.setTenSP(txtTenSP.getText());
                    x.setDonViTinh(txtDvTinh.getText());
                    x.setMoTa(txtMoTa.getText());
                    x.setGia( Double.parseDouble(txtGia.getText()) );
                    x.setSoLuong( Integer.parseInt( txtSL.getText())  );
                }
                bw.write(x.toString());
                bw.newLine();
            }
                
            bw.close();
            fw.close();
        } catch (IOException e) {
        }
        
        fillTable();
        
        JOptionPane.showMessageDialog(this, "Sửa thông tin sản phẩm thành công!");
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        StringBuilder sb = new StringBuilder();

        if (txtMaSP.getText().equals("")) {
            sb.append("Mã sản phẩm không được để trống \n");
            txtMaSP.setBackground(Color.gray);
        } else {
            txtMaSP.setBackground(Color.white);
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá sản phẩm không? ", "Xác Nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION) {
            return;
        }
        
        // Khi đồng ý xóa thì thực hiện xóa
        for (SanPham o : listSP) {
            if (o.getMaSP().equalsIgnoreCase(txtMaSP.getText())) {
                listSP.remove(o);
                break;
            }
        }
        
        // Ghi lại file sau khi xóa sản phẩm
        try {
            FileWriter fw = new FileWriter("CSDL\\SanPham.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (SanPham o : listSP) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
        }
        fillTable();
        
        JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!");
        
        btnRefreshActionPerformed(evt);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        txtMaSP.setText("");
        txtMaSP.setEnabled(true);
        txtTenSP.setText("");
        txtMoTa.setText("");
        txtDvTinh.setText("");
        txtSL.setText("");
        txtGia.setText("");
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        int SelectedRow = tblSP.getSelectedRow();
        txtMaSP.setEnabled(false);
        if (SelectedRow >= 0) {
            String maSP = (String) tblSP.getValueAt(SelectedRow, 0);
            for (SanPham sp : listSP) {
                if (maSP.equals(sp.getMaSP())) {
                    txtMaSP.setText(sp.getMaSP());
                    txtTenSP.setText(sp.getTenSP());
                    txtMoTa.setText(sp.getMoTa());
                    txtDvTinh.setText(sp.getDonViTinh());
                    txtSL.setText("" + sp.getSoLuong());
                    txtGia.setText("" + sp.getGia());
                    break;
                }
            }
        }
    }//GEN-LAST:event_tblSPMouseClicked

    private void txtSLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSLActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        MenuForm menuform = new MenuForm();
        menuform.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        ThongKeForm formThongKe = new ThongKeForm();
        formThongKe.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnQLNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLNCCActionPerformed
        QLyNCCForm formQLNCC = new QLyNCCForm();
        formQLNCC.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnQLNCCActionPerformed

    private void btnQLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLSPActionPerformed
        QLySPForm formQLSP = new QLySPForm();
        formQLSP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnQLSPActionPerformed

    private void btnQLDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLDHActionPerformed
        QLyDHForm formQLDH = new QLyDHForm();
        formQLDH.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnQLDHActionPerformed

    private void btnSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortActionPerformed
        // TODO add your handling code here:
        listSP.sort(Comparator.comparing(SanPham::getGia));
        
        // Đảo ngược danh sách sx tăng dần, đc danh sách sx giảm dần
        if( cbxSapXep.getSelectedIndex() == 1 ) Collections.reverse(listSP);
        
        // Ghi lại danh sách vừa sắp xếp vào file
        try {
            // new FileWriter(path, true/false) : true thì là ghi tiếp vào nội dung đang có, false tức là ghi mới (xóa hết nội dung cũ)
            FileWriter fw = new FileWriter("CSDL\\SanPham.txt", false); 
            BufferedWriter bw = new BufferedWriter(fw);            
                
            for(SanPham sp : listSP){
                bw.write(sp.toString());
                bw.newLine();
            }
                
            bw.close();
            fw.close();
        } catch (IOException e) {
        }
        
        fillTable();
        JOptionPane.showMessageDialog(this, "Sắp xếp thành công!");
    }//GEN-LAST:event_btnSortActionPerformed

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPActionPerformed

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
            java.util.logging.Logger.getLogger(QLySPForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLySPForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLySPForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLySPForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new QLySPForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTree jTree1;
    private javax.swing.JTable tblSP;
    private javax.swing.JTextField txtDvTinh;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
