package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
// import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// import edu.wpi.first.wpilibj2.command.button.POVButton;
// import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.Constants.UsbConstants;
// import frc.robot.Constants.XboxConstants;

import frc.robot.commands.AutoDriveBackCommand;
// import frc.robot.commands.DockDrive;
import frc.robot.commands.VertUp;
import frc.robot.commands.VertDown;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.commands.HorizIn;
import frc.robot.commands.HorizOut;
import frc.robot.commands.WristIn;
import frc.robot.commands.WristOut;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.VertElev;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.HorizElev;
import frc.robot.subsystems.Wrist;

public class RobotContainer {
    private final Drivetrain drivetrain = new Drivetrain();
    private final VertElev vertElev = new VertElev();
    private final Intake intake = new Intake();
    private final HorizElev horizElev = new HorizElev();
    private final Wrist wrist = new Wrist();

    private final XboxController driverController = new XboxController(UsbConstants.DRIVER_CONTROLLER_PORT);
    private final CommandXboxController driverController2 = new CommandXboxController(UsbConstants.AUXDRIVER_CONTROLLER_PORT);

    private final VertUp vertUp = new VertUp(vertElev);
    private final VertDown vertDown = new VertDown(vertElev);
    private final IntakeIn intakeIn = new IntakeIn(intake);
    private final IntakeOut intakeOut = new IntakeOut(intake);
    private final HorizIn horizIn = new HorizIn(horizElev);
    private final HorizOut horizOut = new HorizOut(horizElev);
    private final WristIn wristIn = new WristIn(wrist);
    private final WristOut wristOut = new WristOut(wrist);

    private final SendableChooser<Command> m_autoChooser = new SendableChooser<Command>();

    public RobotContainer() {
        // add negative (-) to getLeftY to invert drive (shooter will be the back, intake will be the front)
        configureButtonBindings();
        
    //arcadedrive gives error
        // drivetrain.arcadeDrive(driverController.getRightX(), driverController.getLeftY());
      }

      private void configureButtonBindings() {
      //   driverController2.lb().whileTrue();
      //   driverCOntroller2.rb().whileTrue();
        driverController2.a().whileTrue(intakeIn);
        driverController2.b().whileTrue(wristIn);
        driverController2.x().whileTrue(wristOut);
        driverController2.y().whileTrue(intakeOut);
        driverController2.povUp().whileTrue(vertUp);
        driverController2.povRight().whileTrue(horizIn);
        driverController2.povLeft().whileTrue(horizOut);
        driverController2.povDown().whileTrue(vertDown);
      //   driverController2.povDownRight().whileTrue();
      //   driverController2.povDownLeft().whileTrue();
      //   driverController2.povRight().whileTrue();
      //   driverController2.povLeft().whileTrue();
      }

      public void initializeAutoChooser(){
        m_autoChooser.setDefaultOption("Do Nothing", new WaitCommand(0));
        m_autoChooser.addOption("Leave Community Zone", new WaitCommand(0.1)
          .andThen(new AutoDriveBackCommand(drivetrain).withTimeout(3.8)));
        // m_autoChooser.addOption("Charge Station Dock", new WaitCommand(0.1)
          // .andThen(new DockDrive(drivetrain).withTimeout(5)));
      
       SmartDashboard.putData("Auto Selector", m_autoChooser);
      }
      
      //before coding, discuss strategy with team
      public Command getAutonomousCommand() {
        return null;
      }
    
}
