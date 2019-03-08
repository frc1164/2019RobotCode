/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.RunBallGrabberWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;



/**
 * Add your docs here.
 */
public class EndEffector extends Subsystem {
  VictorSPX GrabberMotor;
  DoubleSolenoid HatchGrabber, Dropper;
  DigitalInput LimitSwitch0, LimitSwitch1;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {

    setDefaultCommand(new RunBallGrabberWithJoystick(OI.operatorStick));
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public EndEffector(){
    GrabberMotor = new VictorSPX(RobotMap.GrabberMotor);
    HatchGrabber = new DoubleSolenoid(RobotMap.HatchPCM, RobotMap.HatchGrabber1,RobotMap.HatchGrabber2);
    Dropper = new DoubleSolenoid(RobotMap.DropperPCM, RobotMap.Dropper1,RobotMap.Dropper2);
    LimitSwitch0 = new DigitalInput(4);
    LimitSwitch1 = new DigitalInput(5);

    GrabberMotor.setInverted(true);
  }

  public void DropEndEffector() {
    Dropper.set(Value.kForward);
  }

  public void RaiseEndEffector() {
    Dropper.set(Value.kReverse);
  }
  
  public void ExtendHatchGrabber() {
     HatchGrabber.set(Value.kForward);
  }

  public void RetractHatchGrabber() {
     HatchGrabber.set(Value.kReverse);
  }

  public void GrabBall(double speed) {
    GrabberMotor.set(ControlMode.PercentOutput, speed);
  }

  public void EjectBall(double speed) {
    GrabberMotor.set(ControlMode.PercentOutput, -speed);
  }

  public boolean Ballstop() {
    return !LimitSwitch0.get();
  }

  public boolean EndEffectorStop() {
    return !LimitSwitch1.get();
  }

  public void Brake() {
    GrabberMotor.set(ControlMode.PercentOutput, 0);
  }
}


