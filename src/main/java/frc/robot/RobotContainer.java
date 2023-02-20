package frc.robot;

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
import frc.robot.commands.VertUp;
import frc.robot.commands.VertDown;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.commands.HorizIn;
import frc.robot.commands.HorizOut;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.VertElev;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.HorizElev;

public class RobotContainer {
    private final Drivetrain drivetrain = new Drivetrain();
    private final VertElev vertElev = new VertElev();
    private final Intake intake = new Intake();
    private final HorizElev horizElev = new HorizElev();

    private final XboxController driverController = new XboxController(UsbConstants.DRIVER_CONTROLLER_PORT);
    private final XboxController driverController2 = new XboxController(UsbConstants.AUXDRIVER_CONTROLLER_PORT);

    private final VertUp vertUp = new VertUp(vertElev);
    private final VertDown vertDown = new VertDown(vertElev);
    private final IntakeIn intakeIn = new IntakeIn(intake);
    private final IntakeOut intakeOut = new IntakeOut(intake);
    private final HorizIn horizIn = new HorizIn(horizElev);
    private final HorizOut horizOut = new HorizOut(horizElev);

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

      //   lb.whileTrue();
      //   rb.whileTrue();
        a.whileTrue(intakeIn);
      //   b.whileTrue();
      //   x.whileTrue();
        y.whileTrue(intakeOut);
        povUp.whileTrue(vertUp);
        povUpRight.whileTrue(horizIn);
        povUpLeft.whileTrue(horizOut);
        povDown.whileTrue(vertDown);
      //   povDownRight.whileTrue();
      //   povDownLeft.whileTrue();
      //   povRight.whileTrue();
      //   povLeft.whileTrue();
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
