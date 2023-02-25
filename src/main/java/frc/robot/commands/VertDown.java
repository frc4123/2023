package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Tuning;
import frc.robot.subsystems.VertElev;

public class VertDown extends CommandBase{
    VertElev vertElev;

    public VertDown(VertElev vertElev) {
        this.vertElev = vertElev;
        addRequirements(vertElev);
    }

    @Override
    public void execute() {
        vertElev.setPosition(Tuning.VERT_ELEV_POSITION_DOWN);
    }

    @Override
    public void end(boolean interrupted) {
        // vertElev.setVertVelo(0);
    }
}