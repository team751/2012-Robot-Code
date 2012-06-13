package org.team751.sensors;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 * This is a class for the potentiometer on the shooter that implements
 * conversion between voltage and shooter angle
 * @author Sam Crow
 */
public class ShooterPotentiometer extends AnalogChannel {

    /**
     * The voltage of the potentiometer when the tread is configured at its
     * lowest (closest to horizontal) angle as specified by kLowestAngle
     */
    public static final double kVoltageAtLowestAngle = 1;
    /**
     * The angle, in radians above horizontal, at which the ball exits the
     * shooter when the tread is configured at its lowest (closest to horizontal)
     * angle
     */
    public static final double kLowestAngle = Math.PI / 6.0;
    /**
     * The voltage of the potentiometer when the tread is configured at its
     * highest (closest to vertical) angle as specified by kHighestAngle
     */
    public static final double kVoltageAtHighestAngle = 2;
    /**
     * The angle, in radians above horizontal, at which the ball exits the
     * shooter when the tread is configured at its highest (closest to vertical)
     * angle
     */
    public static final double kHighestAngle = Math.PI * (3/8.0);

    /**
     * Constructor with a given analog channel for an analog module in the default slot
     * @param channel
     */
    public ShooterPotentiometer(int channel){
        super(channel);
    }

    /**
     * Get the current angle above horizontal at which a ball will leave the
     * shooter in the current configuration
     * @return The angle above horizontal in radians
	 * @deprecated The system currently does not do mapping between voltage and
	 * angle. Use {@link getVoltage()} instead. This function returns an 
     */
    public double getShooterAngle(){
        double voltage = getVoltage();
        return map(voltage, kVoltageAtLowestAngle, kVoltageAtHighestAngle, kLowestAngle, kHighestAngle);
    }

    /**
     * <p>Re-maps a number from one range to another.
     * </p>
     * <p class='vspace'></p><p>Does not constrain values to within the range, because out-of-range values are sometimes intended and useful.
     * </p>
     * <p class='vspace'></p><p>Note that the "lower bounds" of either range may be larger or smaller than the "upper bounds" so the map() function may be used to reverse a range of numbers, for example
     * </p>
     * <p class='vspace'></p><p><code> y = map(x, 1, 50, 50, 1); </code>
     * </p>
     * <p class='vspace'></p><p>The function also handles negative numbers well, so
     * that this example
     * </p>
     * <p class='vspace'></p><p><code> y = map(x, 1, 50, 50, -100); </code>
     * </p>
     * <p class='vspace'></p><p>is also valid and works well.
     * </p>
     * Taken from http://arduino.cc/en/Reference/Map
     * @param x The input value to be mapped
     * @param in_min The lowest possible input value; mapped to the lowest possible output value
     * @param in_max The highest possible input value; mapped to the highest possible output value
     * @param out_min The lowest possible output value
     * @param out_max The highest possible output value
     * @return The value of x mapped to fit the output values
     */
    protected static double map(double x, double in_min, double in_max, double out_min, double out_max)
    {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    /**
     * Overrides PIDSource::pidGet() to provide the shooter angle to the PID controller
     * @return The angle as specified by getShooterAngle()
     */
    public double pidGet(){
        return getVoltage();
    }
}
