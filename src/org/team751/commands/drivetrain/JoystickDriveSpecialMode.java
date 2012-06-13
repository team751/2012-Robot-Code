/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.commands.drivetrain;

/**
 * Drive the robot in special mode (slowed down)
 * @author Sam Crow
 */
public class JoystickDriveSpecialMode extends JoystickDrive {
    protected void execute(){
        double x = oi.getDriveJoystick().getX();
        double y = oi.getDriveJoystick().getY();
        x *= 0.6;//reduce values
        y *= 0.35;
        drivetrain.arcadeDrive(y, x);
    }
}
