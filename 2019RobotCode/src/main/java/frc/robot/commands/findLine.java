/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Robot;

public class findLine extends Command {

  //get gains from ShuffleBoard
  private ShuffleboardTab tab = Shuffleboard.getTab("findLine commands");
  private NetworkTableEntry XmaxSpeed = tab.add("X Max Speed", 0.15).withWidget(BuiltInWidgets.kTextView).getEntry();
  private NetworkTableEntry XkP, XkI, XkD;
  
  public findLine() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.robotChassis);

    //get gains from Shuffleboard
    XkP = tab.add("kP", 0).withWidget(BuiltInWidgets.kNumberSlider).getEntry();
    XkI = tab.add("kI", 0).withWidget(BuiltInWidgets.kNumberSlider).getEntry();
    XkD = tab.add("kD", 0).withWidget(BuiltInWidgets.kNumberSlider).getEntry();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //reset PID Controller and update gains
    Robot.robotDriveController.resetXPID();
    Robot.robotDriveController.setXGains(XkP.getDouble(0), XkI.getDouble(0), XkD.getDouble(0));
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.robotDriveController.update();
    Robot.robotChassis.setCenterSpeed(Robot.robotDriveController.getXOutput());
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
