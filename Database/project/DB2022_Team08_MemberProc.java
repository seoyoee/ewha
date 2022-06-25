import java.util.List;
import java.util.Scanner;

/* 사용자로부터 입력받은 값을 바탕으로 처리하는 클래스 */

public class DB2022_Team08_MemberProc {
	DB2022_Team08_MemberDAO dao;
	   
    public DB2022_Team08_MemberProc() {
		// TODO Auto-generated constructor stub
    	   dao = new DB2022_Team08_MemberDAO();
	}
    
    /*회원 등록처리*/
    public void insertMember(){        
       
        Scanner scn = new Scanner(System.in);
       
        System.out.println("회원정보를 입력해주세요."); //사용자로부터 입력 받기
        System.out.print("▶전화번호(010-0000-0000형식으로 입력) : ");
        //String phone_num = scn.nextLine();
        String phone_num = reInput(scn);
        System.out.print("▶이름 : ");
        //String name = scn.nextLine();
        String name = reInput(scn);  
        String grade ="bronze";
        Integer purchase = 0;
        // 회원 등록은 무조건 신규라고 가정

        DB2022_Team08_MemberDTO dto = new DB2022_Team08_MemberDTO(phone_num, name, purchase, grade);    
        boolean r = dao.insertMember(dto); //입력받은 데이터 추가
       
        if(r){
            System.out.println("회원등록이 정상적으로 완료되었습니다.");
        }else{
            System.out.println("회원등록이 정상적으로 이루어지지 않았습니다.");
        }
       
    }  
     
    /*저장된 회원 정보 목록 보기*/
    public void showMemberList(){
   
        List<DB2022_Team08_MemberDTO> list = dao.getMemberList();
       
        System.out.println("                             Member List");
        System.out.println("============================================================================");
        if(list!=null&&list.size()>0){         
            System.out.println("전화번호\t\t이름\t\t구매금액\t\t회원등급");
            System.out.println("============================================================================");
           
            for (DB2022_Team08_MemberDTO dto : list){
                System.out.println(dto.getPhone_num()+"\t"+dto.getName()+"\t\t"+dto.getPurchase()+"\t\t"+dto.getGrade());
            }          
           
        }else{
            System.out.println("저장된 데이터가 없습니다. ");
        }
        System.out.println("====================================================================총 "+((list==null)?"0":list.size())+" 명=\n");
    }
   
    /*회원 정보 수정*/
    public void updateMember(){
    	Scanner scn = new Scanner(System.in);
        System.out.println("수정할 회원의 전화번호를 입력해주세요(010-0000-0000형식으로 입력)"); //사용자로부터 입력받기
        System.out.print("▶");
        String phone_num = scn.nextLine();
        DB2022_Team08_MemberDTO dto = dao.getMember(phone_num);
        if (dto!=null) {
           
            System.out.println(dto.getInfo());
           
           
            System.out.println("수정작업을 계속하시겠습니까?(Y/N)");
            String input = scn.nextLine();
            if(input.equalsIgnoreCase("y")){
                System.out.println("-1을 입력하시면 기존의 정보가 그대로 유지됩니다.");
                System.out.print("▶수정할 이름 : ");
                String name = scn.nextLine();
                if(name.trim().equals("-1")) name=dto.getName();
                
                System.out.print("▶수정할 회원등급 : ");
                String grade = scn.nextLine();
                if(grade.trim().equals("-1")) grade=dto.getGrade();
                
                System.out.print("▶수정할 구매금액 :  ");
                Integer purchase = scn.nextInt();
                if(purchase==-1) purchase=dto.getPurchase();
                
                dto =  new DB2022_Team08_MemberDTO(phone_num, name, purchase, grade);
               
                boolean r = dao.updateMember(dto);
               
                if(r){
                   
                    System.out.println("회원의 정보가 다음과 같이 수정되었습니다.");
                    System.out.println(dto.getInfo());
                   
                }else{
                    System.out.println("회원의 정보가 정상적으로 수정되지 않았습니다.");
                }
               
            }else{
                System.out.println("수정 작업을 취소하였습니다.");
            }
           
        }else{
           
            System.out.println("입력하신 전화번호에 해당하는 회원이 존재하지 않습니다.");   
        }
    }
   
    /*회원 삭제*/
    public void deleteMember(){
       
        Scanner scn = new Scanner(System.in);
        System.out.println("삭제할 회원의 전화번호를 입력해주세요(010-0000-0000형식으로 입력)"); //사용자로부터 입력받기
        String phone_num = scn.nextLine();
        DB2022_Team08_MemberDTO dto = dao.getMember(phone_num);
        if (dto!=null) {
            System.out.println(dto.getInfo());
           
            System.out.println("위 회원의 정보를 정말로 삭제하시겠습니까?(Y/N)");
            String input = scn.nextLine();
            if(input.equalsIgnoreCase("y")){
                boolean r = dao.deleteMember(phone_num);
               
                if(r){
                    System.out.println(phone_num+"회원의 정보가 정상적으로 삭제되었습니다.");
                }else{
                    System.out.println("회원의 정보가 정상적으로 삭제되지 않았습니다.");
                }
            }else{
                System.out.println("삭제 작업을 취소하였습니다.");
            }
        }else{
           
            System.out.println("입력하신 전화번호에 해당하는 회원이 존재하지 않습니다.");
           
        }
    }
    
    /*회원 검색*/
    public void searchMember(){
       
        Scanner scn = new Scanner(System.in);
        System.out.println("검색할 회원의 전화번호를 입력해주세요(010-0000-0000형식으로 입력)"); //사용자로부터 입력받기
        String phone_num = scn.nextLine();
        DB2022_Team08_MemberDTO dto = dao.getMember(phone_num);
        if (dto!=null)
            System.out.println(dto.getInfo());
        else 
        	System.out.println("잘못된 입력입니다");
     
    }
    
    /*공백, 음수입력시 재입력 받기*/
    public String reInput(Scanner scn){ //string 형태에서 공백 입력시 처리
       
        String str="";
        while(true){
            str = scn.nextLine();
            if(!(str==null || str.trim().equals(""))){
                break;
            }else{
                System.out.println("공백은 입력하실 수 없습니다. 올바른 값을 입력해주세요!");
                System.out.print("▶");
            }
        }
        return str;
    }
    
    public Integer reInput2(Scanner scn){ //integer 형태에서 음수 입력시 처리
        
        int i=-1;
        while(true){
            i = scn.nextInt();
            if(!(i<0)){
                break;
            }else{
                System.out.println("0 이하는 입력하실 수 없습니다. 올바른 값을 입력해주세요!");
                System.out.print("▶");
            }
        }
       
        return i;
    }

}
