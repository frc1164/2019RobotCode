/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Lift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_VictorSPX liftMotor;
  private Encoder liftEncoder;
  public NetworkTableEntry sP;

  public NetworkTableEntry kP, kI, kD, kV, V_MAX, A_MAX;
  public ShuffleboardTab tuning = Shuffleboard.getTab("PID");
  


public Lift(){

  liftMotor = new WPI_VictorSPX(RobotMap.LiftMotor);
  SmartDashboard.putData("Lift Motor", liftMotor);
  
  liftEncoder = new Encoder(RobotMap.Lift_Encoder_ChannelA, RobotMap.Lift_Encoder_ChannelB, true, Encoder.EncodingType.k4X);
  liftEncoder.setDistancePerPulse(3.57/127.2);
  liftEncoder.setMinRate(10);
  //liftEncoder.setDistancePerPulse(1);
  liftEncoder.reset();

   
  V_MAX = tuning.add("VMax", 1).getEntry();
  

  A_MAX = tuning.add("AMax", 1).getEntry();
  

  kP = tuning.add("kP", 0).getEntry();
  

  kI = tuning.add("kI", 0).getEntry();
  

  kD = tuning.add("kD", 0).getEntry();
  

  kV = tuning.add("kV", 0).getEntry();
  
  sP = tuning.add("Set Point", 0).getEntry();
  

}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    
  }
 
  public double getRaw(){

  return liftEncoder.getRaw();
  }

  public double getDistance(){
    return liftEncoder.getDistance();
  }
  public void resetEncoder(){
    liftEncoder.reset();
  }

  public void setLiftSpeed(double speed){
    SmartDashboard.putNumber("Lift Motor Speed", speed);
    liftMotor.set(speed);

  }

  public void showVoltage(){

  }
 
}
