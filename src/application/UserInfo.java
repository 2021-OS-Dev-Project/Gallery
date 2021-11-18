package application;
import java.util.*;

public class UserInfo {

	private int userNum;
	private String id;
	private String passwd;
	private String email;
	private String city; //자신이 사는 '시'를 필드로 가짐으로써 자신의 시에 있는 지역을 제공함.
	private int[] subscription;
	private ArrayList<ReviewInfo> review; //사용자는 전시작품 1개에 하나의 리뷰를 남길수 있음.
	
	UserInfo(int userNum,String id,String passwd,String city){
		this.userNum=userNum;
		this.id=id;
		this.passwd=passwd;
		this.city=city;
	}
	
	UserInfo(String id,String passwd,String email){
		this.id=id;
		this.passwd=passwd;
		this.email=email;
	}
	String getId() {
		return id;
	}
	String getPasswd() {
		return passwd;
	}
	String getCity() {
		return city;
	}
}
