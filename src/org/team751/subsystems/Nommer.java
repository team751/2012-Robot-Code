/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team751.RobotMap;
import org.team751.util.SlowStartRelay;

/**
 * Manages the intake roller and ball conveyer of the nommer mechanism
 */
public class Nommer extends Subsystem {

    /**
     *
     */
    protected Relay nommerBottomRoller = new Relay(RobotMap.nommerIntakeRoller);
    //protected Relay nommerConveyer = new Relay(RobotMap.nommerElevator);
    /**
     *
     */
    protected Victor nommerConveyorVictor;

    /**
     *
     */
    protected DigitalInput bottomPosition = new DigitalInput(RobotMap.photoswitchTop);
    /**
     *
     */
    protected DigitalInput middlePosition = new DigitalInput(RobotMap.photoswitchMiddle);
    /**
     *
     */
    protected DigitalInput topPosition = new DigitalInput(RobotMap.photoswitchBottom);

    /**
     *
     */
    public static final double kVictorPower = 1.0;

    /**
     *
     */
    public Nommer(){
        nommerConveyorVictor = new Victor(RobotMap.ballConveyorVictor);
        nommerConveyorVictor.setSafetyEnabled(false);//Disable MotorSafety
    }


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        //setDefaultCommand(new AutoNom());
    }

    /*
     * With these, we'll need to figure out which way these relays actually are
     * in terms of forwards and backwards, because I think it's sorta arbitrary.
     */
    /** Rotate the intake roller to pull balls in */
    public void rollerIn() {
        nommerBottomRoller.set(Value.kForward);
    }
    /** Rotate the intake roller to push balls out */
    public void rollerOut() {
        nommerBottomRoller.set(Value.kReverse);
    }
    /** Stop rotation of the intake roller */
    public void rollerOff() {
        nommerBottomRoller.set(Value.kOff);
    }
    /** Rotate the ball conveyor to move the balls down */
    public void ballsDown() {
        //nommerConveyer.set(Value.kReverse);
        nommerConveyorVictor.set(-kVictorPower);
        //System.out.println("Setting victor to "+(-kVictorPower));
    }
    /** Rotate the ball conveyor to move the balls up */
    public void ballsUp(){
        //nommerConveyer.set(Value.kForward);
        nommerConveyorVictor.set(kVictorPower);
        //System.out.println("Setting victor to "+kVictorPower);
    }
    /** Stop the ball conveyor */
    public void ballsStop() {
        //nommerConveyer.set(Value.kOff);
        nommerConveyorVictor.set(0);
        //System.out.println("Setting victor to 0");
    }

	public void setElevatorPower(double input){
		nommerConveyorVictor.set(input);
	}

    /**
     * Determine if a ball is currently occupying the top position
     * @return If the top position is occupied
     */
    public boolean ballInTopPosition(){
        return topPosition.get();
    }
    /**
     * Determine if a ball is currently occupying the middle position
     * @return If the middle position is occupied
     */
    public boolean ballInMiddlePosition(){
        return middlePosition.get();
    }
    /**
     * Determine if a ball is currently occupying the bottom position
     * @return If the bottom position is occupied
     */
    public boolean ballInBottomPosition(){
        return bottomPosition.get();
    }

	/**
	 * Determine if two balls are in the nommer at the top and middle positions.
	 * @return if the top and middle positions are occupied.
	 */
	public boolean twoBallsAtTop(){
		return ballInTopPosition() && ballInBottomPosition();
	}

    /**
     * Get the number of balls currently held in the mechanism
     * @return The number of balls in the mechanism
     */
    //TODO: Find out how many photoelectric switches the nommer actually has
    public short ballCount(){
        boolean top = topPosition.get();
        boolean middle = middlePosition.get();
        boolean bottom = bottomPosition.get();

        short ballCount = 0;
        //For every sensor that detects a ball, increment the count by 1
        if(top) ballCount++;
        if(middle) ballCount++;
        if(bottom) ballCount++;

        return ballCount;
    }
}
