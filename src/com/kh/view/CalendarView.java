package com.kh.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.GregorianCalendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class CalendarView {
	// new Color(0xddc6e6)
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
	
	public CalendarView() {
		Font font = new Font("맑은 고딕", Font.PLAIN, 18);
		jf = new JFrame();
		jf.setBounds(0, 0, 640, 960);
		jf.setTitle("해씨 일기");
		jf.setLayout(null);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		Image hamster = new ImageIcon("image/hamster2.PNG").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ham= new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(hamster,500,230,null);
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
		calendar = new CalenderPanel(nowYear, nowMonth, nowDate);

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

		
		//내정보 버튼	
		infoBtn= new JButton("내 정보");	
		infoBtn.setBounds(300,200,100,50);
		infoBtn.setBackground(new Color(0x5e5e5e));
		infoBtn.setFont(font );
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
		yearCombo.setFont(font );
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
				calendar = new CalenderPanel(nowYear, nowMonth, nowDate);
				
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
				calendar = new CalenderPanel(nowYear, nowMonth, nowDate);
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

	public class HintTextField extends JTextField {
		Font font = new Font("맑은 고딕", Font.PLAIN, 20);

		public HintTextField(final String hint) {
			setName("searchTxt");
			setText(hint);
			setFont(font);
			setForeground(Color.GRAY);
			this.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if (getText().equals(hint)) {
						setText("");
						setForeground(Color.BLACK);
					} else {
						setText(getText());
					}
				}

				@Override
				public void focusLost(FocusEvent e) {
					if (getText().equals(hint) || getText().length() == 0) {
						setText(hint);
						setForeground(Color.GRAY);
					} else {
						setText(getText());
						setForeground(Color.BLACK);
					}
				}
			});
		}
	}



	class BtnActionListner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == searchBtn||e.getSource()==searchText) {
				if( !(searchText.getText().equals(" #해시태그 검색")|| searchText.getText().equals("") ) ) {
					System.out.println(searchText.getText());
					jf.setVisible(false);
					new SearchHashTagView(searchText.getText());
				}
			}
			if (e.getSource() == infoBtn) {
				System.out.println("내정보로 이동");
			}
		}
	}
}