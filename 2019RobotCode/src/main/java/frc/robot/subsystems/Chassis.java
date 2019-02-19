/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.CustomDriveWithXbox;

/**
 * Add your docs here.
 */
public class Chassis extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Spark Left1, Left2, Right1, Right2;
  private TalonSRX Center;
  private boolean sensorRead[];
  private String testOne = "ftftftftftftfttftftln", testTwo = "ffffffttttttftftftftftftftln";

  public Chassis(){
    Left1 = new Spark(RobotMap.LeftMotor1);
    Left2 = new Spark(RobotMap.LeftMotor2);
    Right1 = new Spark(RobotMap.RightMotor1);
    Right2 = new Spark(RobotMap.RightMotor2);
    Center = new TalonSRX(RobotMap.CenterMotor);
    
    arduinoInputToSmartDashboard(testOne);

    Right1.setInverted(true);
    Right2.setInverted(true);
    Center.setInverted(true);

    
  }//end default constructor

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new CustomDriveWithXbox(OI.getDriverStick()));

  }//end initDefaultCommand

  /**
   * Set the speed of the left two motors
   * @param speed The speed to set
   */
  public void setLeftSpeed(double speed){
    Left1.set(speed);
    Left2.set(speed);
  }//end setLeftSpeed
  /**
   * Set the speed of the right two motors
   * @param speed The speed to set
   */
  public void setRightSpeed(double speed){
    Right1.set(speed);
  }//end setRightSpeed

  /**
   * Set the speed of the center motor
   * @param speed The speed to set
   */
  public void setCenterSpeed(double speed){
    Center.set(ControlMode.PercentOutput, speed * RobotMap.CenterMaxSpeed);
  }//end setCenterSpeed

  public void brake(){
    setLeftSpeed(0);
    setRightSpeed(0);
    setCenterSpeed(0);
  }//end brake

  public void arduinoInputToSmartDashboard(String arduinoInput){
    sensorRead = new boolean[arduinoInput.length()];
    int i = 0;
   do{

      if(arduinoInput.charAt(i) == 't'){
          sensorRead[i] = true;
      }sensorRead[i] = false;
      
      SmartDashboard.putBoolean("Sensor" + i, sensorRead[i]);
      i++;
    }while(arduinoInput.charAt(i) != 'l');
    
  }

  public boolean[] getSensorRead(){

    return this.sensorRead;
  }

}//end Chassis class
