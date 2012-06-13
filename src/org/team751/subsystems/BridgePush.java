/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team751.RobotMap;
import org.team751.sensors.LimitSwitch;

/**
 *
 * @author darbusdumbledore
 */
public class BridgePush extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	/** The power, from 0 to 1, to apply to the motor */
	protected static final double kPower = -1;//inverted

	protected Victor motor = new Victor(RobotMap.bridgeMotorChannel);

	protected LimitSwitch topLimitSwitch = new LimitSwitch(RobotMap.bridgeTopLimitSwitch);
	protected LimitSwitch bottomLimitSwitch = new LimitSwitch(RobotMap.bridgeBottomLimitSwitch);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    /**
     * Set the motor to rotate the mechanism down to lower the bridge
     */
    public void rotateDown() {
        motor.set(kPower);
    }

    /**
     * Set the motor to rotate the mechanism up to retract the mechanism
     */
    public void rotateUp() {
        motor.set(-kPower);
    }

    /**
     * Stop the motor
     */
    public void stop(){
        motor.set(0);
    }

	/**
	 * Get the status of the top limit switch
	 * @return True if the limit switch is pressed, otherwise false.
	 */
	public boolean topLimitSwitchPressed(){
		return topLimitSwitch.get();
	}
	/**
	 * Get the status of the bottom limit switch
	 * @return True if the limit switch is pressed, otherwise false.
	 */
	public boolean bottomLimitSwitchPressed(){
		return bottomLimitSwitch.get();
	}
}
