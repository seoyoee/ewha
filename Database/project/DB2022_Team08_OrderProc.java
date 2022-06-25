import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

/* 사용자로부터 입력받은 값을 바탕으로 처리하는 클래스 */
public class DB2022_Team08_OrderProc {

    DB2022_Team08_OrderDAO dao;

    //기본생성자
    public DB2022_Team08_OrderProc() {
        dao = new DB2022_Team08_OrderDAO();

    }

    /**
     * 발주 등록처리
     */
    public void insertOrder(){

        Scanner scn = new Scanner(System.in);

        /*
         * YYYY-MM-DD hh:mm:ss*/
        System.out.println("발주정보를 입력해주세요.");
        System.out.print("▶발주상품번호 : ");
        //String orderNum = scn.nextLine();
        String orderNum = reInput(scn);
        System.out.print("▶발주상품가격 : ");
        //int orderPrice = Integer.parseInt(scn.nextLine());
        int orderPrice = Integer.parseInt(reInput(scn));
        System.out.print("▶발주날짜(YYYY-MM-DD형식으로 입력) : ");
        //Date orderDate = Date.valueOf(scn.nextLine());
        Date orderDate = Date.valueOf(reInput(scn));
        System.out.print("▶발주시간(hh:mm:ss형식으로 입력) : ");
        //Time orderTime = Time.valueOf(scn.nextLine());
        Time orderTime = Time.valueOf(reInput(scn));
        System.out.print("▶발주수량 : ");
        //int orderTotal = Integer.parseInt(scn.nextLine());
        int orderTotal = Integer.parseInt(reInput(scn));
        System.out.print("▶담당자이름 : ");
        //String contactName = scn.nextLine();
        String contactName = reInput(scn);
        System.out.print("▶제조업체 전화번호 : ");
        //String companyPhone = scn.nextLine();
        String companyPhone = reInput(scn);

        DB2022_Team08_OrderDTO dto = new DB2022_Team08_OrderDTO(orderNum,orderPrice,orderDate,orderTime,orderTotal,contactName,companyPhone);
        
        
        
        boolean r = dao.insertOrder(dto); //입력받은 데이터 추가

        if(r){
            System.out.println("상품등록이 정상적으로 완료되었습니다.");
        }else{
            System.out.println("상품등록이 정상적으로 이루지지 않았습니다.");
        }

    }


    /**
     * 저장된 발주 목록 보기
     */
    public void showOrderList(){

        List<DB2022_Team08_OrderDTO> list = dao.getOrderList();

        System.out.println("                                     Order List");
        System.out.println("====================================================================================================");
        if(list!=null&&list.size()>0){
            System.out.println("발주상품번호\t발주가격\t발주날짜\t\t발주시간\t\t발주수량\t제조업체\t\t담당자이름\t제조업체전화번호");
            System.out.println("====================================================================================================");

            for (DB2022_Team08_OrderDTO dto : list){
            	String company = dto.getCompany_name();
            	if (company.length()>5)
            		System.out.println(dto.getOrder_num()+"\t\t"+dto.getOrder_price()+"\t"+dto.getOrder_date()+"\t"+dto.getOrder_time()+"\t"+dto.getOrder_total()+"\t"+company+" "+dto.getContact_name()+"\t"+dto.getCompany_phone());
            	else
            		System.out.println(dto.getOrder_num()+"\t\t"+dto.getOrder_price()+"\t"+dto.getOrder_date()+"\t"+dto.getOrder_time()+"\t"+dto.getOrder_total()+"\t"+company+"\t\t"+dto.getContact_name()+"\t"+dto.getCompany_phone());
            }

        }else{
            System.out.println("저장된 데이터가 없습니다. ");
        }
        System.out.println("==============================================================================================총 "+((list==null)?"0":list.size())+" 개=\n");
    }


    /**
     * 발주 수정.
     */
    public void updateOrder(){

        Scanner scn = new Scanner(System.in);
        System.out.println("수정할 발주의 발주날짜를 입력해주세요(YYYY-MM-DD형식으로 입력)");
        System.out.print("▶");
        Date date = Date.valueOf(scn.nextLine());
        System.out.println("수정할 발주의 발주시간을 입력해주세요(hh:mm:ss형식으로 입력)");
        System.out.print("▶");
        Time time = Time.valueOf(scn.nextLine());
        DB2022_Team08_OrderDTO dto = dao.getOrder(date, time);
        if (dto!=null) {

            System.out.println(dto.getInfo());


            System.out.println("수정작업을 계속하시겠습니까?(Y/N)");
            String input = scn.nextLine();
            if(input.equalsIgnoreCase("y")){
                System.out.println("-1을 입력하시면 기존의 정보가 그대로 유지됩니다.");

                System.out.print("▶수정할 발주상품번호 : ");
                String num = scn.nextLine();
                if(num.trim().equals("-1")) num=dto.getOrder_num();

                System.out.print("▶수정할 발주가격 : ");
                Integer price = scn.nextInt();
                if(price==-1) price=dto.getOrder_price();
                
                /*System.out.print("▶수정할 발주날짜 : ");
                Date orderDate = Date.valueOf(scn.next());
                if(date.equals(0000-00-00)) date=dto.getOrder_date();
                date=dto.getOrder_date();

                System.out.print("▶수정할 발주시간 : ");
                Time orderTime = Time.valueOf(scn.next());
                if(time.equals("00:00:00")) time=dto.getOrder_time();*/


                System.out.print("▶수정할 발주수량 : ");
                Integer total = scn.nextInt();
                if(total==-1) total=dto.getOrder_total();

                System.out.print("▶수정할 담당자이름 : ");
                String contactName = scn.nextLine();
                if(contactName.trim().equals("-1")) contactName=dto.getContact_name();

                System.out.print("▶수정할 제조업체이름 : ");
                String companyName = scn.nextLine();
                if(companyName.trim().equals("-1")) companyName=dto.getCompany_phone();

                dto =  new DB2022_Team08_OrderDTO(num,price,date,time,total,contactName,companyName);

                boolean r = dao.updateOrder(dto);

                if(r){

                    System.out.println("발주의 정보가 다음과 같이 수정되었습니다.");
                    System.out.println(dto.getInfo());

                }else{
                    System.out.println("발주의 정보가 정상적으로 수정 되지 않았습니다.");
                }

            }else{
                System.out.println("수정 작업을 취소하였습니다.");
            }

        }else{

            System.out.println("입력하신 발주번호에 해당하는 발주가 존재하지 않습니다.");

        }
    }

    /**
     * 발주 삭제
     */
    public void deleteOrder(){

        Scanner scn = new Scanner(System.in);
        System.out.println("삭제할 발주의 발주날짜를 입력해주세요(YYYY-MM-DD형식으로 입력)");
        Date date = Date.valueOf(scn.nextLine());
        System.out.println("삭제할 발주의 발주시간을 입력해주세요(hh:mm:ss형식으로 입력)");
        Time time= Time.valueOf(scn.nextLine());
        DB2022_Team08_OrderDTO dto = dao.getOrder(date, time);
        if (dto!=null) {
            System.out.println(dto.getInfo());

            System.out.println("위 발주의 정보를 정말로 삭제하시겠습니까?(Y/N)");
            String input = scn.nextLine();
            if(input.equalsIgnoreCase("y")){
                boolean r = dao.deleteOrder(date, time);

                if(r){
                    System.out.println(date+" "+time+"의 발주 정보가 정상적으로 삭제되었습니다.");
                }else{
                    System.out.println("발주의 정보가 정상적으로 삭제 되지 않았습니다.");
                }
            }else{
                System.out.println("삭제 작업을 취소하였습니다.");
            }
        }else{

            System.out.println("입력하신 발주번호에 해당하는 발주가 존재하지 않습니다.");

        }
    }


    /**
     * 공백입력시 재입력, 테스트 메소드...
     */
    public String reInput(Scanner scn){

        String str="";
        while(true){
            str = scn.nextLine();
            if(!(str==null || str.trim().equals(""))){
                break;
            }else{
                System.out.println("공백은 입력하실수없습니다. 올바른값을 입력해주세요!");
                System.out.print("▶");
            }
        }

        return str;
    }

}