/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.commands.nommer;

import edu.wpi.first.wpilibj.Timer;
import org.team751.commands.CommandBase;

/**
 * This command ejects balls from the nommer. It never ends. It is designed
 * to be used with a button so that it is triggered when the button is pressed
 * and then terminated by the triggering of another nommer command when
 * the button is released.
 * <p/>
 * @author Sam Crow
 */
public class SlowIntake extends CommandBase {

	private Timer t;

	/**
	 *
	 */
	public SlowIntake() {
		// Use requires() here to declare subsystem dependencies
		requires(nommer);
		t = new Timer();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		t.reset();
		t.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		nommer.setElevatorPower(1);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return t.get() > 0.1;
	}

	// Called once after isFinished returns true
	protected void end() {
		nommer.ballsStop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
