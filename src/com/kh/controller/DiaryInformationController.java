package com.kh.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.kh.model.vo.Diary;
import com.kh.view.DiaryInformationView;

public class DiaryInformationController {
	private Diary diary = new Diary();
	public DiaryInformationController() {}
		

	public void diaryRead(String path) {
		Diary d = openFile(path);
		new DiaryInformationView(d);
	}
	public Diary openFile(String path) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));) {
			try {
				diary=(Diary)ois.readObject();
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
		String content="";
		for(String h : hash) {
			content+=("#"+h+" ");
		}
		return content;
		
	}
}
