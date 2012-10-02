package org.team751;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;

    /** PWM channel for left drivetrain Jaguar */
    public static final int drivetrainLeft = 1;
    /** PWM channel for right drivetrain Jaguars */
    public static final int drivetrainRight = 2;

    /** PWM channel for shooter wheel Jaguar */
    public static final int shooterWheelChannel = 3;

    /** Relay channel for shooter tread adjustment roller Spike */
    public static final int shooterRollersChannel = 8;

    /** Relay channel for nommer intake roller Spike */
    public static final int nommerIntakeRoller = 1;
    /** Relay channel for nommer elevator Spike */
    public static final int nommerElevator = 2;
    /** Relay channel for nommer top roller/counter-roller Spike */
    public static final int nommerTopRoller = 3;
	public static final int nommerTopRollerVictor = 6;

    /** PWM channel for the bridge mechanism Victor */
    public static final int bridgeMotorChannel = 5;

	/** Digital channel for the limit switch that triggers when the bridge mechanism is at its top limit */
	public static final int bridgeTopLimitSwitch = 13;
	/** Digital channel for the limit switch that triggers when the bridge mechanism is at its bottom (extended) limit */
	public static final int bridgeBottomLimitSwitch = 14;


    /** Analog channel for gyroscope rotation */
    public static final int gyroscopeRotation = 1;
    /** Analog channel for gyroscope temperature */
    public static final int gyroscopeTemperature = 2;
    /** Analog channel for the left motor temperature sensor */
    public static final int leftMotorTemperature = 3;
    /** Analog channel for the right motor temperature sensor */
    public static final int rightMotorTemperature = 4;
    /** Analog channel for the ultrasonic sensor */
    public static final int ultrasonicChannel = 5;
    /** Digital channel for the PWM interface with the ultrasonic sensor */

    /** Analog channel for the shooter height potentiometer */
    public static final int shooterPotentiometer = 6;

    /**
     * Channel A for the shooter encoder
     */
    public static final int shooterEncoderA = 5;
    /**
     * Channel B for the shooter encoder
     */
    public static final int shooterEncoderB = 6;//For the 2011 robot: Change to 11


    /** Relay channel for left motor fan */
    public static final int leftFan = 5;
    /** Relay channel for right motor fan */
    public static final int rightFan = 6;

    /**
     * Digital I/O channel for the top photoelectric switch
     */
    public static final int photoswitchTop = 7;
    /**
     * Digital I/O channel for the middle photoelectric switch
     */
    public static final int photoswitchMiddle = 8;
    /**
     * Digital I/O channel for the bottom photoelectric switch
     */
    public static final int photoswitchBottom = 9;

    //Drivetrain encoders
    /**
     * Left drivetrain encoder channel A
     */
    public static final int driveLeftEncoderA = 1;
    /**
     * Left drivetrain encoder channel B
     */
    public static final int driveLeftEncoderB = 2;
    /**
     * Right drivetrain encoder channel A
     */
    public static final int driveRightEncoderA = 3;
    /**
     * Right drivetrain encoder channel B
     */
    public static final int driveRightEncoderB = 4;

    /** PWM channel for the Victor used for the lower section of the nommer */
    public static final int lowerNommerVictor = 4;

    public static final int lightsChannel = 10;

}
