package com.kh.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

import com.kh.controller.DiaryInformationController;
import com.kh.model.vo.Diary;

public class DiaryInformationView extends JFrame{

	public DiaryInformationView() {
		// TODO Auto-generated constructor stub
	}
	
	public DiaryInformationView(Diary d) {
		super("해씨 일기");
		DiaryInformationController dic= new DiaryInformationController();
		
		JPanel panel = new JPanel();
		JLabel image = new JLabel(); // 사진 붙여 넣을 화면
		JLabel dateBox = new JLabel(); // 날짜 창
		
		//수정
		ImageIcon icModify = new ImageIcon("Image/complete.png");
		Image imModify = icModify.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		
		JButton modifyBtn = new JButton(); 
		
		//이전
		ImageIcon icPrev =  new ImageIcon("Image/prev.png");
		Image imPrev = icPrev.getImage().getScaledInstance(40,35, Image.SCALE_SMOOTH);
		
		//삭제
		ImageIcon icDelete =  new ImageIcon("Image/delete.png");
		Image imDelete = icDelete.getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
		
		JButton prevBtn = new JButton(); 
		JButton deleteBtn = new JButton(); 

		JTextArea txtField = new JTextArea(); // 내용 작성 텍스트 필드
		
		//프레임 설정
		this.setSize(640, 960);//전체 창 사이즈
		this.setLayout(null);
		this.setLocationRelativeTo(null); // 창 가운데로 켜지게 설정
		
		//패널 설정
		panel.setSize(getMaximumSize());
		panel.setLayout(null);
		panel.setBackground(new Color(0xddc6e6)); // 배경색은 패널에 지정
		
		
		// date 박스 폰트 설정
		dateBox.setFont(new Font("serif", Font.BOLD, 20));
		dateBox.setForeground(Color.BLACK);
		dateBox.setText(d.getdDate());
		
		//image label 테두리 설정
		BevelBorder border = new BevelBorder(BevelBorder.RAISED);
		image.setBorder(border);
		image.setBackground(Color.WHITE);
		
		ImageIcon icon =  new ImageIcon(d.getdImgName());
		Image img = icon.getImage().getScaledInstance(400,300, Image.SCALE_SMOOTH);
		image.setIcon(new ImageIcon(img)); 
		
		
		
		// 이전버튼 아이콘 바꿔서 설정하기 위한 코드
		prevBtn.setBorderPainted(false); 
		prevBtn.setFocusPainted(false); 
		prevBtn.setContentAreaFilled(false);
		prevBtn.setLayout(null);
		prevBtn.setIcon(new ImageIcon(imPrev));
				

		// 수정버튼 아이콘 바꿔서 설정하기 위한 코드
		modifyBtn.setBorderPainted(false); 
		modifyBtn.setContentAreaFilled(false);
		modifyBtn.setLayout(null);
		modifyBtn.setIcon(new ImageIcon(imModify));
		
		deleteBtn.setBorderPainted(false); 
		deleteBtn.setContentAreaFilled(false);
		deleteBtn.setLayout(null);
		deleteBtn.setIcon(new ImageIcon(imDelete));
		
		txtField.setEditable(false);
		if(d.getDhashTag()==null) {
			System.out.println("null이다....왜...");
		}
		else {
			txtField.setText(dic.arrayListToText(d.getDhashTag()));
		}
		
		
		// 컴포넌트 사이즈 설정
		dateBox.setBounds(110,125,70,40); 
		prevBtn.setBounds(45,85,90,35);
		txtField.setBounds(110,500,400,300);
		image.setBounds(110,180,400,300);
		modifyBtn.setBounds(475,845,40,40);
		deleteBtn.setBounds(430,845,35,35);
		
		
		
		
		//컴포넌트 패널에 붙이기 
		panel.add(dateBox); 
		panel.add(prevBtn);
		panel.add(image); 
		panel.add(txtField); 
		panel.add(modifyBtn); 
		panel.add(deleteBtn);
		
		
		this.add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	
}
