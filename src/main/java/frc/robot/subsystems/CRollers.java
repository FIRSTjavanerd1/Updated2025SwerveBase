// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class CRollers extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public CRollers() {}

  private final VictorSPX CRollers = new VictorSPX(7);

public Command backwardCRollers() {
    return run(() -> CRollers.set(VictorSPXControlMode.PercentOutput, -1))
    .withName("backwardCRollers");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}