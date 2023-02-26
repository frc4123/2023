package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Tuning;
import frc.robot.subsystems.Wrist;

public class WristIn extends CommandBase{
    Wrist wrist;

    public WristIn(Wrist wrist) {
        this.wrist = wrist;
        addRequirements(wrist);
    }

    @Override
    public void execute() {
        // wrist.setWristVelo(-0.4);
        wrist.setPosition(Tuning.WRIST_POSITION_IN);
    }

    @Override
    public void end(boolean interrupted) {
        // wrist.setWristVelo(0);
    }
}