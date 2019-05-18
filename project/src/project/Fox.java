package project;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.*;

public class Fox extends GameObject{
	final int finalxloc = 100;
	
	BufferedImage[][] imageArray;
	boolean flag = true;
	
	Fox(int x, int y, int xInc, int yInc, int iW, int iH, GameObjectEnum GobjEnum) {
		super(x, y, xInc, yInc, iW, iH, GobjEnum);
	}
	
	/**
	 * updates x and y locations based on the x and y increment
	 * @param none
	 * @return none
	 * @author Celeste Lemus
	 */
	public void moveEnter() {
			xloc+=xIncr;
			yloc += yIncr;
	}
	
	/**
	 * updates x and y locations based on the negative x and y increment
	 * @param none
	 * @return none
	 * @author Celeste Lemus
	 */
	public void moveExit() {
		xloc+=-xIncr;
		yloc +=-yIncr;	
	}
	
	public void stop() {
		xIncr = 0;
		yIncr = 0;
	}

	@Override
	public void move() {
		if (xloc <= finalxloc && flag) {
			moveEnter();
			if (xloc == finalxloc) {
				flag = false;
				Quiz quiz = new Quiz();
				//stop();
			}
		}
		else {
			moveExit();
		}
	}
}