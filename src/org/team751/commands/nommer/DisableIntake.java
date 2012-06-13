/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.commands.nommer;

import org.team751.commands.CommandBase;

/**
 * This command makes the nommer refuse to intake balls.
 * It continues to feed balls up as usual.
 * @author Sam Crow
 */
public class DisableIntake extends CommandBase {

    /**
     *
     */
    public DisableIntake() {
        // Use requires() here to declare subsystem dependencies
        requires(nommer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        nommer.ballsStop();

        nommer.rollerOut();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!nommer.ballInTopPosition() && (nommer.ballInMiddlePosition() || nommer.ballInBottomPosition())){
            nommer.ballsUp();//Move balls up if there is one or more in the middle/bottom positions and none in the top
        }else{
            nommer.ballsStop();
        }
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
