/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team751.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.team751.commands.counterroller.AutonomousFire;
import org.team751.commands.counterroller.Fire;
import org.team751.commands.counterroller.ReallyLongFire;
import org.team751.commands.nommer.AutoNom;
import org.team751.commands.nommer.DisableIntake;
import org.team751.commands.shooter.AutomaticShooterSet;
import org.team751.commands.shooter.ManualShooterFullPower;
import org.team751.commands.shooter.ShooterOff;
import org.team751.commands.shooter.ShooterOn;

/**
 * Runs autonomous mode
 */
public class Autonomous extends CommandGroup {

    /**
     *
     */
    public Autonomous() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.


		addSequential(new WaitCommand(7));//Wait for other robots to do things

		//addSequential(new AutomaticShooterSet());//Turn on the shooter
		addSequential(new ManualShooterFullPower());
		addSequential(new ShooterOn());
        addSequential(new WaitCommand(3));//Wait for the shooter to power up
        addSequential(new AutonomousFire());//Fire

		addSequential(new WaitCommand(2));//Wait for things to calm down
        addSequential(new ReallyLongFire());//Fire

		addSequential(new WaitCommand(0.5));//Wait for things to calm down
		addSequential(new ShooterOff());//Turn the shooter off
    }
}
