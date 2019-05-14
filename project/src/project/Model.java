package project;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.IntStream;

public class Model {
	
	Player p;
	Fox fox;
	ArrayList<ScoringObject> scoringObjects = new ArrayList<>();
	Scoring score;
	int count;
	Point[] g2locations = new Point[8];
	Point[] clapperlocations = new Point[8];
	boolean[] g2occupancy = new boolean[8];
	boolean g1BoundaryCollision = false;
	boolean g1ScoringObjectCollision = false;
	Random r = new Random();
	GameObjectStorage GobjS = new GameObjectStorage();
	
	
	//******GENERAL******//
	public void addGameObjectStorageToModel(GameObjectStorage GobjS) {
		this.GobjS = GobjS;
	}
	
	public GameObjectStorage getGobjS() {
		return this.GobjS;
	}
	
	/**
	 * calls appropriate updateGame[One/Two] method
	 * 
	 * @param none
	 * @return none
	 */
	public void updateGame(int currentpanel) {
		if (currentpanel == 1) {
			this.updateGameOne();
		}
		if (currentpanel == 2) {
			this.updateGameTwo();
			}
		}
	
	
	//******GAME 1******//
	
	/**
	 * Initializes game 1 by setting new osprey player and adding scoring objects (fish, seaweed) for each level
	 * 
	 * @param none
	 * @return none
	 * @author Ken Chan
	 */
	public void initializeGameOne() {
		GobjS.setPlayer(new Osprey(Constants.OX_I, Constants.OY_I, Constants.OX_INCR_I, Constants.OY_INCR_I, Constants.O_IMW, Constants.O_IMH, GameObjectEnum.g1Osprey));
		score = new Scoring();
		GobjS.setScore(score);
		
		GobjS.getScoringObjects().add(this.createGameOneFish(1));
		GobjS.getScoringObjects().add(this.createGameOneFish(2));
		GobjS.getScoringObjects().add(this.createGameOneFish(3));	
		GobjS.getScoringObjects().add(this.createGameOneSeaweed(1));
		GobjS.getScoringObjects().add(this.createGameOneSeaweed(2));
		GobjS.getScoringObjects().add(this.createGameOneSeaweed(3));
		GobjS.getScoringObjects().add(this.createGameOneRandomFish());
		GobjS.getScoringObjects().add(this.createGameOneRandomFish());
	}
	
	/**
	 * Updates the state of Game 1: Osprey:
	 * updates the scoringObjects (fish and seaweed), updates the location of the player.
	 *
	 * @param none
	 * @return none
	 * @author Hannah Bridge and Brendan Azueta
	 */
	public void updateGameOne() {	
		//System.out.println("Game 1 updated");
		updateGameOneScoringObjects(GobjS.getScoringObjects());
		GobjS.getPlayer().move();
		this.checkIfPlayerCollidesWithBoundary();
		if(GobjS.getPlayer().getYloc() <= Constants.O_YBound) {
			GobjS.getPlayer().setyIncr(0);
			this.g1BoundaryCollision = false;
			this.g1ScoringObjectCollision = false;
		}
	}
	
	/**
	 * Updates the scoring objects for game 1: osprey:
	 * goes through the scoringObjects array list and removes fish and seaweed if they are off screen and creates
	 * new ones each time
	 * Creates a rectangle for each object and using the collisionG1 method it checks if the player's rectangle intersects with
	 * any of the scoring objects. 
	 * 
	 * @param (ArrayList) scoringObjects
	 * @return none
	 * @author Hannah Bridge and Ken Chan
	 */
	public void updateGameOneScoringObjects(ArrayList<ScoringObject> scoringObjects) {
		for(int i = 0; i < scoringObjects.size(); i++) {
			scoringObjects.get(i).move();
			Rectangle o1 = GobjS.getScoringObjects().get(i).getBounds();
			//collisionG1(o1);
			if(this.collisionG1(GobjS.getScoringObjects().get(i).getBounds())) {
				this.score.updateScore(scoringObjects.get(i));
				if(scoringObjects.get(i).GobjEnum != GameObjectEnum.g1Seaweed) {
					scoringObjects.add(this.createGameOneRandomFish());
				}
				else {
					scoringObjects.add(this.createGameOneRandomSeaweed());
				}
				scoringObjects.remove(i);
				System.out.println(this.score.totalScore);
			}
			if(scoringObjects.get(i).GobjEnum != GameObjectEnum.g1Seaweed) {
				if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i))){
					scoringObjects.remove(i);
					scoringObjects.add(this.createGameOneRandomFish());
				}
			}
			if(scoringObjects.get(i).GobjEnum == GameObjectEnum.g1Seaweed) {
				if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).xIncr == Constants.SW1_SPEED) {
					scoringObjects.remove(i);
					scoringObjects.add(this.createGameOneSeaweed(1));
				} 
				else if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).xIncr == Constants.SW2_SPEED) {
					scoringObjects.remove(i);
					scoringObjects.add(this.createGameOneSeaweed(2));
				}
				else if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).xIncr == Constants.SW3_SPEED) {
					scoringObjects.remove(i);
					scoringObjects.add(this.createGameOneSeaweed(3));
				}
			}
		}
	}
	
	public void updateGameOneSeaweed() {	
	}
	
	/**
	 * Checks if the scoringObject is off the screen: returns true if it is, false otherwise
	 * 
	 * @param (ScoringObject) obj
	 * @return (boolean) true/false
	 * @author Ken Chan
	 */
	public boolean checkIfScoringObjectIsOffScreen(ScoringObject obj) {
		if(obj.xloc + obj.imageWidth <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Creates new fish scoring objects for levels 1, 2, 3
	 * 
	 * @param (int) fishLevel
	 * @return null
	 * @author Ken Chan
	 */
	public ScoringObject createGameOneFish(int fishLevel) {
		if(fishLevel == 1) {
			return (new ScoringObject(Constants.FX_I, Constants.SO_LEVEL1, Constants.F1_SPEED, Constants.FY_INCR_I, Constants.F1_PV, Constants.F1_IMW, Constants.F_IMH, GameObjectEnum.g1Fish1));
		} if(fishLevel == 2) {
			return (new ScoringObject(Constants.FX_I, Constants.SO_LEVEL2, Constants.F2_SPEED, Constants.FY_INCR_I, Constants.F2_PV, Constants.F2_IMW, Constants.F_IMH, GameObjectEnum.g1Fish2));
		} if(fishLevel == 3) {
			return (new ScoringObject(Constants.FX_I, Constants.SO_LEVEL3, Constants.F3_SPEED, Constants.FY_INCR_I, Constants.F3_PV, Constants.F3_IMW, Constants.F_IMH, GameObjectEnum.g1Fish3));
		}
		return null;
	}
	
	public ScoringObject createGameOneRandomFish() {
		//magic numbers here
		int fishRand = (int)(Math.random() * 9);
		if(fishRand == 0 || fishRand == 1 || fishRand == 2) {
			return this.createGameOneFish(1);
		}
		else if(fishRand == 3 || fishRand == 4) {
			return this.createGameOneFish(3);
		}
		else {
			return this.createGameOneFish(2);
		}
	}
	
	public ScoringObject createGameOneRandomSeaweed() {
		int seaweedRand = (int)((Math.random() * 3) + 1);
		return this.createGameOneSeaweed(seaweedRand);
	}
	
	/**
	 * Creates new seaweed scoring objects for levels 1, 2, 3
	 * 
	 * @param (int) seaweedLevel
	 * @return null
	 * @author Hannah Bridge
	 */
	public ScoringObject createGameOneSeaweed(int seaweedLevel) {
		if(seaweedLevel == 1) {
			return (new ScoringObject(Constants.SWX_I, Constants.SO_LEVEL1, Constants.SW1_SPEED, Constants.SWY_INCR_I, Constants.SW_PV, Constants.SW_IMW, Constants.SW_IMH, GameObjectEnum.g1Seaweed));
		}
		if(seaweedLevel == 2) {
			return (new ScoringObject(Constants.SWX_I, Constants.SO_LEVEL2, Constants.SW2_SPEED, Constants.SWY_INCR_I, Constants.SW_PV, Constants.SW_IMW, Constants.SW_IMH, GameObjectEnum.g1Seaweed));
		} 
		if(seaweedLevel == 3) {
			return (new ScoringObject(Constants.SWX_I, Constants.SO_LEVEL3, Constants.SW3_SPEED, Constants.SWY_INCR_I, Constants.SW_PV, Constants.SW_IMW, Constants.SW_IMH, GameObjectEnum.g1Seaweed));
		}
		return null;
	}
	
	/**
	 * Creates a rectangle object for the Osprey. Checks if the player intersects with any of the rectangles.
	 * If the Osprey intersects with any of the scoring objects it returns true, otherwise false.
	 * 
	 * @param (Rectangle) object
	 * @return (boolean) true/false
	 * @author Brendan Azueta
	 */

	public boolean collisionG1(Rectangle o1) {
		Rectangle OP = GobjS.getPlayer().getBounds();
		if(!this.g1BoundaryCollision && !this.g1ScoringObjectCollision && OP.intersects(o1)) {
			System.out.println("Collision detected");
			this.g1ScoringObjectCollision = true;
			GobjS.getPlayer().setyIncr(Constants.O_upwardsYIncr);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkIfPlayerCollidesWithBoundary() {
		if(GobjS.getPlayer().getYloc() + GobjS.getPlayer().getImageHeight() >= View.frameHeight) {
			this.g1BoundaryCollision = true;
			GobjS.getPlayer().setyIncr(Constants.O_upwardsYIncr);
			return true;
		}
		else
			return false;
	}

	public void fishOrSeaWeed(ArrayList<ScoringObject> scoringObjects) {
		for(int i = 0; i < scoringObjects.size(); i++) {
			Rectangle o1 = GobjS.getScoringObjects().get(i).getBounds();
			if(collisionG1(o1) == true) {
				if(scoringObjects.get(i).GobjEnum == GameObjectEnum.g1Fish1 || scoringObjects.get(i).GobjEnum == GameObjectEnum.g1Fish2 || scoringObjects.get(i).GobjEnum == GameObjectEnum.g1Fish3) {
					System.out.println("its a fish");
				} 
				else if(scoringObjects.get(i).GobjEnum == GameObjectEnum.g1Seaweed) {
					System.out.println("its a seaweed");
				}
			}
		}
	}
		
	
	
	//******GAME 2******//
	/**
	 * Updates the state of Game 2: Clapper Rail:
	 * creates and removes scoringObjects (food and trash), updates the location of the player.
	 * 
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void updateGameTwo() {
		if (count % Constants.refreshTime == 0) {
			for (int i=0; i < Constants.numNew; i++) {
				createFoodOrTrash();
			}
		}		
		count ++;
		GobjS.getPlayer().move();
		if (count > Constants.foxTime) {
			GobjS.getFox().move();
		}
		updateFoodAndTrash();
	}
	
	/**
	 * Creates a new scoringObject that is "placed" in a random vacant Point from g2locations, and updates the 
	 * respective g2occupancy to true. 
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void createFoodOrTrash() {
		int rand = r.nextInt(8);
		int randFood = r.nextInt(2)+1;
		int randTrash = r.nextInt(2)+1;
		while (g2occupancy[rand] == true) {
			rand = r.nextInt(8);
		}
		int pointValue = foodOrTrash();
		GameObjectEnum gobje;
		if(pointValue == 1) {
			if(randFood == 1){
				gobje = GameObjectEnum.g2Food;
			}
			else {
				gobje = GameObjectEnum.g2Food2;
			}
		}
		else {
			if(randTrash == 1) {
				gobje = GameObjectEnum.g2Trash;
			}
			else {
				gobje = GameObjectEnum.g2Trash2;
			}
		}
		GobjS.getScoringObjects().add(new ScoringObject(g2locations[rand].x,g2locations[rand].y, Constants.FT_XI, Constants.FT_YI, pointValue, Constants.FT_IMW, Constants.FT_IMH, gobje));
		g2occupancy[rand] = true;
	}
	
	
	/**
	 * Iterates through scoringObjects arraylist and removes scoringObjects whose "lifetime" is over and should be removed from game.
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void updateFoodAndTrash() {
		Iterator<ScoringObject> it = GobjS.getScoringObjects().iterator();
		while (it.hasNext()) {
			ScoringObject o = it.next();
			if (o.lifetime>o.g2_lifetime) {
				it.remove();
				g2occupancy[findFoodOrTrash(o)] = false;
			}
			else {
				o.lifetime++;
			}
		}
	}
	
	/**
	 * Returns which of the 8 locations of ScoringObjects(food or trash) in game 2
	 * a specific scoringObject is loaded. 
	 * 
	 * @param ScoringObject o
	 * @return int: position 
	 * @author Anna Bortle
	 */
	public int findFoodOrTrash(ScoringObject o) {
		Point loc = new Point(o.getXloc(), o.getYloc());
		for (int i = 0; i < g2locations.length; i++) {
			if (loc.equals(g2locations[i])) {
				return i; 
			}
		}
		return -1;
	}
	
	/**
	 * If g2occupancy has a location, it iterates through scoringObjects arraylist and removes scoringObjects 
	 * whose location is the same as the clapper rail's location and updates the score appropriately based on 
	 * if food or trash was at that location
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void eatFoodOrTrash() {
		int loc = findClapperRail();
		if (g2occupancy[loc] != false) {
			Iterator<ScoringObject> it = GobjS.getScoringObjects().iterator();
			while (it.hasNext()) {
				ScoringObject o = it.next();
				if (o.getXloc() == g2locations[loc].x && o.getYloc() == g2locations[loc].y){
					score.updateScore(o);
					it.remove();
					g2occupancy[loc] = false;
					System.out.println(score.totalScore);
				}
			}
		}
		else {
			//do nothing (clapper rail is not on a spot with food or trash)
		}
	}
	
	/**
	 * Creates a new point representing the clapper rail's x and y location. Goes through the clapperlocations
	 * array to see if the clapper rail is at one of those 8 positions and returns the location.
	 * 
	 * @param none
	 * @return (int) location of clapper rail
	 * @author Anna Bortle
	 */
	public int findClapperRail() {
		int loc = -1;
		Point cr_loc = new Point(GobjS.getPlayer().getXloc(), GobjS.getPlayer().getYloc());
		for (int i = 0; i < clapperlocations.length; i++) {
			if (cr_loc.equals(clapperlocations[i])) {
				return i; 
			}
		}
		return loc;
	}
	
	/**
	 * Initializes game 2 by creating the 8 locations for the clapper rail (4 on the top row, 4 on the bottom row)
	 * and setting new clapper rail player
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void initializeGameTwo() {
		for (int i = 0; i < 8; i++) {
			g2occupancy[i] = false;
			if (i < 4) {
				clapperlocations[i] = new Point(Constants.CRX_I+Constants.CR_X*(i), Constants.CRY_I);	
				g2locations[i] = new Point(Constants.G2X*(i+1), Constants.G2Y);
			}
			else {
				clapperlocations[i] = new Point(Constants.CRX_I + Constants.CR_X*(i-4), Constants.CRY_I+Constants.CR_Y);
				g2locations[i] = new Point(Constants.G2X*(i-3), Constants.G2Y2);
			}
		}	
		GobjS.setPlayer(new ClapperRail(Constants.CRX_I, Constants.CRY_I, Constants.CRX_INCR_I, Constants.CRY_INCR_I, Constants.CR_IMW, Constants.CR_IMH, GameObjectEnum.g2ClapperRail));
		GobjS.setFox(new Fox(Constants.FX_X, Constants.FX_Y, Constants.FX_XI, Constants.FX_YI, Constants.FX_IMW, Constants.FX_IMH, GameObjectEnum.g2Fox));
		score = new Scoring();
		GobjS.setScore(score);
	}
	
	/**
	 * Returns either a -1 or 1.
	 * Return value used to assign a point value to a scoringObject in Game 2 (Clapper Rail).
	 * A value of -1 denotes trash, and 1 denotes food.
	 * 
	 * @param none
	 * @return integer: random -1 or 1
	 * @author Anna Bortle
	 */
	public int foodOrTrash() {
		int i = r.nextInt();
		if (i%2 == 0) {
			return -1;
		}
		else {
			return 1;
		}
	}
	
}


