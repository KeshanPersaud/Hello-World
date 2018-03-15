package org.usfirst.frc.team4013.robot;

import edu.wpi.first.wpilibj.Joystick;

public class GamePad {

// Joystick Method //	
	private Joystick GamePad;
// buttons port //	
	int A = 1;
	int B = 2;
	int X = 3;
	int Y = 4;
	int leftBumper = 5;
	int rightBumper = 6;
	int back = 7;
	int start = 8;
	int leftStickButton = 9;
	int rightStickButton = 10;
	int dPadUp = 0;
	int dPadRight = 90;
	int dPadDown = 180;
	int dPadLeft = 270;
	double leftXAxis;
	double rightXAxis;
	double leftYAxis;
	double rightYAxis;
	double leftTrigger;
	double rightTrigger;

// Methods //	
	public GamePad(int portNumber) { 
		 GamePad = new Joystick(portNumber); 
	}
	
	public boolean getA() {
		return GamePad.getRawButton(A);
	}
	
	public boolean getB() {
		return GamePad.getRawButton(B);
	}
	
	public boolean getX() {
		return GamePad.getRawButton(X);
	}
	
	public boolean getY() {
		return GamePad.getRawButton(Y);
	}
	
	public boolean getLeftBumper() {
		return GamePad.getRawButton(leftBumper);
	}
	
	public boolean getRightBumper() {
		return GamePad.getRawButton(rightBumper);
	}
	
	public boolean getBack() {
		return GamePad.getRawButton(back);
	}
	
	public boolean getStart() {
		return GamePad.getRawButton(start);
	}
	
	public boolean getLeftStickButton() {
		return GamePad.getRawButton(leftStickButton);
	}
	
	public boolean getRightStickButton() {
		return GamePad.getRawButton(rightStickButton);
	}
	
	public double getLeftXAxis() {
		return GamePad.getRawAxis(0);
	}
	
	public boolean getDPadUp() {
		if(GamePad.getPOV() == dPadUp)
			return true;
		else
			return false;
	}
	
	public boolean getDPadRight() {
		if(GamePad.getPOV() == dPadRight)
			return true;
		else
			return false;
	}
	
	public boolean getDPadDown() {
		if(GamePad.getPOV() == dPadDown)
			return true;
		else
			return false;
	}
	
	public boolean getDPadLeft() {
		if(GamePad.getPOV() == dPadLeft)
			return true;
		else
			return false;
	}
}
