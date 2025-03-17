// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class LinearActuator extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public LinearActuator() {}

  private final VictorSPX linearActuator = new VictorSPX(8); 



public Command startLinearActuator() {
    return run(() -> linearActuator.set(VictorSPXControlMode.PercentOutput, 1))
    .withName("linear actuator started");
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}