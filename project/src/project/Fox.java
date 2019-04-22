package project;

import java.awt.image.BufferedImage;
import java.util.*;

public class Fox{
	int xloc;
	int yloc;
	int xincr;
	int yincr;
	ArrayList<Question> questions= new ArrayList<>();
	BufferedImage[][] imageArray;
	
	Fox(int xloc,int yloc,int xincr,int yincr){
		this.xloc =xloc;
		this.yloc = yloc;
		this.xincr = xincr;
		this.yincr = yincr;
		
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
		questions.add(new Question("Clapper Rails live in...", "marshes", "trees", 1));
		
	}
	
	/**
	 * updates x and y locations based on the x and y increment
	 * @param none
	 * @return none
	 * @author Celeste Lemus
	 */
	public void moveEnter() {
		xloc+=xincr;
		yloc += yincr;
		
		
	}
	
	/**
	 * updates x and y locations based on the negative x and y increment
	 * @param none
	 * @return none
	 * @author Celeste Lemus
	 */
	public void moveExit() {
		xloc+=-xincr;
		yloc +=-yincr;
		
	}

	public void setXloc(int i) {
		this.xloc = i;
		
	}

	public void setYloc(int i) {
		this.yloc = i;
		
	}

	public void setXincr(int i) {
		this.xincr = i;
		
	}

	public void setYincr(int i) {
		this.yincr = i;
		
	}

	public int getXloc() {
		// TODO Auto-generated method stub
		return this.xloc;
	}
	public int getYloc() {
		// TODO Auto-generated method stub
		return this.yloc;
	}
	public int getXincr() {
		// TODO Auto-generated method stub
		return this.xincr;
	}
	public int getYincr() {
		// TODO Auto-generated method stub
		return this.yincr;
	}

}