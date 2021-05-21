package com.kh.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.kh.model.vo.User;

public class UserDao {
	ArrayList<User> list = new ArrayList<User>();

	public void print1() {
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

	}

	public void userList() {
		FileReader fw = null;
		StringTokenizer st;
		String a = "";

		try {
			fw = new FileReader("User.dat");
			int value = 0;
			while ((value = fw.read()) != -1) {
				a += String.valueOf((char) value);
			}

			st = new StringTokenizer(a, "\n");
			String[] arr = new String[st.countTokens()];

			value = 0;
			while (st.hasMoreTokens()) {
				arr[value++] = st.nextToken();
			}

			for (int listSize = 0; listSize < arr.length; listSize++) {
				String id = "";
				String pwd = "";
				String pwdAnswer = "";
				String no = "";
				
				
				
				st = new StringTokenizer(arr[listSize], ",");
				String[] strArr = new String[st.countTokens()];
				value = 0;
				while (st.hasMoreTokens()) {
					strArr[value++] = st.nextToken();
					
				}

				for (int i = 5; i < strArr[1].length(); i++) {
					no += strArr[1].charAt(i);
				}

				for (int i = 5; i < strArr[2].length() - 1; i++) {
					id += strArr[2].charAt(i);
				}

				for (int i = 6; i < strArr[3].length() - 1; i++) {
					pwd += strArr[3].charAt(i);
				}

				for (int i = 12; i < strArr[4].length() - 1; i++) {
					pwdAnswer += strArr[4].charAt(i);
				}

				String dc = strArr[0];
				st = new StringTokenizer(dc, "=");

				dc = "";
				while (st.hasMoreTokens()) {

					dc = st.nextToken();

				}
				int dCount = Integer.parseInt(dc);

				User uUser = new User(id, pwd, pwdAnswer);
				uUser.setDiaryCount(dCount);
				uUser.setuNo(no);
				// System.out.println(uUser);
				list.add(uUser);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("파일없음");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
