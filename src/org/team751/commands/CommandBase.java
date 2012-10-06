package org.team751.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.team751.OI;
import org.team751.subsystems.*;


/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    /**
     * The one Operator Interface used in the code.
     */
    public static OI oi;
    // Create a single static instance of all of your subsystems
    /**
     * The drivetrain subsystem
     */
    public static Drivetrain drivetrain = new Drivetrain();
    /**
     * The shooter wheel subsystem
     */
    public static ShooterWheels shooterWheels = new ShooterWheels();
    /**
     * The shooter tread/plastic subsystem
     */
    public static ShooterTread shooterTread = new ShooterTread();
    /**
     * The intake roller and lower nommer stage
     */
    public static Nommer2Bottom nommerBottom = new Nommer2Bottom();
    /**
     * The top nommer stage
     */
    public static Nommer2Top nommerTop = new Nommer2Top();
    /**
     * The bridge push down mechanism
     */
    public static BridgePush bridgePush = new BridgePush();
    /**
     * The counter-roller
     */
    public static CounterRoller counterRoller = new CounterRoller();


	public static CameraLights lights = new CameraLights();

    /**
     *
     */
    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();


       /* try {
            connection = (FileConnection)Connector.open("file:///tempOut.txt", Connector.WRITE);
            connection.create();
            tempFile = connection.openDataOutputStream();
        }
        catch (IOException e) {

        }
        *
        */

    }

    /**
     * Obligatory constructor override.
     */
    public CommandBase() {
        super();
    }
}
