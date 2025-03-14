package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pivot;

public class DownPivotLimitSwitch extends Command{

    private final Pivot pivot;
    private final Intake intake;


    public DownPivotLimitSwitch(Pivot pivot, Intake intake) {
        this.pivot = pivot;
        this.intake = intake;
        addRequirements(pivot, intake);
    }

    @Override
    public void execute() {
        if (pivot.isDownPivotLimitSwitchPressed()) {
            pivot.setPivotSpeed(0);
        } 
        intake.setIntakeState(1);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}

