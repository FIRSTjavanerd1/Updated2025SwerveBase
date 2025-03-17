package frc.robot.constants;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.lib.util.swerveUtil.RevSwerveModuleConstants;


/**
 * This file comes with command robot projects, and is intended to contain
 * configuration information.
 * I think it would be prudent if this file only contained CanIDs, because it
 * is useful to have all the ids for the whole robot in one place.
 * other configuration goes into subsystem specific configuration files,
 * to make sure this one isn't cluttered.
 */
public final class RevSwerveConstants 
{
    public static final double stickDeadband = 0.1;
    public static final double limelightOffset = 3;
  

    public static final class REV
    {
        public static final int pigeonID = 10;

       
    
    }
    public static final class Swerve {
        /* Module Specific Constants */
    /* Front Left Module - Module 0 */
        public static final class Mod0 { 

            public static final int driveMotorID = 12;
            public static final int angleMotorID = 11;
            public static final int canCoderID = 24;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(-97.26); //Rotation2d.fromDegrees(37.7);
            public static final RevSwerveModuleConstants constants = 
                new RevSwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
        /* Front Right Module - Module 1 */
        public static final class Mod1 { 
            public static final int driveMotorID = 6;
            public static final int angleMotorID = 5;
            public static final int canCoderID = 23;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(-121.2);
            public static final RevSwerveModuleConstants constants = 
                new RevSwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
        /* Back Left Module - Module 2 */
        public static final class Mod2 { 
            public static final int driveMotorID = 7;
            public static final int angleMotorID = 9;
            public static final int canCoderID = 21;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(33.03);
            public static final RevSwerveModuleConstants constants = 
                new RevSwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
         /* Back Right Module - Module 3 */
        public static final class Mod3 { 
            public static final int driveMotorID = 14;
            public static final int angleMotorID = 15;
            public static final int canCoderID = 22;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(32.98);
            public static final RevSwerveModuleConstants constants = 
                new RevSwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
        
    }

     

    
    
    

   

}