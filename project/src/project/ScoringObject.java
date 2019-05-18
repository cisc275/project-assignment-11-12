package project;

import java.awt.image.BufferedImage;

public class ScoringObject extends GameObject{
	String ID;
	int pointValue;
	BufferedImage[][] imageArray;
	
	//Game 1 Variables
	int g1_deltaYCount = -1;
	int deltaY = 0;
	
	//Game 2 Variables
	int lifetime = 0;
	
	ScoringObject(){
		super();
	}
	
	ScoringObject(int x, int y, int xInc, int yInc, int pV, int iW, int iH, GameObjectEnum GobjEnum){
		super(x, y, xInc, yInc, iW, iH, GobjEnum);
		this.pointValue = pV;
	}
	
	/**
	 * updates location(xloc and yloc) for  ScoringObject object
	 * @param none
	 * @return none
	 */
	public void move() {
		this.xloc += this.xIncr;
		this.yloc += this.yIncr;
		if(deltaY == 4){
			g1_deltaYCount = -1;
		}
		if(deltaY == -4) {
			g1_deltaYCount = 1;
		}
		deltaY += g1_deltaYCount;
		//this.deltay = this.deltay * (-1);
		this.yloc += this.yIncr + g1_deltaYCount;
		super.updateBounds();
	}
	
	public int getPointValue() {
		return pointValue;
	}

	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}
	
	public String toString() {
		return this.ID + String.valueOf(xloc);
	}
}