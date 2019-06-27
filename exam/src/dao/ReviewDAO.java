package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Review;
import util.DBUtil;

public class ReviewDAO {
	public int getTotal() {
		int total = 0;
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();){
			String sql = "select count(*) from review";
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) {
				total = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	public Review get(int reviewId) {
		Review bean = new Review();
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();){
			String sql = "select *from reivew where reviewId = "+reviewId;
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) {
				int fk_review_user = rs.getInt("fk_review_user");
				String reviewContent = rs.getString("reviewContent");
				bean.setFk_review_user(fk_review_user);
				bean.setReviewContent(reviewContent);
				bean.setReviewId(reviewId);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	public Review getByFk(int fk_review_user) {
		Review bean = new Review();
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();){
			String sql = "select *from review where fk_review_user = "+fk_review_user;
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) {
				int reviewId = rs.getInt("reviewId");
				String reviewContent = rs.getString("reviewContent");
				int selfId = rs.getInt("selfId");
				bean.setFk_review_user(fk_review_user);
				bean.setReviewContent(reviewContent);
				bean.setReviewId(reviewId);
				bean.setSelfId(selfId);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	public Review getBySelf(int selfId) {
		Review bean = new Review();
		String sql = "select *from review where fk_review_user = ? order by ReviewId";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int reviewId = rs.getInt("reviewId");
				String reviewContent = rs.getString("reviewContent");
				int fk_review_user = rs.getInt("fk_review_user");
				bean.setFk_review_user(fk_review_user);
				bean.setReviewContent(reviewContent);
				bean.setReviewId(reviewId);
				bean.setSelfId(selfId);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	public Review getFk(int selfId) {
		Review bean = new Review();
		String sql = "select *from review where selfId = ? and fk_review_user = ? ";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, selfId);
			ps.setInt(2, selfId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String reviewContent = rs.getString("reviewContent");
				bean.setFk_review_user(selfId);
				bean.setReviewContent(reviewContent);
				bean.setReviewId(selfId);
				bean.setSelfId(selfId);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	public Review get_review(int selfId, int fk_review_user) {
		Review bean = new Review();
		String sql = "select *from review where selfId = ? and fk_review_user = ? ";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, selfId);
			ps.setInt(2, fk_review_user);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String reviewContent = rs.getString("reviewContent");
				bean.setFk_review_user(fk_review_user);
				bean.setReviewContent(reviewContent);
				bean.setReviewId(selfId);
				bean.setSelfId(selfId);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	public List<Review> list(int selfId, int start, int count){
		List<Review> beans = new ArrayList<>();
		String sql = "select *from review where selfId = ? order by reviewId desc limit ?,?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, selfId);
			ps.setInt(2, start);
			ps.setInt(3, count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Review bean = new Review();
				int fk_review_user = rs.getInt("fk_review_user");
				String reviewContent = rs.getString("reviewContent");
				int reviewId = rs.getInt("reviewId");
				bean.setFk_review_user(fk_review_user);
				bean.setReviewId(reviewId);
				bean.setReviewContent(reviewContent);
				bean.setSelfId(selfId);
				beans.add(bean);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return beans;
	}
	public List<Review>list(int selfId) {
		return list(selfId, 1,8);
	}
	public Review add(Review bean) {
		String sql = "insert into review values(null,?,?,?)";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);){
			ps.setInt(1, bean.getSelfId());
			ps.setInt(2, bean.getFk_review_user());
			ps.setString(3, bean.getReviewContent());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				int reviewId = rs.getInt("reviewId");
				bean.setReviewId(reviewId);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	public List<Review> listByFk(int fk_review_user, int start, int count){
		List<Review> beans = new ArrayList<>();
		String sql = "select *from review where fk_review_user = ? order by reviewId desc limit ?,?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, fk_review_user);
			ps.setInt(2, start);
			ps.setInt(3, count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Review bean = new Review();
				int selfId = rs.getInt("selfId");
				String reviewContent = rs.getString("reviewContent");
				int reviewId = rs.getInt("reviewId");
				bean.setFk_review_user(fk_review_user);
				bean.setReviewId(reviewId);
				bean.setReviewContent(reviewContent);
				bean.setSelfId(selfId);
				beans.add(bean);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return beans;
	}
	public List<Review> listByFk(int kf_review_user){
		return listByFk(kf_review_user,1,8);
	}
	public static void main(String[] args) {
		new ReviewDAO().listByFk(5);
	}
}
