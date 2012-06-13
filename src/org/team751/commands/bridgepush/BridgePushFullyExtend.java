package org.team751.commands.bridgepush;

import org.team751.commands.CommandBase;

/**
 * This command extends the bridge mechanism until it has fully retracted (until
 * the top limit switch is pressed).
 * <strong>Warning: Depending on the mechanical design of the system, the actuator
 * may run into the ground before the limit switch is pressed.</strong> In this case,
 * the motor will be stalled for as long as this command is running. Use this
 * command with caution.
 * @author Sam Crow
 */
public class BridgePushFullyExtend extends CommandBase {

	public BridgePushFullyExtend() {
		// Use requires() here to declare subsystem dependencies
		requires(bridgePush);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(!bridgePush.bottomLimitSwitchPressed()){
			bridgePush.rotateDown();
		}else{
			bridgePush.stop();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return bridgePush.bottomLimitSwitchPressed();
	}

	// Called once after isFinished returns true
	protected void end() {
		//Ensure that it stops
		bridgePush.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
