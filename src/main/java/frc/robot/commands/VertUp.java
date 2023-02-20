package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.VertElev;

public class VertUp extends CommandBase{
    VertElev vertElev;

    public VertUp(VertElev vertElev) {
        this.vertElev = vertElev;
        addRequirements(vertElev);
    }

    @Override
    public void execute() {
        vertElev.setVertVelo(0.4);
    }

    @Override
    public void end(boolean interrupted) {
        vertElev.setVertVelo(0);
    }
}