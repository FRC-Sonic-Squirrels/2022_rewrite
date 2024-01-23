package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.configs.VoltageConfigs;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.SHOOTER_CONSTANTS;

enum Mode {
    STOP,
    FORWARD,
    REVERSE
}

public class Shooter extends SubsystemBase {
    TalonFX flywheelMotor;
    
    VelocityVoltage targetVelocity;

    Mode mode = Mode.STOP;

    public Shooter() {
        // flywheel motor is on bus 2 ?
        this.flywheelMotor = new TalonFX(Constants.SHOOTER_CONSTANTS.FLYWHEEL_MOTOR_ID, Constants.CANBUS2_STR);

        this.targetVelocity = new VelocityVoltage(0);

        // motor configs
        TalonFXConfigurator FXConfigurator = this.flywheelMotor.getConfigurator();

        Slot0Configs config = new Slot0Configs();

        config.kP = 0.2;
        config.kI = 0.0;
        config.kD = 0.0;
        config.kS = 0.1;

        this.flywheelMotor.getConfigurator().apply(config);


        FXConfigurator.apply(new VoltageConfigs().withPeakForwardVoltage(2.0).withPeakReverseVoltage(0.0));

    }

    public Command startCMD() {
        System.out.println("starting");

        this.targetVelocity.Velocity = SHOOTER_CONSTANTS.TARGET_RPS;

        return runOnce(() -> {
            this.flywheelMotor.setControl(this.targetVelocity);
        });
    }

    public Command stopCMD() {
        System.out.println("stopping");

        this.targetVelocity.Velocity = 0.0;

        return runOnce(() -> {
            this.flywheelMotor.setControl(this.targetVelocity);
            this.flywheelMotor.stopMotor();
        });
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // if (this.mode == Mode.STOP && this.targetVelocity.Velocity != 0.0) {
        //     this.targetVelocity.Velocity = 0.0;
        // }

        Logger.recordOutput("Shooter/Flywheel/PIDOut", this.flywheelMotor.getClosedLoopOutput().getValueAsDouble());
        Logger.recordOutput("Shooter/Flywheel/Voltage", this.flywheelMotor.getMotorVoltage().getValueAsDouble());
        Logger.recordOutput("Shooter/Flywheel/RPM", this.flywheelMotor.getVelocity().getValueAsDouble() * 60.0);
        Logger.recordOutput("Shooter/Flywheel/targetRPM", this.targetVelocity.Velocity * 60.0);
    }

    public Command increaseRPM(double delta) {

        this.targetVelocity.Velocity = this.targetVelocity.Velocity + (delta / 60.0);

        return runOnce(() -> {
            this.flywheelMotor.setControl(this.targetVelocity);
        });
    }

    public Command decreaseRPM(double delta) {
        this.targetVelocity.Velocity = this.targetVelocity.Velocity - (delta / 60.0);

        return runOnce(() -> {
            this.flywheelMotor.setControl(this.targetVelocity);
        });
    }
}