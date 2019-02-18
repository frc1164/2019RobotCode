/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Command to control the rear chassis climbing solenoid
 */
public class raiseRear extends InstantCommand {
  private Value pos;
  
  /**
   * Toggle the rear solenoid
   * @param position True to raise, false to lower
   */
  public raiseRear(Value val) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.robotChassis);
    pos = val;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.robotChassis.setRearSolenoid(pos);
  }

}
