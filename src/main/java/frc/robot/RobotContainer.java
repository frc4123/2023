package frc.robot;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.Constants.UsbConstants;
import frc.robot.Constants.XboxConstants;

import frc.robot.commands.AutoDriveBackCommand;

import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
    private final Drivetrain drivetrain = new Drivetrain();

    private final XboxController driverController = new XboxController(UsbConstants.DRIVER_CONTROLLER_PORT);
    private final XboxController driverController2 = new XboxController(UsbConstants.AUXDRIVER_CONTROLLER_PORT);

    private final SendableChooser<Command> m_autoChooser = new SendableChooser<Command>();

    public RobotContainer() {
        // add negative (-) to getLeftY to invert drive (shooter will be the back, intake will be the front)
        configureButtonBindings();

        initializeAutoChooser();

        drivetrain.execute();
    
        drivetrain.setDefaultCommand(new RunCommand(() -> drivetrain.arcadeDrive(
          driverController.getRightX(),
          driverController.getLeftY()),
          drivetrain));

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

      public void initializeAutoChooser(){
        m_autoChooser.setDefaultOption("Do Nothing", new WaitCommand(0));
        m_autoChooser.addOption("Drive Back", new WaitCommand(0.1)
          .andThen(new AutoDriveBackCommand(drivetrain).withTimeout(3.8)));
      
       SmartDashboard.putData("Auto Selector", m_autoChooser);
      }
      
      //before coding, discuss strategy with team
      public Command getAutonomousCommand() {
        return m_autoChooser.getSelected();
      }
    
}
