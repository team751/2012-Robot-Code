package org.team751.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team751.RobotMap;

/**
 * Subsystem for the top segment of the nommer
 * @author Sam Crow
 */
public class Nommer2Top extends Subsystem {
    
    private SpeedController topConveyor = new Victor(RobotMap.nommerTopRollerVictor);
    
    /** Ratio of power to apply to the top conveyor segment */
    private static final double kConveyorPower = 1;

    protected void initDefaultCommand() {
    }
    
    /**
     * Move balls up
     */
    public void feedUp() {
        topConveyor.set(kConveyorPower);
    }
    
    /**
     * Move balls down
     */
    public void feedDown() {
        topConveyor.set(-kConveyorPower);
    }
    
    /**
     * Stop the top conveyor
     */
    public void stop() {
        topConveyor.set(0);
    }
}
