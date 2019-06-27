package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entity.Answer;
import util.DBUtil;

public class AnswerDAO {
	public int getTotal(){
		int total = 0;
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();){
			String sql = "select count(*) from answer";
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()){
				total = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return total;
	}
	public void delete(int answerId){
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();){
			String sql = "delete from answer where answerid = "+answerId;
			s.execute(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void update(Answer bean){
		String sql = "update from answer set answerContent = ?, fk_answer_user = ? where answerId = ?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, bean.getAnswerContent());
			ps.setInt(2, bean.getFk_answer_user());
			ps.setInt(3, bean.getAnswerId());
			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public Answer add(Answer bean){
		String sql = "insert into answer values(null,?,?)";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
			ps.setInt(1, bean.getFk_answer_user());
			ps.setString(2, bean.getAnswerContent());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int answerId = rs.getInt(1);
				bean.setAnswerId(answerId);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return bean;
	}
	public Answer get(int answerId){
		Answer bean = new Answer();
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();){
			String sql = "select *from answer where answerId = "+answerId;
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()){
				String answerContent = rs.getString("answerContent");
				int fk_answer_user = rs.getInt("fk_answer_user");
				bean.setAnswerContent(answerContent);
				bean.setFk_answer_user(fk_answer_user);
				bean.setAnswerId(answerId);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return bean;
	}
	public Answer get(String answerContent){
		Answer bean = new Answer();
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();){
			String sql = "select *from answer where answerContent = "+answerContent;
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()){
				int answerId = rs.getInt("answerId");
				int fk_answer_user = rs.getInt("fk_answer_user");
				bean.setAnswerContent(answerContent);
				bean.setAnswerId(answerId);
				bean.setFk_answer_user(fk_answer_user);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return bean;
	}
	public List<Answer> list(int start, int count){
		List<Answer> beans = new ArrayList<>();
		String sql = "select *from answer order by answerid desc limit ?,?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Answer bean = new Answer();
				int answerId = rs.getInt("answerId");
				bean.setAnswerId(answerId);
				String answerContent = rs.getString("answerContent");
				bean.setAnswerContent(answerContent);
				int fk_answer_user = rs.getInt("fk_answer_user");
				bean.setFk_answer_user(fk_answer_user);
				beans.add(bean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return beans;
	}
	public List<Answer> list(){
		return list(0,Short.MAX_VALUE);
	}
}
