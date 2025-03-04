package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climb;

public class ClimbLimitSwitch extends Command{

    private final Climb climb;

    public ClimbLimitSwitch(Climb climb) {
        this.climb = climb;
        addRequirements(climb);
    }

    @Override
    public void execute() {
        if (!climb.isClimbLimitSwitchPressed()) {
            climb.setClimbSpeed(0.8); // Stop motor when switch is pressed
        } else {
            climb.setClimbSpeed(0); // Resume motor when switch is released
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}

