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
  
  public void update(){
    poseMaker.update(Robot.robotLineSensor.Array1Offset(), 
      Robot.robotLineSensor.Array2Offset(), 
      Robot.robotChassis.getUltrasonic());

    
  }//end update
  
}//DriveController
