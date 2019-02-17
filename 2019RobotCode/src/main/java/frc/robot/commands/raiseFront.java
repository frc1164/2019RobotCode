/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Command to control the front chassis climbing solenoid
 */
public class raiseFront extends InstantCommand {
  private boolean pos;
  /**
   * Toggle the front climbing solenoids. 
   * @param position True to raise, false to lower
   */
  public raiseFront(boolean position) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.robotChassis);
    pos = position;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.robotChassis.setFrontSolenoid(pos);
  }

}
