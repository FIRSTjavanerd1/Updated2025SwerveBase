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

    public void setServoPosition(double position) {
        servo.set(position);
    }

    public void setServoAngle(double angle) {
        servo.setAngle(angle);
    }

    // Get the current servo position
    public double getServoPosition() {
        return servo.get();
    }

    // Get the current servo angle
    public double getServoAngle() {
        return servo.getAngle();
    }

    

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}