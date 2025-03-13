package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DownPivotLSSet;
import frc.robot.subsystems.Pivot;

public class DownPivotLimitSwitch extends Command{

    private final DownPivotLSSet pivotSet;


    public DownPivotLimitSwitch(DownPivotLSSet pivotSet) {
        this.pivotSet = pivotSet;
        addRequirements(pivotSet);
    }

    @Override
    public void execute() {
        if (!pivotSet.isDownPivotLimitSwitchPressed()) {
            pivotSet.runPivot(0);
        } 
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}

