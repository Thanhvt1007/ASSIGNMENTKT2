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

public class TestDeleteSanPham {
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
		System.out.println(count);
	}

	@Test
	public void testDeleteSanPham1() {
		SanPham sanPham = sanPhamDAO.SelectByID("SP001");
		if (sanPham != null) {
			try {
				sanPhamDAO.delete(sanPham.getMaSanPham());
			} catch (Exception e) {
				ec.addError(new Throwable("Lỗi khi xóa sản phẩm: " + e));
			}

			index = countSanPham();
			Assert.assertEquals(index, count - 1);
		} else {
			ec.addError(new Throwable("Sản phẩm không tồn tại trong cơ sở dữ liệu"));
		}
	}

	@Test
	public void testDeleteSanPham() {
		SanPham sanPham = sanPhamDAO.SelectByID("SP001");
		if (sanPham != null) {
			// Kiểm tra xem sản phẩm đã được sử dụng trong hóa đơn hay chưa
			if (sanPhamDAO.selectBySanPhamInHoaDon(sanPham.getMaSanPham()).isEmpty()) {
				try {
					sanPhamDAO.delete(sanPham.getMaSanPham());
				} catch (Exception e) {
					ec.addError(new Throwable("Lỗi khi xóa sản phẩm: " + e));
				}

				index = countSanPham();
				Assert.assertEquals(index, count - 1);
			} else {
				// Nếu sản phẩm đã được sử dụng trong hóa đơn, thông báo không thể xóa
				ec.addError(new Throwable("Không thể xóa sản phẩm vì sản phẩm đã được sử dụng trong hóa đơn"));
			}
		} else {
			ec.addError(new Throwable("Sản phẩm không tồn tại trong cơ sở dữ liệu"));
		}
	}
	 @Test
	    public void testDeleteSanPhamWithoutSelectingProduct() {
	        try {
	            sanPhamDAO.delete(null); //không chọn sản phẩm nào trước đó
	            Assert.fail("Không ném ra ngoại lệ khi không chọn sản phẩm trước khi xóa");
	        } catch (Throwable e) {
	            Assert.assertTrue("Vui lòng chọn sản phẩm!'", e.getMessage().contains("Vui lòng chọn sản phẩm!"));
	        }
	    }
	@After
	public void afterSanPham() {
		System.out.println(count);
	}

	@AfterClass
	public static void CloseConnect() {

	}
}
