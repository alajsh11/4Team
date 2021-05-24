package com.kh.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
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

import com.kh.controller.CalendarController;
import com.kh.controller.DiaryController;
import com.kh.model.dao.UserDao;
import com.kh.model.vo.Diary;
import com.kh.model.vo.User;

public class DiaryWriteView  {

	private Diary d = new Diary();
	private DiaryController dc = new DiaryController();
	public static String absoluteFilePath = "";
	private String date;
	private String uId;
	public static int diaryCount = 0;
	
	public DiaryWriteView() {}


	public DiaryWriteView(String date, String uId) {
		
		this.date = date;
		this.uId = uId;

		JFrame jf = new JFrame("해씨일기");
		JPanel panel = new JPanel();

		// 사진 붙여 넣을 화면
		JLabel image = new JLabel();

		// 날짜 창
		JLabel dateBox = new JLabel(date);

		// 사진 붙이기 버튼
		ImageIcon icPlus = new ImageIcon("Image/sunflower seed1.png");
		Image imPlus = icPlus.getImage().getScaledInstance(42, 35, Image.SCALE_SMOOTH);
		JButton plus = new JButton();

		// 저장 버튼
		//ImageIcon icSave = new ImageIcon("Image/save.png");
		//Image imSave = icSave.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		JButton save = new JButton("저장");

		// 이전창 버튼
		ImageIcon icPrev = new ImageIcon("Image/prev.png");
		Image imPrev = icPrev.getImage().getScaledInstance(40, 35, Image.SCALE_SMOOTH);
		JButton prev = new JButton(); // 이전 버튼(작성 > 달력)

		//이미지 라벨 배경 변경
		image.setOpaque(true); //Opaque값을 true로 미리 설정해 주어야 배경색이 적용된다.
		image.setBackground(Color.WHITE);

		// 내용 작성 텍스트 필드
		JTextArea write = new JTextArea();
		write.setFont(new Font("맑은고딕", Font.BOLD, 18));
		
		// 파일 오픈창
		JFileChooser chooser = new JFileChooser();

		// 프레임 설정
		jf.setSize(640, 960);// 전체 창 사이즈
		jf.setLayout(null);
		jf.setLocationRelativeTo(null); // 창 가운데로 켜지게 설정

		// 패널 설정
		panel.setSize(jf.getMaximumSize());
		panel.setLayout(null);
		panel.setBackground(new Color(0xddc6e6)); // 배경색은 패널에 지정

		// date 박스 폰트 설정
		dateBox.setFont(new Font("serif", Font.BOLD, 20));
		dateBox.setForeground(Color.BLACK);

		//image label 테두리, 배경색 설정 
		BevelBorder border = new BevelBorder(BevelBorder.RAISED);
		image.setBorder(border);
		image.setBackground(Color.WHITE);
		image.setOpaque(true); //Opaque값을 true로 미리 설정해 주어야 배경색이 적용된다.
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
		//save.setBorderPainted(false);
		save.setFocusPainted(false);
		save.setContentAreaFilled(false);
		save.setLayout(null);
		//save.setIcon(new ImageIcon(imSave));
		save.setFont(new Font("맑은고딕", Font.BOLD, 15));
		
		

		// 컴포넌트 사이즈 설정
		dateBox.setBounds(110, 125, 70, 40);
		prev.setBounds(45, 85, 90, 35);
		write.setBounds(110, 500, 400, 300);
		image.setBounds(110, 180, 400, 300);
		plus.setBounds(190, 125, 42, 42); // 280, 260
		save.setBounds(443, 840, 70, 35);

		// 컴포넌트 패널에 붙이기
		panel.add(dateBox);
		panel.add(prev);
		panel.add(plus);
		panel.add(image);
		panel.add(write);
		panel.add(save);

		// 프레임 아이콘 변경
		try { 
			jf.setIconImage(ImageIO.read(new File("image/IconHamster.jpg")));

		} catch (IOException e2) {

			e2.printStackTrace();
		}

		jf.add(panel); // 컴포넌트를 붙인 패널을 프레임에 붙인다.

		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 이전 버튼 (작성페이지 > 달력페이지)
		prev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new CalendarView(uId); // 달력페이지로 가고
				jf.setVisible(false); // 이전페이지로 이동 후 현재 페이지 안보이게 설정

			}

		});

		// 사진 선택 버튼
		plus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == plus) {
					if (chooser.showOpenDialog(image) == JFileChooser.APPROVE_OPTION) {

						File file = chooser.getSelectedFile(); // 선택된 파일을 가져온다.

						absoluteFilePath = file.getAbsoluteFile().toString(); // 파일의 경로를 받는 변수 생성

						ImageIcon icon = null;
						try {
							icon = new ImageIcon(ImageIO.read(file));

						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} // 이미지를 이미지 아이콘으로 변경

						Image img = icon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH); // 이미지로 사이즈 조정

						image.setIcon(new ImageIcon(img)); // 해당 이미지 라벨에 붙이기

					} 
				}
			}
		});

		// 유저 아이디 폴더 생성
		dc.createFolder(uId);
	
		// 저장 버튼
		save.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (image.getIcon() == null) {
					JOptionPane.showMessageDialog(null, "이미지가 선택되지 않았습니다.", "", JOptionPane.WARNING_MESSAGE);
				
				} else {
					// 이미지 저장
					dc.saveImage(uId, date, absoluteFilePath);
					
					String content = write.getText(); // text 필드에서 넣은 값을 담는다.

					if (content.length() < 100) {// 내용은 100자 제한이므로 100자 이하일때만 content에 담긴다.

						dc.saveDiary(uId, date, dc.hashtagTokenizer(content));

					} else if (content.length() >= 100) { // 100자 이상이되면 팝업창이 뜬다.

						JOptionPane.showMessageDialog(null, "글자 제한 100자입니다.", "", JOptionPane.WARNING_MESSAGE);

					}
					

//					JOptionPane.showMessageDialog(null, "일기가 저장되었습니다.", "", JOptionPane.WARNING_MESSAGE);
					int result = JOptionPane.showConfirmDialog(null, "일기가 저장되었습니다.", "", JOptionPane.OK_OPTION);
					
					if (result == JOptionPane.OK_OPTION) {
						jf.setVisible(false);
						new CalendarView(uId);	
					}
				}
				
				 new UserDao().userDiaryCountTemp(uId, diaryCount++);
				
				
			}
		});

	}

}
