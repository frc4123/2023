package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Drivetrain;

public class DriveForwardHalf extends CommandBase{
    Drivetrain drivetrain;

    public DriveForwardHalf(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void execute(){
        drivetrain.arcadeDrive(0.55, 0);
    }

    @Override
    public void end(boolean interrupted){
        drivetrain.arcadeDrive(0, 0);
    }
}
