package org.team751.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import java.util.TimerTask;

/**
 * Extends ShooterWheels and adds support for PID to set the
 * @author samcrow
 */
public class PIDShooterWheels extends ShooterWheels {
	protected PIDController pid = new PIDController(1.0E-3, 1.0E-4, 1.0E-4, encoder,
													jaguar, 0.01); //Time in seconds between calculations

	public PIDShooterWheels(){
		super();

		pid.setOutputRange(0, 1);//Another thing to prevent reverse motor setting
		pid.disable();//Ensure that the motor is disabled by default
	}

	/**
	 * Set the target rate of the shooter motors. This will not turn the motors
	 * on.
	 * @param targetRate the desired rate in degrees per second
	 */
	public void setRate(double targetRate) {
		pid.setSetpoint(targetRate);
	}

	/**
	 * Get the current target rate of the shooter wheels. This value
	 * is not dependent on whether the shooter wheels are actually set to be
	 * running.
	 * @return The current rate target in degrees per second
	 */
	public double getRate() {
		return pid.getSetpoint();
	}

	/**
	 * Enable the shooter wheels so that they start turning
	 */
	public void enable() {
		pid.enable();
	}

	/**
	 * Disable the shooter wheels so that they stop turning
	 */
	public void disable() {
		pid.disable();
	}

	protected class ShooterWheelRateTask extends TimerTask {

		public void run() {
			double actualRate = encoder.getRate();
			
		}

	}

}
