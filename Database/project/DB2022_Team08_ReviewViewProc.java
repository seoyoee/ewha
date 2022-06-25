import java.util.List;
import java.util.Scanner;

/* 사용자로부터 입력받은 값을 바탕으로 처리하는 클래스 */

public class DB2022_Team08_ReviewViewProc {


	DB2022_Team08_ReviewViewDAO dao;
	   
    //기본생성자
    public DB2022_Team08_ReviewViewProc() {
		// TODO Auto-generated constructor stub
    	   dao = new DB2022_Team08_ReviewViewDAO();
	}
   
    
       
    /*저장된 리뷰 정보 목록 보기*/
    public void showReviewList(){
   
        List<DB2022_Team08_ReviewViewDTO> list = dao.getReviewList();
       
        System.out.println("     Review List");
        System.out.println("==========================");
        if(list!=null&&list.size()>0){         
            System.out.println("평점\t   작성일자");
            System.out.println("==========================");
           float sum=0; int stars; int count=0;
            for (DB2022_Team08_ReviewViewDTO dto : list){
            	stars = dto.getStars();
                System.out.println(stars+"\t"+dto.getWrite_date());
                sum+=stars; count++;
            }          
           System.out.println("평균 : "+ sum/count + "점");
        }else{
            System.out.println("저장된 데이터가 없습니다. ");
        }
        System.out.println("===================총 "+((list==null)?"0":list.size())+" 개=\n");
    }
   
  
}
