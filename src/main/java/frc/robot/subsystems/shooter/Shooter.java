package frc.robot.subsystems.shooter;

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
    private final ShooterIO io;
    private final ShooterIOInputsAutoLogged inputs = new ShooterIOInputsAutoLogged();

    public Shooter(ShooterIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("Intake", inputs);
    }

    public Command startCMD() {
        System.out.println("starting");

        return runOnce(() -> {
            io.start();
        });
    }

    public Command stopCMD() {
        System.out.println("stopping");

        return runOnce(() -> {
            io.stop();
        });
    }


    public Command increaseRPM(double delta) {
        return runOnce(() -> {
            io.increaseRPM();
        });
    }

    public Command decreaseRPM(double delta) {
        return runOnce((() -> {
            io.decreaseRPM();
        }));
    }
}