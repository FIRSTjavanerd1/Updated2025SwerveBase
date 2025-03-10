// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Climb extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public Climb() {}

private final SparkMax climb = new SparkMax(10,MotorType.kBrushless);
DigitalInput climbLimitSwitch = new DigitalInput(2);

  public void setClimbSpeed(double speed) {
    climb.set(speed);
}

public boolean isClimbLimitSwitchPressed() {
    return climbLimitSwitch.get();
}



@Override
public void periodic() {
    // This method will be called once per scheduler run

   
}
}


