package project;

import java.awt.Point;

public class SunTimer {
	int imageWidth = 100;
	int imageHeight = 100;
 	int radius = View.frameWidth+(View.frameWidth/8);
	double radian = Math.toRadians(230);
	
	double speed = 0.0011;
	Point center = new Point(View.frameWidth/2, View.frameHeight*2);
	int xLoc = center.x + (int)(radius*Math.cos(radian)) - imageWidth/2; 
	int yLoc = center.y + (int)(radius*Math.sin(radian));
	
	public void move() {
		radian += speed;
		xLoc = center.x + (int)(radius*Math.cos(radian))- imageWidth/2; 
		yLoc = center.y + (int)(radius*Math.sin(radian));
	}
	
	public int getXLoc() {
		return xLoc;
	}
	public int getYLoc() {
		return yLoc;
	}
}
