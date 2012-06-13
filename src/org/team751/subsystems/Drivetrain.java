/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.team751.RobotMap;
import org.team751.commands.drivetrain.JoystickDrive;
import org.team751.util.EncoderDrive;

/**
 *
 * @author darbusdumbledore
 */
public class Drivetrain extends Subsystem {
    //Uncomment the below line and comment the regular robotDrive to enable the experimental encoder-driven features (currently not working)
    EncoderDrive drive = new EncoderDrive(RobotMap.drivetrainLeft, RobotMap.drivetrainRight, RobotMap.driveLeftEncoderA, RobotMap.driveLeftEncoderB, RobotMap.driveRightEncoderA, RobotMap.driveRightEncoderB);
    //RobotDrive drive = new RobotDrive(RobotMap.drivetrainLeft, RobotMap.drivetrainRight);
    
    
    /**
     * 
     */
    public Drivetrain(){
        
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new JoystickDrive());
    }
    /** Arcade drive with given move and rotate values
     * @param moveValue The value from -1 to 1 for forward-backwards movement
     * @param rotateValue The value from -1 to 1 for turning 
     */
    public void arcadeDrive(double moveValue, double rotateValue){
        drive.arcadeDrive(moveValue, rotateValue, true);
    }
    
    //Utility/safety methods
    /**
     * Set if the motor safety system should enable the drivetrain
     * @param enabled If the drivetrain should be enabled
     */
    public void setEnabled(boolean enabled){
        drive.setSafetyEnabled(enabled);
    }
    
    /**
     * Set if the drivetrain should operate in closed-loop mode with input from encoders
     * @param closedLoopEnabled If closed-loop mode should be enabled
     */
    public void setClosedLoopEnabled(boolean closedLoopEnabled){
        drive.setClosedLoopEnabled(closedLoopEnabled);
    }
}
