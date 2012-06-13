package org.team751.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;
import org.team751.util.logging.LogLevel;
import org.team751.util.logging.Logger;

/**
 * This class communicates with the Pandaboard over TCP.
 * @author Sam Crow
 */
public class PandaboardInterface {

	/** The socket connection to the Pandaboard */
	SocketConnection connection;
	DataInputStream stream;

	public PandaboardInterface(){
		try {
			connection = (SocketConnection) Connector.open("socket://10.7.51.12:7510");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {
			stream = connection.openDataInputStream();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Get the distance sensed by the ultrasonic sensor.
	 * @return the distance in inches
	 */
	public double getDistanceToHoop(){
		try {
			String received = "";

			while(stream.available() > 0){
				char thisChar = stream.readChar();
				received += String.valueOf(thisChar);
				if(thisChar == '\n'){
					break;
				}
			}

			Logger.getInstance().log(received, LogLevel.kDebug);

		} catch (IOException ex) {
			ex.printStackTrace();
		}


		return 0;
	}
}
