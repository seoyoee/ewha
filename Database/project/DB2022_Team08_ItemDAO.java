
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DB와 연계하여 작업 처리하기 위한 클래스
public class DB2022_Team08_ItemDAO {

	private static Connection conn;
	
	private PreparedStatement pstmt;
	private CallableStatement cstmt;
	private ResultSet rs;
	
	// 기본 생성자
	public DB2022_Team08_ItemDAO() {
		
	}
	
	private void getConnection() throws ClassNotFoundException, SQLException {
		if (conn == null) { // dbConn이 null이면 Connection 객체 얻어오기
			// 접속 정보
			String url = "jdbc:mysql://localhost:3306/DB2022Team08";
			String user = "DB2022Team08";
			String pw = "DB2022Team08";
			
			// JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// DBMS에 연결하여 Connection 객체 얻기
			conn = DriverManager.getConnection(url, user, pw);
		}
	}
	
	// 품목 등록하기
	public boolean insertItem(DB2022_Team08_ItemDTO dto) {
		boolean result = false;
		
		try {
			getConnection();
			
			String sql = "INSERT INTO DB2022_품목 VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getRecNum());
			pstmt.setString(2, dto.getItemNum());
			pstmt.setInt(3, dto.getAmount());
			pstmt.setInt(4, dto.getPrice());
			
			int r = pstmt.executeUpdate();
			
			if (r > 0)
				result = true;
		} catch (Exception e) {
			System.out.println("예외 발생: insertItem() => "+ e.getMessage());
		} finally {
			dbClose();
		}
		
		return result;
	}
	
	// 영수증 번호에 해당하는 품목 정보 보기
	public DB2022_Team08_ItemDTO getItem(String recNum, String itemNum) {
		DB2022_Team08_ItemDTO dto = null;
		
		try {
			getConnection();
			
			String sql = "SELECT 영수증번호, 상품번호, 수량, 가격 FROM DB2022_품목 WHERE 영수증번호 = ? AND 상품번호 = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, recNum);
			pstmt.setString(2, itemNum);
			ResultSet r = pstmt.executeQuery();
			
			if (r.next()) {
				String i_recNum = r.getString("영수증번호");
				String i_itemNum = r.getString("상품번호");
				int i_amount = r.getInt("수량");
				int i_price = r.getInt("가격");
				dto = new DB2022_Team08_ItemDTO(i_recNum, i_itemNum, i_amount, i_price);				
			}
		} catch (Exception e) {
			System.out.println("예외 발생: getItem() => " + e.getMessage());
		} finally {
			dbClose();
		}
		
		return dto;
	}
	
	// 저장된 품목 목록 보기
	public List<DB2022_Team08_ItemDTO> getItemList(){
		List<DB2022_Team08_ItemDTO> list = new ArrayList<DB2022_Team08_ItemDTO>();
		
		try {
			getConnection();
			//DB2022_품목 테이블에서 상품명도 함께 검색하기 위해 조인 쿼리 사용
			String sql = "SELECT 영수증번호, I.상품번호, 상품명, 수량, 가격 \r\n"
					+ "FROM DB2022_품목 as I JOIN DB2022_상품 as P on I.상품번호= P.상품번호\r\n"
					+ "ORDER BY 영수증번호;";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet r = pstmt.executeQuery();
			
			while(r.next()) {
				String i_recNum = r.getString("영수증번호");
				String i_itemNum = r.getString("상품번호");
				String i_Name = r.getString("상품명");
				int i_amount = r.getInt("수량");
				int i_price = r.getInt("가격");
				list.add(new DB2022_Team08_ItemDTO(i_recNum, i_itemNum, i_Name, i_amount, i_price));
			}
		} catch (Exception e) {
			System.out.println("예외 발생: getItemList() => " + e.getMessage());
		} finally {
			dbClose();
		}
		
		return list;
	}
	
	// 품목 수정
	public boolean updateItem(DB2022_Team08_ItemDTO dto) {
		boolean result = false;
		
		try {
			getConnection();
			
			String sql = "UPDATE DB2022_품목 SET 수량=?, 가격=? WHERE 영수증번호=? AND 상품번호=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getAmount());
			pstmt.setInt(2, dto.getPrice());
			pstmt.setString(3, dto.getRecNum());
			pstmt.setString(4, dto.getItemNum());
			
			int r = pstmt.executeUpdate();
			
			if (r > 0)
				result = true;
		} catch (Exception e) {
			System.out.println("예외 발생: updateItem() => " + e.getMessage());
		} finally {
			dbClose();
		}
		
		return result;
	}
	
	// 품목 삭제
	public boolean deleteItem(String recNum, String itemNum) {
		boolean result = false;
		
		try {
			getConnection();
			
			String sql = "DELETE FROM DB2022_품목 WHERE 영수증번호 = ? AND 상품번호 = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, recNum);
			pstmt.setString(2, itemNum);
			int r = pstmt.executeUpdate();
			
			if (r > 0)
				result = true;
		} catch (Exception e) {
			System.out.println("예외 발생: deleteItem() => "+ e.getMessage());
		} finally {
			dbClose();
		}
		
		return result;
	}
	
	// DB 연결 해제
	public void dbClose() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("예외: ResultSet 객체 close(): " + e.getMessage());
			}
		}
		
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("예외: PreparedStatement 객체 close(): " + e.getMessage());
			}
		}
		
		if (cstmt != null) {
			try {
				cstmt.close();
			} catch (SQLException e) {
				System.out.println("예외: CallableStatement 객체 close(): " + e.getMessage());
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("예외: Connection 객체 close(): " + e.getMessage());
			}
		}
		
		conn = null;
	}
}
