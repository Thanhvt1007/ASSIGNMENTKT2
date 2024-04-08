package Testing;

import com.software.dao.LoaiSanPhamDAO;
import com.software.entity.LoaiSanPham;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDeleteLoaiSP {
    private LoaiSanPhamDAO loaiSanPhamDAO;

    @Before
    public void setUp() {
        loaiSanPhamDAO = new LoaiSanPhamDAO();
    }

    @Test
    public void testDeleteLoaiSPWithoutSelectingProduct() {
        try {
            // không chọn loại sản phẩm nào từ bảng
            LoaiSanPham selectedLoaiSP = null;
            // Kiểm tra xem có chọn loại sản phẩm hay không
            if (selectedLoaiSP != null) {
                // Thực hiện xóa
                loaiSanPhamDAO.delete(selectedLoaiSP.getMaLoai());
            } else {
                Assert.fail("Vui lòng chọn sản phẩm trước khi xóa");
            }
        } catch (NullPointerException e) {
            Assert.assertTrue("Thông báo vui lòng chọn sản phẩm thành công!", e.getMessage().contains("Vui lòng chọn sản phẩm trước khi xóa"));
        }
    }
    @Test
    public void testDeleteLoaiSPWithProducts() {
        try {
            // Nhấp đúp chuột vào sản phẩm trên bảng
            LoaiSanPham selectedLoaiSP = new LoaiSanPham();
            selectedLoaiSP.setMaLoai("LSP001");
            selectedLoaiSP.setTenLoai("Loại sản phẩm 1");
            selectedLoaiSP.setMoTa("Mô tả loại sản phẩm 1");
            
            // Thực hiện xóa
            loaiSanPhamDAO.delete(selectedLoaiSP.getMaLoai());
            Assert.fail("Không ném ra ngoại lệ khi xóa loại sản phẩm đang có sản phẩm");
        } catch (RuntimeException e) {
            Assert.assertTrue("Hiển thị thông báo loại sản phẩm đang có sản phẩm", e.getMessage().contains("Hiển thị thông báo loại sản phẩm đang có sản phẩm"));
        }
    }

    @Test
    public void testDeleteLoaiSPWithoutProducts() {
        try {
            // Nhấp đúp chuột vào sản phẩm trên bảng
            LoaiSanPham selectedLoaiSP = new LoaiSanPham();
            selectedLoaiSP.setMaLoai("LSP002");
            selectedLoaiSP.setTenLoai("Loại sản phẩm 2");
            selectedLoaiSP.setMoTa("Mô tả loại sản phẩm 2");
            
            // Thực hiện xóa
            loaiSanPhamDAO.delete(selectedLoaiSP.getMaLoai());
            Assert.fail("Không ném ra ngoại lệ khi xóa loại sản phẩm không có sản phẩm");
        } catch (RuntimeException e) {
            Assert.assertTrue("Hiển thị thông báo hỏi người dùng có chắc xóa loại sản phẩm này không", e.getMessage().contains("Hiển thị thông báo hỏi người dùng có chắc xóa loại sản phẩm này không"));
        }
    }

    @Test
    public void testDeleteLoaiSPWithConfirmation() {
        try {
            // Nhấp đúp chuột vào sản phẩm trên bảng
            LoaiSanPham selectedLoaiSP = new LoaiSanPham();
            selectedLoaiSP.setMaLoai("LSP003");
            selectedLoaiSP.setTenLoai("Loại sản phẩm 3");
            selectedLoaiSP.setMoTa("Mô tả loại sản phẩm 3");
            
            // Thực hiện xóa 	
            loaiSanPhamDAO.delete(selectedLoaiSP.getMaLoai());
            
            // Kiểm tra xem có thông báo xóa loại sản phẩm thành công hay không
            List<LoaiSanPham> deletedLoaiSP = loaiSanPhamDAO.selectByKeyWord("LSP003");
            Assert.assertTrue("Hiển thị thông báo xóa loại sản phẩm thành công", deletedLoaiSP.isEmpty());
        } catch (Throwable e) {
            Assert.fail("Ném ra ngoại lệ không mong muốn: " + e.getMessage());
        }
    }


    @After
    public void tearDown() {
    }
}
