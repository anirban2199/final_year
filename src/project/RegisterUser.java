package project;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import oracle.jdbc.OracleDriver;
public class RegisterUser {
	private static BufferedReader br= new BufferedReader (new InputStreamReader(System.in));
	private static Connection con;
	private static final String url="jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user="anband";
	private static final String password="1234";
	static String bal,name;
	static int num,pin;
	
	public static void main(String[] args) throws IOException {
	
		System.out.println("Enter name");
		name=br.readLine();
		System.out.println("Enter card number");
		num=Integer.parseInt(br.readLine());
		System.out.println("Enter pin number");
		pin=Integer.parseInt(br.readLine());
		System.out.println("Enter balance");
		bal=br.readLine();
		
		PreparedStatement ps=null;
		String sql="insert into Bank values(?,?,?,?)";
		Driver driver=new OracleDriver();
		
		try {
			DriverManager.registerDriver(driver);
			con=DriverManager.getConnection(url, user, password);
			ps=con.prepareStatement(sql);
			ps.setInt(1, pin);
			ps.setInt(2, num);
			ps.setString(3, name);
			ps.setString(4, bal);
			int ret=ps.executeUpdate();
			if(ret>0)
				con.commit();
			System.out.println("Successfully Registered");
		} catch(SQLException s) {
			s.printStackTrace();
		}
	}
}