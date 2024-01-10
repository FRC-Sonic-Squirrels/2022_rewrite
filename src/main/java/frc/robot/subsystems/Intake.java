package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase{
    TalonFX talonFX;
    Solenoid solenoid;

    VoltageOut voltOut;
    VelocityVoltage velOut;
    DutyCycleOut cycleOut;

    public Intake() {
        //todo: config constants
        this.solenoid = new Solenoid(null, 0);
        this.talonFX = new TalonFX(0, null);

        this.voltOut = new VoltageOut(0);
        this.velOut = new VelocityVoltage(0);
        this.cycleOut = new DutyCycleOut(0);
    }

    public Command setPercentOut(double percent) {
        return runOnce(() -> {
            this.talonFX.setControl(this.cycleOut.withOutput(percent));
        });
    }

    public Command setVoltageCMD(double output) {
        return runOnce(() -> {
            this.talonFX.setControl(this.voltOut.withOutput(output));
        });
    }

    public Command setVelocityCMD(double vel) {
        return runOnce(() -> {
            this.talonFX.setControl(this.velOut.withVelocity(vel));
        });
    }

    public Command setVelocityCMD(double vel, double accel) {
        return runOnce(() -> {
            this.talonFX.setControl(this.velOut.withVelocity(vel).withAcceleration(accel));
        });
    }

    public Command deploy() {
        return runOnce(() -> {
            this.solenoid.set(true);
        });
    }

    public Command retract() {
        return runOnce(() -> {
            this.solenoid.set(false);
        });
    }

    public Command stop() {
        return runOnce(() -> {
            this.solenoid.set(false);
            setVelocityCMD(0).schedule();
        });
    }

    public double getRPM() {
        return this.talonFX.getRotorVelocity().getValueAsDouble();
    }

    public boolean isDeployed() {
        // TODO: use sensor instead of solenoid
        return this.solenoid.get();
    }
}
