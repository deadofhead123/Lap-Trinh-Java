package GUI;

/**
 * @author Phạm Minh Hòa
 * Các nguồn tài liệu đã tham khảo (Giữ nút Ctrl và click vào link là vào được luôn)
 * 
 * ++++ Tutorial:
 * - Chèn dữ liệu vào bảng từ ArrayList: https://stackoverflow.com/questions/20526917/load-arraylist-data-into-jtable 
 * 
 * - Update bảng từ dòng đã chọn: https://www.youtube.com/watch?v=Tg62AxNRir4&list=PLv1XiDDk9Y4dL6tCfVND9OUCWDoUZ7fz1&index=3: 
 * 
 * - Lọc điều kiện tìm kiếm:	https://www.youtube.com/watch?v=btA5RzD-Zn0
 * 
 * - Xóa dòng trong bảng: https://www.youtube.com/watch?v=OsgX1grOJZA&t=84s
 * 
 * - RadioButton: https://www.youtube.com/watch?v=gKLlXKjceDs
 * 
 * - Tạo nút "OK": https://www.youtube.com/watch?v=jFjq0qMxiKU
 * 
 * - Gán icon cho nút: https://www.youtube.com/watch?v=gmmsKvt6eBM
 * 
 * - Tuỳ chỉnh kích cỡ của icon trong nút: https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel/16345968
 * 
 * - Tùy chỉnh tiêu đề bảng: https://www.youtube.com/watch?v=4LLe6DS0fM0
 * 
 * - Tùy chỉnh kích cỡ tiêu đề bảng: https://stackoverflow.com/questions/2343391/creating-multi-line-header-for-jtable
 * 
 * - Tùy chỉnh độ cao của dòng: https://stackoverflow.com/questions/15853720/setting-the-height-of-a-row-in-a-jtable-in-java
 * 
 * - Quan trọng và khó nhai nhất: Làm việc với file
 * 		+ Ghi file: https://www.youtube.com/watch?v=Y5Px95AU1Ws
 * 
 * 	    + Đọc file: https://www.youtube.com/watch?v=E_BkxGiFcTs
 * 
 * 		+ Ghi đối tượng vào file: https://www.youtube.com/watch?v=zskL0Nje1Bg
 * 
 * 		+ Đọc đối tượng từ file: https://www.youtube.com/watch?v=p7YsBED-rEw
 *
 * - Tạo nút Exit: https://stackoverflow.com/questions/22286695/create-an-exit-button-in-java
 * 
 * ++++ Nguồn lấy tài nguyên:
 * - Nơi lấy icon cho nút: 	www.iconarchive.com
 */

import java.awt.EventQueue;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import button_Image.LinkToImage;
import classes.Housing;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class HousingManager_Form extends JFrame{

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JTable tableBDS;
	
	private JTextField txtProductName;
	private JTextField txtProductPrice;
	private JTextField txtProductTotal;
	private JTextField txtType;
	private JTextField txtAcreage;
	private JTextField txtYear;
	private JTextField txtID;
	private JTextField txtSearch;
	
	static Housing DSBDS = new Housing();
	static DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HousingManager_Form frame = new HousingManager_Form();
					
					frame.setVisible(true);	
					
					// Lệnh "setLocationRelativeTo" nếu ta ko truyền vào tham số thì vị trí hiển thị của cửa số sẽ ở giữa màn hình chính
					frame.setLocationRelativeTo(null);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
		
	}
	
 
	/**
	 * Create the frame.
	 */
	public HousingManager_Form() {
		setTitle("Phạm Minh Hòa - 2021608573 - Java sáng t6 (tiết 1,2,3)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 790);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Housing Manager");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(565, 11, 232, 27);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.add(lblNewLabel);
		
		
		// Xử lí nút Thêm mới
		JButton btn_addHousing = new JButton("Add");
		btn_addHousing.setToolTipText("Thêm mới bất động sản vào trong hệ thống với các thông tin nhập vào ô text");
		/*
		 * URL imageURL = HousingManager_Form.class.getResource("Add_Icon resized.png");
		 * Image addIcon = Toolkit.getDefaultToolkit().createImage(imageURL);
		 * 
		 * label.setIcon(new ImageIcon(new ImageIcon("icon.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
		 * 
		 * Tham số thứ 3 trong getScaleInstance: Mỗi tham số khác nhau sẽ cho hình ảnh có chất lượng khác nhau
		 *
		 * Image.SCALE_DEFAULT: Uses the default scaling algorithm of the platform.						 - có thể coi là lv1
		   Image.SCALE_FAST: Uses a fast but lower quality scaling algorithm.							 - lv2
		   Image.SCALE_SMOOTH: Uses a slower but higher quality scaling algorithm.						 - lv3
		   Image.SCALE_REPLICATE: Replicates the source image pixels to achieve the desired dimensions.  - lv4
		 */
		btn_addHousing.setIcon( new ImageIcon( new ImageIcon( LinkToImage.class.getResource("Add_Icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT) ) );
		btn_addHousing.setForeground(new Color(0, 102, 0));
		btn_addHousing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				model = (DefaultTableModel) tableBDS.getModel();
				
				String ten = txtProductName.getText();
				String gia = txtProductPrice.getText();
				String sl = txtProductTotal.getText();
				String loai = txtType.getText();
				String dientich = txtAcreage.getText();
				String nam = txtYear.getText();
				
				if(ten.equals("") || gia.equals("") || sl.equals("") || loai.equals("") || dientich.equals("") || nam.equals("")) 
					JOptionPane.showMessageDialog(contentPane, "Data is empty, please input!");
				else {
					Housing x = new Housing(ten, Double.parseDouble(gia), Integer.parseInt(sl), 
							  				loai, Double.parseDouble(dientich), Integer.parseInt(nam) );
					
					if( new Housing().addHousing(x) ) {
						// Thêm đối tượng mới vào file
						try {							
							FileOutputStream fos = new FileOutputStream("Housing.bin");
							ObjectOutputStream oos = new ObjectOutputStream(fos);
							
							for(Housing addH : DSBDS.List_housing) oos.writeObject(addH);
							
							oos.close();
							
							JOptionPane.showMessageDialog(contentPane, "Added successfully, information is saved at \"Housing.bin\" !");
							
							Object [] new_data = {x.getProduct_id(), x.getProduct_name(), x.getProduct_price(), x.getProduct_total(),
												  x.getHousing_type(), x.getHousing_acreage(), x.getHousing_year()};
							
							model.addRow(new_data);							
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}
						
					}
					
				}
				
			}
		});
		btn_addHousing.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_addHousing.setBounds(51, 641, 120, 40);
		contentPane.add(btn_addHousing);
		
		
		// Xử lí nút Sửa thông tin
		JButton btn_editHousing = new JButton("Edit");
		btn_editHousing.setToolTipText("Sửa thông tin bất động sản có trong hệ thống với danh sách được hiển thị");
		btn_editHousing.setIcon( new ImageIcon( new ImageIcon( LinkToImage.class.getResource("Edit_Icon.png") ).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)  ) );
		btn_editHousing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableBDS.getSelectedRowCount() == 1) {
					model = (DefaultTableModel) tableBDS.getModel();
					
					boolean add_success = false;
					
					// Cần chọn 1 dòng nào đó để sửa
					if(tableBDS.getSelectedRowCount() == 1) {
						
						for(int i = 0 ; i < DSBDS.List_housing.size() ; i++) {
							
							// Lấy ra đối tượng ở chỉ số i
							Housing h_edit = DSBDS.List_housing.get(i);
							
							if(h_edit.getProduct_id().equals( txtID.getText().toString() ) ){
								// Cập nhật thông tin cho đối tượng
								String ten = txtProductName.getText();
								String gia = txtProductPrice.getText();
								String sl = txtProductTotal.getText();
								String loai = txtType.getText();
								String dientich = txtAcreage.getText();
								String nam = txtYear.getText();			
								
								if(ten.equals("") || gia.equals("") || sl.equals("") || loai.equals("") 
								    || dientich.equals("") || nam.equals("")) {
									JOptionPane.showMessageDialog(contentPane, "Data is empty, please input!");	
								}
								else {
									h_edit.setProduct_name( ten );
									h_edit.setProduct_price( Double.parseDouble( gia ) );
									h_edit.setProduct_total( Integer.parseInt(sl) );
									h_edit.setHousing_type( loai );
									h_edit.setHousing_acreage( Double.parseDouble( dientich ) );
									h_edit.setHousing_year( Integer.parseInt( nam ) );
									
									add_success = new Housing().editHousing(h_edit);
									DSBDS.List_housing.set(i, h_edit);
									
									// Ghi lại vào file
									try {
										FileOutputStream fos = new FileOutputStream("Housing.bin");
										ObjectOutputStream oos = new ObjectOutputStream(fos);
										
										for(Housing addH : DSBDS.List_housing) oos.writeObject(addH);
										
										oos.close();
									}
									catch(Exception ex){
										ex.printStackTrace();
									}
									
									// Cập nhật lại thông tin vào trong bảng
									model.setValueAt(txtProductName.getText(), tableBDS.getSelectedRow(), 1);
									model.setValueAt(txtProductPrice.getText(), tableBDS.getSelectedRow(), 2);
									model.setValueAt(txtProductTotal.getText(), tableBDS.getSelectedRow(), 3);
									model.setValueAt(txtType.getText(), tableBDS.getSelectedRow(), 4);
									model.setValueAt(txtAcreage.getText(), tableBDS.getSelectedRow(), 5);
									model.setValueAt(txtYear.getText(), tableBDS.getSelectedRow(), 6);
									
									break;
								}
								
							}
						}
						
						if( add_success ) JOptionPane.showMessageDialog(contentPane, "Edit successfully!");
						else JOptionPane.showMessageDialog(contentPane, "Edit failed!");
					}
					
				}
			}
		});
		btn_editHousing.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_editHousing.setBounds(217, 641, 120, 40);
		contentPane.add(btn_editHousing);
		

		// Xử lí nút xóa thông tin
		JButton btn_deleteHousing = new JButton("Delete");
		btn_deleteHousing.setToolTipText("Xóa thông tin bất động sản đã chọn trên danh sách");
		btn_deleteHousing.setIcon( new ImageIcon( new ImageIcon( LinkToImage.class.getResource("Delete_Icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT) ) );
		btn_deleteHousing.setForeground(new Color(204, 51, 0));
		btn_deleteHousing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = (DefaultTableModel) tableBDS.getModel();
				
				if( tableBDS.getSelectedRowCount() == 1 ) {
					for(Housing h : DSBDS.List_housing) {
						if( h.getProduct_id().equals( model.getValueAt( tableBDS.getSelectedRow(), 0).toString())) {
							new Housing().deleteHousing(h);
							
							txtID.setText("");
							txtProductName.setText("");
							txtProductPrice.setText("");
							txtProductTotal.setText("");
							txtType.setText("");
							txtAcreage.setText("");
							txtYear.setText("");
							
							// Cập nhật lại thông tin
							try {
								FileOutputStream fos = new FileOutputStream("Housing.bin");
								ObjectOutputStream oos = new ObjectOutputStream(fos);
								
								for(Housing addH : DSBDS.List_housing) oos.writeObject(addH);
								
								oos.close();
							}
							catch(Exception ex){
								ex.printStackTrace();
							}
							
							model.removeRow( tableBDS.getSelectedRow() );
							
							JOptionPane.showMessageDialog(contentPane, "Delete successfully!");
						}
					}
				}
			}
		});
		btn_deleteHousing.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_deleteHousing.setBounds(402, 641, 120, 40);
		contentPane.add(btn_deleteHousing);
		
		
		JScrollPane scrollPane_DSBDS = new JScrollPane();
		scrollPane_DSBDS.setBounds(41, 139, 867, 482);
		contentPane.add(scrollPane_DSBDS);
		
		
		// Xử lí bảng danh sách
		tableBDS = new JTable();
		// Xử lí chọn dòng trong bảng: 
		// khi chọn 1 dòng thì các thông tin của dòng đó sẽ hiển thị vào các ô Text tương ứng
		tableBDS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtID.setText( model.getValueAt(tableBDS.getSelectedRow(), 0).toString() );
				txtProductName.setText(model.getValueAt(tableBDS.getSelectedRow(), 1).toString());
				txtProductPrice.setText(model.getValueAt(tableBDS.getSelectedRow(), 2).toString());
				txtProductTotal.setText( model.getValueAt(tableBDS.getSelectedRow(), 3).toString() );
				txtType.setText( model.getValueAt(tableBDS.getSelectedRow(), 4).toString() );
				txtAcreage.setText( model.getValueAt(tableBDS.getSelectedRow(), 5).toString() );
				txtYear.setText( model.getValueAt(tableBDS.getSelectedRow(), 6).toString() );
			}
		});
		tableBDS.setFont(new Font("Tahoma", Font.PLAIN, 14));	
		tableBDS.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product ID", "Product name", "Product price", "Product total", "Type", "Acreage", "Year"
			}
		));
		scrollPane_DSBDS.setViewportView(tableBDS);
		// Format tiêu đề bảng
		JTableHeader T_Header = tableBDS.getTableHeader();
		T_Header.setBackground(Color.blue);
		T_Header.setForeground(Color.white);
		tableBDS.getTableHeader().setPreferredSize( new Dimension(tableBDS.getColumnModel().getTotalColumnWidth(), 30));
						
		// Format cột ID
		tableBDS.getColumnModel().getColumn(0).setPreferredWidth(1);
						
		// Đổi màu của text trong cột ID
		DefaultTableCellRenderer color_column = new DefaultTableCellRenderer();
		color_column.setForeground(Color.red);
		tableBDS.getColumnModel().getColumn(0).setCellRenderer(color_column);
		
		// Format cột giá
		tableBDS.getColumnModel().getColumn(2).setPreferredWidth(32);
				
		// Format cột Số lượng
		tableBDS.getColumnModel().getColumn(3).setPreferredWidth(10);
						
		// Format cột Năm cấp sổ đỏ
		tableBDS.getColumnModel().getColumn(6).setPreferredWidth(32);
		
		// Chỉnh kích cỡ các dòng
		tableBDS.setRowHeight(20);
		
		// Hiển thị dữ liệu trong file đã lưu ra bảng
		try {
			FileInputStream fis = new FileInputStream("Housing.bin");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			model = (DefaultTableModel) tableBDS.getModel();
			Housing h = null;
			
			while((h = (Housing) ois.readObject()) != null) {
				String check_id = h.getProduct_id();
				
				Object [] new_data = {h.getProduct_id(), h.getProduct_name(), h.getProduct_price(), h.getProduct_total(),
						  h.getHousing_type(), h.getHousing_acreage(), h.getHousing_year()};
				
				model.addRow(new_data);
				DSBDS.List_housing.add(h);
				DSBDS.cnt_id = Math.max(DSBDS.cnt_id, Integer.parseInt(check_id.substring(3) ) );
			}
			
			ois.close();
		}
		catch(FileNotFoundException fnf) {
			JOptionPane.showMessageDialog(contentPane, "No file existed!");
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(contentPane, "Read all data from file Housing.bin!");
		}
		
		if(DSBDS.cnt_id > 0) DSBDS.cnt_id++;
		
		
		JLabel lblNewLabel_1 = new JLabel("Product Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(973, 219, 108, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Product Price");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(973, 279, 106, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Product Total");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(973, 336, 106, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Type");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(973, 390, 34, 17);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Acreage");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(973, 451, 56, 17);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Year");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_5.setBounds(973, 510, 106, 14);
		contentPane.add(lblNewLabel_1_5);
		
		txtProductName = new JTextField();
		lblNewLabel_1.setLabelFor(txtProductName);
		txtProductName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtProductName.setBounds(1105, 209, 200, 35);
		contentPane.add(txtProductName);
		txtProductName.setColumns(10);
		
		txtProductPrice = new JTextField();
		lblNewLabel_1_1.setLabelFor(txtProductPrice);
		txtProductPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtProductPrice.setColumns(10);
		txtProductPrice.setBounds(1105, 269, 200, 35);
		contentPane.add(txtProductPrice);
		
		txtProductTotal = new JTextField();
		lblNewLabel_1_2.setLabelFor(txtProductTotal);
		txtProductTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtProductTotal.setColumns(10);
		txtProductTotal.setBounds(1105, 326, 200, 35);
		contentPane.add(txtProductTotal);
		
		txtType = new JTextField();
		lblNewLabel_1_3.setLabelFor(txtType);
		txtType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtType.setColumns(10);
		txtType.setBounds(1105, 383, 200, 35);
		contentPane.add(txtType);
		
		txtAcreage = new JTextField();
		lblNewLabel_1_4.setLabelFor(txtAcreage);
		txtAcreage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAcreage.setColumns(10);
		txtAcreage.setBounds(1105, 441, 200, 35);
		contentPane.add(txtAcreage);
		
		txtYear = new JTextField();
		lblNewLabel_1_5.setLabelFor(txtYear);
		txtYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtYear.setColumns(10);
		txtYear.setBounds(1105, 500, 200, 35);
		contentPane.add(txtYear);
		
		JLabel lblNewLabel_1_6 = new JLabel("ID");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_6.setBounds(973, 160, 108, 14);
		contentPane.add(lblNewLabel_1_6);
		
		
		// Tẩy đi các dòng nhập thông tin để nhập mới
		JButton btn_renew = new JButton("Refresh");
		btn_renew.setIcon( new ImageIcon( new ImageIcon( LinkToImage.class.getResource("Renew_Icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT) ) );
		btn_renew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtID.setText("");
				txtProductName.setText("");
				txtProductPrice.setText("");
				txtProductTotal.setText("");
				txtType.setText("");
				txtAcreage.setText("");
				txtYear.setText("");
				
				model = (DefaultTableModel) tableBDS.getModel();
				model.setRowCount(0);
				
				ArrayList<Housing> showList = new ArrayList<>();
				
				try {
					FileInputStream fis = new FileInputStream("Housing.bin");
					ObjectInputStream ois = new ObjectInputStream(fis);
					
					Housing h = null;
					
					while((h = (Housing) ois.readObject()) != null) {
						Object [] new_data = {h.getProduct_id(), h.getProduct_name(), h.getProduct_price(), h.getProduct_total(),
								  h.getHousing_type(), h.getHousing_acreage(), h.getHousing_year()};
						model.addRow(new_data);
						
						showList.add(h);
					}
					
					ois.close();
				}
				catch(FileNotFoundException fnf) {
					JOptionPane.showMessageDialog(contentPane, "File not found!");
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(contentPane, "All data is read!");
				}
				
				DSBDS.List_housing = new ArrayList<>(showList);
				
			}
		});
		btn_renew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_renew.setBounds(766, 641, 120, 40);
		contentPane.add(btn_renew);
				
				
		// Xử lí phần tìm kiếm ID: khi nhập vào ID sẽ tìm ra bất động sản chứa ID đã nhập
		txtID = new JTextField();
		lblNewLabel_1_6.setLabelFor(txtID);
		txtID.setForeground(Color.RED);
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtID.setColumns(10);
		txtID.setBounds(1105, 150, 200, 35);
		contentPane.add(txtID);
		

		JComboBox cbx_TypeSearch = new JComboBox();
		cbx_TypeSearch.setToolTipText("Chọn tiêu chí tìm kiếm");
		cbx_TypeSearch.setModel(new DefaultComboBoxModel(new String[] {"Name", "Price", "Type", "Acreage", "Year"}));
		cbx_TypeSearch.setSelectedIndex(0);
		cbx_TypeSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbx_TypeSearch.setBounds(51, 90, 120, 24);
		contentPane.add(cbx_TypeSearch);
		
		
		// Xử lí nút Tìm kiếm
		JButton btn_searchHousing = new JButton("Search");
		btn_searchHousing.setToolTipText("Tìm kiếm thông tin của bất động sản với tiêu chí đã chọn (phải nhập giá trị của tiêu chí)");
		btn_searchHousing.setIcon( new ImageIcon( new ImageIcon( LinkToImage.class.getResource("Seach_Icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT) ) );
		btn_searchHousing.setForeground(Color.BLACK);
		btn_searchHousing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					model = (DefaultTableModel) tableBDS.getModel();
					List<Housing> result_search = new ArrayList<Housing>();
					
					int idx_option = cbx_TypeSearch.getSelectedIndex();
					
					String search = txtSearch.getText();
					
					if(idx_option == 0) {
						if( search.equals("") ) JOptionPane.showMessageDialog(contentPane, "Input product name!");
						else result_search =  new Housing().searchHousing( search + " 0" );
					}
					else if(idx_option == 1) {
						if( search.equals("") ) JOptionPane.showMessageDialog(contentPane, "Input product price!");
						else result_search = new Housing().searchHousing( search + " 1" );
					}
					else if(idx_option == 2) {
						if( search.equals("") ) JOptionPane.showMessageDialog(contentPane, "Input type!");
						else result_search = new Housing().searchHousing( search + " 2" );
					}
					else if(idx_option == 3) {
						if( search.equals("") ) JOptionPane.showMessageDialog(contentPane, "Input acreage!");
						else result_search = new Housing().searchHousing( search + " 3");
					}
					else if(idx_option == 4) {
						if( search.equals("") ) JOptionPane.showMessageDialog(contentPane, "Input year!");
						else result_search = new Housing().searchHousing( search + " 4");
					}				
					
					if(result_search.size() == 0) JOptionPane.showMessageDialog(contentPane, "No result!");
					else {
						model.setRowCount(0);
						for(Housing h : result_search) {
							Object [] new_data = {h.getProduct_id(), h.getProduct_name(), h.getProduct_price(), h.getProduct_total(),
												  h.getHousing_type(), h.getHousing_acreage(), h.getHousing_year()};
											
							model.addRow(new_data);
						}
						
						JOptionPane.showMessageDialog(contentPane, "Search successfully!");
					}
				}
			});
			btn_searchHousing.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btn_searchHousing.setBounds(437, 81, 140, 40);
			contentPane.add(btn_searchHousing);
			
			
			JComboBox cbx_SortType = new JComboBox();
			cbx_SortType.setToolTipText("Chọn tiêu chí sắp xếp");
			cbx_SortType.setModel(new DefaultComboBoxModel(new String[] {"Ascending", "Descending"}));
			cbx_SortType.setSelectedIndex(0);
			cbx_SortType.setFont(new Font("Tahoma", Font.PLAIN, 13));
			cbx_SortType.setBounds(581, 694, 140, 24);
			contentPane.add(cbx_SortType);
			
			
			// Xử lí nút "Sắp xếp"
			JButton btn_sortedHousing = new JButton("Sort");
			btn_sortedHousing.setToolTipText("Sắp xếp danh sách bất động sản theo giá với kiểu sắp xếp đã chọn");
			btn_sortedHousing.setIcon( new ImageIcon( new ImageIcon( LinkToImage.class.getResource("Sort_Icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT) ) );
			btn_sortedHousing.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					model = (DefaultTableModel) tableBDS.getModel();
					int sort_option = (cbx_SortType.getSelectedIndex());
					
					List<Housing> result_sort = new Housing().sortedHousing( (double)sort_option );
					
					// Cập nhật file
					try {
						FileOutputStream fos = new FileOutputStream("Housing.bin");
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						
						for(Housing addH : DSBDS.List_housing) oos.writeObject(addH);
						
						oos.close();
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
					
					model.setRowCount(0);
					
					for(Housing h : result_sort) {
						Object [] new_data = {h.getProduct_id(), h.getProduct_name(), h.getProduct_price(), h.getProduct_total(),
											  h.getHousing_type(), h.getHousing_acreage(), h.getHousing_year()};
										
						model.addRow(new_data);
					}
					
					JOptionPane.showMessageDialog(contentPane, "Sorted successfully!");
				}
			});
			btn_sortedHousing.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btn_sortedHousing.setBounds(581, 641, 140, 40);
			contentPane.add(btn_sortedHousing);
			
			
			// Xử lí nút "Thoát"
			JButton btn_Exit = new JButton("Exit");
			btn_Exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame exitFrame = new JFrame("Exit");

				    if (JOptionPane.showConfirmDialog
				    		( exitFrame,"Do you want to exit?","Exit program", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION )
				            System.exit(0);
				}
			});
			btn_Exit.setToolTipText("Thoát chương trình");
			btn_Exit.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btn_Exit.setBounds(1201, 678, 120, 40);
			contentPane.add(btn_Exit);
			
			txtSearch = new JTextField();
			txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtSearch.setColumns(10);
			txtSearch.setBounds(214, 87, 200, 30);
			contentPane.add(txtSearch);
			
			
	}
}
