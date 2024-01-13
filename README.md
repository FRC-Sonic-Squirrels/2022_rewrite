# Guide to rewrite 

## First steps. (always ask for help when needed) 

1. First create a new branch
2. Edit this readme file and enter your name (bottom of these instructions) 
3. commit your changes
4. push your changes (to the new branch you just created) 

5. Start working on the subsystems below

This branch belongs to: Landon


## Subsystems to rewrite

you can use the [2022](https://github.com/FRC-Sonic-Squirrels/2022-Robot-Code) code as a reference however a lot of it is outdated and does not follow best practices. 
Please do not copy it verbatim. If you are ever unsure about anything please ask. 

### Intake (general outline)
you can & will have more methods, objects, and variables than this. This is intended as a general outline

#### Objects you will need: 
- Talon_FX
- Solenoid

#### minimum methods to include: 
- setPercentOut(double percent)
- setVoltage(double volts)
- setVelocity(double rpm)
- deploy()
- retract()
- stop()
- getRpm();
- isDeployed();

#### intake commands
- IntakeDeployAndSpin(double rpm)
- IntakeRetractAndStop()
- IntakeDeployNoSpin()

#### Intake button bindings 
- rightTrigger (while being held down) = deploy & spin at 3000 RPM 
- rightBumper (while being held down) = just deploy

