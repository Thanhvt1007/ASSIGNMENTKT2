package Testing;

import com.software.dao.SanPhamDAO;
import com.software.entity.SanPham;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class TestInsertSanPham {

    SanPhamDAO sanPhamDAO = new SanPhamDAO();
    @Rule
    public ErrorCollector ec = new ErrorCollector();
    static int count, index;

    public int countSanPham() {
        int temp = 0;
        try {
            List<SanPham> sanPhamList = sanPhamDAO.SelectAll();
            temp = sanPhamList.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    @Before
    public void beforeSanPham() {
        count = countSanPham();
        System.out.println("Số lượng sản phẩm trước khi thêm: " + count);
    }

    @Test
    public void testInsertSanPham() {
        try {
            SanPham sanPham = new SanPham();
            sanPham.setMaSanPham("SP001");
            sanPham.setTenSanPham("Sản phẩm 1");
            sanPham.setDonViTinh("Cái");
            sanPham.setSoLuong(100);
            sanPham.setGia(50000.0);
            sanPham.setTrangThai("Đang kinh doanh");
            sanPham.setLoaiSanPham("Loại 1");

            sanPhamDAO.insert(sanPham);
        } catch (Exception e) {
            ec.addError(new Throwable("Lỗi khi thêm sản phẩm: " + e));
        }
        SanPham insertedSanPham = sanPhamDAO.SelectByID("SP001");
        try {
            Assert.assertNotNull("Sản phẩm đã được thêm thành công", insertedSanPham);
        } catch (Throwable e) {
            ec.addError(new Throwable("Lỗi kiểm tra sản phẩm sau khi thêm: " + e));
        }
    }

    @Test
    public void testInsertSanPhamNullMASP() {
        // Thêm sản phẩm mới
        try {
            SanPham sanPham = new SanPham();
            sanPham.setMaSanPham("");
            sanPham.setTenSanPham("Sản phẩm 1");
            sanPham.setDonViTinh("Cái");
            sanPham.setSoLuong(100);
            sanPham.setGia(50000.0);
            sanPham.setTrangThai("Đang kinh doanh");
            sanPham.setLoaiSanPham("Loại 1");

            sanPhamDAO.insert(sanPham);
        } catch (Exception e) {
            ec.addError(new Throwable("Lỗi khi thêm sản phẩm: " + e));
        }

        SanPham insertedSanPham = sanPhamDAO.SelectByID("SP001");
        try {
            Assert.assertNull("Sản phẩm không được thêm khi mã sản phẩm là rỗng", insertedSanPham);
        } catch (Throwable e) {
            ec.addError(new Throwable("Lỗi kiểm tra sản phẩm sau khi thêm: " + e));
        }
    }

    @Test
    public void testInsertSanPhamNullTenSP() {
        try {
            SanPham sanPham = new SanPham();
            sanPham.setMaSanPham("SP002");
            sanPham.setTenSanPham("");
            sanPham.setDonViTinh("Cái");
            sanPham.setSoLuong(100);
            sanPham.setGia(50000.0);
            sanPham.setTrangThai("Đang kinh doanh");
            sanPham.setLoaiSanPham("Loại 1");

            sanPhamDAO.insert(sanPham);
        } catch (Exception e) {
            ec.addError(new Throwable("Lỗi khi thêm sản phẩm: " + e));
        }

        SanPham insertedSanPham = sanPhamDAO.SelectByID("SP002");
        try {
            Assert.assertNull("Sản phẩm không được thêm khi tên sản phẩm là rỗng", insertedSanPham);
        } catch (Throwable e) {
            ec.addError(new Throwable("Lỗi kiểm tra sản phẩm sau khi thêm: " + e));
        }
    }


    @Test
    public void testInsertSanPhamNullSLSP() {
        try {
            SanPham sanPham = new SanPham();
            sanPham.setMaSanPham("SP003");
            sanPham.setTenSanPham("Sản phẩm 3");
            sanPham.setDonViTinh("Cái");
            // Không set số lượng sản phẩm
            sanPham.setGia(50000.0);
            sanPham.setTrangThai("Đang kinh doanh");
            sanPham.setLoaiSanPham("Loại 1");

            sanPhamDAO.insert(sanPham);
        } catch (Exception e) {
            ec.addError(new Throwable("Lỗi khi thêm sản phẩm: " + e));
        }
        SanPham insertedSanPham = sanPhamDAO.SelectByID("SP003");
        try {
            Assert.assertNull("Sản phẩm không được thêm thành công với số lượng null", insertedSanPham);
        } catch (Throwable e) {
 
            ec.addError(new Throwable("Lỗi kiểm tra sản phẩm sau khi thêm: " + e));
        }
    }
    @Test
    public void testInsertSanPhamNullDonGiaSP() {
        try {
            SanPham sanPham = new SanPham();
            sanPham.setMaSanPham("SP004");
            sanPham.setTenSanPham("Sản phẩm 4");
            sanPham.setDonViTinh("Cái");
            sanPham.setSoLuong(100);
            sanPham.setTrangThai("Đang kinh doanh");
            sanPham.setLoaiSanPham("Loại 1");

            sanPhamDAO.insert(sanPham);
        } catch (Exception e) {
            ec.addError(new Throwable("Lỗi khi thêm sản phẩm: " + e));
        }
        SanPham insertedSanPham = sanPhamDAO.SelectByID("SP004");
        try {
            Assert.assertNull("Sản phẩm không được thêm thành công với đơn giá null", insertedSanPham);
        } catch (Throwable e) {
            ec.addError(new Throwable("Lỗi kiểm tra sản phẩm sau khi thêm: " + e));
        }
    }
    @Test
    public void testInsertSanPhamSoLuongKieuSo() {
        try {
            // Tạo sản phẩm mới với số lượng không đúng định dạng (không phải kiểu số)
            SanPham sanPham = new SanPham();
            sanPham.setMaSanPham("SP005");
            sanPham.setTenSanPham("Sản phẩm 5");
            sanPham.setDonViTinh("Cái");            
            sanPham.setSoLuong("abc"); 
            sanPhamDAO.insert(sanPham);
            Assert.fail("Không ném ra ngoại lệ khi thêm sản phẩm với số lượng không đúng định dạng số");
        } catch (Exception e) {
            Assert.assertTrue("Thông báo lỗi không chứa chuỗi 'vui lòng điền số lượng là kiểu số'", e.getMessage().contains("vui lòng điền số lượng là kiểu số"));
        }
    }
    @Test
    public void testInsertSanPhamMaSanPhamTonTai() {
        try {
            SanPham sanPham = new SanPham();
            sanPham.setMaSanPham("SP001"); 
            sanPham.setTenSanPham("Sản phẩm mới");
            sanPham.setDonViTinh("Cái");
            sanPham.setSoLuong(10);
            sanPham.setGia(10000.0);
            sanPham.setTrangThai("Đang kinh doanh");
            sanPham.setLoaiSanPham("Loại 1");
            sanPhamDAO.insert(sanPham);
            Assert.fail("Không ném ra ngoại lệ khi mã sản phẩm đã tồn tại");
        } catch (Throwable e) {
            Assert.assertTrue("Thông báo lỗi không chứa chuỗi 'Mã sản phẩm đã tồn tại'", e.getMessage().contains("Mã sản phẩm đã tồn tại"));
        }
    }

    @After
    public void afterSanPham() {
        System.out.println("Số lượng sản phẩm sau khi thêm: " + countSanPham());
    }

    @AfterClass
    public static void CloseConnect() {
    }
}
