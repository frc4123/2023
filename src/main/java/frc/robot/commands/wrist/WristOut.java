package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Tuning;
import frc.robot.subsystems.Wrist;

public class WristOut extends CommandBase{
    Wrist wrist;

    public WristOut(Wrist wrist) {
        this.wrist = wrist;
        addRequirements(wrist);
    }

    @Override
    public void execute() {
        // wrist.setWristVelo(0.6);
        wrist.setPosition(Tuning.WRIST_POSITION_OUT);
    }

    @Override
    public void end(boolean interrupted) {
        // wrist.setWristVelo(0);
    }
}