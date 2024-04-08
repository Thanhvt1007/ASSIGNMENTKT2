package Testing;

import com.software.dao.LoaiSanPhamDAO;
import com.software.entity.LoaiSanPham;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestInsertLoaiSP {
    private LoaiSanPhamDAO loaiSanPhamDAO;

    @Before
    public void setUp() {
        loaiSanPhamDAO = new LoaiSanPhamDAO();
    }

    @Test
    public void testInsertLoaiSanPham() {
        LoaiSanPham newLoaiSP = new LoaiSanPham();
        newLoaiSP.setMaLoai("LSP001");
        newLoaiSP.setTenLoai("Loại sản phẩm 1");
        newLoaiSP.setMoTa("Mô tả loại sản phẩm 1");
        // Số lượng LoaiSanPham trước khi thêm
        int sizeBeforeInsert = loaiSanPhamDAO.SelectAll().size();
        // Thêm mới LoaiSanPham vào cơ sở dữ liệu
        loaiSanPhamDAO.insert(newLoaiSP);
        // Số lượng LoaiSanPham sau khi thêm
        int sizeAfterInsert = loaiSanPhamDAO.SelectAll().size();
        // Kiểm tra xem số lượng LoaiSanPham đã tăng lên sau khi thêm
        Assert.assertEquals(sizeBeforeInsert + 1, sizeAfterInsert);
        // Kiểm tra xem LoaiSanPham mới đã được chèn vào cơ sở dữ liệu chưa
        List<LoaiSanPham> loaiSanPhams = loaiSanPhamDAO.selectByKeyWord("LSP001");
        Assert.assertFalse(loaiSanPhams.isEmpty());
    }
    @Test
    public void testInsertLoaiSanPhamWithEmptyTenLoai() {
        // Tạo một đối tượng LoaiSanPham mới mà không nhập tên loại
        LoaiSanPham newLoaiSP = new LoaiSanPham();
        newLoaiSP.setMaLoai("LSP001");
        newLoaiSP.setTenLoai(""); 
        newLoaiSP.setMoTa("Mô tả loại sản phẩm 1");
        int sizeBeforeInsert = loaiSanPhamDAO.SelectAll().size();
        loaiSanPhamDAO.insert(newLoaiSP);
        int sizeAfterInsert = loaiSanPhamDAO.SelectAll().size();
        Assert.assertEquals(sizeBeforeInsert, sizeAfterInsert);
        List<LoaiSanPham> loaiSanPhams = loaiSanPhamDAO.selectByKeyWord("LSP001");
        Assert.assertTrue(loaiSanPhams.isEmpty());
    }
    @Test
    public void testInsertLoaiSanPhamEmptyML() {
//    	K NHẬP ML
        LoaiSanPham newLoaiSP = new LoaiSanPham();
        newLoaiSP.setMaLoai("");
        newLoaiSP.setTenLoai("Loại sản phẩm 1");
        newLoaiSP.setMoTa("Mô tả loại sản phẩm 1");
        int sizeBeforeInsert = loaiSanPhamDAO.SelectAll().size();
        loaiSanPhamDAO.insert(newLoaiSP);
        int sizeAfterInsert = loaiSanPhamDAO.SelectAll().size();
        Assert.assertEquals(sizeBeforeInsert + 1, sizeAfterInsert);
        List<LoaiSanPham> loaiSanPhams = loaiSanPhamDAO.selectByKeyWord("LSP001");
        Assert.assertFalse(loaiSanPhams.isEmpty());
    }
    @Test
    public void testInsertLoaiSanPhamWithDuplicateMaLoai() {
//    	TRÙNG ML
        LoaiSanPham newLoaiSP = new LoaiSanPham();
        newLoaiSP.setMaLoai("1");
        newLoaiSP.setTenLoai("Loại sản phẩm 1");
        newLoaiSP.setMoTa("Mô tả loại sản phẩm 1");
        int sizeBeforeInsert = loaiSanPhamDAO.SelectAll().size();
        loaiSanPhamDAO.insert(newLoaiSP);
        int sizeAfterInsert = loaiSanPhamDAO.SelectAll().size();
        Assert.assertEquals(sizeBeforeInsert, sizeAfterInsert);
    }
    @After
    public void tearDown() {
        loaiSanPhamDAO.delete("LSP001");
    }
}
