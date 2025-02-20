// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Intake extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public Intake() {}

  private final SparkMax intakeMotor = new SparkMax(4, MotorType.kBrushless); 
  DigitalInput limitSwitch = new DigitalInput(2);

  public void setIntakeSpeed(double speed) {
    intakeMotor.set(speed);
}

public boolean isLimitSwitchPressed() {
    return !limitSwitch.get();
}

public Command runBackwardIntake() {
    return run(() -> intakeMotor.set(-0.1))
        .withName("Reverse Intake");
}

public Command stopIntake() {
    return run(() -> intakeMotor.set(0))
        .withName("Intake Stopped By stopIntake");
}

@Override
public void periodic() {
    // This method will be called once per scheduler run

   
}
}
