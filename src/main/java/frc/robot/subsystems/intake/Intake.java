package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private final IntakeIO io;
    private final IntakeIOInputsAutoLogged inputs = new IntakeIOInputsAutoLogged();

    public Intake(IntakeIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        io.updateInputs(inputs);
        Logger.processInputs("Intake", inputs);
    }

    public Command setPercentOut(double percent) {
        return runOnce(() -> {
            this.io.setPercentOut(percent);
        });
    }

    public Command setVoltageCMD(double output) {
        return runOnce(() -> {
            this.io.setVoltage(output);
        });
    }

    public Command setVelocityCMD(double vel) {
        return runOnce(() -> {
            this.io.setVelocity(vel);
        });
    }

    public Command setVelocityCMD(double vel, double accel) {
        return runOnce(() -> {
            this.io.setVelocity(vel, accel);
        });
    }

    public Command deploy() {
        return runOnce(() -> {
            this.io.deploy();
        });
    }

    public Command retract() {
        System.out.println("retracting");
        
        return runOnce(() -> {
            this.io.retract();
        });
    }

    public Command stop() {
        return runOnce(() -> {
            this.io.retract();
            this.io.setVelocity(0);
        });
    }

    public boolean isDeployed() {
        return this.inputs.solenoidExtended;
    }
}
