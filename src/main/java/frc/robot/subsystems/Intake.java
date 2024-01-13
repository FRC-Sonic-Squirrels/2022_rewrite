package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    TalonFX talonFX;

    Solenoid solenoid;

    VoltageOut voltOut;
    VelocityVoltage velOut;
    DutyCycleOut cycleOut;

    int targetRPM = 0;

    public Intake() {
        //todo: config motor
        this.solenoid = new Solenoid(PneumaticsModuleType.REVPH, Constants.INTAKE_CONSTANTS.PNEUMATIC_CYL_CHANNEL);

        this.talonFX = new TalonFX(Constants.INTAKE_CONSTANTS.INTAKE_MOTOR_ID, Constants.CANBUS_STR);

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
        System.out.println("speen");

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
        System.out.println("retracting");
        
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
        // return this.talonFX.getRotorVelocity().getValueAsDouble();
        return 0.0;
    }

    public boolean isDeployed() {
        // TODO: use sensor instead of solenoid
        return this.solenoid.get();
    }
}
