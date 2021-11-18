package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class DBLoader implements Runnable{
   static Connection conn=null;
   static int count=0;
   String host="127.0.0.1",database="exhibitionmap?";
   int port=3306;
   int DBcount;
   int Initcount;
   private Stage stage2;
   private FXMLLoader fxmlLoader;
   private Parent root2;
   public DBLoader() {
	  DBcount=0;
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         conn=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database+"serverTimezone=UTC","root","hdoo517a*");
      
         fxmlLoader = new FXMLLoader(getClass().getResource("Second_Window.fxml"));
         root2 = (Parent) fxmlLoader.load();
         stage2 = new Stage();
         stage2.initStyle(StageStyle.DECORATED);
         stage2.setTitle("Second Window");
         stage2.setScene(new Scene(root2));
         Initcount=ArtNum();
      }catch(SQLException e) {
         e.printStackTrace();
      }catch(Exception e) {
         e.printStackTrace();
      }
      
   }
   
   public static void SignUpuser(String id,String email,String passwd) {
	   try {
		   Statement stmt=conn.createStatement();
		   String sql;
		   sql="insert into user(UserID,UserPasswd,email) values ('"+id+"','"+passwd+"','"+email+"');";
		   stmt.executeUpdate(sql);
		   //영향받은 데이터가 몇개인지 결과값으로 나옴
	   }catch(SQLException e) {
		   System.out.println("[Insert 쿼리 오류]\n"+e.getStackTrace());   
	   }
   }
   
   public static boolean CheckUser(String id) {
	   try {
		   Statement stmt=conn.createStatement();
		   String sql;
		   sql="select *from user";
		   ResultSet rs=stmt.executeQuery(sql);

		   while(rs.next()) {
			   if(rs.getString("userid").equals(id))
				   return true;
		   }
	   }catch(SQLException e) {
		   System.out.println("[select 쿼리 오류]\n"+e.getStackTrace()); 
	   }
	   return false;
   }
   
   public static boolean LoginUser(String id,String passwd) {
	   try {
		   Statement stmt=conn.createStatement();
		   String sql;
		   sql="select *from user";
		   ResultSet rs=stmt.executeQuery(sql);

		   while(rs.next()) {
			   if(rs.getString("userid").equals(id))
				   if(rs.getString("userpasswd").equals(passwd)) {
					   Object.user=new UserInfo(id,passwd,rs.getString("email"));
					   return true;
				   }
		   }
	   }catch(SQLException e) {
		   System.out.println("[select 쿼리 오류]\n"+e.getStackTrace()); 
	   }
	   return false;
   }
   
   public int ArtNum() {
	   try {
		   Statement stmt=conn.createStatement();
		   String sql;
		   sql="select count(*) from Art";
		   ResultSet rs=stmt.executeQuery(sql);
		   while(rs.next()) {
			   DBcount=rs.getInt(1);   
		   }
		   
		   
	   }catch(SQLException e) {
		   System.out.println("[Select 쿼리 오류]\n"+e.getStackTrace());   
	   }
	   
	   return DBcount;
   }
   
   @Override
   public void run() {
	   LoadLocation();
	   LoadExhibition();
	   LoadArt();
	   while(true) {
		   DBcount=ArtNum();
		   if(DBcount!=Initcount) {
			   Object.art.clear();
			   Object.exhibition.clear();
			   Object.location.clear();
			   LoadLocation();
			   LoadExhibition();
			   LoadArt();
			   secondWindow();
			   Initcount=DBcount;
		   }
	   }
   }
   
  public void secondWindow(){
       Platform.runLater(new Runnable() {
           @Override
           public void run() {
               stage2.show();
               for(int i=0;i<Object.art.size();i++)
            	 Object.art.get(i).PrintArt(); 
           }
       });
   }
   public void LoadArt() {
	   try {
		   Statement stmt=conn.createStatement();
		   String sql;
		   sql="select *from Art";
		   ResultSet rs=stmt.executeQuery(sql);
		   while(rs.next()) {
			   String ArtNum=rs.getString("artnum");
			   String ArtName=rs.getString("artName");
			   String Artist=rs.getString("artistName");
			   String StartPeriod=rs.getString("startPeriod");
			   String EndPeriod=rs.getString("endPeriod");
			   String Time=rs.getString("time");
			   String Price=rs.getString("price");
			   String Exhibitionnum=rs.getString("exhibitionnum");
			   Object.art.add(new ArtInfo(Integer.parseInt(ArtNum), ArtName, Artist, StartPeriod, EndPeriod, Time, Price,Integer.parseInt(Exhibitionnum)));
		   //영향받은 데이터가 몇개인지 결과값으로 나옴
		   }
		   
	   }catch(SQLException e) {
		   System.out.println("[Select 쿼리 오류]\n"+e.getStackTrace());   
	   }
   }
   public void LoadExhibition() {
	   try {
		   Statement stmt=conn.createStatement();
		   String sql;
		   sql="select *from Exhibition";
		   ResultSet rs=stmt.executeQuery(sql);
		   String location;
		   int[] loc=new int[100];
		   int i=0;
		   while(rs.next()) {
			   String num=rs.getString("exhibitionnum");
			   String name=rs.getString("exhibitionname");
			   location=rs.getString("locnum");
			   loc[i++]=Integer.parseInt(location);
			   Object.exhibition.add(new ExhibitionInfo(Integer.parseInt(num),name));
			   //영향받은 데이터가 몇개인지 결과값으로 나옴
		   }
		   for(LocationInfo tmp:Object.location) {
			   for(int j=0;j<i;j++)
				   if(tmp.getLocNum()==loc[j]) {
					   Object.exhibition.get(j).setLocation(tmp);
				   }
		   }
	   }catch(SQLException e) {
		   System.out.println("[Select 쿼리 오류]\n"+e.getStackTrace());   
	   }

   }
   
   public void LoadLocation() {
	   try {
		   Statement stmt=conn.createStatement();
		   String sql;
		   sql="select *from location";
		   ResultSet rs=stmt.executeQuery(sql);
		   String location;
		   while(rs.next()) {
			   String num=rs.getString("locnum");
			   String name=rs.getString("city");
			   //영향받은 데이터가 몇개인지 결과값으로 나옴
			   Object.location.add(new LocationInfo(Integer.parseInt(num),name));
		   }
	   }catch(SQLException e) {
		   System.out.println("[Select 쿼리 오류]\n"+e.getStackTrace());   
	   }
   }
}
