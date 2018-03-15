package org.usfirst.frc.team4013.robot;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Solenoid;


public class Intake {
	
// hardware //	 
	private VictorSP frontLeft; 	// port 3 //
	private VictorSP frontRight;	// port 1 //
	private VictorSP backLeft;		// port 2 //
	private VictorSP backRight;		// port 0 //
	
	private Solenoid leftArm;		// port 1 //
	private Solenoid rightArm;		// port 5 //
// variables //
	 private double frontIntakeSpeed = 0.5;
	 private double backIntakeSpeed = 0.3;
	 private double frontOuttakeSpeed = -0.3;
	 private double backOuttakeSpeed = -0.45;
	 
	 private int PCMPort = 9;

	public Intake(int frontLeftPort, int frontRightPort, int backLeftPort , int backRightPort, int leftArmPort, int rightArmPort) {
		
		frontLeft = new VictorSP(frontLeftPort);
		frontRight = new VictorSP(frontRightPort);
		backLeft = new VictorSP(backLeftPort);
		backRight = new VictorSP(backRightPort);
		
		leftArm = new Solenoid(PCMPort, leftArmPort);
		rightArm = new Solenoid(PCMPort, rightArmPort);
	}
	
	public void initialize() {
		
		frontLeft.setInverted(true);
		backRight.setInverted(true);
	}
			
	public void intake() {

			frontLeft.set(frontIntakeSpeed);
			frontRight.set(frontIntakeSpeed);
			backLeft.set(backIntakeSpeed);
			backRight.set(backIntakeSpeed);
		
	}
	
	public void outtake() {

			frontLeft.set(frontOuttakeSpeed);
			frontRight.set(frontOuttakeSpeed);
			backLeft.set(backOuttakeSpeed);
			backRight.set(backOuttakeSpeed);
			
	}
	
	public void spin() {

		frontLeft.set(frontIntakeSpeed);
		frontRight.set(frontIntakeSpeed);

	}
	
	public void stopIntake() {
		
		frontLeft.set(0);
		backLeft.set(0);
		frontRight.set(0);
		backRight.set(0);
	}

	public void arm(boolean buttonPressed) {
		
			leftArm.set(!buttonPressed);
			rightArm.set(!buttonPressed);
			
	} 		
}


