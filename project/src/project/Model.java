package project;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.IntStream;

public class Model implements java.io.Serializable{
	
	/**
 	Auto Generated serialUID
	**/
	private static final long serialVersionUID = -5969197951986274608L;
	
	Player p;
	Fox fox;
	ArrayList<ScoringObject> scoringObjects = new ArrayList<>();
	GameObjectStorage GobjS = new GameObjectStorage();
	Scoring score;
	
	//Game 1
	int g1NoEnergyCount = 0;
	int g1EnergySnapShot = 0;
	int g1PityCounter = 1;
	int g1OspreyUpdatedHeight = Constants.OY_I;
	int g1TimeMultiplier = 1;
	boolean g1NoEnergy = false;
	boolean g1BoundaryCollision = false;
	boolean g1ScoringObjectCollision = false;
	public boolean tutorialflag1 = true;
	
	//Game 2
	Point[] g2locations;
	Point[] clapperlocations;
	boolean[] g2occupancy;
	boolean g2rightflag = false;
	boolean foxDirectionflag = false;
	public boolean tutorialflag = true;
	
	
	Random r = new Random();
	int timerCount;
	
	//******GENERAL******//
	public void addGameObjectStorageToModel(GameObjectStorage GobjS) {
		this.GobjS = GobjS;
	}
	
	public GameObjectStorage getGobjS() {
		return this.GobjS;
	}
	
	/**
	 * Calls appropriate updateGame[One/Two] method to update current game
	 * 
	 * @param int currentpanel: corresponds to current game playing
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
			this.timerCount = 0;
			GobjS.setPlayer(new Osprey(Constants.OX_I, Constants.OY_I, Constants.OX_INCR_I, Constants.OY_INCR_I, Constants.O_IMW, Constants.O_IMH, GameObjectEnum.g1Osprey));
			score = new Scoring();
			GobjS.setScore(score);
			
			this.g1EnergySnapShot = 0;
			this.g1PityCounter = 1;
			this.g1OspreyUpdatedHeight = Constants.OY_I;
			this.g1TimeMultiplier = 1;
			this.g1BoundaryCollision = false;
			this.g1ScoringObjectCollision = false;
			
			GobjS.getScoringObjects().add(this.createGameOneFish(1));
			GobjS.getScoringObjects().add(this.createGameOneFish(2));
			GobjS.getScoringObjects().add(this.createGameOneFish(3));	
			GobjS.getScoringObjects().add(this.createGameOneSeaweed(1));
			GobjS.getScoringObjects().add(this.createGameOneSeaweed(2));
			GobjS.getScoringObjects().add(this.createGameOneSeaweed(3));
		}
		
		/**
		 * Updates the state of Game 1: Osprey:
		 * updates the scoringObjects (fish and seaweed), updates the location of the player.
		 *
		 * @param none
		 * @return none
		 * @author Ken Chan
		 */
		public void updateGameOne() {	
			//System.out.println("Game 1 updated");
			this.timerCount++;
			if((this.timerCount % Constants.G1_CHECK_ENERGY_FREQUENCY) == 0) {
				this.checkGameOneEnergy();
			}
			updateGameOneScoringObjects(GobjS.getScoringObjects());
			GobjS.getPlayer().move();
			this.checkIfPlayerCollidesWithBoundary();
			if(GobjS.getPlayer().getYloc() <= (this.g1OspreyUpdatedHeight - 25)) {
				GobjS.getPlayer().setyIncr(0);
				this.g1BoundaryCollision = false;
				this.g1ScoringObjectCollision = false;
			}
		}
		
		/**
		 * Checks the amount of energy the osprey has for game 1.
		 * If energy is low, osprey's height decreases
		 * 
		 * @param none
		 * @return none
		 * @author Ken Chan
		 */
		public void checkGameOneEnergy() {
			if(score.getTotalScore() <= (this.g1EnergySnapShot + Constants.G1_NUM_OF_POINTS_NEEDED_FOR_ENERGY)) {
				System.out.println("G1ENERGYSNAPSHOT: " + this.g1EnergySnapShot);
				System.out.println("G1NUMPOINTSNEEDED: " + (score.getTotalScore() + (Constants.G1_NUM_OF_POINTS_NEEDED_FOR_ENERGY * this.g1TimeMultiplier)));
				
				this.g1NoEnergyCount++;
				System.out.println("G1NOENERGYCOUNT: " + this.g1NoEnergyCount);
				this.g1PityCounter++;
			}
			else {
				if(this.g1NoEnergyCount > 0) {
					System.out.println("G1NOENERGYCOUNT: " + this.g1NoEnergyCount);
					this.g1NoEnergyCount--;
					if (this.g1PityCounter > 1) {
						this.g1PityCounter--;
					}
				}
			}
			this.updateOspreyHeight();
			if(this.g1NoEnergyCount == (Constants.G1_NUM_OF_ENERGY_LEVELS - 1)) {
				this.g1NoEnergy = true;
			}
			this.g1EnergySnapShot = score.getTotalScore();
			System.out.println("UPDATEDG1ENERGYS: " + this.g1EnergySnapShot);
		}
		
		/**
		 * Updates the height of the osprey for game 1 depending on how much energy it has
		 * 
		 * @param none
		 * @return none
		 * @author Ken Chan
		 */
		public void updateOspreyHeight() {
			int[] possibleYLoc = new int[Constants.G1_NUM_OF_ENERGY_LEVELS];
			int energyLevelYLoc = Constants.G1_OCEAN_YLOC/Constants.G1_NUM_OF_ENERGY_LEVELS;
			for(int i = 0; i < possibleYLoc.length; i++) {
				possibleYLoc[i] = i * energyLevelYLoc;
			}
			this.g1OspreyUpdatedHeight = Constants.OY_I + possibleYLoc[this.g1NoEnergyCount];
			GobjS.getPlayer().setYloc(this.g1OspreyUpdatedHeight);
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
			this.addFishAndSeaweed(scoringObjects);
			for(int i = 0; i < scoringObjects.size(); i++) {
				scoringObjects.get(i).move();
				if(this.collisionG1(GobjS.getScoringObjects().get(i).getBounds())) {
					this.score.updateScore(scoringObjects.get(i));
					if(scoringObjects.get(i).GobjEnum != GameObjectEnum.g1Seaweed) {
						scoringObjects.add(this.createGameOneRandomFish());
					}
					else {
						scoringObjects.add(this.createGameOneRandomSeaweed());
					}
					scoringObjects.remove(i);
					System.out.println("Total Score: " + this.score.totalScore);
				}
				if(scoringObjects.get(i).GobjEnum != GameObjectEnum.g1Seaweed) {
					if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i))){
						scoringObjects.remove(i);
						scoringObjects.add(this.createGameOneRandomFish());
					}
				}
				if(scoringObjects.get(i).GobjEnum == GameObjectEnum.g1Seaweed) {
					if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i)) ) {
						scoringObjects.remove(i);
						scoringObjects.add(this.createGameOneRandomSeaweed());
					}
				}
			}
		}
		
		/**
		 * Adds more fish and seaweed to game 1
		 * 
		 * @param scoringObjects
		 * @return none
		 * @author Ken Chan
		 */
		public void addFishAndSeaweed(ArrayList<ScoringObject> scoringObjects) {
			//add a pity timer for not catching fish/ slow this down if they are catching fish.
			if((this.timerCount*Constants.DRAW_DELAY) >= Constants.GAME_LENGTH/2){
				this.g1TimeMultiplier++;
			}
			if(timerCount % ((Constants.FS_SPAWN_FREQUENCY / this.g1PityCounter) * this.g1TimeMultiplier)  == 0) {
				scoringObjects.add(this.createGameOneRandomFish());
			}
			if(timerCount % (Constants.FS_SPAWN_FREQUENCY * 2) == 0) {
				scoringObjects.add(this.createGameOneRandomSeaweed());
			}
			
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
				return (new ScoringObject(Constants.FX_I, Constants.SO_LEVEL1, -((int)(Math.random() * 3) + 3), Constants.FY_INCR_I, Constants.F1_PV, Constants.F1_IMW, Constants.F_IMH, GameObjectEnum.g1Fish1));
			} if(fishLevel == 2) {
				return (new ScoringObject(Constants.FX_I, Constants.SO_LEVEL2, -((int)(Math.random() * 3) + 3), Constants.FY_INCR_I, Constants.F2_PV, Constants.F2_IMW, Constants.F_IMH, GameObjectEnum.g1Fish2));
			} if(fishLevel == 3) {
				return (new ScoringObject(Constants.FX_I, Constants.SO_LEVEL3, -((int)(Math.random() * 3) + 4), Constants.FY_INCR_I, Constants.F3_PV, Constants.F3_IMW, Constants.F_IMH, GameObjectEnum.g1Fish3));
			}
			return null;
		}
		
		/**
		 * Creates random new seaweed scoring objects for levels 1, 2, 3
		 * 
		 * @param 
		 * @return null
		 * @author Ken Chan
		 */
		public ScoringObject createGameOneRandomSeaweed() {
			int seaweedRand = (int)(Math.random() * 4);
			if(seaweedRand == 0) {
				return this.createGameOneSeaweed(1);
			}
			else if(seaweedRand == 1) {
				return this.createGameOneSeaweed(2);
			}
			else {
				return this.createGameOneSeaweed(3);
			}
		}
		
		/**
		 * Creates random new fish scoring objects for levels 1, 2, 3
		 * 
		 * @param 
		 * @return null
		 * @author Ken Chan and Brendan Azueta
		 */
		public ScoringObject createGameOneRandomFish() {
			//magic numbers here
			int fishRand = (int)(Math.random() * 7);
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
		
		/**
		 * Creates new seaweed scoring objects for levels 1, 2, 3
		 * 
		 * @param (int) seaweedLevel
		 * @return null
		 * @author Hannah Bridge
		 */
		public ScoringObject createGameOneSeaweed(int seaweedLevel) {
			if(seaweedLevel == 1) {
				return (new ScoringObject(Constants.SWX_I, Constants.SO_LEVEL1, -((int)(Math.random() * 3) + 3), Constants.SWY_INCR_I, Constants.SW_PV, Constants.SW_IMW, Constants.SW_IMH, GameObjectEnum.g1Seaweed));
			}
			if(seaweedLevel == 2) {
				return (new ScoringObject(Constants.SWX_I, Constants.SO_LEVEL2, -((int)(Math.random() * 3) + 3), Constants.SWY_INCR_I, Constants.SW_PV, Constants.SW_IMW, Constants.SW_IMH, GameObjectEnum.g1Seaweed));
			} 
			if(seaweedLevel == 3) {
				return (new ScoringObject(Constants.SWX_I, Constants.SO_LEVEL3, -((int)(Math.random() * 3) + 3), Constants.SWY_INCR_I, Constants.SW_PV, Constants.SW_IMW, Constants.SW_IMH, GameObjectEnum.g1Seaweed));
			}
			return null;
		}
		
		/**
		 * Creates a rectangle object for the Osprey. Checks if the player intersects with any of the rectangles.
		 * If the Osprey intersects with any of the scoring objects it returns true, otherwise false.
		 * 
		 * @param (Rectangle) object
		 * @return (boolean) true/false
		 * @author Brendan Azueta and Ken Chan
		 */
		public boolean collisionG1(Rectangle o1) {
			Rectangle OP = GobjS.getPlayer().getBounds();
			if(!this.g1BoundaryCollision && !this.g1ScoringObjectCollision && OP.intersects(o1)) {
				//System.out.println("Collision detected");
				this.g1ScoringObjectCollision = true;
				GobjS.getPlayer().setyIncr(Constants.O_upwardsYIncr);
				return true;
			} else {
				return false;
			}
		}
	
		/**
		 * Checks if player collides with the boundary
		 * @return
		 * 
		 * @author Ken Chan
		 */
		public boolean checkIfPlayerCollidesWithBoundary() {
			if(GobjS.getPlayer().getYloc() + GobjS.getPlayer().getImageHeight() >= View.frameHeight) {
				this.g1BoundaryCollision = true;
				GobjS.getPlayer().setyIncr(Constants.O_upwardsYIncr);
				return true;
			}
			else
				return false;
		}
		
		/**
		 * Prints out whether the collision between the player and the object was with a fish or seaweed.
		 * @param (ArrayList) object
		 * @return
		 * 
		 * @author Ken Chan and Brendan Azueta
		 */
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
	 * Initializes game 2 by creating the 8 locations for the clapper rail (4 on the top row, 4 on the bottom row)
	 * and setting new clapper rail player
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void initializeGameTwo() {
		this.timerCount = 0;
		initializePositions();
		GobjS.setPlayer(new ClapperRail(Constants.CRX_I, Constants.CRY_I, Constants.CRX_INCR_I, Constants.CRY_INCR_I, Constants.CR_IMW, Constants.CR_IMH, GameObjectEnum.g2ClapperRail));
		GobjS.setFox(new Fox(Constants.FX_X, Constants.FX_Y, Constants.FX_XI, Constants.FX_YI, Constants.FX_IMW, Constants.FX_IMH, GameObjectEnum.g2Fox));
		GobjS.setSunTimer(new SunTimer());
		GobjS.setMoonTimer(new MoonTimer());
		score = new Scoring();
		GobjS.setScore(score);
	}
	
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
		if (tutorialflag) {
			GobjS.getPlayer().move();
			updateFoodAndTrash();
		}
		else {
			if (timerCount % Constants.refreshTime == 0) {
				for (int i=0; i < Constants.numNew; i++) {
					createFoodOrTrash();
				}
			}		
			if (timerCount > Constants.foxTime) {
				GobjS.getFox().move();
			}
			if (GobjS.getFox().getxIncr() < 0) {
				foxDirectionflag=true;
			}
			else{foxDirectionflag = false;}
		
			timerCount ++;
			GobjS.getPlayer().move();
			updateFoodAndTrash();
			GobjS.getSunTimer().move();
			GobjS.getMoonTimer().move();
		}
	}
	
	/**
	 * Updates the location of Clapper Rail.
	 * Used in determining appropriate image for CR.
	 * 
	 * @param none
	 * @return int (location)
	 * @author Anna Bortle
	 */
	public int updateCRloc() {
		int loc;
		int o = findClapperRail();
		if (o > 4) { 			//in the water
			if (g2rightflag) {
				loc = 1;
			}
			else { loc = 3;}
		}
		else { 					//on land
			if (g2rightflag) {
				loc = 0;
			}
			else {loc = 2;}
		}
		return loc;
	}
	
	/**
	 * Creates a new scoringObject (food or trash) that is "placed" in a random vacant Point from g2locations,
	 * and updates the 
	 * respective g2occupancy to true. 
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void createFoodOrTrash() {
		int rand = r.nextInt(8);
		int randFood = r.nextInt(4)+1;
		int randTrash = r.nextInt(4)+1;
		while (g2occupancy[rand] == true) {
			rand = r.nextInt(8);
		}
		int pointValue = foodOrTrash();
		GameObjectEnum gobje;
		if(pointValue == 1) {
			switch (randFood) {
			case 1:
				gobje = GameObjectEnum.g2Food;
				GobjS.getScoringObjects().add(new ScoringObject(g2locations[rand].x,g2locations[rand].y, Constants.FT_XI, Constants.FT_YI, pointValue, Constants.FT_IMW, Constants.FT_IMH, gobje));
				break;
			case 2:
				gobje = GameObjectEnum.g2Food2;
				GobjS.getScoringObjects().add(new ScoringObject(g2locations[rand].x,g2locations[rand].y, Constants.FT_XI, Constants.FT_YI, pointValue, Constants.FT_IMW, Constants.FT_IMH, gobje));
				break;
			case 3:
				gobje = GameObjectEnum.g2Food3;
				GobjS.getScoringObjects().add(new ScoringObject(g2locations[rand].x,g2locations[rand].y, Constants.FT_XI, Constants.FT_YI, pointValue, Constants.FT_IMW, Constants.FT_IMH, gobje));
				break;
			case 4:
				gobje = GameObjectEnum.g2Food4;
				GobjS.getScoringObjects().add(new ScoringObject(g2locations[rand].x,g2locations[rand].y, Constants.FT_XI, Constants.FT_YI, pointValue, Constants.FT_IMW, Constants.FT_IMH, gobje));
				break;
			}
		}
		else {
			switch (randTrash) {
			case 1:
				gobje = GameObjectEnum.g2Trash;
				GobjS.getScoringObjects().add(new ScoringObject(g2locations[rand].x,g2locations[rand].y, Constants.FT_XI, Constants.FT_YI, pointValue, Constants.FT_IMW, Constants.FT_IMH, gobje));
				break;
			case 2:
				gobje = GameObjectEnum.g2Trash2;
				GobjS.getScoringObjects().add(new ScoringObject(g2locations[rand].x,g2locations[rand].y, Constants.FT_XI, Constants.FT_YI, pointValue, Constants.FT_IMW, Constants.FT_IMH, gobje));
				break;
			case 3:
				gobje = GameObjectEnum.g2Trash3;
				GobjS.getScoringObjects().add(new ScoringObject(g2locations[rand].x,g2locations[rand].y, Constants.FT_XI, Constants.FT_YI, pointValue, Constants.FT_IMW, Constants.FT_IMH, gobje));
				break;
			case 4:
				gobje = GameObjectEnum.g2Trash4;
				GobjS.getScoringObjects().add(new ScoringObject(g2locations[rand].x,g2locations[rand].y, Constants.FT_XI, Constants.FT_YI, pointValue, Constants.FT_IMW, Constants.FT_IMH, gobje));
				break;
			}
		}
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
			if (o.lifetime>Constants.g2_lifetime) {
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
	 * Sets up the locations for clapper rail and food/trash
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void initializePositions() {
		g2locations = new Point[10];
		clapperlocations = new Point[10];
		g2occupancy = new boolean[10];
		for (int i = 0; i < 10; i++) {
			g2occupancy[i] = false;
			if (i < 5) {
				clapperlocations[i] = new Point(Constants.CRX_I+Constants.CR_X*(i), Constants.CRY_I);	
				g2locations[i] = new Point(Constants.G2X_I + Constants.G2_X*(i), Constants.G2_Y);
			}
			else {
				clapperlocations[i] = new Point(Constants.CRX_I + Constants.CR_X*(i-5), Constants.CRY_I+Constants.CR_Y);
				g2locations[i] = new Point(Constants.G2X_I + Constants.G2_X*(i-5), Constants.G2_Y2);
			}
		}
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
	
	/**
	 * Updates the totalScore based on the quiz results
	 * 
	 * @param int ans: answer chosen by user, int correctAns: correct answer
	 * @return none
	 * @author Anna Bortle
	 **/
	public void updateQuizScore(int ans, int correctAns) {
		if (ans == correctAns) {
			score.totalScore += 3;
		}
		else {
			score.totalScore -= 3;
		}
	}
	
}


