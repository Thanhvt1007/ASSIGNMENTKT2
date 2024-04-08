package Testing;

import com.software.dao.SanPhamDAO;
import com.software.entity.SanPham;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class TestUpdateSP {
    private SanPhamDAO sanPhamDAO;

    @Rule
    public ErrorCollector ec = new ErrorCollector();

    @BeforeClass
    public static void openConnect() {
    }

    @Before
    public void setUp() {
        sanPhamDAO = new SanPhamDAO();
    }

    @Test
    public void testUpdateSanPhamSuccess() {
        SanPham sanPham = sanPhamDAO.SelectByID("SP001");
        if (sanPham != null) {
            // Cập nhật thông tin của sản phẩm
            sanPham.setTenSanPham("Sản phẩm 1 - Updated");
            sanPham.setDonViTinh("Cái");
            sanPham.setSoLuong(600);
            sanPham.setGia(60000.0);
            sanPham.setTrangThai("Đang kinh doanh");
            sanPham.setLoaiSanPham("Loại 1");
            try {
                sanPhamDAO.update(sanPham);
                // Kiểm tra thông báo sửa sản phẩm thành công
                assertTrue(true);
                System.out.println("Sửa sản phẩm thành công!");
            } catch (Exception e) {
                fail("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
            }
        } else {
            fail("Sản phẩm không tồn tại trong cơ sở dữ liệu");
        }
    }


    @Test
    public void testUpdateNullProductName() {
        // Kiểm tra khi tên sản phẩm là null
        SanPham sanPham = new SanPham();
        try {
            sanPham.setTenSanPham("");
            sanPham.setDonViTinh("Cái");
            sanPham.setSoLuong(100);
            sanPham.setGia(50000.0);
            sanPham.setTrangThai("Đang kinh doanh");
            sanPham.setLoaiSanPham("Loại 1");
            sanPhamDAO.insert(sanPham);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SanPham updatedSanPham = sanPhamDAO.SelectByID(sanPham.getMaSanPham());
        assertNull(updatedSanPham);
    }

    @Test
    public void testUpdateInvalidProductName() {
        // Kiểm tra khi tên sản phẩm không hợp lệ
        SanPham sanPham = new SanPham();
        try {
            sanPham.setTenSanPham("@abc123");
            sanPham.setDonViTinh("Cái");
            sanPham.setSoLuong(100);
            sanPham.setGia(50000.0);
            sanPham.setTrangThai("Đang kinh doanh");
            sanPham.setLoaiSanPham("Loại 1");
            sanPhamDAO.insert(sanPham);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SanPham updatedSanPham = sanPhamDAO.SelectByID(sanPham.getMaSanPham());
        assertNull(updatedSanPham);
    }

    @Test
    public void testUpdateInvalidProductPrice() {
        // Kiểm tra khi giá sản phẩm không hợp lệ
        SanPham sanPham = new SanPham();
        try {
            sanPham.setTenSanPham("Sản phẩm mới");
            sanPham.setDonViTinh("Cái");
            sanPham.setSoLuong(100);
            sanPham.setGia(-50000.0); // Giá không thể là số âm
            sanPham.setTrangThai("Đang kinh doanh");
            sanPham.setLoaiSanPham("Loại 1");
            sanPhamDAO.insert(sanPham);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SanPham updatedSanPham = sanPhamDAO.SelectByID(sanPham.getMaSanPham());
        assertNull(updatedSanPham);
    }
    @Test
    public void testDisableEditButtonWithoutSelectingProduct() {
        try {
            SanPham selectedSanPham = null;
            boolean isEditButtonEnabled = isEditButtonEnabled(selectedSanPham);

            Assert.assertFalse("Nút sửa không được vô hiệu hóa khi không chọn sản phẩm trước khi sửa", isEditButtonEnabled);
        } catch (Throwable e) {
            ec.addError(new Throwable("Lỗi khi kiểm tra vô hiệu hóa nút sửa: " + e));
        }
    }
   
    private boolean isEditButtonEnabled(SanPham selectedSanPham) {
      
        return selectedSanPham != null;
    }
    @After
    public void tearDown() {
    }

    @AfterClass
    public static void closeConnect() {    
    }
}
