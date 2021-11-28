package application.gallery;

import java.util.ArrayList;
public class UserInfo {

	private int userNum;
	private String id;
	private String passwd;
	private String email;
	ArrayList<Integer> subscription=new ArrayList<Integer>();
	private ArrayList<ReviewInfo> review; //����ڴ� ������ǰ 1���� �ϳ��� ���並 ����� ����.
	UserInfo(int userNum,String id, String passwd, String email) {
		this.userNum=userNum;
		this.id = id;
		this.passwd = passwd;
		this.email = email;
	}
	int getUserNum(){
		return userNum;
	}

	String getId() {
		return id;
	}

	String getEmail(){
		return email;
	}
}
