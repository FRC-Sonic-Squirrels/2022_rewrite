package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeDeployAndSpin extends Command {
    private final Intake intake;

    public IntakeDeployAndSpin(Intake intake) {
        this.intake = intake;
        
        addRequirements(intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        this.intake.deploy().schedule();
        
        // TODO: use constant
        this.intake.setVelocityCMD(Constants.INTAKE_CONSTANTS.INTAKE_MOTOR_RPM).schedule();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
