package com.kh.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.kh.controller.UserController;
import com.kh.model.vo.Diary;
import com.kh.model.vo.User;

public class UserInformationView  {

	private UserController uc = new UserController();
	private User us = new User();
	private Diary dy = new Diary();

	public UserInformationView() { 
		
	}
	
	public UserInformationView(User user) {

		JFrame jf = new JFrame();

		jf.setTitle("내정보조회");
		jf.setSize(640, 960);
		jf.setLayout(null);
		jf.getContentPane().setLayout(null);
		jf.setResizable(false); // 창 크기 수정방지
		jf.setLocationRelativeTo(null); // 창이 가운데 나오게

		// 배경 패널
		JPanel info = new JPanel();
		info.setSize(640, 960);
		info.setBackground(new Color(221, 198, 230));

		jf.add(info);

		JLabel title = new JLabel("내 정보 조회");
		title.setOpaque(true);
		title.setBackground(Color.white);
		title.setFont(new Font("맑은고딕", Font.BOLD, 25));
		title.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel tDate = new JLabel("  회원 가입 날짜");
		tDate.setOpaque(true);
		tDate.setBackground(Color.white);
		tDate.setFont(new Font("맑은고딕", Font.BOLD, 17));		
		String date = uc.userSignDate(user.getuId());
		JLabel sDate = new JLabel(date);
		sDate.setOpaque(true);
		sDate.setBackground(Color.LIGHT_GRAY);
		sDate.setFont(new Font("맑은고딕", Font.PLAIN, 15));
		JLabel hint = new JLabel("  비밀번호 힌트 (졸업한 초등학교 이름)");
		
		hint.setOpaque(true);
		hint.setBackground(Color.white);
		hint.setFont(new Font("맑은고딕", Font.BOLD, 17));
		String s = uc.userSignHint(user.getuId());
		JLabel school = new JLabel(s);
		school.setOpaque(true);
		school.setBackground(Color.LIGHT_GRAY);
		school.setFont(new Font("맑은고딕", Font.PLAIN, 15));
		
		JLabel dNo = new JLabel("  총 해씨일기 개수");
		dNo.setOpaque(true);
		dNo.setBackground(Color.white);
		dNo.setFont(new Font("맑은고딕", Font.BOLD, 17));	
		JLabel dCount = new JLabel(" " + String.valueOf(user.getDiaryCount()));
		dCount.setOpaque(true);
		dCount.setBackground(Color.LIGHT_GRAY);
		dCount.setFont(new Font("맑은고딕", Font.PLAIN, 15));
		
		JButton logout = new JButton("로그아웃");
		logout.setFont(new Font("맑은고딕",Font.PLAIN,15));
		
		JButton leave = new JButton("탈퇴");		
		leave.setFont(new Font("맑은고딕",Font.PLAIN,15));
		
		ImageIcon icPrev = new ImageIcon("Image/prev.png");
	    Image imPrev = icPrev.getImage().getScaledInstance(40, 35, Image.SCALE_SMOOTH);
	    JButton prev = new JButton();
	    // 이전버튼 아이콘 바꿔서 설정하기 위한 코드
	    prev.setBorderPainted(false);
	    prev.setFocusPainted(false);
	    prev.setContentAreaFilled(false);
	    prev.setLayout(null);
	    prev.setIcon(new ImageIcon(imPrev));

		// 컴포넌트 위치 및 사이즈
		title.setBounds(190, 100, 250, 40);
		tDate.setBounds(120, 240, 400, 30);
		sDate.setBounds(120, 270, 400, 30);
		hint.setBounds(120, 370, 400, 30);
		school.setBounds(120, 400, 400, 30);
		dNo.setBounds(120, 500, 400, 30);
		dCount.setBounds(120, 530, 400, 30);
		logout.setBounds(220, 700, 90, 30);
		leave.setBounds(330, 700, 90, 30);
		prev.setBounds(60, 100, 80, 30);

		// 패널에 붙이기
		info.setLayout(null);
		info.add(prev);
		info.add(title);
		info.add(tDate);
		info.add(sDate);
		info.add(hint);
		info.add(school);
		info.add(dNo);
		info.add(dCount);
		info.add(logout);
		info.add(leave);

		// 버튼 액션
		
		// 뒤로가기
		prev.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);
				new CalendarView(user);
				
			}
		});

		// 로그아웃
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.CLOSED_OPTION || result == JOptionPane.NO_OPTION) {
					
				} else if (result == JOptionPane.YES_OPTION) {
					
					jf.setVisible(false);
					jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					new LoginView();
				}

			}
		});

		// 회원탈퇴
		leave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "정말로 탈퇴 하시겠습니까?", "Confirm",
						JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.CLOSED_OPTION || result == JOptionPane.NO_OPTION) {
				
				} else if (result == JOptionPane.YES_OPTION) {
					
					jf.setVisible(false);
					uc.userDelete(user.getuId());
					new LoginView();
				}

			}
		});

		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
