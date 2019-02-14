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
  private static final int numSensors = 20;
  private char seperator;
  private int bufferSize = 1;
  private SerialPort arduino;
  private Double prevValue;
  private char endLineChar;
  private int maxStringLen;
  public LineSensor(){
    arduino = new SerialPort(BAUDRATE, SerialPort.Port.kUSB1);
    arduino.setReadBufferSize(bufferSize);
    prevValue = 0.0;
    endLineChar = '\n';
    maxStringLen = 6;
    seperator = '\t';
  }// of default constructor

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public String getString(){
    //return String from buffer
    String string = arduino.readString();
    return string;
  }// of method getRaw

  public byte[] getRaw(){
    //return raw data as byte array
    return arduino.read(arduino.getBytesReceived());
  }//end getRaw
 
  public Double getDouble(){
    String dataIn = getString();
    if(dataIn.length() < maxStringLen*2) return prevValue; //if data is too short to parse use last recorded value

    int index;
    index = dataIn.lastIndexOf(endLineChar);
    dataIn = dataIn.substring(0,index);
    index = dataIn.lastIndexOf(endLineChar);
    dataIn = dataIn.substring(index+1);
    return Double.parseDouble(dataIn);
    }//end getDouble


    public void readLongString(){
      String[] sensorValues = new String[numSensors];
      String dataIn = getString();
      double offset;
      for(int i = 0; i < numSensors; i++){
        sensorValues[i] = dataIn.substring(0, dataIn.indexOf(seperator));
        dataIn = dataIn.substring(dataIn.indexOf(seperator)+1);
      }//end for
      dataIn = dataIn.substring(0, dataIn.indexOf(endLineChar));
      offset = Double.parseDouble(dataIn);
      SmartDashboard.putStringArray("LineSensor array", sensorValues);
      SmartDashboard.putNumber("offset", offset);
    }//end sensorTest
}// of Subsystem LineSeneor
