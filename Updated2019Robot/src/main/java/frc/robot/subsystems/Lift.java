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
import frc.robot.commands.RunLiftWithXbox;

/**
 * Add your docs here.
 */
public class Lift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private VictorSPX LiftMotor;
  private double defaultSpeed;
  private DoubleSolenoid liftSol;

  public Lift(){
    LiftMotor = new VictorSPX(RobotMap.LiftMotor);
    defaultSpeed = 0.5;
    liftSol = new DoubleSolenoid(RobotMap.LiftPCM, RobotMap.LiftSolenoid1, RobotMap.LiftSolenoid2);

  }//end constructor
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new RunLiftWithXbox(OI.operatorStick));
  }

  public void raise(double speed){
    LiftMotor.set(ControlMode.PercentOutput, speed);
  }//end raise

  public void raise(){
    raise(defaultSpeed);
  }//end default raise

  public void lower(double speed){
    LiftMotor.set(ControlMode.PercentOutput, -speed);
  }//end lower

  public void lower(){
    lower(-defaultSpeed);
  }//end default lower

  public void startingConfig(boolean setToStartingConfig){
    if(setToStartingConfig){
      liftSol.set(Value.kForward);
      return;
    }//end if

    liftSol.set(Value.kReverse);
  }//end leanBack

  public void setDefaultSpeed(double newDefaultSpeed){
    if(newDefaultSpeed < -1){
      defaultSpeed = -1;
      return;
    }
    if(newDefaultSpeed > 1){
      defaultSpeed = 1;
      return;
    }

    defaultSpeed = newDefaultSpeed;
  }//end setDefaultSpeed

  public double getDefaultSpeed(){
    return defaultSpeed;
  }//end getDefaultSpeed

  public void brake(){
    raise(0);
  }//end brake
}

 
