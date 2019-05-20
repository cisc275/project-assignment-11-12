package project;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import org.junit.Test;

/**
 * @author Anna Bortle
 *
 */
public class ModelTest {
/*
	@Test
	public void clapperRailTest() {
		ClapperRail cr = new ClapperRail(0,0,2,2,100,100,GameObjectEnum.g2ClapperRail);
		assertNotNull(cr);
		
		//test "setters"
		cr.setXloc(10);
		cr.setYloc(10);
		cr.setxIncr(5);
		cr.setyIncr(-5);
		
		//test move()
		cr.move();
		
		//test "getters"
		int actualx = cr.getXloc();
		int actualy = cr.getYloc();
		int actualxIncr = cr.getxIncr();
		int actualyIncr = cr.getyIncr();
		int actualimagewid = cr.getImageWidth();
		int actualimagehgt = cr.getImageHeight();
		
		assertEquals(actualx, cr.xloc);
		assertEquals(actualy, cr.yloc);
		assertEquals(actualxIncr, cr.xIncr);
		assertEquals(actualyIncr, cr.yIncr);
		assertEquals(actualimagewid, cr.imageWidth);
		assertEquals(actualimagehgt, cr.imageHeight);
		
	}
	
	
	@Test
	public void modelTest() {
		Model m = new Model();
		
		GameObjectStorage gos = new GameObjectStorage();
		m.addGameObjectStorageToModel(gos);
		assertEquals(gos, m.getGobjS());
		//need view for switching games to test
		View v = new View();
		
		m.initializeGameOne();
		
		m.updateGame();
		
		for (int i=0;i<500;i++) {
			m.updateGameOne();
		}
		
		m.initializeGameTwo();
		m.updateGameTwo();
		m.findClapperRail();
		m.eatFoodOrTrash();
		m.findFoodOrTrash(m.getGobjS().scoringObjects.get(0));
		
		assertNotNull(m.scoringObjects);
		assertNotNull(m.g2locations);
		assertNotNull(m.g2occupancy);
		
	}
	
	@Test
	public void ospreyTest() {
		Osprey o = new Osprey(0,0,2,2,100,100, GameObjectEnum.g1Osprey);
		assertNotNull(o);
		String o_file = "images/o_temp.png";
		String o_name = "o_temp";
		assertEquals(o_file, o.GobjEnum.getFullImagePath());
		assertEquals(o_name, o.GobjEnum.getImageFileName());
		
		o.loadImage();
		o.getImageArray();
		o.getImg();
		o.setImageArray(null);
		o.setImg(null);
		//test "setters"
		o.setXloc(10);
		o.setYloc(900);
		o.setxIncr(5);
		o.setyIncr(-5);
		
		//test move()
		o.move();
		
		//test "getters"
		int actualx = o.getXloc();
		int actualy = o.getYloc();
		int actualxIncr = o.getxIncr();
		int actualyIncr = o.getyIncr();
		int actualimagewid = o.getImageWidth();
		int actualimagehgt = o.getImageHeight();
		
		assertEquals(actualx, o.xloc);
		assertEquals(actualy, o.yloc);
		assertEquals(actualxIncr, o.xIncr);
		assertEquals(actualyIncr, o.yIncr);
		assertEquals(actualimagewid, o.imageWidth);
		assertEquals(actualimagehgt, o.imageHeight);
	}
	
	@Test
	public void questionTest() {
		Question q = new Question("question", "ans1", "ans2", 1);
		assertNotNull(q);
	}
	
	@Test
	public void controllerTest() {
		Controller c = new Controller();
		assertNotNull(c);
		assertEquals(c.upflag, false);
		assertEquals(c.downflag, true);
		c.main(null);
		c.endGame();
		
		
			
	}
	
	@Test
	public void scoringTest() {
		Scoring s = new Scoring();
		assertNotNull(s);
		assertNotNull(s.ScoringTable);
		
		ScoringObject sc = new ScoringObject(0, 0, 10, 10, 1, 100, 100, GameObjectEnum.g2Food);
		s.updateScore(sc);
		int score = 1;
		assertEquals(s.totalScore, score);
		assertEquals(s.toString(), "Final Score: 1");
		int amountOfFood = 1;
		assertEquals(s.amountContained(sc.GobjEnum),amountOfFood);
		
	}
	
	@Test
	public void viewTest() {
		View v = new View();
		BufferedImage bi = v.createBufferedImage();
		assertNotNull(bi);
		v.initializeBackground();
		
	}
	
	@Test
	public void scoringObjectTest() {
		ScoringObject so = new ScoringObject(10,10,1,1,2,100,100, GameObjectEnum.g1Fish1);
		ScoringObject so2 = new ScoringObject();
		assertNotNull(so);
		assertNotNull(so2);
		
		//test move()
		so.move();
		System.out.println(so.toString());
		
		//test "setters"
		so.setXloc(100);
		so.setYloc(100);
		so.setxIncr(2);
		so.setyIncr(2);
		so.setPointValue(3);
		so.setImageWidth(10);
		so.setImageHeight(10);
		
		//test "getters"
		int realx = so.getXloc();
		int realy = so.getYloc();
		int realxIncr = so.getxIncr();
		int realyIncr = so.getyIncr();
		int realpointValue = so.getPointValue();
		int realimageW = so.getImageWidth();
		int realimageH = so.getImageHeight();

		assertEquals(realx, so.getXloc());
		assertEquals(realy, so.getYloc());
		System.out.println(realxIncr);
		System.out.println(so.getxIncr());
		assertEquals(realxIncr, so.getxIncr());
		assertEquals(realyIncr, so.getyIncr());
		assertEquals(realpointValue, so.getPointValue());
		assertEquals(realimageW, so.getImageWidth());
		assertEquals(realimageH, so.getImageHeight());
	}
	
	@Test
	public void foxTest() {
		Fox f = new Fox(5,5,1,1,30,30,GameObjectEnum.g2Fox);
		assertNotNull(f);
		
		assertEquals(10, f.questions.size());
		
		//test "setters"
		f.setXloc(20);
		f.setYloc(20);
		f.setxIncr(2);
		f.setyIncr(-2);
		
		//test moveEnter()
		f.moveEnter();
		
		//test moveExit()
		f.moveExit();
		
		//test "getters"
		int realx = f.getXloc();
		int realy = f.getYloc();
		int realxIncr = f.getxIncr();
		int realyIncr = f.getyIncr();

		assertEquals(realx, f.xloc);
		assertEquals(realy, f.yloc);
		assertEquals(realxIncr, f.xIncr);
		assertEquals(realyIncr, f.yIncr);

	}
*/
	
}