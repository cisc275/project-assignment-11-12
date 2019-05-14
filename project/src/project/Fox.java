package project;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.*;

public class Fox extends GameObject{
	final int finalxloc = 100;
	ArrayList<Question> questions= new ArrayList<>();
	BufferedImage[][] imageArray;
	boolean flag = true;
	
	Fox(int x, int y, int xInc, int yInc, int iW, int iH, GameObjectEnum GobjEnum) {
		super(x, y, xInc, yInc, iW, iH, GobjEnum);
		
		//create questions
		questions.add(new Question("Clapper Rails are...", "migratory", "non-migratory", 2));
		questions.add(new Question("A body of water where a river meets the sea is called an...", "estuary", "actuary", 1));
		questions.add(new Question("Clapper Rails make their nests up high in trees.", "True", "False", 2));
		questions.add(new Question("Which of these would a Clapper Rail most likely eat?", "Fish", "Mouse", 1));
		questions.add(new Question("Clapper Rails spend a lot of their time flying", "True", "False", 2));
		questions.add(new Question("The Red Fox is a natural predator of the Clapper Rail", "True", "False", 1));
		questions.add(new Question("Clapper Rails lay...", "1 large egg", "3-7 small eggs", 2));
		questions.add(new Question("Clapper Rails have beaks that are...", "short and fat", "long and slender", 2));
		questions.add(new Question("The size of a Clapper Rail is similar to the size of a...", "chicken", "cardinal", 1));
		questions.add(new Question("Clapper Rails live in...", "marshes", "forests", 1));
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

	@Override
	public void move() {
		if (xloc <= finalxloc && flag) {
			moveEnter();
			if (xloc == finalxloc) {
				flag = false;
			}
		}
		else {
			moveExit();
		}
	}
}