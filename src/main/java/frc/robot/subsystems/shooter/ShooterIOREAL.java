package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.configs.VoltageConfigs;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.Constants;
import frc.robot.Constants.SHOOTER_CONSTANTS;

public class ShooterIOREAL implements ShooterIO {
    TalonFX flywheelMotor;
    
    VelocityVoltage targetVelocity;

    Mode mode = Mode.STOP;

    public ShooterIOREAL() {
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
    @Override
    public void updateInputs(ShooterIOInputs inputs) {
        inputs.targetRPM = this.targetVelocity.Velocity * 60.0;
        inputs.velocityRPM = this.flywheelMotor.getVelocity().getValueAsDouble() * 60.0;
        inputs.voltage = this.flywheelMotor.getMotorVoltage().getValueAsDouble();
    }

    public void start() {
        this.targetVelocity.Velocity = SHOOTER_CONSTANTS.TARGET_RPS;

        this.flywheelMotor.setControl(this.targetVelocity);
    }

    public void stop() {
        this.targetVelocity.Velocity = 0.0;

        this.flywheelMotor.setControl(this.targetVelocity);
        this.flywheelMotor.stopMotor();
    }

    public void increaseRPM(double delta) {
        this.targetVelocity.Velocity = this.targetVelocity.Velocity + (delta / 60.0);

        this.flywheelMotor.setControl(this.targetVelocity);
    }

    public void decreaseRPM(double delta) {
        this.targetVelocity.Velocity = this.targetVelocity.Velocity - (delta / 60.0);

        this.flywheelMotor.setControl(this.targetVelocity);
    }
}
