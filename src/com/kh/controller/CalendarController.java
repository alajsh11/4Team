package com.kh.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarController {
	public String dateInCalendar(Date date) {
		String day = new SimpleDateFormat("yy.MM.dd").format(date);
		return day;
	}
	
	public void exsitDiary() {
		
	}
	
}
