package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.TeleopSwerve;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Intake;
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

    private final JoystickButton intake = new JoystickButton(operator,1);
    private final JoystickButton score = new JoystickButton(operator,2);

    private final JoystickButton climbUp = new JoystickButton(operator,3);
    

    /* Subsystems */
    private final RevSwerve s_Swerve = new RevSwerve();
   
    private final Intake s_Intake = new Intake();
    private final Climb s_Climb = new Climb();
    private final ServoSubsystem s_Servo = new ServoSubsystem();
    private final CRollers s_CRollers = new CRollers();

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

        intake.whileTrue(new ParallelCommandGroup(s_Intake.runBackwardIntake()).alongWith(s_CRollers.));
        score.onTrue(s_Intake.runForwardIntake());
        climbUp.onTrue(new ParallelCommandGroup(s_Servo.backwardServo()).andThen(s_Climb.climbUp()));
        
        

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
