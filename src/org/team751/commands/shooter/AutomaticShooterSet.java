package org.team751.commands.shooter;

import org.team751.commands.CommandBase;
import org.team751.commands.shooter.ShooterLookupTable.ShooterDataPoint;
import org.team751.util.logging.LogLevel;
import org.team751.util.logging.Logger;

/**
 * Sets the shooter angle and power automatically using lookup tables
 * @author Sam Crow
 */
public class AutomaticShooterSet extends CommandBase {

	public AutomaticShooterSet() {
		// Use requires() here to declare subsystem dependencies
		requires(shooterWheels);
		requires(shooterTread);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		ShooterDataPoint[] data = ShooterLookupTable.data;

		final double actualDistance = shooterWheels.getUltrasonicDistance();

		Logger.getInstance().log("Automatically setting shooter. Distance = "+actualDistance, LogLevel.kDebug);

		//See if the current distance is less than the shortest distance that we have a data point for
		if(data[0].distance > actualDistance){
			ShooterDataPoint closestPoint = data[0];//If so, just use the data point with the shortest distance
			//Just set the target values for this point
			shooterWheels.setOpenLoopSpeed(closestPoint.motorPower);
			shooterTread.setTargetAngle(closestPoint.angle);
			Logger.getInstance().log("Current distance is less than the distance of the lowest-distance data point. Using angle "+closestPoint.angle+", power "+closestPoint.motorPower, LogLevel.kWarning);
		} else
		//See if the current distance is greater than the longest distance that we have a data point for
		if(data[data.length-1].distance < actualDistance){
			ShooterDataPoint closestPoint = data[data.length-1];//If so, just use the data point with the longest distance
			//Just set the target values for this point
			shooterWheels.setOpenLoopSpeed(closestPoint.motorPower);
			shooterTread.setTargetAngle(closestPoint.angle);
			Logger.getInstance().log("Current distance is greater than the distance of the highest-distance data point. Using angle "+closestPoint.angle+", power "+closestPoint.motorPower, LogLevel.kWarning);
		}
		//Otherwise, find the two closes points, one above and one below
		else {
			//Create references to the data points lower and higher in distance
			ShooterDataPoint lowerDistancePoint = null, higherDistancePoint = null;
			//data[0] must have a lower distance than the current distance
			boolean hasFoundDataPoint = false;//Set this to true when valid data points are found.
			for(int i = 0; i < data.length; i++){
				if(data[i].distance > actualDistance){
					hasFoundDataPoint = true;
					//With data[i], the iteration has passed the current state
					higherDistancePoint = data[i];//So data[i] is closest data point with a higher distance value
					lowerDistancePoint = data[i-1];//And the point below that in distance order is the closes one with a lower distance value
					Logger.getInstance().log("Using data points "+(i-1)+" and "+i, LogLevel.kDebug);
					break;
				}
			}

			//Check for errors
			if(!hasFoundDataPoint || lowerDistancePoint == null || higherDistancePoint == null){
				//The data point wasn't found in the data set. Something's wrong.
				Logger.getInstance().log("Valid data points for interpolation were not found in the data set. Double-check that the data set is ordered properly and that there are no programming errors. Now trying to use the two shortest-distance data points.", LogLevel.kError);
				//Assign some low-power values as backup
				lowerDistancePoint = data[0];
				try {
					//If there is a data point indexed 1, set it to the hgiher distance point
					higherDistancePoint = data[1];
				} catch(ArrayIndexOutOfBoundsException e){
					//No data[1]! Level 2 contingency!
					Logger.getInstance().log("No data[1]! Using a hard-coded default for the higher-distance interpolation point.", LogLevel.kError);
					higherDistancePoint = new ShooterDataPoint(20, 0.3, 3);//Assign some hard-coded data that hopefully won't make the robot destroy itself
				}
			}

			//Interpolate linearly between the higher and lower distance points
			//Get the Ædistance between the nearby points. This should always be positive.
			double distanceDifferenceBetweenPoints
					= higherDistancePoint.distance - lowerDistancePoint.distance;
			//Get the Ædistance between the current state and the lower distance.
			//This should also always be positive.
			double distanceDifferenceFromLowerDistancePoint //These variable names are getting huge
					= actualDistance - lowerDistancePoint.distance;

			// Get the proportion of this distance between the two points
			// So the current distance is (proportion * 100%) percent from the lower distance point to the higher distance point
			double proportion
					= distanceDifferenceFromLowerDistancePoint / distanceDifferenceBetweenPoints;

			Logger.getInstance().log("Proportion between points: "+proportion, LogLevel.kDebug);

			//Now that we have the proportion, calculate the corresponding other values
			//These are split into seperate functions.
			double actualTargetPower = interpolatePower(lowerDistancePoint, higherDistancePoint, proportion);
			double actualTargetAngle = interpolateAngle(lowerDistancePoint, higherDistancePoint, proportion);

			//Compensate
			actualTargetAngle -= 0.1;

			Logger.getInstance().log("Target power "+actualTargetPower+", target angle "+actualTargetAngle, LogLevel.kDebug);

			//Now we have target speed and angle values, so set them.
			shooterWheels.setOpenLoopSpeed(actualTargetPower);
			shooterTread.setTargetAngle(actualTargetAngle);
		}


	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}


	/**
	 * Interpolate and find a power value a given proportion between two data points.
	 * If, in some eventuality, motor power decreased with a distance increase,
	 * this function would work as expected.
	 * @param lowPoint The data point with a lower distance value to interpolate
	 * @param highPoint The data point with a higher distance value to interpolate
	 * @param proportion The proportion from highPoint to lowPoint of the current state
	 * @return The interpolated power value.
	 */
	protected double interpolatePower(ShooterDataPoint lowPoint, ShooterDataPoint highPoint, double proportion){
		//Get the Æpower
		double powerDifference = highPoint.motorPower - lowPoint.motorPower;

		double interpolatedPower = lowPoint.motorPower + proportion * powerDifference;
		return interpolatedPower;
	}

	/**
	 * Interpolate and find an angle value a given proportion between two data points.
	 * If, in some eventuality, angle decreased with a distance increase,
	 * this function would work as expected.
	 * @param lowPoint The data point with a lower distance value to interpolate
	 * @param highPoint The data point with a higher distance value to interpolate
	 * @param proportion The proportion from highPoint to lowPoint of the current state
	 * @return The interpolated angle value.
	 */
	protected double interpolateAngle(ShooterDataPoint lowPoint, ShooterDataPoint highPoint, double proportion){
		//Get the Æangle
		double angleDifference = highPoint.angle - lowPoint.angle;

		double interpolatedPower = lowPoint.angle + proportion * angleDifference;
		return interpolatedPower;
	}
}
