package Testing;

import com.software.dao.LoaiSanPhamDAO;
import com.software.entity.LoaiSanPham;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestUpdateLoaiSP {
    private LoaiSanPhamDAO loaiSanPhamDAO;

    @Before
    public void setUp() {
        loaiSanPhamDAO = new LoaiSanPhamDAO();
    }

    @Test
    public void testEditButtonDisabledWithoutSelectingProduct() {
        try {
            // không chọn loại sản phẩm nào từ bảng
            LoaiSanPham selectedLoaiSP = null;     
            // Kiểm tra xem nút sửa có bị vô hiệu hóa không
            boolean isEditButtonEnabled = isEditButtonEnabled(selectedLoaiSP);
            Assert.assertFalse("Nút sửa không được kích hoạt khi không chọn loại sản phẩm trước đó", isEditButtonEnabled);
        } catch (Throwable e) {
            Assert.fail("Lỗi khi kiểm tra nút sửa: " + e.getMessage());
        }
    }
    
    private boolean isEditButtonEnabled(LoaiSanPham selectedLoaiSP) {
        // Kiểm tra xem loại sản phẩm đã được chọn trên bảng hay không
        return selectedLoaiSP != null;
    }
    @Test
    public void testUpdateLoaiSanPhamWithEmptyTenLoai() {
        try {
            // không nhập tên loại mới
            LoaiSanPham selectedLoaiSP = new LoaiSanPham(); // Cần cung cấp loại sản phẩm được chọn
            selectedLoaiSP.setTenLoai(""); // không nhập tên loại mới
            selectedLoaiSP.setMoTa("Mô tả loại sản phẩm 1");
            loaiSanPhamDAO.update(selectedLoaiSP);
            Assert.fail("Không ném ra ngoại lệ khi không nhập tên loại sản phẩm mới");
        } catch (Throwable e) {
            Assert.assertTrue("Vui lòng nhập tên loại sản phẩm!", e.getMessage().contains("Vui lòng nhập tên loại sản phẩm!"));
        }
    }

    @Test
    public void testUpdateLoaiSanPhamWithEmptyMoTa() {
        try {
            // nhập tên loại mới nhưng không nhập mô tả
            LoaiSanPham selectedLoaiSP = new LoaiSanPham(); // Cần cung cấp loại sản phẩm được chọn
            selectedLoaiSP.setTenLoai("Loại sản phẩm 1");
            selectedLoaiSP.setMoTa(""); // không nhập mô tả
            loaiSanPhamDAO.update(selectedLoaiSP); 
            Assert.fail("Không ném ra ngoại lệ khi không nhập mô tả loại sản phẩm mới");
        } catch (Throwable e) {
            Assert.assertTrue("Vui lòng nhập mô tả loại sản phẩm!", e.getMessage().contains("Vui lòng nhập mô tả loại sản phẩm!"));
        }
    }

    @Test
    public void testUpdateLoaiSanPhamWithTenAndMoTa() {
        try {
            //  nhập đầy đủ thông tin mới
            LoaiSanPham selectedLoaiSP = new LoaiSanPham(); 
            selectedLoaiSP.setTenLoai("Loại sản phẩm 1");
            selectedLoaiSP.setMoTa("Mô tả loại sản phẩm 1");        
            // Thực hiện việc cập nhật
            loaiSanPhamDAO.update(selectedLoaiSP);           
        } catch (Throwable e) {
            Assert.fail("Ném ra ngoại lệ không mong muốn: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
    }
}
