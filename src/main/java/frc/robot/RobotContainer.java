package frc.robot;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;


import frc.robot.Constants.UsbConstants;
import frc.robot.commands.auto.DriveBackHalf;
import frc.robot.commands.auto.DriveBackFull;
import frc.robot.commands.auto.DriveForwardHalf;
import frc.robot.commands.auto.DriveForwardFull;
import frc.robot.commands.drivetrain.SetDrivetrain;
import frc.robot.commands.horizontal.HorizIn;
import frc.robot.commands.horizontal.HorizOut;
import frc.robot.commands.intake.IntakeCubeIn;
import frc.robot.commands.intake.IntakeCubeOut;
import frc.robot.commands.piston.PistonIn;
import frc.robot.commands.piston.PistonOut;
import frc.robot.commands.vertical.SetVertSetpoint;
// import frc.robot.commands.vertical.VertIncrement;
import frc.robot.commands.vertical.VertDown;
import frc.robot.commands.vertical.VertCone;
import frc.robot.commands.vertical.VertUp;
import frc.robot.commands.wrist.WristIn;
import frc.robot.commands.wrist.WristMid;
import frc.robot.commands.wrist.WristOut;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HorizElev;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Piston;
import frc.robot.subsystems.VertElev;
import frc.robot.subsystems.Wrist;

public class RobotContainer {
    private final Drivetrain m_drivetrain = new Drivetrain();
    private final VertElev m_vertElev = new VertElev();
    private final Intake m_intake = new Intake();
    private final HorizElev m_horizElev = new HorizElev();
    private final Wrist m_wrist = new Wrist();
    private final Piston m_piston = new Piston();

    private final PS4Controller driverController = new PS4Controller(UsbConstants.DRIVER_CONTROLLER_PORT);
    private final PS4Controller driverController2 = new PS4Controller(UsbConstants.AUXDRIVER_CONTROLLER_PORT);

    private final VertUp m_vertUp = new VertUp(m_vertElev);
    private final VertCone m_vertCone = new VertCone(m_vertElev);
    private final VertDown m_vertDown = new VertDown(m_vertElev);
    private final IntakeCubeIn m_intakeCubeIn = new IntakeCubeIn(m_intake);
    private final IntakeCubeOut m_intakeCubeOut = new IntakeCubeOut(m_intake);
    private final HorizIn m_horizIn = new HorizIn(m_horizElev);
    private final HorizOut m_horizOut = new HorizOut(m_horizElev);
    private final WristIn m_wristIn = new WristIn(m_wrist);
    private final WristMid m_wristMid = new WristMid(m_wrist);
    private final WristOut m_wristOut = new WristOut(m_wrist);
    private final PistonIn m_pistonIn = new PistonIn(m_piston);
    private final PistonOut m_pistonOut = new PistonOut(m_piston);

    private final SendableChooser<Command> m_autoChooser = new SendableChooser<Command>();

    public void initializeSubsystems() {
      // add negative (-) to getLeftY to invert drive
      m_drivetrain.setDefaultCommand(
        new SetDrivetrain(
          m_drivetrain, 
          () -> -driverController.getLeftY(), 
          () -> -driverController.getRightX()));

      // m_vertElev.setDefaultCommand(
      //   new VertIncrement(
      //     m_vertElev, 
      //     () -> -driverController2.getLeftY()));
    }

    public RobotContainer() {
        configureButtonBindings();
        initializeSubsystems();
        initializeAutoChooser();
      }

    private void configureButtonBindings() {
      // driverController2.leftBumper().onTrue(new WaitCommand(0.1).andThen(m_toggleControl).withTimeout(0.5));    
      // driverController2.rightBumper().onTrue(new WaitCommand(0.1).andThen(m_toggleSet).withTimeout(0.5));
      while (driverController2.getCrossButtonPressed()) m_vertCone.execute();
      while (driverController2.getCircleButtonPressed()) m_wristIn.execute();
      while (driverController2.getSquareButtonPressed()) m_wristOut.execute();
      while (driverController2.getTriangleButtonPressed()) m_wristMid.execute();
      while (driverController2.getPOV() == 0) m_vertUp.execute();
      while (driverController2.getPOV() == 180) m_vertDown.execute();
      while (driverController2.getPOV() == 270) m_horizIn.execute();
      while (driverController2.getPOV() == 90) m_horizOut.execute();

      while (driverController.getCrossButtonPressed()) m_intakeCubeIn.execute();
      while (driverController.getTriangleButtonPressed()) m_intakeCubeOut.execute();
      if (driverController.getCircleButtonPressed()) m_pistonOut.execute();
      if (driverController.getSquareButtonPressed()) m_pistonIn.execute();
      //when not pressed, dpad = -1 int

      // driverController2.leftStick().whileTrue(
      //   new SetVertSetpoint(
      //     m_vertElev, driverController2::getRightY
      //   )
      // );

      // driverController2.y().whileTrue(
      //   new SetVertSetpoint(
      //     m_vertElev, () -> 1
      //   )
      // );
      // driverController2.a().whileTrue(
      //   new SetVertSetpoint(
      //     m_vertElev, () -> -1
      //   )
      // );
      // driverController2.axisGreaterThan(0, 0)
      //   driverController2.povDownRight().whileTrue();
      //   driverController2.povDownLeft().whileTrue();
      //   driverController2.povUpRight().whileTrue();
      //   driverController2.povUpLeft().whileTrue();
    }

    public void initializeAutoChooser(){
      m_autoChooser.setDefaultOption(
        "Zone 1/3 High",
          new WaitCommand(0.01)
            .andThen(new VertDown(m_vertElev).withTimeout(0.2)
            .alongWith(new HorizIn(m_horizElev).withTimeout(0.2))
            .alongWith(new WristIn(m_wrist)).withTimeout(0.2))
            .andThen(new VertUp(m_vertElev)).withTimeout(0.5)
            .andThen(new HorizOut(m_horizElev).withTimeout(0.5))
            .andThen(new WristOut(m_wrist).withTimeout(1.1234))
            .andThen(new IntakeCubeOut(m_intake).withTimeout(.35)) //1
            .andThen(new IntakeCubeIn(m_intake).withTimeout(.1)) //NA
            .andThen(new WristIn(m_wrist).withTimeout(1)
            .andThen(new HorizIn(m_horizElev).withTimeout(0.5))
            .andThen(new VertDown(m_vertElev).withTimeout(0.3)
            .andThen(new DriveBackHalf(m_drivetrain).withTimeout(6))))); 
      m_autoChooser.addOption(
        "Zone 1/3 Mid",
          new WaitCommand(0.01)
            .andThen(new VertDown(m_vertElev).withTimeout(0.2)
            .alongWith(new HorizIn(m_horizElev).withTimeout(0.2))
            .alongWith(new WristIn(m_wrist)).withTimeout(0.2))
            .andThen(new VertUp(m_vertElev)).withTimeout(0.5)
            .andThen(new WristOut(m_wrist).withTimeout(1.4123))
            .andThen(new IntakeCubeOut(m_intake).withTimeout(.35)) //1
            .andThen(new IntakeCubeIn(m_intake).withTimeout(.1)) //NA
            .andThen(new WristIn(m_wrist).withTimeout(1)
            .andThen(new VertDown(m_vertElev).withTimeout(0.3)
            .andThen(new DriveBackHalf(m_drivetrain).withTimeout(6))))); 
      m_autoChooser.addOption(
        "Zone 2 High (Red)",
          new WaitCommand(0.01)
            .andThen(new VertDown(m_vertElev).withTimeout(0.2)
            .alongWith(new HorizIn(m_horizElev).withTimeout(0.2))
            .alongWith(new WristIn(m_wrist)).withTimeout(0.2))
            .andThen(new VertUp(m_vertElev)).withTimeout(0.5)
            .andThen(new HorizOut(m_horizElev).withTimeout(0.5))
            .andThen(new WristOut(m_wrist).withTimeout(1.1234))
            .andThen(new IntakeCubeOut(m_intake).withTimeout(.35)) //1
            .andThen(new IntakeCubeIn(m_intake).withTimeout(.1)) //NA
            .andThen(new WristIn(m_wrist).withTimeout(1)
            .andThen(new HorizIn(m_horizElev).withTimeout(0.5))
            .andThen(new VertDown(m_vertElev).withTimeout(0.3)
            .andThen(new DriveBackHalf(m_drivetrain).withTimeout(3.33))))); //3.335
      m_autoChooser.addOption(
        "Zone 2 High (Blue)",
            new WaitCommand(0.01)
                  .andThen(new VertDown(m_vertElev).withTimeout(0.2)
                  .alongWith(new HorizIn(m_horizElev).withTimeout(0.2))
                  .alongWith(new WristIn(m_wrist)).withTimeout(0.2))
                  .andThen(new VertUp(m_vertElev)).withTimeout(0.5)
                  .andThen(new HorizOut(m_horizElev).withTimeout(0.5))
                  .andThen(new WristOut(m_wrist).withTimeout(1.1234))
                  .andThen(new IntakeCubeOut(m_intake).withTimeout(.35)) //1
                  .andThen(new IntakeCubeIn(m_intake).withTimeout(.1)) //NA
                  .andThen(new WristIn(m_wrist).withTimeout(1)
                  .andThen(new HorizIn(m_horizElev).withTimeout(0.5))
                  .andThen(new VertDown(m_vertElev).withTimeout(0.3)
                  .andThen(new DriveBackHalf(m_drivetrain).withTimeout(3.255))))); //3.335
      m_autoChooser.addOption("Zone 2 Mid (Red)",
          new WaitCommand(0.01)
            .andThen(new VertDown(m_vertElev).withTimeout(0.2)
            .alongWith(new HorizIn(m_horizElev).withTimeout(0.2))
            .alongWith(new WristIn(m_wrist)).withTimeout(0.2))
            .andThen(new VertUp(m_vertElev)).withTimeout(0.5)
            .andThen(new WristOut(m_wrist).withTimeout(1.4123))
            .andThen(new IntakeCubeOut(m_intake).withTimeout(.35)) //1
            .andThen(new IntakeCubeIn(m_intake).withTimeout(.1)) //NA
            .andThen(new WristIn(m_wrist).withTimeout(1)
            .andThen(new VertDown(m_vertElev).withTimeout(0.3)
            .andThen(new DriveBackHalf(m_drivetrain).withTimeout(3.33))))); //3.335
      m_autoChooser.addOption("Zone 2 Mid (Blue)",
          new WaitCommand(0.01)
              .andThen(new VertDown(m_vertElev).withTimeout(0.2)
              .alongWith(new HorizIn(m_horizElev).withTimeout(0.2))
              .alongWith(new WristIn(m_wrist)).withTimeout(0.2))
              .andThen(new VertUp(m_vertElev)).withTimeout(0.5)
              .andThen(new WristOut(m_wrist).withTimeout(1.4123))
              .andThen(new IntakeCubeOut(m_intake).withTimeout(.35)) //1
              .andThen(new IntakeCubeIn(m_intake).withTimeout(.1)) //NA
              .andThen(new WristIn(m_wrist).withTimeout(1)
              .andThen(new VertDown(m_vertElev).withTimeout(0.3)
              .andThen(new DriveBackHalf(m_drivetrain).withTimeout(3.255))))); //3.335
      
      
       SmartDashboard.putData("Auto Selector", m_autoChooser);
       }
      
      //before coding, discuss strategy with team
      public Command getAutonomousCommand() {
        return m_autoChooser.getSelected();
      }
    
}
