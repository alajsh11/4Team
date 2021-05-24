package com.kh.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.kh.controller.CalendarController;
import com.kh.controller.DiaryController;
import com.kh.model.vo.User;

public class CalendarView {
	private JPanel panel;
	
	private JButton searchBtn;
	private JButton infoBtn;
	private JComboBox monCombo;
	private JComboBox yearCombo;
	private HintTextField searchText;
	private String[] mon = { "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" };
	private GregorianCalendar gc = new GregorianCalendar();

	private int nowYear;
	private int nowMonth;
	private int nowDate;

	private JPanel calendar;
	private JPanel ham;
	private JFrame jf;

	private User user;

	private CalendarController cc = new CalendarController();
	private JButton[] month;
	private int loW; // 해당 월 1일의 요일을 받기위한 변수
	private int[] lastDate = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	
	public CalendarView() {
	}

	public CalendarView(User user) {
		this.user= user;
		Font font = new Font("맑은 고딕", Font.PLAIN, 18);
		jf = new JFrame();
		jf.setBounds(0, 0, 640, 960);
		jf.setTitle("해씨 일기");
		jf.setLayout(null);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		try {
			jf.setIconImage(ImageIO.read(new File("image/IconHamster.PNG")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Image hamster = new ImageIcon("image/hamster2.PNG").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ham = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(hamster, 500, 230, null);
			}
		};
		ham.setSize(jf.getMaximumSize());

		panel = new JPanel();
		panel.setBackground(new Color(0xddc6e6));
		panel.setSize(jf.getMaximumSize());
		panel.setLayout(new BorderLayout());
		panel.setFocusable(true);

		nowYear = gc.get(GregorianCalendar.YEAR);
		nowMonth = gc.get(GregorianCalendar.MONTH);
		nowDate = gc.get(GregorianCalendar.DATE);
		calendar = createCalendarPanel(nowYear, nowMonth, nowDate, user.getuId());

		// 검색 버튼 및 엔터 시
		ImageIcon icon2 = new ImageIcon("image/search.PNG");

		searchBtn = new JButton(icon2);
		searchBtn.setBounds(100, 100, 50, 50);
		searchBtn.setBackground(new Color(0xeeeeee));
		searchBtn.addActionListener(new BtnActionListner());
		searchBtn.setName("searchBtn");

		// 검색창
		searchText = new HintTextField(" #해시태그 검색");
		searchText.setBounds(150, 100, 350, 50);
		searchText.registerKeyboardAction(new BtnActionListner(), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_FOCUSED);

		// 내정보 버튼
		infoBtn = new JButton("내 정보");
		infoBtn.setBounds(300, 200, 100, 50);
		infoBtn.setBackground(new Color(0x5e5e5e));
		infoBtn.setFont(font);
		infoBtn.setForeground(Color.white);
		infoBtn.addActionListener(new BtnActionListner());
		// 연도 선택 콤보박스
		int cnt = 0;
		String[] year = new String[200];
		for (int i = 1900; i < 2100; i++) {
			year[cnt++] = i + "년";
		}

		yearCombo = new JComboBox(year);
		yearCombo.setBounds(100, 200, 90, 50);
		yearCombo.setFont(font);
		yearCombo.setBackground(Color.WHITE);
		yearCombo.setSelectedItem(nowYear + "년");

		yearCombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

				String selected = (String) yearCombo.getSelectedItem();
				String selectedYear = selected.substring(0, 4);
				nowYear = Integer.parseInt(selectedYear);
				jf.remove(calendar);
				calendar = createCalendarPanel(nowYear, nowMonth, nowDate, user.getuId());

				jf.add(calendar, BorderLayout.CENTER);
				jf.add(ham);
				jf.add(panel);
				jf.setVisible(true); // 이거 안해주면 안뜸. . . .
				jf.repaint();
			}
		});

		// 월 선택 콤보박스
		monCombo = new JComboBox(mon);
		monCombo.setBounds(190, 200, 60, 50);
		monCombo.setFont(font);
		monCombo.setBackground(Color.WHITE);
		monCombo.setSelectedIndex(nowMonth);
		monCombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int selectedIndex = monCombo.getSelectedIndex();
				nowMonth = selectedIndex;
				jf.remove(calendar);
				calendar = createCalendarPanel(nowYear, nowMonth, nowDate, user.getuId());
				jf.add(calendar, BorderLayout.CENTER);
				jf.add(ham);
				jf.add(panel);
				jf.setVisible(true); // 이거 안해주면 안뜸...
				jf.repaint();
			}
		});

		jf.add(searchBtn);
		jf.add(searchText, BorderLayout.SOUTH);
		jf.add(calendar, BorderLayout.CENTER);
		jf.add(monCombo);
		jf.add(yearCombo);
		jf.add(infoBtn);
		jf.add(ham);
		jf.add(panel);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JPanel createCalendarPanel(int y, int m, int d, String uId) {
		lastDate[1] = cc.isLeapYear(y); // 2월 윤년이면 날짜배열 세팅 바꾸기
		JPanel calendar = new JPanel();
		Color calendarBack = new Color(0xeeeeee);
		month = new JButton[42];

		JPanel[] emptyPanel = new JPanel[14];
		JLabel[] date = new JLabel[49];
		JPanel[] doW = new JPanel[7]; // (일~토)요일 텍스트 패널에 표시

		nowYear = y;
		nowMonth = m;
		nowDate = d;

		gc.set(nowYear, nowMonth, 1);
		loW = gc.get(GregorianCalendar.DAY_OF_WEEK); // 해당 월 1일의 요일을 받기위한 변수
		// 캘린더 흰색
		calendar.setBounds(2, 300, 620, 500);
		calendar.setLayout(new GridLayout(0, 7, 1, 1));
		calendar.setBackground(new Color(0xeeeeee));

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
			calendar.add(doW[i]);
		}

		int cnt = 0;
		String imgPath = null;
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
				calendar.add(emptyPanel[cnt]);
			} else if (i <= lastDate[nowMonth] + loW - 2) { //패널 세팅
				imgPath = cc.setCalendarDiaryImage(uId, nowYear, nowMonth, i - loW + 2); //해당 날짜에 일기이미지 있는지 확인
				if (imgPath != null) {
					Image diaryImg = new ImageIcon(imgPath).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT); 
					ImageIcon diaryImgIcon = new ImageIcon(diaryImg); 
					month[i].setIcon(diaryImgIcon);
				}
				month[i].setBackground(Color.white);
				calendar.add(month[i], BorderLayout.EAST);

			}

		}

		return calendar;
	}

	class BtnActionListner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == searchBtn || e.getSource() == searchText) {
				if (!(searchText.getText().equals(" #해시태그 검색") || searchText.getText().equals(""))) {
					jf.setVisible(false);
					new SearchHashTagView(user,searchText.getText(),1); //캘린더 -> 검색
				}
			}
			if (e.getSource() == infoBtn) {
				jf.setVisible(false);
				new UserInformationView(user);
			}

			for (int i = loW - 1; i < lastDate[gc.get(GregorianCalendar.MONTH)] + (loW - 1); i++) {
				if (e.getSource() == month[i]) {
					gc.set(nowYear, nowMonth, i - loW + 2);
					String today = cc.dateInCalendar(gc.getTime());
					System.out.println("today" + today);
					jf.setVisible(false);
					if (cc.exsitDiary(today, user.getuId())) { //해당날짜에 일기가 존재하는지 확인
						new DiaryController().diaryRead(user, today,null,0); //캘린더 -> 일기 flag=0
					} else { //없을 시 작성창으로
						new DiaryWriteView(today, user);
					}
				}
			}
		}
	}
}