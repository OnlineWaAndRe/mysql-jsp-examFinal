package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Question;
import util.DBUtil;

public class QuestionDAO {
	public int getTotal(){
		int total = 0;
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();){
			String sql = "select count(*) from question";
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()){
				total = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return total;
	}
	public void delete(int questionId){
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();){
			String sql = "delete *from question where questionId = "+questionId;
			s.execute(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void update(Question bean){
		String sql = "update question set questionContent = ?, set fk_question_user = ? where questionId = ?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, bean.getQuestionContent());
			ps.setInt(2, bean.getFk_question_user());
			ps.setInt(3, bean.getQuestionId());
			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void add(Question bean){
		String sql = "insert into question values(null,?,?)";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
			ps.setInt(1, bean.getFk_question_user());
			ps.setString(2, bean.getQuestionContent());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int questionId = rs.getInt(1);
				bean.setQuestionId(questionId);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public Question get(int questionId) {
		Question bean = new Question();
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();){
			String sql = "select *from question where questionid="+questionId;
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()){
				String questionContent = rs.getString("questionContent");
				int fk_question_user = rs.getInt("fk_question_user");
				bean.setQuestionContent(questionContent);
				bean.setFk_question_user(fk_question_user);
				bean.setQuestionId(questionId);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return bean;
	}
	public List<Question> list(int qid, int start, int count){
		List<Question> beans = new ArrayList<>();
		String sql = "select *from question where questionId = ? order by questionid desc limit ?,?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, qid);
			ps.setInt(2, start);
			ps.setInt(3, count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Question bean = new Question();
				String questionContent = rs.getString("questionContent");
				int questionId = rs.getInt("questionId");
				int fk_question_user = rs.getInt("fk_question_user");
				bean.setQuestionContent(questionContent);
				bean.setQuestionId(questionId);
				bean.setFk_question_user(fk_question_user);
				beans.add(bean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return beans;
	}
	public List<Question> list(int qid){
		return list(qid, 0, Short.MAX_VALUE);
	}
	public List<Question> list(int start, int count){
		List<Question> beans = new ArrayList<>();
		String sql = "select *from question order by questionid desc limit ?,?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Question bean = new Question();
				String questionContent = rs.getString("questionContent");
				int questionId = rs.getInt("questionId");
				int fk_question_user = rs.getInt("fk_question_user");
				bean.setQuestionContent(questionContent);
				bean.setQuestionId(questionId);
				bean.setFk_question_user(fk_question_user);
				beans.add(bean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return beans;
	}
	public List<Question> list(){
		return list(0, Short.MAX_VALUE);
	}
}
