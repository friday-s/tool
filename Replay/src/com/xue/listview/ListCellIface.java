package com.xue.listview;

import javax.swing.JComponent;

/**
 * Cell的接口类，用于构造BaseList中的cell BaseList Demo 重要类
 * 
 * @author wei.xue
 * @date 2013-01-18
 */
public interface ListCellIface {
	
	public JComponent getListCell(BaseList list, Object value);

	public void setSelect(boolean iss);
}
