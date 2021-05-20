package com.kh.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kh.view.CalendarView;
import com.kh.view.DiaryWriteView;

public class CalendarController {
	private CalendarView cv;
	public CalendarController() {}
	
	public CalendarController(String uId) {
		cv = new CalendarView(uId);
	}
	
	public String dateInCalendar(Date date) {
		String day = new SimpleDateFormat("yy.MM.dd").format(date);
		return day;
	}
	
	public void startCalendar(String uId) {
		new CalendarView(uId);
	}
	
	public boolean exsitDiary(String date,String uId) {
		String path=uId+"\\"+date+".dat";
		File diary= new File(path);
		if(diary.exists()) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
