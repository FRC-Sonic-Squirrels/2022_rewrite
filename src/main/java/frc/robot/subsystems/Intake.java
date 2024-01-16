// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */

  final TalonFX m_intake = new TalonFX(18);
  final Solenoid intakeSolenoid = new Solenoid(PneumaticsModuleType.REVPH, 15);
  
  private double intakeRPM = 0.0;
  private boolean isDeployed = false;
  public Intake() {
    

    /*TODO: write the following methods
      setPercentOut(double percent) - completed, yet to be tested
      setVoltage(double volts) - completed, yet to be tested
      setVelocity(double rpm) - completed, yet to be tested
      deploy() - completed, yet to be tested
      retract() - completed, yet to be tested
      stop() - completed, yet to be tested
      getRpm(); - completed, yet to be tested
      isDeployed(); - completed, yet to be tested
      IT DOESNT WORK :(


    */
    
  }

  @Override
  public void periodic() {
    
  }
  public void setPercentOut(double percent){
    //This method sets the percent output of the intake
    m_intake.set(percent);
  }
  
  //sets the voltage of the intake to the provided parameter
   public void setVoltage(double volts){
    m_intake.setVoltage(volts);
   }

   public void setVelocity(double rpm){
    //sets the velocity in the intakke to the rpm passed through the parameter
    intakeRPM = rpm;
    m_intake.set(intakeRPM);
   }

   public void deploy(){
    intakeSolenoid.set(true);
    isDeployed = true;
   }

   public void retract(){
    intakeSolenoid.set(false);
    isDeployed = false;
   }

   public void stop(){
    m_intake.close();
   }

   public double getRPM(){
    return intakeRPM;
   }

   public boolean isDeployed(){
    return isDeployed;
   }

   public void IntakeDeployAndSpin(double rpm){
    deploy();
    setVelocity(rpm);
   }

   public void IntakeRetractAndStop(){
    setVelocity(0.0);
    retract();
    
   }

   public void IntakeDeployNoSpin(){
    deploy();
   }
}
