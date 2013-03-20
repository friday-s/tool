package com.xue.listview;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SimpleList extends JList implements ListSelectionListener {

	private DataModel source;

	public SimpleList(DataModel source) {
		this.setBackground(Color.gray);
		this.source = source;
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(SimpleList.this.getSelectedValue());
				
			}
		});
		createList();
	}

	public void createList() {
		this.setModel(source);
		this.setVisibleRowCount(5);
	}

	public void addElement(Object obj) {
		source.addElement(obj);
	}

	public void delElement(int index) {
		source.remove(index);
	}
	
	public void delAll(){
		source.removeAllElements();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getSource());
	}
}
