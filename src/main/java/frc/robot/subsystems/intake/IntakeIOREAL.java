package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.AutoLogOutput;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants;

public class IntakeIOREAL implements IntakeIO {
    TalonFX talonFX;

    Solenoid solenoid;

    VoltageOut voltOut;
    VelocityVoltage velOut;
    DutyCycleOut cycleOut;

    int targetRPM = 0;

    public IntakeIOREAL() {
        this.solenoid = new Solenoid(PneumaticsModuleType.REVPH, Constants.INTAKE_CONSTANTS.PNEUMATIC_CYL_CHANNEL);

        this.talonFX = new TalonFX(Constants.INTAKE_CONSTANTS.INTAKE_MOTOR_ID, Constants.CANBUS_STR);

        this.voltOut = new VoltageOut(0);
        this.velOut = new VelocityVoltage(0);
        this.cycleOut = new DutyCycleOut(0);


        // motor configs
        Slot0Configs config = new Slot0Configs();

        config.kP = 0.2;
        config.kA = 0.05;

        this.talonFX.getConfigurator().apply(config);
    }

    @Override
    public void updateInputs(IntakeIOInputs inputs) {
        inputs.solenoidExtended = this.solenoid.get();
        inputs.velocityRPM = this.getRPM();
        inputs.voltage = this.talonFX.getMotorVoltage().getValueAsDouble();
    }

    public void setPercentOut(double percent) {
        this.talonFX.setControl(this.cycleOut.withOutput(percent));
    }

    public void setVoltage(double output) {
        this.talonFX.setControl(this.voltOut.withOutput(output));
    }

    public void setVelocity(double vel) {
        this.talonFX.setControl(this.velOut.withVelocity(vel));
    }

    public void setVelocity(double vel, double accel) {
        this.talonFX.setControl(this.velOut.withVelocity(vel).withAcceleration(accel));
    }

    public void deploy() {
        this.solenoid.set(true);
    }

    public void retract() {
        this.solenoid.set(false);
    }

    public void stop() {
        this.solenoid.set(false);
        setVelocity(0);
    }

    public double getRPM() {
        return this.talonFX.getRotorVelocity().getValueAsDouble() * 60.0;
    }

    public boolean isDeployed() {
        // TODO: use sensor instead of solenoid
        return this.solenoid.get();
    }
}