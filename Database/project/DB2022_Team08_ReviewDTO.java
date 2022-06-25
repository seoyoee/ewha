import java.util.Formatter;

/* 한 개의 리뷰 정보를 관리하기 위한 클래스*/

public class DB2022_Team08_ReviewDTO {
	private String receipt_num; //primary key (영수증번호)
	private String visit_date; //방문일자
	private String writer; //작성자(foreign key - 회원 테이블의 전화번호)
	private Integer stars; //평점
	private String write_date; //작성일자
	
	public DB2022_Team08_ReviewDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public DB2022_Team08_ReviewDTO(String receipt_num, String visit_date, String writer, Integer stars, String write_date) {
		super();
		this.receipt_num = receipt_num;
		this.visit_date = visit_date;
		this.writer = writer; 
		this.stars = stars;
		this.write_date = write_date;
	}
	
	/*영수증번호*/
	public String getReceipt_num() {
		return receipt_num;
	}
	public void setReceipt_num(String receipt_num) {
		this.receipt_num = receipt_num;
	}
	
	/*방문일자*/
	public String getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(String visit_date) {
		this.visit_date = visit_date;
	}
	
	/*작성자*/
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	/*평점*/
	public Integer getStars() {
		return stars;
	}
	public void setStars(Integer stars) {
		this.stars = stars;
	}
	
	/*작성일자*/
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	
	
	public String toString() {
		try (Formatter fm = new Formatter()) {
			return fm.toString();
		}
	}
	
	
	public String getInfo() { //리뷰 정보 불러오기
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n");
		sb.append("[ " + receipt_num + " ] 리뷰 정보====\n");
		sb.append("방문 일자 : " + visit_date + "\n");
		sb.append("작 성 자 :" + writer + "\n");
		sb.append("평    점 : " + stars + "\n");
		sb.append("작성 일자 : " + write_date + "\n");
			
		return sb.toString();
	}

}
