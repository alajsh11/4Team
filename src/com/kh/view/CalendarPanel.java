package com.kh.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kh.controller.CalendarController;
import com.kh.view.CalendarView.BtnActionListner;

public class CalenderPanel extends JPanel {
	private CalendarController cc = new CalendarController();
	private int nowYear;
	private int nowMonth;
	private int nowDate;
	private JButton[] month;
	private int loW; // 해당 월 1일의 요일을 받기위한 변수
	private int[] lastDate = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	private GregorianCalendar gc = new GregorianCalendar();
	public CalenderPanel() {
		// TODO Auto-generated constructor stub
	}
	public CalenderPanel(int y,int m, int d) {
		super();
		isLeapYear(y); // 2월 윤년이면 날짜배열 세팅 바꾸기
		Color calendarBack = new Color(0xeeeeee);
		month = new JButton[42];

		JPanel[] emptyPanel = new JPanel[14];
		JLabel[] date = new JLabel[49];
		JPanel[] doW = new JPanel[7]; // (일~토)요일 텍스트 패널에 표시
		
		nowYear = y;
		nowMonth = m;
		nowDate = d;
		System.out.println(nowYear+" "+nowMonth+" "+nowDate);
		
		gc.set(nowYear, nowMonth, 1);
//		gc.set(nowYear, nowMonth, 1);
		loW = gc.get(GregorianCalendar.DAY_OF_WEEK); // 해당 월 1일의 요일을 받기위한 변수
		// 캘린더 흰색
		this.setBounds(2, 300, 620, 500);
		this.setLayout(new GridLayout(0, 7, 1, 1));
		this.setBackground(new Color(0xeeeeee));

		String[] dayOfWeek = { "일", "월", "화", "수", "목", "금", "토" };
		// 캘린더 요일 표시
		for (int i = 0; i < 7; i++) {
			date[i] = new JLabel();
			date[i].setFont(new Font("Serif", Font.PLAIN, 20));
			date[i].setHorizontalAlignment(JLabel.CENTER);

			doW[i] = new JPanel();
			doW[i].setLayout(new BorderLayout());

			date[i].setText(dayOfWeek[i]);
			doW[i].setBackground(new Color(0xdddddd));

			doW[i].add(date[i]);
			this.add(doW[i]);
		}

		int cnt = 0;
		// 캘린더 날짜 표시
		for (int i = 0; i < 42; i++) {
			month[i] = new JButton();
			month[i].setLayout(new BorderLayout());
			if ((i < lastDate[nowMonth] + (loW - 1)) && (i >= loW - 1)) { // 날짜세팅
//				System.out.println(" i: " + i);
				date[i] = new JLabel();
				date[i].setFont(new Font("Serif", Font.PLAIN, 20));
				date[i].setHorizontalAlignment(JLabel.CENTER);
				date[i].setText(Integer.toString(i - loW + 2));
				month[i].addActionListener(new BtnActionListner());
				month[i].add(date[i]);
			}
			if (i < loW - 1) { // 앞에 빈 날짜
				emptyPanel[cnt] = new JPanel();
				emptyPanel[cnt].setBackground(calendarBack);
				this.add(emptyPanel[cnt]);
			} else if (i <= lastDate[nowMonth] + loW - 2) { // 말일이 있는 주까지 세팅
				month[i].setBackground(Color.white);

				this.add(month[i], BorderLayout.EAST);
			}

		}
	}


	// 윤년확인 메소드
	public void isLeapYear(int year) {
		if ((year % 4 == 0) && (year % 100 != 0) || year % 400 == 0) {
			lastDate[1]=29;
		}
		else {
			lastDate[1] = 28;
		}

	}

	class BtnActionListner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < lastDate[gc.get(GregorianCalendar.MONTH)]; i++) {
				if (e.getSource() == month[i]) {
					gc.set(nowYear, nowMonth, i - loW + 2);
					System.out.println(cc.dateInCalendar(gc.getTime()));
					
				}
			}
		}
	}

}
