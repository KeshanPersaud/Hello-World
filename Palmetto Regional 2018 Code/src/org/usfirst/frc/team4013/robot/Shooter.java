package org.usfirst.frc.team4013.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;


public class Shooter {
	
// hardware //	
	private TalonSRX topLeft; 		// port 5 //
	private TalonSRX topRight;		// port 4 //
	private TalonSRX bottomLeft;	// port 6 //
	private TalonSRX bottomRight;	// port 3 //
	
	private Solenoid yeet;			// port 3 // shooterCubePull //
	private Solenoid raiseLeft;		// port 2 //
	private Solenoid raiseRight;	// port 4 //
	 Timer shootTime = new Timer();
	 Timer revTime = new Timer();
// variables //
	 // Numbers //
	 	private int PCMPort = 9;
	 	private int topSpeed = 1;
	 	private double halfSpeed = 0.75;
	 	private int  noSpeed = 0;
	
	public Shooter(int topLeftPort, int topRightPort, int bottomLeftPort, int bottomRightPort, int yeetPort, int raiseLeftPort, int raiseRightPort) {
		
		topLeft = new TalonSRX(topLeftPort);
		topRight = new TalonSRX(topRightPort);
		bottomLeft = new TalonSRX(bottomLeftPort);
		bottomRight = new TalonSRX(bottomRightPort);
		
		yeet = new Solenoid(PCMPort, yeetPort);
		raiseLeft = new Solenoid(PCMPort, raiseLeftPort);
		raiseRight = new Solenoid(PCMPort, raiseRightPort);
		
	}
	
	public void initialize() {
		// Inverting Motors //
			topLeft.setInverted(true);	
			bottomLeft.setInverted(true);
	}

	public void setShooterPosition(boolean position) {	
		
		raiseLeft.set(position); 
		raiseRight.set(position);
		
	}
	
	public void shoot(boolean shouldShoot)  {
		
		yeet.set(shouldShoot);
		
	}
	
	public void revForward(double speed) {
			topLeft.set(ControlMode.PercentOutput, speed);
			topRight.set(ControlMode.PercentOutput, speed);
			bottomLeft.set(ControlMode.PercentOutput, speed);
			bottomRight.set(ControlMode.PercentOutput, speed);	
	}

	public void revBackward(double speed) {
		topLeft.set(ControlMode.PercentOutput, -speed);
		topRight.set(ControlMode.PercentOutput, -speed);
		bottomLeft.set(ControlMode.PercentOutput, -speed);
		bottomRight.set(ControlMode.PercentOutput, -speed);	
	}
	
	public void stopRev() {
		topLeft.set(ControlMode.PercentOutput, noSpeed);
		topRight.set(ControlMode.PercentOutput, noSpeed);
		bottomLeft.set(ControlMode.PercentOutput, noSpeed);
		bottomRight.set(ControlMode.PercentOutput, noSpeed);	
	}
	
	public void autoShootSwitch() {
		revBackward(1);
		revTime.start();
		while(revTime.get() < 0.5) 
		{}
		revTime.stop();
		revTime.reset();
		
		revForward(0.75);
		revTime.start();
		while(revTime.get() < 1.5)
		{}
		shoot(true);
		shootTime.start();
		while(shootTime.get() < 1.5)
		{}
		
		stopRev();
		shoot(false);
		shootTime.stop();
		shootTime.reset();
		revTime.stop();
		revTime.reset();
	}

	public void autoShootScale() {
		revBackward(1);
		revForward(1);
		revTime.start();
		while(revTime.get() > 1.5)
		{}
		
		shoot(true);
		shootTime.start();
		while(shootTime.get() > 1.5)
		{}
		
		stopRev();
		shoot(false);
	}

	public void differentRev(double topmotorspeed, double bottommotorspeed) {
		topLeft.set(ControlMode.PercentOutput, topmotorspeed);
		topRight.set(ControlMode.PercentOutput, topmotorspeed);
		bottomLeft.set(ControlMode.PercentOutput, bottommotorspeed);
		bottomRight.set(ControlMode.PercentOutput, bottommotorspeed);	
	}
}
