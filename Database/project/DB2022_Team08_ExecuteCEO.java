import java.util.InputMismatchException;
import java.util.Scanner;
//점주 회원이 사용하는 메뉴 실행하는 클래스
public class DB2022_Team08_ExecuteCEO {

	public static void main(String[] args) {
		
		DB2022_Team08_ReviewViewProc rv= new DB2022_Team08_ReviewViewProc(); //Team08_ReviewViewProc 객체 생성
		
		boolean continued = true;
		while (continued) {
			System.out.println();
	        System.out.println("============== 점주 회원 관리 프로그램 ==============");
	        System.out.println("1. 고객 정보 관리\t2. 발주 관리    3. 상품 관리");
	        System.out.println("4. 리뷰 조회   \t5. 영수증 관리   6. 품목 관리 ");
	        System.out.println("7. 뒤로가기\t8. 모두 종료");
	        
	        System.out.println();
	        System.out.println("0. 실험실"); 
	        System.out.println("==============================================");
	        System.out.print("메뉴를 입력하세요 : ");
	
	        Scanner scn = new Scanner(System.in);
	        int num=0;
	        try {
	            num = scn.nextInt();
	            if(!(num>=0 && num<9)){ //0~8외의 숫자가 입력되면 예외 강제 발생
	                throw new InputMismatchException();
	            }
	        } catch (InputMismatchException e) {
	            System.out.println("입력된 값이 잘못되었습니다. [0-8] 메뉴를 선택해주세요!");
	        }
	
	        switch (num) {
	        	case 0:
	        		DB2022_Team08_TechLab.main(args);//실험실 프로그램 실행
	        		break;
	            case 1:
	                DB2022_Team08_MemberManagement.main(args);//고객 정보 관리 프로그램 실행
	                break;
	            case 2:
	            	DB2022_Team08_OrderManagement.main(args);//발주 관리 프로그램 실행
	                break;
	            case 3:
	                DB2022_Team08_ProductManagement.main(args);//상품 관리 프로그램 실행
	                break;
	            case 4:
	            	rv.showReviewList();//작성자, 방문 일자, 영수증 리뷰 등을 제외한 리뷰 목록을 제공하는 리뷰 조회 프로그램 실행
	                break;
	            case 5:
	                DB2022_Team08_ReceiptManagement.main(args);//영수증 관리 프로그램 실행
	                   break;
	            case 6:
	            	DB2022_Team08_ProductManagement.main(args);//품목 관리 프로그램 실행
	                break; 
	            case 7:
	            	System.out.println("뒤로 가기가 완료되었습니다.");
	            	continued=false; //while문 종료 > 뒤로가기
	            	break;
	            case 8 : 
	                System.out.println("프로그램을 종료합니다.");
	                System.exit(0); //프로그램 종료
	
	        }//end of switch()

		}//end of while
    }//end of main

	

}
