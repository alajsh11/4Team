package com.kh.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopUp extends JFrame implements ActionListener {
// 팝업 클래스 	
	SignUpView suv = new SignUpView();
	LoginView lv = new LoginView();

	// 로그인 성공 시 만들어지는 팝업
	public void loginSuccess() {
		setTitle("System");

		JPanel loginSuccess = new JPanel();
		setContentPane(loginSuccess);

		JLabel successLabel = new JLabel("로그인에 성공하였습니다.");
		loginSuccess.add(successLabel);

		// 확인 버튼 클릭 시 해당 팝업 종료
		JButton ok = new JButton("확인");
		loginSuccess.add(ok);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				lv.setVisible(false);
				new CalendarView();
			}
		});

		setSize(300, 100);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	// 로그인 실패시 만들어지는 새 창을 정의한 메소드
	public void loginFail() {
		setTitle("System");

		JPanel loginFail = new JPanel();
		setContentPane(loginFail);

		JLabel failLabel = new JLabel("아이디 또는 비밀번호가 잘못되었습니다.");
		loginFail.add(failLabel);

		// 확인 버튼 클릭 시 해당 팝업 종료
		JButton ok = new JButton("확인");
		loginFail.add(ok);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		setSize(300, 100);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// 회원가입 완료 메소드
	public void signUpOk() {
		setTitle("System");

		JPanel signUpOk = new JPanel();
		setContentPane(signUpOk);

		JLabel okLabel = new JLabel("회원가입이 완료되었습니다.");
		signUpOk.add(okLabel);

		// 확인 버튼 클릭 시 해당 팝업 종료
		JButton ok = new JButton("확인");
		signUpOk.add(ok);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				suv.setVisible(false);
				new LoginView();
			}
		});

		setSize(300, 100);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void idPass() {
		// 중복된 아이디가 없을 때
		setTitle("System");

		JPanel idPass = new JPanel();
		setContentPane(idPass);

		JLabel passLabel = new JLabel("사용 가능한 아이디입니다.");
		idPass.add(passLabel);

		JButton ok = new JButton("확인");
		idPass.add(ok);

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		setSize(300, 100);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void idFail() {
		// 중복된 아이디 있을 때
		setTitle("System");

		JPanel idFail = new JPanel();
		setContentPane(idFail);

		JLabel failLabel = new JLabel("중복된 아이디가 있습니다." + "\n" + "다른 아이디를 입력해주세요.");
		idFail.add(failLabel);

		JButton ok = new JButton("확인");
		idFail.add(ok);

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		setSize(300, 100);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void pwdDiffer() {
		// 회원가입 시 비밀번호를 다르게 입력했을 때
		setTitle("System");

		JPanel differ = new JPanel();
		setContentPane(differ);

		JLabel differLabel = new JLabel("비밀번호가 다릅니다." + "\n" + "비밀번호를 확인해주세요");
		differ.add(differLabel);

		JButton ok = new JButton("확인");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		setSize(300, 100);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
