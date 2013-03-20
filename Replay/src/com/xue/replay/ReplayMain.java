package com.xue.replay;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.SystemColor;
import javax.swing.JLayeredPane;
import com.xue.events.EventHandler;
import com.xue.events.FileHandler;
import com.xue.listview.BaseList;
import com.xue.listview.CellPanel;
import com.xue.listview.DataModel;
import com.xue.listview.ListSource;
import com.xue.listview.SimpleList;
import com.xue.util.ReplayUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

/**
 * 程序主界面
 * 
 * @author wei.xue
 * @date 2013-01-18
 */
public class ReplayMain extends SwingWorker<JFrame, Integer> implements
		ActionListener {

	private JFrame replayFrame;

	private JLayeredPane layeredPane;

	private JButton btn_start_record;
	private JButton btn_stop_record;
	private JButton btn_start_replay;
	private JButton btn_stop_replay;

	private JButton btn_add;
	private JButton btn_move_up;
	private JButton btn_nmove_down;
	private JButton btn_del;
	private JButton btn_refresh;

	private ArrayList<JButton> btns_event_handle;

	private String[] btnStrings = { "add", "up", "down", "remove", "refresh" };

	private String[] menuStrings = { "File", "Options", "About" };
	private String[] fileItemsStrings = { "Open Events", "Exit" };
	private String[] optionsItemsStrings = { "Settings" };
	private String[] aboutItemStrings = { "About Replay" };
	private String[][] childStrings = { fileItemsStrings, optionsItemsStrings,
			aboutItemStrings };

	private String[] record_stateStrings = { "Recording", "Recording.",
			"Recording..", "Recording..." };
	private String[] replay_stateStrings = { "Replaying", "Replaying.",
			"Replaying..", "Replaying..." };
	private ArrayList<JMenu> menus;

	public static ArrayList<ImageIcon> iconList;
	public static JLabel progessLabel;
	public static JLabel stateLabel;
	public static JLabel replayState;

	private JFileChooser jFileChooser;

	private FileHandler fh;

	private ListSource listSource;
	private BaseList transferList;

	private SimpleList sl;

	// 文件路径
	private File file_path;

	// 事件控制器
	private EventHandler eh = new EventHandler();

	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {

		replayFrame = new JFrame();
		replayFrame.setTitle("Replay");

		initResource();
		ReplayUtil.countProgress();
		publish(InitializeView.progressValue);
		initJFrame();
		ReplayUtil.countProgress();
		publish(InitializeView.progressValue);
		initMenuBar();
		ReplayUtil.countProgress();
		publish(InitializeView.progressValue);
		initComponent();
		ReplayUtil.countProgress();
		publish(InitializeView.progressValue);

	}

	private void initJFrame() {
		replayFrame.setResizable(false);
		replayFrame.setBackground(Color.gray);
		ImageIcon icon = new ImageIcon(".\\res\\image\\replay.png");
		replayFrame.setIconImage(icon.getImage());
		replayFrame.setBounds(InitializeView.screenWidth / 2 - 599 / 2,
				InitializeView.screenHeight / 2 - 343, 598, 343);
		replayFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		replayFrame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				exitDialog();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

		ReplayUtil.countProgress();
		publish(InitializeView.progressValue);
	}

	private void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.ORANGE);
		replayFrame.setJMenuBar(menuBar);
		menus = new ArrayList<JMenu>();
		for (int i = 0; i < menuStrings.length; i++) {
			menus.add(ReplayUtil.getMenu(menuStrings[i]));
			menuBar.add(menus.get(i));
			for (int j = 0; j < childStrings[i].length; j++) {
				menus.get(i).add(
						ReplayUtil.getMenuItem(childStrings[i][j], this));
				ReplayUtil.countProgress();
				publish(InitializeView.progressValue);
			}
			ReplayUtil.countProgress();
			publish(InitializeView.progressValue);
		}
	}

	private void initComponent() {
		layeredPane = new JLayeredPane();
		replayFrame.setContentPane(layeredPane);
		ReplayUtil.countProgress();
		publish(InitializeView.progressValue);

		btn_start_record = ReplayUtil
				.getButton(40, 40, 100, 50, "record", this);
		btn_stop_record = ReplayUtil.getButton(10, 10, 100, 50, "stop", this);
		btn_start_replay = ReplayUtil.getButton(452, 40, 100, 50, "replay",
				this);
		btn_stop_replay = ReplayUtil.getButton(482, 10, 100, 50, "stop", this);
		ReplayUtil.countProgress();
		publish(InitializeView.progressValue);

		btn_stop_record.setEnabled(false);
		btn_stop_replay.setEnabled(false);
		ReplayUtil.countProgress();
		publish(InitializeView.progressValue);

		btn_add = ReplayUtil.getButton(258, 110, 77, 30, btnStrings[0], this);
		btn_move_up = ReplayUtil.getButton(258, 145, 77, 30, btnStrings[1],
				this);
		btn_nmove_down = ReplayUtil.getButton(258, 180, 77, 30, btnStrings[2],
				this);
		btn_del = ReplayUtil.getButton(258, 215, 77, 30, btnStrings[3], this);
		btn_refresh = ReplayUtil.getButton(258, 250, 77, 30, btnStrings[4],
				this);

		layeredPane.add(btn_add);
		layeredPane.add(btn_move_up);
		layeredPane.add(btn_nmove_down);
		layeredPane.add(btn_del);
		layeredPane.add(btn_refresh);

		layeredPane.add(btn_start_record, new Integer(300));
		layeredPane.add(btn_stop_record, new Integer(200));
		layeredPane.add(btn_start_replay, new Integer(300));
		layeredPane.add(btn_stop_replay, new Integer(200));
		ReplayUtil.countProgress();
		publish(InitializeView.progressValue);

		progessLabel = new JLabel("");
		progessLabel.setBounds(270, 10, 80, 80);
		layeredPane.add(progessLabel);
		ReplayUtil.countProgress();
		publish(InitializeView.progressValue);

		stateLabel = new JLabel("");
		stateLabel.setBounds(266, -25, 80, 80);
		layeredPane.add(stateLabel);

		replayState = new JLabel("");
		replayState.setBounds(255, 60, 300, 50);
		layeredPane.add(replayState);
		ReplayUtil.countProgress();
		publish(InitializeView.progressValue);

		file_path = new File(".\\event\\");
		fh = new FileHandler();
		DataModel dm = new DataModel(fh.getEventsNames(file_path));

		sl = new SimpleList(dm);
		layeredPane.add(ReplayUtil.getJScrollPane(8, 100, 250, 185, "Events",
				sl));

		listSource = new ListSource();
		transferList = null;
		transferList = new BaseList();

		transferList.setCellIface(new CellPanel());
		transferList.setSource(listSource);

		layeredPane.add(ReplayUtil.getJScrollPane(335, 100, 250, 185, "Plan",
				transferList));

		ReplayUtil.countProgress();
		publish(InitializeView.progressValue);

		jFileChooser = new JFileChooser();
		ReplayUtil.countProgress();
		publish(InitializeView.progressValue);
		jFileChooser.setCurrentDirectory(file_path);
		ReplayUtil.countProgress();
		publish(InitializeView.progressValue);
		jFileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		ReplayUtil.countProgress();
		publish(InitializeView.progressValue);
	}

	private void initResource() {
		iconList = new ArrayList<ImageIcon>();
		for (int i = 1; i < 17; i++) {
			ImageIcon icon = new ImageIcon(".\\res\\image\\ProgressGear" + i
					+ "_Gray_small@2x.png");
			ReplayUtil.countProgress();
			publish(InitializeView.progressValue);
			icon.setImage(icon.getImage().getScaledInstance(50, 50,
					Image.SCALE_DEFAULT));
			iconList.add(icon);
			ReplayUtil.countProgress();
			publish(InitializeView.progressValue);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getSource());

		if (e.getSource() == btn_start_record) {
			layeredPane.setLayer(btn_start_record, 200);
			layeredPane.setLayer(btn_stop_record, 300);
			btn_start_record.setEnabled(false);
			btn_stop_record.setEnabled(true);
			RunningState.flag = true;
			progessLabel.setVisible(true);
			stateLabel.setVisible(true);
			if (eh.pushXFile("event_record") == -1) {
				msgDialog(EventHandler.errorMsg);
				eh.stop();
				layeredPane.setLayer(btn_start_record, 300);
				layeredPane.setLayer(btn_stop_record, 200);
				btn_start_record.setEnabled(true);
				btn_stop_record.setEnabled(false);
				RunningState.flag = false;
				stateLabel.setVisible(false);
				progessLabel.setVisible(false);
				btn_start_replay.setEnabled(true);
				return;
			}
			eh.startRecord(replayFrame, btn_stop_record);
			new Progress(record_stateStrings).start();
			btn_start_replay.setEnabled(false);
			return;
		}
		if (e.getSource() == btn_stop_record) {
			eh.stop();
			layeredPane.setLayer(btn_start_record, 300);
			layeredPane.setLayer(btn_stop_record, 200);
			btn_start_record.setEnabled(true);
			btn_stop_record.setEnabled(false);
			RunningState.flag = false;
			stateLabel.setVisible(false);
			progessLabel.setVisible(false);
			btn_start_replay.setEnabled(true);
			// display jfilechooser
			jFileChooser
					.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int result = jFileChooser.showSaveDialog(replayFrame);
			if (result == JFileChooser.APPROVE_OPTION) {
				eh.saveEvent(replayFrame, jFileChooser.getSelectedFile()
						.getName());
			}
			return;
		}

		if (e.getSource() == btn_start_replay) {
			layeredPane.setLayer(btn_start_replay, 200);
			layeredPane.setLayer(btn_stop_replay, 300);
			btn_start_replay.setEnabled(false);
			btn_stop_replay.setEnabled(true);
			RunningState.flag = true;
			progessLabel.setVisible(true);
			stateLabel.setVisible(true);
			// 开始重播
			if (eh.pushXFile("event_replay") == -1) {
				msgDialog(EventHandler.errorMsg);
			}
			eh.startReplay(btn_stop_replay, replayFrame, file_path,
					transferList.getTotalCell());
			new Progress(replay_stateStrings).start();
			btn_start_record.setEnabled(false);
			btn_add.setEnabled(false);
			btn_move_up.setEnabled(false);
			btn_nmove_down.setEnabled(false);
			btn_del.setEnabled(false);
			return;
		}
		if (e.getSource() == btn_stop_replay) {

			eh.stop();

			layeredPane.setLayer(btn_start_replay, 300);
			layeredPane.setLayer(btn_stop_replay, 200);
			btn_start_replay.setEnabled(true);
			btn_stop_replay.setEnabled(false);
			RunningState.flag = false;
			stateLabel.setVisible(false);
			progessLabel.setVisible(false);
			btn_start_record.setEnabled(true);
			btn_add.setEnabled(true);
			btn_move_up.setEnabled(true);
			btn_nmove_down.setEnabled(true);
			btn_del.setEnabled(true);
			return;
		}

		if (e.getActionCommand() == "Open Events") {
			jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int result = jFileChooser.showOpenDialog(replayFrame);
			if (result == JFileChooser.APPROVE_OPTION) {

				Thread thread = new Thread() {
					public void run() {
						file_path = jFileChooser.getSelectedFile();
						String[] a = file_path.list();
						sl.delAll();
						for (int i = 0; i < a.length; i++) {
							System.out.println(a[i]);
							sl.addElement(a[i]);
						}
					}
				};
				DlgProgess.show(replayFrame, thread);
			}
			return;
		}
		if (e.getSource() == btn_add) {
			if (sl.getSelectedValue() != null) {
				listSource.addCell(sl.getSelectedValue());
				System.out.println(listSource.getCell(0));
				// transferList.
			}
			return;
		}
		if (e.getSource() == btn_del) {
			listSource.removeCell(transferList.getSelectIndex());
			return;
		}
		if (e.getSource() == btn_move_up) {
			System.out.println("aaaa  " + transferList.getSelectIndex());
			listSource.moveUp(transferList.getSelectIndex());
			return;
		}
		if (e.getSource() == btn_nmove_down) {
			listSource.moveDown(transferList.getSelectIndex());
			return;
		}
		if (e.getSource() == btn_refresh) {
			Thread thread = new Thread() {
				public void run() {
					String[] a = file_path.list();
					sl.delAll();
					for (int i = 0; i < a.length; i++) {
						System.out.println(a[i]);
						sl.addElement(a[i]);
					}
				}
			};
			DlgProgess.show(replayFrame, thread);
			return;
		}
		if (e.getActionCommand() == "About Replay") {
			new AboutReplay(replayFrame);
		}
		if (e.getActionCommand() == "Exit") {
			exitDialog();
		}

	}

	private void exitDialog() {
		int result = JOptionPane.showConfirmDialog(replayFrame, "确定要退出吗?",
				"退出程序", JOptionPane.OK_CANCEL_OPTION);
		if (JOptionPane.OK_OPTION == result) {
			System.exit(0);
		}
	}

	private void msgDialog(String message) {
		JOptionPane.showMessageDialog(replayFrame, message, "WARNING",
				JOptionPane.WARNING_MESSAGE);
	}

	@Override
	protected JFrame doInBackground() throws Exception {
		// TODO Auto-generated method stub
		initialize();
		return null;
	}

	@Override
	protected void process(List<Integer> chunks) {
		// TODO Auto-generated method stub
		super.process(chunks);
		for (int i = 0; i < chunks.size(); i++) {
			InitializeView.progressBar.setValue(chunks.get(i));
			if (chunks.get(i) > 50) {
				chunks.set(i, 50);
			}
			InitializeView.percentLabel.setText(chunks.get(i) * 2 + "%");
		}
	}

	@Override
	protected void done() {
		// TODO Auto-generated method stub
		super.done();
		System.out.println(replayFrame);

		replayFrame.setVisible(true);
		InitializeView.jFrame.setVisible(false);
	}

}
