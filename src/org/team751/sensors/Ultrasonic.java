package org.team751.sensors;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author samcrow
 */
public class Ultrasonic extends AnalogChannel{
    /**
     * Constructor
     * @param channel The analog channel
     */
    public Ultrasonic(int channel) {
        super(channel);
		setAverageBits(10);//Set up a lot of averaging to reduce the effect of noise and voltage spikes
    }

    /**
     * Calculate and return the distance in inches sensed by the sensor
     * @return The number of inches to the nearest object detected by the sensor
     */
    public double getDistance () {
        double voltage = getAverageVoltage();
        double distance = voltage * 84.4489 + -3.5425;
        return distance;
    }
}
