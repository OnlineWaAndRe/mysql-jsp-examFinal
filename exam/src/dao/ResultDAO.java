package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import entity.Result;
import util.DBUtil;

public class ResultDAO {
	public int getTotal(){
		int total = 0;
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();){
			String sql = "select count(*) from result";
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()){
				total = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return total;
	}
	public void delete(int rid){
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement()){
			String sql = "delete from result where id = "+rid;
			s.execute(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void update(Result bean){
		String sql = "update result qid = ?, aid = ? where resultid = ?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps =  c.prepareStatement(sql);){
			ps.setInt(1,bean.getQid());
			ps.setInt(2,bean.getAid());
			ps.setInt(3,bean.getResultId());
			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void add(Result bean){
		String sql = "insert into result values(null,?,?)";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps =  c.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
			ps.setInt(1, bean.getQid());
			ps.setInt(2, bean.getAid());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int resultid = rs.getInt(1);
				bean.setResultId(resultid);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public Result getByQid(int qid) {
		Result bean = new Result();
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();){
			String sql = "select *from result where qid = "+qid;
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				int resultId = rs.getInt("resultId");
				bean.setResultId(resultId);
				int aid = rs.getInt("aid");
				bean.setAid(aid);
				bean.setQid(qid);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return bean;
	}
	public Result get(int resultId){
		Result bean = new Result();
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();){
			String sql = "select *from result where resultid = "+resultId;
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				int qid = rs.getInt("qid");
				bean.setQid(qid);
				int aid = rs.getInt("aid");
				bean.setAid(aid);
				bean.setResultId(resultId);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return bean;
	}
	public List<Result> list(int questionId, int start, int count){
		String sql = "select *from result where qid = ? order by resultid desc limit ?,?";
		List<Result> beans = new ArrayList<>();
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, questionId);
			ps.setInt(2, start);
			ps.setInt(3, count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Result bean = new Result();
				int resultId = rs.getInt("resultId");
				bean.setResultId(resultId);
				int qid = rs.getInt("qid");
				bean.setQid(qid);
				int aid = rs.getInt("aid");
				bean.setAid(aid);
				beans.add(bean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return beans;
	}
	public List<Result> list(int questionId){
		return list(questionId,0,Short.MAX_VALUE);
	}
	public List<Result> list(int start, int count){
		String sql = "select *from result order by resultid desc limit ?,?";
		List<Result> beans = new ArrayList<>();
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Result bean = new Result();
				int resultId = rs.getInt("resultId");
				bean.setResultId(resultId);
				int qid = rs.getInt("qid");
				bean.setQid(qid);
				int aid = rs.getInt("aid");
				bean.setAid(aid);
				beans.add(bean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return beans;
	}
	public List<Result> list(){
		return list(0,Short.MAX_VALUE);
	}
}
