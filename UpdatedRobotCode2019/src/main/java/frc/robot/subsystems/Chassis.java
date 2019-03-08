/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.CustomDriveWithXbox;
import frc.robot.commands.DriveLayout1;
import frc.robot.commands.DriveWithPilot1;

/**
 * Robot Chassis subsystem
 */
public class Chassis extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private VictorSPX LeftFront, LeftRear, RightFront, RightRear, Center1, Center2;
  private DoubleSolenoid frontSolenoid, rearSolenoid;

  public Chassis(){
    LeftFront = new VictorSPX(RobotMap.LeftMotorFront);
    LeftRear = new VictorSPX(RobotMap.LeftMotorRear);
    RightFront = new VictorSPX(RobotMap.RightMotorFront);
    RightRear = new VictorSPX(RobotMap.RightMotorRear);
    Center1 = new VictorSPX(RobotMap.CenterMotor1);
    Center2 = new VictorSPX(RobotMap.CenterMotor2);

    frontSolenoid = new DoubleSolenoid(RobotMap.ClimbFrontPCM, RobotMap.ClimbFrontSolenoid1, RobotMap.ClimbFrontSolenoid2);
    rearSolenoid = new DoubleSolenoid(RobotMap.ClimbRearPCM, RobotMap.ClimbRearSolenoid1, RobotMap.ClimbRearSolenoid2);
    
    LeftFront.setInverted(true);
    LeftRear.setInverted(true);
    setFrontSolenoid(Value.kForward);
    setRearSolenoid(Value.kReverse);
  }//end default constructor

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DriveWithPilot1(OI.driverStick));
  }//end initDefaultCommand

  /**
   * Set the speed of the left two motors
   * @param speed The speed to set
   */
  public void setLeftSpeed(double speed){
    LeftFront.set(ControlMode.PercentOutput, speed);
    LeftRear.set(ControlMode.PercentOutput, speed);
  }//end setLeftSpeed
  /**
   * Set the speed of the right two motors
   * @param speed The speed to set
   */
  public void setRightSpeed(double speed){
    RightFront.set(ControlMode.PercentOutput, speed);
    RightRear.set(ControlMode.PercentOutput, speed);
  }//end setRightSpeed

  /**
   * Set the speed of the center motor
   * @param speed The speed to set
   */
  public void setCenterSpeed(double speed){
    Center1.set(ControlMode.PercentOutput, speed * RobotMap.CenterMaxSpeed);
    Center2.set(ControlMode.PercentOutput, speed * RobotMap.CenterMaxSpeed);
  }//end setCenterSpeed

  /**
   * Set all the motors on the Chassis to 0
   */
  public void brake(){
    setLeftSpeed(0);
    setRightSpeed(0);
    setCenterSpeed(0);
  }//end brake

  /**
   * Set the position of the front solenoid. 
   * @param pos The position. True sets to on, false sets to off 
   */
  public void setFrontSolenoid(Value val){
    frontSolenoid.set(val);
  }//end setFrontSolenoid
  
  /**
   * Set the position of the rear solenoid
   * @param pos The position. True sets to on, false sets to off
   */
  public void setRearSolenoid(Value val){
    rearSolenoid.set(val);
  }//end setRearSolenoid

  /**
   * Get the current position of the front solenoid
   * @return True if the solenoid is on. False if the solenoid is off
   */
  public boolean geFrontSolenoidPosition(){
    return (frontSolenoid.get() == Value.kForward);
  }//end getRearSolenoidPosition
  
  /**
   * Get the current position of the rear solenoid.
   * @return True if the solenoid is on. False if the solenoid is off.
   */
  public boolean getRearSolenoidPosition(){
    return (rearSolenoid.get() == Value.kForward);
  }//end getRearSolenoidPosition

  public double getUltrasonic(){
    return 0;
    //TODO: update getUltrasonic method in Chassis subsystem
  }

}//end Chassis class
