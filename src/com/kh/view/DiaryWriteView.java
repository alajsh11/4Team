package com.kh.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

import com.kh.controller.DiaryController;
import com.kh.model.vo.Diary;

public class DiaryWriteView extends JFrame  {

	Diary d = new Diary();
	DiaryController dc = new DiaryController();
	
	JPanel panel = new JPanel();
	
	JLabel image = new JLabel(); // 사진 붙여 넣을 화면

	String date = d.getdDate().toString();
	// NullPointerException > 객체 생성후 다시! 
	
	JLabel dateBox = new JLabel(date); // 날짜 창
		
	// 사진 붙이기 버튼
	ImageIcon icPlus =  new ImageIcon("Image/seed1.png");
	Image imPlus = icPlus.getImage().getScaledInstance(42, 35, Image.SCALE_SMOOTH);
	
	JButton plus = new JButton(); 
	
	// 저장 버튼
	ImageIcon icSave =  new ImageIcon("Image/save.png");
	Image imSave = icSave.getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
	
	JButton save = new JButton(); 
	
	
	// 이전창 버튼
	ImageIcon icPrev =  new ImageIcon("Image/prev.png");
	Image imPrev = icPrev.getImage().getScaledInstance(40,35, Image.SCALE_SMOOTH);
	
	JButton prev = new JButton(); // 이전 버튼(작성 > 달력)
	
	JTextArea write = new JTextArea(); // 내용 작성 텍스트 필드

	JFileChooser chooser = new JFileChooser(); // 파일 오픈창
		
	String content = "";

	
	
	public DiaryWriteView() {
		
		
		super("해씨 일기");
		
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
		
		
	
		 //image label 테두리 설정
		BevelBorder border = new BevelBorder(BevelBorder.RAISED);
		image.setBorder(border);
		image.setBackground(Color.WHITE);
		
		
		// 사진 더하기 아이콘 바꿔서 설정하기 위한 코드
		plus.setBorderPainted(false); 
		plus.setFocusPainted(false); 
		plus.setContentAreaFilled(false);
		plus.setLayout(null);
		plus.setIcon(new ImageIcon(imPlus));
		
		
		// 이전버튼 아이콘 바꿔서 설정하기 위한 코드
		prev.setBorderPainted(false); 
		prev.setFocusPainted(false); 
		prev.setContentAreaFilled(false);
		prev.setLayout(null);
		prev.setIcon(new ImageIcon(imPrev));
		
		
		// 저장버튼 아이콘 바꿔서 설정하기 위한 코드
		save.setBorderPainted(false); 
		save.setFocusPainted(false); 
		save.setContentAreaFilled(false);
		save.setLayout(null);
		save.setIcon(new ImageIcon(imSave));
				
		
		
		// 컴포넌트 사이즈 설정
		dateBox.setBounds(110,125,70,40); 
		prev.setBounds(45,85,90,35);
		write.setBounds(110,500,400,300);
		image.setBounds(110,180,400,300);
		plus.setBounds(190,125,42,42); // 280, 260
		save.setBounds(480,845,35,35);
		
		
		//컴포넌트 패널에 붙이기 
		panel.add(dateBox); 		
		panel.add(prev);
		panel.add(plus); 
		panel.add(image); 
		panel.add(write); 
		panel.add(save); 
		
		
		this.add(panel); // 컴포넌트를 붙인 패널을 프레임에 붙인다.
		
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		// 이전 버튼 (작성페이지 > 달력페이지)
		prev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//new CalendarView(); // 달력페이지로 가고
				setVisible(false); // 이전페이지로 이동 후 현재 페이지 안보이게 설정
				
			}
			
		});
	
		
			
		//사진 선택 버튼
		plus.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == plus) {
					if (chooser.showOpenDialog(image) == JFileChooser.APPROVE_OPTION) {
						
						d.setdImgName(chooser.getSelectedFile().getPath());  // 이미지 경로를 diary dImgName에 설정하기
						
						//라벨 이미지 비율 유지 
						ImageIcon icon =  new ImageIcon(d.getdImgName()); // 이미지를 이미지 아이콘으로 변경
						
						Image img = icon.getImage().getScaledInstance(400,300, Image.SCALE_SMOOTH); // 이미지로 사이즈 조정
						
						image.setIcon(new ImageIcon(img)); // 해당 이미지 라벨에 붙이기
						
						
						
					} else { // 사진 선택하지 않고 창을 닫으면 사진을 선택하지 않았습니다. 경고창
						JOptionPane.showMessageDialog(null, "사진을 선택하지 않았습니다.", "", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
			}
		});
		
		
		while (true) {

			String str = write.getText(); // text 필드에서 넣은 값을 담는다.

			if (content.length() + str.length() < 100) { // 내용은 100자 제한이므로 100자 이하일때만 content에 담긴다.

				content += str;
				

			} else {
				break;
			}
		}
		
		
		// 저장 버튼
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(date + ".dat")); //해당 날짜의 파일로 저장
					
				
					bw.write(d.getdImgName()); // 이미지 경로 저장
					
					
					d.setDhashTag(dc.hashtagTokenizer(content)); //ArrayList를 해쉬태그 리스트에 세팅
					
					
					bw.write(content); // 해시태그 내용 저장
					
					JOptionPane.showMessageDialog(null, "일기가 저장되었습니다.", "", JOptionPane.WARNING_MESSAGE);

					
				} catch (IOException a) {
					a.printStackTrace();
				}
				
			}
			
		});
		
	}
	
}
