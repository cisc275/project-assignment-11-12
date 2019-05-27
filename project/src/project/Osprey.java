package project;

import java.awt.image.BufferedImage;

public class Osprey extends Player{
	
	Osprey(int x, int y, int xInc, int yInc, int iW, int iH, GameObjectEnum GobjEnum) {
		super(x, y, xInc, yInc, iW, iH, GobjEnum);
	}
	
	/**
	 * updates location(xloc and yloc) for osprey object
	 * @param none
	 * @return none
	 * @author Ken Chan
	 */
	public void move() {
		yloc += yIncr;
		super.updateBounds();
	}
	
}