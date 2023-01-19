package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.CanIdConstants;

public class Drivetrain {
    private CANSparkMax rightLeader = new CANSparkMax(CanIdConstants.RIGHT_LEADER_ID, MotorType.kBrushless);
}
