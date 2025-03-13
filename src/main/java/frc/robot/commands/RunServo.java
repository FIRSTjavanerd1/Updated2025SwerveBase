package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ServoSubsystem;

public class RunServo extends Command{

    private final ServoSubsystem servoSubsystem;
    

    public RunServo(ServoSubsystem servoSubsystem) {
        this.servoSubsystem = servoSubsystem;
        addRequirements(servoSubsystem);
    }

    @Override
    public void initialize() {
        servoSubsystem.setServoPosition();
    }

    @Override
    public boolean isFinished() {
        return true; // Run once and finish
    }

}
