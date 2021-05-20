package com.kh.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.kh.model.vo.User;
import com.kh.view.PopUp;

public class UserController {

	User us = new User();

	// 관리자 초기화
	ArrayList<User> user = new ArrayList<User>();
	{
		BufferedWriter bw = null;

		user.add(new User("01012341234/", "1234/", "강남초등학교"));
		try {
			bw = new BufferedWriter(new FileWriter("User.dat"));
			bw.write((us.getuNo() + 1) + user.toString() + "\n");

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// 회원가입 유저 --> dat 파일에 저장
	public void userSignUp(String id, String pwd, String hint) {

		BufferedWriter bw = null;
		
		try {
			bw = new BufferedWriter(new FileWriter("User.dat"));

			bw.write(id + "/");
			bw.write(pwd + "/");
			bw.write(hint + "\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		user.add(new User(id + "/", pwd + "/", hint));
		us.setuNo(us.getuNo() + 1);

	}

	// 회원가입 시 중복 아이디 비교
	public boolean userIdCompare(String id) {


		boolean result = true;
		BufferedReader br = null;
		String line = "";
		String array[];

		try {
			br = new BufferedReader(new FileReader("User.dat"));

			while ((line = br.readLine()) != null) {
				array = line.split("/");
				if (id.equals(array[1])) {
					result = false;
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return result;
	}

	
	// 로그인 성공 
	public boolean userLogin(String id, String pwd) {
		
		boolean result = false;
		BufferedReader br = null;
		String line = "";
		String array[];

		try {
			br = new BufferedReader(new FileReader("User.dat"));

			while ((line = br.readLine()) != null) {
				array = line.split("/");
				if (id.equals(array[1]) && pwd.equals(array[2])) {
					result = true;
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return result;
	}

}
