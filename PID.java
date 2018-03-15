package org.usfirst.frc.team4013.robot;

import  org.usfirst.frc.team4013.robot.PIDVariables;
import edu.wpi.first.wpilibj.Timer;



public class PID {
	PIDVariables variables;
	double totalGain = 0;
	private double maxGain = 0;	
	private double targetValue = 0;
	private double currentValue = 0;
	private Timer timer = new Timer();
	private double dt = 0;
// P //	
	private double error = 0;
	double rangeOfError = 2;
// I //	
	private double integral = 0;
	private double integralRange;
// D //
	private double derivative = 0;
	private double lastError = 0;
	
		
	public PID (double KF, double KP, double KI, double KD, double _maxGain, double _integralRange) {
		variables = new PIDVariables(KF,KP,KI,KD);
		maxGain = _maxGain;
		integralRange = _integralRange;
		timer.start();
	}
	
	public PID (double KP, double KI, double KD, double _maxGain, double _integralRange) {
		variables = new PIDVariables(0,KP,KI,KD);
		maxGain = _maxGain;
		integralRange = _integralRange;
	}
	
	public double updateLoop(double _currentValue,  double _targetValue) {
		
		currentValue = _currentValue;
		targetValue = _targetValue;
		
		dt = timer.get();
		timer.reset();
		
		error = targetValue-currentValue;
		if(Math.abs(error) < integralRange)
			integral += error* dt;
		derivative =(lastError-error)/dt;
		
		totalGain = variables.getKp()*error + variables.getKi()*integral + variables.getKd()*derivative;
		
		if (totalGain > maxGain) 
			totalGain = maxGain;
			
		if (totalGain<-maxGain)
			totalGain = -maxGain;
		
		lastError = error;
		return totalGain;
	}
}
