package org.usfirst.frc.team4013.robot;

import edu.wpi.first.wpilibj.VictorSP;


public class WingardiumLeviosa {
	
	// hardware //	  
		private VictorSP WLWinch1;
		private VictorSP WLWinch2;
		private VictorSP WLLift;
	// variables //
		
		
		// done //
		public  WingardiumLeviosa (int WLW1,  int WLW2,  int WLL) {
			WLWinch1 = new VictorSP(WLW1);
			WLWinch2 = new VictorSP(WLW2);
			WLLift = new VictorSP(WLL);
		}
		
		// what are we doing //
		public void initialize() {
			
		}
		
		// what are we doing //
		public void moveLift(int power) {
			WLLift.set(power);
		}
		
		// what are we doing //
		public void raiseRobot(int power ) {
			WLLift.set(-power);
			WLWinch1.set(power);
			WLWinch2.set(power);
		}

}
