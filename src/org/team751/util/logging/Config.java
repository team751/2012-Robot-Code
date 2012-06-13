package org.team751.util.logging;

/**
 * Stores configuration for the logger and reads/writes it
 * @author Sam Crow
 */
public class Config {

    /**
     * Create a new configuration instance with the hard-coded default settings
     */
    public Config(){

    }

    /**
     * Create a new configuration instance with settings from a configuration
     * file with a given name.
     * @param fileName The name of the configuration file to read
     */
    public Config(String fileName){
        String filePath = kConfigPath + fileName;

        //BufferedReader reader = new BufferedReader(new FileReader(filePath));
        //Currently not implemented: Need to determine if file I/O is available
        // in this Java environment
    }

    /**
     * Write the current configuration to a file
     * @param fileName The name of the configuration file to write
     */
    public void write(String fileName){
        //Not yet implemented
    }


    /** If messages with log level kDebug are sent to the console */
    public boolean debugToConsole = true;
    /** If messages with log level kWarning are sent to the console */
    public boolean warningToConsole = true;
    /** If messages with log level kError are sent to the console */
    public boolean errorToConsole = true;
    /** If messages with log level kStatus are sent to the console */
    public boolean statusToConsole = true;

    /** If messages with log level kDebug are sent to the driver station display */
    public boolean debugToDriverStationDisplay = true;
    /** If messages with log level kWarning are sent to the driver station display */
    public boolean warningToDriverStationDisplay = true;
    /** If messages with log level kError are sent to the driver station display */
    public boolean errorToDriverStationDisplay = true;
    /** If messages with log level kStatus are sent to the driver station display */
    public boolean statusToDriverStationDisplay = false;

    /** If messages with log level kDebug are sent to the log file */
    public boolean debugToLogFile = false;
    /** If messages with log level kWarning are sent to the log file */
    public boolean warningToLogFile = true;
    /** If messages with log level kError are sent to the log file */
    public boolean errorToLogFile = true;
    /** If messages with log level kStatus are sent to the log file */
    public boolean statusToLogFile = true;

    /** The absolute path to the directory containing the configuration file(s), starting and ending with a slash */
    private static final String kConfigPath = "/config/";
}
