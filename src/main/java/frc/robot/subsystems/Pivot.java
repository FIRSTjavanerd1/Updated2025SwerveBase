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

  private final SparkMax pivotMotor = new SparkMax(13, MotorType.kBrushless); 

  private final RelativeEncoder pivotEncoder = pivotMotor.getEncoder();
  private final PIDController pivotUpController = new PIDController(0.12, 0.0, 0.00); // these pid need to be adjusted
  private final PIDController pivotDownController = new PIDController(0.06, 0.0, 0.00);
  
public Command pivotDown() {
  return run(()->
  pivotMotor.set(pivotDownController.calculate(pivotEncoder.getPosition(), 0)))//might not be -0.4
  .withName("Pivot Down");
  }

  public Command pivotUp() {
    return run(()->
  pivotMotor.set(pivotUpController.calculate(pivotEncoder.getPosition(), 2.5)))//might not be 0.4
  .withName("Pivot Up");
  
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //SmartDashboard.putNumber("Pivot Encoder", pivotEncoder.getPosition());
    //SmartDashboard.putNumber("PID Output SpeakerPOS", pivotController.calculate(pivotEncoder.getPosition(), -0.8));
    SmartDashboard.putNumber("Encoder", pivotEncoder.getPosition());
  }


}