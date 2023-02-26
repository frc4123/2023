package frc.robot.subsystems;

import edu.wpi.first.networktables.BooleanPublisher;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.StringPublisher;
import edu.wpi.first.wpilibj.DriverStation;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Controls extends SubsystemBase {
  StringPublisher allianceString;
  BooleanPublisher allianceBoolean;
  // private PowerDistribution pdh = new PowerDistribution(1, ModuleType.kRev);

  public Controls() {
    initSmartDashboard();
  }

  public void updateAllianceColor() {
    allianceString.set(getAllianceColor().toString());
    allianceBoolean.set(getAllianceColorBoolean());
  }

  /**
   * Returns the robot's current alliance color
   *
   * @return Returns the current alliance color.
   */
  public DriverStation.Alliance getAllianceColor() {
    return DriverStation.getAlliance();
  }

  /**
   * Returns true when the alliance color is not Blue`
   *
   * @return Returns the current alliance color.
   */
  public boolean getAllianceColorBoolean() {
    return DriverStation.getAlliance() != DriverStation.Alliance.Blue;
  }

  public void setPDHChannel(boolean on) {
    // pdh.setSwitchableChannel(on);
  }

  /** Initializes values on SmartDashboard*/
  private void initSmartDashboard() {
    //    Shuffleboard.getTab("SmartDashboard")
    //            .add("Alliance", getAllianceColorBoolean())
    //            .withWidget(BuiltInWidgets.kBooleanBox)
    //            .withProperties(Map.of("Color when true", "#FF0000", "Color when false",
    // "#0000FF"));
    var controlsTab =
        NetworkTableInstance.getDefault().getTable("Shuffleboard").getSubTable("Controls");
    allianceString = controlsTab.getStringTopic("alliance_string").publish();
    allianceBoolean = controlsTab.getBooleanTopic("Alliance").publish();
  }

  /** Sends values to SmartDashboard */
  private void updateSmartDashboard() {
    allianceString.set(getAllianceColor().toString());
    allianceBoolean.set(getAllianceColorBoolean());
    // SmartDashboardTab.putString("Controls", "alliance_string", getAllianceColor().toString()); TODO: fix 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updateSmartDashboard();
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}