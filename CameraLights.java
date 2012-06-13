package org.team751.subsystems;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team751.RobotMap;

/**
 * This subsysytem controls the lights that provide reflective illumination for camera tracking
 * @author Sam Crow
 */
public class CameraLights extends Subsystem {
	/**
	 * The PWM channel
	 */
	protected PWM output = new PWM(RobotMap.lightsChannel);

	/** The reference power level */
	public static final double kPower = 0.3;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	/**
	 * Set the LEDs to illuminate at a given power level
	 * @param input The power level, 0-1
	 */
	public void setPower(double input){
		output.setRaw(powerLevelToPWMCycle(input));
	}

	public void off(){
		setPower(0);
	}

	public void on(){
		setPower(kPower);
	}

	/**
	 * Convert a power level to a PWM duty cycle
	 * @param input The power from 0 to 1
	 * @return The corresponding PWM duty cycle from 0 to 255
	 */
	protected static int powerLevelToPWMCycle(double input){
		int dutyCycle = (int)Math.round(255 * input);

		return dutyCycle;
	}
}
