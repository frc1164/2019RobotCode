/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.cscore.UsbCamera;


/**
 * Add your docs here.
 */
public class Vision extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private UsbCamera pixyTest = CameraServer.getInstance().startAutomaticCapture();
  private int cameraFrameRate = 0;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setCameraFrameRate(int frameRate){
    this.cameraFrameRate = frameRate;
    pixyTest.setFPS(frameRate);

  }

  public double getCameraFrameRate(){
    return pixyTest.getActualFPS();
  }

  public double getCameraDataRate(){

    return pixyTest.getActualDataRate();
  }
}
