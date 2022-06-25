
import java.util.InputMismatchException;
import java.util.Scanner;
/* 품목 관리 프로그램을 실행하는 클래스 */
public class DB2022_Team08_ItemManagement {
	
	public static void main(String[] args) {
		
		DB2022_Team08_ItemProc rr = new DB2022_Team08_ItemProc(); // ItemProc 객체 생성
		boolean continued = true;
		while (continued) {
			System.out.println();
            System.out.println("============== 품목 관리 프로그램 ==============");
			System.out.println("1. 품목목록");         
            System.out.println("2. 품목등록   3. 품목삭제   4. 품목수정   5. 품목검색");
            System.out.println("6. 뒤로가기   7. 모두 종료");
            System.out.println("==========================================");
            System.out.print("메뉴를 입력하세요 : ");
            
            Scanner scn = new Scanner(System.in);
            int num = 0;
            
            try {
            	num = scn.nextInt();
            	
            	if (!(num > 0 && num < 8)) { // 1-7 이외의 숫자가 입력되면 예외 강제 발생
            		throw new InputMismatchException();
            	}
            } catch (InputMismatchException e) {
            	System.out.println("[1-7] 메뉴를 선택하세요.");
            }
            
            switch (num) {
            case 1:
            	rr.showItemList(); // 품목목록
            	break;
            case 2:
            	rr.insertItem(); // 품목등록
            	break;
            case 3:
            	rr.deleteItem(); // 품목삭제
            	break;
            case 4:
            	rr.updateItem(); // 품목수정
            	break;
            case 5:
            	rr.searchItem(); // 품목검색
            	break;
            case 6:
            	System.out.println("뒤로 가기가 완료되었습니다.");
            	continued=false; //while문 종료 > 뒤로가기
            	break;
            case 7 : 
            	System.out.println("프로그램을 종료합니다.");
            	System.exit(0); // 프로그램 종료
            }
		}
	}
}
