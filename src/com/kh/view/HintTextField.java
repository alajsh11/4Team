package com.kh.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

public class HintTextField extends JTextField {
	private Font font = new Font("맑은 고딕", Font.PLAIN, 20);
	public HintTextField() {
		// TODO Auto-generated constructor stub
	}
	public HintTextField(final String HINT) {
		setName("searchTxt");
		setText(HINT);
		setFont(font);
		setForeground(Color.GRAY);
		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (getText().equals(HINT)) {
					setText("");
					setForeground(Color.BLACK);
				} else {
					setText(getText());
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (getText().equals( HINT) || getText().length() == 0) {
					setText(HINT);
					setForeground(Color.GRAY);
				} else {
					setText(getText());
					setForeground(Color.BLACK);
				}
			}
		});
	}
}