package org.team751.commands.bridgepush;

import org.team751.commands.CommandBase;

/**
 * This command retracts the bridge mechanism until it has fully retracted (until
 * the top limit switch is pressed).
 * @author Sam Crow
 */
public class BridgePushFullyRetract extends CommandBase {

	public BridgePushFullyRetract() {
		// Use requires() here to declare subsystem dependencies
		requires(bridgePush);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(!bridgePush.topLimitSwitchPressed()){
			bridgePush.rotateUp();
		}else{
			bridgePush.stop();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return bridgePush.topLimitSwitchPressed();
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
