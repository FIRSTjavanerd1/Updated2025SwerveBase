
package frc.robot.subsystems.swerve.rev;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.DriverStation;
import frc.lib.util.swerveUtil.CTREModuleState;
import frc.lib.util.swerveUtil.RevSwerveModuleConstants;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
//import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
// import com.revrobotics.SparkPIDController;
// import com.revrobotics.CANSparkBase.ControlType;
// import com.revrobotics.CANSparkBase.FaultID;
// import com.revrobotics.CANSparkLowLevel.MotorType;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkClosedLoopController;

/**
 * a Swerve Modules using REV Robotics motor controllers and CTRE CANcoder absolute encoders.
 */
public class RevSwerveModule implements SwerveModule
{
    public int moduleNumber;
    private Rotation2d angleOffset;

    private SparkMax mAngleMotor;
    private SparkMax mDriveMotor;




    private CANcoder angleEncoder;
    private RelativeEncoder relAngleEncoder;
    public RelativeEncoder relDriveEncoder;




    public RevSwerveModule(int moduleNumber, RevSwerveModuleConstants moduleConstants)
    {
        this.moduleNumber = moduleNumber;
        this.angleOffset = moduleConstants.angleOffset;
        
       
        /* Angle Motor Config */
        mAngleMotor = new SparkMax(moduleConstants.angleMotorID, MotorType.kBrushless);
        configAngleMotor();

        /* Drive Motor Config */
        mDriveMotor = new SparkMax(moduleConstants.driveMotorID,  MotorType.kBrushless);
        //configDriveMotor();

         /* Angle Encoder Config */
    
        angleEncoder = new CANcoder(moduleConstants.cancoderID);
        configEncoders();


    }

    private void configEncoders()
    {     
        SparkMaxConfig configRelDrive = new SparkMaxConfig();
     /*    config
            .inverted(true)
            .idleMode(IdleMode.kBrake); */
        configRelDrive.encoder
            .positionConversionFactor(RevSwerveConfig.driveRevToMeters)
            .velocityConversionFactor(RevSwerveConfig.driveRpmToMetersPerSecond);
   /*      config.closedLoop
    .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
    .pid(1.0, 0.0, 0.0); */
    

        //Absolute Encoder;   
         angleEncoder.getConfigurator().apply(new CANcoderConfiguration());
         angleEncoder.getConfigurator().apply(new RevSwerveConfig().canCoderConfig);

        

 
       
        relDriveEncoder = mDriveMotor.getEncoder();
        relDriveEncoder.setPosition(0);
        //relDriveEncoder.PositionConversionFactor(RevSwerveConfig.driveRevToMeters);
        //relDriveEncoder.setVelocityConversionFactor(RevSwerveConfig.driveRpmToMetersPerSecond);
        mDriveMotor.configure(configRelDrive, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        
        
        SparkMaxConfig configAngleDrive = new SparkMaxConfig();
        configRelDrive.encoder
        .positionConversionFactor(RevSwerveConfig.driveRevToMeters)
        .velocityConversionFactor(RevSwerveConfig.driveRpmToMetersPerSecond);
        
       // mAngleMotor.configure(configAngleDrive, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        relAngleEncoder = mAngleMotor.getEncoder();
       // relAngleEncoder.setPositionConversionFactor(RevSwerveConfig.DegreesPerTurnRotation);
        // in degrees/sec
       // relAngleEncoder.setVelocityConversionFactor(RevSwerveConfig.DegreesPerTurnRotation / 60);
    

         resetToAbsolute();
        // mDriveMotor.burnFlash();
        // mAngleMotor.burnFlash();

        
        
    }

    private void configAngleMotor()
    {

        SparkMaxConfig config = new SparkMaxConfig();

        config.signals.primaryEncoderPositionPeriodMs(5);
        mAngleMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
      //  double position = mAngleMotor.getEncoder().getPosition();

       
        // SparkPIDController controller = mAngleMotor.getPIDController();
        // controller.setP(RevSwerveConfig.angleKP, 0);
        // controller.setI(RevSwerveConfig.angleKI,0);
        // controller.setD(RevSwerveConfig.angleKD,0);
        // controller.setFF(RevSwerveConfig.angleKF,0);
        // controller.setOutputRange(-RevSwerveConfig.anglePower, RevSwerveConfig.anglePower);
        // mAngleMotor.setSmartCurrentLimit(RevSwerveConfig.angleContinuousCurrentLimit);
       
        // mAngleMotor.setInverted(RevSwerveConfig.angleMotorInvert);
        // mAngleMotor.setIdleMode(RevSwerveConfig.angleIdleMode);

      

        config
            .inverted(true)
            .idleMode(IdleMode.kBrake);
        config.encoder
            .positionConversionFactor(16.8);
        config.closedLoop
            .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
            .pid(0.02, 0.0, 0.006);
            
        mAngleMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        //
       
    }

    private void configDriveMotor()
    {        
        SparkMaxConfig config = new SparkMaxConfig();

        config.signals.primaryEncoderPositionPeriodMs(5);
        mDriveMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        //mDriveMotor.restoreFactoryDefaults();
        // SparkPIDController controller = mDriveMotor.getPIDController();
        // controller.setP(RevSwerveConfig.driveKP,0);
        // controller.setI(RevSwerveConfig.driveKI,0);
        // controller.setD(RevSwerveConfig.driveKD,0);
        // controller.setFF(RevSwerveConfig.driveKF,0);
        // controller.setOutputRange(-RevSwerveConfig.drivePower, RevSwerveConfig.drivePower);
        // mDriveMotor.setSmartCurrentLimit(RevSwerveConfig.driveContinuousCurrentLimit);
        // mDriveMotor.setInverted(RevSwerveConfig.driveMotorInvert);
        // mDriveMotor.setIdleMode(RevSwerveConfig.driveIdleMode); 
    
        config
            .inverted(true)
            .idleMode(IdleMode.kBrake);
        config.encoder
            .positionConversionFactor(16.8);
        config.closedLoop
            .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
            .pid(0.02, 0.0, 0.0006);
            
        mDriveMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
       
       
       
    }



    public void setDesiredState(SwerveModuleState desiredState, boolean isOpenLoop)
    {
        
        
        /* This is a custom optimize function, since default WPILib optimize assumes continuous controller which CTRE and Rev onboard is not */
        // CTREModuleState actually works for any type of motor.
        desiredState = CTREModuleState.optimize(desiredState, getState().angle); 
        setAngle(desiredState);
        setSpeed(desiredState, isOpenLoop);

        if(mDriveMotor.getFaults() != null)
        {
            DriverStation.reportWarning("Sensor Fault on Drive Motor ID:"+mDriveMotor.getDeviceId(), false);
        }

        if(mAngleMotor.getFaults() != null)
        {
            DriverStation.reportWarning("Sensor Fault on Angle Motor ID:"+mAngleMotor.getDeviceId(), false);
        }
    }

    private void setSpeed(SwerveModuleState desiredState, boolean isOpenLoop)
    {
       
        if(isOpenLoop)
        {
            double percentOutput = desiredState.speedMetersPerSecond / RevSwerveConfig.maxSpeed;
            mDriveMotor.set(percentOutput);
            return;
        }
 
        double velocity = desiredState.speedMetersPerSecond;
        
        SparkClosedLoopController controller = mDriveMotor.getClosedLoopController();
        controller.setReference(velocity, ControlType.kVelocity, ClosedLoopSlot.kSlot0);
        
    }

    private void setAngle(SwerveModuleState desiredState)
    {
        if(Math.abs(desiredState.speedMetersPerSecond) <= (RevSwerveConfig.maxSpeed * 0.01)) 
        {
            mAngleMotor.stopMotor();
            return;

        }
        Rotation2d angle = desiredState.angle; 
        //Prevent rotating module if speed is less then 1%. Prevents Jittering.
        
        SparkClosedLoopController controller = mAngleMotor.getClosedLoopController();
        
        double degReference = angle.getDegrees();
     
       
        
        controller.setReference (degReference, ControlType.kPosition);
        //the below has no errors but not sure it's right
        //controller.setReference (degReference, ControlType.kPosition, null, 0);
        
    }

   

    private Rotation2d getAngle()
    {
        return Rotation2d.fromDegrees(relAngleEncoder.getPosition());
    }

    public Rotation2d getCanCoder()
    {
        
        return Rotation2d.fromDegrees(angleEncoder.getAbsolutePosition().getValueAsDouble() * 360);
    }

    public int getModuleNumber() 
    {
        return moduleNumber;
    }

    public void setModuleNumber(int moduleNumber) 
    {
        this.moduleNumber = moduleNumber;
    }

    private void resetToAbsolute()
    {
    
        double absolutePosition =getCanCoder().getDegrees() - angleOffset.getDegrees();
        relAngleEncoder.setPosition(absolutePosition);
    }

  

    public SwerveModuleState getState()
    {
        return new SwerveModuleState(
            relDriveEncoder.getVelocity(),
            getAngle()
        ); 
    }

    public SwerveModulePosition getPosition()
    {
        return new SwerveModulePosition(
            relDriveEncoder.getPosition(), 
            getAngle()
        );
    }

    public Double swerveDistance()
    {
        return relDriveEncoder.getPosition();
    }

    public void resetDriveEncoders(){
        relDriveEncoder.setPosition(0.0);
    }

}

