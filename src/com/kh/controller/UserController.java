package com.kh.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.kh.model.vo.User;
import com.kh.view.CalendarView;
import com.kh.view.MemberInformationView;

public class UserController {

	User us = new User();

	private Date date;
	private String hint;
	private String id;

	// 회원가입 유저 --> dat 파일에 저장
	public void userId(String id) {
		this.id = id;

	}

	public void userSignUp(String id, String pwd, String hint) {

		Date d = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		String today = date.format(d);

		BufferedWriter bw = null;
		String s = "";
		String line = "";
		String array[];
		int count = 0;
		
		File file = new File("User.dat");
		if (file.exists()) {	
			try {
				BufferedReader br = new BufferedReader(new FileReader("User.dat"));
				while ((line = br.readLine()) != null) {
					array = line.split(",");
					s = array[0];
					count = Integer.valueOf(s) + 1 ;
				}
				if(file == null) {
				s = "0";
				count = Integer.valueOf(s);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		String uNum = String.valueOf(count);

		try {
			bw = new BufferedWriter(new FileWriter("User.dat", true));
			bw.write(uNum.toString() + ",");
			bw.write(id.toString() + ",");
			bw.write(pwd.toString() + ",");
			bw.write(hint.toString() + ",");
			bw.write(today + "\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		us.setuNo(String.valueOf(count));

	}

	// 회원가입 시 중복 아이디 비교
	public boolean userIdCompare(String id) {

		boolean result = false;
		String line = "";
		String array[];

		File file = new File("User.dat");
		if (file.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader("User.dat"));
				while ((line = br.readLine()) != null) {
					array = line.split(",");
					if (!id.equals(array[1])) {
						result = true;
						break;
					} else {
						result = false;
					}
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else {
			result = true;
		}

		return result;
	}

	// 로그인
	public boolean userLogin(String id, String pwd) {

		BufferedReader br = null;
		boolean result = false;
		String line = "";
		String array[];

		try {
			br = new BufferedReader(new FileReader("User.dat"));
			while ((line = br.readLine()) != null) {
				array = line.split(",");
				if (id.equals(array[1]) && pwd.equals(array[2])) {
					JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
					if (array[0].equals("0")) {
						new MemberInformationView();

					} else {
						new CalendarView(id);
					}
					result = true;

				} else if (id.equals(array[1]) && !pwd.equals(array[2])
						|| !id.equals(array[1])&& pwd.equals(array[2]))  {
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 잘못되었습니다.", "Message", JOptionPane.ERROR_MESSAGE);
				}
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;

	}

	// 유저 정보 return
	public void userInfo() {

		String line = "";
		String array[];

		try {
			BufferedReader br = new BufferedReader(new FileReader("User.dat"));
			while ((line = br.readLine()) != null) {
				array = line.split(",");
				if (id.equals(array[1])) {
					SimpleDateFormat sd = new SimpleDateFormat();
					date = sd.parse(array[4]);
					hint = array[3];
					userSignDate();
					userSignHint();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 회원 탈퇴
	public void userDelete() {

		String line = null;
		String dummy = null;
		String array[];
		try {
			BufferedReader br = new BufferedReader(new FileReader("User.dat"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("User.dat"));

			while ((line = br.readLine()) != null) {
				array = line.split(",");
				if (!id.equals(array[1])) {
					dummy += (line + "\n");
				}

			}
			bw.write(dummy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 비밀번호 재설정
	public boolean userRpwd(String id, String hint, String pwd) {

		boolean result = false;
		String line = "";
		String dummy = "";
		String array[];

		try {
			BufferedReader br = new BufferedReader(new FileReader("User.dat"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("User.dat"));

			while ((line = br.readLine()) != null) {
				array = line.split(",");
				if (id.equals(array[1]) && hint.equals(array[3])) {
					result = true;
					for (int i = 0; i < array.length; i++) {
						if (i == 2) {
							array[i] = pwd;
							bw.write(array[i] + ",");
						} else if (i == 4) {
							bw.write(array[i] + "\n");
						} else {
							bw.write(array[i] + ",");
						}
					}
					break;
				} else {
					dummy += (line + "\n");
					bw.write(dummy);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	// 내 정보 조회로 값 return
	public Date userSignDate() {

		return date;
	}

	public String userSignHint() {

		return hint;
	}

}
