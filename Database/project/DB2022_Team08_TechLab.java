import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DB2022_Team08_TechLab {

private static Connection conn;
	
	private static PreparedStatement pstmt;
	private static CallableStatement cstmt;
	private static ResultSet rs;
	
	// 기본 생성자
	public DB2022_Team08_TechLab() {
		
	}
	
	private static void getConnection() throws ClassNotFoundException, SQLException {
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
	
	public static void main(String[] args) {
        boolean continued = true;   
        while (continued) {
            System.out.println();
    		System.out.println("                        기능 실험실 입니다");
            System.out.println("==================================================================");
    		System.out.println("1. 우수고객확인");
    		System.out.println("2. 발주진행상황확인");
            System.out.println("3. 뒤로가기");
            System.out.println("4. 모두 종료");
            System.out.println();
            System.out.println("* 기능 실험실이란? ");
            System.out.println("정식 업데이트 서비스 전 제공하는 기능으로 언제든 없어지거나 바뀔 수 있는 기능입니다 :)");
            System.out.println("==================================================================");
            System.out.print("메뉴를 입력하세요 : ");
           
            Scanner scn = new Scanner(System.in);
            int num=0;
            try {
                num = scn.nextInt();
                if(!(num>0 && num<5)){ //1~4외의 숫자가 입력되면 예외 강제 발생
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("입력된 값이 잘못되었습니다. [1-4] 메뉴를 선택해주세요!");
            }
           
            switch (num) {
            case 1:
                goodcustomers();        
                break;
            case 2:
                orderisokay();
                break;
            case 3:
            	System.out.println("뒤로 가기가 완료되었습니다.");
            	continued=false; //while문 종료 > 뒤로가기
            	break;
            case 4:
            	 System.out.println("프로그램을 종료합니다.");
                 System.exit(0); //프로그램 종료                  
            }//end of switch()
        }//end of while 
	}
	
	// 우수고객관리
	public static boolean goodcustomers() {
		boolean result = false;
		
		try {
			getConnection();
			//중첩된 쿼리 사용
			//1년 내에 방문한 적이 있으며 리뷰 평점이 4점 이상이었고 회원등급이 실버보다 높은 경우 > 우수회원
			String sql = "select 회원이름, 전화번호, 회원등급\r\n"
					+ "from db2022_회원\r\n"
					+ "where (회원등급='gold' || 회원등급='vip') and 전화번호 in (\r\n"
					+ "    select 작성자\r\n"
					+ "    from db2022_리뷰\r\n"
					+ "    where 평점>3 and 방문일자>='2021-01-01'\r\n"
					+ ");";
			PreparedStatement stmt = conn.prepareStatement(sql);	
			
			rs = stmt.executeQuery();
			
			System.out.println();
			System.out.println("        1년 내에 방문하여 4점 이상의 평점 리뷰를 작성한 gold, vip 회원이에요.");
	        System.out.println("============================================================================");
			int count = 0;
			
			if (!rs.next()) System.out.println("해당 정보가 존재하지 않습니다."); //empty set인 경우
			else {
				count++;
				System.out.println("회원 이름\t\t\t\t 전화번호\t\t\t\t회원 등급");
				System.out.println("============================================================================");
				System.out.println(rs.getString(1)+"\t\t\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3));
		        
			}
			while(rs.next()) {
				System.out.println(rs.getString(1)+"\t\t\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3));
				count++;
			}
	        System.out.println("======================================================================총 "+count+"개=");

		} catch (Exception e) {
			System.out.println("예외 발생: insertReceipt() => "+ e.getMessage());
		} finally {
			dbClose();
		}
		
		return result;
	}
	
	
		
    
	
	// 발주상황관리
	public static boolean orderisokay() {
		boolean result = false;
		
		try {
			getConnection();
			//중첩된 쿼리 사용
			//재고량이 50보다 작은 상품에 대해서 최근 발주 기록이 3일보다 오래된 상품을 출력
			String sql = "SELECT 재고량, 상품명, MAX(발주날짜) as 최근발주날짜\r\n"
					+ "FROM DB2022_상품, DB2022_발주\r\n"
					+ "WHERE 재고량<50 and 발주상품번호 = 상품번호 and 상품번호  in (\r\n"
					+ "    SELECT 발주상품번호\r\n"
					+ "    FROM DB2022_발주, DB2022_상품\r\n"
					+ "    WHERE 발주날짜 < Date_sub(NOW()  , INTERVAL 3 DAY)\r\n"
					+ ")\r\n"
					+ "GROUP BY 발주상품번호;";
			
					
					
			PreparedStatement stmt = conn.prepareStatement(sql);		
			
			rs = stmt.executeQuery();
			//int r = stmt.executeUpdate();
			System.out.println();
			System.out.println("             재고량이 50 미만이고 마지막 발주로부터 3일 이상 경과하였어요.");
	        System.out.println("============================================================================");
			int count = 0;
			if (!rs.next()) System.out.println("해당 정보가 존재하지 않습니다."); //empty set인 경우
			else 
			{
				count++;
				System.out.println("재고량\t\t\t  상품명\t\t\t\t  최근발주날짜\t\t\t");
				System.out.println("============================================================================");

		            	
				if(rs.getString(2).length()>=6)
		           	System.out.println(rs.getString(1)+"\t\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3));
		        else
		        	System.out.println(rs.getString(1)+"\t\t\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3));
		            
			}
				
			while(rs.next()) {
				count++;
				if(rs.getString(2).length()>=6)
					System.out.println(rs.getString(1)+"\t\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3));
				else
					System.out.println(rs.getString(1)+"\t\t\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3));
			}
	        System.out.println("======================================================================총 "+count+"개=");

			
		} catch (Exception e) {
			System.out.println("예외 발생: insertReceipt() => "+ e.getMessage());
		} finally {
			dbClose();
		}
		
		return result;
	}

	
	// DB 연결 해제
	public static void dbClose() {
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
