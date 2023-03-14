package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Tuning;
import frc.robot.subsystems.Wrist;

public class WristMid extends CommandBase{
    Wrist wrist;

    public WristMid(Wrist wrist) {
        this.wrist = wrist;
        addRequirements(wrist);
    }

    @Override
    public void execute() {
        // wrist.setWristVelo(-0.4);
        wrist.setPosition(Tuning.WRIST_POSITION_MID);
    }

    @Override
    public void end(boolean interrupted) {
        // wrist.setWristVelo(0);
    }
}