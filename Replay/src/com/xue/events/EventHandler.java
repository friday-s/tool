package com.xue.events;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import com.xue.replay.DlgProgess;
import com.xue.replay.ReplayMain;
import com.xue.replay.RunningState;

/**
 * 
 * @author wei.xue
 * @date 2013-01-22
 */

public class EventHandler {

	private Process p;
	private Thread thread_replay;
	public static String errorMsg;

	private int execCommand(String command) {
		try {
			p = Runtime.getRuntime().exec(command);

			new StreamGobbler(p.getErrorStream(), "ERROR").start();
			new StreamGobbler(p.getInputStream(), "OUTPUT").start();

			try {
				// 返回结果可能有风险存在
				if (p.waitFor() < 0) {
					System.err.println("执行\"" + command + "\"时返回值="
							+ p.exitValue());
					RunningState.flag = false;
					return -1;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	// 保存事件到工程目录
	public void saveEvent(JFrame jframe, String fileName) {

		// String command = "." + File.separator + "adb" + File.separator
		// + "adb pull sdcard" + File.separator + "events ."
		// + File.separator + "event" + File.separator + fileName;
		// System.out.println(System.getProperty("os.name"));
		// System.out.println(command);
		final String commString = ".\\adb\\adb pull sdcard/events .\\event\\"
				+ fileName;
		Thread thread = new Thread() {
			public void run() {
				execCommand(commString);
			}
		};
		DlgProgess.show(jframe, thread, "save event...");
	}

	// 推入record可执行文件
	public int pushXFile(String xFile) {
		String command = ".\\adb\\adb push .\\lib\\" + xFile
				+ " data/local/tmp/";
		String commandChmod = ".\\adb\\adb shell chmod 777 data/local/tmp/"
				+ xFile;
		System.out.println("导入执行文件...");
		if (execCommand(command) == 1) {
			System.out.println("赋予权限...");
			// 赋予可读写权限
			return execCommand(commandChmod);
		}
		return -1;
	}

	public void startRecord(final JFrame jframe, final JButton btn_stop) {
		final String command = ".\\adb\\adb shell data/local/tmp/event_record";
		System.out.println("准备录制...");

		final Thread thread = new Thread() {
			public void run() {
				execCommand(command);
			};
		};
		thread.start();
		new Thread() {
			public void run() {
				try {
					System.out.println("正在录制...");
					thread.join();
					System.out.println("录制结束... ");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				btn_stop.doClick();
			}
		}.start();
	}

	public void startReplay(final JButton stopReplay, JFrame jframe,
			final File file, final List<JComponent> listPlan) {
		final String commandReplay = ".\\adb\\adb shell data/local/tmp/event_replay ";
		// String commandPushEvent = ".\\adb\\adb push ";
		
		
		final ArrayList<Plan> arrayPlan = new ArrayList<Plan>();
		Thread thread = new Thread() {
			public void run() {

				for (int i = 0; i < listPlan.size(); i++) {
					Component[] components = listPlan.get(i).getComponents();
					Plan plan = new Plan();
					for (Component c : components) {
						if (c instanceof JLabel) {
							plan.setEvent_name(((JLabel) c).getText());
						}
						if (c instanceof JSpinner) {
							plan.setEvent_times((Integer) ((JSpinner) c)
									.getValue());
						}
					}
					arrayPlan.add(plan);
				}
			}
		};

		DlgProgess.show(jframe, thread, "import events...");
		thread_replay = new Thread() {
			public void run() {

				for (int i = 0; i < arrayPlan.size(); i++) {
					System.out.println(".\\adb\\adb push " + file + "\\"
							+ arrayPlan.get(i).getEvent_name()
							+ " sdcard/events");
					execCommand(".\\adb\\adb push " + file + "\\"
							+ arrayPlan.get(i).getEvent_name()
							+ " sdcard/events");
					ReplayMain.replayState.setText("Current: plan " + (i + 1));
					execCommand(commandReplay
							+ String.valueOf(arrayPlan.get(i).getEvent_times()));
				}
			}
		};
		thread_replay.start();
		new Thread() {
			public void run() {

				try {
					Thread.sleep(10);
					thread_replay.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stopReplay.doClick();
				ReplayMain.replayState.setText("");
			}
		}.start();
	}

	@SuppressWarnings("deprecation")
	public void stop() {
		p.destroy();
		if (thread_replay != null && thread_replay.isAlive()) {
			thread_replay.stop();
		}
	}

	public void replayStop() {

	}

	class StreamGobbler extends Thread {

		private InputStream is;
		private String type;
		private BufferedReader br;

		public StreamGobbler(InputStream is, String type) {
			this.is = is;
			this.type = type;
		}

		@Override
		public void run() {

			// TODO Auto-generated method stub
			try {
				br = new BufferedReader(new InputStreamReader(is));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.err.println(type + ">" + line);
					errorMsg = line;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					br.close();
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}