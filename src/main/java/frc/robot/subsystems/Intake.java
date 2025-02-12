// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Intake extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public Intake() {}

  private final SparkMax intakeMotor = new SparkMax(10, MotorType.kBrushless); // might not be 10


  public Command runForwardIntake() {
    if (intakeMotor.getOutputCurrent() < 45) { 
        return run(() -> intakeMotor.set(1.0))
            .withName("Intake");
    } else {
        return run(() -> intakeMotor.set(0.0))
            .withName("Intake Stopped");
    }
}

public Command runBackwardIntake() {
    return run(() -> intakeMotor.set(-1.0))
        .withName("Reverse Intake");
}

@Override
public void periodic() {
    // This method will be called once per scheduler run
}
}
