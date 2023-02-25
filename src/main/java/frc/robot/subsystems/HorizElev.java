package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.ElevatorFeedforward;

import frc.robot.Constants.CanIdConstants;

public class HorizElev extends SubsystemBase{
    private CANSparkMax horiz = new CANSparkMax(CanIdConstants.HORIZ_ELEV_ID, MotorType.kBrushless);
    private ElevatorFeedforward feedforward = new ElevatorFeedforward(0.14213, -0.01, 0.40918, 0.0072925);

    /** Creates a new Horizontal Subsystem*/
    public HorizElev(){
        horiz.setOpenLoopRampRate(0.7);
        horiz.setIdleMode(IdleMode.kBrake);
        horiz.clearFaults();
    }

    public void setHorizVelo(double velo){
      horiz.set(feedforward.calculate(velo));
    }
}