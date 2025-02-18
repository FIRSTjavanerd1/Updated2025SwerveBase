package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.TeleopSwerve;
import frc.robot.subsystems.CRollers;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.ServoSubsystem;
import frc.robot.subsystems.swerve.rev.RevSwerve;


/**
 * This is Luke This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private final Joystick operator = new Joystick(1);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);

    private final JoystickButton intake = new JoystickButton(operator,6);
    private final JoystickButton scoreAlgae = new JoystickButton(operator,1);
    private final JoystickButton climbUp = new JoystickButton(operator,2);
    private final JoystickButton scoreCoral = new JoystickButton(operator, 4);
    private final JoystickButton reverseCoral = new JoystickButton(operator, 5);

    /* Subsystems */
    private final RevSwerve s_Swerve = new RevSwerve();
   
    private final Intake s_Intake = new Intake();
    private final Climb s_Climb = new Climb();
    private final ServoSubsystem s_Servo = new ServoSubsystem();
    private final CRollers s_CRollers = new CRollers();
    private final Pivot s_Pivot = new Pivot();

    private final SendableChooser<Command> autoChooser = new SendableChooser<Command>();
  
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
          s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(2), 
                () -> false
            )
        ); 

      
        s_Climb.setDefaultCommand(s_Climb.stopClimb());
        s_CRollers.setDefaultCommand(s_CRollers.stopRollers());
        s_Pivot.setDefaultCommand(s_Pivot.pivotUp());

       
      
        // Configure the button bindings
        configureButtonBindings();

      SmartDashboard.putData("Auto Mode", autoChooser); 
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
       


        /* Operator Buttons */

        intake.whileTrue(new ParallelCommandGroup(s_Intake.runForwardIntake()).alongWith(s_Pivot.pivotDown()));
        intake.onFalse(s_Pivot.pivotUp());
        scoreAlgae.onTrue(s_Intake.runBackwardIntake());
        climbUp.onTrue(new ParallelCommandGroup(s_Servo.backwardServo()).andThen(s_Climb.climbUp()));
        scoreCoral.whileTrue(s_CRollers.forwardCRollers());
        scoreCoral.onFalse(s_CRollers.stopRollers());
        reverseCoral.whileTrue(s_CRollers.backwardCRollers());
        reverseCoral.onFalse(s_CRollers.stopRollers());
        
        

    }
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
     public Command getAutonomousCommand() {
   return autoChooser.getSelected();
  }
}
//test
