package frc.robot.commands.vertical;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Tuning;
import frc.robot.subsystems.VertElev;

public class VertCone extends CommandBase{
    VertElev vertElev;

    public VertCone(VertElev vertElev) {
        this.vertElev = vertElev;
        addRequirements(vertElev);
    }

    @Override
    public void execute() {
        vertElev.setPosition(Tuning.VERT_ELEV_POSITION_CONE);
        // vertElev.setVertVelo(0.4123);
    }

    @Override
    public void end(boolean interrupted) {
        // vertElev.setVertVelo(0);
    }
}