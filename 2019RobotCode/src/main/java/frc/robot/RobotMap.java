/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  //Controller Mapping
  public static final int XboxLeftY = 1;
  public static final int XboxLeftX = 0;
  public static final int XboxRightY = 5;
  public static final int XboxRightX = 4;
  public static final int XboxRightTrigger = 3;
  public static final int XboxLeftTrigger = 2;
  public static final int XboxRightShoulder = 6;
  public static final int XboxLeftShoulder = 5;
  public static final int driverPort = 0;

  public static final int PilotX = 0;//TODO: update joystick mapping for Pilot
  public static final int PilotY = 0;
  public static final int PilotRotate = 0;

  

  //Chassis
  public static final int LeftMotorFront = 7;
  public static final int LeftMotorRear = 8;
  public static final int RightMotorFront = 6;
  public static final int RightMotorRear = 5;
  public static final int CenterMotor = 4;

  public static final double CenterMaxSpeed = 0.5;

  //Climber
  //TODO: Map Solenoids
  public static final int ClimbMotor1 = 0;
  public static final int ClimbMotor2 = 0;
  public static final int ClimbFrontSolenoid = 0;
  public static final int ClimbRearSolenoid = 0;

}//end class RobotMap
