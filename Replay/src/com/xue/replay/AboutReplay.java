package com.xue.replay;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.xue.util.ReplayUtil;

public class AboutReplay {

	private String title = "About Replay";

	private String[] content = { "Replay", "版本: V1.0 测试版本", "编译时间: 2013-02-15",
			"注意: 如有建议或者发现BUG可与作者联系", "联系方式:", "QQ: 175427603",
			"Email: xue1210@qq.com" };
	private JPanel jpanel;
	private JDialog about_replay;

	public AboutReplay(JFrame jframe) {
		about_replay = new JDialog(jframe,false);
		about_replay.setTitle(title);
		about_replay.setSize(300, 210);
		about_replay.setVisible(true);
		about_replay.setResizable(false);
		about_replay.setLocationRelativeTo(jframe);
		initUI();
		about_replay.setContentPane(jpanel);
	}

	private void initUI() {
		jpanel = new JPanel(null);
		jpanel.setBackground(Color.gray);
		jpanel.setVisible(true);
		int y = -20;
		for (int i = 0; i < content.length; i++) {
			jpanel.add(ReplayUtil.getJLabel(5, y += 20, 250, 20, content[i]));
		}
		JButton button = new JButton("我知道了...");
		button.setBounds(100, 145, 100, 30);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				about_replay.dispose();
			}
		});
		jpanel.add(button);
	}
}
