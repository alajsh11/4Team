package com.kh.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

public class HintTextField extends JTextField {
	Font font = new Font("맑은 고딕", Font.PLAIN, 20);

	public HintTextField(final String hint) {
		setName("searchTxt");
		setText(hint);
		setFont(font);
		setForeground(Color.GRAY);
		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (getText().equals(hint)) {
					setText("");
					setForeground(Color.BLACK);
				} else {
					setText(getText());
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (getText().equals(hint) || getText().length() == 0) {
					setText(hint);
					setForeground(Color.GRAY);
				} else {
					setText(getText());
					setForeground(Color.BLACK);
				}
			}
		});
	}
}