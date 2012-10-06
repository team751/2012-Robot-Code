package org.team751.commands.nommer2;

import edu.wpi.first.wpilibj.Timer;
import org.team751.commands.CommandBase;

/**
 * Feeds balls up into the shooter. Runs momentarily.
 * @author Sam Crow
 */
public class Feed extends CommandBase {
    
    private Timer timer = new Timer();
    /**
     * Number of seconds to run before ending this command
     */
    private double kTimeout = 0.1;
    
    public Feed() {
        requires(nommerTop);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        timer.start();
        nommerTop.feedUp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() >= kTimeout;
    }

    // Called once after isFinished returns true
    protected void end() {
        nommerTop.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
