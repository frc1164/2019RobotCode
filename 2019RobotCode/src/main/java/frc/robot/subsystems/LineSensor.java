/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class LineSensor extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final int BAUDRATE = 9600;
  private final int array1_numSensors = 20;
  private final int array2_numSensors = 20;
  private int bufferSize = 1;
  private SerialPort arduino;
  //TODO: update offsets of each sensor
  private double[] array1_offsets;
  private double[] array2_offsets;

  public LineSensor(){
    arduino = new SerialPort(BAUDRATE, SerialPort.Port.kUSB1);
    arduino.setReadBufferSize(bufferSize);
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

  /**
   * Get the calculated center of white line
   * @return Calculated offset from center of bot
   */
  public double getArray1(){
    double[] lineDetected = new double[array1_numSensors];    
    String data = getString();
    int i, j = 0;
    double sum = 0;

    //Find all positions where white was detected
    for( i = 0; i < array1_numSensors; i++){
      if(data.charAt(i) == 't'){
        lineDetected[j] = array1_offsets[i];
        j++;
      }//end if
    }//end for

    //average the offsets of the sensors that detected white
    for(i = 0; i < j; i++){
      sum += lineDetected[i];
    }//end for 

    return sum/j;
  }//end getArray1

  /**
   * Get calculated center of white line
   * @return Calculated offset from center of bot
   */
  public double getArray2(){
    double[] lineDetected = new double[array1_numSensors];    
    String data = getString();
    int i, j = 0;
    double sum = 0;

    //Find all positions where white was detected
    for(i = array1_numSensors; i < array2_numSensors; i++){
      if(data.charAt(i) == 't'){
        lineDetected[j] = array2_offsets[i];
        j++;
      }//end if
    }//end for

    //average the offsets of the sensors that detected white
    for(i = 0; i < j; i++){
      sum += lineDetected[i];
    }//end for 

    return sum/j;
  }//end getArray2
}// of Subsystem LineSeneor
