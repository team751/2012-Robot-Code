package org.team751.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import java.util.Timer;
import java.util.TimerTask;
import org.team751.RobotMap;
import org.team751.util.logging.LogLevel;
import org.team751.util.logging.Logger;

/**
 * This subsystem manages the tread-based angle adjusting mechanism of the shooter
 */
public class ShooterTread extends Subsystem {
   private Relay relay = new Relay(RobotMap.shooterRollersChannel);
   private AnalogChannel potentiometer = new AnalogChannel(RobotMap.shooterPotentiometer);

    //PID constants for the angle one: Need tuning
   /**
    * The P constant
    */
   public static final double kP = 0.3;

   public static final double kUpperAngleLimit = 4.622;
   public static final double kLowerAngleLimit = 3.726;


   private Timer pid = new Timer();
   private ShooterTreadPIDTask task;


	public ShooterTread(){
		task = new ShooterTreadPIDTask(this);
		task.targetVoltage = kLowerAngleLimit;
		//pid.schedule(task, 0, 100);//Schedule task repeating every 100 miliseconds

		potentiometer.setOversampleBits(2);

	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	public void angleUp(){
		if(getActualVoltage() < kUpperAngleLimit){
			relay.set(Value.kForward);//Reverse = voltage & angle up
		}else{
			relay.set(Value.kOff);
		}
                
                Logger.getInstance().log("Potentiometer: "+getActualVoltage(), LogLevel.kDebug);
	}

	public void angleDown(){
		if(getActualVoltage() > kLowerAngleLimit){
			relay.set(Value.kReverse);//Forward = voltage & angle down
		}else{
			relay.set(Value.kOff);
		}
                
                
                Logger.getInstance().log("Potentiometer: "+getActualVoltage(), LogLevel.kDebug);
	}

	public void stop(){
		relay.set(Value.kOff);
	}

	public double getActualVoltage() {
		return potentiometer.getAverageVoltage();
	}

	public void setPIDEnabled(boolean input){
		task.enabled = input;
	}
	public boolean getPIDEnabled(boolean input){
		return task.enabled;
	}

	public double getTargetAngle() {
		return task.getTargetVoltage();
	}

	public void setTargetAngle(double input){
		if(input > kUpperAngleLimit){
			input = kUpperAngleLimit;
		}
		if(input < kLowerAngleLimit){
			input = kLowerAngleLimit;
		}
		task.targetVoltage = input;
	}


	private class ShooterTreadPIDTask extends TimerTask{

		/**
		 * The difference, in volts in either direction, that the value must
		 * be within for the motor to stop on target
		 */
		protected static final double kTolerance = 0.05;

		public boolean enabled = true;

		public double targetVoltage = 0;

		private ShooterTread tread;

		public ShooterTreadPIDTask(ShooterTread tread){
			this.tread = tread;


		}

		public void run() {
                    
			if(enabled){
				if(Math.abs(targetVoltage - tread.getActualVoltage()) < kTolerance){//on target
					tread.stop();//Stop
				} else if(tread.getActualVoltage() > targetVoltage){
					tread.angleDown();
				}else if(tread.getActualVoltage() < targetVoltage){
					tread.angleUp();
				}else{
					//This shouldn't happen
					Logger.getInstance().log("None of the expected cases was met in the shooter angle control loop. Check the programming.", LogLevel.kWarning);
				}
			}
		}


		public void setTargetVoltage(double input){
			targetVoltage = input;
		}

		private double getTargetVoltage() {
			return targetVoltage;
		}

	}
}