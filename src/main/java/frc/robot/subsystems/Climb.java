// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Climb extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public Climb() {}

  private final SparkMax climb = new SparkMax(10, MotorType.kBrushless); 


public Command climbUp() {
 
    return runOnce(() -> climb.set(0.1))
    .withName("climbUp");
  
  }

  public Command climbDown() {
    return runOnce(() -> climb.set(-0.1))
    .withName("climbDn");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}