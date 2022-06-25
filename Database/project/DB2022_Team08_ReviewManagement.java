import java.util.InputMismatchException;
import java.util.Scanner;

/* 리뷰 관리 프로그램을 실행하는 클래스 */

public class DB2022_Team08_ReviewManagement {
	public static void main(String[] args) {
	       
        DB2022_Team08_ReviewProc mm = new DB2022_Team08_ReviewProc(); //DB2022_Team08_ReviewProc객체 생성
        DB2022_Team08_ReviewViewProc rv = new DB2022_Team08_ReviewViewProc(); //DB2022_Team08_ReviewViewProc객체 생성

        boolean continued = true; //반복문 종료에 사용될 불린 변수
        while (continued) {
            System.out.println();
            System.out.println("============== 리뷰 관리 프로그램 ==============");
            System.out.println("1. 리뷰목록");         
            System.out.println("2. 리뷰등록   3. 리뷰삭제   4. 리뷰정보 수정   5. 내가 쓴 리뷰");
            System.out.println("6. 뒤로가기   7. 모두종료");
            System.out.println("==========================================");
            System.out.print("메뉴를 입력하세요 : ");
           
            Scanner scn = new Scanner(System.in);
            int num=0;
            try {
                num = scn.nextInt();
                if(!(num>0 && num<8)){ //1~7외의 숫자가 입력되면 예외 강제 발생
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("입력된 값이 잘못되었습니다. [1-7] 메뉴를 선택해주세요!");
            }
           
            switch (num) {
            case 1:
            	rv.showReviewList();//작성자, 방문 일자, 영수증 리뷰 등을 제외한 리뷰 목록
                //mm.showReviewList();//리뷰 목록         
                break;
            case 2:
                mm.insertReview(); //리뷰 등록 >>>> 영수증 테이블의 회원 번호와 맞는지 확인하는 거 넣기
                break;
            case 3:
                mm.deleteReview(); //리뷰 삭제             
                break;
            case 4:
                mm.updateReview(); //리뷰정보 수정
                break;
            case 5:
            	mm.searchReview(); //리뷰 검색
            	break;
            case 6:
            	System.out.println("뒤로 가기가 완료되었습니다.");
            	continued=false; //while문 종료 > 뒤로가기
            	break; // 뒤로가기
            case 7:
            	System.out.println("프로그램을 종료합니다.");
                System.exit(0); //프로그램 종료
                   
            }//end of switch()
        }//end of while      
    }//end of main()
}
