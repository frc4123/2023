// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
        public static final double HORIZ_ELEV_PID_P = 0.873;
        public static final double HORIZ_ELEV_PID_I = 0;
        public static final double HORIZ_ELEV_PID_D = 0.015217;
        public static final double HORIZ_ELEV_FF_S = 3.8764;
        public static final double HORIZ_ELEV_FF_A = 0.066433;
        public static final double HORIZ_ELEV_POSITION_IN = 1;
        public static final double HORIZ_ELEV_POSITION_OUT = 18;
        public static final double HORIZ_ELEV_CONSTRAINTS_VELOCITY = 40;
        public static final double HORIZ_ELEV_CONSTRAINTS_ACCELERATION = 150;


        public static final double VERT_ELEV_PID_P = 0.086423;
        public static final double VERT_ELEV_PID_I = 0;
        public static final double VERT_ELEV_PID_D = 0.021931;
        public static final double VERT_ELEV_FF_S = 0.80362;
        public static final double VERT_ELEV_FF_A = 0.025455;
        public static final double VERT_ELEV_POSITION_DOWN = 4; // 0
        public static final double VERT_ELEV_POSITION_UP = 35; //29.21
        public static final double VERT_ELEV_CONSTRAINTS_VELOCITY = 500;
        public static final double VERT_ELEV_CONSTRAINTS_ACCELERATION = 60;

        public static final double WRIST_PID_P = 0.27155;
        public static final double WRIST_PID_I = 0;
        public static final double WRIST_PID_D = 0;
        public static final double WRIST_FF_S = 0.80362;
        public static final double WRIST_FF_A = 0.25455;
        public static final double WRIST_POSITION_IN = 1;
        public static final double WRIST_POSITION_OUT = 64; //50
        public static final double WRIST_CONSTRAINTS_VELOCITY = 500;
        public static final double WRIST_CONSTRAINTS_ACCELERATION = 50;
    }
}