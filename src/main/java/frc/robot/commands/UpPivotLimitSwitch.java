package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pivot;

public class UpPivotLimitSwitch extends Command{

    private final Pivot pivot;

    public UpPivotLimitSwitch(Pivot pivot) {
        this.pivot = pivot;
        addRequirements(pivot);
    }

    @Override
    public void execute() {
        if (pivot.isUpPivotLimitSwitchPressed()) {
            pivot.setPivotSpeed(0);
        } 
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}

