package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Servo;

public class ServoSubsystem extends SubsystemBase {
    private final Servo servo;

    public ServoSubsystem() {
        servo = new Servo(0); // Assign a PWM port 
    }

    public Command forwardServo() {
        return Commands.runOnce(() -> servo.setAngle(180));
    }

    public Command backwardServo() {
        return Commands.runOnce(() -> servo.setAngle(0));
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}