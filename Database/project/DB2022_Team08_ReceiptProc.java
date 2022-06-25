
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
/* 사용자로부터 입력받은 값을 바탕으로 처리하는 클래스 */
public class DB2022_Team08_ReceiptProc {
	
	DB2022_Team08_ReceiptDAO dao;
	
	// 기본 생성자
	public DB2022_Team08_ReceiptProc() {
		dao = new DB2022_Team08_ReceiptDAO();
	}
	
	// 영수증 등록 처리
	public void insertReceipt() {
		Scanner scn = new Scanner(System.in);
		
		System.out.println("영수증 정보를 입력해주세요.");
		System.out.print("▶영수증번호 : ");
		String num = reInput(scn);
		System.out.print("▶전화번호 : ");
		String phoneNum = reInput(scn);
		System.out.print("▶총결제금액: ");
		int totalPrice = Integer.parseInt(reInput(scn));
		System.out.print("▶판매일자: ");
		String date = reInput(scn);
		
		DB2022_Team08_ReceiptDTO dto = new DB2022_Team08_ReceiptDTO(num, phoneNum, totalPrice, date);
		boolean r = dao.insertReceipt(dto);
		
		if (r) {
			System.out.println("영수증 등록이 정상적으로 완료되었습니다.");
		} else {
			System.out.println("영수증 등록이 정상적으로 이루어지지 않았습니다.");
		}
	}
	
	// 저장된 영수증 목록 보기
	public void showReceiptList() {
		List<DB2022_Team08_ReceiptDTO> list = dao.getReceiptList();
		
		System.out.println("                             Receipt List");
		System.out.println("============================================================================");
		
		if (list != null && list.size() > 0) {
			System.out.println("영수증번호\t\t전화번호\t\t총결제금액\t\t판매일자");
            System.out.println("============================================================================");
            
            for (DB2022_Team08_ReceiptDTO dto : list) {
            	System.out.println(dto);
            }
        } else {
        	System.out.println("저장된 데이터가 없습니다. ");
        }
		
		System.out.println("====================================================================총 " + ((list == null)? "0" : list.size()) + " 개=\n");
	}
	
	// 영수증 수정
	public void updateReceipt() {
		Scanner scn = new Scanner(System.in);
		
		System.out.println("수정할 영수증의 영수증번호를 입력하세요.");
		System.out.print("▶ ");
		
		String num = scn.nextLine();
		DB2022_Team08_ReceiptDTO dto = dao.getReceipt(num);
		
		if (dto != null) {
			System.out.println(dto.getInfo());
			System.out.println("수정작업을 계속하시겠습니까? (Y/N)");
			String input = scn.nextLine();
			
			if(input.equalsIgnoreCase("y")){
                System.out.println("-1을 입력하시면 기존의 정보가 그대로 유지됩니다.");
                
                System.out.print("▶수정할 전화번호 : ");
                String phoneNum = scn.nextLine();
                if(phoneNum.trim().equals("-1"))
                	phoneNum = dto.getPhoneNum();
  
                System.out.print("▶수정할 총결제금액 : ");
                int totalPrice = Integer.parseInt(scn.nextLine());
                if(Integer.toString(totalPrice).trim().equals("-1"))
                	totalPrice = dto.getTotalPrice();
                
                System.out.print("▶수정할 판매일자 : ");
                String date = scn.nextLine();
                if(date.trim().equals("-1"))
                	date = dto.getDate();
  
                dto =  new DB2022_Team08_ReceiptDTO(num, phoneNum, totalPrice, date);
                boolean r = dao.updateReceipt(dto);
				
				if (r) {
					System.out.println("영수증의 정보가 다음과 같이 수정되었습니다.");
					System.out.println(dto.getInfo());
				} else {
					System.out.println("영수증의 정보가 정상적으로 수정되지 않았습니다.");
				}
			} else {
				System.out.println("수정 작업을 취소하였습니다.");
			}
		} else {
			System.out.println("입력하신 영수증번호에 해당하는 영수증이 존재하지 않습니다.");
		}
	}
	
	// 영수증 삭제
	public void deleteReceipt() {
		Scanner scn = new Scanner(System.in);
		
		System.out.println("삭제할 영수증의 영수증번호를 입력하세요.");
		System.out.print("▶ ");
		
		String num = scn.nextLine();
		DB2022_Team08_ReceiptDTO dto = dao.getReceipt(num);
		
		if (dto != null) {
			System.out.println(dto.getInfo());
			System.out.println("위 영수증의 정보를 삭제하시겠습니까? (Y/N)");
			String input = scn.nextLine();
			
			if (input.equalsIgnoreCase("y")) {
				boolean r = dao.deleteReceipt(num);
				
				if (r) {
					System.out.println(num + " 영수증의 정보가 정상적으로 삭제되었습니다.");
				} else {
					System.out.println("영수증의 정보가 정상적으로 삭제되지 않았습니다.");
				}
			} else {
				System.out.println("삭제 작업을 취소하였습니다.");
			}
		} else {
			System.out.println("입력하신 영수증번호에 해당하는 영수증이 존재하지 않습니다.");
		}
	}
	
	// 영수증 검색
    public void searchReceipt(){
        Scanner scn = new Scanner(System.in);
        
        System.out.println("검색할 영수증의 영수증번호를 입력해주세요");
        System.out.print("▶ ");
        
        String num = scn.nextLine();
        DB2022_Team08_ReceiptDTO dto = dao.getReceipt(num);
        
        if (dto!=null)
            System.out.println(dto.getInfo());
        else 
        	System.out.println("잘못된 입력입니다.");
    }
	
	public String reInput(Scanner scn) {
		String str = "";
		
		while (true) {
			str = scn.nextLine();
			if (!(str == null || str.trim().equals(""))) {
				break;
			} else {
				System.out.println("공백은 입력하실 수 없습니다. 올바른 값을 입력해 주세요!");
				System.out.print("▶ ");
			}
		}
		
		return str;
	}
}
