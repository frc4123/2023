package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Wrist;

public class WristOut extends CommandBase{
    Wrist wrist;

    public WristOut(Wrist wrist) {
        this.wrist = wrist;
        addRequirements(wrist);
    }

    @Override
    public void execute() {
        wrist.setWristVelo(0.6);
    }

    @Override
    public void end(boolean interrupted) {
        wrist.setWristVelo(0);
    }
}