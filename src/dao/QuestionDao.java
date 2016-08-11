package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

import model.Contestant;
import model.Question;
import util.Contest;
import util.DBUtil;

public class QuestionDao {
	
	private Contest contest;
	
	public QuestionDao(Contest contest) {
		this.contest = contest;
	}
	
	public void add(Vector<Question> questions, int c_id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "INSERT question VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);		
		
		for (Question question : questions) {
			ptmt.setInt(1, c_id);
			ptmt.setInt(2, question.getId());
			ptmt.setInt(3, question.getAccept() ? 1 : 0);
			ptmt.setLong(4, question.getAcceptTime().getTime());
			ptmt.setInt(5, question.getWrongTimes());
			ptmt.setInt(6, question.getSubmitTimes());
			ptmt.execute();
		}
	}
	
	public Vector<Question> query(Contestant c) throws SQLException {
		Vector<Question> lists = new Vector<>();
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT * FROM question WHERE contestant_id=" + c.getId() +
						" ORDER BY question_id";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		Question q = null;
		while (rs.next()) {
			q = new Question(contest);
			q.setAccept(rs.getInt("is_accept")==1 ? true : false);
			
			q.setAcceptTime(new Date(rs.getLong("accept_time") - Contest.DF));
			q.setId(rs.getInt("question_id"));
			q.setWrongTimes(rs.getInt("wrong_times"));
			q.setSubmitTimes(rs.getInt("submit_times"));
			lists.add(q);
		}
		
		return lists;
	}
	
	public void update(Question question, int c_id) throws SQLException {
		
		
		Connection conn = DBUtil.getConnection();
		String sql = " UPDATE question SET "
					+ " is_accept=?, accept_time=?, wrong_times=?, submit_times=? "
					+ " WHERE contestant_id=? AND question_id=? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, question.getAccept() ? 1 : 0);
		
		
		ptmt.setLong(2, question.getAcceptTime().getTime());
		ptmt.setInt(3, question.getWrongTimes());
		ptmt.setInt(4, question.getSubmitTimes());
		
		ptmt.setInt(5, c_id);
		ptmt.setInt(6, question.getId());
		ptmt.execute();
		
	}
	
	public void del(int c_id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "DELETE FROM question WHERE contestant_id = " + c_id;
		
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
	}
	
	
}
