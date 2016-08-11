package model;

import java.io.Serializable;
import java.util.Vector;

import util.Contest;
import util.ContestantManager;

public class Contestant implements Comparable<Object>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int rank;
	private String name;
	private String className;
	private int solveCount;
	private int penalty;
	private String gender;
	private Boolean init;
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	private Contest contest;
	private Vector<Question> questions;
	
	public Contestant(String name, String className, String gender, Contest contest) {
	
		this.name = name;
		this.className = className;
		this.gender = gender;
		this.contest = contest;
		questions = new Vector<Question>();
		for (int i = 1; i <= contest.getQuestionNums(); i++) {
			questions.addElement(new Question(contest));
		}
	}
	
	public Contestant() {
		init = true;
	}
	
	public void updatePenalty() {
		this.penalty = 0;
		for (int i = 0; i < questions.size(); i++) {
			this.penalty += questions.get(i).getPenalty();
		}
	}
	
	public void updateSolveCount() {
		this.solveCount = 0;
		for (int i = 0; i < questions.size(); i++) {
			this.solveCount += questions.get(i).getAccept() ? 1 : 0;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRank() {
		return this.rank = ContestantManager.getRank(this) ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}	

	public void setClassName(String className) {
		this.className = className;
	}

	public int getSolveCount() {
		this.updateSolveCount();
		return solveCount;
	}

	public int getPenalty() {
		this.updatePenalty();
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}

	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
		if (init) {
			questions = new Vector<Question>();
			for (int i = 1; i <= contest.getQuestionNums(); i++) {
				questions.addElement(new Question(contest));
			}			
		}
	}

	public Vector<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Vector<Question> questions) {
		this.questions = questions;
	}

	@Override
	public int compareTo(Object o) {
		if (this.solveCount == ((Contestant)o).solveCount && 
				((Contestant)o).penalty == this.penalty
				) {
			
			long t1 = 0, t2 = 0;
			for (Question question : this.questions) {
				t1 += question.getAccept() ? question.getAcceptTime().getTime() : 0;
			}
			for (Question question : ((Contestant)o).questions) {
				t1 += question.getAccept() ? question.getAcceptTime().getTime() : 0;
			}
			if (t1 == t2) {
				return this.id - ((Contestant)o).id;				
			}
			return (int)(t1 -t2);
			
		}
		
		if (this.solveCount == ((Contestant)o).solveCount) {
			return this.penalty - ((Contestant)o).penalty;
		}
		return ((Contestant)o).solveCount - this.solveCount;
	}

	@Override
	public String toString() {
		return "Contestant [id=" + id + ", rank=" + rank + ", name=" + name + ", className=" + className
				+ ", solveCount=" + solveCount + ", penalty=" + penalty + ", gender=" + gender + "]";
	}
	
	
}