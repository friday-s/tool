package com.xue.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.xue.replay.InitializeView;

public abstract class ReplayUtil {

	public static JButton getButton(int x, int y, int width, int height,
			String s, ActionListener a) {
		JButton button = new JButton();
		button.setBounds(x, y, width, height);
		button.setText(s);
		button.setVisible(true);
		button.addActionListener(a);
		// button.setBackground(Color.LIGHT_GRAY);
		return button;
	}

	public static JMenu getMenu(String s) {
		JMenu menu = new JMenu();
		menu.setText(s);
		return menu;
	}

	public static JMenuItem getMenuItem(String s, ActionListener a) {
		JMenuItem menuItem = new JMenuItem();
		menuItem.setText(s);
		menuItem.addActionListener(a);
		return menuItem;
	}

	public static int getScreenSize(int a) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		if (a == 1) {
			return dimension.width;
		}
		if (a == 2) {
			return dimension.height;
		}
		return 0;
	}

	public static JLabel getJLabel(int x, int y, int width, int height, String s) {
		JLabel jLabel = new JLabel(s);
		jLabel.setBounds(x, y, width, height);
		return jLabel;
	}

	public static JPanel getJPanel(int x, int y, int width, int height,
			String title) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(x, y, width, height);
		jPanel.setBorder(BorderFactory.createTitledBorder(title));
		jPanel.setBackground(Color.gray);
		return jPanel;
	}

	public static JList getJList(Vector<String> v) {
		JList jList = new JList(v);
		jList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		// jList.setBorder(BorderFactory.createTitledBorder(title));
		jList.setBackground(Color.gray);
		jList.setVisible(true);
		jList.setVisibleRowCount(5);
		return jList;
	}

	public static JPanel getJScrollPane(int x, int y, int width, int height,
			String title, JComponent jComponent) {
		JPanel jpanel = getJPanel(x, y, width, height, title);
		jpanel.setBounds(x, y, width, height);
		jpanel.setBorder(BorderFactory.createTitledBorder(title));
		jpanel.setOpaque(false);
		jpanel.setLayout(null);
		JScrollPane jScrollPane = new JScrollPane(jComponent);
		jScrollPane.setBounds(5, 15, width - 10, height - 20);

		jScrollPane.setBorder(null);
		jScrollPane.setOpaque(false);

		jScrollPane.getViewport().setOpaque(false);
		jpanel.add(jScrollPane);
		return jpanel;
	}

	public static void countProgress() {
		InitializeView.progressValue++;
	}

}
