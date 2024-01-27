package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.AutoLog;

public interface ShooterIO {
    /** Contains all of the input data received from hardware. */
    @AutoLog
    public static class ShooterIOInputs {
        public double velocityRPM = 0.0;
        public double targetRPM = 0.0;
        public double voltage = 0.0;
    }

    /** Updates the set of loggable inputs. */
    public default void updateInputs(ShooterIOInputs inputs) {}

    public default void start() {}

    public default void stop() {}

    public default void increaseRPM() {}

    public default void decreaseRPM() {}
}