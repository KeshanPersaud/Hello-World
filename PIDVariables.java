package org.usfirst.frc.team4013.robot;

public class PIDVariables {
	 // PID Variables //
	 	private double Kp;
	 	private double Ki;
	 	private double Kd;
	 	private double Kf;
	 	
	// constructors //
	 	public PIDVariables( double inputKF ,double inputKP, double inputKI, double inputKD) {
		
			this.Kf = inputKF;
			this.Kp = inputKP;
			this.Ki = inputKI;
			this.Kd = inputKD;
			
	} 	
	 	
	 	public PIDVariables(double inputKP, double inputKI, double inputKD) {
		
			this.Kp = inputKP;
			this.Ki = inputKI;
			this.Kd = inputKD;
		
	}
	
	 	public PIDVariables(double inputKP, double inputKI) {
		
			this.Kp = inputKP;
			this.Ki = inputKI;
			this.Kd = 0;
		
	}
	
	 	public PIDVariables(double inputKP) {
		
	 		this.Kp = inputKP;
	 		this.Ki = 0;
	 		this.Kd = 0;
		
	}
	
	// methods //
	 	public double getKp() {
	 		return Kp;
	 	}
	
		public double getKi() {
			return Ki;
		}
		
		public double getKd() {
			return Kd;
		}
		
		public double getKf() {
			return Kf;
		}
		
}
