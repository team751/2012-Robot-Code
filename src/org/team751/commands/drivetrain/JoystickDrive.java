/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.commands.drivetrain;

import org.team751.commands.CommandBase;

/**
 *
 * @author darbusdumbledore
 */
public class JoystickDrive extends CommandBase {
    
    /**
     * 
     */
    public JoystickDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivetrain.setEnabled(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double x = oi.getDriveJoystick().getX();
        double y = oi.getDriveJoystick().getY();
        drivetrain.arcadeDrive(y, x);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        drivetrain.setEnabled(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
