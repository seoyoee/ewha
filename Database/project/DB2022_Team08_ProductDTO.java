/* 한 개의 상품 정보를 관리하기 위한 클래스*/
public class DB2022_Team08_ProductDTO {

    private String category;
    private String name;
    private String size;
    private String product_num;
    private int price;
    private String company;
    private int stock;

    //기본생성자
    public DB2022_Team08_ProductDTO() {

    }

    //생성자
    public DB2022_Team08_ProductDTO(String product_num, String category, String name, String size,
                                    int price, String company, int stock) {
        super();
        this.category = category;
        this.name = name;
        this.size = size;
        this.product_num = product_num;
        this.price = price;
        this.company = company;
        this.stock = stock;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getProduct_num() {
        return product_num;
    }

    public void setProduct_num(String product_num) {
        this.product_num = product_num;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "product_num=" + product_num + '\'' +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price + '\'' +
                ", company='" + company + '\'' +
                ", stock=" + stock +
                '}';
    }

    public String getInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("\r\n");
        sb.append("[ " + product_num + " ] 상품 정보====\n");
        sb.append("상품명: "+name+"\n");
        sb.append("상품카테고리 : "+category+"\n");
        sb.append("상품사이즈 : "+size+"\n");
        sb.append("판매가격 : "+price+"\n");
        sb.append("제조업체 : "+company+"\n");
        sb.append("재고량 : "+stock+"\n");

        return sb.toString();
    }

}

