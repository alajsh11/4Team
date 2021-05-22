package com.kh.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import com.kh.model.vo.Diary;
import com.kh.view.DiaryInformationView;

public class DiaryController {

	

	public DiaryController() {

	}

	
	// 작성된 content ArrayList로 생성
	public ArrayList<String> hashtagTokenizer(String content) {

		ArrayList<String> hashTagList = new ArrayList<String>();

		StringTokenizer st = new StringTokenizer(content, "#"); // 매개변수로 받은 content를 # 기준으로 분리

		while (st.hasMoreTokens()) {

			hashTagList.add(st.nextToken() + " "); // #를 기준으로 빼서 정렬한 값을 다시 completeContent에 넣는다.
		}

		return hashTagList;

	}

	
	// 유저 아이디 폴더 생성
	public void createFolder(String uId) {
		
		String Folder = uId;

		File folder = new File(Folder);

		folder.mkdir();
		
	}
	
	// 해당 유저 폴더에 날짜별 파일 만들기
	public void saveDiary(String uId, String date, ArrayList<String> content) {
		
		 Diary d = new Diary();
		 
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(uId + "\\" + date + ".dat"));) {
				
			d.setdImgName(uId + "\\" + date + ".png"); // d 객체에 이미지 경로 세팅
			d.setdDate(date); // d 객체에 date 세팅
			d.setDhashTag(content); // // d 객체에 해쉬태그 리스트에 세팅
			
			oos.writeObject(d); // 날짜, 이미지경로, 해시태그 diary 객체 저장
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 이미지 저장
	public void saveImage(String uId, String date, String absoluteFilePath) {
		try {
			//저장할 원본이 있는 경로를 가져와서 
			File file = new File(absoluteFilePath);
		
			// 해당 파일을 읽어서 
			BufferedImage saveImage = ImageIO.read(file); 
			ImageIO.write(saveImage, "png", new File(uId + "\\" + date + ".png")); //저장
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
	
	
	public void diaryRead(String userId, String date,String searchText,int flag) {
		Diary d = openFile(userId+"/"+date+".dat");
		new DiaryInformationView(userId,d,searchText,flag);
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
