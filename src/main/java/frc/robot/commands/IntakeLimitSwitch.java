package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;


public class IntakeLimitSwitch extends Command{

    private final Intake intake;
    
    

    public IntakeLimitSwitch(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        /*if (intake.isIntakeLimitSwitchPressed()) {
            intake.setIntakeSpeed(0.8); 
        } else {
            intake.setIntakeSpeed(0); 
            
        }*/

        intake.setIntakeState(1);
        
    }

    @Override
    public boolean isFinished() {
        return !intake.isIntakeLimitSwitchPressed();
    }

}
