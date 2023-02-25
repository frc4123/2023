package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.ElevatorFeedforward;

import frc.robot.Constants.CanIdConstants;

public class VertElev extends SubsystemBase{
    private CANSparkMax vert = new CANSparkMax(CanIdConstants.VERT_ELEV_ID, MotorType.kBrushless);
    private ElevatorFeedforward feedforward = new ElevatorFeedforward(0.01175, -0.01, 3.5, 0.049848);

    /** Creates a new Vert Subsystem*/
    public VertElev(){
        vert.setOpenLoopRampRate(0.7);
        vert.setIdleMode(IdleMode.kBrake);
        vert.clearFaults();
    }

    public void setVertVelo(double velo){
        vert.set(feedforward.calculate(velo));
    }
}