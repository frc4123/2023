package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.HorizElev;

import static frc.robot.Constants.*;

public class HorizIn extends CommandBase{
    HorizElev horizElev;

    public HorizIn(HorizElev horizElev) {
        this.horizElev = horizElev;
        addRequirements(horizElev);
    }

    @Override
    public void execute() {
        horizElev.setPosition(Tuning.HORIZ_ELEV_POSITION_IN);

        
    }

    @Override
    public void end(boolean interrupted) {
        // TODO: Come back to this
        // horizElev.setHorizVelo(0);
    }
}