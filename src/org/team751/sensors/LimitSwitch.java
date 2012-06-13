package org.team751.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Interfaces with a limit switch.
 * The switch should be wired with ground to the common terminal and the digital
 * I/O line to the normally open terminal.
 * This class inverts values properly so that when the switch is pressed and
 * the circuit between ground and the digital input is closed, the get() function
 * returns true.
 * @author Sam Crow
 */
public class LimitSwitch extends DigitalInput {
	/**
	 * Obligatory constructor. See the DigitalInput constructor documentation.
	 * @param channel The digital I/O channel to use
	 */
	public LimitSwitch(int channel){
		super(channel);
	}

	/**
	 * Determine if the limit switch is pressed
	 * @return True if the limit switch is pressed, otherwise false
	 */
	public boolean get(){
		return !super.get();
	}
}
