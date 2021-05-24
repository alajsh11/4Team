package com.kh.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

import com.kh.controller.DiaryController;
import com.kh.model.dao.UserDao;
import com.kh.model.vo.Diary;
import com.kh.model.vo.User;

public class DiaryInformationView {

	public DiaryInformationView() {
		// TODO Auto-generated constructor stub
	}
	
	public DiaryInformationView(User user, Diary d,String searchText, int flag) {
		JFrame jf = new JFrame();
		jf.setTitle("해씨일기");
		DiaryController dc= new DiaryController();
		
		JPanel panel = new JPanel();
		JLabel image = new JLabel(); // 사진 붙여 넣을 화면
		JLabel dateBox = new JLabel(); // 날짜 창
		
		//수정
		//ImageIcon icModify = new ImageIcon("Image/modify.png");
		//Image imModify = icModify.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		JButton modifyBtn = new JButton("수정"); 
		
		//이전
		ImageIcon icPrev =  new ImageIcon("Image/prev.png");
		Image imPrev = icPrev.getImage().getScaledInstance(40,35, Image.SCALE_SMOOTH);
		JButton prevBtn = new JButton(); 
		
		//삭제
		//ImageIcon icDelete =  new ImageIcon("Image/delete.png");
		//Image imDelete = icDelete.getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
		JButton deleteBtn = new JButton("삭제"); 

		JTextArea txtField = new JTextArea(); // 내용 작성 텍스트 필드
		txtField.setFont(new Font("맑은고딕", Font.BOLD, 18));
		
		//프레임 설정
		jf.setSize(640, 960);//전체 창 사이즈
		jf.setLayout(null);
		jf.setLocationRelativeTo(null); // 창 가운데로 켜지게 설정
		
		//패널 설정
		panel.setSize(jf.getMaximumSize());
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


		// 수정버튼
		modifyBtn.setFocusPainted(false);
		modifyBtn.setContentAreaFilled(false);
		modifyBtn.setLayout(null);
		modifyBtn.setFont(new Font("Plain", Font.BOLD, 15));
		modifyBtn.setBackground(Color.GRAY);
	
		// 삭제버튼 
		deleteBtn.setFocusPainted(false);
		deleteBtn.setContentAreaFilled(false);
		deleteBtn.setLayout(null);
		deleteBtn.setFont(new Font("맑은고딕", Font.BOLD, 15));
		
		
		txtField.setEditable(false);
		if(d.getDhashTag() != null) {
			txtField.setText(dc.arrayListToText(d.getDhashTag()));
		}
		else {
			System.out.println("해시태그 비어있음");
		}
		
		
		// 컴포넌트 사이즈 설정
		dateBox.setBounds(110,125,70,40); 
		prevBtn.setBounds(45,85,90,35);
		txtField.setBounds(110,500,400,300);
		image.setBounds(110,180,400,300);
		modifyBtn.setBounds(355, 840,70,35);
		deleteBtn.setBounds(443,840,70,35);
		
		
		//컴포넌트 패널에 붙이기 
		panel.add(dateBox); 
		panel.add(prevBtn);
		panel.add(image); 
		panel.add(txtField); 
		panel.add(modifyBtn); 
		panel.add(deleteBtn);
		
		
		// 프레임 아이콘 변경
		try {
			jf.setIconImage(ImageIO.read(new File("image/IconHamster.jpg")));

		} catch (IOException e2) {

			e2.printStackTrace();
		}
		
		jf.add(panel);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prevBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jf.setVisible(false);
				if(flag==0) {
					new CalendarView(user);
				}
				else if(flag==1) {
					new SearchHashTagView(user,searchText,flag);
				}
			}
		});
		modifyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);
				new DiaryReWriteView(user,d);
			}
		});
		deleteBtn.addActionListener(new ActionListener() {
			
				@Override
			public void actionPerformed(ActionEvent e) { //해당날짜 버튼 클릭
			    			
			        File deleteFile = new File(user.getuId()+"/"+ d.getdDate() + ".dat");
			        File deleteImgFile= new File(user.getuId()+"/"+ d.getdDate() + ".png");
			        if(deleteImgFile.exists()) {
			        	deleteImgFile.delete();
			        }
			        if(deleteFile.exists()) { //만약 파일이 존재한다면
			            
			            // 파일을 삭제
			            deleteFile.delete(); 
			            
			            //일기파일 삭제 후 팝업창 
			            JOptionPane.showMessageDialog(null, "일기가 삭제되었습니다.", "", JOptionPane.WARNING_MESSAGE);
			            
			            user.setDiaryCount(user.getDiaryCount()-1);
			            new UserDao().userDiaryCountTemp(user, user.getDiaryCount()-1);
						
			            jf.setVisible(false);   // 이전페이지로 이동 후 현재 페이지 안보이게 설정
			            new CalendarView(user); // 삭제 후 달력페이지로 이동
						
			        } else { //존재하지 않는다면 삭제할 일기가 없습니다. 팝업창
			        	JOptionPane.showMessageDialog(null, "삭제할 일기가 없습니다.", "", JOptionPane.WARNING_MESSAGE);

			        }
			       
			        
			       
			    }
		});
			
		
		
	}
	
	
}
