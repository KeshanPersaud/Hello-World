package org.usfirst.frc.team4013.robot;
        
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import org.usfirst.frc.team4013.robot.PID;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Timer;
        
        
public class Drivetrain {
	    
// hardware //	
	private TalonSRX frontLeft;   // port 7 //
	private TalonSRX frontRight;  // port 1 //
	private TalonSRX backLeft;    // port 8 //
	private TalonSRX backRight;   // port 2 //
	    
	 ADXRS450_Gyro gyro;
	 Timer time = new Timer();
	    
// variables //
		// Master Motor Ports //
			private int masterFrontLeft;
			private int masterFrontRight;
 		// Drive TalonSRX's Variable //
			PID DriveTrain = new PID(0,0,0,0,0);
		// PID Loop Gyro //
			private double currentAngle = 0;
			private double angleRangeOfError = 2;
			
	    
	// done //
	public Drivetrain(int frontLeftPort, int frontRightPort, int backLeftPort, int backRightPort ) {
		
		frontLeft = new TalonSRX(frontLeftPort);
		frontRight = new TalonSRX(frontRightPort);
		backLeft = new TalonSRX(backLeftPort);
		backRight = new TalonSRX(backRightPort);
		
		gyro = new ADXRS450_Gyro();
		
		masterFrontLeft = frontLeftPort;
		masterFrontRight = frontRightPort;
	}   
        
	// done //
	public void initialize() {
		frontLeft.setInverted(true);
		backLeft.setInverted(true);
		
		gyro.reset();
		gyro.calibrate();
		 
		backLeft.set(ControlMode.Follower, masterFrontLeft);
		backRight.set(ControlMode.Follower, masterFrontRight);
		
	}   
	     
	// done //
	public void autoTurn(double targetAngle) {
		gyro.reset();
		PID turningLoop = new PID(1.0/180.0, 0, 0, 1, 2);
		currentAngle = gyro.getAngle();
		
		while(Math.abs(targetAngle-currentAngle) > angleRangeOfError) {
			
			turningLoop.updateLoop(currentAngle,targetAngle);
			
			frontRight.set(ControlMode.PercentOutput,-turningLoop.totalGain);
			frontLeft.set(ControlMode.PercentOutput, turningLoop.totalGain);
			
			currentAngle = gyro.getAngle();
		}
		
	}   
	     
	// done //
	public void drive(double leftpower, double rightpower) {
	
		if(Math.abs(leftpower) < 0.2)
			leftpower = 0;
		
		if(Math.abs(rightpower) < 0.2)
			rightpower = 0;
		
			frontRight.set(ControlMode.PercentOutput,rightpower);
			backRight.set(ControlMode.PercentOutput, rightpower);
			frontLeft.set(ControlMode.PercentOutput, leftpower);
			backLeft.set(ControlMode.PercentOutput,  leftpower);
	}
	
	public void timeDrive(double seconds) {

		drive(0.5,0.5);
		time.start();
		while(time.get() < seconds)
		{}
		time.stop();
		time.reset();
		drive(0,0);
	}
}
 