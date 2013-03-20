package com.xue.listview;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 自定义的源List 其内容是一个List，外部通过setSources(List sources)来设置源 BaseList Demo 核心类
 * 
 * @ClassName ListSource
 * @author wei.xue
 * @date 2013-01-18
 */
public class ListSource extends BaseModel {
	private Comparator<Object> comparator = null;
	private List<Object> sources = new ArrayList<Object>();

	// 通过list设置数据源
	public void setSources(List<Object> sources) {
		this.sources = sources;
	}

	// 添加一个单元
	public void addCell(Object obj) {
		sources.add(obj);
		notifySourceRefreshEvent(sources);
	}

	// 根据索引删除一个单元
	public void removeCell(int index) {
		System.out.println("删除:" + index);
		if (sources.size() > 0 && index < sources.size() && index >= 0) {
			sources.remove(index);
			notifySourceRefreshEvent(sources);
		}
	}

	// 根据值删除一个单元
	public void removeCell(Object value) {
		sources.remove(value);
		notifySourceRefreshEvent(sources);
	}

	// 设置一个单元
	public void setCell(int index, Object obj) {
		sources.set(index, obj);
		notifySourceRefreshEvent(sources);
	}

	// 获取一个单元的信息
	public Object getCell(int index) {
		return sources.get(index);
	}

	// 获取所有单元信息
	public List<Object> getAllCell() {
		return sources;
	}

	// 移除所有
	public void removeAll() {
		sources.clear();
		notifySourceRefreshEvent(sources);
	}

	// 上移
	public void moveUp(int index) {
		if (index > 0 && index < sources.size()) {
			Object temp = sources.get(index - 1);
			sources.set(index - 1, sources.get(index));
			sources.set(index, temp);
			notifySourceRefreshEvent(sources);
		}
	}

	// 下移
	public void moveDown(int index) {
		if (index < sources.size() - 1 && index > 0) {
			Object temp = sources.get(index + 1);
			sources.set(index + 1, sources.get(index));
			sources.set(index, temp);
			notifySourceRefreshEvent(sources);
		}
	}

	public void setSort(Comparator<Object> comparator) {
		this.comparator = comparator;
		notifySourceRefreshEvent(sources);
	}

	public Comparator<Object> getComparator() {
		return comparator;
	}
}
