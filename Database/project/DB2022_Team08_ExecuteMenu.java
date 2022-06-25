import java.util.InputMismatchException;
import java.util.Scanner;

public class DB2022_Team08_ExecuteMenu {

	public static void main(String[] args) {
	       
           
        while (true) {
            System.out.println();
            System.out.println("============ 주류 매장 관리 프로그램 ============");
            System.out.println("1. 고객 회원\t\t\t2. 사장님 회원");         
            System.out.println("3. 종료");
            System.out.println("(결과값은 아래 콘솔창에 출력됩니다.)");
            System.out.println("==========================================");
            System.out.print("메뉴를 입력하세요 : ");
           
            Scanner scn = new Scanner(System.in);
            int num=0;
            try {
                num = scn.nextInt();
                if(!(num>0 && num<4)){ //1~3외의 숫자가 입력되면 예외 강제 발생
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("입력된 값이 잘못되었습니다. [1-3] 메뉴를 선택해주세요!");
            }
           
            switch (num) {
            case 1:
            	DB2022_Team08_ExecuteCustomer.main(args);
                //고객 회원 제공 메뉴    
                break;
            case 2:
            	DB2022_Team08_ExecuteCEO.main(args);
                //사장님 회원(점주) 제공 메뉴
                break;
            case 3:
                System.out.println("프로그램을 종료합니다.");
                System.exit(0); //프로그램 종료
                   
            }//end of switch()
        }//end of while      
    }//end of main()

}
