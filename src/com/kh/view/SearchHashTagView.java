package com.kh.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class SearchHashTagView {
	private static final int MAX = 10;
	private JPanel panel;
	private JButton searchBtn;
	private HintTextField searchText;
	private JFrame jf;
	private JPanel[] listBoard = new JPanel[MAX];
	private JButton[] boardInfoBtn = new JButton[MAX];
	private JLabel[] listText = new JLabel[MAX];
	private static String searched;

	public SearchHashTagView() {
		// TODO Auto-generated constructor stub
	}

	public SearchHashTagView(String searchedText) {
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
		panel = new JPanel();
		panel.setBackground(new Color(0xddc6e6));
		panel.setSize(jf.getMaximumSize());
		panel.setLayout(new BorderLayout());
		panel.setFocusable(true);

		// 검색 버튼 및 엔터 시
		ImageIcon icon2 = new ImageIcon("image/search.PNG");

		searchBtn = new JButton(icon2);
		searchBtn.setBounds(100, 100, 50, 50);
		searchBtn.setBackground(grayColor);
		searchBtn.addActionListener(new BtnActionListner());
		searchBtn.setName("searchBtn");

		// 검색창
		searchText = new HintTextField(searched);
		searchText.setBounds(150, 100, 350, 50);
		searchText.registerKeyboardAction(new BtnActionListner(), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_FOCUSED);

		board.setLayout(new GridLayout(0, 1, 1, 1));
		board.setBounds(0, 200, 625, 652);
		board.setBackground(Color.BLACK);
//		일기목록맨윗줄 타이틀 추가
		JPanel title = new JPanel(new BorderLayout());
		title.setLayout(new BorderLayout());
		title.setSize(630, 100);
		board.add(title);

		JLabel[] titles = new JLabel[3];
		JPanel[] panels = new JPanel[3];

		titles[0] = new JLabel("날짜");
		titles[1] = new JLabel("해시태그");
		titles[2] = new JLabel("읽기");

		for (int i = 0; i < 3; i++) {
			titles[i].setFont(font);
			titles[i].setAlignmentY(JLabel.CENTER);
			panels[i] = new JPanel();
			panels[i].add(titles[i]);

		}
		panels[0].setSize(150, 100);
		panels[1].setSize(400, 100);
		panels[2].setSize(190, 100);
		title.add(panels[0]);
		title.add(panels[1], BorderLayout.CENTER); //, BorderLayout.CENTER
		title.add(panels[2], BorderLayout.EAST); //, BorderLayout.EAST

		// 일기목록 내용
		JPanel dateBoard[] = new JPanel[MAX]; // 날짜 패널
		JPanel hashBoard[] = new JPanel[MAX]; // 해시태그 패널

		JLabel dateList[] = new JLabel[MAX];
		JLabel hashList[] = new JLabel[MAX];

		int cnt = MAX;
		Image temp = new ImageIcon("image/arrow.PNG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon arrow = new ImageIcon(temp);
		for (int i = 0; i < cnt; i++) {

			listBoard[i] = new JPanel();
			listBoard[i].setLayout(new BorderLayout());
			listBoard[i].setSize(630, 30);

			dateList[i] = new JLabel();
			dateList[i].setText("날짜" + i);
			dateList[i].setFont(font);

			hashList[i] = new JLabel();
			hashList[i].setText("#hash" + i);
			hashList[i].setFont(font);

			dateBoard[i] = new JPanel();
			dateBoard[i].setSize(150, 100);
			dateBoard[i].setBackground(Color.white);
			hashBoard[i] = new JPanel();

			hashBoard[i].setSize(300, 100);
			hashBoard[i].setBackground(Color.white);

			boardInfoBtn[i] = new JButton(arrow);
			boardInfoBtn[i].setBackground(Color.WHITE);
			boardInfoBtn[i].setSize(100, 100);
			boardInfoBtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			});

			dateBoard[i].add(dateList[i]);
			hashBoard[i].add(hashList[i]);

			
			listBoard[i].add(dateBoard[i]);
			listBoard[i].add(hashBoard[i]);
			listBoard[i].add(boardInfoBtn[i], BorderLayout.EAST);
			board.add(listBoard[i]);
		}
//		

		jf.add(searchBtn);
		jf.add(searchText);
		jf.add(board);
		jf.add(panel);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	class BtnActionListner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == searchBtn || e.getSource() == searchText) {
				if (!searchText.getText().equals(searched)) { // 검색해서 들어온 검색어에는 반응하지 않게
					System.out.println(searchText.getText());
					jf.setVisible(false);

					new SearchHashTagView(searchText.getText());
				}

			}


		}
	}

}
