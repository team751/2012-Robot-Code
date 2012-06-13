package org.team751.util;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import org.team751.util.logging.LogLevel;
import org.team751.util.logging.Logger;

/**
 * This class contains several static methods used miscellaneously in the rest of the code.
 * @author Sam Crow
 */
public class Utilities {
    /** A string representation of the code version */
    public static final String kVersion = "1.0.0";

    /**
     * Print a message, using the {@link Logger Logger},
     * providing information about the system state.
     */
    public static void printStartupMessage(){
        Logger.getInstance().log("Team 751 robot code version "+kVersion+" initialized.", LogLevel.kStatus);
        printAllianceStatusMessage();
    }

    /**
     * Output a message using the {@link Logger Logger} that provides information on the
	 * alliance color, driver station position, and FMS status of the robot.
     */
    public static void printAllianceStatusMessage(){
        DriverStation ds = DriverStation.getInstance();

        Alliance alliance = ds.getAlliance();
        String allianceColor = allianceToString(alliance);
        int driverStationNumber = ds.getLocation();

        int actualTeamNumber = ds.getTeamNumber();

        boolean fms = ds.isFMSAttached();

        Logger.getInstance().log("Running as team "+actualTeamNumber+" on the "+allianceColor+" alliance, station "+driverStationNumber+".", LogLevel.kStatus);
        if(fms){
            Logger.getInstance().log("Connected to the Field Management System.", LogLevel.kStatus);
        }else{
            Logger.getInstance().log("Not connected to the Field Management System.", LogLevel.kStatus);
        }
    }

    /**
     * Convert from a semantic Alliance to a string representation of the alliance color.
     * @param alliance The input alliance
     * @return A string representation of the alliance color: "red", "blue", or "unknown"
     */
    private static String allianceToString(Alliance alliance){
        if(alliance.equals(Alliance.kBlue)){
            return "blue";
        }else if(alliance.equals(Alliance.kRed)){
            return "red";
        }else{
            return "unknown ("+alliance.value+")";
        }
    }

	/**
	 * A private constructor to prevent the use of this class in a non-static way
	 */
	private Utilities() {
	}
}
