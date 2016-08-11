package util;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import dao.QuestionDao;
import model.Contestant;
import model.Question;

public class ContestantManager {
	public static List<Contestant> contestants = null;
	public static Contest contest;
	
	
	public ContestantManager() {}
	
	public static void setContest(Contest c) {
		contest = c;
	}
	
	public static void updateList(List<Contestant> lists) {
		contestants = lists;
		updateList();
	}
	
	public static void updateList() {
		for (Contestant c : contestants) {
			c.updatePenalty();
			c.updateSolveCount();
		}
		updateRank();
		updatePenalty();
		setFB();
	}
	
	public static int getRank(Contestant contestant) {
		
		return contestants.indexOf(contestant) + 1;
	}
	
	public static void updateRank() {
		Collections.sort(contestants);
	}
	
	public static void setQuestion(Contest contest, int c_id, char q_id, Boolean ac) throws SQLException {
		System.out.println("In QuestionDao::update  question_id:" + q_id + " " +c_id);
		for (Contestant contestant: contestants) {
			if (contestant.getId() == c_id) {
				for (Question question: contestant.getQuestions()) {
					if (question.getId() == q_id - 'A' + 1) {
						question.setAccept(ac);
						new QuestionDao(contest).update(question, c_id);
						break;
					}
				}
				break;
			}
		}
		updateList();
		setFB();
	}
	
	public static List<Contestant> getList() {
		return contestants;
	}
	
	public static void printList() {
		for (Contestant c : contestants) {
			System.out.println(c);
		}
	}
	
	public static void setFB() {
		if (contestants.isEmpty()) {
			return ;
		}
		for (int i = 1; i <= Math.min(contest.getQuestionNums(), 
					contestants.get(0).getQuestions().size()); i++) {
		
			Date date = null;
			Contestant t = null;
			for (Contestant c : contestants) {
				Question q = c.getQuestions().get(i-1);
				if (q.getAccept()) {
					if (date == null) {
						date = q.getAcceptTime();
						t = c;
					}else {
						if (q.getAcceptTime().before(date)) {
							date = q.getAcceptTime();
							t = c;
						}
					}
				}
			}
			if (date != null) {
				t.getQuestions().get(i-1).setIsFB(true);
			}
		}
	}
	
	public static void updatePenalty() {
		for (Contestant c : contestants) {
			c.updatePenalty();
		}
	}
	
 }
