

import java.util.Formatter;
/* 한 개의 영수증 정보를 관리하기 위한 클래스*/
public class DB2022_Team08_ReceiptDTO {
	
	private String num;
	private String phoneNum;
	private int totalPrice;
	private String date;
	
	// 기본 생성자
	public DB2022_Team08_ReceiptDTO() {
		
	}
	
	// 생성자
	public DB2022_Team08_ReceiptDTO(String num, String phoneNum, int totalPrice, String date) {
		this.num = num;
		this.phoneNum = phoneNum;
		this.totalPrice = totalPrice;
		this.date = date;
	}
	
	// getter, setter
	public String getNum() {
		return num;
	}
	
	public void setNum(String num) {
		this.num = num;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		Formatter fm = new Formatter();
		String recInfo = fm.format("%5s\t  %-16s\t%-14d\t%-14s", num, phoneNum, totalPrice, date).toString();
		return recInfo;
	}
	
	public String getInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n");
		sb.append("[" + num + "] 영수증 정보 ====\n");
		sb.append("전화번호: " + phoneNum + "\n");
		sb.append("총 결제금액: " + totalPrice + "\n");
		sb.append("판매 일자: " + date + "\n");
		
		return sb.toString();
	}

}
