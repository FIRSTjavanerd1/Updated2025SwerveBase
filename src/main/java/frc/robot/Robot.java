// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.lib.util.loggingUtil.LogManager;
import frc.robot.subsystems.CRollers;
import frc.robot.subsystems.swerve.rev.RevSwerve;
import frc.robot.commands.TeleopSwerve;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static RevConfigs ctreConfigs;

  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private double autospeed;

  
  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    ctreConfigs = new RevConfigs();
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
   
    // Start capturing from USB camera
        UsbCamera camera = CameraServer.startAutomaticCapture();
        camera.setResolution(320, 240);
        camera.setFPS(30);

        // Add camera feed to Shuffleboard
        Shuffleboard.getTab("Camera")
    .add("USB Camera", camera)
    .withWidget(BuiltInWidgets.kCameraStream)
    .withSize(6, 4)
    .withPosition(0, 0);
   
    
  

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    LogManager.log();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {

    if (DriverStation.getAlliance().get() == Alliance.Blue) {
      autospeed = 0.5;
    } else {
      autospeed = 0.5;
    }

    Command autoDriveCommand = new TeleopSwerve(
      m_robotContainer.getRevSwerve(), 
         () -> autospeed * 0.5, 
        () -> 0.0 * 0.5, 
        () -> 0.0 * 0.5, 
        () -> false).withTimeout(1.0);

    Command waitForTime = new WaitCommand(1.7);

    Command stopCommand = new TeleopSwerve(
      m_robotContainer.getRevSwerve(), 
         () -> 0.0 * 0.5, 
        () -> 0.0 * 0.5, 
        () -> 0 * 0.5, 
        () -> false);

    Command scoreCoral = m_robotContainer.getCRollers().forwardCRollers();

    Command runAuto = new SequentialCommandGroup(autoDriveCommand,waitForTime,stopCommand,scoreCoral);

    runAuto.schedule();

    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    
  
   // m_robotContainer.setStartingPose();

    
    // schedule the autonomous command (example)
    //  if (m_autonomousCommand != null) {
    //   m_autonomousCommand.schedule();
    // } 

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    











    
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
