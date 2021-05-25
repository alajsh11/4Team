package com.kh.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.kh.controller.AdministratorController;
import com.kh.model.vo.User;

public class MemberCheckView {
	private JTextField adminIdField, userMembershipField, userPwdField, uesrTotalDialyField;
	private JButton prevButton, userDeleteButton;
	private JFrame jf;
	private DeleteModal dm;
	private CheckModal cm;
	private AdministratorController ac = new AdministratorController();
	
	public MemberCheckView(User user) {
		jf = new JFrame();
		try {
			jf.setIconImage(ImageIO.read(new File("image/IconHamster.jpg")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		jf.setSize(640, 960);
		jf.setTitle("해씨일기");
		jf.setLayout(null);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);

		// 전체 배경패널
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0xddc6e6));
		panel.setSize(jf.getMaximumSize());
		panel.setLayout(null);

		// prev 버튼
		Image prevImg = new ImageIcon("Image/prev.png").getImage().getScaledInstance(40, 35, Image.SCALE_SMOOTH);
		prevButton = new JButton(); // 이전 버튼
		prevButton.setBorderPainted(false);
		prevButton.setFocusPainted(false);
		prevButton.setContentAreaFilled(false);
		prevButton.setIcon(new ImageIcon(prevImg));
		prevButton.setBounds(60, 60, 90, 35);

		// 버튼클릭시 adminView MemberInformationView화면 이동
		prevButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MemberInformationView();
				jf.setVisible(false);
			}
		});

		// 관리자 이미지라벨
		Image adminImg = new ImageIcon("image/admin.PNG").getImage().getScaledInstance(100, 100, 0);
		JLabel adminImgLabel = new JLabel(new ImageIcon(adminImg));
		adminImgLabel.setBounds(80, 180, 100, 100);

		// 관리자 아이디 텍스트필드
		adminIdField = new JTextField(user.getuId());
		adminIdField.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		adminIdField.setBounds(200, 200, 350, 50);
		adminIdField.setEditable(false);

		// 회원가입 날짜 텍스트필드
		JTextField umsField = new JTextField("회원 가입 날짜");
		umsField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		umsField.setBounds(100, 350, 450, 30);
		userMembershipField = new JTextField(user.getuDate());
		userMembershipField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		
		userMembershipField.setBounds(100, 380, 450, 30);
		userMembershipField.setEditable(false);

		// 회원가입 날짜 텍스트필드
		JTextField upField = new JTextField("비밀번호 힌트 (졸업한 초등학교 이름)");
		upField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		upField.setBounds(100, 430, 450, 30);
		userPwdField = new JTextField(user.getuPwdAnswer());
		userPwdField.setBounds(100, 460, 450, 60);
		userPwdField.setEditable(false);
		userPwdField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		

		// 회원가입 날짜 텍스트필드
		JTextField utdField = new JTextField("총 해씨 일기 개수");
		utdField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		utdField.setBounds(100, 570, 450, 30);
		uesrTotalDialyField = new JTextField(String.valueOf(user.getDiaryCount()));
		uesrTotalDialyField.setBounds(100, 600, 450, 30);
		uesrTotalDialyField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		uesrTotalDialyField.setEditable(false);
		// 회원 삭제 버튼
		userDeleteButton = new JButton("회원삭제");
		userDeleteButton.setBounds(260, 750, 100, 30);
		dm = new DeleteModal(jf);
		
		// 삭제 버튼 클릭시 팝업창 띄우기
		userDeleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dm.setVisible(true);

			}
		});
		
		//회원 삭제작업
		dm.getDeleteButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 삭제메소드 작업
				cm = new CheckModal(dm);
				cm.setVisible(true);
				dm.setVisible(false);
				jf.setVisible(false);
				ac.userDelete(user);		// 회원 삭제
				ac.memberTableInfo();		// 회원 정보 업데이트 
				new MemberInformationView();	
				
			}
		});
		
		// 팝업 취소 버튼 클릭시 팝업창 닫기
		dm.getCancelButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dm.setVisible(false);
			}
		});
		

		// 패널에 추가
		panel.add(umsField);
		panel.add(userMembershipField);
		panel.add(upField);
		panel.add(userPwdField);
		panel.add(utdField);
		panel.add(uesrTotalDialyField);
		panel.add(prevButton);
		panel.add(adminIdField);
		panel.add(adminImgLabel);
		panel.add(userDeleteButton);

		jf.add(panel);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);

	}

//삭제하기 버튼 팝업창
class DeleteModal extends JDialog {
	private JButton deleteButton, cancelButton;

	public JButton getDeleteButton() {
		return deleteButton;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}

	public DeleteModal(Window parent) {
		super(parent, "삭제", ModalityType.APPLICATION_MODAL);
		// 화면구성
		setSize(400, 300);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		JLabel lb = new JLabel("정말로 삭제 하시겠습니까?");
		lb.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lb.setBounds(70, 30, 250, 100);

		// 삭제 버튼
		deleteButton = new JButton("삭제");
		deleteButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		deleteButton.setBounds(80, 180, 80, 50);

		// 취소버튼
		cancelButton = new JButton("취소");
		cancelButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		cancelButton.setBounds(220, 180, 80, 50);
		
		

		add(cancelButton);
		add(lb);
		add(deleteButton);

	}
}

//DeleteModal에서 삭제 버튼 클릭 팝업창
class CheckModal extends JDialog {
	private JButton checkButton;
	
	public JButton getCheckButton() {
		return checkButton;
	}

	public CheckModal(Window parent) {
		super(parent, "삭제", ModalityType.APPLICATION_MODAL);
		// 화면구성
		setSize(400, 300);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		JLabel lb = new JLabel("삭제 되었습니다");
		lb.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lb.setBounds(120, 60, 200, 70);

		checkButton = new JButton("확인");
		checkButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		checkButton.setBounds(40, 180, 300, 50);
		checkButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		add(checkButton);
		add(lb);

	}
}
}