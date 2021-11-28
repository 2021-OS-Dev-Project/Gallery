package application.gallery;

import javafx.application.Platform;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.sql.*;


public class DBLoader implements Runnable{
   static Connection conn=null;
   static int count=0;
   String host="127.0.0.1",database="exhibitionmap?";
   int port=3306;
   static int DBcount;
   static int Initcount;
   static boolean update; //EachgalleryControll
   
   public DBLoader() {
	  DBcount=0;
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         conn=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database+"serverTimezone=UTC","root","hdoo517a*");
		 Initcount=ArtNum();
		 update=false;
      }catch(SQLException e) {
         e.printStackTrace();
      }catch(Exception e) {
         e.printStackTrace();
      }
      
   }


   public static void SubscribeUser(int userid,int exhibitionnum){
	   try {
		   Statement stmt=conn.createStatement();
		   stmt.executeUpdate("insert into subscribe values ("+userid+","+exhibitionnum+")");
	   }catch(SQLException e) {
		   System.out.println("[Insert err #0]\n"+e.getStackTrace());
	   }
   }

   public static boolean CheckSubscribe(int usernum){
	   try {
		   Statement stmt=conn.createStatement();
		   ResultSet rs=stmt.executeQuery("select count(*) from subscribe where usernum="+usernum);

		   while(rs.next()) {
			   return true;
		   }
	   }catch(SQLException e) {
		   System.out.println("[#2. select *from user, LoginUser function err ]\n"+e.getStackTrace());
	   }
	   return false;
   }
   public static void SignUpuser(String id,String email,String passwd) {
	   try {
		   Statement stmt=conn.createStatement();
		   stmt.executeUpdate("insert into user(userid,userpasswd,email) values ('\"+id+\"','\"+passwd+\"','\"+email+\"');");
	   }catch(SQLException e) {
		   System.out.println("[Insert ���� ����]\n"+e.getStackTrace());   
	   }
   }
   
   public static boolean CheckUser(String id) {
	   try {
		   Statement stmt=conn.createStatement();
		   ResultSet rs=stmt.executeQuery("select *from user");

		   while(rs.next()) {
			   if(rs.getString("userid").equals(id)) {
				   return true;
			   }
		   }
	   }catch(SQLException e) {
		   System.out.println("[#1. select *from user, CheckUser function err]\n"+e.getStackTrace());
	   }
	   return false;
   }
   
   public static boolean LoginUser(String id,String passwd) {
	   try {
		   Statement stmt=conn.createStatement();
		   ResultSet rs=stmt.executeQuery("select *from user");

		   while(rs.next()) {
			   if(rs.getString("userid").equals(id))
				   if(rs.getString("userpasswd").equals(passwd)) {
					   Object.user=new UserInfo(rs.getInt("userNum"),id,passwd,rs.getString("email"));
					   LoadSubscribe();
					   return true;
				   }
		   }
	   }catch(SQLException e) {
		   System.out.println("[#2. select *from user, LoginUser function err ]\n"+e.getStackTrace());
	   }
	   return false;
   }
   
   public int ArtNum() {
	   try {
		   Statement statement=conn.createStatement();
		   ResultSet rs = statement.executeQuery("select count(*) from Art");
		   while(rs.next()) {
			   DBcount=rs.getInt(1);   
		   }
	   }catch(SQLException e) {
		   System.out.println("[#3. Select count(*) from art, ArtNum functiona is err\n"+e.getStackTrace());
	   }
	   
	   return DBcount;
   }


   public void secondWindow(){
       Platform.runLater(new Runnable() {
           @Override
           public void run() {
			   TrayNotification tray = new TrayNotification();

			   AnimationType type = AnimationType.POPUP;
			   tray.setAnimationType(type);
			   tray.setTitle("NEW ART!!");
			   tray.setNotificationType(NotificationType.INFORMATION);
			   tray.showAndDismiss(Duration.seconds(2));

			   // 팝업 이미지 사진 바꾸고 싶으면 여기에 url 링크 넣으면 됩니다! 개인 컴퓨터에 맞게 링크 조정 필요합니당
			  // Image whatsAppImg = new Image("file=///C://Users//ASUS//IdeaProjects//Gallery//src//main//java//application//gallery//image//project_gallery.png");
			   tray.setRectangleFill(Paint.valueOf("#d195ff"));  // 색상 변경
			  // tray.setImage(whatsAppImg);
			   tray.setMessage(GalleryListController.selectExhibitionName+"에 새로운 전시회가 추가되었습니다.");
			   //stage2.show();
           }
       });
   }
   static public void LoadAdministrative(){
	   try {
		   Object.art.clear();
		   Statement stmt=conn.createStatement();
		   ResultSet rs=stmt.executeQuery("select *from administrativeD");
		   while(rs.next()) {
			   int id=rs.getInt("id");
			   String province=rs.getString("province");
			   Object.province.add(new ProvinceInfo(id,province));
		   }
	   }catch(SQLException e) {
		   System.out.println("#10. [Select *from art Provinces fuction is err]\n"+e.getStackTrace());
	   }
   }
	static public void LoadSubscribe(){
		try {
			if(Object.user!=null) {
				Object.user.subscription.clear();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select *from subscribe");
				while (rs.next()) {
					int id = rs.getInt("usernum");
					if (id == Object.user.getUserNum()) {
						int exhibitionnum = rs.getInt("exhibitionnum");
						Object.user.subscription.add(exhibitionnum);
					}
				}
			}
		}catch(SQLException e) {
			System.out.println("#11. [Select *from subscribe fuction is err]\n"+e.getStackTrace());
		}
	}
   static public void LoadArt() {
	   try {
		   Object.art.clear();
		   Statement stmt=conn.createStatement();
		   ResultSet rs=stmt.executeQuery("select *from art");
		   while(rs.next()) {
			   String ArtNum = rs.getString("artnum");
			   String ArtName = rs.getString("artName");
			   String Artist = rs.getString("artistName");
			   String StartPeriod = rs.getString("startPeriod");
			   String EndPeriod = rs.getString("endPeriod");
			   String Time = rs.getString("time");
			   String Price = rs.getString("price");
			   String SRC = rs.getString("imageSRC");
			   String Exhibitionnum = rs.getString("exhibitionnum");
			   Object.art.add(new ArtInfo(Integer.parseInt(ArtNum), ArtName, Artist, StartPeriod, EndPeriod, Time, Price, SRC, Integer.parseInt(Exhibitionnum)));
		   }
	   }catch(SQLException e) {
		   System.out.println("#4. [Select *from art LoadArt fuction is err]\n"+e.getStackTrace());
	   }
   }
   static public void LoadExhibition() {
	   try {
		   Object.exhibition.clear();

		   Statement stmt=conn.createStatement();
		   ResultSet rs=stmt.executeQuery("select *from Exhibition");
		   while(rs.next()) {
			   String num=rs.getString("exhibitionnum");
			   String name=rs.getString("exhibitionname");
			   String location=rs.getString("locnum");
			   String site=rs.getString("siteURL");
			   Object.exhibition.add(new ExhibitionInfo(Integer.parseInt(num),name,site,Integer.parseInt(location)));
			}
	   }catch(SQLException e) {
		   System.out.println("[#5. Select *from exhibition, LoadExhibition function is err]\n"+e.getStackTrace());
	   }

   }

	@Override
	public void run() {

		   LoadAdministrative();
		   LoadLocation();
		   LoadExhibition();
		   LoadArt();
		   while (true) {
			   DBcount = ArtNum();
			   if (DBcount != Initcount) {
				   System.out.println(DBcount + " - " + Initcount + "=" + (DBcount - Initcount));
				   Object.art.clear();
				   Object.exhibition.clear();
				   Object.location.clear();
				   LoadLocation();
				   LoadExhibition();
				   LoadArt();
				   System.out.println(Object.art.size());
				   secondWindow();
				   Initcount++;
				   update = false;
			   }
		   }

	}

	static public void LoadLocation() {
	   try {
		   Object.location.clear();

		   Statement stmt=conn.createStatement();
		   ResultSet rs=stmt.executeQuery("select *from location");
		   while(rs.next()) {
			   int num=rs.getInt("locnum");
			   String name=rs.getString("city");
			   int province=rs.getInt("provinceid");
			   Object.location.add(new LocationInfo(num,name,province));
		   }
	   }catch(SQLException e) {
		   System.out.println("[#8. Select *from location]\n"+e.getStackTrace());
	   }
   }
}
