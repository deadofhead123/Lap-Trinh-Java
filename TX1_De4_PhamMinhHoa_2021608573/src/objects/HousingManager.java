package objects;

import java.util.List;

public interface HousingManager {
	// Thêm mới bất động sản
	public boolean addHousing(Housing h);
	
	// Sửa thông tin bất động sản
	public boolean editHousing(Housing h);
	
	// Xóa bất động sản
	public boolean deleteHousing(Housing h);
	
	// Tìm kiếm bất động sản theo các tiêu chí
	public List<Housing> searchHousing(String name);
	
	//Sắp xếp bất động sản theo diện tích tăng hoặc giảm
	public List<Housing> sortedHousing(double acreage);
}
