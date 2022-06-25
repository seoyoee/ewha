import java.util.List;

public class DB2022_Team08_PriceViewProc {

	DB2022_Team08_PriceViewDAO dao;

    //기본생성자
    public DB2022_Team08_PriceViewProc() {
        dao = new DB2022_Team08_PriceViewDAO();


    }
    
    /**
     * 저장된 상품 목록 보기(상품명, 상품 사이즈, 판매가격만 공개)
     */
    public void showProductList(){

        List<DB2022_Team08_PriceViewDTO> list = dao.getProductList();

        System.out.println("                             Product List");
        System.out.println("============================================================================");
        if(list!=null&&list.size()>0){
            System.out.println("상품명\t\t\t  상품사이즈\t\t  상품가격\t\t");
            System.out.println("============================================================================");

            for (DB2022_Team08_PriceViewDTO dto : list){
            	String pd_name = dto.getName();
            	if(pd_name.length()>=6)
                System.out.println(pd_name+"\t\t"+dto.getSize()+"\t\t\t"+dto.getPrice());
            	else
            		System.out.println(pd_name+"\t\t\t"+dto.getSize()+"\t\t\t"+dto.getPrice());
            }

        }else{
            System.out.println("저장된 데이터가 없습니다. ");
        }
        System.out.println("============================================================================");
    }

	
}
