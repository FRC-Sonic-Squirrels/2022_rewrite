package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;


public class IntakeSubsystem extends SubsystemBase {
  // Objects
  private TalonFX m_intake = new TalonFX(18);
  private Solenoid intakeSolenoid = new Solenoid(PneumaticsModuleType.REVPH, 15);
  
  // Variables
  private double intakeRPM = 3000;
  private boolean isDeployed;
  public double velocity = 1000;

  public IntakeSubsystem(double rpm) {
    intakeRPM = rpm;
  }

  @Override
  public void periodic(){

  }
  
  // sets percent of intake roller speed
  public void setPercentOut(double percent){
    if (percent > 1.0){
      percent = 1.0;
    }
    if (percent < -1.0) {
      percent = -1.0;
    }
    m_intake.set(percent);
  }

  // sets voltage
  public void setVoltage(double volts) {
    m_intake.setVoltage(volts);
  }

  public void setVelocity(double rpm){
    velocity = rpm;
  }

  public void deploy(){
    intakeSolenoid.set(true);
    isDeployed = true;
  }

  public void retract(){
    intakeSolenoid.set(false);
    isDeployed = false;
  }

  // stops intake rollers
  public void stop(){
    setPercentOut(0);
  }
    
  public double getRpm(){
    return intakeRPM;
  }

  public boolean isDeployed(){
    return isDeployed;
  }

}