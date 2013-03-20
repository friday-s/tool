package com.xue.listview;

import java.util.List;
import javax.swing.DefaultListModel;


public class DataModel extends DefaultListModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataModel(List<String> source) {
		for (int i = 0; i < source.size(); i++) {
			this.addElement(source.get(i));
		}
	}
}
