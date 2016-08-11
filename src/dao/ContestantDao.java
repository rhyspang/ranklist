package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Contestant;
import util.Contest;
import util.DBUtil;

public class ContestantDao {
	
	private Contest contest;
	
	public ContestantDao(Contest contest) {
		this.contest = contest;
	}
	
	public List<Contestant> query() throws SQLException {
		List<Contestant> contestants = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT * FROM contestant";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		Contestant contestant = null;
		while (rs.next()) {
			contestant = new Contestant();
			contestant.setContest(contest);
			contestant.setClassName(rs.getString("classname"));
			contestant.setId(rs.getInt("id"));
			contestant.setName(rs.getString("name"));
			contestant.setGender(rs.getString("gender"));
			contestant.setPenalty(rs.getInt("penalty"));
			contestant.setQuestions(new QuestionDao(contest).query(contestant));
			contestants.add(contestant);
		}
		return contestants;
	}
	
	public void add(Contestant contestant) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "INSERT contestant VALUES(NULL, ?, ?, ?, ?, ?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, contestant.getName());
		ptmt.setString(2, contestant.getClassName());
		ptmt.setString(3, contestant.getGender());
		ptmt.setInt(4, contestant.getSolveCount());
		ptmt.setInt(5, contestant.getPenalty());
		ptmt.execute();
		
		//将contestant的question信息保存, 此时保存contestant中没有id
		new QuestionDao(contest).add(contestant.getQuestions(), getContestantId(contestant));
	}
	
	public int getContestantId(Contestant contestant) throws SQLException {
		String sql = "SELECT id FROM contestant WHERE name=? AND classname=?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, contestant.getName());
		ptmt.setString(2, contestant.getClassName());
		ResultSet rs = ptmt.executeQuery();
		int c_id = -1;
		if (rs.next()) {
			c_id = rs.getInt("id");
		}
		return c_id;
	}
	
	public void add(List<Contestant> contestants) throws SQLException {
		for (Contestant contestant: contestants) {
			add(contestant);
		}
	}
	
	public void del(Integer id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "DELETE FROM contestant WHERE id=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
		new QuestionDao(contest).del(id);
	}
}
