package frc.robot;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.Constants.UsbConstants;

import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
    private final Drivetrain drivetrain = new Drivetrain();

    private final XboxController driverController = new XboxController(UsbConstants.DRIVER_CONTROLLER_PORT);
    private final XboxController driverController2 = new XboxController(UsbConstants.AUXDRIVER_CONTROLLER_PORT);

    public RobotContainer() {
        // add negative (-) to getLeftY to invert drive (shooter will be the back, intake will be the front)
        configureButtonBindings();
        
    //arcadedrive gives error
        // drivetrain.arcadeDrive(driverController.getRightX(), driverController.getLeftY());
      }

      private void configureButtonBindings() {
        Trigger lb = new JoystickButton(driverController, XboxConstants.LB_BUTTON);
        Trigger rb = new JoystickButton(driverController, XboxConstants.RB_BUTTON);
        Trigger a = new JoystickButton(driverController2, XboxConstants.A_BUTTON);
        Trigger b = new JoystickButton(driverController2, XboxConstants.B_BUTTON);
        Trigger x = new JoystickButton(driverController2, XboxConstants.X_BUTTON);
        Trigger y = new JoystickButton(driverController2, XboxConstants.Y_BUTTON);
        POVButton povUp = new POVButton(driverController2, 0);
        POVButton povUpRight = new POVButton(driverController2, 45);
        POVButton povRight = new POVButton(driverController2, 90);
        POVButton povDownRight = new POVButton(driverController2, 135);
        POVButton povDown = new POVButton(driverController2, 180);
        POVButton povDownLeft = new POVButton(driverController2, 225);
        POVButton povLeft = new POVButton(driverController2, 270);
        POVButton povUpLeft = new POVButton(driverController2, 315);
      }
      
      //before coding, discuss strategy with team
      public Command getAutonomousCommand() {
        return null;
      }
    
}
