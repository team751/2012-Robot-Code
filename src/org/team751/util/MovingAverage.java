package org.team751.util;

import com.sun.squawk.util.Arrays;

/**
 * Calculates moving averages (or at least this is what I call a moving average).
 * This class keeps a fixed number of data points and returns upon request
 * the average of them.
 * @author Sam Crow
 */
public class MovingAverage {
	protected double[] values;

	/**
	 * Constructor
	 * @param dataCount The number of data points to keep and take the average of
	 */
	public MovingAverage(int dataCount){
		values = new double[dataCount];
		Arrays.fill(values, 0);
	}

	/**
	 * Constructor that sets each data point to a given initial value
	 * @param dataCount The number of data points to keep and take the average of
	 * @param initialValue The initial value to set every data point to
	 */
	public MovingAverage(int dataCount, double initialValue){
		values = new double[dataCount];
		Arrays.fill(values, initialValue);
	}

	/**
	 * Add a value to the data set and bump the oldest value out
	 * @param value The value to add
	 */
	public void addValue(double value){

		for(int i = values.length - 1; i > 0; i--){
			values[i] = values[i - 1];
		}
		values[0] = value;
	}

	/**
	 * Get the average value of the currently stored data
	 * @return The average.
	 */
	public double average(){
		double total = 0;
		for(int i = 0; i < values.length; i++){
			total += values[i];
		}
		return total / (double)values.length;
	}
}
