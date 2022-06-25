import java.util.Formatter;

/* 한 개의 리뷰 정보를 관리하기 위한 클래스*/
public class DB2022_Team08_ReviewViewDTO {

	
	private Integer stars; //평점
	private String write_date; //작성일자
	
	public DB2022_Team08_ReviewViewDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public DB2022_Team08_ReviewViewDTO(Integer stars, String write_date) {
		super();
		 
		this.stars = stars;
		this.write_date = write_date;
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
		sb.append("====리뷰 정보====\n");
		
		sb.append("평    점 : " + stars + "\t");
		sb.append("작성 일자 : " + write_date + "\n");
			
		return sb.toString();
	}

}
