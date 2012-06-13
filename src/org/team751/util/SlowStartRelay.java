package org.team751.util;

import edu.wpi.first.wpilibj.Relay;
import java.util.Timer;
import java.util.TimerTask;
import org.team751.util.logging.LogLevel;
import org.team751.util.logging.Logger;

/**
 * Implements delays for reversing a Relay
 * @author Sam Crow
 */
public class SlowStartRelay extends Relay {

	private Value lastValue = Value.kOff;

	private Value slowStartingValue = null;

	private Timer timer = new Timer();
	private RelaySetTask task = new RelaySetTask(Value.kOff);

	/**
	 * Mandatory single overriden constructor
	 * @param channel
	 */
	public SlowStartRelay(int channel){
		super(channel);
	}

	public void set(Value value) {
		if((lastValue.equals(Value.kForward) && value.equals(Value.kReverse)) || (lastValue.equals(Value.kReverse) && value.equals(Value.kForward))){
			//Changing directions
			Logger.getInstance().log("Changing directions to "+value, LogLevel.kDebug);
			slowStartingValue = value;
			task = null;
			task = new RelaySetTask(value);
			timer.schedule(task, 500);//Schedule the thingy to be set in x miliseconds
		}
		else if(slowStartingValue != null){
			//Waiting for slow start
			//Do nothing
		}else{
			Logger.getInstance().log("Setting relay as usual to "+value, LogLevel.kDebug);
			super.set(value);
		}
		lastValue = value;
	}


	private class RelaySetTask extends TimerTask {
		private Value value;

		public RelaySetTask(Value value){
			this.value = value;
			Logger.getInstance().log("RelaySetTask initializing to set to "+value, LogLevel.kDebug);
		}

		public void run() {
			superSet(value);
			Logger.getInstance().log("RelaySetTask setting relay to "+value, LogLevel.kDebug);
			slowStartingValue = null;
		}

	}

	/**
	 * Sets the value in the superclass without doing timing changes
	 * @param value
	 */
	private void superSet(Value value){
		super.set(value);
	}
}
