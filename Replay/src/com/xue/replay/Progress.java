package com.xue.replay;


/**
 * 程序运行状态显示
 * @author wei.xue
 * @date 2013-01-18
 */
public class Progress extends Thread {

	private String[] stateStrings;

	public Progress(String[] stateStrings) {
		this.stateStrings = stateStrings;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (RunningState.flag == true) {
			int i = 1;
			while (RunningState.flag == true && i < ReplayMain.iconList.size()) {
				ReplayMain.progessLabel.setIcon(ReplayMain.iconList.get(i));
				ReplayMain.stateLabel.setText(stateStrings[i % 4]);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
		}
	}
}
