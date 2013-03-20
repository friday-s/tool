/**
 * 
 */
package com.xue.listview;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;



/**
 * @author wei.xue
 * @date 2013-01-18
 */
public class BaseModel {
	private Vector<BaseList> repository = new Vector<BaseList>();
	private BaseList bl;
	// 注册监听器，如果这里没有使用Vector而是使用ArrayList那么要注意同步问题
	public void addSourceRefreshListener(BaseList list) {
		repository.addElement(list);// 这步要注意同步问题
	}

	// 如果这里没有使用Vector而是使用ArrayList那么要注意同步问题
	public void notifySourceRefreshEvent(List<Object> event) {
		Enumeration<BaseList> en = repository.elements();// 这步要注意同步问题
		while (en.hasMoreElements()) {
			bl = (BaseList) en.nextElement();
			bl.sourceRefreshEvent(event);
		}
	}
	// 删除监听器，如果这里没有使用Vector而是使用ArrayList那么要注意同步问题
	public void removeSourceRefreshListener(BaseList srl) {
		repository.remove(srl);// 这步要注意同步问题
	}
}
