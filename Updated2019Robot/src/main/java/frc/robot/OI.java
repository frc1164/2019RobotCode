/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.commands.*;
import frc.robot.triggers.CenterWithSensor;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public static Joystick driverStick = new Joystick(RobotMap.driverPort);
  public static XboxController operatorStick = new XboxController(RobotMap.operatorPort);
  
  public static Button toggleChassisFront = new JoystickButton(driverStick, 4);
  public static Button toggleChassisRear = new JoystickButton(driverStick, 3);
  Trigger AutoLineUp = new CenterWithSensor(driverStick, RobotMap.DriverTargetLine);

  Button DropEndEffector = new JoystickButton(operatorStick, RobotMap.XboxBButton);
	Button RaiseEndEffector = new JoystickButton(operatorStick, RobotMap.XboxAButton);
	Button ExtendHatchGrabber = new JoystickButton(operatorStick, RobotMap.XboxRightShoulder);
  Button RetractHatchGrabber = new JoystickButton(operatorStick, RobotMap.XboxLeftShoulder);
  Button LeanLiftForward = new JoystickButton(operatorStick, RobotMap.XboxSelect);
  Button LeanLiftBack = new JoystickButton(operatorStick, RobotMap.XboxStart);
  
  public OI(){

    //Climber
    toggleChassisFront.whenPressed(new raiseFront(Value.kReverse));
    toggleChassisFront.whenReleased(new raiseFront(Value.kForward));
    toggleChassisRear.whenPressed(new raiseRear(Value.kForward));
    toggleChassisRear.whenReleased(new raiseRear(Value.kReverse));

    //End Effector
    DropEndEffector.whenPressed(new DropEndEffector());
		RaiseEndEffector.whenPressed(new RaiseEndEffector());
		ExtendHatchGrabber.whenPressed(new ExtendHatchGrabber());
    RetractHatchGrabber.whenPressed(new RetractHatchGrabber());
    
    //Lift
    LeanLiftForward.whenPressed(new leanLiftForward());
    LeanLiftBack.whenPressed(new leanLiftBack());

    //Chassis
    AutoLineUp.whileActive(new targetLine());
  }//end constructor

  public static double deadband(double tolerance, double value){
    return (Math.abs(value) <= tolerance) ? 0 : value;
  }//end deadband
}//end OI
