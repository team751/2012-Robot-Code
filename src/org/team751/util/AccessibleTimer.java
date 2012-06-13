package org.team751.util;

import edu.wpi.first.wpilibj.Timer;

/**
 * Makes the WPIlib timer different so that its status can be accessed.
 * @author Sam Crow
 */
public class AccessibleTimer extends Timer {

	private boolean isRunning = true;

	public synchronized void start() {
		super.start();
		isRunning = true;
	}

	public synchronized void stop() {
		super.stop();
		isRunning = false;
	}

	public boolean isRunning(){
		return isRunning;
	}

}
