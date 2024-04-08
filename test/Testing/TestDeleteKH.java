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

public class TestDeleteKH {
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
    public void testDeleteKhachHang1() {
        // Giả sử có một khách hàng có sẵn trong cơ sở dữ liệu
        KhachHang khachHang = khachHangDAO.SelectByID(1);
        if (khachHang != null) {
            // Kiểm tra xem khách hàng đã lập hóa đơn hay chưa
            if (khachHangDAO.selectByKhachHanngInHoaDon(khachHang.getMaKhachHang()).isEmpty()) {
                try {
                    khachHangDAO.delete(khachHang.getMaKhachHang());
                } catch (Exception e) {
                    ec.addError(new Throwable("Lỗi khi xóa khách hàng: " + e));
                }

                index = countKhachHang();
                Assert.assertEquals(index, count - 1);
            } else {
                // Nếu khách hàng đã lập hóa đơn, thông báo không thể xóa
                ec.addError(new Throwable("Không thể xóa khách hàng vì khách hàng đã lập hóa đơn"));
            }
        } else {
            ec.addError(new Throwable("Khách hàng không tồn tại trong cơ sở dữ liệu"));
        }
    }

    @After
    public void afterKhachHang() {
        System.out.println(count);
    }

    @AfterClass
    public static void CloseConnect() {

    }
}
