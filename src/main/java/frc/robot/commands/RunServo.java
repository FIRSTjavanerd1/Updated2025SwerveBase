package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ServoSubsystem;

public class RunServo extends Command{

    private final ServoSubsystem servoSubsystem;
    private final double position;

    public RunServo(ServoSubsystem servoSubsystem, double position) {
        this.servoSubsystem = servoSubsystem;
        this.position = position;
        addRequirements(servoSubsystem);
    }

    @Override
    public void initialize() {
        servoSubsystem.setServoPosition(position);
    }

    @Override
    public boolean isFinished() {
        return true; // Run once and finish
    }

}
