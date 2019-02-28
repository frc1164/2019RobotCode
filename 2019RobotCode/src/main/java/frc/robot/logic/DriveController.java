/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.logic;

import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class DriveController{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private PoseGenerator poseMaker = new PoseGenerator();
  private PID XPID = new PID();
  private PID YPID = new PID();
  private PID ThetaPID = new PID();

  private double XGoal, YGoal, ThetaGoal, XOutput, YOutput, ThetaOutput;

  /**
   * Create DriveController instance with Default goals
   * <ul>
   *  <li>XGoal = 0</li>
   *  <li>YGoal = 0</li>
   *  <li>ThetaGoal = 0</li>
   * </ul> 
   */
  public DriveController(){
    //TODO: Update DriveController Goals
    XGoal = 0;
    YGoal = 0;
    ThetaGoal = 0;
  }//end default constructor

  /**
   * Create DriveController instance with custom goals
   * @param X_Goal Desired horizontal position, relative to robot center
   * @param Y_Goal Desired distance from wall, relative to robot center
   * @param Theta_Goal Desired angle to wall, relative to robot center
   */
  public DriveController(double X_Goal, double Y_Goal, double Theta_Goal){
    XGoal = X_Goal;
    YGoal = Y_Goal;
    ThetaGoal = Theta_Goal;
  }//end constructor
  
  /**
   * Update the DriveController goals and current positions
   */
  public void update(){
    poseMaker.update(Robot.robotLineSensor.getArray1(), 
      Robot.robotLineSensor.getArray2(), 
      Robot.robotChassis.getUltrasonic());
    double currX = poseMaker.getX();
    double currY = poseMaker.getY();
    double currTheta = poseMaker.getTheta();


    if(currX != PoseGenerator.invalid){
      XOutput = XPID.update(XGoal, currX);
    } else{
      XPID.reset();
    }
    if(currY != PoseGenerator.invalid){
      YOutput = YPID.update(YGoal, currY);
    } else{
      YPID.reset();
    }
    if(currTheta != PoseGenerator.invalid){
      ThetaOutput = ThetaPID.update(ThetaGoal, currTheta);
    } else{
      ThetaPID.reset();
    }

  }//end update

  /**
   * @return Output of X PID Controller
   */
  public double getXOutput(){
    return XOutput;
  }//end getXOutput

  /**
   * @return Output of Y PID Controller
   */
  public double getYOutput(){
    return YOutput;
  }//end getYOutput

  /**
   * 
   * @return Output of Theta PID Controller
   */
  public double getThetaOutput(){
    return ThetaOutput;
  }//end getThetaOuptut

  /**
   * Reset X PID Controller
   */
  public void resetXPID(){
    XPID.reset();
  }//end resetXPID

  /**
   * Set the gains of the X PID Controller
   */
  public void setXGains(double P, double I, double D){
    XPID.setGains(P, I, D);
  }//end setXGains

}//DriveController
