import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;

/* 사용자로부터 입력받은 값을 바탕으로 처리하는 클래스 */

public class DB2022_Team08_ReviewProc {
	DB2022_Team08_ReviewDAO dao;
	
	   
    //기본생성자
    public DB2022_Team08_ReviewProc() {
		// TODO Auto-generated constructor stub
    	   dao = new DB2022_Team08_ReviewDAO();
	}
    DB2022_Team08_ReceiptDAO rdao = new DB2022_Team08_ReceiptDAO(); //영수증 번호 확인을 위해 ReceiptDAO 객체 생성
    DB2022_Team08_ReceiptDTO rdto;//영수증 번호 확인을 위해 ReceiptDTO 객체 생성
    LocalDate now = LocalDate.now();
    
    /* 리뷰 등록처리*/
    public void insertReview(){        
       
        Scanner scn = new Scanner(System.in);
       
        System.out.println("리뷰정보를 입력해주세요."); //사용자로부터 입력받기
        System.out.print("▶영수증번호 : ");
        
        // >>>> 영수증 테이블에서 확인하기
        
       String receipt_num = reInput(scn);
       
       String write_date = now.toString(); //작성 일자는 현재 날짜로 자동 설정
       
        
        System.out.println("▶본인 확인을 위해 전화번호를 입력해주세요.(010-0000-0000형식으로 입력)"); //본인이 구매한 영수증이 맞는지 작성자 번호를 입력받아 확인
       //String writer = scn.nextLine();
        String writer = reInput(scn);
        
        
        
        
        rdto = rdao.getReceipt(receipt_num);
        String visit_date = rdto.getDate(); // 방문 일자 : 영수증 테이블에서 가져옴
        String phone;
        if (rdto!=null) {
             phone = rdto.getPhoneNum();
        	if(writer.equals(phone))
        		{
        		System.out.print("▶평점 (1~5 사이의 정수를 입력하세요) : ");
                //Integer stars = scn.nextInt();
                Integer stars = reInput2(scn); 
                while (!(stars==1||stars==2||stars==3||stars==4||stars==5))
                {
                	System.out.println("올바른 입력값이 아닙니다.");
                	System.out.print("▶평점 (1~5 사이의 정수를 입력하세요) : ");
                    //Integer stars = scn.nextInt();
                    stars = reInput2(scn); 
                }
                DB2022_Team08_ReviewDTO dto = new DB2022_Team08_ReviewDTO(receipt_num, visit_date, writer, stars, write_date);    
                boolean r = dao.insertReview(dto); //입력받은 데이터 추가
               
                if(r){
                    System.out.println("리뷰등록이 정상적으로 완료되었습니다.");
                }else{
                    System.out.println("리뷰등록이 정상적으로 이루어지지 않았습니다.");
                }
        		} //>> 등록 진행
        		else 
                	System.out.println("본인이 구매한 영수증에 대해서만 리뷰를 등록할 수 있습니다.");
        }else {
			System.out.println("입력하신 정보에 해당하는 영수증이 존재하지 않습니다.");
			System.out.println("올바른 영수증 번호 또는 전화번호인지 확인해주세요.");
		}
        
        
        
        
    }  
   
   
    /* 리뷰 정보 수정*/
    public void updateReview(){ //사용자로부터 입력받기
        Scanner scn = new Scanner(System.in);
        System.out.println("수정할 리뷰의 영수증번호를 입력해주세요");
        System.out.print("▶");
        String receipt_num = scn.nextLine();
        DB2022_Team08_ReviewDTO dto = dao.getReview(receipt_num);
        if (dto!=null) {
           
            System.out.println(dto.getInfo());
            
           
           
            System.out.println("수정작업을 계속하시겠습니까?(Y/N)");
            String input = scn.nextLine();
            if(input.equalsIgnoreCase("y")){ //사용자는 평점만 수정 가능, 작성 일자는 수정일(현재 날짜)로 자동 업데이트
                System.out.println("-1을 입력하시면 기존의 정보가 그대로 유지됩니다.");
  
                System.out.println("▶본인 확인을 위해 전화번호를 입력해주세요.(010-0000-0000형식으로 입력)");//본인이 구매한 영수증이 맞는지 작성자 번호를 입력받아 확인
                String writer = scn.nextLine();
                if(writer.trim().equals("-1")) writer=dto.getWriter();
                
                rdto = rdao.getReceipt(receipt_num);
                String visit_date = rdto.getDate(); // 방문 일자 : 영수증 테이블에서 가져옴
                String phone;
                String write_date = now.toString(); //작성 일자는 현재 날짜로 자동 설정
                

                if (rdto!=null) {
                    phone = rdto.getPhoneNum();
               	if(writer.equals(phone))
               		{
               		System.out.print("▶ 수정할 평점 (1~5 사이의 정수를 입력하세요) : ");
                       //Integer stars = scn.nextInt();
                       Integer stars = reInput2(scn); 
                       if(stars==-1) stars=dto.getStars();
                       else {while (!(stars==1||stars==2||stars==3||stars==4||stars==5))
                       {
                       	System.out.println("올바른 입력값이 아닙니다.");
                       	System.out.print("▶평점 (1~5 사이의 정수를 입력하세요) : ");
                           //Integer stars = scn.nextInt();
                           stars = reInput2(scn); 
                       }
                       }//-1(이전 값 유지) 또는 1~5 사이의 정수가 아닌 다른 값이 입력되었을 시 재입력
                       dto = new DB2022_Team08_ReviewDTO(receipt_num, visit_date, writer, stars, write_date);    
                       boolean r = dao.updateReview(dto); //입력받은 데이터 추가
                      
                       if(r){
                    	   System.out.println("리뷰 정보가 다음과 같이 수정되었습니다.");
                           System.out.println(dto.getInfo());
                       }else{
                    	   System.out.println("리뷰 정보가 정상적으로 수정되지 않았습니다.");
                       }
               		} //>> 등록 진행
               		else 
                       	System.out.println("본인이 구매한 영수증에 대해서만 리뷰를 수정할 수 있습니다.");
               }
                
            }else{
                System.out.println("수정 작업을 취소하였습니다.");
            }
           
        }else{ 
            System.out.println("입력하신 정보에 해당하는 리뷰가 존재하지 않습니다.");
            System.out.println("올바른 영수증 번호 또는 전화번호인지 확인해주세요.");
        }
    }
   
    /*리뷰 삭제*/
    public void deleteReview(){
       
        Scanner scn = new Scanner(System.in);
        System.out.println("삭제할 리뷰의 영수증번호를 입력해주세요"); //사용자로부터 입력받기
        String receipt_num = scn.nextLine();
        DB2022_Team08_ReviewDTO dto = dao.getReview(receipt_num);
        
        
        System.out.println("▶본인 확인을 위해 전화번호를 입력해주세요.(010-0000-0000형식으로 입력)"); //본인이 구매한 영수증이 맞는지 작성자 번호를 입력받아 확인
        //String writer = scn.nextLine();
         String writer = reInput(scn);
         
         
         
         
         rdto = rdao.getReceipt(receipt_num);
         String phone;
         if (rdto!=null) {
              phone = rdto.getPhoneNum();
         	if(writer.equals(phone))
         	{
         		if (dto!=null) {
                    System.out.println(dto.getInfo());
                    System.out.println("위 리뷰 정보를 정말로 삭제하시겠습니까?(Y/N)");
                    String input = scn.nextLine();
                    if(input.equalsIgnoreCase("y")){
                        boolean r = dao.deleteReview(receipt_num);
                       
                        if(r){
                            System.out.println(receipt_num+"리뷰 정보가 정상적으로 삭제되었습니다.");
                        }else{
                            System.out.println("리뷰 정보가 정상적으로 삭제되지 않았습니다.");
                        }
                    }else{
                        System.out.println("삭제 작업을 취소하였습니다.");
                    }
                }else{
                   
                    System.out.println("입력하신 영수증번호에 해당하는 리뷰가 존재하지 않습니다.");
                }
         	}
         	else 
         		System.out.println("본인이 구매한 영수증에 대해서만 리뷰를 삭제할 수 있습니다.");
         }else {
 			System.out.println("입력하신 정보에 해당하는 영수증이 존재하지 않습니다.");
 			System.out.println("올바른 영수증 번호 또는 전화번호인지 확인해주세요.");
 		}
        
        
        
    }
      
    /*내가 리뷰 검색 > 전화번호로 검색*/
    public void searchReview(){
    	Scanner scn = new Scanner(System.in);
    	System.out.println("▶본인 확인을 위해 전화번호를 입력해주세요.(010-0000-0000형식으로 입력)");
    	String phone= scn.nextLine();
        List<DB2022_Team08_ReviewDTO> list = dao.getMyReviewList(phone);
       
        System.out.println("                             Review List");
        System.out.println("============================================================================");
        if(list!=null&&list.size()>0){         
            System.out.println("영수증번호\t\t  방문일자\t\t     작성자\t       평점\t   작성일자");
            System.out.println("============================================================================");
           
            for (DB2022_Team08_ReviewDTO dto : list){
                System.out.println(dto.getReceipt_num()+"\t\t"+dto.getVisit_date()+"\t"+dto.getWriter()+"\t\t"+dto.getStars()+"\t"+dto.getWrite_date());
            }          
           
        }else{
            System.out.println("저장된 데이터가 없습니다. ");
        }
        System.out.println("====================================================================총 "+((list==null)?"0":list.size())+" 개=\n");
    
    	
      
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
