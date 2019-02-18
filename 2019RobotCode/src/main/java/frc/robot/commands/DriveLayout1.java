/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveLayout1 extends Command {
  private Joystick stick;
  double RightMotors;
  double LeftMotors;
  public DriveLayout1(Joystick joystick) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.robotChassis);
    stick = joystick;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.robotChassis.setCenterSpeed(OI.deadband(0.1, stick.getRawAxis(RobotMap.XboxLeftX)));
    RightMotors = OI.deadband(0.1, stick.getRawAxis(RobotMap.XboxLeftY));
    LeftMotors = OI.deadband(0.1, stick.getRawAxis(RobotMap.XboxLeftY));

    RightMotors += 0.5 * OI.deadband(0.1, stick.getRawAxis(RobotMap.XboxRightX));
    LeftMotors -= 0.5 * OI.deadband(0.1, stick.getRawAxis(RobotMap.XboxRightX));

    Robot.robotChassis.setLeftSpeed(LeftMotors);
    Robot.robotChassis.setRightSpeed(RightMotors);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
