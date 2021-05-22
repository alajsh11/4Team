package com.kh.controller;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.kh.view.CalendarView;

public class CalendarController {
	private CalendarView cv;

	public CalendarController() {
	}

	public CalendarController(String uId) {
		cv = new CalendarView(uId);
	}

	// 윤년확인 메소드
	public int isLeapYear(int year) {
		if ((year % 4 == 0) && (year % 100 != 0) || year % 400 == 0) {
			return 29;
		} else {
			return 28;
		}

	}

	public String dateInCalendar(Date date) {
		String day = new SimpleDateFormat("yy.MM.dd").format(date);
		return day;
	}

	public void startCalendar(String uId) {
		new CalendarView(uId);
	}

	public boolean exsitDiary(String date, String uId) {
		String path = uId + "/" + date + ".dat";
		File diary = new File(path);
		if (diary.exists()) {
			return true;
		} else {
			return false;
		}
	}

	public String setCalendarDiaryImage(String uId, int year, int month, int date) {
		int yearFormat = year % 100;
		String path=null;
		String m = String.format("%02d", month + 1);

		String d = String.format("%02d", date);
		File file = null;
		file = new File(uId + "/" + yearFormat + "." + m + "." + d + ".png");
		if(file.exists()) {
			path=file.getPath();
		}
		return path;
	}

}
