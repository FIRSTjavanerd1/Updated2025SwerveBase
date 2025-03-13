package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;

public class UpPivotLSSet extends SubsystemBase {

    private final DigitalInput pivotUpLimitSwitch;
    private final Pivot pivot;

    public UpPivotLSSet(Pivot pivot) { 
        this.pivot = pivot;
        pivotUpLimitSwitch = new DigitalInput(3); 
    }

    public void runPivot(double speed) {
        pivot.setPivotSpeed(speed);
    }

    public boolean isUpPivotLimitSwitchPressed() {
        return pivotUpLimitSwitch.get(); // Check if the limit switch is pressed
    }
}


