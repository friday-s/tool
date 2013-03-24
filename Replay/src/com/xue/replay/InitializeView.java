package com.xue.replay;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.xue.util.ReplayUtil;

/**
 * ≥ı ºªØ
 * 
 * @author wei.xue
 * @date 2013-01-18
 */
public class InitializeView {
	public static int progressValue = 0;
	public static int screenWidth;
	public static int screenHeight;
	public static JProgressBar progressBar;
	public static JLabel percentLabel;
	public static JFrame jFrame;

	public InitializeView() {
		JFrame.setDefaultLookAndFeelDecorated(false);
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jFrame = new JFrame();
		getScreenSize();
		jFrame.setUndecorated(true);
		jFrame.setLayout(null);
		jFrame.setBounds(screenWidth / 2 - 200, screenHeight / 3, 400, 60);

		JPanel panel = new JPanel();
		panel.setBackground(Color.gray);
		panel.setLayout(null);

		jFrame.setContentPane(panel);
		panel.setBounds(0, 0, 300, 60);

		progressBar = new JProgressBar();
		progressBar.setMinimum(0);
		progressBar.setMaximum(50);
		progressBar.setBounds(0, 20, 400, 40);
		panel.add(progressBar);

		JLabel jLabel = ReplayUtil.getJLabel(0, 0, 200, 20, " Loading...");
		percentLabel = ReplayUtil.getJLabel(348, 0, 50, 20, "");
		percentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(jLabel);
		panel.add(percentLabel);
	}

	private void getScreenSize() {
		screenWidth = ReplayUtil.getScreenSize(1);
		screenHeight = ReplayUtil.getScreenSize(2);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		/**
		 * Create the application.
		 */
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {

					InitializeView window = new InitializeView();
					 UIManager.LookAndFeelInfo[] looks = UIManager
					 .getInstalledLookAndFeels();
					 for (UIManager.LookAndFeelInfo look : looks) {
					 System.out.println(look.getClassName());
					
					 }
			
					window.jFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		/**
		 * Initialize the contents of the frame.
		 */
		new ReplayMain().execute();
	}
}
