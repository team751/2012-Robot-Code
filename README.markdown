# Team 751 2012 Robot Code #

This code controls Team 751's 2012 competition robot. It is based on the Command-Based Robot template.

The history of this repository is available at https://code.google.com/p/team751robotcode/

Everything is located in the `org.team751` package and its subpackages.

More information on the command-based system is available at http://wpilib.screenstepslive.com/s/3120/m/7952

## Files ##

`Robot2012.java` contains the core functions. It does not contain much actual code. It uses the commmand system for its actual logic.

`OI.java` holds the two `Joystick` objects for the two joysticks on the operator console and maps the joystick buttons to robot commands. In autonomous mode, it starts the autonomous command group.

`RobotMap.java` defines the PWM, digital I/O, and analog channels that various components of the robot connect to.

The subsystems directory contains subsystem classes for various robot subsystems. Each subsystem class holds motor controllers, relays, and/or sensors that its subsystem uses.

The commands directory contains many commands that control the subsystem logic. They are organized in subdirectories corresponding to different robot subsystems.

Of particular importance is `Autonomous.java`, which is the `CommandGroup` that defines the set of commands to execute during the autonomous period.

The `sensors` and `util` directories contain utility classes for additional functions and sensors that are not in the wpilibj library.
