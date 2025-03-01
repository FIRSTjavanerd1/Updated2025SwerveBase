package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class LimitSwitch extends Command{

    private final Intake intake;

    public LimitSwitch(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        if (intake.isLimitSwitchPressed()) {
            intake.setIntakeSpeed(0.8); // Stop motor when switch is pressed
        } else {
            intake.setIntakeSpeed(0); // Resume motor when switch is released
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
