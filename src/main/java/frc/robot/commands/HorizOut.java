package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.HorizElev;

public class HorizOut extends CommandBase{
    HorizElev horizElev;

    public HorizOut(HorizElev horizElev) {
        this.horizElev = horizElev;
        addRequirements(horizElev);
    }

    @Override
    public void execute() {
        horizElev.setHorizVelo(-0.4);
    }

    @Override
    public void end(boolean interrupted) {
        horizElev.setHorizVelo(0);
    }
}