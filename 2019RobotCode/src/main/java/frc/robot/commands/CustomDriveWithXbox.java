package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * 
 */
public class CustomDriveWithXbox extends Command {

    private Joystick stick;
    public CustomDriveWithXbox(Joystick _stick) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.robotChassis);
      this.stick = _stick;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double LStickValue;
    	double RStickValue;
    	double LTriggerValue;
    	double RTriggerValue;
    	
    	double LeftMotorValue;
    	double RightMotorValue;
    	
    	LStickValue = OI.deadband(0.1, stick.getRawAxis(RobotMap.DriverLeftX));
    	RStickValue = OI.deadband(0.1, stick.getRawAxis(RobotMap.DriverRightX));
    	LTriggerValue = OI.deadband(0.1, stick.getRawAxis(RobotMap.DriverLeftTrigger));
      RTriggerValue = OI.deadband(0.1, stick.getRawAxis(RobotMap.DriverRightTrigger));
      
		 //Drive forward and backward
    	RightMotorValue = RTriggerValue - LTriggerValue;
    	LeftMotorValue = RTriggerValue - LTriggerValue;
    	
      //Turning slowly (Assuming LAxis is the slow turning axis)
      RightMotorValue = (1 - LStickValue) * RightMotorValue;
      LeftMotorValue = (1 + LStickValue) * LeftMotorValue;

      //Turning quickly (Assuming RAxis is the fast turning axis)
      RightMotorValue = RightMotorValue - (0.5 * RStickValue);
      LeftMotorValue = LeftMotorValue + (0.5 * RStickValue);
    		
		
		  Robot.robotChassis.setRightSpeed(RightMotorValue);
		  Robot.robotChassis.setLeftSpeed(LeftMotorValue);

    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      Robot.robotChassis.brake();
    }
}