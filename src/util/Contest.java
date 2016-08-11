package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Contest {
	
	public static final String FORMAT = "yyyy_MM_dd HH:mm:ss";
	public static final long DE = 8*60*60*1000;
	public static final long DF = 15*60*60*1000;
	public static List<Character> alphas;
	
	private Date startTime;
	private Date endTime;
	private String contestName;
	private Date duration;
	private int questionNums;
	
	public Contest(String contestName, Date startTime) {
		this.contestName = contestName;
		this.startTime = startTime;
		//默认比赛时长５hours
		this.endTime = new Date(this.startTime.getTime() + 5*60*60*1000);
	}
	
	public Contest(String contestName, String startTimeStr, 
			String durationStr, int questionNums) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
		this.contestName = contestName;
		try {
			this.startTime = sdf.parse(startTimeStr);
			this.duration = sdf.parse(durationStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.endTime = new Date(this.startTime.getTime() + duration.getTime());
		this.questionNums = questionNums;
		alphas = new ArrayList<>();
		for (int i = 0; i < questionNums; i++) {
			alphas.add((char) (i+'A'));
		}
	}
	
	
	public Date getStartTime() {
		return startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public Date getDuration() {
		return duration;
	}
	
	public int getQuestionNums() {
		return questionNums;
	}
	
	public String getContestName() {
		return contestName;
	}
	public List<Character> getAlphas() {
		return Contest.alphas;
	}
}
