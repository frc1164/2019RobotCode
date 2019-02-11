/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.Chassis;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static Chassis robotChassis = new Chassis();

  TalonSRX Talon1 = new TalonSRX(1);
  TalonSRX Talon2 =new TalonSRX(2);
  Joystick Stick = new Joystick(0);
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
   
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    
    //arcade drive 
    //left joystick
    // Spark1.set(Stick.getRawAxis(1));
    // Spark2.set(Stick.getRawAxis(1));
    // Spark3.set(-Stick.getRawAxis(1));
    // Spark4.set(-Stick.getRawAxis(1));
    // Talon3.set(ControlMode.PercentOutput, -Stick.getRawAxis(0));
    
      /*
      // set left drive to left joystick value
      Spark1.set(Stick.getRawAxis(1));
      Spark2.set(Stick.getRawAxis(1));
   
      //set right drive to right joystick
      Spark3.set(-Stick.getRawAxis(3));
      Spark4.set(-Stick.getRawAxis(3));
    
    if (Stick.getRawButton(4) == true) {// move climbing arm up on button press and stop on release
      Talon1.set(ControlMode.PercentOutput, 0.5);
      Talon2.set(ControlMode.PercentOutput, -0.5);
    } else if (Stick.getRawButton(2) == true) {
      Talon1.set(ControlMode.PercentOutput, -0.5);
      Talon2.set(ControlMode.PercentOutput, 0.5);
    } else {
      Talon1.set(ControlMode.PercentOutput, 0); 
      Talon2.set(ControlMode.PercentOutput, 0);
    }// end ifelse*/

  }// end teleopPeriodic

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
