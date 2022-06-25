import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* DB와 연계하여 작업 처리하기 위한 클래스*/


public class DB2022_Team08_ReviewViewDAO {

private static Connection conn;
	   
    private PreparedStatement pstmt;
    private CallableStatement cstmt;
    private ResultSet rs;
   
    public DB2022_Team08_ReviewViewDAO() {
		// TODO Auto-generated constructor stub
    }
    
	private void getConnection() throws ClassNotFoundException, SQLException{ //예외처리
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
	   
	    
	  
	    /*저장된 리뷰 목록 보기*/
	    public List<DB2022_Team08_ReviewViewDTO> getReviewList(){
	        List<DB2022_Team08_ReviewViewDTO> list = new ArrayList<DB2022_Team08_ReviewViewDTO>();
	       
	        try {
	            getConnection();
	            String sql = "SELECT * FROM DB2022_REVIEW_VIEW"; //select 쿼리
	            PreparedStatement pstmt = conn.prepareStatement(sql);          
	            ResultSet r = pstmt.executeQuery();
	           
	            while(r.next()) {
	                
	                
	                Integer m_stars = r.getInt("평점");
	                String m_write_date = r.getString("작성일자");
	                list.add(new DB2022_Team08_ReviewViewDTO(m_stars, m_write_date));
	            }
	           
	        } catch (Exception e) {
	            System.out.println("예외발생:getReviewList()=> "+e.getMessage());
	        }finally{          
	            dbClose();
	        }  
	        return list;
	    }
	   
	   
	    /*DB연결 해제*/
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
	    }
}
