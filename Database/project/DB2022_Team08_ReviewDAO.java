import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* DB와 연계하여 작업 처리하기 위한 클래스*/

public class DB2022_Team08_ReviewDAO {
	private static Connection conn;
	   
    private PreparedStatement pstmt;
    private CallableStatement cstmt;
    private ResultSet rs;
   
    public DB2022_Team08_ReviewDAO() {
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
	   
	       
	    /*리뷰 등록하기*/
	    public boolean insertReview(DB2022_Team08_ReviewDTO dto){    
	       
	        boolean result = false;            
	        try {
	            getConnection();
	           
	           
	            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DB2022_리뷰 VALUES(?, ?, ?, ?, ?)"); //insert 쿼리
	            pstmt.setString(1,dto.getReceipt_num());
	            pstmt.setString(2,dto.getVisit_date());
	            pstmt.setString(3,dto.getWriter());
	            pstmt.setInt(4, dto.getStars());
	            pstmt.setString(5, dto.getWrite_date());
	           
	            int r = pstmt.executeUpdate();
	           
	            if(r>0) result = true;
	           
	        } catch (Exception e) {
	            System.out.println("예외발생:insertReview()=> "+e.getMessage());
	        }finally{          
	            dbClose();
	        }      
	        return result;
	    }      
	   
	    /*영수증번호에 해당하는 리뷰정보 보기*/
	    public DB2022_Team08_ReviewDTO getReview(String receipt_num){
	        DB2022_Team08_ReviewDTO dto =null;
	        try {
	            getConnection();
	           
	            String sql = "SELECT * FROM DB2022_리뷰 WHERE 영수증번호=?"; //select 쿼리
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, receipt_num);
	            ResultSet r = pstmt.executeQuery();
	           
	            if(r.next()) {
	                String m_receipt_num = r.getString("영수증번호");
	                String m_visit_date = r.getString("방문일자");
	                String m_writer = r.getString("작성자");
	                Integer m_stars = r.getInt("평점");
	                String m_write_date = r.getString("작성일자");
	                dto = new DB2022_Team08_ReviewDTO(m_receipt_num, m_visit_date, m_writer, m_stars, m_write_date);
	            }
	           
	        } catch (Exception e) {
	            System.out.println("예외발생:getReview()=> "+e.getMessage());
	        }finally{          
	            dbClose();
	        }      
	        return dto;
	    }
	 
	    /*저장된 리뷰 목록 보기 > 검색하는 유저가 작성한 것만*/ 
	    public List<DB2022_Team08_ReviewDTO> getMyReviewList(String phone){
	        List<DB2022_Team08_ReviewDTO> list = new ArrayList<DB2022_Team08_ReviewDTO>();
	       
	        try {
	            getConnection();
	            String sql = "SELECT * FROM DB2022_리뷰 WHERE 작성자 = ?"; //select 쿼리
	            
	            PreparedStatement pstmt = conn.prepareStatement(sql); 
	            pstmt.setString(1,phone);
	            
	            ResultSet r = pstmt.executeQuery();
	           
	            while(r.next()) {
	                String m_receipt_num = r.getString("영수증번호");
	                String m_visit_date = r.getString("방문일자");
	                String m_writer = r.getString("작성자");
	                Integer m_stars = r.getInt("평점");
	                String m_write_date = r.getString("작성일자");
	                list.add(new DB2022_Team08_ReviewDTO(m_receipt_num, m_visit_date, m_writer, m_stars, m_write_date));
	            }
	           
	        } catch (Exception e) {
	            System.out.println("예외발생:getReviewList()=> "+e.getMessage());
	        }finally{          
	            dbClose();
	        }  
	        return list;
	    }
	   
	 
	    /*리뷰 수정*/
	    public boolean updateReview(DB2022_Team08_ReviewDTO dto){
	       
	        boolean result = false;            
	        try {
	            getConnection();
	           
	            String sql = "UPDATE DB2022_리뷰 SET 영수증번호=? , 방문일자=? , 작성자=? , 평점=?, 작성일자=? WHERE 영수증번호=?"; //update 쿼리
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	           
	            pstmt.setString(1,dto.getReceipt_num());
	            pstmt.setString(2,dto.getVisit_date());
	            pstmt.setString(3,dto.getWriter());
	            pstmt.setInt(4,dto.getStars());        
	            pstmt.setString(5,dto.getWrite_date());
	            pstmt.setString(6, dto.getReceipt_num());
	            int r = pstmt.executeUpdate();
	           
	            if(r>0) result = true;
	           
	        } catch (Exception e) {
	            System.out.println("예외발생:updateReview()=> "+e.getMessage());
	        }finally{          
	            dbClose();
	        }      
	        return result;
	    }
	   
	   
	    /*리뷰 삭제*/
	    public boolean deleteReview(String receipt){        
	        boolean result = false;            
	        try {
	            getConnection();
	           
	            String sql = "DELETE FROM DB2022_리뷰 WHERE 영수증번호 = ?"; //delete 쿼리
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, receipt);
	            int r = pstmt.executeUpdate();
	           
	            if(r>0) result = true;
	           
	        } catch (Exception e) {
	            System.out.println("예외발생:deleteReview()=> "+e.getMessage());
	        }finally{          
	            dbClose();
	        }      
	        return result;
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
