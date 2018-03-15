package org.usfirst.frc.team4013.robot;

public class AutonomousThread {
	
	// Methods //	
		Drivetrain drivetrain;
		Intake intake;
		Shooter shooter;
		public AutonomousThread (Drivetrain _drivetrain, Intake _intake, Shooter _shooter) {
			drivetrain = _drivetrain;
			intake = _intake;
			shooter = _shooter;
		}	
		
// odds one outs //		
	Thread Null = new Thread(new Runnable() {
		@Override
		public void run() {
			
		}
	} );
	
	Thread CrossBaseline = new Thread(new Runnable() {
		@Override
		public void run() {
			
			drivetrain.timeDrive(1.5);
			
		}
	} );

	
// switch //	
	Thread CenterSwitchLeftPlate = new Thread(new Runnable() {
		@Override
		public void run() {
			
			drivetrain.timeDrive(3);
			
		}
	} );
	
	Thread CenterSwitchRightPlate = new Thread(new Runnable() {
		@Override
		public void run() {
			
			drivetrain.timeDrive(3);
		}
	} );
	
	Thread RightSwitchLeftPlate = new Thread(new Runnable() {
		@Override
		public void run() {
			drivetrain.timeDrive(3);
			
		}
	} );
	
	Thread RightSwitchRightPlate = new Thread(new Runnable() {
		@Override
		public void run() {
			
			drivetrain.timeDrive(-3);
			shooter.autoShootSwitch();
			
		}
	} );
	
	Thread LeftSwitchLeftPlate = new Thread(new Runnable() {
		@Override
		public void run() {
			
			drivetrain.timeDrive(-3);
			shooter.autoShootSwitch();
		}
	} );
	
	Thread LeftSwitchRightPlate = new Thread(new Runnable() {
		@Override
		public void run() {
			
			drivetrain.timeDrive(3);
			
		}
	} );

	
// scale //	
	Thread CenterScaleLeftPlate = new Thread(new Runnable() {
		@Override
		public void run() {
			
			
		}
	} );
	
	Thread CenterScaleRightPlate = new Thread(new Runnable() {
		@Override
		public void run() {
			
		}
	} );
	
	Thread RightScaleLeftPlate = new Thread(new Runnable() {
		@Override
		public void run() {
			
		}
	} );
	
	Thread RightScaleRightPlate = new Thread(new Runnable() {
		@Override
		public void run() {
			
			
		}
	} );
	
	Thread LeftScaleLeftPlate = new Thread(new Runnable() {
		@Override
		public void run() {
			
			
		}
	} );
	
	Thread LeftScaleRightPlate = new Thread(new Runnable() {
		@Override
		public void run() {
			
			
		}
	} );
}
