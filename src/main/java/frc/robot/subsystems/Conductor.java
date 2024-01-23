package frc.robot.subsystems;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Conductor extends SubsystemBase{
    Orchestra orchestra = new Orchestra();

    public Conductor() {
        TalonFX motor1 = new TalonFX(Constants.SWERVE_CONSTANTS.FLEFT_DRIVE_MOTOR, Constants.CANBUS_STR);
        TalonFX motor2 = new TalonFX(Constants.SWERVE_CONSTANTS.FLEFT_STEER_MOTOR, Constants.CANBUS_STR);
        TalonFX motor3 = new TalonFX(Constants.SWERVE_CONSTANTS.FRIGHT_DRIVE_MOTOR, Constants.CANBUS_STR);
        TalonFX motor4 = new TalonFX(Constants.SWERVE_CONSTANTS.FRIGHT_STEER_MOTOR, Constants.CANBUS_STR);
        TalonFX motor5 = new TalonFX(Constants.SWERVE_CONSTANTS.BLEFT_DRIVE_MOTOR, Constants.CANBUS_STR);
        TalonFX motor6 = new TalonFX(Constants.SWERVE_CONSTANTS.BLEFT_STEER_MOTOR, Constants.CANBUS_STR);
        TalonFX motor7 = new TalonFX(Constants.SWERVE_CONSTANTS.BRIGHT_DRIVE_MOTOR, Constants.CANBUS_STR);
        TalonFX motor8 = new TalonFX(Constants.SWERVE_CONSTANTS.BRIGHT_STEER_MOTOR, Constants.CANBUS_STR);

        this.orchestra.addInstrument(motor1, 0);
        this.orchestra.addInstrument(motor2, 0);
        this.orchestra.addInstrument(motor3, 1);
        this.orchestra.addInstrument(motor4, 2);
        this.orchestra.addInstrument(motor5, 3);
        this.orchestra.addInstrument(motor6, 4);
        this.orchestra.addInstrument(motor7, 5);
        this.orchestra.addInstrument(motor8, 6);
    }

    public StatusCode loadMusic(String filename) {
        return this.orchestra.loadMusic(filename);
    }

    public void play() {
        System.out.println("playing");
        this.orchestra.play();
    }

    public void pause() {
        this.orchestra.pause();
    }

    public void stop() {
        
        System.out.println("stopped");
        this.orchestra.stop();
    }
}

