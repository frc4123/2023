package frc.robot.commands.horizontal;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.HorizElev;

import static frc.robot.Constants.*;

public class HorizOut extends CommandBase{
    HorizElev horizElev;

    public HorizOut(HorizElev horizElev) {
        this.horizElev = horizElev;
        addRequirements(horizElev);
    }

    @Override
    public void execute() {
        horizElev.setPosition(Tuning.HORIZ_ELEV_POSITION_OUT);
    }

    @Override
    public void end(boolean interrupted) {
        // TODO: Come back to this
        // horizElev.setHorizVelo(0);
    }
}