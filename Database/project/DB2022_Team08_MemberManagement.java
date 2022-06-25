import java.util.InputMismatchException;
import java.util.Scanner;

/* 멤버 관리 프로그램을 실행하는 클래스 */

public class DB2022_Team08_MemberManagement {
	
	public static void main(String[] args) {
	       
        DB2022_Team08_MemberProc mm = new DB2022_Team08_MemberProc(); //DB2022_Team08_MemberProc객체 생성
        boolean continued = true;   
        while (continued) {
            System.out.println();
            System.out.println("============== 멤버 관리 프로그램 ==============");
            System.out.println("1. 회원목록");         
            System.out.println("2. 회원등록   3. 회원삭제   4. 회원정보 수정   5. 회원 검색");
            System.out.println("6. 뒤로가기   7. 모두 종료");
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
                mm.showMemberList();//회원 목록         
                break;
            case 2:
                mm.insertMember(); //회원 등록
                break;
            case 3:
                mm.deleteMember(); //회원 삭제             
                break;
            case 4:
                mm.updateMember(); //회원 정보 수정
                break;
            case 5:
            	mm.searchMember(); //회원 검색
            	break;
            case 6:
            	System.out.println("뒤로 가기가 완료되었습니다.");
            	continued=false; //while문 종료 > 뒤로가기
            	break;
            case 7:
                System.out.println("프로그램을 종료합니다.");
                System.exit(0); //프로그램 종료
                   
            }//end of switch()
        }//end of while 
        
    }//end of main()
   


}
