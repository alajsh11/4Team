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
import javax.swing.JTextField;

import com.kh.controller.UserController;

public class UserPwdView {
	
	private UserController uc = new UserController();
	
	private String userId;
	private String userHint;
	private String userRpwd;
	
	public UserPwdView() {
		
		JFrame jf = new JFrame();
		
		jf.setTitle("비밀번호 재설정");
		jf.setSize(640, 960);
		jf.setLayout(null);
		jf.getContentPane().setLayout(null);
		jf.setResizable(false); // 창 크기 수정방지
		jf.setLocationRelativeTo(null); // 창이 가운데 나오게
		
		// 배경 패널
		JPanel rPwd = new JPanel();
		rPwd.setSize(640, 960);
		rPwd.setBackground(new Color(221, 198, 230));

		jf.add(rPwd);
		
		Image hamster = new ImageIcon("image/hamster3.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);;
		JLabel ham = new JLabel();
		ham.setIcon(new ImageIcon(hamster));
		
		JLabel title = new JLabel(" 비밀번호 재설정입니다.");
		title.setOpaque(true);
		title.setBackground(Color.white);
		title.setFont(new Font("맑은고딕", Font.BOLD, 17));
		title.setForeground(Color.gray);
		
		JLabel id = new JLabel(" 아이디( - 를 제외한 핸드폰 번호)");
		id.setOpaque(true);
		id.setBackground(Color.white);
		id.setFont(new Font("맑은고딕", Font.BOLD, 15));
		
		JTextField tx = new JTextField();
		tx.setOpaque(true);
		tx.setBackground(Color.LIGHT_GRAY);
		tx.setFont(new Font("맑은고딕", Font.PLAIN, 15));
		
		JLabel hint = new JLabel(" 비밀번호 힌트 (졸업한 초등학교 이름)");
		hint.setOpaque(true);
		hint.setBackground(Color.white);
		hint.setFont(new Font("맑은고딕",Font.BOLD,15));
		
		JTextField hi = new JTextField();
		hi.setOpaque(true);
		hi.setBackground(Color.LIGHT_GRAY);
		hi.setFont(new Font("맑은고딕",Font.PLAIN,15));
		
		JLabel r = new JLabel(" 새로운 비밀번호");
		r.setOpaque(true);
		r.setBackground(Color.white);
		r.setFont(new Font("맑은고딕", Font.BOLD, 15));
		
		JTextField rp = new JTextField();
		rp.setOpaque(true);
		rp.setBackground(Color.LIGHT_GRAY);
		rp.setFont(new Font("맑은고딕", Font.PLAIN, 15));
		
		JButton cancel = new JButton("취소");
		cancel.setFont(new Font("맑은고딕",Font.PLAIN,15));
		JButton ok = new JButton("확인");
		ok.setFont(new Font("맑은고딕",Font.PLAIN,15));
		
		// 컴포넌트 위치 및 사이즈  
		ham.setBounds(110,80,100,100);
		title.setBounds(220, 120, 300, 40);
		id.setBounds(120, 240, 400, 30);
		tx.setBounds(120, 270, 400, 30);
		hint.setBounds(120, 390, 400, 30);
		hi.setBounds(120, 420, 400, 30);
		r.setBounds(120, 540, 400, 30);
		rp.setBounds(120, 570, 400, 30);
		cancel.setBounds(220, 700, 80, 30);
		ok.setBounds(330, 700, 80, 30);
		
		// 패널에 추가
		rPwd.setLayout(null);
		rPwd.add(title);
		rPwd.add(id);
		rPwd.add(tx);
		rPwd.add(hint);
		rPwd.add(hi);
		rPwd.add(r);
		rPwd.add(rp);
		rPwd.add(cancel);
		rPwd.add(ok);
		rPwd.add(ham);
		
		
		// 버튼 액션
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "비밀번호 재설정이 취소되었습니다.");
				jf.setVisible(false);
				new LoginView();
				
			}
		});
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				userId = tx.getText();
				userHint = hi.getText();
				userRpwd = rp.getText();

				boolean result = uc.userRpwd(userId, userHint, userRpwd);

				if (result == true) {
					JOptionPane.showMessageDialog(null, "비밀번호가 재설정되었습니다.");
					jf.setVisible(false);
					new LoginView();
				} else if (result == false) {
					JOptionPane.showMessageDialog(null, "아이디와 힌트가 맞지 않습니다.");
				}
			}
		});
		
		
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
		
	}

}

