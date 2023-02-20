package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Drivetrain;

public class DockDrive extends CommandBase{
    Drivetrain drivetrain;

    public DockDrive(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void execute(){
        drivetrain.arcadeDrive(0, 0.7);
    }

    @Override
    public void end(boolean interrupted){
        drivetrain.arcadeDrive(0, 0);
    }
}
