/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import java.util.Timer;
import java.util.TimerTask;
import org.team751.RobotMap;
import org.team751.sensors.Ultrasonic;
import org.team751.util.logging.LogLevel;
import org.team751.util.logging.Logger;

/**
 * Controls the shooter wheels and their speed setting.
 * This class does not use PID. The subclass PIDShooterWheels does.
 * For PID, the maximmum speed is around 20,000 degrees per second.
 * @author darbusdumbledore
 */
public class ShooterWheels extends Subsystem {

    /** The Jaguar (actually two jaguars) that control the shooter wheels */
    protected SpeedController jaguar = new Jaguar(RobotMap.shooterWheelChannel);

	/**
	 * The power level from 0 to 1 to target. This is stored here so that it can
	 * be saved while the shooter wheels are turned off.
	 */
	protected double targetPower = 0;

	/**
	 * The ultrasonic sensor
	 */
	protected Ultrasonic ultrasonic = new Ultrasonic(RobotMap.ultrasonicChannel);
    /**
     * The encoder
     */
    protected Encoder encoder = new Encoder(RobotMap.shooterEncoderA, RobotMap.shooterEncoderB);


	protected Timer speedTimer;
	protected static final int speedDelay = 10;//Number of miliseconds between speed calculations
	protected ShooterWheelPIDTask task = new ShooterWheelPIDTask();

	/** The maximum rate at full power in degrees per second */
	public static final double kMaxRate = 18000;


	public ShooterWheels(){

		encoder.start();

		speedTimer = new Timer();
		speedTimer.schedule(task, 0, speedDelay);//Set the encoder speed task to run every 1/2 second
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    /**
     * Get the distance in inches measured from the ultrasonic sensor to the nearest object
     * @return The distance in inches
     */
    public double getUltrasonicDistance(){
        return ultrasonic.getDistance();
    }

	public double getUltrasonicVoltage(){
		return ultrasonic.getAverageVoltage();
	}

	public double getActualRate(){
		//return task.getSpeed();
		return encoder.getRate();
	}

	public int getEncoderCount(){
		return encoder.get();
	}

	/**
	 * Set the motor power
	 * @param speed The power from 0 to 1 to apply
	 */
	public void setOpenLoopSpeed(double speed){
		targetPower = speed;
	}

	/**
	 * Get the current speed setting of the motor for open-loop mode
	 * @return The speed from 0 to 1
	 */
	public double getOpenLoopSpeed(){
		return targetPower;
	}
	/**
	 * Enable the shooter wheels so that they start turning
	 */
	public void enable(){
		task.enabled = true;
	}

	/**
	 * Disable the shooter wheels so that they stop turning.
	 * The previous power level is preserved in memory.
	 */
	public void disable() {
		task.enabled = false;
	}

	public double getTargetRate() {
		return targetPower * kMaxRate;
	}


	protected class ShooterWheelPIDTask extends TimerTask {

		public boolean enabled = false;

		public void run() {
                    double actualRate = encoder.getRate();
                    double targetRate = targetPower * kMaxRate;
                                
//                    Logger.getInstance().log("Actual rate "+actualRate+" target rate "+targetRate, LogLevel.kDebug);

			if(enabled){
				
				if(targetRate > actualRate) {
					jaguar.set(1);
//                                        Logger.getInstance().log("Motor on", LogLevel.kDebug);
				}else {
					jaguar.set(0);
//                                        Logger.getInstance().log("Motor off", LogLevel.kDebug);
				}

			}else{
				jaguar.set(0);
			}
		}

	}
}
