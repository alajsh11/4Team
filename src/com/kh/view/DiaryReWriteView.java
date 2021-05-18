package com.kh.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
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
import com.kh.model.vo.User;

public class DiaryReWriteView extends JFrame {

	public DiaryReWriteView() {
		
	}
	
	public DiaryReWriteView(String date, String uId) {
		
	super("해씨 일기");
	
	Diary d = new Diary();
	DiaryController dc = new DiaryController();
	

	JPanel panel = new JPanel();
	
	JLabel image = new JLabel(); // 사진 붙여 넣을 화면
	
	
	// 날짜 창
	JLabel dateBox = new JLabel(date); 
	
	// 사진수정 버튼
	ImageIcon icPlus = new ImageIcon("Image/seed1.png");
	Image imPlus = icPlus.getImage().getScaledInstance(42, 35, Image.SCALE_SMOOTH);
	JButton plus = new JButton(); 
	
	
	// 수정완료 버튼
	ImageIcon icModify = new ImageIcon("Image/complete.png");
	Image imModify = icModify.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	JButton modify = new JButton(); 
	
	
	// 이전 버튼
	ImageIcon icPrev =  new ImageIcon("Image/prev.png");
	Image imPrev = icPrev.getImage().getScaledInstance(40,35, Image.SCALE_SMOOTH);
	JButton prev = new JButton(); 
	
	
	// 내용 작성 텍스트 필드
	JTextArea write = new JTextArea(); 

	// 이미지 파일 선택창
	JFileChooser chooser = new JFileChooser(); 
		
	String content = ""; // 파일 내용 
	
	
	BufferedReader br = null;
	
	
		
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
				

		// 수정버튼 아이콘 바꿔서 설정하기 위한 코드
		modify.setBorderPainted(false); 
		modify.setFocusPainted(false); 
		modify.setContentAreaFilled(false);
		modify.setLayout(null);
		modify.setIcon(new ImageIcon(imModify));
		
		
		// 컴포넌트 사이즈 설정
		dateBox.setBounds(110,125,70,40); 
		prev.setBounds(45,85,90,35);
		write.setBounds(110,500,400,300);
		image.setBounds(110,180,400,300);
		plus.setBounds(190,125,42,42); // 280, 260
		modify.setBounds(475,845,40,40);
		//delet.setBounds(475,845,35,35);
		
		
		//컴포넌트 패널에 붙이기 
		panel.add(dateBox); 
		panel.add(prev);
		panel.add(plus); 
		panel.add(image); 
		panel.add(write); 
		panel.add(modify); 
		
		
		this.add(panel); // 컴포넌트를 붙인 패널을 프레임에 붙인다.
		
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		// 이전 버튼 (일기 조회 > 달력 페이지) > flag 전달받아서 처리
		//		   (일기 조회 > 해시태그 검색)
		 prev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//new CalendarView();
				setVisible(false); // 이전페이지로 이동 후 현재 페이지 안보이게 설정
				
			}
			
		});
		
	
				
		// 사진 추가 버튼 > 기존 사진 변경
		plus.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == plus) {
					if (chooser.showOpenDialog(image) == JFileChooser.APPROVE_OPTION) {
						
						d.setdImgName(chooser.getSelectedFile().getPath()); // 이미지 경로 뽑기
						
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
		
		//d 객체에 해쉬태그 리스트에 세팅
		d.setDhashTag(dc.hashtagTokenizer(content)); 
		
		// 유저 아이디 폴더 생성
		String Folder = uId;
			
		File folder = new File(Folder);
				
		folder.mkdir();

		
		// 파일 수정 버튼
		modify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { // 해당 날짜의 일기 혹은 해시태그 검색으로 들어와서 수정

				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(folder+"\\"+date+".dat",true));){
					
					oos.writeObject(d); // 날짜, 이미지경로, 해시태그 diary 객체 저장
					
					// 이미지 저장
					// 해당 경로의 이미지를 불러와서 다시 파일에 저장
					File file = new File(d.getdImgName());

					BufferedImage saveImage = ImageIO.read(file); 

					ImageIO.write(saveImage, "png", new File(folder+"\\"+date+".png")); // 다시 저장
					
					JOptionPane.showMessageDialog(null, "일기가 수정되었습니다.", "", JOptionPane.WARNING_MESSAGE);

				} catch (IOException e1) {

					e1.printStackTrace();
				}

			}
			
		});
		
		
	}
}


