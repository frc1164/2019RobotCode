/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.logic;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class PID {
    private double kP, kI, kD;
    private double prevError, integral;

    public PID(double _kP, double _kI, double _kD){
        kP = _kP;
        kI = _kI;
        kD = _kD;
        prevError = 0;
        integral = 0;

    }//end constructor

    public PID(){
        kP = 0;
        kI = 0;
        kD = 0;
        prevError = 0;
        integral = 0;

    }//end default constructor

    public void setGains(double _kP, double _kI, double _kD){
        kP = _kP;
        kI = _kI;
        kD = _kD;
    }//end setGains

    public double update(double goal, double currPos){
        double output;
        double error = goal - currPos;
        integral = error + integral;
        output = kP*error + kI*integral + kD*(error-prevError);
        prevError = error;
        double[] gainsArray = {kP, kI, kD};
        SmartDashboard.putNumberArray("PID gains", gainsArray);
        return output;
    }//end update

    public void reset(){
        integral = 0;
        prevError = 0;
    }//end reset
}//end class PID

