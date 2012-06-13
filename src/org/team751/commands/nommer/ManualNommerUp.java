/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.commands.nommer;

import org.team751.commands.CommandBase;

/**
 *
 * @author Programmer
 */
public class ManualNommerUp extends CommandBase {
    
    /**
     * 
     */
    public ManualNommerUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(nommer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        nommer.ballsUp();
        nommer.rollerIn();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        nommer.ballsStop();
        nommer.rollerOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
