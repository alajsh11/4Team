package com.kh.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.kh.model.vo.Diary;
import com.kh.view.DiaryInformationView;

public class DiaryController {

	Diary d = new Diary();

	public DiaryController() {

	}

	// 작성된 내용 따로 빼기
	public ArrayList<String> hashtagTokenizer(String content) {

		ArrayList<String> hashTagList = new ArrayList<String>();

		StringTokenizer st = new StringTokenizer(content, "#"); // 매개변수로 받은 content를 # 기준으로 분리

		while (st.hasMoreTokens()) {

			hashTagList.add(st.nextToken()); // #를 기준으로 빼서 정렬한 값을 다시 completeContent에 넣는다.
		}

		return hashTagList;

	}

	public void diaryRead(String userId, String date) {
		Diary d = openFile(userId+"/"+date+".dat");
		new DiaryInformationView(userId,d);
	}

	public Diary openFile(String path) {
		Diary diary = null;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));) {
			try {
				diary = (Diary) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(diary.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return diary;
	}

	public String arrayListToText(ArrayList<String> hash) {
		String content = "";
		for (String h : hash) {
			content += ("#" + h + " ");
		}
		return content;

	}
}
