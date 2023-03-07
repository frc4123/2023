// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

public final class Constants {
    /** USB ports on the computer */
    public static final class UsbConstants {

        public static final int DRIVER_CONTROLLER_PORT = 0;
        public static final int AUXDRIVER_CONTROLLER_PORT = 1;
        public static final int USB_PORT_2 = 2;
        public static final int USB_PORT_3 = 3;
    }

    /** Xbox buttons to their corresponding integer value. */
    public static final class XboxConstants {

        // Button mappings
        public static int D_PAD = 0;
        public static int A_BUTTON = 1;
        public static int B_BUTTON = 2;
        public static int X_BUTTON = 3;
        public static int Y_BUTTON = 4;
        public static int LB_BUTTON = 5;
        public static int RB_BUTTON = 6;
        public static int BACK_BUTTON = 7;
        public static int START_BUTTON = 8;
        public static int LEFT_STICK = 9;
        public static int RIGHT_STICK = 10;

        // Axis control mappings
        // Notes:
        // - Left and right trigger use axis 3
        // - Left trigger range: 0 to 1
        // - Right trigger range: 0 to -1).
        public static int LEFT_AXIS_X = 6;
        public static int LEFT_AXIS_Y = 1;
        public static int LEFT_TRIGGER_AXIS = 2;
        public static int RIGHT_TRIGGER_AXIS = 3;
        public static int RIGHT_AXIS_X = 4;
        public static int RIGHT_AXIS_Y = 5;

        // Direction pad lookup angles
        public static int POV_UP = 0;
        public static int POV_RIGHT = 90;
        public static int POV_DOWN = 180;
        public static int POV_LEFT = 270;
    }

    /**
     * Mapping between physical CAN devices and their corresponding ID in the CAN
     * chain
     */
    public static final class CanIdConstants {

        // Note: RIO is always 0
        // Note: PDH is always 1
        public static final int RIGHT_LEADER_ID = 3;
        public static final int RIGHT_FOLLOWER_ID = 4;
        public static final int LEFT_LEADER_ID = 5;
        public static final int LEFT_FOLLOWER_ID = 6;
        public static final int VERT_ELEV_ID = 7;
        public static final int HORIZ_ELEV_ID = 8;
        public static final int INTAKE_LEADER_ID = 9;
        public static final int WRIST_ID = 10;
    }

    public static final class Tuning {

        // Horizontal Elevator Constants
        public static final double HORIZ_ELEV_PID_P = 0.873;
        public static final double HORIZ_ELEV_PID_I = 0;
        public static final double HORIZ_ELEV_PID_D = 0.015217;
        public static final double HORIZ_ELEV_FF_S = 3.8764;
        public static final double HORIZ_ELEV_FF_A = 0.066433;
        public static final double HORIZ_ELEV_POSITION_IN = 1;
        public static final double HORIZ_ELEV_POSITION_OUT = 18;
        public static final double HORIZ_ELEV_CONSTRAINTS_VELOCITY = 40;
        public static final double HORIZ_ELEV_CONSTRAINTS_ACCELERATION = 150;


        
        // Verticle Elevator Constants
        public static final double VERT_ELEV_PID_P = 0.04123;
        public static final double VERT_ELEV_PID_I = 0;
        public static final double VERT_ELEV_PID_D = 0;
        public static final double VERT_ELEV_FF_S = 0;
        public static final double VERT_ELEV_FF_V = 0;
        public static final double VERT_ELEV_FF_A = 0;
        public static final double VERT_ELEV_FF_G = 0;
        public static final double VERT_ELEV_POSITION_DOWN = 0;
        public static final double VERT_ELEV_POSITION_CONE = 35;
        public static final double VERT_ELEV_POSITION_UP = 115;
        public static final double VERT_ELEV_CONSTRAINTS_VELOCITY = 150;
        public static final double VERT_ELEV_CONSTRAINTS_ACCELERATION = 240;

        // Wrist Constants
        public static final double WRIST_PID_P = 0.27155;
        public static final double WRIST_PID_I = 0;
        public static final double WRIST_PID_D = 0;
        public static final double WRIST_FF_S = 0.80362;
        public static final double WRIST_FF_A = 0.25455;
        public static final double WRIST_POSITION_IN = 1;
        public static final double WRIST_POSITION_OUT = 64; //50
        public static final double WRIST_CONSTRAINTS_VELOCITY = 500;
        public static final double WRIST_CONSTRAINTS_ACCELERATION = 50;

        // Drivetrain Constants
        //Position PID
        public static final double Drivetrain_Position_Kp = 25.049;
        public static final double Drivetrain_Position_Ki = 0;
        public static final double Drivetrain_Position_Kd = 0.9254;
        //Velocity PID
        public static final double Drivetrain_Velocity_Kp = 0.0014708;
        public static final double Drivetrain_Velocity_Ki = 0;
        public static final double Drivetrain_Velocity_Kd = 0;
        public static final double Drivetrain_Position_Max_Position_Error = 0.039898;
        public static final double Drivetrain_Position_Max_Velocity_Error = 4.2947;
        public static final double Drivetrain_Position_Max_Control_Effort = 7;
        public static final double kTrackwidthMeters = 0.69; // this is not tuned for our robot
        public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(Tuning.kTrackwidthMeters);
        public static final double kEncoderDistancePerPulse = 0.665; // we're not sure about this one
        public static final double ksVolts = 0.10703;
        public static final double kvVoltSecondsPerMeter = 0.69231;
        public static final double kaVoltSecondsSquaredPerMeter = 0.13482;
    }
    public static final class AutoConstants{
        // Drivetrain
        public static final double kMaxSpeedMetersPerSecond = 3; // this is not tuned for our robot
        public static final double kMaxAccelerationMetersPerSecondSquared = 1; // this is not tuned for our robot
        public static final double kRamseteB = 2; // this is not tuned for our robot
        public static final double kRamseteZeta = 0.7; // this is not tuned for our robot
    }
}