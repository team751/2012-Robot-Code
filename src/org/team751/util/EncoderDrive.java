package org.team751.util;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Extends the RobotDrive class with support for closed-loop control using encoders
 * @author Programmer
 */
public class EncoderDrive extends RobotDrive {
    /**
     * The encoder on the left side of the drivetrain
     */
    /**
     * The encoder on the right side of the drivetrain
     */
    protected Encoder leftEncoder, rightEncoder;

    /** If closed-loop control using the encoders is enabled */
    protected boolean closedLoopEnabled = false;

    /** The number of meters that the robot moves for each encoder pulse */
    public static final double kMetersPerPulse = (15/40.0)*(.2032*Math.PI)/360.0;

    /**
	 * The ideal speed, in meters per second, at which the robot moves when the
     * drivetrain motors are on at full speed
     */
    public static final double kMaxSpeed = 6.0;

    /**
     * This constant determines the strength of the influence that the
     * closed-loop system has over the actual motor power.
     * The actual maximum amount of motor power ratio that is changed is
     * approximately equal to 2 * kInfluenceFactor<br />
     * Increase this value if the loop is not effectively closed.
     * Decrease this value if you experience jittering of the drivetrain.
     */
    public static final double kInfluenceFactor = .3;

    /**
     * Constructor
     * @param leftMotorChannel The PWM channel for the left motors
     * @param rightMotorChannel The PWM channel for the right motors
     * @param leftEncoderA The digital I/O channel for channel A of the left side encoder
     * @param leftEncoderB The digital I/O channel for channel B of the left side encoder
     * @param rightEncoderA The digital I/O channel for channel A of the right side encoder
     * @param rightEncoderB The digital I/O channel for channel B of the right side encoder
     */
    public EncoderDrive(int leftMotorChannel, int rightMotorChannel, int leftEncoderA, int leftEncoderB, int rightEncoderA, int rightEncoderB){
        super(leftMotorChannel, rightMotorChannel);
        leftEncoder = new Encoder(leftEncoderA, leftEncoderB, true);
        rightEncoder = new Encoder(rightEncoderA, rightEncoderB, false);
        leftEncoder.setDistancePerPulse(kMetersPerPulse);
        rightEncoder.setDistancePerPulse(kMetersPerPulse);
        leftEncoder.start();
        rightEncoder.start();
    }

    /** Set the speed of the right and left motors.
     * This is used once an appropriate drive setup function is called such as
     * twoWheelDrive(). The motors are set to "leftSpeed" and "rightSpeed"
     * and includes flipping the direction of one side for opposing motors.
     * <br />This method, as overriden by EncoderDrive, adjusts to compensate
     * for the actual measured speed of the motors.
     * @param leftOutput The speed to send to the left side of the robot.
     * @param rightOutput The speed to send to the right side of the robot.
     */
    public void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {
        //The RobotDrive constructor calls this before the encoders are initialized,
        //so only use the encoders if they have been instantiated
        if(leftEncoder != null && rightEncoder != null){
            //Only close the loop if the system has it enabled
            if(closedLoopEnabled){
                //Invert the values to make forward = positive
                leftOutput = -leftOutput;
                rightOutput = -rightOutput;

                double leftTargetSpeed = leftOutput * kMaxSpeed;
                double rightTargetSpeed = rightOutput * kMaxSpeed;//Calculate the target speed

                double leftSpeed = leftEncoder.getRate();
                double rightSpeed = rightEncoder.getRate();
                SmartDashboard.putDouble("Left target", leftTargetSpeed);
                SmartDashboard.putDouble("Right target", rightTargetSpeed);
                SmartDashboard.putDouble("Left actual", leftSpeed);
                SmartDashboard.putDouble("Right actual", rightSpeed);


                double leftDelta = leftTargetSpeed - leftSpeed;
                double rightDelta = rightTargetSpeed - rightSpeed;
                //Delta values, from testing, fall approximately within the range -2 to +2
                //When the motors are moving forward, the delta value is positive.


                double leftInfluence = leftDelta * kInfluenceFactor;
                double rightInfluence = rightDelta * kInfluenceFactor;


                leftOutput += leftInfluence;
                rightOutput += rightInfluence;

                //Re-invert the values to restore the original
                leftOutput = -leftOutput;
                rightOutput = -rightOutput;
            }
        }
        super.setLeftRightMotorOutputs(leftOutput, rightOutput);
    }

    /**
     * Set if the drivetrain should operate in closed-loop mode with input from encoders
     * @param closedLoopEnabled If closed-loop mode should be enabled
     */
    public void setClosedLoopEnabled(boolean closedLoopEnabled){
        this.closedLoopEnabled = closedLoopEnabled;
    }
}
