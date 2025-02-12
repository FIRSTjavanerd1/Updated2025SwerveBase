// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.RelativeEncoder;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Pivot extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public Pivot() {}

  private final SparkMax pivotMotor = new SparkMax(13, MotorType.kBrushless); // might not be 13

  private final RelativeEncoder pivotEncoder = pivotMotor.getEncoder();
  private final PIDController pivotController = new PIDController(3, 0.0, 0.03); // these pid need to be adjusted


public Command pivotDown() {
  return run(()->
  pivotMotor.set(pivotController.calculate(pivotEncoder.getPosition(), -0.4)))//might not be -0.4
  .withName("Pivot Down");
  }

  public Command pivotUp() {
    return run(()->
  pivotMotor.set(pivotController.calculate(pivotEncoder.getPosition(), 0.4)))//might not be 0.4
  .withName("Pivot Up");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Pivot Encoder", pivotEncoder.getPosition());
    SmartDashboard.putNumber("PID Output SpeakerPOS", pivotController.calculate(pivotEncoder.getPosition(), -0.8));
  }


}