package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.UserDao;
import com.kh.model.vo.User;
import com.kh.view.MemberCheckView;
import com.kh.view.MemberInformationView;

public class AdministratorController {
	private MemberInformationView mi;
	private MemberCheckView mc ;
	private UserDao user =  new UserDao();
	private String[] idArr ;
	private String[] noArr ;
	
	
	//회원 정보 넣기
	public void memberTableInfo() {
		ArrayList<User> tableList = new ArrayList<>();
		user.userListUpdate();
		tableList.addAll(user.getList());
		
		idArr = new String[user.getList().size()];
		noArr = new String[user.getList().size()];
		System.err.println(user.getList().get(1).getDiaryCount());
		for(int i = 0; i < tableList.size(); i++ ) {
			noArr[i] = tableList.get(i).getuNo();
			idArr[i] = tableList.get(i).getuId();
		}
	}
	
	//id 불러오기
	public String[] getIdArr() {
		return idArr;
	}
	
	//회원 정보 불러오기
	public String[] getNoArr() {
		return noArr;
	}

	public UserDao getUser() {
		return user;
	}

	public void userDelete(User user) {
		this.user.userDelete(user);
	}
	
	
	
	

}
