package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.UpPivotLSSet;

public class UpPivotLimitSwitch extends Command{

    private final UpPivotLSSet pivotSet;


    public UpPivotLimitSwitch(UpPivotLSSet pivotSet) {
        this.pivotSet = pivotSet;
        addRequirements(pivotSet);
    }

    @Override
    public void execute() {
        if (!pivotSet.isUpPivotLimitSwitchPressed()) {
            pivotSet.runPivot(0);
        } 
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}

