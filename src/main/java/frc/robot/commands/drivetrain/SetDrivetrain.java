package frc.robot.commands.drivetrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class SetDrivetrain extends CommandBase{
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Drivetrain m_drivetrain;

    private final DoubleSupplier m_speed, m_direction;

    /**
   * Creates a new SetDrivetrain that adds a deadband to the controller.
   *
   * @param drivetrain The subsystem used by this command.
   * @param speedInput The x value of the controller.
   * @param directionInput The y value of the controller.   
   */
    public SetDrivetrain(
        Drivetrain drivetrain,
        DoubleSupplier speedInput,
        DoubleSupplier directionInput){
            m_drivetrain = drivetrain; 
            m_speed = speedInput;
            m_direction = directionInput;
            addRequirements(drivetrain);
        }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double speed = 
            MathUtil.applyDeadband(Math.abs(m_speed.getAsDouble()), 0.1)
                * Math.signum(m_speed.getAsDouble());
        double direction =             
            MathUtil.applyDeadband(Math.abs(m_direction.getAsDouble()), 0.1)
                * Math.signum(m_direction.getAsDouble());
        m_drivetrain.arcadeDrive(speed, direction);
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
    return false;
  }
}
