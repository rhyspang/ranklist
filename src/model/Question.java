package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import util.Contest;

public class Question {
	private Contest contest;
	private int id;
	private int submitTimes;
	private int wrongTimes;
	private Boolean accept = false;
	private Boolean isFB = false;
	private char alphaId;
	private String acceptTimeStr;

	private Date acceptTime = new Date();;
	private int penalty;
	private static int idStart = 0;
	
	public Question(Contest contest) {
		this.contest = contest;
		this.id = ++idStart;
		if (idStart == contest.getQuestionNums()) {
			idStart = 0;
		}
		this.alphaId = (char)(id + 'A' - 1);
	}
	
	public Boolean getIsFB() {
		return isFB;
	}
	
	public void setIsFB(Boolean isFB) {
		this.isFB = isFB;
	}
	
	public int calPenalty() {
		if (!accept || this.wrongTimes==0) {
			return this.penalty = 0;
		}
		
		int hours = Integer.parseInt(this.acceptTimeStr.split(":")[0]);
		int minutes = Integer.parseInt(this.acceptTimeStr.split(":")[1]);
		
		return this.penalty = 60*hours + minutes + wrongTimes*20;
	}
	
	public void submit(Boolean ac) {
		if (ac) {
			this.calPenalty();
		}else {
			this.wrongTimes++;
			this.submitTimes++;
		}
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSubmitTimes() {
		return submitTimes;
	}

	public void setSubmitTimes(int submitTimes) {
		this.submitTimes = submitTimes;
	}

	public int getWrongTimes() {
		return wrongTimes;
	}

	public void setWrongTimes(int wrongTimes) {
		this.wrongTimes = wrongTimes;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}
	
	public String getAcceptTimeStr() {
		return this.acceptTimeStr;
	}

	public void setAcceptTime(Date acceptTime) {
		
		this.acceptTime = new Date(
				acceptTime.getTime() 
				- contest.getStartTime().getTime()
				-Contest.DE);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		this.acceptTimeStr = sdf.format(this.acceptTime);
	}

	public int getPenalty() {
		this.calPenalty();
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}

	public Boolean getAccept() {
		return accept;
	}

	public void setAccept(Boolean accept) {
		this.submitTimes++;
		if (this.accept == false && accept == true) {
			this.accept = accept;
			this.setAcceptTime(new Date());
		}
		if (accept == false) {
			this.wrongTimes++;	
		}
		
		if (accept && wrongTimes != 0) {
			this.calPenalty();
		}
		
	}
	public char getAlphaId() {
		return this.alphaId;
	}
		
}
