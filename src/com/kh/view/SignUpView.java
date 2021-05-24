package com.kh.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kh.controller.UserController;

public class SignUpView implements ActionListener {

	UserController uc = new UserController();
	
	private String signId;
	private String signPwd;
	private String signRpwd;
	private String signHint;

	public SignUpView() {

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

		// 배경 패널
		JPanel signUp = new JPanel();
		signUp.setSize(640, 960);
		signUp.setBackground(new Color(221, 198, 230));

		jf.add(signUp);

		// 타이틀 패널
		JPanel titleBack = new JPanel();
		
		// 타이틀 라벨
		JLabel title = new JLabel("회원가입");
		title.setFont(new Font("맑은고딕", Font.BOLD, 25));
		title.setForeground(Color.darkGray);
		//title.setBounds(225, 80, 250, 50);

		//signUp.add(title);
		titleBack.add(title);


		// 아이디
		JLabel lbId = new JLabel("아이디를 입력해주세요");
		lbId.setFont(new Font("맑은고딕", Font.PLAIN, 17));
		lbId.setForeground(Color.DARK_GRAY);
		JLabel phone = new JLabel("( -를 제외한 휴대폰 번호 )");
		phone.setFont(new Font("맑은고딕", Font.PLAIN, 15));
		phone.setForeground(Color.DARK_GRAY);
		
		JTextField id = new JTextField();
			
		JButton overlap = new JButton("중복확인");
		overlap.setFont(new Font("맑은고딕",Font.PLAIN,15));
		
		
		
		// 비밀번호
		JLabel lbPwd = new JLabel("비밀번호를 입력해주세요");
		lbPwd.setFont(new Font("맑은고딕", Font.PLAIN, 17));
		lbPwd.setForeground(Color.DARK_GRAY);
		
		JPasswordField pwd = new JPasswordField();

		

		// 비밀번호 재입력
		JLabel lbRpwd = new JLabel("비밀번호를 한 번 더 입력해주세요");
		lbRpwd.setFont(new Font("맑은고딕", Font.PLAIN, 17));
		lbRpwd.setForeground(Color.DARK_GRAY);
		
		JPasswordField rpwd = new JPasswordField();
		
		
		// 비밀번호 힌트 입력 
		JLabel lbHint1 = new JLabel("당신의 출신 초등학교는?");
		lbHint1.setFont(new Font("맑은고딕", Font.PLAIN, 17));
		lbHint1.setForeground(Color.DARK_GRAY);
					
		JLabel lbHint2 = new JLabel("(비밀번호 재설정시 사용될 내용입니다.)");
		lbHint2.setFont(new Font("맑은고딕", Font.PLAIN, 15));
		lbHint2.setForeground(Color.DARK_GRAY);
				
		JTextField hint = new JTextField();
		
		
		// 회원가입, 취소
		JButton cancel = new JButton("취소");
		JButton ok = new JButton("회원가입");
		cancel.setFont(new Font("맑은고딕",Font.PLAIN,15));
		ok.setFont(new Font("맑은고딕",Font.PLAIN,15));
		
		
		// 컴포넌트 위치 및 사이즈
		titleBack.setBounds(210, 80, 200, 50);
		lbId.setBounds(120, 210, 300, 20);
		phone.setBounds(300, 210, 300, 20);
		id.setBounds(120, 240, 370, 40);
		overlap.setBounds(500, 240, 90, 40);
		lbPwd.setBounds(120, 320, 300, 20);		
		pwd.setBounds(120, 350, 370, 40);
		lbRpwd.setBounds(120, 430, 300, 20);
		rpwd.setBounds(120,460,370,40);
		lbHint1.setBounds(120, 540, 300, 20);
		lbHint2.setBounds(300, 540, 300, 20);
		hint.setBounds(120, 570, 370, 40);
		cancel.setBounds(220, 700, 90, 30);
		ok.setBounds(330, 700, 90, 30);
		
		// 컴포넌트 패널에 추가 
		signUp.setLayout(null);
		signUp.add(titleBack);
		signUp.add(lbId);
		signUp.add(phone);
		signUp.add(id);
		signUp.add(overlap);
		signUp.add(lbPwd);
		signUp.add(pwd);
		signUp.add(lbRpwd);
		signUp.add(rpwd);
		signUp.add(lbHint1);
		signUp.add(lbHint2);
		signUp.add(hint);
		signUp.add(cancel);
		signUp.add(ok);
		
		
		// 버튼액션
		
		// 회원가입 취소 
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);
				new LoginView();
				
			}
		});

	
		
		// 아이디 중복확인 
			
		overlap.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {	
			signId = id.getText();
			boolean result = uc.userIdCompare(signId);
				if(result == true) {
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
					
				}else if(result == false) {
					JOptionPane.showMessageDialog(null, "중복된 아이디가 있습니다.","Message",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});

		// 회원가입 
		ok.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 회원가입 완료 시 
				
				 signId = id.getText();
				 signPwd = pwd.getText();
				 signRpwd = rpwd.getText();
				 signHint = hint.getText();
				 
				if (signPwd.equals(signRpwd)) {
					
					uc.userSignUp(signId, signPwd, signHint);
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
					jf.setVisible(false);
					new LoginView();
										
				// 비밀번호가 다를 시 
				} else if(!signPwd.equals(signRpwd)) {
					JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다." + "\n" + "다시 입력해주세요","Message",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
