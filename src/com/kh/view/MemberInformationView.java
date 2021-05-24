package com.kh.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.kh.controller.AdministratorController;
import com.kh.model.dao.UserDao;

public class MemberInformationView {
	private JTextField userListField;
	private JTable userListTable;
	private String[] headings;
	private Object[][] data;
	private AdministratorController ac = new AdministratorController();
	private JFrame jf;

	public MemberInformationView() {
		jf = new JFrame();
		jf.setSize(640, 960);
		jf.setTitle("회원조회");
		jf.setLayout(null);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		// 전체 배경패널
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0xddc6e6));
		panel.setSize(jf.getMaximumSize());
		panel.setLayout(null);

		// 회원조회 패널
		userListField = new JTextField("회원조회");
		userListField.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		userListField.setBounds(200, 100, 300, 30);
		userListField.setEditable(false);

		// 관리자 이미지라벨
		Image adminImg = new ImageIcon("image/admin.PNG").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		JLabel adminImgLabel = new JLabel(new ImageIcon(adminImg));
		adminImgLabel.setBounds(100, 80, 100, 100);
//		adminImgLabel.setBackground(new Color(0xddc6e6));

		// 유저 목록 리스트 패널
		JPanel userListPanel = new JPanel();
		userListPanel.setLayout(new GridLayout());
		userListPanel.setBounds(0, 200, 640, 600);

		
		
		// 유저 목록 리스트 테이블
		// 테이블 clounm
		headings = new String[] { "회원번호", "회원아이디", "" };
		// 테이블 data
		ac.memberTableInfo();
		data = new Object[ac.getIdArr().length][3];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < 3; j++) {
				switch (j) {
				case 0:
					data[i][j] = ac.getNoArr()[i];
					break;
				case 1:
					data[i][j] = ac.getIdArr()[i];
					break;
				case 2:
					data[i][j] = ac.getUser().getList().get(i);
					break;
				}
			}
		}
		
		

		
//		// 테이블 생성
		TableCell btnTable = new TableCell();
		
		JTable userListTable = new JTable(data, headings);
		userListTable.getColumnModel().getColumn(2).setCellRenderer(btnTable);
		userListTable.getColumnModel().getColumn(2).setCellEditor(btnTable);
		userListTable.setPreferredScrollableViewportSize(new Dimension(640, 600)); // 테이블 사이즈
		userListTable.setFillsViewportHeight(true);
		userListTable.setRowHeight(50);
		userListTable.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		userListPanel.add(new JScrollPane(userListTable));
	
		
		
		
		
		
		//
		btnTable.getJb().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				jf.setVisible(false);
				String value;
				value = (String)userListTable.getValueAt(userListTable.getSelectedRow(), 0);
				
				for(int i = 0; i < ac.getNoArr().length; i++) {
					if(ac.getNoArr()[i].equals(value)) {
						new MemberCheckView(ac.getUser().getList().get(i));
						jf.setVisible(false);
					}
				}
			}
		});
		
		
		
		

		// 로그아웃 버튼
		JButton logOutButton = new JButton("로그아웃");
		logOutButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		logOutButton.setBounds(250, 850, 100, 50);
		// 로그아웃 버튼 클릭시 loginView화면으로 이동
		logOutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginView();
				jf.setVisible(false);
			}
		});

		// 패널에 추가
		panel.add(userListPanel);
		panel.add(adminImgLabel);
		panel.add(userListField);
		panel.setVisible(true);
		panel.add(logOutButton);

		// 화면에 추가
		jf.add(panel);
		jf.setVisible(true);
	}

	
	
	

class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

	JButton jb;
	UserDao a = new UserDao();

	public TableCell() {

		Image memberInfoImg = new ImageIcon("Image/arrow.png").getImage().getScaledInstance(40, 35,
				Image.SCALE_SMOOTH);
		jb = new JButton();
		jb.setBorderPainted(false);
		jb.setContentAreaFilled(false);
		jb.setIcon(new ImageIcon(memberInfoImg));
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

	public JButton getJb() {
		return jb;
	}

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		return jb;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		return jb;
	}

}

}