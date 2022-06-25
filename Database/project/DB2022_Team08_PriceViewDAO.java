import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//DB와 연계하여 작업 처리하기 위한 클래스
public class DB2022_Team08_PriceViewDAO {
	 private static Connection conn;

	    private PreparedStatement pstmt;
	    private CallableStatement cstmt;
	    private ResultSet rs;


	    //기본생성자
	    public DB2022_Team08_PriceViewDAO() {

	    }

	    private void getConnection() throws ClassNotFoundException, SQLException{
	        if(conn == null){
	            //접속정보
	            String url = "jdbc:mysql://localhost:3306/DB2022Team08"; //database 이름
	            String user = "DB2022Team08"; //사용자명
	            String pw = "DB2022Team08"; //비밀번호

	            //JDBC드라이버 로드
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            //mysql에 연결하여 Connection 객체 얻기.
	            conn = DriverManager.getConnection(url,user,pw);

	        }
	    }


	   
	    /**
	     * 저장된 상품 목록 보기(상품명, 상품 사이즈, 판매가격만 공개)
	     */
	    public List<DB2022_Team08_PriceViewDTO> getProductList(){
	        List<DB2022_Team08_PriceViewDTO> list = new ArrayList<DB2022_Team08_PriceViewDTO>();

	        try {
	            getConnection();

	            String sql = "SELECT * FROM DB2022_price_view";
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            ResultSet r = pstmt.executeQuery();

	            while(r.next()) {
	                String name = r.getString("상품명");
	                String size = r.getString("상품사이즈");
	                int price = r.getInt("판매가격");
	                list.add(new DB2022_Team08_PriceViewDTO(name,size,price));
	            }

	        } catch (Exception e) {
	            System.out.println("예외발생:getProductList()=> "+e.getMessage());
	        }finally{
	            dbClose();
	        }

	        return list;
	    }


	   


	    /**DB연결 해제(닫기)*/
	    public void dbClose(){

	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                System.out.println("예외:ResultSet객체 close():" + e.getMessage());
	            }
	        }

	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException e) {
	                System.out.println("예외:PreparedStatement객체 close():" + e.getMessage());
	            }
	        }

	        if (cstmt != null) {
	            try {
	                cstmt.close();
	            } catch (SQLException e) {
	                System.out.println("예외:CallableStatement객체 close():" + e.getMessage());
	            }
	        }


	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                System.out.println("예외:Connection객체 close():" + e.getMessage());
	            }
	        }

	        conn = null;
	    }//dbClose()---------

}
