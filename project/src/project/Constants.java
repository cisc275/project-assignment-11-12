package project;

public class Constants {
	//General Constants
	final static int GAME_LENGTH = 60000; //milliseconds
	final static int DRAW_DELAY = 25;
	final static int ADD_TO_Y1 = 10;
	final static int ADD_TO_Y2 = 70;
	final static int ADD_TO_Y3 = 120;
	final static int D2 = 2;
	final static int D3 = 3;
	final static int D4 = 4;
	final static int D6 = 6;
	final static int D7 = 7;
	final static int MENU_SUBX = 150;
	final static int MENU_Y = 10;
	final static int MENU_W = 100;
	final static int MENU_H = 30;
	final static int ZERO = 0;
	final static int TEN = 10;
	final static int TWENTY = 20;
	
	//Game 1 Constants
		
	    final static int G1_SPACEBAR_COOLDOWN = 50;
	    final static int G1_NUM_OF_POINTS_NEEDED_FOR_ENERGY = 2;
	    final static int G1_NUM_OF_ENERGY_LEVELS = 5;
	    final static int G1_CHECK_ENERGY_FREQUENCY = 240;

	    //Boundaries 
	    final static int G1_XB_LEFT = 0;
	    final static int G1_XB_RIGHT = View.frameWidth-140;
	    
		//initial values/positions for osprey
		final static int O_IMH = 120;
		final static int O_IMW = 120;
		final static int OX_I = (View.frameWidth / 2) - O_IMW;
		final static int OY_I = View.frameHeight/15;
		final static int OX_INCR_I = 15;
		final static int OY_INCR_I = 0;
		final static int G1_OCEAN_YLOC = View.frameHeight/2;
		
		//initial values/positions for fish
		final static int FX_I = View.frameWidth;
		final static int FY_INCR_I = 0;
		final static int F1_SPEED = -3;
		final static int F2_SPEED = -4;
		final static int F3_SPEED = -5;
		final static int F1_PV = 1;
		final static int F2_PV = 2;
		final static int F3_PV = 3;
		final static int F_IMH = 50;
		final static int F1_IMW = 90;
		final static int F2_IMW = 90;
		final static int F3_IMW = 90;
		
		final static int FS_SPAWN_FREQUENCY = 180;
		final static int FS_DELTAY = 7;
		
		//initial values/positions for seaweed
		final static int SWX_I = View.frameWidth;
		final static int SWY_INCR_I = 0;
		final static int SW1_SPEED = -3;  
		final static int SW2_SPEED = -3;
		final static int SW3_SPEED = -3;
		final static int SW_PV = -2;
		final static int SW_IMH = 60;
		final static int SW_IMW = 60;
		
		//levels for both scoring objects (game1)
		final static int SO_LEVEL1 = 4*View.frameHeight/7;
		final static int SO_LEVEL2 = 5*View.frameHeight/7;
		final static int SO_LEVEL3 = 6*View.frameHeight/7;
		
		//boundaries/collision
		final static int O_YBound = OY_I;
		final static int O_upwardsYIncr = -(Constants.DRAW_DELAY + 5);
		
		//Game 1 tutorial
		final static int FISH_IMAGE_W = 100;
		final static int FISH_IMAGE_H = 50;
		final static int SEAWEED_IMAGE_H = 100;
		final static int RECT_HW = 50;
		final static int RECT1_OFFSET = 488;
		final static int RECT2_OFFSET = 612;
		final static int ARROWKEYS_HW = 200;
		final static int ARROW_OFFSET = 475;
		final static int UP_ARROW_W = 75;
		final static int UP_ARROW_H = 100;
		final static int SEAWEED_ADD_TO_Y = 170;
		final static int X_ADD_TO_Y = 200;
		final static int SPACE_SUBX = 15;
		final static int SPACE_Y = 50;
		final static int SPACE_W = 250;
		final static int SPACE_H = 150;

	//Game 2 Constants
		
		//initial values/positions for clapper rail
		final static int CR_IMH = 100;
		final static int CR_IMW = 100;
		
		final static int CRX_I = View.frameWidth/6 - CR_IMW;
		final static int CRY_I = (int) (1.5*(View.frameHeight/4));
		final static int CR_Y = View.frameHeight/4;
		final static int CR_X = View.frameWidth/6;
		final static int CRX_INCR_I = 0;
		final static int CRY_INCR_I = 0;
		
		final static int CR_Y_SPACE = 50;
		final static int CR_BOUND = CR_Y+ CR_Y;
		final static int CR_BOUND_TOP = CRY_I;
		
		//initial values
		final static int FT_XI = 0;
		final static int FT_YI = 0;
		final static int FT_IMW = 50;
		final static int FT_IMH = 50;
		
		//occupancy positions
		final static int G2_Y = 2*CR_Y;
		final static int G2_Y2 = G2_Y + CR_Y;
		final static int G2X_I = CRX_I + FT_IMW;
		final static int G2Y_I = CRY_I + CR_IMH;
		final static int G2_X = CR_X;
		
		//scoringObjects
		final static int numNew = 5;
		static int g2_lifetime = 100;
		static int refreshTime = g2_lifetime + 10;
		
		//fox
		final static int FX_IMW = 250;
		final static int FX_IMH = 250;

		final static int foxTime = 600;
		
		//initial positions
		final static int FX_X = -FX_IMW;
		final static int FX_Y = View.frameHeight/4;
		final static int FX_XI = 1;
		final static int FX_YI = 0;
		
		//timers
		final static int SUN_MOON_IMAGE_W_H = 100;
				
		//game 2 tutorial
		final static int FOOD_TRASH_IMW = 60;
		final static int FOOD_TRASH_IMH = 50;
		final static int EGG_IMW = 50;
		final static int EGG_IMH = 70;
		final static int PLUS = 40;
		final static int MINUS = 50;
		final static int FOOD_ADD_TO_X = 140;
		final static int FT_ADD_TO_Y4 = 170;
		final static int CX_ADD_TO_Y4 = 180;
		final static int SPACE_IMW = 200;
		final static int SPACE_IMH = 150;
		final static int PLUS_ADDX =200;
		final static int EGG_ADDX = 250;
		final static int MINUS_ADDX = 450;
		final static int EGG_ADDX2 = 500;
		final static int PM_ADD_Y = 250;

		
}