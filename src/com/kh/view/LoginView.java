package com.kh.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kh.controller.UserController;
import com.kh.model.vo.User;

public class LoginView implements ActionListener {

	private UserController uc = new UserController();
	private User us = new User();

	public LoginView() {

		JFrame jf = new JFrame();
		
		try {
	         jf.setIconImage(ImageIO.read(new File("image/IconHamster.jpg")));
	      } catch (IOException e1) {
	         // TODO Auto-generated catch block
	         e1.printStackTrace();
	      }

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
		name.setFont(new Font("맑은고딕", Font.BOLD, 45));
		name.setForeground(Color.white);

		Image hamster = new ImageIcon("image/hamster3.png").getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT);
		JLabel ham = new JLabel();
		ham.setIcon(new ImageIcon(hamster));

		Image seeds = new ImageIcon("image/login_seed.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
		JLabel seed = new JLabel();
		seed.setIcon(new ImageIcon(seeds));
		JLabel seed2 = new JLabel();
		seed2.setIcon(new ImageIcon(seeds));

		JLabel lbId = new JLabel("아이디");
		lbId.setFont(new Font("맑은고딕",Font.PLAIN,17));
		JLabel lbPwd = new JLabel("비밀번호");
		lbPwd.setFont(new Font("맑은고딕",Font.PLAIN,17));
		JTextField tfId = new JTextField();
		tfId.setFont(new Font("맑은고딕",Font.PLAIN,17));
		JPasswordField tfPwd = new JPasswordField();
		tfPwd.setFont(new Font("맑은고딕",Font.PLAIN,17));
		JButton login = new JButton("로그인");
		login.setFont(new Font("맑은고딕",Font.PLAIN,15));
		JButton signUp = new JButton("회원가입");
		signUp.setFont(new Font("맑은고딕",Font.PLAIN,15));
		JButton rPwd = new JButton("비밀번호 재설정");
		rPwd.setFont(new Font("맑은고딕",Font.PLAIN,15));

		// 컴포넌트 위치 및 사이즈
		name.setBounds(225, 100, 200, 60);
		ham.setBounds(250, 200, 130, 130);
		seed.setBounds(160, 240, 70, 70);
		seed2.setBounds(400,240,70,70);
		lbId.setBounds(240, 430, 50, 30);
		tfId.setBounds(300, 430, 120, 30);
		lbPwd.setBounds(220, 480, 70, 30);
		tfPwd.setBounds(300, 480, 120, 30);
		login.setBounds(280, 600, 80, 30);
		signUp.setBounds(270, 650, 100, 30);
		rPwd.setBounds(245, 700, 150, 30);

		// 컴포넌트 패널에 추가
		bGround.setLayout(null);
		bGround.add(name);
		bGround.add(ham);
		bGround.add(seed2);
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
				
				boolean result = uc.userLogin(id, pwd);	
				if(result == true) {
					jf.setVisible(false);
				}
				
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
