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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kh.controller.UserController;
import com.kh.model.vo.User;

public class LoginView extends JFrame implements ActionListener {

	private UserController uc = new UserController();
	private User us = new User();

	public LoginView() {

		JFrame jf = new JFrame();

		jf.setTitle("해씨일기");
		jf.setSize(640, 960);
		jf.setLayout(null);
		jf.getContentPane().setLayout(null);
		jf.setResizable(false); // 창 크기 수정방지
		jf.setLocationRelativeTo(null); // 창이 가운데 나오게

		JPanel bGround = new JPanel();
		bGround.setSize(640, 960);
		bGround.setBackground(new Color(221, 198, 230));

		jf.add(bGround);

		JLabel name = new JLabel("해씨일기");
		name.setFont(new Font("", Font.BOLD, 45));
		name.setForeground(Color.white);

		Image hamster = new ImageIcon("image/hamster3.png").getImage();
		JLabel ham = new JLabel();
		ham.setIcon(new ImageIcon(hamster));

		Image seeds = new ImageIcon("image/seed (1).png").getImage();
		JLabel seed = new JLabel();
		seed.setIcon(new ImageIcon(seeds));

		JLabel lbId = new JLabel("아이디");
		JLabel lbPwd = new JLabel("비밀번호");
		JTextField tfId = new JTextField();
		JPasswordField tfPwd = new JPasswordField();
		JButton login = new JButton("로그인");
		JButton signUp = new JButton("회원가입");
		JButton rPwd = new JButton("비밀번호 재설정");

		// 컴포넌트 위치 및 사이즈
		name.setBounds(225, 100, 200, 60);
		ham.setBounds(320, 170, 130, 130);
		seed.setBounds(180, 170, 130, 130);
		lbId.setBounds(240, 340, 50, 25);
		tfId.setBounds(300, 340, 100, 25);
		lbPwd.setBounds(230, 380, 50, 25);
		tfPwd.setBounds(300, 380, 100, 25);
		login.setBounds(280, 500, 80, 25);
		signUp.setBounds(270, 550, 100, 25);
		rPwd.setBounds(255, 600, 130, 25);

		// 컴포넌트 패널에 추가
		bGround.setLayout(null);
		bGround.add(name);
		bGround.add(ham);
		bGround.add(seed);
		bGround.add(lbId);
		bGround.add(lbPwd);
		bGround.add(tfId);
		bGround.add(tfPwd);
		bGround.add(login);
		bGround.add(signUp);
		bGround.add(rPwd);

		// 회원가입창으로 넘어가기
		signUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jf.setVisible(false);
				new SignUpView();
			}

		});
	
		// 로그인 하기
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = tfId.getText();
				String pwd = tfPwd.getText();
				
				uc.userLogin(id, pwd);				
				uc.userId(id);
				
			}

		});
		
		// 비밀번호 재설정 
		rPwd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserPwdView();
				jf.setVisible(false);
				
			}
		});

		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
