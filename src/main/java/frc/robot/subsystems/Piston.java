package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

public class Piston extends SubsystemBase{

    private final Compressor comp;
    private final DoubleSolenoid doubleSolenoid;

    
    public Piston() {
        comp = new Compressor(0, PneumaticsModuleType.CTREPCM);
        doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

        doubleSolenoid.set(kReverse);
        comp.enableDigital();

        boolean pressureSwitch = comp.getPressureSwitchValue();
        System.out.println(pressureSwitch);

    }

    public void togglePiston(int channel) {
        if ( channel == 0 ){
            doubleSolenoid.set(kForward);
        } else if (channel == 1) {
            doubleSolenoid.set(kReverse);
        }  
    }
}
