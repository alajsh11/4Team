package com.kh.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MemberInformationView extends JFrame{
	private JTextField userListField;
	private JTable userListTable;
	private String[] headings ;
	Object[][] data;
	
	public MemberInformationView() {
		this.setSize(640,960);
		this.setTitle("회원조회");
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		//전체 배경패널
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0xddc6e6));
		panel.setSize(getMaximumSize());
		panel.setLayout(null);
		
		//회원조회 패널
		userListField = new JTextField("회원조회");
		userListField.setBounds(200, 100, 300, 30);
		userListField.setEditable(false);

		
		//관리자 이미지라벨
		Image adminImg = new ImageIcon("image/user.PNG").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		JLabel adminImgLabel = new JLabel(new ImageIcon(adminImg));
		adminImgLabel.setBounds(100, 80, 100,100);
		adminImgLabel.setBackground(new Color(0xddc6e6));
		
		//유저 목록 리스트 패널
		JPanel userListPanel = new JPanel();
		userListPanel.setLayout(new GridLayout(3,6));
		userListPanel.setBounds(0,200,640,600);
		
		//유저 목록 리스트 테이블
		//테이블 clounm
		headings = new String[] {"회원번호", "회원아이디", "회원조회"};
		//테이블 data
		data = new Object[][] {
			{"001", "진성현", ""},
			{"002", "성현", ""}
		};
		//테이블 생성 
		userListTable = new JTable(data, headings);
		userListTable.setPreferredScrollableViewportSize(new Dimension(640,600));	//테이블 사이즈  
		userListTable.setFillsViewportHeight(true);
		userListTable.setRowHeight(30);
		
		userListPanel.add(new JScrollPane(userListTable));
		userListPanel.setOpaque(false);
		//로그아웃 버튼
		JButton logOutButton = new JButton("로그아웃");
		logOutButton.setBounds(250, 850, 100, 50);
		//로그아웃 버튼 클릭시 loginView화면으로 이동
		logOutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//LoginView.this.setVisible(true);
				MemberInformationView.this.setVisible(false);
			}
		});
		
		//패널에 추가
		panel.add(userListPanel);
		panel.add(adminImgLabel);
		panel.add(userListField);
		panel.setVisible(true);
		panel.add(logOutButton);
		
		//화면에 추가
		this.add(panel);
		this.setVisible(true);
	}

}
