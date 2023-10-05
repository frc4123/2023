package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Intake;

public class IntakeCubeIn extends CommandBase{
    Intake intake;

    public IntakeCubeIn(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.setIntakeVelo(-0.7); // -0.4 with neo 550
    }

    @Override
    public void end(boolean interrupted) {
        intake.setIntakeVelo(0);
    }
}