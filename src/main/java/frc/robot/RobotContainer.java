package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.Constants.UsbConstants;

import frc.robot.commands.HorizIn;
import frc.robot.commands.HorizOut;
import frc.robot.commands.IntakeCubeIn;
import frc.robot.commands.IntakeCubeOut;
import frc.robot.commands.SetVertSetpoint;
import frc.robot.commands.VertDown;
import frc.robot.commands.VertUp;
import frc.robot.commands.WristIn;
import frc.robot.commands.WristOut;
import frc.robot.commands.auto.AutoDriveBackCommand;
import frc.robot.commands.auto.AutoTrajectory;
import frc.robot.commands.drivetrain.SetDrivetrain;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HorizElev;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.VertElev;
import frc.robot.subsystems.Wrist;

public class RobotContainer {
    private final Drivetrain m_drivetrain = new Drivetrain();
    private final VertElev m_vertElev = new VertElev();
    private final Intake m_intake = new Intake();
    private final HorizElev m_horizElev = new HorizElev();
    private final Wrist m_wrist = new Wrist();

    private final XboxController driverController = new XboxController(UsbConstants.DRIVER_CONTROLLER_PORT);
    private final CommandXboxController driverController2 = new CommandXboxController(UsbConstants.AUXDRIVER_CONTROLLER_PORT);

    private final VertUp m_vertUp = new VertUp(m_vertElev);
    private final VertDown m_vertDown = new VertDown(m_vertElev);
    private final IntakeCubeIn m_intakeCubeIn = new IntakeCubeIn(m_intake);
    private final IntakeCubeOut m_intakeCubeOut = new IntakeCubeOut(m_intake);
    private final HorizIn m_horizIn = new HorizIn(m_horizElev);
    private final HorizOut m_horizOut = new HorizOut(m_horizElev);
    private final WristIn m_wristIn = new WristIn(m_wrist);
    private final WristOut m_wristOut = new WristOut(m_wrist);

    private final SendableChooser<Command> m_autoChooser = new SendableChooser<Command>();

    public void initializeSubsystems() {
      // add negative (-) to getLeftY to invert drive
      m_drivetrain.setDefaultCommand(
          new SetDrivetrain(
            m_drivetrain, 
            () -> -driverController.getLeftY(), 
            () -> -driverController.getRightX()));
    }

    public RobotContainer() {
        configureButtonBindings();
        initializeSubsystems();
        initializeAutoChooser();
      }

      private void configureButtonBindings() {
        // driverController2.leftBumper().onTrue();    can we make turn the drivetrain half speed with this?
        // driverController2.rightBumper().onTrue();    maybe flip drive direction with this? ill talk with marco and toshi
        // driverController2.a().whileTrue(m_intakeCubeIn);
        driverController2.b().whileTrue(m_wristIn);
        driverController2.x().whileTrue(m_wristOut);
        // driverController2.y().whileTrue(m_intakeCubeOut);
        // driverController2.povUp().whileTrue(m_vertUp);
        // driverController2.povDown().whileTrue(m_vertDown);
        driverController2.povLeft().whileTrue(m_horizIn);
        driverController2.povRight().whileTrue(m_horizOut);

        // driverController2.leftStick().whileTrue(
        //   new SetVertSetpoint(
        //     m_vertElev, driverController2::getRightY
        //   )
        // );

        driverController2.y().whileTrue(
          new SetVertSetpoint(
            m_vertElev, () -> 1
          )
        );
        driverController2.a().whileTrue(
          new SetVertSetpoint(
            m_vertElev, () -> -1
          )
        );
        // driverController2.axisGreaterThan(0, 0)
      //   driverController2.povDownRight().whileTrue();
      //   driverController2.povDownLeft().whileTrue();
      //   driverController2.povUpRight().whileTrue();
      //   driverController2.povUpLeft().whileTrue();
      }

      public void initializeAutoChooser(){
        m_autoChooser.setDefaultOption("Do Nothing", new WaitCommand(0));
        m_autoChooser.addOption("Leave Community Zone", new WaitCommand(0.1)
          .andThen(new AutoDriveBackCommand(m_drivetrain).withTimeout(3.8)));
        // m_autoChooser.addOption("Charge Station Dock", new WaitCommand(0.1)
          // .andThen(new DockDrive(drivetrain).withTimeout(5)));
        m_autoChooser.addOption("Drivetrain Test", new WaitCommand(0.1)
          .andThen(new AutoDriveBackCommand(m_drivetrain).withTimeout(5)));
        m_autoChooser.addOption("brady test", new WaitCommand(0.1)
        .andThen(new AutoTrajectory(m_drivetrain)).withTimeout(15));
      
       SmartDashboard.putData("Auto Selector", m_autoChooser);
      }
      
      //before coding, discuss strategy with team
      public Command getAutonomousCommand() {
        return m_autoChooser.getSelected();
      }
    
}
