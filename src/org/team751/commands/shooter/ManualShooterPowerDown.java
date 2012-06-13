/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.commands.shooter;

import org.team751.commands.CommandBase;
import org.team751.util.logging.LogLevel;
import org.team751.util.logging.Logger;

/**
 *
 * @author darbusdumbledore
 */
public class ManualShooterPowerDown extends CommandBase {

    /**
     *
     */
    public ManualShooterPowerDown() {
        requires(shooterWheels);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        double powerGoal = shooterWheels.getOpenLoopSpeed();
        powerGoal -= 0.1;
        shooterWheels.setOpenLoopSpeed(powerGoal);

        Logger.getInstance().log("New shooter power target: "+powerGoal, LogLevel.kDebug);
    }

    // Called repeatedly when this Command is scheduled to run
    //This decreases the motor power by 0.1
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
