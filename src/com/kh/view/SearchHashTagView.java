package com.kh.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.kh.controller.CalendarController;
import com.kh.controller.DiaryController;
import com.kh.controller.SearchController;
import com.kh.model.vo.Diary;
import com.kh.model.vo.User;

//일기창 -> 검색 flag =1
//캘린더-> 검색 flag=0;
public class SearchHashTagView {
	private static final int MAX = 8;
	private User user;
	private JPanel panel;
	private JButton searchBtn;
	private JButton prevBtn;
	private HintTextField searchTextField;
	private JFrame jf;
	private JPanel[] listBoard = new JPanel[MAX];
	private JButton[] boardInfoBtn = new JButton[MAX];
	private JLabel[] listText = new JLabel[MAX];
	private static String searched;

	private int page = 0;

	private ArrayList<Diary> diary;
	private SearchController sc = new SearchController();
	private int flag;

	public SearchHashTagView() {
		// TODO Auto-generated constructor stub
	}

	public SearchHashTagView(User user, String searchedText, int p, int flag) {
		page=p;
		this.flag = flag;
		this.user = user;
		JPanel board = new JPanel();
		searched = searchedText;
		Color grayColor = new Color(0xeeeeee);
		Font font = new Font("맑은 고딕", Font.PLAIN, 18);

		jf = new JFrame();
		jf.setBounds(0, 0, 640, 960);
		jf.setTitle("해씨 일기");
		jf.setLayout(null);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		try {
			jf.setIconImage(ImageIO.read(new File("image/IconHamster.jpg")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel = new JPanel();
		panel.setBackground(new Color(0xddc6e6));
		panel.setSize(jf.getMaximumSize());
		panel.setLayout(new BorderLayout());
		panel.setFocusable(true);

		// 이전
		ImageIcon icPrev = new ImageIcon("Image/prev.png");
		Image imPrev = icPrev.getImage().getScaledInstance(40, 35, Image.SCALE_SMOOTH);

		prevBtn = new JButton();
		prevBtn.setBorderPainted(false);
		prevBtn.setFocusPainted(false);
		prevBtn.setContentAreaFilled(false);
		prevBtn.setLayout(null);
		prevBtn.setIcon(new ImageIcon(imPrev));
		prevBtn.setBounds(20, 110, 90, 35);
		prevBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jf.setVisible(false);
				new CalendarController(user);
			}
		});

		// 검색 버튼 및 엔터 시
		ImageIcon icon2 = new ImageIcon("image/search.PNG");

		searchBtn = new JButton(icon2);
		searchBtn.setBounds(100, 100, 50, 50);
		searchBtn.setBackground(grayColor);
		searchBtn.addActionListener(new BtnActionListner());
		searchBtn.setName("searchBtn");

		// 검색창
		searchTextField = new HintTextField(searched);
		searchTextField.setBounds(150, 100, 350, 50);
		searchTextField.registerKeyboardAction(new BtnActionListner(), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_FOCUSED);

		board.setLayout(new GridLayout(0, 1, 1, 1));
		board.setBounds(0, 200, 625, 652);
		board.setBackground(Color.BLACK);
//		일기목록맨윗줄 타이틀 추가
		JPanel title = new JPanel();
		title.setLayout(null);
		title.setSize(630, 100);
		board.add(title);

		JLabel[] titles = new JLabel[3];
		JPanel[] panels = new JPanel[3];

		titles[0] = new JLabel("날짜");
		titles[1] = new JLabel("해시태그");
		titles[2] = new JLabel("읽기");

		for (int i = 0; i < 3; i++) {
			titles[i].setFont(font);
			panels[i] = new JPanel();
			panels[i].setBackground(grayColor);
			panels[i].add(titles[i]);

		}

		panels[0].setBounds(0, 0, (int) (title.getWidth() * 0.2), title.getHeight());
		panels[1].setBounds((int) (title.getWidth() * 0.2), 0, (int) (title.getWidth() * 0.7), title.getHeight());
		panels[2].setBounds((int) (title.getWidth() * 0.9), 0, (int) (title.getWidth() * 0.1), title.getHeight());

		title.add(panels[0]);
		title.add(panels[1]); // , BorderLayout.CENTER
		title.add(panels[2]); // , BorderLayout.EAST

		// 일기목록 내용
		JPanel dateBoard[] = new JPanel[MAX]; // 날짜 패널
		JPanel hashBoard[] = new JPanel[MAX]; // 해시태그 패널

		JLabel dateList[] = new JLabel[MAX];
		JLabel hashList[] = new JLabel[MAX];

		Image temp = new ImageIcon("image/arrow.PNG").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		ImageIcon arrow = new ImageIcon(temp);

		diary = sc.searchDiary(user.getuId(), searchedText);
		String hashTagText = "";

		if (page > 0) {
			JButton beforeBtn = new JButton();
			beforeBtn.setBorderPainted(false);
			beforeBtn.setFocusPainted(false);
			beforeBtn.setContentAreaFilled(false);
			beforeBtn.setLayout(null);
			beforeBtn.setText("◀");
			beforeBtn.setFont(new Font("맑은고딕", Font.PLAIN, 30));
			beforeBtn.setBounds(150, 830, 100, 100);
			beforeBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					page -= 1;
					jf.setVisible(false);
					new SearchHashTagView(user, searchTextField.getText(), page, 1);
				}
			});
			jf.add(beforeBtn);
		}
		
		if ( ((diary.size() % MAX)==0 && page < (diary.size() / MAX)-1) || ((diary.size() % MAX)!=0 && page < (diary.size() / MAX))) {
			JButton AfterBtn = new JButton();
			AfterBtn.setBorderPainted(false);
			AfterBtn.setFocusPainted(false);
			AfterBtn.setContentAreaFilled(false);
			AfterBtn.setLayout(null);
			AfterBtn.setText("▶");
			AfterBtn.setFont(new Font("맑은고딕", Font.PLAIN, 30));
			AfterBtn.setBounds(400, 830, 100, 100);
			AfterBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					page += 1;
					jf.setVisible(false);
					new SearchHashTagView(user, searchTextField.getText(), page, 1);
				}
			});
			jf.add(AfterBtn);
		}

		for (int i = 0; i < MAX; i++) {
			listBoard[i] = new JPanel();
			listBoard[i].setLayout(null);
			listBoard[i].setSize(board.getWidth(), (int) ((board.getHeight() - title.getHeight()) * 0.125));

			// 날짜 라벨
			dateList[i] = new JLabel();
			dateList[i].setFont(font);

			// 해시태그 라벨
			hashList[i] = new JLabel();
			hashList[i].setFont(font);

			boardInfoBtn[i] = new JButton(arrow); // 버튼
			dateBoard[i] = new JPanel(); // 날짜 라벨 붙인 패널
			hashBoard[i] = new JPanel(); // 해시태그 라벨 붙인 패널

			boardInfoBtn[i].setBackground(Color.WHITE);
			boardInfoBtn[i].setBorderPainted(false);
			boardInfoBtn[i].setFocusPainted(false);
			boardInfoBtn[i].setBounds((int) (listBoard[i].getWidth() * 0.9), 0, (int) (listBoard[i].getWidth() * 0.1),
					listBoard[i].getHeight());

			if (i+(page*8) < diary.size()) { // 다이어리 객체수만큼만 실행함
				hashTagText = sc.searchHashText(diary.get(i + (page * MAX)), searchedText); // 검색어가 포함된 해시태그를 가진 객체를 받아서 그
																							// 객체에 검색어가 포함된 해시태그 스트링을 저장
				hashList[i].setText(hashTagText); // 해시태그 스트링 세팅
				dateList[i].setText(diary.get(i + (page * MAX)).getdDate()); // 날짜 스트링 세팅
				boardInfoBtn[i].addActionListener(new BtnActionListner()); // 버튼 이벤트 추가해주기

			} else {
				boardInfoBtn[i].setIcon(null);
				boardInfoBtn[i].setEnabled(false); // 다이어리 객체수 이외의 버튼들은 비활성화 -> 빈줄에 버튼 안뜨게
			}

			dateBoard[i].setBounds(0, 0, (int) (listBoard[i].getWidth() * 0.2), listBoard[i].getHeight());
			dateBoard[i].setBackground(Color.white);

			hashBoard[i].setBounds((int) (listBoard[i].getWidth() * 0.2), 0, (int) (listBoard[i].getWidth() * 0.7),
					listBoard[i].getHeight());
			hashBoard[i].setBackground(Color.white);

			dateBoard[i].add(dateList[i]);
			hashBoard[i].add(hashList[i]);
			listBoard[i].add(dateBoard[i]);
			listBoard[i].add(hashBoard[i]);
			listBoard[i].add(boardInfoBtn[i]);

			board.add(listBoard[i]);
		}

		// 패널에 붙이기
		jf.add(prevBtn);
		jf.add(searchBtn);
		jf.add(searchTextField);

		jf.add(board);
		jf.add(panel);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	class BtnActionListner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == searchBtn || e.getSource() == searched) {
				if (!searchTextField.getText().equals(searched)) { // 검색해서 들어온 검색어에는 반응하지 않게
					jf.setVisible(false);
					new SearchHashTagView(user, searchTextField.getText(), 0, 1);
				}

			}

			for (int i = 0; i < boardInfoBtn.length; i++) {
				// 일기창 -> 검색 flag =1
				// 캘린더-> 검색 flag=0;
				if (e.getSource() == boardInfoBtn[i]) {
					jf.setVisible(false);
					new DiaryController().diaryRead(user, diary.get(i+(page * MAX)).getdDate(), searched, flag);

				}
			}

		}
	}

}
