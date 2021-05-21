package com.kh.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.kh.controller.UserController;
import com.kh.model.vo.Diary;

public class UserInformationView extends JFrame {

	UserController uc = new UserController();
	Diary dy = new Diary();

	public UserInformationView() {

		super("UserInformationView");

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

		// JPanel panel = new JPanel();
		// panel.setBackground(Color.white);
		JLabel title = new JLabel("내 정보 조회");
		title.setOpaque(true);
		title.setBackground(Color.white);
		title.setFont(new Font("맑은고딕", Font.BOLD, 20));
		title.setHorizontalAlignment(JLabel.CENTER);
		JLabel tDate = new JLabel("  회원 가입 날짜");
		tDate.setOpaque(true);
		tDate.setBackground(Color.white);
		tDate.setFont(new Font("맑은고딕", Font.BOLD, 14));
		// Date date = uc.userSignDate();
		// SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		JLabel sDate = new JLabel();
		sDate.setOpaque(true);
		sDate.setBackground(Color.LIGHT_GRAY);
		sDate.setFont(new Font("맑은고딕", Font.PLAIN, 14));
		JLabel hint = new JLabel("  비밀번호 힌트 (졸업한 초등학교 이름)");
		hint.setOpaque(true);
		hint.setBackground(Color.white);
		hint.setFont(new Font("맑은고딕", Font.BOLD, 13));
		// String s = uc.userSignHint();
		JLabel school = new JLabel();
		school.setOpaque(true);
		school.setBackground(Color.LIGHT_GRAY);
		school.setFont(new Font("맑은고딕", Font.PLAIN, 14));
		JLabel dNo = new JLabel("  총 해씨일기 개수");
		dNo.setOpaque(true);
		dNo.setBackground(Color.white);
		dNo.setFont(new Font("맑은고딕", Font.BOLD, 14));
		JLabel dCount = new JLabel();
		dCount.setOpaque(true);
		dCount.setBackground(Color.LIGHT_GRAY);
		dCount.setFont(new Font("맑은고딕", Font.PLAIN, 14));
		JButton logout = new JButton("로그아웃");
		JButton leave = new JButton("탈퇴");

		// 컴포넌트 위치 및 사이즈
		title.setBounds(190, 100, 250, 40);
		tDate.setBounds(120, 240, 400, 30);
		sDate.setBounds(120, 270, 400, 30);
		hint.setBounds(120, 370, 400, 30);
		school.setBounds(120, 400, 400, 30);
		dNo.setBounds(120, 500, 400, 30);
		dCount.setBounds(120, 530, 400, 30);
		logout.setBounds(220, 650, 90, 30);
		leave.setBounds(330, 650, 90, 30);

		// 패널에 붙이기
		info.setLayout(null);
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

		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.CLOSED_OPTION || result == JOptionPane.NO_OPTION) {
					dispose();
				} else if (result == JOptionPane.YES_OPTION) {
					dispose();
					jf.setVisible(false);
					jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					new LoginView().loginView();
				}

			}
		});

		leave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "정말로 탈퇴 하시겠습니까?", "Confirm",
						JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.CLOSED_OPTION || result == JOptionPane.NO_OPTION) {
					dispose();
				} else if (result == JOptionPane.YES_OPTION) {
					dispose();
					jf.setVisible(false);
					uc.userDelete();
					new LoginView().loginView();
				}

			}
		});

		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}

