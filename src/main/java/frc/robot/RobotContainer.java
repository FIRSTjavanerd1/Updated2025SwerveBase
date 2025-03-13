package frc.robot;


import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ClimbLimitSwitch;
import frc.robot.commands.IntakeLimitSwitch;
import frc.robot.commands.RunServo;
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
    
    private final CommandXboxController driver = new CommandXboxController(0);
    private final CommandXboxController operator = new CommandXboxController(1);
    

    /* Subsystems */
    private final RevSwerve s_Swerve = new RevSwerve();
   
    private final Intake s_Intake = new Intake();
    private final Climb s_Climb = new Climb();
    private final ServoSubsystem s_Servo = new ServoSubsystem();
    private final CRollers s_CRollers = new CRollers();
    private final Pivot s_Pivot = new Pivot();
    private final IntakeLimitSwitch intakeLimitSwitch = new IntakeLimitSwitch(s_Intake);
    private  SendableChooser<Command> autoChooser = new SendableChooser<>();

    
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {

      
     
  s_Swerve.setDefaultCommand(
  new TeleopSwerve(
    s_Swerve, 
      () -> driver.getLeftY(), 
      () -> driver.getLeftX(),
      () -> driver.getRightX(), 
      () -> false));

      /* () -> driver.getLeftY(), 
      () -> driver.getLeftX(), 
      () -> driver.getRightX(),  */
      
 
      
        
        s_CRollers.setDefaultCommand(s_CRollers.stopRollers());
        //s_Pivot.setDefaultCommand(s_Pivot.pivot());
        s_Intake.setDefaultCommand(intakeLimitSwitch); 


       
      
        // Configure the button bindings
        configureButtonBindings();
      //autoChooser = AutoBuilder.buildAutoChooser();
      //SmartDashboard.putData("Auto Mode", autoChooser); 

      //NamedCommands.registerCommand("scoreCoral",s_CRollers.forwardCRollers().andThen(new WaitCommand(1).andThen(s_CRollers.stopRollers())));

      // autoChooser.addOption("Far Blue Coral", new PathPlannerAuto("Far Blue Coral"));
      // autoChooser.addOption("Drive Forward", new PathPlannerAuto("Drive Forward"));

      autoChooser = AutoBuilder.buildAutoChooser();
      SmartDashboard.putData("Auto Chooser", autoChooser);
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        driver.a().onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
       
        /* Operator Buttons */
        
        
        operator.a().whileTrue(new RunCommand(() -> s_Intake.setIntakeSpeed(-1)));
        operator.a().onFalse(new RunCommand(() -> s_Intake.setIntakeSpeed(0)));
        operator.b().onTrue(new ParallelCommandGroup(new ClimbLimitSwitch(s_Climb), new RunServo(s_Servo, .2)).andThen(new WaitCommand(1)).andThen(new RunCommand(() -> s_Climb.setClimbSpeed(0.8))).alongWith(new RunServo(s_Servo, -0.3)));
        operator.b().onFalse(new RunCommand(() -> s_Climb.setClimbSpeed(0)));
        operator.x().whileTrue(s_CRollers.forwardCRollers());
        operator.x().onFalse(s_CRollers.stopRollers());
        operator.y().onTrue(new ParallelCommandGroup(s_Pivot.pivotDown(),new IntakeLimitSwitch(s_Intake)));
        operator.y().onFalse((s_Pivot.pivotUp()));
        
        
        

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
