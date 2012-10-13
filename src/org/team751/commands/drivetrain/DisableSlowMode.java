/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.commands.drivetrain;

import org.team751.commands.CommandBase;

/**
 * Enables drivetrain slow mode, then returns to the default drive command instantly
 * @author Sam Crow
 */
public class DisableSlowMode extends CommandBase {
    
    public DisableSlowMode() {
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivetrain.setSlowModeEnabled(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
