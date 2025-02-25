// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Climb extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public Climb() {}

private final TalonFX climb = new TalonFX(10);

public Command climbUp() {
 
    return run(() -> climb.set(0.85)) // might be too slow
    .withName("climbUp");
  
  }

public Command stopClimb() {
return run(() -> climb.set(0)).withName("Climb Stopped");
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}