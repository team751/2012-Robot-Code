package org.team751;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.team751.commands.bridgepush.BridgePushMomentaryExtend;
import org.team751.commands.bridgepush.BridgePushMomentaryRetract;
import org.team751.commands.drivetrain.DisableSlowMode;
import org.team751.commands.drivetrain.EnableSlowMode;
import org.team751.commands.nommer2.Feed;
import org.team751.commands.nommer2.NommerEject;
import org.team751.commands.nommer2.NommerIntake;
import org.team751.commands.shooter.*;

/**
 * Contains structure and mappings for the operator interface.<br />
 *
 * Shooter theory of operation: <ol> <li>Drive to the shooting position</li>
 * <li>Press the forward button to automatically set target speed &amp;
 * angle</li> <li>Start holding down the back button to turn the shooter on</li>
 * <li>Pull the trigger to shoot</li> <li>Release the back button to turn the
 * shooter off</li> </ol>
 */
public class OI {
    // Process operator interface input here.

    /**
     * The joystick used for driving
     */
    private Joystick driveJoystick = new Joystick(1);
    /**
     * The joystick used for system operation
     */
    private Joystick operatorJoystick = new Joystick(2);//Changed to 2 from 3

    /**
     * Get a reference to the joystick used for driving.
     *
     * @return The joystick
     */
    public Joystick getDriveJoystick() {
        return driveJoystick;
    }

    /**
     * Get a reference to the joystick used for system operation.
     *
     * @return The joystick
     */
    public Joystick getOperatorJoystick() {
        return operatorJoystick;
    }
    //Shoot: Trigger
    private JoystickButton driverTrigger = new JoystickButton(driveJoystick, 1);
    //Power up: right side button on top
    private JoystickButton operator5 = new JoystickButton(operatorJoystick, 5);
    //Power down: left side button on top
    private JoystickButton operator4 = new JoystickButton(operatorJoystick, 4);
    //Set auto power: forward button on top
    private JoystickButton operator3 = new JoystickButton(operatorJoystick, 3);
    //Closed-loop drive enable: forward button on top
    private JoystickButton driver3 = new JoystickButton(driveJoystick, 3);
    //Closed-loop drive diable: back button on top
    private JoystickButton driver2 = new JoystickButton(driveJoystick, 2);
    //Nommer on/intake: base left side forward button
    private JoystickButton operator6 = new JoystickButton(operatorJoystick, 6);
    //Nommer off/reject: base left side back button
    private JoystickButton operator7 = new JoystickButton(operatorJoystick, 7);
    //Shooter button: back button on top (Hold down to turn shooter on, release to turn it off)
    private JoystickButton operator2 = new JoystickButton(operatorJoystick, 2);
    private JoystickButton operatorTrigger = new JoystickButton(operatorJoystick, 1);
    //Shooter angle controls
    //Shooter angle up: Base right side back button
    private JoystickButton operator11 = new JoystickButton(operatorJoystick, 11);
    //Shooter angle down: Base right side forward button
    private JoystickButton operator10 = new JoystickButton(operatorJoystick, 10);
    private JoystickButton driver6 = new JoystickButton(driveJoystick, 6);
    private JoystickButton driver7 = new JoystickButton(driveJoystick, 7);

    /**
     * Construct the operator interface and link buttons to commands
     */
    public OI() {
        operator5.whenPressed(new ManualShooterPowerUp());
        operator4.whenPressed(new ManualShooterPowerDown());
        operator3.whenPressed(new AutomaticShooterSet());
        driverTrigger.whileHeld(new Feed());//Right stick trigger

        driver3.whenPressed(new EnableSlowMode());
        driver2.whenPressed(new DisableSlowMode());

        operator6.whileHeld(new NommerIntake());//Left stick button 6
        operator7.whileHeld(new NommerEject());//Left stick button 7
        operatorTrigger.whileHeld(new Feed());

        operator2.whenPressed(new ShooterOn());
        operator2.whenReleased(new ShooterOff());

        operator11.whileHeld(new ManualShooterAngleUp());
        operator10.whileHeld(new ManualShooterAngleDown());

        driver6.whileHeld(new BridgePushMomentaryExtend());
        driver7.whileHeld(new BridgePushMomentaryRetract());

    }
}
