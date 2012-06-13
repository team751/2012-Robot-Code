/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.commands.nommer;

import org.team751.commands.CommandBase;
import org.team751.util.AccessibleTimer;
import org.team751.util.logging.LogLevel;
import org.team751.util.logging.Logger;

/**
 * This command manages the Nommer and automatically pulls balls in
 * @author Sam Crow
 */
public class AutoNom extends CommandBase {

	protected AccessibleTimer reverseTimer = new AccessibleTimer();

    /**
     *
     */
    public AutoNom() {
        // Use requires() here to declare subsystem dependencies
        requires(nommer);
        requires(counterRoller);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		reverseTimer.reset();
		reverseTimer.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //Start off with something simple: Use the intake roller constantly,
        //use the conveyor when the top position is not occupied

		/*
		 * To do:
		 * 2 balls in top only
		 * reverse delay
		 */

        if(!nommer.twoBallsAtTop()){
            nommer.rollerIn();
            nommer.ballsUp();
            counterRoller.reverse();
        }else{
			Logger.getInstance().log("Nommer not full.", LogLevel.kDebug);
			//Roller: Stop for 0.1 seconds before reversing
			if(!reverseTimer.isRunning()){//If the timer isn't running, reset & start the timer and turn the intake roller off
				Logger.getInstance().log("Timer not running. Resetting timer, starting timer, and turning intake roller off.", LogLevel.kDebug);
				reverseTimer.reset();
				reverseTimer.start();
				nommer.rollerOff();
			}
			if(reverseTimer.get() < 0.1){
				Logger.getInstance().log("Timer is running and has a time of less than 0.1. Turning intake roller off.", LogLevel.kDebug);
				nommer.rollerOff();//If fewer than 0.1 seconds have passed, keep the roller off
			}else{
				Logger.getInstance().log("Timer value is greater than 0.1 ("+reverseTimer.get()+"). Setting roller to out and stopping timer.", LogLevel.kDebug);
				nommer.rollerOut();//If 0.1 seconds has passed, make the roller roll out and stop the timer
				reverseTimer.stop();
			}


            nommer.ballsStop();
            counterRoller.stop();

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
        counterRoller.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
