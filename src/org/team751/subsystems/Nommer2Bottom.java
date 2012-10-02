package org.team751.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team751.RobotMap;

/**
 * The subsystem for the bottom segment of the nommer, including
 * the intake roller
 * @author Sam Crow
 */
public class Nommer2Bottom extends Subsystem {
    
    Relay intakeRoller = new Relay(RobotMap.nommerIntakeRoller);
    
    SpeedController lowerConveyor = new Victor(RobotMap.lowerNommerVictor);
    
    /** The ratio (0-1) of power to apply to the conveyor motor */
    protected static final double kConveyorPower = 1;

    protected void initDefaultCommand() {
        
    }
    
    /**
     * Set the intake roller and lower conveyor to pull balls in
     */
    public void intake() {
        intakeRoller.set(Relay.Value.kForward);
        lowerConveyor.set(kConveyorPower);
    }
    
    /**
     * Set the intake roller and lower conveyor to push balls out
     */
    public void eject() {
        intakeRoller.set(Relay.Value.kReverse);
        lowerConveyor.set(-kConveyorPower);
    }
    
    /**
     * Stop the intake roller and lower conveyor
     */
    public void stop() {
        intakeRoller.set(Relay.Value.kOff);
        lowerConveyor.set(0);
    }
}
