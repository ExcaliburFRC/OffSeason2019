/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.TransporterCommands.TransporterDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Transporter extends Subsystem {
  private static Transporter instance;
  private VictorSPX trasportMotor; 
  private Ultrasonic isBallInSensor;

  private Transporter(){
    isBallInSensor = new Ultrasonic(RobotMap.BallInSensorPingP, RobotMap.BallInSensorEchoP);
    trasportMotor = new VictorSPX(RobotMap.transportMotorP);
  }

  public static Transporter getInstance(){
    if (instance == null) instance = new Transporter();
    return instance;
  }

  public void setTransportSpeed(double speed){
    this.trasportMotor.set(ControlMode.PercentOutput, speed);
  }

  public double getUltrasonicValue(){
    return this.isBallInSensor.getRangeMM();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TransporterDrive());
  }
}
