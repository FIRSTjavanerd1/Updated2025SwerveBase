// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class CRollers extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public CRollers() {}

    private final SparkMax coralMotor = new SparkMax(3, MotorType.kBrushless); 



public Command forwardCRollers() {
    return run(() -> coralMotor.set(1))
    .withName("forwardCRollers");
  }
public Command stopRollers(){
  return runOnce(()-> coralMotor.set(0))
  .withName("Stop Coral Rollers");
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}