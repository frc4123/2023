package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.CanIdConstants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class Wrist extends SubsystemBase{
    private CANSparkMax wrist = new CANSparkMax(CanIdConstants.WRIST_ID, MotorType.kBrushless);

    /** Creates a new IntakeSubsystem*/
    public Wrist(){
        wrist.setOpenLoopRampRate(0.7);
        wrist.setIdleMode(IdleMode.kBrake);
        wrist.clearFaults();
    }

    public void setWristVelo(double velo){
        //command below needs fixing
        wrist.set(velo);
    }
}