package com.kh.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.kh.model.vo.User;
import com.kh.view.CalendarView;
import com.kh.view.MemberInformationView;

public class UserController {

	User us = new User();
	private int count;

	// 회원가입 유저 --> dat 파일에 저장

	public void userSignUp(String id, String pwd, String hint) {

		Date d = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
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
					array = line.split("/");
					s = array[0];
					count = Integer.valueOf(s) + 1;
				}
				if (file == null) {
					s = "0";
					count = Integer.valueOf(s);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		DecimalFormat df = new DecimalFormat("0000");		
		String uNum = String.valueOf(df.format(count));

		try {
			bw = new BufferedWriter(new FileWriter("User.dat", true));
			bw.write(uNum.toString() + "/");
			bw.write(id.toString() + "/");
			bw.write(pwd.toString() + "/");
			bw.write(hint.toString() + "/");
			bw.write(today + "/");
			bw.write(String.valueOf(0) + "/" + "\n");

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
					array = line.split("/");
					  if (id.equals(array[1])) {
		                  result = false;
		                  break;
		               }
		               else {
		                  result=true;
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
				array = line.split("/");
				if (id.equals(array[1]) && (pwd.equals(array[2]))) {
						JOptionPane.showMessageDialog(null, "로그인 되었습니다.");				
						User user = new User(array[0],id, pwd, array[3],array[4], Integer.valueOf(array[5]));
						if (array[0].equals("0000")) {
							new MemberInformationView();
						} else {
							new CalendarView(user);
						}
						result = true;
						break;
					} else if(!(id.equals(array[1])) && (!(pwd.equals(array[2]))) || 
							(id.equals(array[1])) && (!(pwd.equals(array[2])))) {
							result = false;			
					}
				}
			
			if(result == false) {
				JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 잘못되었습니다.", "Message", JOptionPane.ERROR_MESSAGE);
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

	// 회원 탈퇴
	public void userDelete(String id) {

		String line = "";
		String dummy = "";
		String array[];
		try {
			BufferedReader br = new BufferedReader(new FileReader("User.dat"));

			while ((line = br.readLine()) != null) {
				array = line.split("/");
				if (!(id.equals(array[1]))) {
					dummy += (line + "\n");
				}
			}

			FileWriter fw = new FileWriter("User.dat");
			fw.write(dummy);
			fw.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 비밀번호 재설정
	public boolean userRpwd(String id, String hint, String pwd) {

		boolean result = false;
		String line = "";
		String array[];

		try {
			BufferedReader br = new BufferedReader(new FileReader("User.dat"));

			while ((line = br.readLine()) != null) {
				array = line.split("/");
				if (id.equals(array[1]) && hint.equals(array[3])) {
					result = true;
					rUserInfo(id, hint, pwd);
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// User 파일 재생성
	public void rUserInfo(String id, String hint, String pwd) {

		String line = "";
		String array[];
		String dummy = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader("User.dat"));

			while ((line = br.readLine()) != null) {
				array = line.split("/");
				if (id.equals(array[1]) && hint.equals(array[3])) {
					for (int i = 0; i < array.length; i++) {
						if (i == 2) {
							dummy += (pwd + "/");
						} else if (i == 5) {
							dummy += (array[i] + "/" + "\n");
						} else {
							dummy += (array[i] + "/");
						}
					}
				} else {
					dummy += (line + "\n");
				
				}
			}
			FileWriter fw = new FileWriter("User.dat");
			fw.write(dummy);
			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 내 정보 조회로 값 return
	public String userSignDate(String id) {

		String line = "";
		String array[];
		String d = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader("User.dat"));
			while ((line = br.readLine()) != null) {
				array = line.split("/");
				if (id.equals(array[1])) {
					d = array[4];
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return d;
	}

	public String userSignHint(String id) {

		String line = "";
		String array[];
		String hint = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader("User.dat"));
			while ((line = br.readLine()) != null) {
				array = line.split("/");
				if (id.equals(array[1])) {
					hint = array[3];
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hint;
	}

	public void diaryCount(int count) {
		
		this.count = count;
	}
	
	public int retunrCount() {
	
	return count;
	}
}
