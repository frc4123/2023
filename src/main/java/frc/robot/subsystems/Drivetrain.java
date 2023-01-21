package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CanIdConstants;

public class Drivetrain extends SubsystemBase{
    private CANSparkMax rightLeader = new CANSparkMax(CanIdConstants.RIGHT_LEADER_ID, MotorType.kBrushless);
    private CANSparkMax rightFollower = new CANSparkMax(CanIdConstants.RIGHT_FOLLOWER_ID, MotorType.kBrushless);
    private CANSparkMax leftLeader = new CANSparkMax(CanIdConstants.LEFT_LEADER_ID, MotorType.kBrushless);
    private CANSparkMax leftFollower = new CANSparkMax(CanIdConstants.LEFT_FOLLOWER_ID, MotorType.kBrushless);

    private final DifferentialDrive differentialDrive = new DifferentialDrive(leftLeader, rightLeader);

    public Drivetrain() {
        rightLeader.clearFaults();
        rightFollower.clearFaults();
        leftLeader.clearFaults();
        leftFollower.clearFaults();

        rightLeader.setSmartCurrentLimit(40);
        rightFollower.setSmartCurrentLimit(40);
        leftLeader.setSmartCurrentLimit(40);
        leftFollower.setSmartCurrentLimit(40);

        rightLeader.setIdleMode(IdleMode.kBrake);
        rightFollower.setIdleMode(IdleMode.kBrake);
        leftLeader.setIdleMode(IdleMode.kBrake);
        leftFollower.setIdleMode(IdleMode.kBrake);

        leftFollower.follow(leftLeader);
        rightFollower.follow(rightLeader);

        differentialDrive.feed();
    }
        //left leader is speed, right leader is direction
    public void arcadeDrive(double speed, double direction){
        differentialDrive.arcadeDrive(speed, direction);
      }
    public void execute() {
        SmartDashboard.putNumber("Left Volt", leftLeader.getBusVoltage());
        SmartDashboard.putNumber("Right Volt", rightLeader.getBusVoltage());
    }
}
