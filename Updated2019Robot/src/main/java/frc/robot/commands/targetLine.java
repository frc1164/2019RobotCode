/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.logic.PID;

public class targetLine extends Command {

  PID lineFinder = new PID();
  private ShuffleboardTab tab = Shuffleboard.getTab("PID Settings");
  private NetworkTableEntry kP = tab.add("Line P", 0).getEntry();
  private NetworkTableEntry kI = tab.add("Line I", 0).getEntry();
  private NetworkTableEntry kD = tab.add("Line D", 0).getEntry();
  private NetworkTableEntry goal = tab.add("Line goal", 7500).getEntry();
  double P, I, D, PIDGoal;
  Double speed;
  int sensorVal;

  public targetLine() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.robotChassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    lineFinder.reset();
    P = kP.getDouble(0.0);
    I = kI.getDouble(0.0);
    D = kD.getDouble(0.0);
    lineFinder.setGains(P, I, D);
    

    double[] gains = {P, I, D};
    SmartDashboard.putNumberArray("PID Gains", gains);
    PIDGoal = goal.getDouble(10000);
    Robot.robotLineSensor.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sensorVal = Robot.robotLineSensor.getInt();
    speed = lineFinder.update(PIDGoal,  sensorVal) * 0.001;
    Robot.robotChassis.setCenterSpeed(speed);
    SmartDashboard.putNumber("PID Output", speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (sensorVal > 13900);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.robotChassis.brake();
  }
}
