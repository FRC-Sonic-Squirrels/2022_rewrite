// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static final String CANBUS_STR = "rio";
  public static final String CANBUS2_STR = "CANivore";

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static final class SWERVE_CONSTANTS {
    public static final int FLEFT_DRIVE_MOTOR = 1;
    public static final int FLEFT_STEER_MOTOR = 11;
    public static final int FLEFT_CANCODER = 21;

    public static final int FRIGHT_DRIVE_MOTOR = 2;
    public static final int FRIGHT_STEER_MOTOR = 12;
    public static final int FRIGHT_CANCODER = 22;

    public static final int BLEFT_DRIVE_MOTOR = 4;
    public static final int BLEFT_STEER_MOTOR = 14;
    public static final int BLEFT_CANCODER = 24;

    public static final int BRIGHT_DRIVE_MOTOR = 3;
    public static final int BRIGHT_STEER_MOTOR = 13;
    public static final int BRIGHT_CANCODER = 23;

  }
  

  public static class INTAKE_CONSTANTS {
    // intake
    public static final int INTAKE_MOTOR_ID = 18;

    private static final int INTAKE_MOTOR_RPM = 400;
    public static final double INTAKE_MOTOR_RPS = (INTAKE_MOTOR_RPM / 60.0) * 5;
  
    public static final int PNEUMATIC_CYL_CHANNEL = 15;
  }


  public static class SHOOTER_CONSTANTS {
    public static final int FLYWHEEL_MOTOR_ID = 16;

    private static final int TARGET_RPM = 200;
    public static final double TARGET_RPS = TARGET_RPM / 60.0;

    private static final int TARGET_ACCELERATION = 10;
    public static final double TARGET_ACCELERATION_PS = TARGET_ACCELERATION / 60.0;
  }
}
