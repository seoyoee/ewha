
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//DB와 연계하여 작업 처리하기 위한 클래스
public class DB2022_Team08_ReceiptDAO {

	private static Connection conn;
	
	private PreparedStatement pstmt;
	private CallableStatement cstmt;
	private ResultSet rs;
	
	// 기본 생성자
	public DB2022_Team08_ReceiptDAO() {
		
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
	
	// 영수증 등록하기
	public boolean insertReceipt(DB2022_Team08_ReceiptDTO dto) {
		boolean result = false;
		
		try {
			getConnection();
			
			String sql = "INSERT INTO DB2022_영수증 VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getNum());
			pstmt.setString(2, dto.getPhoneNum());
			pstmt.setInt(3, dto.getTotalPrice());
			pstmt.setString(4, dto.getDate());
			
			int r = pstmt.executeUpdate();
			
			if (r > 0)
				result = true;
		} catch (Exception e) {
			System.out.println("예외 발생: insertReceipt() => "+ e.getMessage());
		} finally {
			dbClose();
		}
		
		return result;
	}
	
	// 영수증 번호에 해당하는 영주증 정보 보기
	public DB2022_Team08_ReceiptDTO getReceipt(String num) {
		DB2022_Team08_ReceiptDTO dto = null;
		
		try {
			getConnection();
			
			String sql = "SELECT 영수증번호, 전화번호, 총결제금액, 판매일자 FROM DB2022_영수증 WHERE 영수증번호 = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			ResultSet r = pstmt.executeQuery();
			
			if (r.next()) {
				String r_num = r.getString("영수증번호");
				String r_phoneNum = r.getString("전화번호");
				int r_totalPrice = r.getInt("총결제금액");
				String r_date = r.getString("판매일자");
				dto = new DB2022_Team08_ReceiptDTO(r_num, r_phoneNum, r_totalPrice, r_date);				
			}
		} catch (Exception e) {
			System.out.println("예외 발생: getReceipt() => " + e.getMessage());
		} finally {
			dbClose();
		}
		
		return dto;
	}
	
	// 저장된 영수증 목록 보기
	public List<DB2022_Team08_ReceiptDTO> getReceiptList(){
		List<DB2022_Team08_ReceiptDTO> list = new ArrayList<DB2022_Team08_ReceiptDTO>();
		
		try {
			getConnection();
			
			String sql = "SELECT 영수증번호, 전화번호, 총결제금액, 판매일자 FROM DB2022_영수증 ORDER BY 영수증번호";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet r = pstmt.executeQuery();
			
			while(r.next()) {
				String r_num = r.getString("영수증번호");
				String r_phoneNum = r.getString("전화번호");
				int r_totalPrice = r.getInt("총결제금액");
				String r_date = r.getString("판매일자");
				list.add(new DB2022_Team08_ReceiptDTO(r_num, r_phoneNum, r_totalPrice, r_date));
			}
		} catch (Exception e) {
			System.out.println("예외 발생: getReceiptList() => " + e.getMessage());
		} finally {
			dbClose();
		}
		
		return list;
	}
	
	// 영수증 수정
	public boolean updateReceipt(DB2022_Team08_ReceiptDTO dto) {
		boolean result = false;
		
		try {
			getConnection();
			
			String sql = "UPDATE DB2022_영수증 SET 전화번호=?, 총결제금액=?, 판매일자=? WHERE 영수증번호=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getPhoneNum());
			pstmt.setInt(2, dto.getTotalPrice());
			pstmt.setString(3, dto.getDate());
			pstmt.setString(4, dto.getNum());
			
			int r = pstmt.executeUpdate();
			
			if (r > 0)
				result = true;
		} catch (Exception e) {
			System.out.println("예외 발생: updateReceipt() => " + e.getMessage());
		} finally {
			dbClose();
		}
		
		return result;
	}
	
	// 영수증 삭제
	public boolean deleteReceipt(String num) {
		boolean result = false;
		
		try {
			getConnection();
			
			String sql = "DELETE FROM DB2022_영수증 WHERE 영수증번호 = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			int r = pstmt.executeUpdate();
			
			if (r > 0)
				result = true;
		} catch (Exception e) {
			System.out.println("예외 발생: deleteReceipt() => "+ e.getMessage());
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
