package com.xue.replay;

import javax.swing.JDialog;
import javax.swing.JPanel;

import com.xue.util.ReplayUtil;

public class Settings {

	private JDialog settings;
	private JPanel jpanel;
	private String[] text = { "Language:", "Backgroud Run:" };

	public Settings() {
		settings = new JDialog();
		settings.setSize(300, 200);
		settings.setVisible(true);
	}

	private void initUI() {
		jpanel = new JPanel(null);
		
		int y = -30;
		for (int i = 0; i < text.length; i++) {
			jpanel.add(ReplayUtil.getJLabel(5, y += 30, 100, 30, text[i]));
		}

		settings.setContentPane(jpanel);
	}

}
