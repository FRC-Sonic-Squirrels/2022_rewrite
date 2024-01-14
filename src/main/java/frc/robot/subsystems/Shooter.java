package frc.robot.subsystems;

import org.littletonrobotics.junction.AutoLogOutput;
import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.configs.VoltageConfigs;
import com.ctre.phoenix6.controls.VelocityDutyCycle;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    TalonFX flywheelMotor;
    
    VelocityVoltage tVelocity;

    public Shooter() {
        // flywheel motor is on bus 2 ?
        this.flywheelMotor = new TalonFX(Constants.SHOOTER_CONSTANTS.FLYWHEEL_MOTOR_ID, Constants.CANBUS2_STR);

        this.tVelocity = new VelocityVoltage(0);

        // motor configs
        TalonFXConfigurator FXConfigurator = this.flywheelMotor.getConfigurator();

        Slot0Configs config = new Slot0Configs();

        config.kP = 0.1;
        config.kI = 0.0;
        config.kD = 0.0;
        config.kS = 0.0;

        this.flywheelMotor.getConfigurator().apply(config);


        FXConfigurator.apply(new VoltageConfigs().withPeakForwardVoltage(4.0).withPeakReverseVoltage(0.0));

    }

    public Command startCMD() {
        System.out.println("starting");
        return runOnce(() -> {
            this.flywheelMotor.setControl(
                tVelocity
                    .withVelocity(Constants.SHOOTER_CONSTANTS.TARGET_RPS)
                );
        });
    }

    public Command stopCMD() {
        System.out.println("stopping");
        return runOnce(() -> {
            this.flywheelMotor.setControl(
                tVelocity.withVelocity(0)
            );
        });
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

        Logger.recordOutput("Shooter/Flywheel/Setpoint", this.flywheelMotor.getClosedLoopOutput().getValueAsDouble());
        Logger.recordOutput("Shooter/Flywheel/Voltage", this.flywheelMotor.getMotorVoltage().getValueAsDouble());
        Logger.recordOutput("Shooter/Flywheel/kP", this.flywheelMotor.getClosedLoopProportionalOutput().getValueAsDouble());

    }

    @AutoLogOutput(key = "Shooter/Flywheel/RPM")
    public double getRPM() {
        return this.flywheelMotor.getVelocity().getValueAsDouble() * 60.0;
    }
  
}