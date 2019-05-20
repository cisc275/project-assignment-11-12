package project;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class ClapperRail extends Player{
	
	BufferedImage[][] imageArray;

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
		//System.out.println("clapper rail move method called.");
		xloc += xIncr;
		xIncr = 0;
		yloc += yIncr;
		yIncr = 0;
		super.updateBounds();
	}
	public Point getPointLoc() {
		return (new Point(xloc,yloc));
	}
}