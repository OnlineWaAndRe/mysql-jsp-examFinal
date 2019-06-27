package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import entity.User;
import util.DBUtil;

public class UserDAO {
	public int getTotal(){
		int total = 0;
		try(Connection c = DBUtil.getConnection(); Statement s =  c.createStatement();){
			String sql = "select count(*) from user";
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()){
				total = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return total;
	}
	public User get(String userName){
		String sql = "select *from user where userName = ?";
		User bean = new User();
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String userPassword = rs.getString("userPassword");
				int userId = rs.getInt("userId");
				bean.setUserPassword(userPassword);
				bean.setUserName(userName);
				bean.setUserId(userId);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return bean;
	}
	public void update(User bean){
		String sql = "update user set userName = ?, userPassword = ? where userId = ?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = (PreparedStatement) c.prepareStatement(sql);){
			ps.setString(1, bean.getUserName());
			ps.setString(2, bean.getUserPassword());
			ps.setInt(3, bean.getUserId());
			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void delete(int userId){
		try(Connection c = DBUtil.getConnection(); Statement s = (Statement) c.createStatement();){
			String sql = "delete from user where userId = "+userId;
			s.execute(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void add(User bean){
		String sql = "insert into user values(null,?,?)";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
			ps.setString(1, bean.getUserName());
			ps.setString(2, bean.getUserPassword());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int userId = rs.getInt(1);
				bean.setUserId(userId);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public User get(int userId){
		User bean = new User();
		try(Connection c = DBUtil.getConnection(); Statement s = (Statement)c.createStatement();){
			String sql = "select *from user where userid = "+userId;
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				String userName = rs.getString("userName");
				String userPassword = rs.getString("userPassword");
				bean.setUserName(userName);
				bean.setUserPassword(userPassword);
				bean.setUserId(userId);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return bean;
	}
	public boolean isExist(String userName) {
		User bean = get(userName);
		return bean!=null;
	}
	public List<User> list(int start, int count){
		List<User> beans = new ArrayList<>();
		String sql = "select *from user order by userid desc limit ? ,?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = (PreparedStatement)c.prepareStatement(sql);){
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				User bean = new User();
				int userId = rs.getInt("userId");
				bean.setUserId(userId);
				String userName = rs.getString("userName");
				bean.setUserName(userName);
				String userPassword = rs.getString("userPassword");
				bean.setUserPassword(userPassword);
				beans.add(bean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return beans;
	}
	public List<User> list(){
		return list(0,Short.MAX_VALUE);
	}
	public static void main(String[] args) {
	}
}
