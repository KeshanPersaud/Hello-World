/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4013.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;

public class Robot extends IterativeRobot {

// Methods //
	Drivetrain drivetrain = new Drivetrain(7,1,8,2);
	Intake intake = new Intake(3,1,2,0,1,5);
	Shooter shooter = new Shooter(5,4,6,3,3,2,4);
	Compressor c = new Compressor(9);
	AutonomousThread auto = new AutonomousThread(drivetrain, intake, shooter);
	
	UsbCamera camera0;
	UsbCamera camera1;
	
// controllers //
	Joystick rightdrive = new Joystick(0);
	Joystick leftdrive = new Joystick(1);
	GamePad gamepad = new GamePad(2);
// String autonomous names //	
	private static final String kDefaultAuto = "Default";
	private static final String kBaseline = "Cross Baseline";
	private static final String kSwitch = "Score in Switch";
	private static final String kScale = "Score in Scale";
	private String functionSelected;
	private SendableChooser<String> functionChooser = new SendableChooser<>();

	private static final String startingPositionLeft = "Left";
	private static final String startingPositionCenter = "Center";
	private static final String startingPositionRight = "Right";
	private String positionSelected;
	private SendableChooser<String> positionChooser = new SendableChooser<>();
	
	private String gameData = "";
	private char switchPosition;
	private char scalePosition;
// create autonomous //	
	private Thread autonomousSelected;
// start time //	
	Timer timeout = new Timer();

	@Override
	public void robotInit() {
	// initialize //
		drivetrain.initialize();
		intake.initialize();
		shooter.initialize();
		
		c.start();
		SmartDashboard.putString("Compressor:", "Enabled");
		SmartDashboard.updateValues();
		
	// autonomous Selections //	
		/*
		functionChooser.addDefault("Default Auto", kDefaultAuto);
		functionChooser.addObject("Baseline Auto", kBaseline);
		functionChooser.addObject("Switch Auto", kSwitch);
		functionChooser.addObject("Scale Auto", kScale);
		SmartDashboard.putData("Function choices", functionChooser);
		
		positionChooser.addDefault("Center", startingPositionCenter);
		positionChooser.addObject("Left", startingPositionLeft);
		positionChooser.addObject("Right", startingPositionRight);
		SmartDashboard.putData("Starting Position?", positionChooser);
		*/
		timeout.start();
	// Camera //
		CameraServer.getInstance().startAutomaticCapture(0);
		//camera1 = CameraServer.getInstance().startAutomaticCapture(1);
	// compressor //
		c.setClosedLoopControl(true);
	}

	@Override
	public void autonomousInit() {
	// autonomous filtering //
		// Right side //
//			drivetrain.drive(0.5, 0.5);
//			timeout.reset();
//			
//			while(timeout.get() < 5.5)
//			{}
//			drivetrain.drive(0, 0);
//			if(DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'R')
//			{
//				drivetrain.autoTurn(90);
//				shooter.autoShootScale();
//			}
		// Left side //
			drivetrain.drive(0.5, 0.5);
			timeout.reset();
			
			while(timeout.get() < 2)
			{}
			drivetrain.drive(0, 0);
			
			if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R')
			{
				intake.arm(false);
				intake.intake();
				shooter.autoShootSwitch(); 
				intake.stopIntake();
			}
		
		/*
		gameData = "";
		SmartDashboard.putString("Is game data null? ", "/Empty/");
		SmartDashboard.putString("Saved gameData", "/Empty/");
		SmartDashboard.putString("Current FMS Data", "/Empty/");
		SmartDashboard.putString("Game Data", "/Empty/");	
		SmartDashboard.putString("Auto Selected: ", "/Empty/");
		SmartDashboard.putString("Timed Out?", "/Empty/");
		
		functionSelected = functionChooser.getSelected();
		
		positionSelected = positionChooser.getSelected();

		timeout.reset();
		
		while(gameData.length()==0) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
			if(timeout.get() > 2) {
				SmartDashboard.putString("Timed Out?", "Yes");
				break;
			}
		}
		
		
		if (DriverStation.getInstance().getGameSpecificMessage().equals("")) {
			SmartDashboard.putString("Is game data null? ", "Null");
			functionSelected = kDefaultAuto;
		}
		else {
			SmartDashboard.putString("Is game data null? ", "Not Null");
		}
		
		SmartDashboard.putString("Saved gameData", gameData);
		SmartDashboard.putString("Current FMS Data", DriverStation.getInstance().getGameSpecificMessage());
		
		if(gameData.equals("LRL")|| gameData.equals("LLL") || gameData.equals("RLR") || gameData.equals("RRR")) {		
			switchPosition = gameData.charAt(0);
			scalePosition = gameData.charAt(1);	
			SmartDashboard.putString("Game Data", "Valid");		
		}
		else {
			functionSelected = kDefaultAuto;
			SmartDashboard.putString("Game Data", "Invalid");
		}
		
		switch (functionSelected) {
		
		case kDefaultAuto:
		default:
			SmartDashboard.putString("Auto Selected: ", "Null");
			autonomousSelected = auto.Null; 
			break;
			
		case kBaseline:
			SmartDashboard.putString("Auto Selected: ", "Cross Baseline");
			autonomousSelected = auto.CrossBaseline;
			break;
			
		case kSwitch:
			switch(positionSelected) {
			case startingPositionCenter:
				if (switchPosition == 'L') {
					SmartDashboard.putString("Auto Selected: ", "Center Switch Left Plate");
					autonomousSelected = auto.CenterScaleLeftPlate;
				}
				else {
					SmartDashboard.putString("Auto Selected: ", "Center Switch Right Plate");
					autonomousSelected = auto.CenterScaleRightPlate;
				}
				break;
			case startingPositionRight:
				if (switchPosition == 'L') {
					SmartDashboard.putString("Auto Selected: ", "Right Switch Left Plate");
					autonomousSelected = auto.RightScaleLeftPlate;
				}
				else {
					SmartDashboard.putString("Auto Selected: ", "Right Switch Right Plate");
					autonomousSelected = auto.RightScaleRightPlate;
				}
				break;
			case startingPositionLeft:
				if (switchPosition == 'L') {
					SmartDashboard.putString("Auto Selected: ", "Left Switch Left Plate");
					autonomousSelected = auto.LeftScaleLeftPlate;
				}
				else {
					SmartDashboard.putString("Auto Selected: ", "Left Switch Right Plate");
					autonomousSelected = auto.LeftScaleRightPlate;
				}
				break;
			}			
			break;
			
		case kScale:
			switch(positionSelected) {
			case startingPositionCenter:
				if(scalePosition =='L') {
					SmartDashboard.putString("Auto Selected: ", "Center Scale Left Plate");
					autonomousSelected = auto.CenterScaleLeftPlate;
				}
				else {
					SmartDashboard.putString("Auto Selected: ", "Center Scale Right Plate");
					autonomousSelected = auto.CenterScaleRightPlate;
				}
				break;
			case startingPositionRight:
				if(scalePosition =='L') {
					SmartDashboard.putString("Auto Selected: ", "Right Scale Left Plate");
					autonomousSelected = auto.RightScaleLeftPlate;
				}
				else {
					SmartDashboard.putString("Auto Selected: ", "Right Scale Right Plate");
					autonomousSelected = auto.RightScaleRightPlate;
				}
				break;
			case startingPositionLeft:
				if(scalePosition =='L') {
					SmartDashboard.putString("Auto Selected: ", "Left Scale Left Plate");
					autonomousSelected = auto.LeftScaleLeftPlate;
				}
				else {
					SmartDashboard.putString("Auto Selected: ", "Left Scale Right Plate");
					autonomousSelected = auto.LeftScaleRightPlate;
				}
				break;
			}
			break;
		}		
	// get autonomous from filtering //	
		autonomousSelected.start();
		*/
	}

	@Override
	public void autonomousPeriodic() {
		
		//while(autonomousSelected.isAlive()) {}
		//autonomousSelected.destroy();
		
	}
	
	@Override
	public void teleopPeriodic() {
//////////////////////////////////////////////////////////////		
	// DriveTrain Coding //
		drivetrain.drive(rightdrive.getY(), leftdrive.getY());
		
/////////////////////////////////////////////////////////////		
	// Intake Coding //
		if(leftdrive.getRawButton(1))
			intake.intake();
		
		else if(leftdrive.getRawButton(2))
			intake.outtake();
		else 
			intake.stopIntake();
		
		if(rightdrive.getRawButton(1))
			intake.spin();
		
		if(rightdrive.getRawButton(8))
			intake.arm(true);
		
		if(rightdrive.getRawButton(9))
			intake.arm(false);
		
//////////////////////////////////////////////////////////////
	// Shooter Coding //
		shooter.shoot(gamepad.getY());
//////////////////////////////////////////////////////////////		
		if(gamepad.getDPadUp()) {
			if(gamepad.getA()) 
				shooter.revBackward(1);

			else
				shooter.revForward(1);
		}
		
		else if(gamepad.getDPadRight()) {
			if(gamepad.getA()) 
				shooter.revBackward(0.7);

			else
				shooter.revForward(0.7);
		}
		
		else if(gamepad.getDPadDown()) {
			if(gamepad.getA()) 
				shooter.revBackward(0.5);

			else
				shooter.revForward(0.5);
		}
		
		else if(gamepad.getDPadLeft()) {
			if(gamepad.getA()) 
				shooter.revBackward(0.25);

			else
				shooter.revForward(0.25);
		}
		
		else 
			shooter.stopRev();
//////////////////////////////////////////////////////////////		
		if(gamepad.getX())
			shooter.setShooterPosition(true);
		
		if(gamepad.getB())
			shooter.setShooterPosition(false);
//////////////////////////////////////////////////////////////		
		if(gamepad.getStart()) {
			c.stop();
			SmartDashboard.putString("Compressor:", "Disabled");
			SmartDashboard.updateValues();
		}
		
		if(gamepad.getBack()) { 
			c.start();
			SmartDashboard.putString("Compressor:", "Enabled");
			SmartDashboard.updateValues();
		}

/////////////////////////////////////////////////////////////
	}

	@Override 
	public void testPeriodic() {
		if(gamepad.getA())
			shooter.differentRev(1, 0.75);
		else 
			shooter.stopRev();
		
		shooter.shoot(gamepad.getY());
		
		if(gamepad.getLeftBumper())
			intake.arm(true);
		
		if(gamepad.getRightBumper())
			intake.arm(false);
	}
}
