// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.vertical;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.VertElev;
import java.util.function.DoubleSupplier;
import edu.wpi.first.math.MathUtil;

public class SetVertSetpoint extends CommandBase {
  private VertElev m_vert;
  // private double m_setpoint;
  private DoubleSupplier m_input;

  public SetVertSetpoint(VertElev vert, DoubleSupplier input) {
    m_vert = vert;
    // m_setpoint = setpoint;
    m_input = input;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(vert);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // m_wrist.setControlState(WRIST.STATE.USER_SETPOINT);
    // m_wrist.setDesiredPositionRadians(m_setpoint);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // System.out.println(m_vert.getPosition() + m_input.getAsDouble()*1);

    // double position = m_vert.getPosition();
    // double delta = m_input.getAsDouble()*30;
    // double newSetpoint = position + delta;
    // System.out.println("position:" + position + "\tdelta"+delta + "\tnewSetpoint" + newSetpoint);
    // m_vert.setPosition(newSetpoint);
    // if (m_vert.getControlState() == Elevator.State.CONTROL_MODE) {
    //   m_vert.setDesiredPositionMeters(m_vert.getHeightMeters());
    //   m_vert.SetPosition(0);
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // vert.setControlState(WRIST.STATE.AUTO_SETPOINT);
    // vert.setPosition(WRIST.SETPOINT.STOWED.get());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
