
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
public class DB2022_Team08_ItemProc {
	
	DB2022_Team08_ItemDAO dao;
	
	// 기본 생성자
	public DB2022_Team08_ItemProc() {
		dao = new DB2022_Team08_ItemDAO();
	}
	
	// 품목 등록 처리
	public void insertItem() {
		Scanner scn = new Scanner(System.in);
		
		System.out.println("품목 정보를 입력해주세요.");
		System.out.print("▶영수증번호 : ");
		String recNum = reInput(scn);
		System.out.print("▶상품번호 : ");
		String itemNum = reInput(scn);
		System.out.print("▶수량: ");
		int amount = Integer.parseInt(reInput(scn));
		System.out.print("▶가격 : ");
		int price = Integer.parseInt(reInput(scn));
		
		DB2022_Team08_ItemDTO dto = new DB2022_Team08_ItemDTO(recNum, itemNum, amount, price);
		boolean r = dao.insertItem(dto); // 입력받은 데이터 추가
		
		if (r) {
			System.out.println("품목 등록이 정상적으로 완료되었습니다.");
		} else {
			System.out.println("품목 등록이 정상적으로 이루어지지 않았습니다.");
		}
	}
	
	// 저장된 품목 목록 보기
	public void showItemList() {
		List<DB2022_Team08_ItemDTO> list = dao.getItemList();
		
		System.out.println("                             Item List");
		System.out.println("============================================================================");
		
		if (list != null && list.size() > 0) {
			System.out.println("영수증번호\t\t상품번호\t\t상품명\t\t수량\t\t가격");
            System.out.println("============================================================================");
            
            for (DB2022_Team08_ItemDTO dto : list) {
            	String pd_name = dto.getItemName();
            	if(pd_name.length()>=6)
                    System.out.println(dto.getRecNum()+"\t\t"+dto.getItemNum()+"\t\t"+pd_name+"\t"+dto.getAmount()+"\t\t"+dto.getPrice());
            	else           	
            		System.out.println(dto.getRecNum()+"\t\t"+dto.getItemNum()+"\t\t"+pd_name+"\t\t"+dto.getAmount()+"\t\t"+dto.getPrice());

            }
        } else {
        	System.out.println("저장된 데이터가 없습니다. ");
        }
		
		System.out.println("====================================================================총 " + ((list == null)? "0" : list.size()) + " 개=\n");
	}
	
	// 품목 수정
	public void updateItem() {
		Scanner scn = new Scanner(System.in);
		
		System.out.println("수정할 품목의 영수증번호를 입력하세요.");
		System.out.print("▶ ");
		String recNum = scn.nextLine();
		
		System.out.println("수정할 품목의 상품번호를 입력하세요.");
		System.out.print("▶ ");
		String itemNum = scn.nextLine();
		DB2022_Team08_ItemDTO dto = dao.getItem(recNum, itemNum);
		
		if (dto != null) {
			System.out.println(dto.getInfo());
			System.out.println("수정작업을 계속하시겠습니까? (Y/N)");
			String input = scn.nextLine();
			
			if(input.equalsIgnoreCase("y")){
                System.out.println("-1을 입력하시면 기존의 정보가 그대로 유지됩니다.");
                
                System.out.print("▶수정할 수량 : ");
                int amount = Integer.parseInt(scn.nextLine());
                if(Integer.toString(amount).trim().equals("-1"))
                	amount = dto.getAmount();
  
                System.out.print("▶수정할 가격 : ");
                int price = Integer.parseInt(scn.nextLine());
                if(Integer.toString(price).trim().equals("-1"))
                	price = dto.getPrice();
  
                dto =  new DB2022_Team08_ItemDTO(recNum, itemNum, amount, price);
                boolean r = dao.updateItem(dto);
				
				if (r) {
					System.out.println("품목의 정보가 다음과 같이 수정되었습니다.");
					System.out.println(dto.getInfo());
				} else {
					System.out.println("품목의 정보가 정상적으로 수정되지 않았습니다.");
				}
			} else {
				System.out.println("수정 작업을 취소하였습니다.");
			}
		} else {
			System.out.println("입력하신 영수증번호와 상품번호에 해당하는 품목이 존재하지 않습니다.");
		}
	}
	
	// 품목 삭제
	public void deleteItem() {
		Scanner scn = new Scanner(System.in);
		
		System.out.println("삭제할 품목의 영수증번호를 입력하세요.");
		System.out.print("▶ ");
		String recNum = scn.nextLine();
		
		System.out.println("삭제할 품목의 상품번호를 입력하세요.");
		System.out.print("▶ ");
		String itemNum = scn.nextLine();
		DB2022_Team08_ItemDTO dto = dao.getItem(recNum, itemNum);
		
		if (dto != null) {
			System.out.println(dto.getInfo());
			System.out.println("위 품목의 정보를 삭제하시겠습니까? (Y/N)");
			String input = scn.nextLine();
			
			if (input.equalsIgnoreCase("y")) {
				boolean r = dao.deleteItem(recNum, itemNum);
				
				if (r) {
					System.out.println(recNum + " " + itemNum + " 품목 정보가 정상적으로 삭제되었습니다.");
				} else {
					System.out.println("품목 정보가 정상적으로 삭제되지 않았습니다.");
				}
			} else {
				System.out.println("삭제 작업을 취소하였습니다.");
			}
		} else {
			System.out.println("입력하신 영수증번호와 상품번호에 해당하는 품목이 존재하지 않습니다.");
		}
	}
	
	// 품목 검색
    public void searchItem(){
        Scanner scn = new Scanner(System.in);
        
        System.out.println("검색할 품목의 영수증번호를 입력하세요.");
		System.out.print("▶ ");
		String recNum = scn.nextLine();
		
		System.out.println("검색할 품목의 상품번호를 입력하세요.");
		System.out.print("▶ ");
		String itemNum = scn.nextLine();
		DB2022_Team08_ItemDTO dto = dao.getItem(recNum, itemNum);
        
        if (dto!=null)
            System.out.println(dto.getInfo());
        else 
        	System.out.println("잘못된 입력입니다.");
    }
	
	public String reInput(Scanner scn) {
		String str = "";
		
		while (true) {
			str = scn.nextLine();
			if (!(str == null || str.trim().contentEquals(""))) {
				break;
			} else {
				System.out.println("공백은 입력하실 수 없습니다. 올바른 값을 입력해 주세요!");
				System.out.print("▶ ");
			}
		}
		
		return str;
	}
}
