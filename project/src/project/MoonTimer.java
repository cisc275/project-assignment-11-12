package project;

public class MoonTimer {

		int xLoc;
		int yLoc;
		int xIncr;
		int yIncr;
		int count;
		
		MoonTimer(int xLoc, int yLoc, int xIncr, int yIncr){
			this.xLoc = xLoc;
			this.yLoc = yLoc;
			this.xIncr = xIncr;
			this.yIncr = yIncr;
			this.count = 0;
		}
		
		public void move() {
			count++;
			xLoc += xIncr;
			if (count % 3 == 0 && xLoc >= -100) {
				yLoc += yIncr;
			}
		}
		
		public int getXLoc() {
			return xLoc;
		}
		public int getYLoc() {
			return yLoc;
		}
	}


