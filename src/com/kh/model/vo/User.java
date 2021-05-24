package com.kh.model.vo;

public class User {		
	private int diaryCount;
	
	
	private String uNo;		// 유저번호가 0이면 관리자
	private String uId;
	private String uPwd;
	private String uPwdAnswer;
	private String uDate;
	
	public String getuDate() {
		return uDate;
	}

	public void setuDate(String uDate) {
		this.uDate = uDate;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	// 회원가입
	public User(String uId, String uPwd, String uPwdAnswer, String uDate) {
	      super();
	      this.diaryCount = 0;
	      this.uId = uId;
	      this.uPwd = uPwd;
	      this.uPwdAnswer = uPwdAnswer;
	   }
	// 로그인 
	 public User(String uNo, String uId, String uPwd, String uPwdAnswer, String uDate, int diaryCount) {
	      super();
	      this.uNo=uNo;
	      this.uId = uId;
	      this.uPwd = uPwd;
	      this.uPwdAnswer = uPwdAnswer;
	      this.diaryCount=diaryCount;
	   }

	public int getDiaryCount() {
		return diaryCount;
	}

	public void setDiaryCount(int diaryCount) {
		this.diaryCount = diaryCount;
	}

	public String getuNo() {
		return uNo;
	}

	public void setuNo(String uNo) {
		this.uNo = uNo;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getuPwd() {
		return uPwd;
	}

	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}

	public String getuPwdAnswer() {
		return uPwdAnswer;
	}

	public void setuPwdAnswer(String uPwdAnswer) {
		this.uPwdAnswer = uPwdAnswer;
	}

	@Override
	public String toString() {
		return "User [diaryCount=" + diaryCount + ", uNo=" + uNo + ", uId=" + uId + ", uPwd=" + uPwd + ", uPwdAnswer="
				+ uPwdAnswer + "]";
	}
	
	
	

}
