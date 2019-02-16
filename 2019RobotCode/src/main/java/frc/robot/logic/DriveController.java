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
  PoseGenerator poseMaker = new PoseGenerator();
  PID XPID = new PID();
  PID YPID = new PID();
  PID ThetaPID = new PID();

  private double XGoal;
  private double YGoal;
  private double ThetaGoal;
  
  public void update(){
    poseMaker.update(Robot.robotLineSensor.Array1Offset(), 
      Robot.robotLineSensor.Array2Offset(), 
      Robot.robotChassis.getUltrasonic());
    double currX = poseMaker.getX();
    double currY = poseMaker.getY();
    double currTheta = poseMaker.getTheta();


    if(currX != PoseGenerator.invalid){
      XPID.update(XGoal, currX);
    } else{
      XPID.reset();
    }
    if(currY != PoseGenerator.invalid){
      YPID.update(YGoal, currY);
    } else{
      YPID.reset();
    }
    if(currTheta != PoseGenerator.invalid){
      ThetaPID.update(ThetaGoal, currTheta);
    } else{
      ThetaPID.reset();
    }

  }//end update
  
}//DriveController
