package org.team751.commands.bridgepush;

import edu.wpi.first.wpilibj.Timer;
import org.team751.commands.CommandBase;

/**
 * This command momentarily lowers the bridge mechanism. It terminates after one
 * cycle but does not stop the motor. This can be used with Button::whileHeld()
 * to lower the bridge while a button is held down.
 * @author Sam Crow
 */
public class BridgePushMomentaryExtend extends CommandBase {

	Timer timer = new Timer();

	public BridgePushMomentaryExtend() {
		// Use requires() here to declare subsystem dependencies
		requires(bridgePush);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.start();
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
		return timer.get() > 0.1;
	}

	// Called once after isFinished returns true
	protected void end() {
		bridgePush.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
