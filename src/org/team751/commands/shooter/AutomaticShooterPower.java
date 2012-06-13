/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.commands.shooter;

import org.team751.commands.CommandBase;
import org.team751.subsystems.ShooterWheels;

/**
 * Revert to revision 181 to bring back the former, more complex, way.
 * Various things had to be removed for various updates.
 * The new AutomaticShooterSet command sets speed and angle using a simpler method.
 */
public class AutomaticShooterPower extends CommandBase {

    public AutomaticShooterPower() {
        requires(shooterWheels);
    }


    // Called just before this Command runs the first time
    protected void initialize() {

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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
