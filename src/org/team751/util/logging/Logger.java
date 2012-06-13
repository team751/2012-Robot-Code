package org.team751.util.logging;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import java.io.PrintStream;
import java.util.Date;

/**
 * This class handles logging and/or reporting of messages
 * @author Sam Crow
 */
public class Logger {

    private static Logger instance;
    private static final PrintStream console = System.out;
    private static final PrintStream errorConsole = System.err;
    private final DriverStationLCDConsole dsConsole = new DriverStationLCDConsole();

    private Config config = new Config();

	private LogFile file;

    private Logger(){
		file = new LogFile();
    }

    /**
     * Log a message
     * @param message The information to log. This is often in the form of a
     * String. Otherwise, it will be converted to a String with its toString()
     * method.
     * @param level The log level that applies to this message
     */
    public void log(Object message, LogLevel level){
        String messageString = getPrefix() + getString(message);

        if(level.equals(LogLevel.kStatus)){
            if(config.statusToConsole){
                console.println(messageString);
            }
            if(config.statusToDriverStationDisplay){
                dsConsole.println(messageString);
            }
            if(config.statusToLogFile){
                file.println(messageString, level);
            }
        }

        if(level.equals(LogLevel.kDebug)){
            if(config.debugToConsole){
                console.println(messageString);
            }
            if(config.debugToDriverStationDisplay){
                dsConsole.println(messageString);
            }
            if(config.debugToLogFile){
                file.println(messageString, level);
            }
        }

        if(level.equals(LogLevel.kWarning)){
            if(config.warningToConsole){
                errorConsole.println(messageString);
            }
            if(config.warningToDriverStationDisplay){
                dsConsole.println(messageString);
            }
            if(config.warningToLogFile){
                file.println(messageString, level);
            }
        }

        if(level.equals(LogLevel.kError)){
            if(config.errorToConsole){
                errorConsole.println(messageString);
            }
            if(config.errorToDriverStationDisplay){
                dsConsole.println(messageString);
            }
            if(config.errorToLogFile){
                //not yet implemented
            }
        }

    }

    /**
     * Log a message
     * @param message The information to log.
     * @param level The log level that applies to this message.
     */
    public void log(boolean message, LogLevel level){
        log(String.valueOf(message), level);
    }
    /**
     * Log a message
     * @param message The information to log.
     * @param level The log level that applies to this message.
     */
    public void log(char message, LogLevel level){
        log(String.valueOf(message), level);
    }
    /**
     * Log a message
     * @param message The information to log.
     * @param level The log level that applies to this message.
     */
    public void log(char[] message, LogLevel level){
        log(String.valueOf(message), level);
    }
    /**
     * Log a message
     * @param message The information to log.
     * @param level The log level that applies to this message.
     */
    public void log(double message, LogLevel level){
        log(String.valueOf(message), level);
    }
    /**
     * Log a message
     * @param message The information to log.
     * @param level The log level that applies to this message.
     */
    public void log(float message, LogLevel level){
        log(String.valueOf(message), level);
    }
    /**
     * Log a message
     * @param message The information to log.
     * @param level The log level that applies to this message.
     */
    public void log(int message, LogLevel level){
        log(String.valueOf(message), level);
    }
    /**
     * Log a message
     * @param message The information to log.
     * @param level The log level that applies to this message.
     */
    public void log(long message, LogLevel level){
        log(String.valueOf(message), level);
    }

    /**
     * Get the one instance of the Logger
     * @return the instance
     */
    public static Logger getInstance(){
        if(instance == null){
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Convert any Object into a String.
     * @param input The input Object
     * @return the object if it is a String, or a string representation of that object
     */
    private String getString(Object input){

        if(input instanceof String){
            return (String) input;
        }else{
            return input.toString();
        }
    }

    /**
     * Get the log prefix, indicating the timestamp, to be prepended to each message
     * @return The prefix
     */
    private String getPrefix(){
        return "["+System.currentTimeMillis()+" "+ MathUtils.round(DriverStation.getInstance().getMatchTime())+"] ";
    }


    /**
     * Handles interface with the driver station display to use it like
     * a console
     */
    private class DriverStationLCDConsole {
        /** The number of addressable lines on the driver station display */
        private static final int kLineCount = 5;
        private DriverStationLCD lcd = DriverStationLCD.getInstance();

        /**
         * An array of Strings representing the current contents of the display,
         * with 0 = the 2nd line from the top. The contents of the top line
         * are not stored.
         */
        private String[] lines = new String[kLineCount - 1];

        public DriverStationLCDConsole(){
            for(int i = 0; i < lines.length; i++){
                lines[i] = "";//Initialize each line as an empty string
            }


        }

        /**
         * Print a line of text onto the bottom line of the driver station display
         * @param line The text to print
         */
        public void println(String line){
            //Shift each existing line up
            lcd.println(Line.kUser2, 1, lines[0]);//Replace the top line with the contents of the line 2nd from top
            lcd.println(Line.kUser3, 1, lines[1]);//Replace the 2nd line with the contents of the 3d line
            lcd.println(Line.kUser4, 1, lines[2]);//Replace the 3d line with the contents of the 4th line
            lcd.println(Line.kUser5, 1, lines[3]);//Replace the 4th line with the contents of the 5th line

            //Print the new message on the 5th line
            lcd.println(Line.kUser6, 1, line);

            //Shift the array contents down one index (up one line)
            for(int i = 1; i < lines.length; i++){
                lines[i-1] = lines[i];
            }

            lines[lines.length - 1] = line;//Put the new line into the highest index in the array
        }

    }
}
