package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
	public static String url = "jdbc:mysql://localhost:3306/exam?useUnicode=true&characterEncoding=UTF-8&userSSL=true&serverTimezone=UTC";
	public static String driver = "com.mysql.cj.jdbc.Driver";
	public static String userName = "root";
	public static String userPassword = "2018";
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(url,userName,userPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println("===");
	}
}
