package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.CanIdConstants;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import static frc.robot.Constants.*;

public class HorizElev extends SubsystemBase{
    private CANSparkMax vert = new CANSparkMax(CanIdConstants.HORIZ_ELEV_ID, MotorType.kBrushless);

    /** Creates a new IntakeSubsystem*/
    public HorizElev(){
        vert.setOpenLoopRampRate(0.7);
        vert.setIdleMode(IdleMode.kBrake);
        vert.clearFaults();
    }

    public void setHorizVelo(double velo){
        //command below needs fixing
        vert.set(velo);
    }
}