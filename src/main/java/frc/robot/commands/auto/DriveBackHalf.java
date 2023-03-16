package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Drivetrain;

public class DriveBackHalf extends CommandBase{
    Drivetrain drivetrain;

    public DriveBackHalf(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void execute(){
        drivetrain.arcadeDrive(-0.425, 0);
    }

    @Override
    public void end(boolean interrupted){
        drivetrain.arcadeDrive(0, 0);
    }
}
