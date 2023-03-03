package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.CanIdConstants;
import edu.wpi.first.math.controller.ArmFeedforward;
// import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class Wrist extends SubsystemBase{
    private CANSparkMax wrist = new CANSparkMax(CanIdConstants.WRIST_ID, MotorType.kBrushless);
    private SparkMaxLimitSwitch m_forwardLimit = wrist.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);

    private final ArmFeedforward m_feedforward = new ArmFeedforward(Tuning.WRIST_FF_S, 0, Tuning.WRIST_FF_A);
    /** Creates a new IntakeSubsystem*/
    private final TrapezoidProfile.Constraints m_constraints =
    new TrapezoidProfile.Constraints(Tuning.WRIST_CONSTRAINTS_VELOCITY, Tuning.WRIST_CONSTRAINTS_ACCELERATION);
    private TrapezoidProfile.State m_goal = new TrapezoidProfile.State();
    private TrapezoidProfile.State m_setpoint = new TrapezoidProfile.State();

    private static double kDt = 0.02;
    private double setpoint = 0;

    public Wrist(){
        // wrist.setOpenLoopRampRate(0.7);
        wrist.setIdleMode(IdleMode.kBrake);
        wrist.clearFaults();

        wrist.getPIDController().setP(Tuning.WRIST_PID_P);
        wrist.getPIDController().setI(Tuning.WRIST_PID_I);
        wrist.getPIDController().setD(Tuning.WRIST_PID_D);
    }

    public void setWristVelo(double velo){
        System.err.println("Calling setVertVelo when you should be using setPosition");
        //command below needs fixing
        // wrist.set(velo);
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
      internalSetPosition(setpoint);
      SmartDashboard.putBoolean("Is wrist down?", !(m_forwardLimit.isPressed()));
    }

    public void setPosition(double position) {
        setpoint = position;
    }

    private void internalSetPosition(double position) {
        m_goal = new TrapezoidProfile.State(position, 0);
      
  

        SmartDashboard.putNumber("Wrist Position", wrist.getEncoder().getPosition());
        SmartDashboard.putNumber("Wrist Velocity", wrist.getEncoder().getVelocity());
  
        // Create a motion profile with the given maximum velocity and maximum
        // acceleration constraints for the next setpoint, the desired goal, and the
        // current setpoint.
        var profile = new TrapezoidProfile(m_constraints, m_goal, m_setpoint);
    
        // Retrieve the profiled setpoint for the next timestep. This setpoint moves
        // toward the goal while obeying the constraints.
        m_setpoint = profile.calculate(kDt);
    
        // Send setpoint to offboard controller PID
        wrist.getPIDController().setReference(
          m_setpoint.position,
          ControlType.kPosition,
          0,
          m_feedforward.calculate(m_setpoint.position, m_setpoint.velocity) / 12.0);
    }
}