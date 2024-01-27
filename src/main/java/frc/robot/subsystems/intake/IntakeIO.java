package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.AutoLog;

public interface IntakeIO {
    /** Contains all of the input data received from hardware. */
    @AutoLog
    public static class IntakeIOInputs{
        public double velocityRPM = 0.0;
        public double voltage = 0.0;
        public boolean solenoidExtended = false;
    }

    /** Updates the set of loggable inputs. */
    public default void updateInputs(IntakeIOInputs inputs) {}

    public default void setPercentOut(double percent) {}

    public default void setVoltage(double output) {}

    public default void setVelocity(double vel) {}

    public default void setVelocity(double vel, double accel) {}

    public default void deploy() {}

    public default void retract() {}

    public default void stop() {}
}