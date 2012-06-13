/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.commands.shooter;

import edu.wpi.first.wpilibj.Timer;
import org.team751.commands.CommandBase;

/**
 *
 * @author darbusdumbledore
 */
public class ManualShooterAngleUp extends CommandBase {

	private Timer timer = new Timer();

    public ManualShooterAngleUp() {
        requires(shooterTread);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		double angle = shooterTread.getTargetAngle();
		angle += 0.1;
		shooterTread.setTargetAngle(angle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > 0.05;
    }

    // Called once after isFinished returns true
    protected void end() {
		shooterTread.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		end();
    }
}
