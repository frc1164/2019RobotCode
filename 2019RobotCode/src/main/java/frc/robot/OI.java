/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.findLine;
import frc.robot.commands.raiseFront;
import frc.robot.commands.raiseRear;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public static Joystick driverStick = new Joystick(RobotMap.driverPort);
  
  public static Button toggleChassisFront = new JoystickButton(driverStick, RobotMap.DriverRightShoulder);
  public static Button toggleChassisRear = new JoystickButton(driverStick, RobotMap.DriverLeftShoulder);

  public static Button target = new JoystickButton(driverStick, RobotMap.DriverYButton);

  public OI(){
    toggleChassisFront.whenPressed(new raiseFront(!Robot.robotChassis.geFrontSolenoidPosition()));
    toggleChassisRear.whenPressed(new raiseRear(!Robot.robotChassis.getRearSolenoidPosition()));
    
    target.whileHeld(new findLine());
  }//end constructor

  public static double deadband(double tolerance, double value){
    return (Math.abs(value) <= tolerance) ? 0 : value;
  }//end deadband
  public static Joystick getDriverStick(){
    return driverStick;
  }//end getDriverStick
}
