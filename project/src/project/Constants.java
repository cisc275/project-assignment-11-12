package project;

public class Constants {
	//General Constants
	
	
	//Game 1 Constants
	
		//initial values/positions for osprey
		final static int OX_I = (View.frameWidth / 2) - 100;
		final static int OY_I = 100;
		final static int OX_INCR_I = 0;
		final static int OY_INCR_I = 0;
		final static int O_IMH = 75;
		final static int O_IMW = 110;
		
		//initial values/positions for fish
		final static int FX_I = View.frameWidth - 100;
		final static int FY_INCR_I = 0;
		final static int F1_SPEED = -3;
		final static int F2_SPEED = -4;
		final static int F3_SPEED = -5;
		final static int F1_PV = 1;
		final static int F2_PV = 2;
		final static int F3_PV = 3;
		final static int F_IMH = 50;
		final static int F1_IMW = 50;
		final static int F2_IMW = 75;
		final static int F3_IMW = 100;
		
		//initial values/positions for seaweed
		final static int SWX_I = View.frameWidth;
		final static int SWY_INCR_I = 0;
		final static int SW1_SPEED = -3;  
		final static int SW2_SPEED = -3;
		final static int SW3_SPEED = -3;
		final static int SW_PV = -2;
		final static int SW_IMH = 50;
		final static int SW_IMW = 50;
		
		//levels for both scoring objects (game1)
		final static int SO_LEVEL1 = 275;
		final static int SO_LEVEL2 = 375;
		final static int SO_LEVEL3 = 475;
		
		//boundaries/collision
		final static int O_YBound = 80;
		final static int O_upwardsYIncr = -35;
	
		
	//Game 2 Constants
		
		//initial values/positions for clapper rail
		final static int CRX_I = 140;
		final static int CRY_I = 250;
		final static int CR_Y = 150;
		final static int CR_X = 160;
		final static int CRX_INCR_I = 0;
		final static int CRY_INCR_I = 0;
		final static int CR_IMH = 50;
		final static int CR_IMW = 50;
		
		
		final static int CR_Y_SPACE = 30;
		final static int CR_BOUND = 250 + CR_Y;
		final static int CR_BOUND_TOP = 250;
		final static int CR_BOUND_BOTTOM = 250 + CR_Y;
		
		//occupancy positions
		final static int G2Y = 300;
		final static int G2Y2 = 450;
		final static int G2X = (View.frameWidth/5);
		
		//scoringObjects
		final static int refreshTime = 70;
		final static int numNew = 3;
		//initial values
		final static int FT_XI = 0;
		final static int FT_YI = 0;
		final static int FT_IMW = 30;
		final static int FT_IMH = 50;
		
		//fox
		final static int foxTime = 1000;
		//initial positions
		final static int FX_X = -200;
		final static int FX_Y = 200;
		final static int FX_XI = 1;
		final static int FX_YI = 0;
		final static int FX_IMW = 150;
		final static int FX_IMH = 150;
}
