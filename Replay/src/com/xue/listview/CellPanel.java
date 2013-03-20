package com.xue.listview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.xue.replay.InitializeView;


/**
 * cell的样式Panel，实现ListCellIface
 * @author wei.xue
 * @date 2013-01-18
 */
public class CellPanel extends JPanel implements ListCellIface{

	private static final long serialVersionUID = 1L;
	private int index = 0;
	private CellPanel per2CellPanel=this;
	private BaseList baseList;
	private String bean;
	private JLabel label;
	private JButton remove = new JButton("RM");
	public CellPanel(){
		super();
		inGui();
	}
	private void inGui(){
		this.setMaximumSize(new Dimension(223,30));
		this.setPreferredSize(new Dimension(223, 30));
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
	}
	/**
	 * 实现接口中的方法
	 */
	@Override
	public JComponent getListCell(BaseList list, Object value) {
		String valuelist = (String)value;
		this.bean = valuelist;
		this.baseList=list;
		
		label = new JLabel(bean);
		final JSpinner jSpinner = new JSpinner();
		jSpinner.setModel(new SpinnerNumberModel(1, 1, null, 1));
		jSpinner.setPreferredSize(new Dimension(35,30));
		jSpinner.setMaximumSize(new Dimension(35,30));
		jSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(jSpinner.getValue());
			}
		});
//		remove.setBounds(100, 0, 60, 25);
//		remove.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//			
//				if(e.getButton()==MouseEvent.BUTTON1){
//					per2CellPanel.baseList.getSource().removeCell(bean);
//				}
//			}
//		});
		this.add(label,"West");
		this.add(jSpinner,"East");
		
		return this;
	}
	@Override
	public void setSelect(boolean iss) {
		
	}
}