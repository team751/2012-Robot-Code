/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.commands.shooter;

import org.team751.commands.CommandBase;

/**
 *
 * @author darbusdumbledore
 */
public class AutomaticShooterAngle extends CommandBase {
    
    /**
     * 
     */
    public AutomaticShooterAngle() {
        requires(shooterTread);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //This assumes that needed exit angle has been determined... elsewhere.
        //Put the logic here
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
