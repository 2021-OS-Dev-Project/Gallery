package application;
import java.util.*;

public class UserInfo {

	private int userNum;
	private String id;
	private String passwd;
	private String email;
	private String city; //�ڽ��� ��� '��'�� �ʵ�� �������ν� �ڽ��� �ÿ� �ִ� ������ ������.
	private int[] subscription;
	private ArrayList<ReviewInfo> review; //����ڴ� ������ǰ 1���� �ϳ��� ���並 ����� ����.
	
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
