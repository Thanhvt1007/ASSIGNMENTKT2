package Testing;

import com.software.dao.KhachHangDAO;
import com.software.entity.KhachHang;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.testng.Assert;

public class TestUpdateKH {

	private KhachHangDAO khachHangDAO;
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

	@BeforeClass
	public static void openConnect() {
	}

	@Before
	public void setUp() {
		khachHangDAO = new KhachHangDAO();
	}

	@Test
	public void testUpdateKhachHang() {
		// Giả sử có một khách hàng có sẵn trong cơ sở dữ liệu
		KhachHang khachHang = khachHangDAO.SelectByID(1);
		if (khachHang != null) {
			// Update thông tin của khách hàng
			khachHang.setTenKhachHang("Nguyen Van B");
			khachHang.setSoDT("0987654321");
			try {
				khachHangDAO.update(khachHang);
			} catch (Exception e) {
				ec.addError(new Throwable("Lỗi khi cập nhật khách hàng: " + e));
			}
			KhachHang updatedKhachHang = khachHangDAO.SelectByID(khachHang.getMaKhachHang());
			Assert.assertEquals(updatedKhachHang.getTenKhachHang(), "Nguyen Van B");
			Assert.assertEquals(updatedKhachHang.getSoDT(), "0987654321");
		} else {
			ec.addError(new Throwable("Khách hàng không tồn tại trong cơ sở dữ liệu"));
		}
	}

	@Test
	public void testUpdateNullName() {
		boolean isSuccess;
		KhachHang khachHang = new KhachHang();
		try {
			khachHang.setTenKhachHang("");
			khachHang.setSoDT("123456789");
			khachHangDAO.insert(khachHang);
		} catch (Exception e) {
			e.printStackTrace();
		}
		KhachHang updatedKhachHang = khachHangDAO.SelectByID(khachHang.getMaKhachHang());
		if (updatedKhachHang != null) {
			boolean temp = khachHang.getMaKhachHang().equals(updatedKhachHang.getMaKhachHang())
					&& khachHang.getTenKhachHang().equals(updatedKhachHang.getTenKhachHang())
					&& khachHang.getSoDT().equals(updatedKhachHang.getSoDT());
			isSuccess = temp;
		} else {
			isSuccess = false;
		}

		assertFalse(isSuccess);
	}

	@Test
	public void testUpdateNullPhone() {
//    	test null phone
		boolean isSuccess;
		KhachHang khachHang = new KhachHang();
		try {
			khachHang.setTenKhachHang("John Doe");
			khachHang.setSoDT("");
			khachHangDAO.insert(khachHang);
		} catch (Exception e) {
			e.printStackTrace();
		}
		KhachHang updatedKhachHang = khachHangDAO.SelectByID(khachHang.getMaKhachHang());
		if (updatedKhachHang != null) {
			boolean temp = khachHang.getMaKhachHang().equals(updatedKhachHang.getMaKhachHang())
					&& khachHang.getTenKhachHang().equals(updatedKhachHang.getTenKhachHang())
					&& khachHang.getSoDT().equals(updatedKhachHang.getSoDT());
			isSuccess = temp;
		} else {
			isSuccess = false;
		}

		assertFalse(isSuccess);
	}

	@Test
	public void testUpdateInvalidName() {
		boolean isSuccess;
		KhachHang khachHang = new KhachHang();
		try {
			khachHang.setTenKhachHang("@bdhsjjnsjnjsndjsn");
			khachHang.setSoDT("123456789");
			khachHangDAO.insert(khachHang);
		} catch (Exception e) {
			e.printStackTrace();
		}
		KhachHang updatedKhachHang = khachHangDAO.SelectByID(khachHang.getMaKhachHang());
		if (updatedKhachHang != null) {
			boolean temp = khachHang.getMaKhachHang().equals(updatedKhachHang.getMaKhachHang())
					&& khachHang.getTenKhachHang().equals(updatedKhachHang.getTenKhachHang())
					&& khachHang.getSoDT().equals(updatedKhachHang.getSoDT());
			isSuccess = temp;
		} else {
			isSuccess = false;
		}
		assertFalse(isSuccess);
	}

	@Test
	public void testUpdateInvalidPhone() {
		boolean isSuccess;
		KhachHang khachHang = new KhachHang();
		try {
			khachHang.setTenKhachHang("Võ Thị Thành");
			khachHang.setSoDT("9298djdsjaj");
			khachHangDAO.insert(khachHang);
		} catch (Exception e) {
			e.printStackTrace();
		}
		KhachHang updatedKhachHang = khachHangDAO.SelectByID(khachHang.getMaKhachHang());
		if (updatedKhachHang != null) {
			boolean temp = khachHang.getMaKhachHang().equals(updatedKhachHang.getMaKhachHang())
					&& khachHang.getTenKhachHang().equals(updatedKhachHang.getTenKhachHang())
					&& khachHang.getSoDT().equals(updatedKhachHang.getSoDT());
			isSuccess = temp;
		} else {
			isSuccess = false;
		}
		assertFalse(isSuccess);
	}

	@After
	public void tearDown() {
	}

	@AfterClass
	public static void closeConnect() {
	}
}
