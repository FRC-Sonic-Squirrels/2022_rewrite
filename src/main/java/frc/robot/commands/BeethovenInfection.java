package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Conductor;

public class BeethovenInfection extends Command {
    // CONDUCTOR CONDUCTOR WE HAVE A PROBLEM
    Conductor conductor;
    
    public BeethovenInfection(Conductor conductor) {
        this.conductor = conductor;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        this.conductor.loadMusic();
        this.conductor.play();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {}

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        this.conductor.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}