package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.CanIdConstants;

public class Drivetrain {
    private CANSparkMax rightLeader = new CANSparkMax(CanIdConstants.RIGHT_LEADER_ID, MotorType.kBrushless);
    private CANSparkMax rightFollower = new CANSparkMax(CanIdConstants.RIGHT_FOLLOWER_ID, MotorType.kBrushless);
    private CANSparkMax leftLeader = new CANSparkMax(CanIdConstants.LEFT_LEADER_ID, MotorType.kBrushless);
    private CANSparkMax leftFollower = new CANSparkMax(CanIdConstants.LEFT_FOLLOWER_ID, MotorType.kBrushless);
}
