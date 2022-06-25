import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//DB와 연계하여 작업 처리하기 위한 클래스
public class DB2022_Team08_OrderDAO {

    private static Connection conn;

    private PreparedStatement pstmt;
    private CallableStatement cstmt;
    private ResultSet rs;


    //기본생성자
    public DB2022_Team08_OrderDAO() {

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
     * 상품 등록하기
     */
    public boolean insertOrder(DB2022_Team08_OrderDTO dto){

        boolean result = false;
        try {
            getConnection();

            //DB2022_발주 테이블에 새로운 발주 내역을 등록 시, DB2022_상품 테이블의 재고량 또한 업데이트해야 하므로 트랜잭션 생성
            conn.setAutoCommit(false);
            //String sql = "INSERT INTO DB2022_발주 VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DB2022_발주 VALUES(?, ?, ?, ?, ?, ?, ?)");
            Statement stmt=conn.createStatement();            
            pstmt.setString(1,dto.getOrder_num());
            pstmt.setInt(2,dto.getOrder_price());
            pstmt.setDate(3,dto.getOrder_date());
            pstmt.setTime(4,dto.getOrder_time());
            pstmt.setInt(5,dto.getOrder_total());
            pstmt.setString(6,dto.getContact_name());
            pstmt.setString(7,dto.getCompany_phone());
            
            
            int r = pstmt.executeUpdate(); //발주 테이블에 tuple 추가
            
            String sql_2 = "UPDATE DB2022_상품 SET 재고량=재고량+"+dto.getOrder_total()+" WHERE 상품번호="+dto.getOrder_num()+";";
            
            stmt.executeUpdate(sql_2); //발주한 수량만큼 상품 테이블에 재고량 업데이트
            conn.commit();

            if(r>0) result = true;

        } catch (Exception e) {
            System.out.println("예외발생:insertOrder()=> "+e.getMessage());
            System.out.println("rolling back data here...");
			
			try {
				if(conn!=null) {
					conn.rollback();
				}
			}
			catch(SQLException se){
				se.printStackTrace();
			}
        }finally{
        	try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            dbClose();
        }
    
        return result;
    }

    /**
     * 발주번호에 해당하는 발주정보 보기
     */
    public DB2022_Team08_OrderDTO getOrder(Date date, Time time){
        DB2022_Team08_OrderDTO dto =null;
        try {
            getConnection();

            String sql = "SELECT * FROM DB2022_발주 WHERE 발주날짜 = ? and 발주시간=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, date);
            pstmt.setTime(2, time);
            ResultSet r = pstmt.executeQuery();

            if(r.next()) {
                String orderNum = r.getString("발주상품번호");
                int orderPrice = r.getInt("발주상품가격");
                Date orderDate = r.getDate("발주날짜");
                Time orderTime = r.getTime("발주시간");
                int orderTotal = r.getInt("발주수량");
                String contactName = r.getString("담당자이름");
                String companyPhone = r.getString("제조업체전화번호");

                dto = new DB2022_Team08_OrderDTO(orderNum,orderPrice,orderDate,orderTime,orderTotal,contactName,companyPhone);
            }

        } catch (Exception e) {
            System.out.println("예외발생:deleteOrder()=> "+e.getMessage());
        }finally{
            dbClose();
        }

        return dto;
    }

    /**
     * 저장된 발주 목록 보기
     */
    public List<DB2022_Team08_OrderDTO> getOrderList(){
        List<DB2022_Team08_OrderDTO> list = new ArrayList<DB2022_Team08_OrderDTO>();

        try {
            getConnection();
            // DB2022_발주 테이블에서 제조업체명도 함께 검색하기 위해 조인 쿼리 사용
            String sql = "SELECT 발주상품번호, 발주상품가격, 발주날짜, 발주시간, 발주수량, 제조업체, 담당자이름, 제조업체전화번호 "
            		+"FROM DB2022_발주 JOIN DB2022_상품 on DB2022_발주.발주상품번호 = DB2022_상품.상품번호;";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet r = pstmt.executeQuery();

            while(r.next()) {
                String orderNum = r.getString("발주상품번호");
                int orderPrice = r.getInt("발주상품가격");
                Date orderDate = r.getDate("발주날짜");
                Time orderTime = r.getTime("발주시간");
                int orderTotal = r.getInt("발주수량");
                String companyName = r.getString("제조업체");
                String contactName = r.getString("담당자이름");
                String companyPhone = r.getString("제조업체전화번호");
                list.add(new DB2022_Team08_OrderDTO(orderNum,orderPrice,orderDate,orderTime,orderTotal,companyName,contactName,companyPhone));
            }

        } catch (Exception e) {
            System.out.println("예외발생:getOrderList()=> "+e.getMessage());
        }finally{
            dbClose();
        }

        return list;
    }


    /**
     * 회원 수정
     */
    public boolean updateOrder(DB2022_Team08_OrderDTO dto){

        boolean result = false;
        try {
            getConnection();

            String sql = "UPDATE DB2022_발주 SET 발주상품번호=?, 발주가격=? , 발주날짜=?, 발주시간=?, 발주수량=?, 담당자이름=?, 제조업체전화번호=? WHERE 발주번호=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,dto.getOrder_num());
            pstmt.setInt(2,dto.getOrder_price());
            pstmt.setDate(3,dto.getOrder_date());
            pstmt.setTime(4,dto.getOrder_time());
            pstmt.setInt(5,dto.getOrder_total());
            pstmt.setString(6,dto.getContact_name());
            pstmt.setString(7,dto.getCompany_phone());

            int r = pstmt.executeUpdate();

            if(r>0) result = true;

        } catch (Exception e) {
            System.out.println("예외발생:updateOrder()=> "+e.getMessage());
        }finally{
            dbClose();
        }
        return result;
    }


    /**
     * 회원 삭제
     */
    public boolean deleteOrder(Date date, Time time){
        boolean result = false;
        try {
            getConnection();

            String sql = "DELETE FROM DB2022_발주 WHERE 발주날짜 = ? and 발주시간=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, date);
            pstmt.setTime(2, time);
            int r = pstmt.executeUpdate();

            if(r>0) result = true;

        } catch (Exception e) {
            System.out.println("예외발생:deleteOrder()=> "+e.getMessage());
        }finally{
            dbClose();
        }
        return result;
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
