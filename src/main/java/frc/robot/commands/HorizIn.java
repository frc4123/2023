package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.HorizElev;

public class HorizIn extends CommandBase{
    HorizElev horizElev;

    public HorizIn(HorizElev horizElev) {
        this.horizElev = horizElev;
        addRequirements(horizElev);
    }

    @Override
    public void execute() {
        horizElev.setHorizVelo(-0.237);
    }

    @Override
    public void end(boolean interrupted) {
        horizElev.setHorizVelo(0);
    }
}