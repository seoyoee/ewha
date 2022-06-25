import java.util.List;
import java.util.Scanner;
/* 사용자로부터 입력받은 값을 바탕으로 처리하는 클래스 */
public class DB2022_Team08_ProductProc {

    DB2022_Team08_ProductDAO dao;

    //기본생성자
    public DB2022_Team08_ProductProc() {
        dao = new DB2022_Team08_ProductDAO();


    }

    /**
     * 상품 등록처리
     */
    public void insertProduct(){

        Scanner scn = new Scanner(System.in);

        System.out.println("상품정보를 입력해주세요.");
        System.out.print("▶상품카테고리 : ");
        //String category = scn.nextLine();
        String category = reInput(scn);
        System.out.print("▶상품명 : ");
        //String name = scn.nextLine();
        String name = reInput(scn);
        System.out.print("▶상품사이즈 : ");
        //String size = scn.nextLine();
        String size = reInput(scn);
        System.out.print("▶상품번호 : ");
        //String product_num = scn.nextLine();
        String product_num = reInput(scn);
        System.out.print("▶상품가격 : ");
        //Integer price = scn.nextInt();
        int price = Integer.parseInt(reInput(scn));
        System.out.print("▶제조업체 : ");
        //String company= scn.nextLine();
        String company = reInput(scn);
        System.out.print("▶재고량 : ");
        //Integer stock = scn.nextInt();
        int stock = Integer.parseInt(reInput(scn));

        DB2022_Team08_ProductDTO dto = new DB2022_Team08_ProductDTO(product_num,category,name,size,price,company,stock);
        boolean r = dao.insertProduct(dto); //입력받은 데이터 추가

        if(r){
            System.out.println("상품등록이 정상적으로 완료되었습니다.");
        }else{
            System.out.println("상품등록이 정상적으로 이루지지 않았습니다.");
        }

    }


    /**
     * 저장된 상품 목록 보기
     */
    public void showProductList(){

        List<DB2022_Team08_ProductDTO> list = dao.getProductList();

        System.out.println("                             Product List");
        System.out.println("============================================================================");
        if(list!=null&&list.size()>0){
            System.out.println("상품번호\t카테고리 \t  상품명\t\t상품사이즈\t상품가격\t  제조업체\t\t       재고량");

            //System.out.println("상품번호\t\t   카테고리 \t\t  상품명\t\t  상품사이즈\t\t  상품가격\t\t  제조업체\t\t  재고량");
            System.out.println("============================================================================");

            for (DB2022_Team08_ProductDTO dto : list){
            	String pd_name = dto.getName(); String company_name = dto.getCompany();
            	if(pd_name.length()>=6)
            		if (company_name.length()>5)
            			System.out.println(dto.getProduct_num()+"\t"+dto.getCategory()+"\t"+pd_name+"\t"+dto.getSize()+"\t"+dto.getPrice()
                    +"\t"+company_name+"\t"+dto.getStock());
            		else
            			System.out.println(dto.getProduct_num()+"\t"+dto.getCategory()+"\t"+pd_name+"\t"+dto.getSize()+"\t"+dto.getPrice()
                        +"\t"+company_name+"\t\t\t"+dto.getStock());
                else
                	if (company_name.length()>5)
                		System.out.println(dto.getProduct_num()+"\t"+dto.getCategory()+"\t"+pd_name+"\t\t"+dto.getSize()+"\t"+dto.getPrice()
                        +"\t"+company_name+"\t"+dto.getStock());
                	else
                	System.out.println(dto.getProduct_num()+"\t"+dto.getCategory()+"\t"+pd_name+"\t\t"+dto.getSize()+"\t"+dto.getPrice()
                        +"\t"+company_name+"\t\t\t"+dto.getStock());
            	
            	
                //System.out.println(dto.getProduct_num()+"\t\t"+dto.getCategory()+"\t"+dto.getName()+"\t\t"+dto.getSize()+"\t"+dto.getPrice()
                //+"\t\t"+dto.getCompany()+"\t"+dto.getStock());
            }

        }else{
            System.out.println("저장된 데이터가 없습니다. ");
        }
        System.out.println("====================================================================총 "+((list==null)?"0":list.size())+" 개=\n");
    }


    /**
     * 상품 수정.
     */
    public void updateProduct(){

        Scanner scn = new Scanner(System.in);
        System.out.println("수정할 상품의 상품번호를 입력해주세요");
        System.out.print("▶");
        String no = scn.nextLine();
        DB2022_Team08_ProductDTO dto = dao.getProduct(no);
        if (dto!=null) {

            System.out.println(dto.getInfo());


            System.out.println("수정작업을 계속하시겠습니까?(Y/N)");
            String input = scn.nextLine();
            if(input.equalsIgnoreCase("y")){
                System.out.println("-1을 입력하면 기존의 정보가 그대로 유지됩니다.");
                System.out.print("▶수정할 카테고리 : ");
                String category = scn.nextLine();
                if(category.trim().equals("-1")) category=dto.getCategory();

                System.out.print("▶수정할 상품명 : ");
                String name = scn.nextLine();
                if(name.trim().equals("-1")) name=dto.getName();

                System.out.print("▶수정할 사이즈 : ");
                String size = scn.nextLine();
                if(size.trim().equals("-1")) size=dto.getSize();

                System.out.print("▶수정할 상품가격 : ");
                Integer price = scn.nextInt();
                if(price==-1) price=dto.getPrice();

                System.out.print("▶수정할 제조업체 : ");
                String company = scn.nextLine();
                if(company.trim().equals("-1")) company=dto.getCompany();

                System.out.print("▶수정할 재고량 : ");
                Integer stock = scn.nextInt();
                if(stock==-1) price=dto.getStock();

                dto =  new DB2022_Team08_ProductDTO(no,category,name,size,price,company,stock);
                boolean r = dao.updateProduct(dto);


                if(r){

                    System.out.println("상품의 정보가 다음과 같이 수정되었습니다.");
                    System.out.println(dto.getInfo());

                }else{
                    System.out.println("상품의 정보가 정상적으로 수정 되지 않았습니다.");
                }

            }else{
                System.out.println("수정 작업을 취소하였습니다.");
            }

        }else{

            System.out.println("입력하신 상품번호에 해당하는 상품이 존재하지 않습니다.");

        }
    }

    /**
     * 상품 삭제
     */
    public void deleteProduct(){

        Scanner scn = new Scanner(System.in);
        System.out.println("삭제할 상품의 상품번호를 입력해주세요");
        String no = scn.nextLine();
        DB2022_Team08_ProductDTO dto = dao.getProduct(no);
        if (dto!=null) {
            System.out.println(dto.getInfo());

            System.out.println("위 상품의 정보를 정말로 삭제하시겠습니까?(Y/N)");
            String input = scn.nextLine();
            if(input.equalsIgnoreCase("y")){
                boolean r = dao.deleteProduct(no);

                if(r){
                    System.out.println("상품"+no+"의 정보가 정상적으로 삭제되었습니다.");
                }else{
                    System.out.println("상품의 정보가 정상적으로 삭제 되지 않았습니다.");
                }
            }else{
                System.out.println("삭제 작업을 취소하였습니다.");
            }
        }else{

            System.out.println("입력하신 상품번호에 해당하는 회원이 존재하지 않습니다.");

        }
    }


    /*상품 검색*/
    public void searchProduct(){

        Scanner scn = new Scanner(System.in);
        System.out.println("검색할 상품의 상품번호를 입력해주세요"); //사용자로부터 입력받기
        String product_num = scn.nextLine();
        DB2022_Team08_ProductDTO dto = dao.getProduct(product_num);
        if (dto!=null)
            System.out.println(dto.getInfo());
        else
            System.out.println("잘못된 입력입니다");
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