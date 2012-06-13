/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.commands.counterroller;

import edu.wpi.first.wpilibj.Timer;
import org.team751.commands.CommandBase;

/**
 * This command feeds a ball into the shooter to fire it.
 * Currently feeds for a set amount of time.
 * It could be changed to do this based on some other conditions.
 * @author Programmer
 */
public class Fire extends CommandBase {

    Timer timer;

    /** The number of seconds to feed one ball */
    public static final double kFireTime = 0.3;

    /**
     * Constructor
     */
    public Fire() {
        // Use requires() here to declare subsystem dependencies
        requires(counterRoller);
        requires(nommer);

        timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        timer.start();
        nommer.ballsUp();
        counterRoller.feed();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double time = timer.get();
        if(time > kFireTime * .75){
            nommer.ballsStop();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > kFireTime;//Done if the specified time has passed
    }

    // Called once after isFinished returns true
    protected void end() {
        nommer.ballsStop();
        counterRoller.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}