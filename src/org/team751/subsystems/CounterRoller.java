/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team751.RobotMap;

/**
 *
 * @author darbusdumbledore
 */
public class CounterRoller extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    /**
     *
     */
    //protected Relay nommerCounterRoller = new Relay(RobotMap.nommerTopRoller);
	protected Victor victor = new Victor(RobotMap.nommerTopRollerVictor);

	protected static final double kPower = -1;

	public CounterRoller(){
		victor.setSafetyEnabled(false);
	}

    public void initDefaultCommand() {
        //setDefaultCommand(new HoldBalls());
    }

    //We will need to figure out the actual directions for these.
    /** Rotate the counter-roller to feed balls up to the shooter */
    public void feed() {
        victor.set(kPower);
    }
    /** Rotate the counter-roller to reverse-feed balls back down from the shooter */
    public void reverse() {
        victor.set(-kPower);
    }
    /** Stop the counter-roller */
    public void stop() {
        victor.set(0);
    }
}
