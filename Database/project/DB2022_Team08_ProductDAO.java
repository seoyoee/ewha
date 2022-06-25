import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//DB와 연계하여 작업 처리하기 위한 클래스
public class DB2022_Team08_ProductDAO {

    private static Connection conn;

    private PreparedStatement pstmt;
    private CallableStatement cstmt;
    private ResultSet rs;


    //기본생성자
    public DB2022_Team08_ProductDAO() {

    }

    private void getConnection() throws ClassNotFoundException, SQLException{
        if(conn == null){
            //접속정보
        	String url = "jdbc:mysql://localhost:3306/DB2022Team08";
			String user = "DB2022Team08";
			String pw = "DB2022Team08";

            //JDBC드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            //mysql에 연결하여 Connection 객체 얻기.
            conn = DriverManager.getConnection(url,user,pw);

        }
    }


    /**
     * 상품 등록하기
     */
    public boolean insertProduct(DB2022_Team08_ProductDTO dto){

        boolean result = false;
        try {
            getConnection();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DB2022_상품 VALUES(?, ?, ?, ?, ?, ?, ?)");

            pstmt.setString(1,dto.getProduct_num());
            pstmt.setString(2,dto.getCategory());
            pstmt.setString(3,dto.getName());
            pstmt.setString(4,dto.getSize());
            pstmt.setInt(5,dto.getPrice());
            pstmt.setString(6,dto.getCompany());
            pstmt.setInt(7,dto.getStock());

            int r = pstmt.executeUpdate();

            if(r>0) result = true;

        } catch (Exception e) {
            System.out.println("예외발생:insertMember()=> "+e.getMessage());
        }finally{
            dbClose();
        }
        return result;
    }

    /**
     * 상품번호에 해당하는 상품정보 보기
     */
    public DB2022_Team08_ProductDTO getProduct(String product_num){
        DB2022_Team08_ProductDTO dto =null;
        try {
            getConnection();

            String sql = "SELECT * FROM DB2022_상품 WHERE 상품번호 = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product_num);
            ResultSet r = pstmt.executeQuery();

            if(r.next()) {
                String category = r.getString("상품카테고리");
                String name = r.getString("상품명");
                String size = r.getString("상품사이즈");
                Integer price = r.getInt("판매가격");
                String company = r.getString("제조업체");
                Integer stock = r.getInt("재고량");

                dto = new DB2022_Team08_ProductDTO(product_num, category, name, size,
                price, company, stock);
            }

        } catch (Exception e) {
            System.out.println("예외발생:deleteMember()=> "+e.getMessage());
        }finally{
            dbClose();
        }

        return dto;
    }

    /**
     * 저장된 상품 목록 보기
     */
    public List<DB2022_Team08_ProductDTO> getProductList(){
        List<DB2022_Team08_ProductDTO> list = new ArrayList<DB2022_Team08_ProductDTO>();

        try {
            getConnection();

            String sql = "SELECT * FROM DB2022_상품";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet r = pstmt.executeQuery();

            while(r.next()) {
                String product_num = r.getString("상품번호");
                String category = r.getString("상품카테고리");
                String name = r.getString("상품명");
                String size = r.getString("상품사이즈");
                int price = r.getInt("판매가격");
                String company = r.getString("제조업체");
                int stock = r.getInt("재고량");
                list.add(new DB2022_Team08_ProductDTO(product_num,category,name,size,price,company,stock));
            }

        } catch (Exception e) {
            System.out.println("예외발생:getProductList()=> "+e.getMessage());
        }finally{
            dbClose();
        }

        return list;
    }


    /**
     * 상품 수정
     */
    public boolean updateProduct(DB2022_Team08_ProductDTO dto){

        boolean result = false;
        try {
            getConnection();

            String sql = "UPDATE DB2022_상품 SET 상품카테고리=? ,상품명=?, 상품사이즈=?, 판매가격=?, 제조업체=?, 재고량=? WHERE 상품번호=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,dto.getCategory());
            pstmt.setString(2,dto.getName());
            pstmt.setString(3,dto.getSize());
            pstmt.setInt(4,dto.getPrice());
            pstmt.setString(5,dto.getCompany());
            pstmt.setInt(6,dto.getStock());
            pstmt.setString(7,dto.getProduct_num());

            int r = pstmt.executeUpdate();

            if(r>0) result = true;

        } catch (Exception e) {
            System.out.println("예외발생:updateProduct()=> "+e.getMessage());
        }finally{
            dbClose();
        }
        return result;
    }


    /**
     * 상품 삭제
     */
    public boolean deleteProduct(String productNum){
        boolean result = false;
        try {
            getConnection();

            String sql = "DELETE FROM DB2022_상품 WHERE 상품번호 = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productNum);
            int r = pstmt.executeUpdate();

            if(r>0) result = true;

        } catch (Exception e) {
            System.out.println("예외발생:deleteProduct()=> "+e.getMessage());
        }finally{
            dbClose();
        }
        return result;
    }//deleteMember()--------------


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
