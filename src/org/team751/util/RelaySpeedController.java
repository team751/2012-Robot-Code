package org.team751.util;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * This class extends Relay and implements SpeedController to make it compatible with PID.
 * @author Sam Crow
 */
public class RelaySpeedController extends Relay implements SpeedController {
    /** The current value that the relay is set to */
    protected Value currentRelayValue = Value.kOff;
    
    /** The magnitude above which the relay is switched on */
    public static final double kThreshold = 0.2;
    
    /**
     * 
     * @param channel
     */
    public RelaySpeedController(int channel){
        super(channel);
    }
    /**
     * 
     * @param slot
     * @param channel
     */
    public RelaySpeedController(int slot, int channel){
        super(slot, channel);
    }

    public double get() {
        return valueToNumber(currentRelayValue);
    }

    public void set(double speed, byte syncGroup) {
        set(speed);
    }

    public void set(double speed) {
        Value newValue = numberToValue(speed);
        currentRelayValue = newValue;
        super.set(newValue);
    }

    public void disable() {
        Value newValue = Value.kOff;
        currentRelayValue = newValue;
        super.set(newValue);
    }

    public void pidWrite(double output) {
        set(output);
    }
    
    
    /**
     * Convert from a numerical input value to a relay command value
     * @param input The speed-control-type input from -1 to 1
     * @return  the corresponding relay value
     */
    public static Value numberToValue(double input){
        if(input > kThreshold){
            return Value.kForward;
        }else if(input < -kThreshold){
            return Value.kReverse;
        }else{
            return Value.kOff;
        }
    }
    /**
     * Convert from a relay value to a numerical speed value
     * @param input The relay value
     * @return The corresponding motor speed value, -1 to 1
     */
    public static double valueToNumber(Value input){
        if(input.equals(Value.kForward)){
            return 1;
        }else if(input.equals(Value.kReverse)){
            return -1;
        }else{
            return 0;
        }
    }
}
