import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* DB와 연계하여 작업 처리하기 위한 클래스*/

public class DB2022_Team08_MemberDAO {
		private static Connection conn;
		   
	    private PreparedStatement pstmt;
	    private CallableStatement cstmt;
	    private ResultSet rs;
	   
	    public DB2022_Team08_MemberDAO() {
			// TODO Auto-generated constructor stub
		}
	    
		private void getConnection() throws ClassNotFoundException, SQLException{ //예외처리
		        if(conn == null){ 
		            //접속정보
		            String url = "jdbc:mysql://localhost:3306/DB2022Team08"; //database 이름
		            String user = "DB2022Team08"; //사용자명
		            String pw = "DB2022Team08";  //비밀번호
		           
		            //JDBC드라이버 로드
		            Class.forName("com.mysql.cj.jdbc.Driver");
		           
		            //mysql에 연결하여 Connection 객체 얻기.
		            conn = DriverManager.getConnection(url,user,pw);
		                               
		        }
		    }
		
		/*회원 등록하기*/
		public boolean insertMember(DB2022_Team08_MemberDTO dto){    
			boolean result = false;            
			try {
				getConnection();
		        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DB2022_회원 VALUES(?, ?, ?, ?)"); //insert 쿼리
		        pstmt.setString(1,dto.getPhone_num());
		        pstmt.setString(2,dto.getName());
		        pstmt.setInt(3,dto.getPurchase());
		        pstmt.setString(4, dto.getGrade());
		        int r = pstmt.executeUpdate();
		        if(r>0) result = true;
		        } 
			catch (Exception e) {
		        	System.out.println("예외발생:insertMember()=> "+e.getMessage());
		        	}
			finally{
				dbClose();
				}      
		        
			return result;
			}      
		   
		/*회원번호에 해당하는 회원정보 보기*/    
		public DB2022_Team08_MemberDTO getMember(String phone_num){    
			DB2022_Team08_MemberDTO dto =null;
		    try {
		        getConnection();
		        String sql = "SELECT * FROM DB2022_회원 WHERE 전화번호=?"; //select 쿼리
		        PreparedStatement pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1, phone_num);
		        ResultSet r = pstmt.executeQuery();
		        
		        if(r.next()) {
		            String m_phone_num = r.getString("전화번호");
		            String m_name = r.getString("회원이름");
		            Integer m_purchase = r.getInt("구매금액");
		            String m_grade = r.getString("회원등급");
		            dto = new DB2022_Team08_MemberDTO(m_phone_num, m_name, m_purchase, m_grade);
		            }
		        } catch (Exception e) {
		            System.out.println("예외발생:getMember()=> "+e.getMessage());
		            } finally{          
		            dbClose();
		            }      
		    return dto;
		}

		/*저장된 회원 목록 보기*/
		public List<DB2022_Team08_MemberDTO> getMemberList(){
		    List<DB2022_Team08_MemberDTO> list = new ArrayList<DB2022_Team08_MemberDTO>();
		       
		    try {
		    	getConnection();
		    	String sql = "SELECT * FROM DB2022_회원"; //select 쿼리
		 
		        PreparedStatement pstmt = conn.prepareStatement(sql);          
		        ResultSet r = pstmt.executeQuery();
		           
		        while(r.next()) {
		        	String m_phone_num = r.getString("전화번호");
		            String m_name = r.getString("회원이름");
		            Integer m_purchase = r.getInt("구매금액");
		            String m_grade = r.getString("회원등급");
		            list.add(new DB2022_Team08_MemberDTO(m_phone_num, m_name, m_purchase, m_grade));    
		        }    
		    } catch (Exception e) {
		        System.out.println("예외발생:getMemberList()=> "+e.getMessage());
		        }finally{          
		            dbClose();
		            }  
		    return list;
		}
		   
		/*회원 정보 수정*/
		public boolean updateMember(DB2022_Team08_MemberDTO dto){
		       boolean result = false;            
		       try {
		    	   getConnection();
		           String sql = "UPDATE DB2022_회원 SET 전화번호=? , 회원이름=? , 구매금액=? , 회원등급=? WHERE 전화번호=?"; //update 쿼리
		           PreparedStatement pstmt = conn.prepareStatement(sql);
		           pstmt.setString(1,dto.getPhone_num());
		           pstmt.setString(2,dto.getName());
		           pstmt.setInt(3,dto.getPurchase());
		           pstmt.setString(4,dto.getGrade());        
		           pstmt.setString(5,dto.getPhone_num());
		           int r = pstmt.executeUpdate();
		           if(r>0) result = true;
		        
		       } catch (Exception e) {
		           System.out.println("예외발생:updateMember()=> "+e.getMessage());
		       }finally{          
		    	   dbClose();
		       }      
		       return result;
		}
		
		/*회원 삭제*/
		public boolean deleteMember(String phone){        
		boolean result = false;            
		try {
			getConnection();
		    String sql = "DELETE FROM DB2022_회원 WHERE 전화번호 = ?"; //delete 쿼리
		    PreparedStatement pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, phone);
		    int r = pstmt.executeUpdate();
		           
		    if(r>0) result = true;
		} catch (Exception e) {
			System.out.println("예외발생:deleteMember()=> "+e.getMessage());
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
