package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pivot;

public class DownPivotLimitSwitch extends Command{

    private final Pivot pivot;

    public DownPivotLimitSwitch(Pivot pivot) {
        this.pivot = pivot;
        addRequirements(pivot);
    }

    @Override
    public void execute() {
        if (pivot.isDownPivotLimitSwitchPressed()) {
            pivot.setPivotSpeed(0);
        } 
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}

