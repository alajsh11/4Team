package com.kh.model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.kh.model.vo.User;

public class UserDao { // User.dat 이용해서 User 객체화

	private ArrayList<User> list = new ArrayList<User>();

	public ArrayList<User> getList() { // list 반환 (getter)
		return list;
	}

	// 다이어리 개수 수정
	public void userDiaryCountTemp(User user, int diaryCount) { // 유저 정보와 일기 개수 받아서 수정하기

		FileInputStream fi = null;
		FileOutputStream fo = null;
		BufferedReader br = null;
		BufferedWriter bw = null;

		System.out.println("user!!" + user);

		File inputFile = new File("User.dat"); // 읽어올 파일
		File outputFile = new File("User.dat" + ".temp"); // 수정할 파일
		String count = diaryCount + "";
		boolean result = false;
		try {
			fi = new FileInputStream(inputFile);
			fo = new FileOutputStream(outputFile);
			br = new BufferedReader(new InputStreamReader(fi));
			bw = new BufferedWriter(new OutputStreamWriter(fo));

			String line = ""; // 바꿀라인
			String repLine = ""; // 새로운 라인

			String originStr = user.getuNo() + "/" + user.getuId() + "/" + user.getuPwd() + "/" + user.getuPwdAnswer()
					+ "/" + user.getuDate() + "/" + user.getDiaryCount() + "/";
			String reStr = user.getuNo() + "/" + user.getuId() + "/" + user.getuPwd() + "/" + user.getuPwdAnswer() + "/"
					+ user.getuDate() + "/" + count + "/";

			System.out.println(originStr + "  " + reStr);
			user.setDiaryCount(diaryCount);
			while ((line = br.readLine()) != null) {
				repLine = line.replace(originStr, reStr); // 기존 문자열(originStr)과 새로운 문자열(reStr)교체
				bw.write(repLine, 0, repLine.length());
				bw.newLine(); // 줄바꿈
			}
			result = true;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (result) {
				inputFile.delete(); // 기존 파일 삭제
				outputFile.renameTo(new File("User.dat")); // 새로운 파일 이름 교체
			}
		}

	}

	public void userDelete(User user) { // 유저 정보 받아서 User.dat 정보 삭제
		FileInputStream fi = null;
		FileOutputStream fo = null;
		BufferedReader br = null;
		BufferedWriter bw = null;

		File inputFile = new File("User.dat"); // 읽어올 파일
		File outputFile = new File("User.dat" + ".temp"); // 수정할 파일

		boolean result = false;
		try {
			fi = new FileInputStream(inputFile);
			fo = new FileOutputStream(outputFile);
			br = new BufferedReader(new InputStreamReader(fi));
			bw = new BufferedWriter(new OutputStreamWriter(fo));

			String line = ""; // 바꿀라인

			String originStr = user.getuNo() + "/" + user.getuId() + "/" + user.getuPwd() + "/" + user.getuPwdAnswer()
					+ "/" + user.getuDate() + "/" + user.getDiaryCount() + "/";

			while ((line = br.readLine()) != null) {

				if (!(originStr.equals(line))) {
					bw.write(line);
					bw.newLine(); // 줄바꿈
				}
			}
			result = true;

			File folder = new File(user.getuId());

			if (folder.exists()) {

				File[] folderList = folder.listFiles();

				for (int i = 0; i < folderList.length; i++) {
					folderList[i].delete();
				}

					folder.delete();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (result) {
				inputFile.delete(); // 기존 파일 삭제
				outputFile.renameTo(new File("User.dat")); // 새로운 파일 이름 교체
			}
		}

	}

	public void userListUpdate() { // User.dat파일 정보 이용해서 list객체화 메소드
		FileReader fw = null;
		StringTokenizer st;
		String a = "";
		list.clear(); // 초기화

		try {
			// User.dat 내용 String a에 넣기
			fw = new FileReader("User.dat");
			int value = 0;
			while ((value = fw.read()) != -1) {
				a += String.valueOf((char) value);
			}

			// String a 내용 한줄씩 나눈후 arr[] 배열에 대입
			st = new StringTokenizer(a, "\n");
			ArrayList<String> strList = new ArrayList<>();

			value = 0;
			String asd;
			while (st.hasMoreTokens()) {
				asd = st.nextToken();
				if (asd.length() > 5)
					strList.add(asd); // 빈칸 무시(삭제한 유저정보 담지않기)
			}

			// arr[] 배열 길이만큼 반복시켜 list 추가하기
			for (int listSize = 0; listSize < strList.size(); listSize++) {
				String id = "";
				String pwd = "";
				String pwdAnswer = "";
				String no = "";
				String date = "";
				// arr[listSize] 에 , 기준으로 나눈후 strArr[]에 대입
				st = new StringTokenizer(strList.get(listSize), "/");
				String[] strArr = new String[st.countTokens()];
				value = 0;
				while (st.hasMoreTokens()) {
					strArr[value++] = st.nextToken();

				}

				// 유저 번호 값
				for (int i = 0; i < strArr[0].length(); i++) {
					no += strArr[0].charAt(i);
				}

				// 유저 아이디 값
				for (int i = 0; i < strArr[1].length(); i++) {
					id += strArr[1].charAt(i);
				}

				// 유저 비밀번호 값
				for (int i = 0; i < strArr[2].length(); i++) {
					pwd += strArr[2].charAt(i);
				}

				// 유저 비밀번호 힌트값
				for (int i = 0; i < strArr[3].length(); i++) {
					pwdAnswer += strArr[3].charAt(i);
				}

				// 유저 가입 날짜
				for (int i = 0; i < strArr[4].length(); i++) {
					date += strArr[4].charAt(i);
				}
				// 총 일기 계수 구하기

				String dc = "";

				for (int i = 0; i < strArr[5].length(); i++) {
					dc += strArr[5].charAt(i);
				}
				int dCount = Integer.parseInt(dc); // 일기계수 int 형변환

				// 위 값을 이용해 list에 넣을 uUser 생성
				User uUser = new User(no, id, pwd, pwdAnswer, date, dCount);

				list.add(uUser); // 리스트 추가
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
