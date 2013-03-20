package com.xue.events;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wei.xue
 * @data 2013-01-24
 */

public class FileHandler {

//	public List<Plan> getEventsList(File file) {
//		List<Plan> eventsList = new ArrayList<Plan>();
//		for (String s : file.list()) {
//			Plan event = new Plan(file.getAbsolutePath() + File.separator
//					+ s, s);
//			eventsList.add(event);
//		}
//		return eventsList;
//	}

	public List<String> getEventsNames(File file) {
		List<String> eventsList = new ArrayList<String>();
		for (String s : file.list()) {
			eventsList.add(s);
		}
		return eventsList;
	}
}
