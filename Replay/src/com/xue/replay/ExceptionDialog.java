package com.xue.replay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ExceptionDialog {

	private String msg;
	private JDialog dialog;

	public ExceptionDialog(JFrame jframe, String msg) {
		this.msg = msg;
		dialog = new JDialog();
		dialog.setVisible(true);
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(jframe);
		dialog.setSize(300, 100);
	}

	private void initUI() {
		
		
		
		
		JButton button = new JButton("confirm");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.dispose();
			}
		});
	}

}
