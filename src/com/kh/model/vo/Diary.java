package com.kh.model.vo;

import java.util.ArrayList;
import java.util.Date;

public class Diary {
	private Date dDate;
	private String dImgName;
	private ArrayList<String> dhashTag;
	
	public Diary() {
		// TODO Auto-generated constructor stub
	}

	public Diary(Date dDate, String dImgName, ArrayList<String> dhashTag) {
		super();
		this.dDate = dDate;
		this.dImgName = dImgName;
		this.dhashTag = dhashTag;
	}

	public Date getdDate() {
		return dDate;
	}

	public void setdDate(Date dDate) {
		this.dDate = dDate;
	}

	public String getdImgName() {
		return dImgName;
	}

	public void setdImgName(String dImgName) {
		this.dImgName = dImgName;
	}

	public ArrayList<String> getDhashTag() {
		return dhashTag;
	}

	public void setDhashTag(ArrayList<String> dhashTag) {
		this.dhashTag = dhashTag;
	}

	@Override
	public String toString() {
		return "Diary [dDate=" + dDate + ", dImgName=" + dImgName + ", dhashTag=" + dhashTag + "]";
	}
	
	
	

}