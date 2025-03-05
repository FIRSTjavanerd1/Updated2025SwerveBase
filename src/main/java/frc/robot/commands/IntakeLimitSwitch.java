package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pivot;

public class IntakeLimitSwitch extends Command{

    private final Intake intake;
    private final Pivot pivot;
    

    public IntakeLimitSwitch(Intake intake, Pivot pivot) {
        this.intake = intake;
        this.pivot = pivot;
        addRequirements(intake,pivot);
    }

    @Override
    public void execute() {
        if (intake.isIntakeLimitSwitchPressed()) {
            intake.setIntakeSpeed(0.8); 
            pivot.pivotUp();
        } else {
            intake.setIntakeSpeed(0); 
            
        }
        
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
