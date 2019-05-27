package project;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class ClapperRail extends Player{

	ClapperRail(int x, int y, int xInc, int yInc, int iW, int iH, GameObjectEnum GobjEnum) {
		super(x, y, xInc, yInc, iW, iH, GobjEnum);
	}
	
	/**
	 * updates x location and y location for clapper rail object
	 * @param none
	 * @return none
	 * @author Hannah Bridge
	 */
	public void move() {
		xloc += xIncr;
		xIncr = 0;
		yloc += yIncr;
		yIncr = 0;
		super.updateBounds();
	}
	
	/**
	 * Returns a Point object that represents CR's current x,y location
	 * @param none
	 * @return	Point : CR x,y location
	 */
	public Point getPointLoc() {
		return (new Point(xloc,yloc));
	}
}