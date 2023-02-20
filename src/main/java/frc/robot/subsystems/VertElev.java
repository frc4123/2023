package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.CanIdConstants;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import static frc.robot.Constants.*;

public class VertElev extends SubsystemBase{
    private CANSparkMax vert = new CANSparkMax(CanIdConstants.VERT_ELEV_ID, MotorType.kBrushless);

    /** Creates a new IntakeSubsystem*/
    public VertElev(){
        vert.setOpenLoopRampRate(0.7);
        vert.setIdleMode(IdleMode.kBrake);
        vert.clearFaults();
    }

    public void setVertVelo(double velo){
        //command below needs fixing
        vert.set(velo);
    }
}