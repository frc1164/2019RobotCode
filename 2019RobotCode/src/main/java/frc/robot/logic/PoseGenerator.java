/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.logic;

/**
 * Code to calculate the robot position
 */
public class PoseGenerator {
    //If data is invalid
    public static final int invalid = -1000;

    //If ultrasonic is too far
    private static final double ultrasonicMax = 1000;//TODO: update
    
    //Offset of lineSensor arrays from center of bot
    public final double array1Offset = 0;
    public final double array2Offset = 0;//TODO: update

    //Offset of ultrasonic sensor from center of bot
    public final double ultrasonicYOffset = 0;
    public final double ultrasonicXOffset = 0;//TODO: update

    //Positions of white line detected by lineSensor
    private double x1, x2;
    //Distance from wall detected by ultrasonic
    private double y0;

    /**
     * 
     * @return Calculated X. Returns x1 if x2 is unavailable, x2 if x1 is unavailable, 
     * or -1000 if x1 and x2 are unable to be calculated
     */
    public double getX(){
        double d1 = array1Offset;
        double d2 = array2Offset;

        //special cases
        if(x1 == invalid && x2 == invalid) return invalid;
        if(x1 == invalid && x2 != invalid) return x2;
        if(x1 != invalid && x2 == invalid) return x1;

        return (-d1/(d1-d2))*(x1 - x2) + x1;
    }//end getX

    /**
     * 
     * @return Calculated angle to wall
     */
    public double getTheta(){
        double d1 = array1Offset;
        double d2 = array2Offset;

        //special cases
        if(x1 == invalid || x2 == invalid) return invalid;

        return Math.atan((x1-x2)/(d1-d2));
    }//end getTheta

    /**
     * @return Calculated Y position
     */
    public double getY(){
        double ysensor = ultrasonicYOffset;
        double xsensor = ultrasonicXOffset;
        double theta = getTheta();

        if(theta == invalid) theta = 0;
        if(y0 == invalid) return invalid;

        return (ysensor + y0 - (xsensor * Math.atan(theta)));
    }//end getY

    /**
     * update the measured position of bot. Pass -1000 if unable to measure
     * @param line1 offset detected by Array1
     * @param line2 offset detected by Array2
     * @param ultrasonic distance detected by ultrasonic
     */
    public void update(double line1, double line2, double ultrasonic){
        x1 = line1;
        x2 = line2;

        //special case
        if(ultrasonic > ultrasonicMax){
            y0 = invalid; 
            return;
        }

        y0 = ultrasonic;
    }//end update
}//end class PoseGenerator
