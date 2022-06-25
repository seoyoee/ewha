import java.util.Formatter;

/* 한 명의 회원 정보를 관리하기 위한 클래스*/

public class DB2022_Team08_MemberDTO { 
	private String phone_num; //primary key (전화번호)
	private String name; //고객이름
	private Integer purchase; //구매금액
	private String grade; //회원등급
	
	public DB2022_Team08_MemberDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public DB2022_Team08_MemberDTO(String phone_num, String name, Integer purchase, String grade) {
		super();
		this.phone_num = phone_num;
		this.name = name;
		this.purchase = purchase;
		this.grade = grade;
	}
	
	/*전화번호*/
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	
	/*이름*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/*구매금액*/
	public Integer getPurchase() {
		return purchase;
	}
	public void setPurchase(Integer purchase) {
		this.purchase = purchase;
	}
	
	/*회원등급*/
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
	public String toString() {
		try (Formatter fm = new Formatter()) {
			return fm.toString();
		}
	}
	
	public String getInfo() { //회원 정보 불러오기
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n");
		sb.append("[ " + phone_num + " ] 회원님의 정보====\n");
		sb.append("이   름 : " + name + "\n");
		sb.append("구매 금액 :" + purchase + "\n");
		sb.append("회원 등급 : " + grade + "\n");
		return sb.toString();
	}
}