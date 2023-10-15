package frc.robot.commands.piston;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Piston;

public class PistonOut extends CommandBase{
    Piston piston;

    public PistonOut (Piston piston) {
        this.piston = piston;
        addRequirements(piston);
    }

    @Override
    public void execute() {
        piston.togglePiston(0); //out (forward) 0
    }
}