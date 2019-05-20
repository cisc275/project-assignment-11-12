package project;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;


public class Controller implements KeyListener {
	
	Model model;
	View view;
	Quiz quiz;
	MenuPopUp mpop;
	endTutorialPopUp tutpop;
	Timer t;
	final int drawDelay = 25; // change this to 25
	Action drawAction;
	private int clockcount = 0;
	int currentpanel;
	
	int g1_spaceCooldown = Constants.G1_SPACEBAR_COOLDOWN; // need to add a visual representation of this
	boolean menuflag = false;
	boolean quizflag = false;
	boolean tutpopflag = false;

	//Tutorial 2 counts
	int arrowcount = 0;
	
	Controller(){
		this.initializeView();
		this.initializeModel();
		drawAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
					view.repaint();
					view.addGameObjectStorageToView(model.getGobjS());
					model.updateGame(currentpanel);
					view.setG1EnergyCount(model.g1NoEnergyCount);
					checkQuiz();
					checkMenu();
					updateTutorial();
					
					if(g1_spaceCooldown > 0) {
						g1_spaceCooldown--;
					}
					if (view.tutorialflag == false) {
						clockcount++;
					}
					
					if (clockcount > (Constants.GAME_LENGTH/drawDelay)) { //2000*drawDelay[30] = 60000 = 1.0min
						endGame();	
					}
					if (model.g1NoEnergy) {
						endGame();
					}
			}
		};
		
	}
	
	/**
	 * Checks for the existence of a quiz and adds itself to it as an ActionListener.
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void checkQuiz() {
		if (model.GobjS.getFox() != null) {
			if (model.GobjS.getFox().flag == false) {
				quizflag = true;
				model.GobjS.getFox().quiz.addListenertoQuiz(this);
				model.GobjS.getFox().quiz.setVisible(true); 
				model.GobjS.getFox().flag = true;
				quizflag = false;
			}
		}
	}
	
	/**
	 * Initializes view, adds a key listener for when keys are pressed.
	 * 
	 * @param none
	 * @return none
	 */
	public void initializeView() {
		view = new View();
		view.addKeyListener(this);
		view.setFocusable(true);
		view.setFocusTraversalKeysEnabled(false);
	}
	
	/**
	 * Switches to the appropriate panel after the game ends.
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void endGame() {
		if (currentpanel == 1) {
			view.cl.show(view.panelContainer, "3");
			currentpanel = 3;
		}
		else {
			view.cl.show(view.panelContainer, "4");
			currentpanel = 4;
		}
	}
	
	/**
	 * Creates a new model. 
	 * 
	 * @param none
	 * @return none
	 */
	public void initializeModel() {
		model = new Model();
	}

	/**
	 * Starts the game.
	 * 
	 * @param none
	 * @return none
	 */
	public void start(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Timer t = new Timer(drawDelay, drawAction); //call drawAction every (drawDelay) msecs
				t.start();
				} 
		});	
		
		
	}
	
	/**
	 * Checks to see which panel/pop up player is on. Calls actions depending on which keys are pressed and which 
	 * panels/pop ups they correspond to. 
	 * 
	 * @param key that was pressed (key event)
	 * @return none
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (currentpanel == 0) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_1:
				//System.out.println("game1");
				view.cl.show(view.panelContainer, "1");
				currentpanel = 1;
				model.initializeGameOne();
				break;
			case KeyEvent.VK_2:
				System.out.println("game2");
				view.cl.show(view.panelContainer, "2");
				currentpanel = 2;
				model.initializeGameTwo();
				break;
			}
		}
		else if (currentpanel != 0) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_M:
					menuflag = true;
					break;
			}
		}
		if (menuflag) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_Y:
					model.getGobjS().getScoringObjects().removeAll(model.getGobjS().getScoringObjects()); //clear scoring objects
					view.cl.show(view.panelContainer, "0");
					clockcount = 0;
					currentpanel = 0;
					mpop.dispose();
					break;
				case KeyEvent.VK_C:
					mpop.dispose();
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
			}
		}
		if (tutpopflag) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_P:
				tutpop.dispose();
				break;
		}
		}
		if(currentpanel == 2) {
			int k = e.getKeyCode();
			switch(k) { 
	        	case KeyEvent.VK_UP:
	        		if (model.getGobjS().getPlayer().getYloc() - Constants.CR_Y >= Constants.CRX_I) {
	        			model.getGobjS().getPlayer().setyIncr(-Constants.CR_Y);
	        		}
	        		if (!view.learnmovementflag) {
	        			arrowcount++;
	        			System.out.println(arrowcount);
	        			if (arrowcount > 10) {
	        				view.learnmovementflag=true;
	        			}
	        		}
	        		System.out.println("up");
	        		break;
	        		
	        	case KeyEvent.VK_DOWN:
	        		if (model.getGobjS().getPlayer().getYloc() + Constants.CR_Y  <= Constants.CRY_I+Constants.CR_Y) {
	        			model.getGobjS().getPlayer().setyIncr(Constants.CR_Y);
	        		}
	        		if (!view.learnmovementflag) {
	        			arrowcount++;
	        			System.out.println(arrowcount);
	        			if (arrowcount > 10) {
	        				view.learnmovementflag=true;
	        			}
	        		}
	        		System.out.println("down");
	        		break;
	        		
	        	case KeyEvent.VK_LEFT:
	        		System.out.println("left");
	        		if (!(model.getGobjS().getPlayer().getXloc() - Constants.CR_X < 0)){
	        			model.getGobjS().getPlayer().setxIncr(-Constants.CR_X);
	        		}
	        		if (!view.learnmovementflag) {
	        			arrowcount++;
	        			System.out.println(arrowcount);
	        			if (arrowcount > 10) {
	        				view.learnmovementflag=true;
	        			}
	        		}
	        		break;
	        	case KeyEvent.VK_RIGHT :
	        		System.out.println("right");
	        		if (!(model.getGobjS().getPlayer().getXloc() + model.getGobjS().getPlayer().getImageWidth()*2 
	        				+ Constants.CR_X > View.frameWidth)) {
	        			model.getGobjS().getPlayer().setxIncr(Constants.CR_X);
	        		}
	        		if (!view.learnmovementflag) {
	        			arrowcount++;
	        			System.out.println(arrowcount);
	        			if (arrowcount > 10) {
	        				view.learnmovementflag=true;
	        			}
	        		}
	        		break;
	        	case KeyEvent.VK_SPACE:
	        		model.eatFoodOrTrash();
	        		break;
			}
			if (quizflag) {
				switch (e.getKeyCode()) {
	        	case KeyEvent.VK_1:
	        		model.updateQuizScore(1, model.GobjS.getFox().quiz.correctAns);
	        		model.GobjS.getFox().quiz.dispose();
	        		break;
	        	case KeyEvent.VK_2:
	        		model.updateQuizScore(2, model.GobjS.getFox().quiz.correctAns);
	        		model.GobjS.getFox().quiz.dispose();
	        		break;
				}
			}
			}
			if(currentpanel == 1) {
				//System.out.println("game1");
				int k2 = e.getKeyCode();
				switch( k2 ) { 
	        		case KeyEvent.VK_SPACE:
	        			if(!model.g1BoundaryCollision && !model.g1ScoringObjectCollision && g1_spaceCooldown == 0) {
		        			model.getGobjS().getPlayer().setyIncr(-Constants.O_upwardsYIncr);
		        			g1_spaceCooldown = (Constants.G1_SPACEBAR_COOLDOWN/model.g1PityCounter);
		        		}
	        			//System.out.println("space");
	        			break;
			}
		}
		
	}
	
	/**
	 * Checks to see which key was released. 
	 * 
	 * @param key that was released after being pressed (key event)
	 * @return none
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE && currentpanel == 1) {
			//model.getGobjS().getPlayer().setyIncr(-O_Y);
		}
		/*
		if(key == KeyEvent.VK_SPACE && currentpanel == 2) {
			model.getGobjS().getPlayer().setyIncr(-Constants.CR_Y_SPACE);
		}
		*/
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
	/**
	 * Checks if menu is shown. 
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void checkMenu() {
		if (menuflag) {
			mpop = new MenuPopUp();
			mpop.addKeyListener(this);
			mpop.setVisible(true); 
			menuflag = false;
		}
	}
	
	public void updateTutorial() {
		if (currentpanel == 2) {
			if (!view.tutorialflag && view.tutorialcount == 1) {
				tutpop = new endTutorialPopUp();
				tutpopflag = true;
				tutpop.addKeyListener(this);
				tutpop.setVisible(true);
				tutpopflag = false;
				
				view.tutorialcount++;
				Constants.g2_lifetime = 50;
				Constants.refreshTime = Constants.g2_lifetime+10;
				model.tutorialflag = false;
				model.getGobjS().getScoringObjects().removeAll(model.getGobjS().getScoringObjects()); //clear scoring objects
				model.initializeGameTwo();
			}
			if (view.tutorialflag && model.getGobjS().scoringObjects.isEmpty()) {
				for (int i = 0; i < 5; i++) {
				model.createFoodOrTrash();
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		Controller c = new Controller();
		c.start();
	}
}