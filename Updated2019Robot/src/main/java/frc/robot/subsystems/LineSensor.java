/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class LineSensor extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  
  private static final int BAUDRATE = 9600;
  private int bufferSize = 1;
  private SerialPort arduino;
  private int maxStringLen = 5;
  private int prevValue = 0;
  private char endLineChar = '\n';


  public LineSensor(){

    try{
      arduino = new SerialPort(BAUDRATE, SerialPort.Port.kUSB1);
      arduino.setReadBufferSize(bufferSize);

    } catch(Exception e){
      arduino = null;
      System.out.println("Lost Arduino");
    }
    

  }// of default constructor

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public String getString(){
    //return String from buffer
    String string = "";
    int count = 0;
    char currentChar;
    while(count < 2){
      currentChar = (char) arduino.read(1)[0];
      string += currentChar;
      if(currentChar == '\n') count++;
    }//end while
    SmartDashboard.putString("Arduino String", string);
    return string;
  }// of method getRaw

  public int getInt(){
    String dataIn = getString();
    int index;
    index = dataIn.lastIndexOf(endLineChar);
    dataIn = dataIn.substring(0,index);
    index = dataIn.lastIndexOf(endLineChar);
    dataIn = dataIn.substring(index+1);
    prevValue = Integer.parseInt(dataIn);
    SmartDashboard.putNumber("Line Sensor", Integer.parseInt(dataIn));
    return Integer.parseInt(dataIn);
    }//end getDouble

  public void reset(){
    arduino.reset();
  }//end reset

}// of Subsystem LineSeneor
