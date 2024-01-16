// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeDeployAndSpin extends Command {
  IntakeSubsystem m_intake;
  public double rpm;

  /** Creates a new IntakeDeployAndSpin. */
  public IntakeDeployAndSpin(double rpm, IntakeSubsystem intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.rpm = rpm;
    m_intake = intake;
    addRequirements(m_intake);
  }
  


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.setVelocity(rpm);
    m_intake.deploy();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("yo!"); // testing!
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.retract();
    m_intake.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
