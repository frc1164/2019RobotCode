/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class CenterWithSensor extends Trigger {

  private Joystick stick;
  private int buttonChannel;

  public CenterWithSensor(Joystick joystick, int port){
    stick = joystick;
    buttonChannel = port;
  }//end constructor

  @Override
  public boolean get() {
    return (stick.getRawButton(buttonChannel) && Robot.robotLineSensor.seesLine());
  }
}
