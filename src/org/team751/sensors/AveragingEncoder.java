package org.team751.sensors;

import edu.wpi.first.wpilibj.Encoder;
import org.team751.util.MovingAverage;

/**
 * This class implements an Encoder that uses the {@link MovingAverage MovingAverage}
 * class to smooth out its rate data.
 * @author Sam Crow
 */
public class AveragingEncoder extends Encoder {

	/** The number of samples that the average value is taken from */
	protected static final int kAveragedSamples = 5;

	protected MovingAverage averager = new MovingAverage(kAveragedSamples);

	//Obligatory one overriden constructor
	public AveragingEncoder(int a, int b){
		super(a, b);
	}

	/**
	 * Get the rate of the encoder rotation in counts per second, averaged
	 * @return The rate
	 */
	public double getRate() {

		double currentRate = super.getRate();
		averager.addValue(currentRate);

		return averager.average();
	}


}
