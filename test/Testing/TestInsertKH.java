package Testing;

import com.software.dao.KhachHangDAO;
import com.software.entity.KhachHang;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class TestInsertKH {

    KhachHangDAO khachHangDAO = new KhachHangDAO();
    @Rule
    public ErrorCollector ec = new ErrorCollector();
    static int count, index;

    public int countKhachHang() {
        int temp = 0;
        try {
            List<KhachHang> khachHangList = khachHangDAO.SelectAll();
            temp = khachHangList.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    @Before
    public void beforeKhachHang() {
        count = countKhachHang();
        System.out.println(count);
    }


    @Test
    public void testInsertKHNullPhone() {
    	//thêm khách hàng nhưng nullsdt
        try {
            KhachHang khachHang = new KhachHang();
            khachHang.setTenKhachHang("Vo Thi Thanh");
            khachHang.setSoDT("");
            khachHangDAO.insert(khachHang);
        } catch (Exception e) {
            ec.addError(new Throwable("Lỗi khi thêm khách hàng: " + e));
        }
        index = countKhachHang();
        Assert.assertEquals(index, count);
    }
    @Test
    public void testInsertKHNullName() {
        try {
            KhachHang khachHang = new KhachHang();
            khachHang.setTenKhachHang("");
            khachHang.setSoDT("0766738103");
            khachHangDAO.insert(khachHang);
        } catch (Exception e) {
            ec.addError(new Throwable("Lỗi khi thêm khách hàng: " + e));
        }
        index = countKhachHang();
        Assert.assertEquals(index, count);
    }

    @Test
    public void testInsertKHInvalidPhone() {
        try {
            KhachHang khachHang = new KhachHang();
            khachHang.setTenKhachHang("Võ Thị Thành");
            khachHang.setSoDT("012346789999qq");
            khachHangDAO.insert(khachHang);
        } catch (Exception e) {
            ec.addError(new Throwable("Lỗi khi thêm khách hàng: " + e));
        }
        index = countKhachHang();
        Assert.assertEquals(index, count);
    }
    @Test
    public void testInsertKHInvalidName() {
        try {
            KhachHang khachHang = new KhachHang();
            khachHang.setTenKhachHang("@thậkkkkaklkalklaklaal");
            khachHang.setSoDT("012346789999qq");
            khachHangDAO.insert(khachHang);
        } catch (Exception e) {
            ec.addError(new Throwable("Lỗi khi thêm khách hàng: " + e));
        }
        index = countKhachHang();
        Assert.assertEquals(index, count);
    }

    @After
    public void afterKhachHang() {
        System.out.println(count);
    }

    @AfterClass
    public static void CloseConnect() {

    }
}
