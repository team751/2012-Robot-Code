package org.team751.sensors;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.visa.VisaException;
import org.team751.util.logging.LogLevel;
import org.team751.util.logging.Logger;

/**
 * This class interfaces with the ultrasonic sensor over the cRIO serial port.
 * Messages sent by the ultrasonic sensor are in the form "R***\r" where ***
 * represents three numerical digits that provide the measured distance
 * in inches.
 * @author Sam Crow
 */
public class SerialUltrasonic extends SerialPort {

    /**
     * The baud rate that the ultrasonic sensor transmits at
     */
    public static final int kBaudRate = 9600;
    /**
     * The data bits used by the ultrasonic sensor
     */
    public static final int kDataBits = 8;
    /**
     * The parity used by the ultrasonic sensor
     */
    public static final Parity kParity = Parity.kNone;
    /**
     * The stop bits used by the ultrasonic sensor
     */
    public static final StopBits kStopBits = StopBits.kOne;

    /** The distance, in inches, of the last measurement. This is used when a new measurement is not available. */
    private int lastMeasurement = 0;

    /**
     * Constructor that configures the serial port correctly to work with the ultrasonic sensor
     * @throws VisaException If a VisaException is thrown by a lower-level operation
     */
    public SerialUltrasonic() throws VisaException{
        super(kBaudRate, kDataBits, kParity, kStopBits);
        setFlowControl(FlowControl.kNone);
		disableTermination();
    }

    /**
     * Get the distance to the object currently detected by the ultrasonic sensor.
     * Distance information is bufferred by the serial port firmware. If no
     * new distance information has been received since the last time this was
     * called, the last known distance is returned.
     * @return The distance in inches
     * @throws VisaException if a communication error or something occurred
     */
    public int getDistance() throws VisaException {

        //It looks like this Java environment doesn't have String::split(String regex).
        //Therefore, it must be processed in other ways.
        if(getBytesReceived() >= 5){//Only take data out of the buffer if it contains at least all 5 parts (R***\r) of a distance message
			String readBuffer = readString();

			int bufferLength = readBuffer.length();

			String last5Characters = readBuffer.substring(bufferLength - 5);//This, ideally is in the format R***\r

			Logger.getInstance().log(last5Characters, LogLevel.kDebug);

			String distanceString = last5Characters.substring(1, 3);
			int distance;
			try {
				distance = Integer.parseInt(distanceString);
			} catch(NumberFormatException e){
				//That wasn't a valid number
				return lastMeasurement;
			}
			return distance;
		}

		return -1;
	}

	public void log(){
		try {
			this.print("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		} catch (VisaException ex) {
			ex.printStackTrace();
		}

		try {
			int available = getBytesReceived();
			Logger.getInstance().log(available+" bytes available", LogLevel.kDebug);

			String buffer = "";

			for(int i = 0; i < available; i++){
				byte thisByte = read(available)[i];
				char thisChar = ASCIIToChar(thisByte);


				buffer += thisByte + " ";
			}

			//reset();//Clear buffers

			Logger.getInstance().log("Read: "+buffer, LogLevel.kDebug);

		} catch (VisaException ex) {
			ex.printStackTrace();
		}
	}

    //These overriden constructors are private so that they cannot be used.
    //The constructor above with no arguments should be used. It will set
    //up the serial port with the right
    private SerialUltrasonic(int baudRate) throws VisaException{
        super(baudRate);
    }
    private SerialUltrasonic(int baudRate, int dataBits) throws VisaException{
        super(baudRate, dataBits);
    }
    private SerialUltrasonic(int baudRate, int dataBits, Parity parity) throws VisaException{
        super(baudRate, dataBits, parity);
    }
    private SerialUltrasonic(int baudRate, int dataBits, Parity parity, StopBits stopBits) throws VisaException{
        super(baudRate, dataBits, parity, stopBits);
    }


	/**
	 * Convert a numerical value to its corresponding character based on the ASCII table.
	 * @param input The numerical value
	 * @return The character that it corresponds to
	 */
	private static char ASCIIToChar(byte input){
		switch(input){
			case 48:
				return '0';
			case 49:
				return '1';
			case 50:
				return '2';
			case 51:
				return '3';
			case 52:
				return '4';
			case 53:
				return '5';
			case 54:
				return '6';
			case 55:
				return '7';
			case 56:
				return '8';
			case 57:
				return '9';

			case 65:
				return 'R';
			case 83:
				return 'S';

			default:
				return '-';
		}
	}

	private static char inputToChar(byte input){
		switch(input){
			case 48:
				return '0';
			case 49:
				return '1';

			default:
				return '-';
		}
	}
}
