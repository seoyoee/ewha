import java.util.InputMismatchException;
import java.util.Scanner;

//고객 회원이 사용하는 메뉴 실행하는 클래스

public class DB2022_Team08_ExecuteCustomer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DB2022_Team08_MemberProc mm = new DB2022_Team08_MemberProc(); //DB2022_Team08_MemberProc객체 생성
		DB2022_Team08_PriceViewProc pv = new DB2022_Team08_PriceViewProc(); //DB2022_Team08_PriceViewProc 객체 생성
		
		boolean continued = true;
        while (continued) {
        	 System.out.println();
             System.out.println("============ 고객 회원 관리 프로그램 ============");
             System.out.println("1. 로그인(리뷰 관리)\t2. 회원 가입\t3. 상품 조회");         
             System.out.println("4. 뒤로가기\t5. 모두 종료");
             System.out.println("==========================================");
            
            Scanner scn = new Scanner(System.in);
            int num=0;
            try {
                num = scn.nextInt();
                if(!(num>0 && num<6)){ //1~5외의 숫자가 입력되면 예외 강제 발생
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("입력된 값이 잘못되었습니다. [1-5] 메뉴를 선택해주세요!");
            }
           
            switch (num) {
            case 1:
            	
            	DB2022_Team08_MemberDAO dao = new DB2022_Team08_MemberDAO();
                System.out.println("검색할 회원의 전화번호를 입력해주세요(010-0000-0000형식으로 입력)");//사용자로부터 입력받기
                scn.nextLine();
                String phone_num = scn.nextLine();
                DB2022_Team08_MemberDTO dto = dao.getMember(phone_num);
                if (dto!=null) { //로그인 성공시 리뷰 관리로 이동
                    System.out.println(dto.getInfo());
                	DB2022_Team08_ReviewManagement.main(args);
                	}
                else //로그인 실패
                	System.out.println("입력하신 전화번호에 해당하는 회원이 존재하지 않습니다.");
              break;
            case 2:
                mm.insertMember();//회원등록  
                break;
            case 3:
            	pv.showProductList();//판매중인 상품의 상품명, 용량, 가격 list를 보여줌
            	break;
            case 4:
            	System.out.println("뒤로 가기가 완료되었습니다.");
            	continued=false; //while문 종료 > 뒤로가기
            	break;
            case 5:
                System.out.println("프로그램을 종료합니다.");
                System.exit(0); //프로그램 종료
                   
            }//end of switch()
        }//end of while      
	}

}
