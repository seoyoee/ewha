/* 한 개의 (제한된) 상품 정보를 관리하기 위한 클래스*/
public class DB2022_Team08_PriceViewDTO {

    
    private String name;
    private String size;
    private int price;

    //기본생성자
    public DB2022_Team08_PriceViewDTO() {

    }

    //생성자
    public DB2022_Team08_PriceViewDTO(String name, String size,
                                    int price) {
        super();
        this.name = name;
        this.size = size;
        this.price = price;

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    
    @Override
    public String toString() {
        return "PriceViewDTO{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                '}';
    }

    public String getInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("\r\n");
        sb.append("[ " + name + " ] 상품 정보====\n");
        sb.append("상품명: "+name+"\n");
        
        sb.append("상품사이즈 : "+size+"\n");
        sb.append("판매가격 : "+price+"\n");
       
   

        return sb.toString();
    }

}

