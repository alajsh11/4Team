package com.kh.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class MemberCheckView extends JFrame {
	private JTextField adminIdField, userMembershipField, userPwdField, uesrTotalDailyField;
	private JButton prevButton, userDeleteButton;
	MemberInformationView adminView;

	public MemberCheckView() {
		this.setSize(640, 960);
		this.setTitle("회원조회");
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		// 전체 배경패널
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0xddc6e6));
		panel.setSize(getMaximumSize());
		panel.setLayout(null);

		// prev 버튼
		ImageIcon icon1 = new ImageIcon("Image/prev.png");
		Image img1 = icon1.getImage().getScaledInstance(40, 35, Image.SCALE_SMOOTH);
		prevButton = new JButton(); // 이전 버튼
		prevButton.setBorderPainted(false);
		prevButton.setFocusPainted(false);
		prevButton.setContentAreaFilled(false);
		prevButton.setIcon(new ImageIcon(img1));
		prevButton.setBounds(60, 60, 90, 35);

		// 버튼클릭시 adminView MemberInformationView화면 이동
		prevButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				adminView = new MemberInformationView();
				adminView.setVisible(true);

				MemberCheckView.this.setVisible(false);
			}
		});

		// 관리자 이미지라벨
		Image adminImg = new ImageIcon("image/user.PNG").getImage().getScaledInstance(100, 100, 0);
		JLabel adminImgLabel = new JLabel(new ImageIcon(adminImg));
		adminImgLabel.setBounds(80, 180, 100, 100);
		adminImgLabel.setBackground(Color.WHITE);
		BevelBorder border = new BevelBorder(BevelBorder.RAISED);
		adminImgLabel.setBorder(border);
		// 관리자 아이디 텍스트필드
		adminIdField = new JTextField("010-5495-3709");
		adminIdField.setBounds(200, 200, 350, 50);
		adminIdField.setEditable(false);

		// 회원가입 날짜 텍스트필드
		JTextField umsField = new JTextField("회원 가입 날짜");
		umsField.setBounds(100, 350, 450, 30);
		userMembershipField = new JTextField("2021-01-01");
		userMembershipField.setBounds(100, 380, 450, 30);
		userMembershipField.setEditable(false);

		// 회원가입 날짜 텍스트필드
		JTextField upField = new JTextField("비밀번호 힌트 (졸업한 초등학교 이름)");
		upField.setBounds(100, 430, 450, 30);
		userPwdField = new JTextField("잠실초등학교");
		userPwdField.setBounds(100, 460, 450, 60);
		userPwdField.setEditable(false);

		// 회원가입 날짜 텍스트필드
		JTextField utdField = new JTextField("총 해씨 일기 개수");
		utdField.setBounds(100, 570, 450, 30);
		uesrTotalDailyField = new JTextField("1");
		uesrTotalDailyField.setBounds(100, 600, 450, 30);
		uesrTotalDailyField.setEditable(false);

		// 회원 삭제 버튼
		userDeleteButton = new JButton("회원삭제");
		userDeleteButton.setBounds(260, 750, 100, 30);
		userDeleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteModal m = new DeleteModal(MemberCheckView.this);
				m.setVisible(true);

			}
		});

		// 패널에 추가
		panel.add(umsField);
		panel.add(userMembershipField);
		panel.add(upField);
		panel.add(userPwdField);
		panel.add(utdField);
		panel.add(uesrTotalDailyField);
		panel.add(prevButton);
		panel.add(adminIdField);
		panel.add(adminImgLabel);
		panel.add(userDeleteButton);

		this.add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
}

//삭제하기 버튼 팝업창
class DeleteModal extends JDialog {
	JButton deleteButton;
	
	public DeleteModal(Window parent) {
		super(parent, "삭제", ModalityType.APPLICATION_MODAL);
		//화면구성
		setSize(400, 300);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		JLabel lb = new JLabel("정말로 삭제 하시겠습니까?");
		lb.setBounds(120, 30, 200, 100);
		
		//삭제 버튼
		deleteButton = new JButton("삭제");
		deleteButton.setBounds(80, 180, 80, 50);
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//삭제메소드 작업
				CheckModal cm = new CheckModal(DeleteModal.this);
				cm.setVisible(true);
				DeleteModal.this.setVisible(false);
			}
		});
		
		
		//취소버튼
		JButton cancelButton = new JButton("취소");
		cancelButton.setBounds(220, 180, 80, 50);
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteModal.this.setVisible(false);
			}
		});
		
		
		add(cancelButton);
		add(lb);
		add(deleteButton);
		
	}
}

//DeleteModal에서 삭제 버튼 클릭 팝업창
class CheckModal extends JDialog{
	MemberInformationView adminView;
	
	public CheckModal(Window parent) {
		super(parent, "삭제", ModalityType.APPLICATION_MODAL);
		//화면구성
		setSize(400, 300);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		JLabel lb = new JLabel("삭제 되었습니다.");
		lb.setBounds(150, 30, 200, 100);
		
		JButton checkButton = new JButton("확인");
		checkButton.setBounds(50, 180, 300, 40);
		checkButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				adminView = new MemberInformationView();
				adminView.setVisible(true);
				setVisible(false);
				
			}
		});
		
		add(checkButton);
		add(lb);
		
	}
}