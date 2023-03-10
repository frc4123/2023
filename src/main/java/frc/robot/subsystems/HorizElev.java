package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.CanIdConstants;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class HorizElev extends SubsystemBase{
    private CANSparkMax vert = new CANSparkMax(CanIdConstants.HORIZ_ELEV_ID, MotorType.kBrushless);

    private final SimpleMotorFeedforward m_feedforward = new SimpleMotorFeedforward(Tuning.HORIZ_ELEV_FF_A , 0, Tuning.HORIZ_ELEV_FF_A);
  
    private final TrapezoidProfile.Constraints m_constraints =
        new TrapezoidProfile.Constraints(Tuning.HORIZ_ELEV_CONSTRAINTS_VELOCITY, Tuning.HORIZ_ELEV_CONSTRAINTS_ACCELERATION);
    private TrapezoidProfile.State m_goal = new TrapezoidProfile.State();
    private TrapezoidProfile.State m_setpoint = new TrapezoidProfile.State();
  
    private static double kDt = 0.02;

    private double setpoint = 0;

    /** Creates a new Horizontal Subsystem*/
    public HorizElev(){
        vert.setOpenLoopRampRate(0.7);
        vert.setIdleMode(IdleMode.kBrake);
        vert.clearFaults();

        vert.getPIDController().setP(Tuning.HORIZ_ELEV_PID_P);
        vert.getPIDController().setI(Tuning.HORIZ_ELEV_PID_I);
        vert.getPIDController().setD(Tuning.HORIZ_ELEV_PID_D);
    
    }

    public void setHorizVelo(double velo){
        System.err.println("Calling setHorizVelo when you should be using setPosition");
        //command below needs fixing
        vert.set(velo);
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
      internalSetPosition(setpoint);
    }

    public void setPosition(double position) {
        setpoint = position;
    }

    private void internalSetPosition(double position) {
        m_goal = new TrapezoidProfile.State(position, 0);
      
  

        SmartDashboard.putNumber("HorizElev Position", vert.getEncoder().getPosition());
        SmartDashboard.putNumber("HorizElev Velocity", vert.getEncoder().getVelocity());
  
        // Create a motion profile with the given maximum velocity and maximum
        // acceleration constraints for the next setpoint, the desired goal, and the
        // current setpoint.
        var profile = new TrapezoidProfile(m_constraints, m_goal, m_setpoint);
    
        // Retrieve the profiled setpoint for the next timestep. This setpoint moves
        // toward the goal while obeying the constraints.
        m_setpoint = profile.calculate(kDt);
    
        // Send setpoint to offboard controller PID
        vert.getPIDController().setReference(
          m_setpoint.position,
          ControlType.kPosition,
          0,
          m_feedforward.calculate(m_setpoint.velocity) / 12.0);
    }

}