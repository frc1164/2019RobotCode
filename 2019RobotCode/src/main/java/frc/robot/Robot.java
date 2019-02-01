/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import edu.wpi.first.wpilibj.

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  TalonSRX Talon1 = new TalonSRX(1); 
  TalonSRX Talon2 = new TalonSRX(2);
  TalonSRX Talon3 = new TalonSRX(3);
  Spark Spark1 = new Spark(1);
  Spark Spark2 = new Spark(2);
  Spark Spark3 = new Spark(3);
  Spark Spark4 = new Spark(4);

  Joystick Stick = new Joystick(0);



  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
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
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    //arcade drive 

    //left joystick
    Spark1.set(Stick.getRawAxis(1));
    Spark2.set(Stick.getRawAxis(1));
    Spark3.set(-Stick.getRawAxis(1));
    Spark4.set(-Stick.getRawAxis(1));
    Talon3.set(ControlMode.PercentOutput, -Stick.getRawAxis(0));
    
      /*
      // set left drive to left joystick value
      Spark1.set(Stick.getRawAxis(1));
      Spark2.set(Stick.getRawAxis(1));
   
      //set right drive to right joystick
      Spark3.set(-Stick.getRawAxis(3));
      Spark4.set(-Stick.getRawAxis(3));
    */
    if (Stick.getRawButton(4) == true) {// move climbing arm up on button press and stop on release
      Talon1.set(ControlMode.PercentOutput, 0.1);
      Talon2.set(ControlMode.PercentOutput, -0.1);
    } else if (Stick.getRawButton(2) == true) {
      Talon1.set(ControlMode.PercentOutput, -0.1);
      Talon2.set(ControlMode.PercentOutput, 0.1);
    } else {
      Talon1.set(ControlMode.PercentOutput, 0); 
      Talon2.set(ControlMode.PercentOutput, 0);
    }// end ifelse

  }// end teleopPeriodic

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

}
