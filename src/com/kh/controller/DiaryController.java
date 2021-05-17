package com.kh.controller;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.kh.model.vo.Diary;

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
}
