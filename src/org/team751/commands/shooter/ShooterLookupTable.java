package org.team751.commands.shooter;

/**
 * This class stores lookup table data for automatically setting the speed and angle
 * of the shooter.
 * @author Sam Crow
 */
public class ShooterLookupTable {

	/**
	 * The set of data points that have been recorded and result in successful scoring.
	 * These must be in order from lowest distance to highest distance.
	 */
	public static final ShooterDataPoint[] data = {
			new ShooterDataPoint(45, 0.6, 3.926)//Fender
			,new ShooterDataPoint(123, 0.8, 4.026)
			,new ShooterDataPoint(184, 0.8, 4.276)
			,new ShooterDataPoint(220, 1, 4.276)
			//Put data points here as they are recorded
	};


	/**
	 * Stores a point of test data for the shooter
	 */
	public static class ShooterDataPoint {

		/**
		 * Constructor
		 * @param distance The distance in inches as reported by the ultrasonic sensor
		 * @param motorPower The amount of power, from 0 to 1, to apply to the motor
		 * @param angle The angle, in volts as reported by the shooter potentiometer, to set the shooter tread to
		 */
		public ShooterDataPoint(double distance, double motorPower, double angle){
			this.distance = distance;
			this.motorPower = motorPower;
			this.angle = angle;
		}

		/**
		 * The distance in inches as reported by the ultrasonic sensor
		 */
		public double distance;
		/**
		 * The amount of power, from 0 to 1, to apply to the motor
		 */
		public double motorPower;
		/**
		 * The angle, in volts as reported by the shooter potentiometer, to set the shooter tread to
		 */
		public double angle;
	}

	/**
	 * Makes this non-constructible
	 */
	private ShooterLookupTable() {
	}
}
