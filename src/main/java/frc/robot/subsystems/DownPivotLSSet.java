package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;

public class DownPivotLSSet extends SubsystemBase {

    private final DigitalInput pivotDownLimitSwitch;
    private final Pivot pivot;

    public DownPivotLSSet(Pivot pivot) { 
        this.pivot = pivot;
        pivotDownLimitSwitch = new DigitalInput(4); 
    }

    public void runPivot(double speed) {
        pivot.setPivotSpeed(speed);
    }

    public boolean isDownPivotLimitSwitchPressed() {
        return pivotDownLimitSwitch.get(); // Check if the limit switch is pressed
    }
}


