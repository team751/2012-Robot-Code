package org.team751.util;

import edu.wpi.first.wpilibj.Jaguar;

/**
 * This class extends Jaguar and implements software safeguards to prevent
 * being set to a reverse value.
 * @author Sam Crow
 */
public class ForwardOnlyJaguar extends Jaguar {

	/**
	 * The one obligatory constructor. See the Jaguar class documentation
	 * for the actual documentation of this constructor.
	 * @param channel The PWM channel
	 */
	public ForwardOnlyJaguar(int channel){
		super(channel);
	}

	public void set(double speed) {
		speed = limitForward(speed);
		super.set(speed);
	}

	public void pidWrite(double output) {
		output = limitForward(output);
		super.pidWrite(output);
	}



	/**
	 * Limit a value to the range of 0 to +1 inclusive. Input of less than zero
	 * will become zero. Input of greater than +1 will become 1.
	 * @param input The value to limit
	 * @return The limited value.
	 */
	protected static double limitForward(double input){
		if(input < 0){
			input = 0;
		}
		if(input > 1){
			input = 1;
		}
		return input;
	}
}
