package com.kh.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import com.kh.model.vo.Diary;


public class SearchController {
	public ArrayList<Diary> searchDiary(String uId, String searchText) { //검색어가 포함된 객체를 찾는 메소드

		File dir = new File(uId);
		File files[] = dir.listFiles();
		ArrayList<Diary> diaryList = new ArrayList<Diary>();
		
		if(searchText!=null && dir.exists()) { //해당 폴더가 있는지 확인 있으면
			for (File diary : files) {
				if(diary!=null && diary.getName().contains(".dat")) { //파일확장자가 dat인것만
					System.out.println(diary.getName());
					try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(diary));) {
						Diary d = (Diary) ois.readObject();
						for (String hash : d.getDhashTag()) {
							if (hash.contains(searchText)) { //검색어가 포함된 해시태그가 있으면 객체를 리스트에 추가
								diaryList.add(d);
								System.out.println(d);
								break;
							}
						}

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}

			}
		}

		return diaryList;
	}

	
	//다이어리 객체의 해시태그 리스트에 검색어가 포함된 해시태그들을 결과로 보내줌
	public String searchHashText(Diary diary,String searchText) {
		String result="";
		for (String hash : diary.getDhashTag()) {
			if(hash.contains(searchText)) {
				result+=("#"+hash+" ");
			}
		}
		return result;
	}
	

	
}
