package com.kh.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.kh.model.vo.User;

public class UserDao {	// User.dat 이용해서 User 객체화
	
	private ArrayList<User> list = new ArrayList<User>();

	
	public ArrayList<User> getList() {	//list 반환 (getter)
		return list;
	}
	
	public void userDelete(String no) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getuNo().equals(no)) {
				list.remove(i);
			}
			
		}
	}

	
	public void userListUpdate() {	//User.dat파일 정보 이용해서 list객체화 메소드
		FileReader fw = null;
		StringTokenizer st;
		String a = "";
		list.clear();	// 초기화
		
		try {
			// User.dat 내용 String a에 넣기
			fw = new FileReader("User.dat");
			int value = 0;
			while ((value = fw.read()) != -1) {
				a += String.valueOf((char) value);
			}
			
			// String a 내용 한줄씩 나눈후 arr[] 배열에 대입
			st = new StringTokenizer(a, "\n");
			String[] arr = new String[st.countTokens()];

			value = 0;
			while (st.hasMoreTokens()) {
				arr[value++] = st.nextToken();
			}
			
			
			// arr[] 배열 길이만큼 반복시켜 list 추가하기
			for (int listSize = 0; listSize < arr.length; listSize++) {
				String id = "";
				String pwd = "";
				String pwdAnswer = "";
				String no = "";
				
				//arr[listSize] 에 , 기준으로 나눈후 strArr[]에 대입
				st = new StringTokenizer(arr[listSize], ",");
				String[] strArr = new String[st.countTokens()];
				value = 0;
				while (st.hasMoreTokens()) {
					strArr[value++] = st.nextToken();
					
				}
				
				//유저 번호 값
				for (int i = 5; i < strArr[1].length(); i++) {
					no += strArr[1].charAt(i);
				}
				
				//유저 아이디 값
				for (int i = 5; i < strArr[2].length() - 1; i++) {
					id += strArr[2].charAt(i);
				}
				
				//유저 비밀번호 값
				for (int i = 6; i < strArr[3].length() - 1; i++) {
					pwd += strArr[3].charAt(i);
				}
				
				//유저 비밀번호 힌트값
				for (int i = 12; i < strArr[4].length() - 2; i++) {
					pwdAnswer += strArr[4].charAt(i);
				}
				
				
				//총 일기 계수 구하기
				String dc = strArr[0];
				st = new StringTokenizer(dc, "=");

				dc = "";
				while (st.hasMoreTokens()) {

					dc = st.nextToken();

				}
				int dCount = Integer.parseInt(dc);	// 일기계수 int 형변환
				
				// 위 값을 이용해 list에 넣을  uUser 생성
				User uUser = new User(id, pwd, pwdAnswer);
				uUser.setDiaryCount(dCount);
				uUser.setuNo(no);
				list.add(uUser);	// 리스트 추가
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
