package frc.robot.commands.piston;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Piston;

public class PistonIn extends CommandBase{
    Piston piston;

    public PistonIn (Piston piston) {
        this.piston = piston;
        addRequirements(piston);
    }

    @Override
    public void execute() {
        piston.togglePiston(1); //in (reverse) 1
    }
}
