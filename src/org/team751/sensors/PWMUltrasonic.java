package org.team751.sensors;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import org.team751.util.logging.LogLevel;
import org.team751.util.logging.Logger;

/**
 * Interfaces with the ultrasonic sensor over a PWM interface using a digital I/O port
 * @author Sam Crow
 */
public class PWMUltrasonic extends DigitalInput {
    
    /**
     * 
     */
    protected PWMTask task;
    
    /**
     * 
     * @param moduleNumber
     * @param channel
     */
    public PWMUltrasonic(int moduleNumber, int channel) {
        super(moduleNumber, channel);
        setupThread();
    }

    /**
     * 
     * @param channel
     */
    public PWMUltrasonic(int channel) {
        super(channel);
        setupThread();
    }
    
    private void setupThread(){
        task = new PWMTask(this);
        task.start();//Begin execution of the task in a separate thread
    }
    
    /**
     * 
     */
    protected class PWMTask extends Thread {
        
        /** The last digital value received from the sensor */
        private boolean lastInputValue = false;
        
        
        private Timer pulseTimer = new Timer();
        
        /**
         * 
         */
        protected PWMUltrasonic input;
        
        /**
         * 
         */
        protected boolean running = true;
        
        /**
         * 
         * @param input
         */
        public PWMTask(PWMUltrasonic input){
            this.input = input;
        }
        
        

        /**
         * 
         */
        public void run() {
            while(running){
                boolean inputValue = input.get();
                
                Logger.getInstance().log(inputValue, LogLevel.kDebug);
                
                if(inputValue == true && lastInputValue == false){
                    //Caught a rising pulse (low to high)
                    pulseTimer.stop();
                    pulseTimer.reset();
                    pulseTimer.start();
                }
                
                if(inputValue == false && lastInputValue == true){
                    //Caught a falling pulse (high to low)
                    pulseTimer.stop();
                    double pulseTime = pulseTimer.get();
                    Logger.getInstance().log("Pulse time "+pulseTime+" seconds", LogLevel.kDebug);
                }
                
                lastInputValue = inputValue;
            }
        }
        
        /**
         * 
         */
        public void stop(){
            running = false;
        }
        
        /**
         * 
         */
        public void start(){
            running = true;
            super.start();
        }
    }
}
