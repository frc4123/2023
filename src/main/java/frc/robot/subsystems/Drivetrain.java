package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CanIdConstants;
import frc.robot.Constants.Tuning;

public class Drivetrain extends SubsystemBase{
    private CANSparkMax rightLeader = new CANSparkMax(CanIdConstants.RIGHT_LEADER_ID, MotorType.kBrushless);
    private CANSparkMax rightFollower = new CANSparkMax(CanIdConstants.RIGHT_FOLLOWER_ID, MotorType.kBrushless);
    private CANSparkMax leftLeader = new CANSparkMax(CanIdConstants.LEFT_LEADER_ID, MotorType.kBrushless);
    private CANSparkMax leftFollower = new CANSparkMax(CanIdConstants.LEFT_FOLLOWER_ID, MotorType.kBrushless);

    
    private final Gyro m_gyro = new ADXRS450_Gyro();
    private final DifferentialDriveOdometry m_odometry;



    private final DifferentialDrive differentialDrive = new DifferentialDrive(leftLeader, rightLeader);

    public Drivetrain() {
        rightLeader.clearFaults();
        rightFollower.clearFaults();
        leftLeader.clearFaults();
        leftFollower.clearFaults();

        rightLeader.setInverted(true);
        rightFollower.setInverted(true);

        rightLeader.setOpenLoopRampRate(0.5);
        rightFollower.setOpenLoopRampRate(0.5);
        leftLeader.setOpenLoopRampRate(0.5);
        leftFollower.setOpenLoopRampRate(0.5);

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

        rightLeader.getPIDController().setP(Tuning.Drivetrain_Position_Kp);
        rightLeader.getPIDController().setI(Tuning.Drivetrain_Position_Ki);
        rightLeader.getPIDController().setD(Tuning.Drivetrain_Position_Kd);

        leftLeader.getPIDController().setP(Tuning.Drivetrain_Position_Kp);
        leftLeader.getPIDController().setI(Tuning.Drivetrain_Position_Ki);
        leftLeader.getPIDController().setD(Tuning.Drivetrain_Position_Kd);


        rightLeader.getEncoder().setPositionConversionFactor(Tuning.kEncoderDistancePerPulse);
        leftLeader.getEncoder().setPositionConversionFactor(Tuning.kEncoderDistancePerPulse);

        rightLeader.burnFlash();
        rightFollower.burnFlash();
        leftLeader.burnFlash();
        leftFollower.burnFlash();
        
        m_odometry = new DifferentialDriveOdometry(m_gyro.getRotation2d(), leftLeader.getEncoder().getPosition(), rightLeader.getEncoder().getPosition());
        differentialDrive.feed();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Left Output", leftLeader.getOutputCurrent());
        SmartDashboard.putNumber("Right Output", rightLeader.getOutputCurrent());
        m_odometry.update(
            m_gyro.getRotation2d(), 
            leftLeader.getEncoder().getPosition(), 
            rightLeader.getEncoder().getPosition()
        );
        SmartDashboard.putNumber("Odometry X", m_odometry.getPoseMeters().getX());
        SmartDashboard.putNumber("Odometry Y", m_odometry.getPoseMeters().getY());
        SmartDashboard.putNumber("Odometry Heading", m_odometry.getPoseMeters().getRotation().getDegrees());
        differentialDrive.feed();
    }


  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  /**
   * Returns the current wheel speeds of the robot.
   *
   * @return The current wheel speeds.
   */
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(leftLeader.getEncoder().getVelocity(), rightLeader.getEncoder().getVelocity());
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(
        m_gyro.getRotation2d(), leftLeader.getEncoder().getPosition(), rightLeader.getEncoder().getPosition(),
        pose
    );
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    differentialDrive.arcadeDrive(fwd, rot);
  }

  /**
   * Controls the left and right sides of the drive directly with voltages.
   *
   * @param leftVolts the commanded left output
   * @param rightVolts the commanded right output
   */
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftLeader.setVoltage(leftVolts);
    rightLeader.setVoltage(rightVolts);
    differentialDrive.feed();
  }

  /** Resets the drive encoders to currently read a position of 0. */
  public void resetEncoders() {
    leftLeader.getEncoder().setPosition(0);
    rightLeader.getEncoder().setPosition(0);
  }

  /**
   * Gets the average distance of the two encoders.
   *
   * @return the average of the two encoder readings
   */
  public double getAverageEncoderDistance() {
    return (leftLeader.getEncoder().getPosition() + rightLeader.getEncoder().getPosition()) / 2.0;
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public RelativeEncoder getLeftEncoder() {
    return leftLeader.getEncoder();
  }

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  public RelativeEncoder getRightEncoder() {
    return rightLeader.getEncoder();
  }

  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    differentialDrive.setMaxOutput(maxOutput);
  }

  /** Zeroes the heading of the robot. */
  public void zeroHeading() {
    m_gyro.reset();
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from -180 to 180
   */
  public double getHeading() {
    return m_gyro.getRotation2d().getDegrees();
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return -m_gyro.getRate();
  }
}

