package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Intake;

public class IntakeOut extends CommandBase{
    Intake intake;

    public IntakeOut(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.setIntakeVelo(0.4);
    }

    @Override
    public void end(boolean interrupted) {
        intake.setIntakeVelo(0);
    }
}