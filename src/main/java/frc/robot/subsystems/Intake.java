package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.CanIdConstants;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class Intake extends SubsystemBase{
    private CANSparkMax intakeLeader = new CANSparkMax(CanIdConstants.INTAKE_LEADER_ID, MotorType.kBrushless);

    /** Creates a new IntakeSubsystem*/
    public Intake(){
        intakeLeader.setOpenLoopRampRate(0.7);
        intakeLeader.setIdleMode(IdleMode.kBrake);
    }

    public void setIntakeVelo(double velo){
        //command below needs fixing
        intakeLeader.set(velo);
    }
}